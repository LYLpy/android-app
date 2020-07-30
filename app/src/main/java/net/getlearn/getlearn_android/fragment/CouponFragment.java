package net.getlearn.getlearn_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.VipCenterActivity;
import net.getlearn.getlearn_android.adapter.RvAdapterCoupon;
import net.getlearn.getlearn_android.model.bean.MyCouponModel;
import net.getlearn.getlearn_android.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 * 优惠券
 */

public class CouponFragment extends BaseFragment {

    /**
     * @param couponStatus 优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
     * @return
     */
    public static CouponFragment newInstance(int couponStatus) {
        CouponFragment fm = new CouponFragment();
        Bundle b = new Bundle();
        b.putInt("couponStatus", couponStatus);
        fm.setArguments(b);
        return fm;
    }
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    @BindView(R.id.ll_not_data)
    LinearLayout mLlNotData;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;
    @BindView(R.id.rv_data)
    RecyclerView mRv;
    private MyCouponModel.DataBeanX mData;
    private int couponStatus;
    private int page;
    private int per_page = 20;
    private boolean getAllCoupon = true;//是否获取全部优惠券
    private RvAdapterCoupon mAdapter;
    @Override
    protected void initData() {
        page = 1;
        mData = null;
        Bundle args = getArguments();
        couponStatus = args.getInt("couponStatus");
        mHttpHelper.getMyCoupon(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                couponStatus,page,per_page);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initView(View view) {
        initSpringView();
    }

    @Override
    public void onClick(View view) {

    }

    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(getActivity()));
        mSpringView.setFooter(new DefaultFooter(getActivity()));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mData = null;
                        if (mAdapter != null){
                            mAdapter.removeData();
                        }
                        mHttpHelper.getMyCoupon(CouponFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                couponStatus,page,per_page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getMyCoupon(CouponFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                couponStatus,page,per_page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_MY_COUPON:
                MyCouponModel couponModel = (MyCouponModel) msg.obj;
                setData(couponModel);
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_MY_COUPON:
                break;
        }
    }

    private void setData(MyCouponModel couponModel) {
        page++;
        //证明是第一次加载
        if (mData == null){
            mData = couponModel.getData();
            mAdapter = new RvAdapterCoupon(getActivity(),mData);
            mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRv.setNestedScrollingEnabled(false);
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemButtonClickListener(new RvAdapterCoupon.OnItemButtonClickListener() {
                @Override
                public void onItemButtonClick(int position) {
                    Intent intent = new Intent(getActivity(), VipCenterActivity.class);
//                    intent.putExtra("coupon_id",mData.getAppGiftList().getData().get(position).get);
                    getActivity().startActivity(intent);
                }
            });
            //之前已经有数据了，将数据加进去，刷新即可
        }else {
            List<MyCouponModel.DataBeanX.AppGiftListBean.DataBean> data = mData.getAppGiftList().getData();
            List<MyCouponModel.DataBeanX.AppGiftListBean.DataBean> newData = couponModel.getData().getAppGiftList().getData();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }
        if (mData != null && mData.getAppGiftList() != null && mData.getAppGiftList().getData().size() > 0){
//            mLlNoData.setVisibility(View.GONE);
            mLlNotData.setVisibility(View.GONE);
            //设置layout_gravity为Top
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.TOP;
            mRlContent.setLayoutParams(lp);
        }
    }
}
