package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterChallengToday;
import net.getlearn.getlearn_android.adapter.RvAdapterChallengWeek;
import net.getlearn.getlearn_android.adapter.RvAdapterSignIn;
import net.getlearn.getlearn_android.model.bean.PersionalHomeModel;
import net.getlearn.getlearn_android.model.bean.TodayChallengeModel;
import net.getlearn.getlearn_android.model.bean.UserSignModel;
import net.getlearn.getlearn_android.model.bean.WeekChallengeModel;
import net.getlearn.getlearn_android.model.bean.WeekSignModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/9------更新------
 * 挑战中心
 */

public class ChallengeCenterActivity extends BaseActivity implements RvAdapterSignIn.OnItemClickListener {

    @BindView(R.id.tv_rule)
    TextView mTvRule;
    @BindView(R.id.tv_exchange_prize)
    TextView mTvExchangePrize;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.rv_sign_in)
    RecyclerView mRvSignIn;
    @BindView(R.id.rv_today_challenge)
    RecyclerView mRvTodayChallenge;
    @BindView(R.id.rv_week_challenge)
    RecyclerView mRvWeekChallenge;
    @BindView(R.id.spring_view)
    SpringView springView;
    @BindView(R.id.tv_integrals)
    TextView mTvIntegrals;
    @BindView(R.id.tv_series_sign_in)
    TextView mTvSeriesSignIn;
    @BindView(R.id.ll_today_challenge)
    LinearLayout llTodayChallenge;
    @BindView(R.id.ll_week_challenge)
    LinearLayout llWeekChallenge;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    private RvAdapterSignIn mAdapterSignIn;
    private IOSAlertDialog myDialog;
    private String mRule;
    private int today_per_page;
    private int today_current_page;
    private int week_per_page;
    private int week_current_page;
    private RvAdapterChallengToday mAdapterChallengToday;
    private RvAdapterChallengWeek mAdapterChallengWeek;
    private WeekSignModel.Databean mWeekSignData;
    private TodayChallengeModel.DatabeanX mTodayData;
    private WeekChallengeModel.DatabeanX mWeekData;
    private PersionalHomeModel.Databean mPersionalModel;

    @Override
    protected void initData() {
        today_per_page = 20;
        today_current_page = 1;
        week_per_page = 20;
        week_current_page = 1;
        mHttpHelper.getPersonalHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getWeekSign(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getTodayChallenge(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), today_per_page, today_current_page);
        mHttpHelper.getWeekChallenge(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), week_per_page, week_current_page);
        mRule = "签到规则:\n" + "1、连续签到可获得红包惊喜；\n" + "2、连续7天为一个周期，最多连续签到7天；\n" + "3、如果签到中断，重新计算天数。\n" + "\n" + "挑战规则:\n" + "1、今日挑战每天0点开始，次日0点结束；\n" + "2、本周挑战以每周一至周日自然周循环，每周 一0点重新开始。";
        myDialog = new IOSAlertDialog(this).builder();
        myDialog.setMsgGravity(Gravity.LEFT);

//        llTodayChallenge.setVisibility(View.GONE);//今日挑战未完成
//        llWeekChallenge.setVisibility(View.GONE);//本周挑战未完成

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_challenge_center;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mFlBack.setOnClickListener(this);
        mTvRule.setOnClickListener(this);
        mTvExchangePrize.setOnClickListener(this);
        initSpringView();
    }

    private void initSpringView() {
        springView.setHeader(new DefaultHeader(this));
//        springView.setFooter(new DefaultFooter(this));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        today_current_page = 1;
                        week_current_page = 1;
                        mWeekSignData = null;
                        mTodayData = null;
                        mWeekData = null;
                        mPersionalModel = null;
                        if (mAdapterChallengToday != null) {
                            mAdapterChallengToday.removeData();
                        }
                        if (mAdapterChallengWeek != null) {
                            mAdapterChallengWeek.removeData();
                        }
                        mHttpHelper.getPersonalHome(ChallengeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                        mHttpHelper.getWeekSign(ChallengeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                        mHttpHelper.getTodayChallenge(ChallengeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), today_per_page, today_current_page);
                        mHttpHelper.getWeekChallenge(ChallengeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), week_per_page, week_current_page);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getTodayChallenge(ChallengeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), today_per_page, today_current_page);
                        mHttpHelper.getWeekChallenge(ChallengeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), today_per_page, today_current_page);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_rule:
