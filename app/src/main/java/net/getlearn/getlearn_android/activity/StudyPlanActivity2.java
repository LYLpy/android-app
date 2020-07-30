package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.GvAdapterCalendar;
import net.getlearn.getlearn_android.adapter.RvAdapterStudyPlanNew;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.GridViewForScrollView;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/9------更新------
 * 新的学习计划,UI改版之后的新界面
 */

public class StudyPlanActivity2 extends BaseActivity implements RvAdapterStudyPlanNew.OnItemBtnClickListener {


    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_new_plan)
    ImageView mIvNewPlan;
    @BindView(R.id.toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.gv)
    GridViewForScrollView mGv;
    @BindView(R.id.tv_completion)
    TextView mTvCompletion;
    @BindView(R.id.iv_no_data)
    ImageView mIvNoData;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    private List<String> mDataStr;
    private GvAdapterCalendar mGvAdapter;
    private RvAdapterStudyPlanNew mAdapter;
    private List<Calendar> mDataDate;
    private StudyPlanModel.DatabeanX mData;
    private int page;
    private int per_page;
    private Calendar mCalendar;
    private StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean currentDatabean;
    private IOSAlertDialog mDialog;
    private String plan_date;
    @Override
    protected void initData() {
        mFlBack.setOnClickListener(this);
        Intent intent = getIntent();
        plan_date = intent.getStringExtra("plan_date");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mData = null;
        mDialog = new IOSAlertDialog(this).builder();
        page = 1;
        per_page = 20;
        mDataStr = new ArrayList<>();
        mDataDate = new ArrayList<>();
//        addWeek(mDataStr);
        mCalendar = Calendar.getInstance();

        for (int i = 0; i < 7; i++) {
            Calendar clendar = Calendar.getInstance();
            // 获取本周的第一天
            int firstDayOfWeek = mCalendar.getFirstDayOfWeek();
            clendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek + i);
            String day = new SimpleDateFormat("d").format(clendar.getTime());
//            mDataStr.add(day);
            mDataDate.add(clendar);
        }
        mGvAdapter = new GvAdapterCalendar(this, mDataDate);
        mGv.setAdapter(mGvAdapter);
        if (plan_date == null || plan_date.equals("")){
            plan_date =  CalendarUtils.getTimeYearMonthDayStr(mCalendar);
        }
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 7){
                    mIvNoData.setVisibility(View.VISIBLE);
                    int position = i - 7;
                    plan_date =  CalendarUtils.getTimeYearMonthDayStr(mDataDate.get(position));
                            page = 1;
            mData = null;
            if (mAdapter!=null){
                mAdapter.removeData();
            }
            mHttpHelper.getStudyPlan(StudyPlanActivity2.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                page, per_page,plan_date);
            }
            }
        });
        mHttpHelper.getStudyPlan(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                page, per_page, plan_date);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_study_plan_new;
    }

    @Override
    protected void initView() {
        mTvCompletion.setOnClickListener(this);
        mIvNewPlan.setOnClickListener(this);
        initSpringView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_completion:
            case R.id.iv_new_plan:
                startActivity(new Intent(this, CreateStudyPlanActivity.class));
                break;

        }

    }
    private void initSpringView(){
        mSpringView.setHeader(new DefaultHeader(this));
        mSpringView.setFooter(new DefaultFooter(this));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mData = null;
                        if (mAdapter!=null){
                            mAdapter.removeData();
                        }
                        mHttpHelper.getStudyPlan(StudyPlanActivity2.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                page, per_page,plan_date);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getStudyPlan(StudyPlanActivity2.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                page, per_page,plan_date);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_STUDY_PLAN:
                StudyPlanModel studyPlanModel = (StudyPlanModel) msg.obj;
                StudyPlanModel.DatabeanX databean = studyPlanModel.getData();
                LogUtil.e("__onHttpSuccess", "请求成功");
                setData(databean);
                setStudyPlanData();
                break;
            case Constants.REQUEST_CREATE_STUDY_PLAN:
                //修改状态成功，重新获取数据
                page = 1;
                mData = null;
                mHttpHelper.getStudyPlan(StudyPlanActivity2.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                        page, per_page,plan_date);
//                CreateStudyPlanModel createStudyPlanModel = (CreateStudyPlanModel) msg.obj;
//                for (int i = 0; i <  mData.getAppStudyPlanList().getData().size(); i++) {
//                    StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean data = mData.getAppStudyPlanList().getData().get(i);
//                    if (data.getId() == createStudyPlanModel.getData().getId()){
//                        if (data.getIsFinish() == 0){
//                            data.setIsFinish(1);
//                        }else {
//                            data.setIsFinish(0);
//                        }
//                        mAdapter.notifyDataSetChanged();
//                    }
//                }
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_GET_STUDY_PLAN:
//                LogUtil.e("__onHttpError", "失败");
                ToastUtil.showToast("获取学习计划失败，请重试");
                break;
            case Constants.REQUEST_CREATE_STUDY_PLAN:
                ToastUtil.showToast("修改状态失败，请重试");
                break;
        }
    }

    private void setData(StudyPlanModel.DatabeanX databean) {
        if (databean != null && databean.getAppStudyPlanList()!= null && databean.getAppStudyPlanList().getData().size() > 0){
            page ++;
        }
        if (mData == null){
            mData = databean;
            mAdapter = new RvAdapterStudyPlanNew(this, mData);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mRv.setLayoutManager(manager);
            mRv.setNestedScrollingEnabled(false);
            mRv.setAdapter(mAdapter);
            mAdapter.setClickListener(this);
        }else {
            List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> data = mData.getAppStudyPlanList().getData();
            List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> newData = databean.getAppStudyPlanList().getData();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }
        if (mData != null && mData.getAppStudyPlanList()!= null && mData.getAppStudyPlanList().getData() != null &&
                mData.getAppStudyPlanList().getData().size() > 0){
            mIvNoData.setVisibility(View.GONE);
        }
    }

    private void addWeek(List<String> datas) {
        datas.add("日");
        datas.add("一");
        datas.add("二");
        datas.add("三");
        datas.add("四");
        datas.add("五");
        datas.add("六");
    }

    private void setStudyPlanData() {
        if (mData != null && mGvAdapter != null){
            mGvAdapter.setPlanData(mData.getDate());
//            mTvCompletion.setText( "今日任务：" + String.valueOf(mData.getSchedule()));
        }
    }

    @Override
    public void onItemBtnClick(int position) {
        currentDatabean = mData.getAppStudyPlanList().getData().get(position);
//        是否已完成 1表示未完成 0表示已完成
        if (currentDatabean.getIsFinish() == 1){

            mDialog.setGone().setMsg("\n标记为已完成\n").setNegativeButton("取消",R.color.black_333,null).
                    setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mHttpHelper.createStudyPlan(StudyPlanActivity2.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                                    currentDatabean.getTaskContent(),currentDatabean.getTipsMode(),currentDatabean.getCloseDateTime(),
                                    currentDatabean.getTipsDateTime(),0,currentDatabean.getId(),true);
                        }
                    }).setMsgStyle(Typeface.defaultFromStyle(Typeface.BOLD))
                    .setMsgSize(16)
                    .show();

        }else {
            mDialog.setGone().setMsg("标记为未完成").setNegativeButton("取消",R.color.bg_gray5,null).
                    setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mHttpHelper.createStudyPlan(StudyPlanActivity2.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                                    currentDatabean.getTaskContent(),currentDatabean.getTipsMode(),currentDatabean.getCloseDateTime(),
                                    currentDatabean.getTipsDateTime(),1,currentDatabean.getId(),true);
                        }
                    })
                    .show();
        }
    }

}
