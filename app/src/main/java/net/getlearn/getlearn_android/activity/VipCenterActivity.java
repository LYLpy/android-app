package net.getlearn.getlearn_android.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import net.getlearn.getlearn_android.AuthResult;
import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.PayResult;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterPrice;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.CreateOrderAndPayModel;
import net.getlearn.getlearn_android.model.bean.GetCouponModel;
import net.getlearn.getlearn_android.model.bean.PriceModel;
import net.getlearn.getlearn_android.model.bean.UserInfoModel;
import net.getlearn.getlearn_android.model.bean.WXLoginInfoModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.DateUtil;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ScaleUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.RoundCheckBox;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/12------更新------
 * 会员中心
 */

public class VipCenterActivity extends BaseActivity implements RvAdapterPrice.OnItemClickListener, BaseHelper.IHttpCallback {
    public final int SELECT_COUPON = 1;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @BindView(R.id.tv_is_vip)
    TextView mTvIsVip;
    @BindView(R.id.rv_price)
    RecyclerView mRvPrice;
    @BindView(R.id.iv_arrow_coupon)
    ImageView mIvArrow;
    @BindView(R.id.iv_vip_privilege)
    ImageView mIvVipPrivilege;

    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;
    @BindView(R.id.cb_wechat)
    RoundCheckBox mCbWechat;
    @BindView(R.id.cb_alipay)
    RoundCheckBox mCbAlipay;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.tv_original_price)
    TextView mTvOriginalPrice;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    private RvAdapterPrice mAdapter;
    private PriceModel.DataBeanX mData;
    private int mPosition;
    private int discount;//0不是限时优惠1为限时优惠
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    private int pay_type;//支付方式
    private int u_gift_id;//礼品ID
    private int coupon_id;//优惠券ID
    private float coupon_amount;//优惠券金额
    float putpayPrice ;//选中套餐的价格

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private CreateOrderAndPayModel.Databean mDatabean;
    private float payPrice;
    private float screenWidth;
    private float mIvVipPrivilegeHeiht;//图片拉伸之后的高度
    @Override
    protected void initData() {
        //动态计算图片与实际宽度的倍数，然后给ImageView高度赋值
        screenWidth = ScaleUtils.getScreenWidth(MainApp.getContext());
        ViewGroup.LayoutParams layoutParams = mIvVipPrivilege.getLayoutParams();
        mIvVipPrivilegeHeiht = getImgHeight()[0] * getImgHeight()[1];
        layoutParams.height = (int) mIvVipPrivilegeHeiht;
        layoutParams.width = (int) screenWidth;
        mIvVipPrivilege.setLayoutParams(layoutParams);
        //添加删除线
        mTvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //如果app_id为空，则需要从后台获取
        if (UserManager.getInstance().getApp_id()==null && UserManager.getInstance().getApp_id().equals("")){
            mHttpHelper.getWxLoginInfo(this,CommonUtils.getCurrentTimeStampInt());
        }else {
            regToWx();
        }
        mHttpHelper.getPrice(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getUserInfo(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_vip_center;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mCbWechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mCbAlipay.setChecked(false);
                    mCbWechat.setClickable(false);
                    pay_type = 0;
                }else {
                    mCbWechat.setClickable(true);
                }
            }
        });
        mCbAlipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mCbWechat.setChecked(false);
                    mCbAlipay.setClickable(false);
                    pay_type = 1;
                }else {
                    mCbAlipay.setClickable(true);
                }
            }
        });
        mCbWechat.setChecked(true);
        mTvPay.setOnClickListener(this);
        mIvArrow.setOnClickListener(this);
        mTvCoupon.setOnClickListener(this);
        initSpringView();
    }
    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(this));