//                myDialog.setGone().setMsg(mRule).setPositiveButton("我知道了",null).show();
                startActivity(new Intent(this, SignRuleActivity.class));
                break;
            case R.id.tv_exchange_prize:
                startActivity(new Intent(this, ExchallgeCenterActivity.class));
                break;
        }
    }


    @Override
    public void onItemClick(int position) {
//        LogUtil.e("__","" + position);
//        String date = mWeekSignData.getWeek().get(position).getDay();
//        String todayDate = CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance());
//        LogUtil.e("__date","" + date);
//        LogUtil.e("__todayDate","" + todayDate);
//        if (date.equals(todayDate)){
//            LogUtil.e("__onItemClick","签到");
//            mHttpHelper.userSign(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
//        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_PERSONAL_HOME:
                PersionalHomeModel persionalHomeModel = (PersionalHomeModel) msg.obj;
                mTvIntegrals.setText("我的积分：" + String.valueOf(persionalHomeModel.getData().getIntegral()));
                break;
            case Constants.REQUEST_GET_WEEK_SIGN:
                WeekSignModel weekSignModel = (WeekSignModel) msg.obj;
                mWeekSignData = weekSignModel.getData();
                setWeekSign();
                break;
            case Constants.REQUEST_TODAY_CHALLENGE:
                TodayChallengeModel todayChallengeModel = (TodayChallengeModel) msg.obj;
                setTodayData(todayChallengeModel.getData());
                break;
            case Constants.REQUEST_WEEK_CHALLENGE:
                WeekChallengeModel weekChallengeModel = (WeekChallengeModel) msg.obj;
                setWeekData(weekChallengeModel.getData());
                break;
            case Constants.REQUEST_USER_SIGN:
                UserSignModel userSignModel = (UserSignModel) msg.obj;
                ToastUtil.showToast("签到成功");
                mWeekSignData = null;
                mTodayData = null;
                mWeekData = null;
                mPersionalModel = null;
                if (mAdapterChallengToday != null) {
                    mAdapterChallengToday.removeData();
                }
                if (mAdapterChallengWeek != null) {
                    mAdapterChallengWeek.removeData();
                }
                initData();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {

    }

    private void setWeekSign() {
        mTvSeriesSignIn.setText(String.valueOf("已签到" + mWeekSignData.getSignContinueDay() + "天"));
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 7);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapterSignIn = new RvAdapterSignIn(this, mWeekSignData);
        mRvSignIn.setLayoutManager(manager);
        mRvSignIn.setAdapter(mAdapterSignIn);
        mRvSignIn.setNestedScrollingEnabled(false);//解决rv和sv嵌套冲突
        mAdapterSignIn.setItemClickListener(this);
    }

    /**
     * 设置今日挑战数据
     *
     * @param todayModel
     */
    private void setTodayData(TodayChallengeModel.DatabeanX todayModel) {
        if (todayModel.getAppWeekChallengesList() != null && todayModel.getAppWeekChallengesList().getData() != null && todayModel.getAppWeekChallengesList().getData().size() > 0) {
//            today_current_page++;
        }
        if (mTodayData == null) {
            mTodayData = todayModel;
            mAdapterChallengToday = new RvAdapterChallengToday(this, mTodayData);
            RecyclerView.LayoutManager managerToday = new LinearLayoutManager(this);
            mRvTodayChallenge.setNestedScrollingEnabled(false);
            mRvTodayChallenge.setLayoutManager(managerToday);
            mRvTodayChallenge.setAdapter(mAdapterChallengToday);
            mAdapterChallengToday.setButtonClickListener(new RvAdapterChallengToday.OnButtonClickListener() {
                @Override
                public void onButtonClick(int position) {

                    LogUtil.e("__today_btn", "" + position);
                }
            });
        } else {
            List<TodayChallengeModel.DatabeanX.AppWeekChallengesListbean.Databean> data = mTodayData.getAppWeekChallengesList().getData();
            List<TodayChallengeModel.DatabeanX.AppWeekChallengesListbean.Databean> newData = todayModel.getAppWeekChallengesList().getData();
            if (newData == null || newData.size() == 0 && mTodayData != null) {
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapterChallengToday.notifyDataSetChanged();
        }

//        mAdapterChallengToday = new RvAdapterChallengToday(this,mDataToday,"今日挑战");


    }

    /**
     * 设置本周挑战数据
     *
     * @param weekModel
     */
    private void setWeekData(WeekChallengeModel.DatabeanX weekModel) {
        mAdapterChallengWeek = new RvAdapterChallengWeek(this, mWeekData);
        if (weekModel.getAppWeekChallengesList() != null && weekModel.getAppWeekChallengesList().getData() != null && weekModel.getAppWeekChallengesList().getData().size() > 0) {
//            week_current_page++;
        }
        if (mWeekData == null) {
            mWeekData = weekModel;
            mAdapterChallengWeek = new RvAdapterChallengWeek(this, mWeekData);
            RecyclerView.LayoutManager managerWeek = new LinearLayoutManager(this);
            mRvWeekChallenge.setNestedScrollingEnabled(false);
            mRvWeekChallenge.setLayoutManager(managerWeek);
            mRvWeekChallenge.setAdapter(mAdapterChallengWeek);
            mAdapterChallengWeek.setButtonClickListener(new RvAdapterChallengWeek.OnButtonClickListener() {
                @Override
                public void onButtonClick(int position) {
                    LogUtil.e("__week_btn", "" + position);
                }
            });
        } else {
            List<WeekChallengeModel.DatabeanX.AppWeekChallengesListbean.Databean> data = mWeekData.getAppWeekChallengesList().getData();
            List<WeekChallengeModel.DatabeanX.AppWeekChallengesListbean.Databean> newData = weekModel.getAppWeekChallengesList().getData();
            if (newData == null || newData.size() == 0 && mWeekData != null) {
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapterChallengWeek.notifyDataSetChanged();
        }

    }

    //EventBus接收微信分享回调
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
//            LogUtil.e("__handleData_getType",mMessageEvent.getType() + "");
        if (mMessageEvent.getType() == Constants.REQUEST_SHARE) {
            LogUtil.e("__handleData_readAct", Constants.REQUEST_SHARE + "");
            //发送到后台记录分享，不需要回调处理
            mHttpHelper.share(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), UserManager.getInstance().getUserId(), CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()), 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
