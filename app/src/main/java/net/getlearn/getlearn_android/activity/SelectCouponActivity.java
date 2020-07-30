package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import net.getlearn.getlearn_android.adapter.RvAdapterSelectCoupon;
import net.getlearn.getlearn_android.model.bean.MyCouponModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/14------更新------
 * 选择优惠券界面
 */

public class SelectCouponActivity extends BaseActivity {

    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.iv_no_data)
    ImageView mIvNoData;
    @BindView(R.id.rv_data)
    RecyclerView mRv;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    private float payPrice;
    private List <MyCouponModel.DataBeanX.AppGiftListBean.DataBean> ddd = new ArrayList<>();

    private int couponStatus = 1;//优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
    private int page;
    private int per_page = 20;
    private int coupon_id;//优惠券id
    private int u_gift_id;//礼品id
    private float coupon_amount;//优惠券金额
    private RvAdapterSelectCoupon mAdapter;
    private  MyCouponModel couponModel = new MyCouponModel();
    private MyCouponModel.DataBeanX mData;
    MyCouponModel.DataBeanX.AppGiftListBean.DataBean dataBean = new MyCouponModel.DataBeanX.AppGiftListBean.DataBean();
    @Override
    protected void initData() {


        Intent intent = getIntent();
        payPrice=intent.getFloatExtra("putpayPrice",0);//获取到选中套餐的价格
        LogUtil.e("__json33payPrice",payPrice+"");
        page = 1;
        mHttpHelper.getMyCoupon1(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                couponStatus, page, per_page,payPrice);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_select_coupon;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
        initSpringView();
    }
    private void initSpringView() {
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
                        mHttpHelper.getMyCoupon(SelectCouponActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
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
                        mHttpHelper.getMyCoupon(SelectCouponActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                couponStatus,page,per_page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_confirm:
                Intent intent = new Intent();
                if (coupon_id != 0){
                    intent.putExtra("coupon_id",coupon_id);
                    intent.putExtra("u_gift_id",u_gift_id);
                    intent.putExtra("coupon_amount",coupon_amount);
                }
                LogUtil.e("__confirm_coupon_id",coupon_id + "");
                LogUtil.e("u_gift_id",u_gift_id + "");
                LogUtil.e("__confirm_coupon_amount",coupon_amount + "");
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_MY_COUPON1:
                 couponModel = (MyCouponModel) msg.obj;
                setData(couponModel);
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_MY_COUPON:
                ToastUtil.showToast("获取优惠券失败，请重试");
                break;
        }
    }
    private void setData(MyCouponModel couponModel) {
//        byte [] bytes = null;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream((OutputStream) couponModel.getData().getAppGiftList().getData());
//            oos.writeObject(couponModel);
//            oos.flush();
//            bytes  = CompressUtil
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (couponModel.getData() != null &&
                couponModel.getData().getAppGiftList() != null &&
                couponModel.getData().getAppGiftList().getData()!=null &&
                couponModel.getData().getAppGiftList().getData().size() > 0){
            page++;
        }

        //证明是第一次加载
        if (mData == null){
            mData = couponModel.getData();
            mAdapter = new RvAdapterSelectCoupon(this,mData,coupon_id,payPrice);
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemButtonClickListener(new RvAdapterSelectCoupon.OnItemButtonClickListener() {
                @Override
                public void onItemButtonClick(int position) {
                    coupon_id = mData.getAppGiftList().getData().get(position).getId();
                    u_gift_id = mData.getAppGiftList().getData().get(position).getU_gift_id();
                    coupon_amount = mData.getAppGiftList().getData().get(position).getCouponCurrency();
//                    DecimalFormat df = new DecimalFormat("0.00");
//                    coupon_amount = Float.valueOf(df.format(coupon_amount));
                    if (payPrice>coupon_amount) {
                        Intent intent = new Intent();
                        if (coupon_id != 0) {
                            intent.putExtra("coupon_id", coupon_id);
                            intent.putExtra("u_gift_id", u_gift_id);
                            intent.putExtra("coupon_amount", coupon_amount);
                        }
                        setResult(RESULT_OK, intent);
                        finish();
                    }else {
                        ToastUtil.showToast("该优惠卷未满足使用条件");
                    }
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
        if (mData != null && mData.getAppGiftList() != null && mData.getAppGiftList().getData().size() > 0 && mIvNoData != null){
            mIvNoData.setVisibility(View.GONE);
        }
        if (mData != null && mData.getAppGiftList() != null &&
                mData.getAppGiftList().getData()!=null){
            for (int i = 0; i < mData.getAppGiftList().getData().size(); i++) {
//                if (mData.getAppGiftList().getData().get(i).get){
//                }
            }
        }

    }
}
