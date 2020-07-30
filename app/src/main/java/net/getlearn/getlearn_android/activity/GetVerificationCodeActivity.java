package net.getlearn.getlearn_android.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.GetVerificationCodeModel;
import net.getlearn.getlearn_android.model.bean.LoginModel;
import net.getlearn.getlearn_android.model.bean.WXLoginInfoModel;
import net.getlearn.getlearn_android.model.bean.WXLoginModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.ButtonCountDownTimerUtil;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 * 获取验证码/微信等联合登录的界面
 */

public class GetVerificationCodeActivity extends BaseActivity implements BaseHelper.IHttpCallback {

    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.edit_verification_code)
    EditText mEditVerificationCode;

    @BindView(R.id.ll_weichat_login)
    LinearLayout ll_weichat_login;
    @BindView(R.id.ll_qq_login)
    LinearLayout ll_qq_login;
    @BindView(R.id.iv_clean_phone)
    ImageView mIvCleanPhone;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_login_by_other_way)
    TextView mTvLoginByOtherWay;
    @BindView(R.id.cbox_protocol)
    CheckBox mCheckBox;
    @BindView(R.id.tv_get_verification)
    TextView mTvGetVerify;
    @BindView(R.id.tv_user_protocol)
    TextView mTvUserProrocol;
    @BindView(R.id.tv_secret_protocol)
    TextView mTvSecretProrocol;
    private String phone = "";
    private String verify_id;
    private ButtonCountDownTimerUtil mCountDownTimer;
    @Override
    protected void initData() {
        mCountDownTimer = new ButtonCountDownTimerUtil(mTvLogin,60 * 1000, 1000);
//        mCountDownTimer = new ButtonCountDownTimerUtil(mTvGetVerificationCode,5 * 1000, 1000);
        mCountDownTimer.setCallBack(new ButtonCountDownTimerUtil.CallBack() {
            @Override
            public void onTick() {
//                mTvGetVerify.setBackground(getResources().getDrawable(R.drawable.shape_code_gray3));
                mTvLogin.setBackground(getResources().getDrawable(R.drawable.shape_gray3));
            }
            @Override
            public void onFinish() {
//                mTvGetVerify.setBackground(getResources().getDrawable(R.drawable.selector_code));
                mTvLogin.setBackground(getResources().getDrawable(R.drawable.selectot_button_blue));
            }
        });
        checkPermission();
//        if (!UserManager.getContext().getFirstUse()){
//            startActivity(new Intent(this,MainActivity.class));
//        }
        mHttpHelper.getWxLoginInfo(this,CommonUtils.getCurrentTimeStampInt());
        mCheckBox.setChecked(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_get_verification_code;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mTvLogin.setOnClickListener(this);
        mIvCleanPhone.setOnClickListener(this);
        ll_weichat_login.setOnClickListener(this);
        ll_qq_login.setOnClickListener(this);
        mTvGetVerify.setOnClickListener(this);
        mTvUserProrocol.setOnClickListener(this);
        mTvSecretProrocol.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:
                if (!mCheckBox.isChecked()){
                    ToastUtil.showToast("请先阅读相关协议");
                    return;
                }
                if ("".equals(mEditPhone.getText().toString().trim())){
                    ToastUtil.showToast("手机号码不能为空");
                    return;
                }else if (mEditPhone.getText().toString().trim().length()!=11){
                    ToastUtil.showToast("请输入11位手机号码");
                    return;
                } else {
                    phone = mEditPhone.getText().toString().trim().replace(" ","");
                    mHttpHelper.getVerificationCode(this, phone, CommonUtils.getCurrentTimeStampInt());
                    mCountDownTimer.start();
                }
                break;
            case R.id.iv_clean_phone:
                mEditPhone.setText("");
                break;
            case R.id.ll_weichat_login:
                if (!mCheckBox.isChecked()){
                    ToastUtil.showToast("请先阅读相关协议");
                    return;
                }
                //如果未空则表示还没有从后台获取到微信登录信息，需要获取之后，客户再重新点击登录
                if (UserManager.getInstance().getScope() == null || UserManager.getInstance().getScope().equals("")){
                    mHttpHelper.getWxLoginInfo(this,CommonUtils.getCurrentTimeStampInt());
                    delayHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToast("获取微信登录信息失败，请重试");
                        }
                    },2000);
                    return;
                }
                SendAuth.Req req = new SendAuth.Req();
                req.scope = UserManager.getInstance().getScope();
                req.state = UserManager.getInstance().getState();
                api.sendReq(req);
                break;
            case R.id.ll_qq_login:
                if (!mCheckBox.isChecked()){
                    ToastUtil.showToast("请先阅读相关协议");
                    return;
                }
                ToastUtil.showToast("暂不支持QQ联合登录，敬请期待");
                break;
            case R.id.tv_get_verification:
                if ("".equals(mEditPhone.getText().toString().trim())){
                    ToastUtil.showToast("手机号码不能为空");
                    return;
                }else if (mEditPhone.getText().toString().trim().length()!=11){
                    ToastUtil.showToast("请输入11位手机号码");
                    return;
                } else {
                    phone = mEditPhone.getText().toString().trim().replace(" ","");
                    mHttpHelper.getVerificationCode(this, phone, CommonUtils.getCurrentTimeStampInt());
                    mCountDownTimer.start();
                }
                break;
            case R.id.tv_user_protocol:
                startActivity(new Intent(this,UserProtocolActivity.class));
                break;
            case R.id.tv_secret_protocol:
                startActivity(new Intent(this,SecretProtocolActivity.class));
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        try {
            switch (reqType){
                case Constants.REQUEST_WX_LOGIN:
                    WXLoginModel wxLoginModel = (WXLoginModel) msg.obj;
                    setWxLoginData(wxLoginModel);
                    break;
                case Constants.REQUEST_VERIFICATIONCODE:
                    GetVerificationCodeModel model = (GetVerificationCodeModel) msg.obj;
                    verify_id = model.getData().getVerify_id();

                    Intent intent = new Intent(this,LoginActivity.class);
                    intent.putExtra("phone", model.getData().getPhone());
                    intent.putExtra("verify_id", model.getData().getVerify_id());
                    LogUtil.e("__verify_id_get_",model.getData().getVerify_id());
                    if (model.getData().getIs_old() == 1){
                        intent.putExtra("is_old", true);
                    }else {
                        intent.putExtra("is_old", false);
                    }
                    startActivity(intent);
                    break;
                    case Constants.REQUEST_GET_WXLOGIN_INFO:
                        WXLoginInfoModel wxLoginInfoModel = (WXLoginInfoModel) msg.obj;
                        WXLoginInfoModel.Databean databean = wxLoginInfoModel.getData();
                        UserManager.getInstance().setScope(databean.getScope());
                        UserManager.getInstance().setState(databean.getState());
                        UserManager.getInstance().setApp_id(databean.getAppid());
                        regToWx();
                        break;
                case Constants.REQUEST_LOGIN:
                    dismissProgressDialog();
                    LoginModel loginModeldel = (LoginModel) msg.obj;
                    UserManager.getInstance().setToken(loginModeldel.getData().getToken());
                    UserManager.getInstance().setHeadImgUrl(loginModeldel.getData().getHeadimgurl());
                    UserManager.getInstance().setUserPhone(loginModeldel.getData().getPhone());
                    UserManager.getInstance().setIntegral(loginModeldel.getData().getIntegral());
                    UserManager.getInstance().setSex(loginModeldel.getData().getSex());
                    UserManager.getInstance().setUserIcon(loginModeldel.getData().getHeadimgurl());
                    UserManager.getInstance().setNickName(loginModeldel.getData().getWechatnickname());
                    if (loginModeldel.getData().getCountry() != null){
                        UserManager.getInstance().setCountry(loginModeldel.getData().getCountry());
                    }
                    if (loginModeldel.getData().getProvince() != null){
                        UserManager.getInstance().setProvince(loginModeldel.getData().getProvince());
                    }
                    if (loginModeldel.getData().getCity() != null){
                        UserManager.getInstance().setCity(loginModeldel.getData().getCity());
                    }
                    if (loginModeldel.getData().getIsVip() == 0){
                        UserManager.getInstance().setIsVip(false);
                    }else {
                        UserManager.getInstance().setIsVip(true);
                    }
                    LogUtil.e("__course() == null",String.valueOf(loginModeldel.getData().getSelect_course() == null));
                    UserManager.getInstance().setFirstUse(false);
                    if (loginModeldel.getData().getSelect_course() == null || loginModeldel.getData().getSelect_course().getSelect_course() == null){
                        Intent intent1 = new Intent(this,SelectGradeActivity.class);
                        startActivity(intent1);
                    }else {
                        Intent intent1 = new Intent(this,MainActivity.class);
                        //跳转并清除前面的的activity
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    }
                    break;
            }
        }catch (Exception e){
                ToastUtil.showToast("解析错误，请重试");
            LogUtil.e("__Exception_verify","解析错误，请重试" + e.getMessage());
        }
    }

    private void setWxLoginData(WXLoginModel model) {
//        LogUtil.e("__setWxLoginData_getPhone",wxLoginModel.getData().getIsVip() + "");
//        LogUtil.e("__setWxLoginData_getPhone",model.getData().getUserPhone());
//        LoginModel model = (LoginModel) msg.obj;
        try{
        UserManager.getInstance().setToken(model.getData().getToken());
            UserManager.getInstance().setHeadImgUrl(model.getData().getHeadimgurl());
        UserManager.getInstance().setUserPhone(model.getData().getPhone());
        UserManager.getInstance().setIntegral(model.getData().getIntegral());
        UserManager.getInstance().setSex(model.getData().getSex());
        UserManager.getInstance().setUserIcon(model.getData().getHeadimgurl());
        UserManager.getInstance().setNickName(model.getData().getWechatnickname());
        if (model.getData().getCountry() != null){
            UserManager.getInstance().setCountry(model.getData().getCountry());
        }
        if (model.getData().getProvince() != null){
            UserManager.getInstance().setProvince(model.getData().getProvince());
        }
        if (model.getData().getCity() != null){
            UserManager.getInstance().setCity(model.getData().getCity());
        }
        if (model.getData().getIsVip() == 0){
            UserManager.getInstance().setIsVip(false);
        }else {
            UserManager.getInstance().setIsVip(true);
        }
        Intent intent;
        LogUtil.e("__setWxLoginData_getPhone_course()==null",String.valueOf(model.getData().getSelect_course()==null));
        if (model.getData().getPhone() == null || model.getData().getPhone().equals("")){
            LogUtil.e("__setWxLoginData_getPhone",UserManager.getInstance().getUserPhone());
            intent = new Intent(this,CompleteRegisterActivity.class);
        }else if (model.getData().getSelect_course() == null || model.getData().getSelect_course().getSelect_course() == null){
            intent = new Intent(this,SelectGradeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }else {
            LogUtil.e("__getPhone",model.getData().getPhone());
            intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }catch (Exception e){

    }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_WXLOGIN_INFO:
                //不弹出，因为一进来界面就会从后台获取微信登录信息
//                ToastUtil.showToast("获取微信登录信息失败");
                break;
            case Constants.REQUEST_LOGIN:
                dismissProgressDialog();
                ToastUtil.showToast(String.valueOf("登录失败"));
                break;
            case Constants.REQUEST_VERIFICATIONCODE:
                ToastUtil.showToast(String.valueOf("获取验证码失败，请稍后重试"));
                break;
        }
//        ToastUtil.showToast(error);
//        switch (){
//            case :
//                break;
//        }
    }



    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        LogUtil.e("__GetVerificationCodeActivity","onDestroy");
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
//    public static final String APP_ID = "wxa2429fce0d35f2b1";
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    /**
     * 启动界面的时候，需要先将APP注册到微信
     */
    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, UserManager.getInstance().getApp_id(), true);
        // 将应用的appId注册到微信
        api.registerApp(UserManager.getInstance().getApp_id());
        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 将该app注册到微信
                api.registerApp(UserManager.getInstance().getApp_id());
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }

    public static final int PERMISSIONS_REQUEST_STORAGE = 1;
    private void checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_STORAGE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
       String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
//                    LogUtil.e("__","请先授予APP读取手机状态权限");
                    LogUtil.e("__","权限不足");
                    }
                return;
            }
        }
    }
    //EventBus接收微信登录code
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
//            LogUtil.e("__handleData_getType",mMessageEvent.getType() + "");
            if (mMessageEvent.getType() == Constants.REQUEST_WX_LOGIN){
                LogUtil.e("__handleData_getContent",mMessageEvent.getContent());
                mHttpHelper.WXLogin(this,CommonUtils.getCurrentTimeStampInt(),mMessageEvent.getContent(),this);
            }

    }
}