//        mSpringView.setFooter(new DefaultFooter(this));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //如果app_id为空，则需要从后台获取
                        if (UserManager.getInstance().getApp_id()==null && UserManager.getInstance().getApp_id().equals("")){
                            mHttpHelper.getWxLoginInfo(VipCenterActivity.this,CommonUtils.getCurrentTimeStampInt());
                        }else {
                            regToWx();
                        }
                        mHttpHelper.getPrice(VipCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                        mHttpHelper.getUserInfo(VipCenterActivity.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_arrow_coupon:
            case R.id.tv_coupon:
                Intent intent = new Intent(this,SelectCouponActivity.class);
                intent.putExtra("putpayPrice",putpayPrice);

                startActivityForResult(intent,SELECT_COUPON);
                break;
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_pay:
                if (mData.getData().get(mPosition).getId() == 0){
                    ToastUtil.showToast("请先选择套餐");
                    return;
                }
//                if (pay_type == 0){
//                    ToastUtil.showToast("暂未开通微信支付");
//                    return;
//                }
                LogUtil.e("__pay_type",pay_type + "");
                LogUtil.e("__discount",discount + "");
               // discount=mData.getData().get(mPosition).getDiscount();
                mHttpHelper.createOrderAndPay(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                        mData.getData().get(mPosition).getId(),
                        String.valueOf(payPrice),
                        coupon_id,coupon_amount,pay_type,discount,u_gift_id);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_COUPON){
            u_gift_id = data.getIntExtra("u_gift_id",0);
            coupon_id = data.getIntExtra("coupon_id",0);
            coupon_amount = data.getFloatExtra("coupon_amount",0);
        }
        if (u_gift_id == 0 || coupon_amount==0){
            mTvCoupon.setText("请选择");
//            mTvCoupon.setTextSize(15);
            mTvCoupon.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mTvCoupon.setTextColor(getResources().getColor(R.color.bg_gray6));
        }else {
//            mTvCoupon.setTextSize(20);
            mTvCoupon.setText("¥" + coupon_amount);
            mTvCoupon.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mTvCoupon.setTextColor(getResources().getColor(R.color.red_deep));
        }
        calculatePrice();
    }

    @Override
    public void onItemClick(int position ,String price) {
        mPosition = position;
        discount = mData.getData().get(mPosition).getDiscount();
            LogUtil.e("__onItemClick,discount","" + price);
            LogUtil.e("__discount",discount + "");
            u_gift_id = 0;
            coupon_id = 0;
            coupon_amount = 0;
            putpayPrice = 0;
        if (u_gift_id == 0 || coupon_amount==0){
            mTvCoupon.setText("请选择");
            mTvCoupon.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mTvCoupon.setTextColor(getResources().getColor(R.color.bg_gray6));
        }else {
            mTvCoupon.setText("¥" + coupon_amount);
            mTvCoupon.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mTvCoupon.setTextColor(getResources().getColor(R.color.red_deep));
        }
        int discount = mData.getData().get(mPosition).getDiscount();
        if (discount==0) {
            payPrice = Float.parseFloat(mData.getData().get(mPosition).getPrice());
        }else if (discount ==1){
            payPrice = Float.parseFloat(mData.getData().get(mPosition).getDiscount_price());
        }
        putpayPrice = payPrice;
        mHttpHelper.getCoupon(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(), payPrice);
        LogUtil.e("__onItemClick","" + price);
        calculatePrice();
    }

    /**
     * 计算价格
     */
    private void calculatePrice() {
        //判断选中的是否为限时套餐,然后把价格重新赋值，避免减了两次优惠券价格
        int discount = mData.getData().get(mPosition).getDiscount();
        if (discount==0) {
            payPrice = Float.parseFloat(mData.getData().get(mPosition).getPrice());
        }else if (discount ==1){
            payPrice = Float.parseFloat(mData.getData().get(mPosition).getDiscount_price());
        }
        putpayPrice = payPrice;
            DecimalFormat df = new DecimalFormat("0.00");
            coupon_amount = Float.valueOf(df.format(coupon_amount));
        LogUtil.e("__calculatePrice_payPrice_1", String.valueOf(payPrice));
        LogUtil.e("__calculatePrice_coupon_amount", String.valueOf(coupon_amount));
            if (payPrice <= coupon_amount) {
                payPrice = 0.0f;
            } else {
                LogUtil.e("__calculatePrice_payPrice", String.valueOf(payPrice));
                LogUtil.e("__calculatePrice_coupon_amount", String.valueOf(coupon_amount));
                payPrice = payPrice - coupon_amount;
            }
            LogUtil.e("__calculatePrice_payPrice_2", String.valueOf(payPrice));
            payPrice = Float.valueOf(df.format(payPrice));
            LogUtil.e("__calculatePrice_payPrice_3", String.valueOf(payPrice));
            mTvTotalPrice.setText("¥" + payPrice);
            mTvOriginalPrice.setText("¥" + mData.getData().get(mPosition).getVm_price());

        }
    public void setData(PriceModel.DataBeanX data) {
        mData = data;
        mAdapter = new RvAdapterPrice(this,mData);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this,3);
        mRvPrice.setLayoutManager(manager);
        mRvPrice.setNestedScrollingEnabled(false);
        mRvPrice.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
        //判断选中的是否为限时套餐,然后把价格重新赋值，避免减了两次优惠券价格
        int discount = mData.getData().get(mPosition).getDiscount();
        if (discount==0) {
            payPrice = Float.parseFloat(mData.getData().get(mPosition).getPrice());
        }else if (discount ==1){
            payPrice = Float.parseFloat(mData.getData().get(mPosition).getDiscount_price());
        }
        putpayPrice = payPrice;
        calculatePrice();
        mHttpHelper.getCoupon(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(), payPrice);
        LogUtil.e("__json73u_gift_id",u_gift_id+"");
        LogUtil.e("__json73ucoupon_id",coupon_id+"");
        LogUtil.e("__json73coupon_amount",coupon_amount+"");
    }

    /**
     * 网络请求成功回调
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的javabean数据
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_PRICE:
                PriceModel priceModel = (PriceModel) msg.obj;
                setData(priceModel.getData());
                break;
            case Constants.REQUEST_CREATE_ORDER_AND_PAY:
                CreateOrderAndPayModel createOrderAndPayModel = (CreateOrderAndPayModel) msg.obj;
                mDatabean = createOrderAndPayModel.getData();
                if (mDatabean.getPay_type() == 0){
                    wechatPay(mDatabean);
                }else {
                    alipay(mDatabean);
                }
                break;
            case Constants.REQUEST_GET_WXLOGIN_INFO:
                WXLoginInfoModel wxLoginInfoModel = (WXLoginInfoModel) msg.obj;
                WXLoginInfoModel.Databean wxLoginInfoModelData = wxLoginInfoModel.getData();
                UserManager.getInstance().setScope(wxLoginInfoModelData.getScope());
                UserManager.getInstance().setState(wxLoginInfoModelData.getState());
                UserManager.getInstance().setApp_id(wxLoginInfoModelData.getAppid());
                regToWx();
                break;
            case Constants.REQUEST_GET_USER_INFO:
                UserInfoModel loginModel = (UserInfoModel) msg.obj;
                UserInfoModel.Databean databean1 = loginModel.getData();
                setUserInfo(databean1);
                break;
            case Constants.REQUEST_GET_COUPON:
                GetCouponModel getCouponModel = (GetCouponModel) msg.obj;
                int couponCondition = getCouponModel.getData().getCouponCondition();
                LogUtil.e("__json73couponCondition",couponCondition+"");
                if (  payPrice >=couponCondition ) {
                    u_gift_id = getCouponModel.getData().getU_gift_id();
                    coupon_id = getCouponModel.getData().getId();
                    coupon_amount = getCouponModel.getData().getCouponCurrency();
                    if (u_gift_id == 0 || coupon_amount == 0) {
                        mTvCoupon.setText("请选择");
                        mTvCoupon.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        mTvCoupon.setTextColor(getResources().getColor(R.color.bg_gray6));
                    } else {
                        LogUtil.e("__json73u_gift_id", u_gift_id + "");
                        LogUtil.e("__json73ucoupon_id", coupon_id + "");
                        LogUtil.e("__json73coupon_amount", coupon_amount + "");
                        mTvCoupon.setText("¥" + coupon_amount);
                        mTvCoupon.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        mTvCoupon.setTextColor(getResources().getColor(R.color.red_deep));
                    }
                    calculatePrice();
                }
                break;
        }
    }

    /**
     * 设置用户信息
     * @param databean
     */
    private void setUserInfo(UserInfoModel.Databean databean){
        UserManager.getInstance().setToken(databean.getToken());
        UserManager.getInstance().setHeadImgUrl(databean.getHeadimgurl());
        UserManager.getInstance().setIntegral(databean.getIntegral());
        UserManager.getInstance().setSex(databean.getSex());
        if (databean.getCountry() != null){
            UserManager.getInstance().setCountry(databean.getCountry());
        }
        if (databean.getProvince() != null){
            UserManager.getInstance().setProvince(databean.getProvince());
        }
        if (databean.getCity() != null){
            UserManager.getInstance().setCity(databean.getCity());
        }
        UserManager.getInstance().setVipTime(databean.getVip_time());
        UserManager.getInstance().setUserPhone(databean.getPhone());
        UserManager.getInstance().setUserIcon(databean.getHeadimgurl());
        UserManager.getInstance().setNickName(databean.getWechatnickname());
        //0表示不是VIP,1表示是VIP
        Boolean judge = System.currentTimeMillis() < UserManager.getInstance().getVipTime();
        if (databean.getIsVip() == 0){
            UserManager.getInstance().setIsVip(false);
            mTvIsVip.setText("您还不是VIP");
        }else {
            UserManager.getInstance().setIsVip(true);
            LogUtil.e("__getVip_time", databean.getVip_time() + "");
            if (judge&&databean.getIsVip() == 1){
                mTvIsVip.setText("VIP会员在" + DateUtil.timeStamp2Date(databean.getVip_time(),"yyyy-MM-dd HH:mm") + "到期,请重新购买");
            }else {
                mTvIsVip.setText("VIP会员" + DateUtil.timeStamp2Date(databean.getVip_time(), "yyyy-MM-dd HH:mm") + "到期,购买后有效期顺延");
            }
        }

        mTvUserName.setText(databean.getWechatnickname());
        String userPhoneSrc = UserManager.getInstance().getUserPhone();
        //加密后的手机号码
        String userPhoneSectet = userPhoneSrc.replace(userPhoneSrc.substring(3,7),"****");
        mTvUserPhone.setText("(" + userPhoneSectet +  ")");
        RequestOptions options = new RequestOptions().circleCrop()
                .placeholder(R.drawable.img_user)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(this).load(databean.getHeadimgurl()).apply(options).into(mIvIcon);
    }
    /**
     * 网络请求失败回调
     * @param reqType 区分调用的是哪一个接口
     * @param error   请求失败的原因
     */
    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_PRICE:
                ToastUtil.showToast("获取套餐失败");
                break;
            case Constants.REQUEST_CREATE_ORDER_AND_PAY:
                ToastUtil.showToast("支付失败：" + error);
                break;
            case Constants.REQUEST_GET_WXLOGIN_INFO:
                break;
            case Constants.REQUEST_GET_USER_INFO:
                ToastUtil.showToast("获取用户信息失败");
                break;
        }
    }

    /**
     * 调起微信支付
     * @param createOrderModel
     */
    private void wechatPay(CreateOrderAndPayModel.Databean createOrderModel){

        PayReq request = new PayReq();
//        request.appId = "wxd930ea5d5a258f4f";
//        request.partnerId = "1900000109";
//        request.prepayId= "1101000000140415649af9fc314aa427";
//        request.packageValue = "Sign=WXPay";
//        request.nonceStr= "1101000000140429eb40476f8896f4c9";
//        request.timeStamp= "1398746574";
//        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";

        CreateOrderAndPayModel.Databean.PayInfobean payInfobean = createOrderModel.getPay_info();
        LogUtil.e("__wechatPay_appId",payInfobean.getAppid());
        LogUtil.e("__wechatPay_cTime",createOrderModel.getCreate_time());
        LogUtil.e("__wechatPay_partnerId",payInfobean.getPartnerid());
        LogUtil.e("__wechatPay_prepayId",payInfobean.getPrepayid());
        LogUtil.e("__wechatPay_getPackageX",payInfobean.getPackageX());
        LogUtil.e("__wechatPay_getNoncestr",payInfobean.getNoncestr());
        LogUtil.e("__wechatPay_getTimestamp",String.valueOf(payInfobean.getTimestamp()));
        LogUtil.e("__wechatPay_getSign",payInfobean.getSign());
        request.appId = payInfobean.getAppid();
        request.partnerId = payInfobean.getPartnerid();
        request.prepayId= payInfobean.getPrepayid();
        request.packageValue = payInfobean.getPackageX();
        request.nonceStr= payInfobean.getNoncestr();
        request.timeStamp= String.valueOf(payInfobean.getTimestamp());
        request.sign= payInfobean.getSign();

        api.sendReq(request);
    }

    /**
     * 启动界面的时候，需要先将APP注册到微信
     */
    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, UserManager.getInstance().getApp_id(), true);
        // 将应用的appId注册到微信
        api.registerApp( UserManager.getInstance().getApp_id());
        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 将该app注册到微信
                api.registerApp(UserManager.getInstance().getApp_id());
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }

    /**
     * 拉起支付宝支付
     * @param createOrderModel
     */
    private void alipay(CreateOrderAndPayModel.Databean createOrderModel){
        LogUtil.e("__alipay","alipay");
        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(VipCenterActivity.this);
//                Map<String,String> result = alipay.payV2(orderInfo,true);
                LogUtil.e("__getPay_info_Src",createOrderModel.getPay_info().getAlipay());
//                final String orderInfo = "alipay_sdk=alipay-sdk-php-20180705&amp;app_id=2019073066063134&amp;biz_content=%7B%22body%22%3A%22%5Cu4e00%5Cu4e2a%5Cu6708%5Cu4f1a%5Cu5458%5Cu5957%5Cu9910%22%2C%22subject%22%3A%22%5Cu4e00%5Cu4e2a%5Cu6708%5Cu4f1a%5Cu5458%5Cu5957%5Cu9910%22%2C%22out_trade_no%22%3A%22GL201907311605024632007263%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%2212.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&amp;charset=UTF-8&amp;format=json&amp;method=alipay.trade.app.pay&amp;notify_url=http%3A%2F%2Fglapp.x1298.com%2Fapi%2Fpay_notify_url%2Falipay&amp;sign_type=RSA2&amp;timestamp=2019-07-31+16%3A05%3A02&amp;version=1.0&amp;sign=f2KHzVT9wn%2BWtNjr8fMwNHSAbFrkpxIV9jTym9S1GAbfoZGQAr%2FGHBVEhIpnsWSKV6bwJubeSHMjrgRSSVDaxu8B2rJhAm89x8njTiv1X8ttTJwS9mdZjdEx74RX%2FBmbprmH%2Fpttk7JgwS8c0xrZBgD8VB72%2FEintd5RaiW5ojt8FCLAiem%2BsHOnFr9LP9k1YgQsSC8DWJFmjFom8fTxXY9etUi%2Bk1TJlWUCobYIK0uzvn1Qnw05pcs2Z03SS3Q18LRO%2FAGYAP00IFxP5f09muGNO0Zd3KndWja9MFHYf4Ek8SgjIjMy62Txs%2BNlUmx5MNX6rwMoTXoXEzDq3GTdyA%3D%3D";

                //json解析的时候会把&自动转化成&amp;，替换之后再调起支付
                final String orderInfoSrc = createOrderModel.getPay_info().getAlipay();
                final String orderInfo = orderInfoSrc.replace("&amp;","&");
                LogUtil.e("__getPay_info",createOrderModel.getPay_info().getAlipay());
                Map<String,String> result = alipay.payV2(orderInfo,true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showAlert(VipCenterActivity.this, "支付成功:" + payResult);
                        showAlert(VipCenterActivity.this, "支付成功");
                        mHttpHelper.getUserInfo(VipCenterActivity.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(VipCenterActivity.this, "支付失败:" + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(VipCenterActivity.this, "授权成功:" + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(VipCenterActivity.this, "授权失败:" + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    private void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private  void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toOrderDetail();
                    }
                })
                .setOnDismissListener(onDismiss)
                .show();
    }
    private void showSuccessAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        toOrderDetail();
                    }
                })
                .setOnDismissListener(onDismiss)
                .show();
    }

    private void toOrderDetail(){
        Intent intent = new Intent(this,OrderDetailActivity.class);
        intent.putExtra("order_id",mDatabean.getOrder_id());
        startActivity(intent);
    }

    /**
     * 计算屏幕跟图片宽度的比例
     * @return
     */
    private float[] getImgHeight(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.vip_privilege, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        float i[] = new float[2];
        i[0] = screenWidth/imageWidth;
        i[1] = imageHeight;
//        LogUtil.e("__initData_imageWidth",String.valueOf(imageWidth));
//        LogUtil.e("__initData_i",String.valueOf(i));
//        LogUtil.e("__initData_i_int_0",String.valueOf((int)i[0]));
//        LogUtil.e("__initData_i_int_1",String.valueOf((int)i[1]));

        return i;
    }

    public int calculateInSampleSize(BitmapFactory.Options options,
                                     int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
