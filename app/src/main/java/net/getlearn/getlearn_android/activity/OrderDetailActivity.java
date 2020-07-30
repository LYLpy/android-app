package net.getlearn.getlearn_android.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import net.getlearn.getlearn_android.AuthResult;
import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.PayResult;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.CreateOrderAndPayModel;
import net.getlearn.getlearn_android.model.bean.OrderDetailModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.Map;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/30------更新------
 */

public class OrderDetailActivity extends BaseActivity implements BaseHelper.IHttpCallback {
    //返回按钮
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    //订单支付状态，已支付还是待付款
    @BindView(R.id.tv_pay_status)
    TextView mTvPayStatus;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    //优惠券数量
    @BindView(R.id.tv_coupon_amount)
    TextView mTvCouponAmount;
    @BindView(R.id.tv_order_price)
    TextView mTvOrderPrice;
    @BindView(R.id.tv_coupon_price)
    TextView mTvCouponPrice;
    @BindView(R.id.tv_pay_price)
    TextView mTvPayPrice;
    @BindView(R.id.tv_pay_way)
    TextView mTvPayWay;
    @BindView(R.id.tv_pay_time)
    TextView mTvPayTime;
    @BindView(R.id.tv_order_num)
    TextView mTvOrderNum;
    //订单描述
    @BindView(R.id.tv_order_describe)
    TextView mTvDesceibe;
    @BindView(R.id.tv_create_time)
    TextView mTvCreateTime;
    @BindView(R.id.rl_pay_time)
    RelativeLayout mRlPayTime;
    @BindView(R.id.rl_reality_pay)
    RelativeLayout mRlReality;

    @BindView(R.id.tv_pay)
    TextView mBtnPay;
    private int mOrderId;
    private OrderDetailModel.Databean mDatabean;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    @Override
    protected void initData() {
        regToWx();
        Intent intent = getIntent();
        mOrderId = intent.getIntExtra("order_id", 0);
        mHttpHelper.getOrderDetail(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), mOrderId);
         }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mBtnPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_pay:
                LogUtil.e("__onClick","btn_pay");
                if (mDatabean == null){
                    ToastUtil.showToast("获取订单信息失败，请刷新重试");
                    return;
                }
//                mHttpHelper.pay(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getContext().getToken(),
//                        mDatabean.getOrder_id(),mDatabean.getOrder_amount(),mDatabean.getPay_type());
                mHttpHelper.pay(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                        mDatabean.getOrder_id(),mDatabean.getOrder_amount(),mDatabean.getPay_type());
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_ORDER_DETAIL:
                OrderDetailModel orderDetailModel = (OrderDetailModel) msg.obj;
                mDatabean = orderDetailModel.getData();
                setData();
                break;
            case Constants.REQUEST_PAY:
                CreateOrderAndPayModel model = (CreateOrderAndPayModel) msg.obj;
                CreateOrderAndPayModel.Databean databean = model.getData();
                if (databean.getPay_type() == 0){
                    wechatPay(databean);
                }else {
                    alipay(databean);
                }
                break;
                default:break;
        }
    }


    private void setData() {
        mTvOrderNum.setText(mDatabean.getOrder_no());
         mTvCouponAmount.setText(mDatabean.getCoupon() + "张 | 抵用卷");
        mTvCouponPrice.setText( "¥" + mDatabean.getPreferential_amount());

        //待付款
        if (mDatabean.getOrder_status() == 0){
            mRlPayTime.setVisibility(View.GONE);
            mBtnPay.setVisibility(View.VISIBLE);
            mTvPayStatus.setText("待付款");
            mTvPayPrice.setText("¥" +  mDatabean.getPay_amount());
            //已完成
        }else {
            mTvPayStatus.setText("已完成");
            mRlPayTime.setVisibility(View.VISIBLE);
            mTvPayTime.setText(String.valueOf(mDatabean.getPay_time()));
            mBtnPay.setVisibility(View.GONE);
            mTvPayPrice.setText("¥" + mDatabean.getBuyer_pay_amount());
        }
        if (mDatabean.getPay_type() == 0){
            mTvPayWay.setText("微信");
        }else if(mDatabean.getPay_type() == 1){
            mTvPayWay.setText("支付宝");
        }
        mTvCreateTime.setText(mDatabean.getCreate_time());
        mTvDesceibe.setText(mDatabean.getSet_meal_name());
        mTvOrderPrice.setText("¥" + mDatabean.getOrder_amount());
        //加载图片
        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(this).load(mDatabean.getIcon()).apply(options).into(mIvIcon);
    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
    /**
     * 调起微信支付
     * @param createOrderModel
     */
    private void wechatPay(CreateOrderAndPayModel.Databean createOrderModel){

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
        PayReq request = new PayReq();
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
     * 拉起支付宝支付
     * @param createOrderModel
     */
    private void alipay(CreateOrderAndPayModel.Databean createOrderModel){

        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailActivity.this);
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
                        showAlert(OrderDetailActivity.this, "支付成功");
                        mHttpHelper.getOrderDetail(OrderDetailActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), mOrderId);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(OrderDetailActivity.this, "支付失败:" + payResult);
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
                        showAlert(OrderDetailActivity.this, "授权成功:" + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(OrderDetailActivity.this, "授权失败:" + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton("确定", null)
                .setOnDismissListener(onDismiss)
                .show();
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
}
