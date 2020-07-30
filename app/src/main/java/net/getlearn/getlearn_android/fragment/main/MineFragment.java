package net.getlearn.getlearn_android.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.ChallengeCenterActivity;
import net.getlearn.getlearn_android.activity.CouponActivity;
import net.getlearn.getlearn_android.activity.CustomerServiceCenterActivity;
import net.getlearn.getlearn_android.activity.ExchangeCodeActivity;
import net.getlearn.getlearn_android.activity.MyCollectActivity;
import net.getlearn.getlearn_android.activity.MyOrderActivity;
import net.getlearn.getlearn_android.activity.SettingActivity;
import net.getlearn.getlearn_android.activity.UserInfoActivity;
import net.getlearn.getlearn_android.activity.VipCenterActivity;
import net.getlearn.getlearn_android.activity.webview.InvitationWebViewActivity;
import net.getlearn.getlearn_android.fragment.BaseFragment;
import net.getlearn.getlearn_android.model.bean.GetActiveModel;
import net.getlearn.getlearn_android.model.bean.PersionalHomeModel;
import net.getlearn.getlearn_android.model.bean.UserSignModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Getlearn on 2019/7/14.
 * 首页-我的
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_nick_name)
    TextView mTvNickName;
    @BindView(R.id.tv_signature)
    TextView mTvSignature;
    @BindView(R.id.ll_personal_info)
    LinearLayout mLlPersonalInfo;
    @BindView(R.id.btn_sign)
    Button mBtnSign;
    @BindView(R.id.tv_study_day)
    TextView mTvStudyDay;
    @BindView(R.id.tv_challenge_day)
    TextView mTvChallengeDay;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.ll_challenge)
    LinearLayout mChallenge;
    @BindView(R.id.ll_vip_center)
    LinearLayout mLlVipCenter;
    @BindView(R.id.ll_my_order)
    LinearLayout mLlMyOrder;
    @BindView(R.id.ll_my_collect)
    LinearLayout mLlMyCollect;
    @BindView(R.id.ll_coupon)
    LinearLayout mLlCoupon;
    @BindView(R.id.call_center)
    LinearLayout mCallCenter;
    @BindView(R.id.ll_setting)
    LinearLayout mLlSetting;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_study_day)
    LinearLayout mLlStudyDay;
    @BindView(R.id.ll_challenge_day)
    LinearLayout mLlChallengeDay;
    @BindView(R.id.ll_integral)
    LinearLayout mLlIntegral;
    @BindView(R.id.ll_invitation)
    LinearLayout mLInvitation;

    @BindView(R.id.tv_invitation)
    TextView mBtnBuyVip;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    @BindView(R.id.ll_exchange_code)
    LinearLayout llExchangeCode;
    Unbinder unbinder;

    private PersionalHomeModel.Databean mPersionalData;

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        mHttpHelper.getPersonalHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getActive(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        //判断vip时间是否过期
        Boolean judge = System.currentTimeMillis() > UserManager.getInstance().getVipTime();

        if (UserManager.getInstance().getIsVip() && judge) {
            mBtnBuyVip.setVisibility(View.GONE);
        } else {
            mBtnBuyVip.setVisibility(View.VISIBLE);
        }
        LogUtil.e("__onResume", "MineFragment");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.e("__SelectSubjcetFragment", "onHiddenChanged:" + hidden + "");
        if (hidden) {//不在最前端界面显示，相当于调用了onPause()

        } else {//重新显示到最前端 ,相当于调用了onResume()

        }
    }

    @Override
    protected void initView(View view) {
        mIvIcon.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
        mLlPersonalInfo.setOnClickListener(this);
        mLlMyCollect.setOnClickListener(this);
        mLlVipCenter.setOnClickListener(this);
        mLlMyOrder.setOnClickListener(this);
        mLlSetting.setOnClickListener(this);
        mBtnSign.setOnClickListener(this);
        mBtnBuyVip.setOnClickListener(this);
        mLlCoupon.setOnClickListener(this);
        mChallenge.setOnClickListener(this);
        mLInvitation.setOnClickListener(this);
        llExchangeCode.setOnClickListener(this);
        initSpringView();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_invitation:
            case R.id.ll_vip_center:
                intent = new Intent(getActivity(), VipCenterActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_my_order:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_icon:
            case R.id.ll_personal_info:
                intent = new Intent(getActivity(), UserInfoActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.btn_sign:
                if (mPersionalData == null || mPersionalData.isSign()) {
                    intent = new Intent(getActivity(), ChallengeCenterActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    mHttpHelper.userSign(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                }
                break;
            case R.id.ll_challenge:
                intent = new Intent(getActivity(), ChallengeCenterActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_coupon:
                intent = new Intent(getActivity(), CouponActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_my_collect:
                intent = new Intent(getActivity(), MyCollectActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_customer_service:
                intent = new Intent(getActivity(), CustomerServiceCenterActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_invitation:
                intent = new Intent(getActivity(), InvitationWebViewActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_exchange_code:

                intent =  new Intent(getActivity(),ExchangeCodeActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;

        }
    }


    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_PERSONAL_HOME:
                PersionalHomeModel persionalHomeModel = (PersionalHomeModel) msg.obj;
                mPersionalData = persionalHomeModel.getData();
                setData();
                break;
            case Constants.REQUEST_USER_SIGN:
                UserSignModel userSignModel = (UserSignModel) msg.obj;
                mBtnSign.setText("已签到");
                mBtnSign.setBackground(getResources().getDrawable(R.drawable.bg_gray3_20));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getActivity(), ChallengeCenterActivity.class);
                        getActivity().startActivity(intent);
                    }
                }, 500);
                break;
            case Constants.REQUEST_GET_ACTIVE:
                GetActiveModel getActiveModel = (GetActiveModel) msg.obj;
                if (getActiveModel.getData() != null && getActiveModel.getData().getActive_id() != 0) {
                    mLInvitation.setVisibility(View.VISIBLE);
                } else {
                    mLInvitation.setVisibility(View.GONE);
                }
                break;

        }
    }

    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(getActivity()));
//        mSpringView.setFooter(new DefaultFooter(getActivity()));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getPersonalHome(MineFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getPersonalHome(MineFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    private void setData() {
        //Glide加载圆形头像
        RequestOptions options = new RequestOptions().circleCrop().placeholder(R.drawable.img_user).diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(getActivity()).load(mPersionalData.getHeadimgurl()).apply(options).into(mIvIcon);
        if (mPersionalData.getWechatnickname() == null || mPersionalData.getWechatnickname().equals("")) {
            mTvNickName.setText("未设置昵称");
        } else {
            mTvNickName.setText(mPersionalData.getWechatnickname());
        }

        mTvStudyDay.setText(String.valueOf(mPersionalData.getStudy()));
        mTvChallengeDay.setText(String.valueOf(mPersionalData.getChallenges()));
        mTvIntegral.setText(String.valueOf(mPersionalData.getIntegral()));
        if (mPersionalData.getSignature() != null) {
            mTvSignature.setText(String.valueOf(mPersionalData.getSignature()));
        }
        if (mPersionalData.isSign()) {
            mBtnSign.setText("已签到");
            mBtnSign.setBackground(getResources().getDrawable(R.drawable.bg_gray3_20));
        } else {
            mBtnSign.setText("签到");
            mBtnSign.setBackground(getResources().getDrawable(R.drawable.bg_yellow_selector4));
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_GET_PERSONAL_HOME:
//                ToastUtil.showToast("获取个人信息失败");
                break;
            case Constants.REQUEST_USER_SIGN:
//                ToastUtil.showToast("签到失败");
                break;
            case Constants.REQUEST_GET_ACTIVE:
                mLInvitation.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
