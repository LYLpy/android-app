package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.GetVerificationCodeModel;
import net.getlearn.getlearn_android.model.bean.LoginModel;
import net.getlearn.getlearn_android.utils.ButtonCountDownTimerUtil;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/25------更新------
 * 微信或者QQ联合登录之后的完成注册页
 */
public class CompleteRegisterActivity extends BaseActivity implements BaseHelper.IHttpCallback{

    @BindView(R.id.edit_verification_code)
    EditText mEditVerificationCode;
    @BindView(R.id.tv_get_verification)
    TextView mTvGetVerificationCode;
    @BindView(R.id.edit_phone_num)
    EditText mEditPhone;
    @BindView(R.id.iv_clean_phone_num)
    ImageView mIvCleanPhoneNum;
    @BindView(R.id.btn_register_and_login)
    Button mBtnRegisterAndLogin;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;

    private String phone = "";
    private String verify_id;
    private ButtonCountDownTimerUtil mCountDownTimer;
    @Override
    protected void initData() {
        mCountDownTimer = new ButtonCountDownTimerUtil(mTvGetVerificationCode,60 * 1000, 1000);
//        mCountDownTimer = new ButtonCountDownTimerUtil(mTvGetVerificationCode,5 * 1000, 1000);
        mCountDownTimer.setCallBack(new ButtonCountDownTimerUtil.CallBack() {
            @Override
            public void onTick() {
                mTvGetVerificationCode.setBackground(CompleteRegisterActivity.this.getResources().getDrawable(R.drawable.shape_code));
            }
            @Override
            public void onFinish() {
                mTvGetVerificationCode.setBackground(CompleteRegisterActivity.this.getResources().getDrawable(R.drawable.selector_code));
                }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_complete_register;
    }

    @Override
    protected void initView() {
        mIvCleanPhoneNum.setOnClickListener(this);
        mBtnRegisterAndLogin.setOnClickListener(this);
        mTvGetVerificationCode.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
        mTvGetVerificationCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.iv_clean_phone_num:
                mEditPhone.setText("");
                break;
            case R.id.btn_register_and_login:
                    String codeStr = mEditVerificationCode.getText().toString().replace(" ","");
                    if (codeStr == null || codeStr.equals("")){
                        ToastUtil.showToast("验证码不能为空");
                        return;
                    }
//                    ToastUtil.showToast("注册并登录");
                    mHttpHelper.login(this,verify_id, CommonUtils.getCurrentTimeStampInt(),codeStr,phone,UserManager.getInstance().getToken(),this,"");
                break;
            case R.id.fl_back:
                finish();
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_LOGIN:
                LoginModel loginModel = (LoginModel) msg.obj;
                //将后台返回的token写入本地工具类里
                UserManager.getInstance().setToken(loginModel.getData().getToken());
                UserManager.getInstance().setHeadImgUrl(loginModel.getData().getHeadimgurl());
                UserManager.getInstance().setUserId(loginModel.getData().getUid());
                UserManager.getInstance().setIntegral(loginModel.getData().getIntegral());
                UserManager.getInstance().setSex(loginModel.getData().getSex());
                if (loginModel.getData().getCountry() != null){
                    UserManager.getInstance().setCountry(loginModel.getData().getCountry());
                }
                if (loginModel.getData().getProvince() != null){
                    UserManager.getInstance().setProvince(loginModel.getData().getProvince());
                }
                if (loginModel.getData().getCity() != null){
                    UserManager.getInstance().setCity(loginModel.getData().getCity());
                }
                if (loginModel.getData().getIsVip() == 0){
                    UserManager.getInstance().setIsVip(false);
                }else {
                    UserManager.getInstance().setIsVip(true);
                }
//                LogUtil.e("__course() == null",String.valueOf(loginModel.getData().getSelect_course() == null));
//                LogUtil.e("__course() == null_2",String.valueOf(loginModel.getData().getSelect_course().getSelect_course() == null));
//                LogUtil.e("__course() == null",String.valueOf(loginModel.getData().getSelect_course() == null));
//                LogUtil.e("__course() == null",String.valueOf(loginModel.getData().getSelect_course() == null));
                UserManager.getInstance().setFirstUse(false);
                if (loginModel.getData().getSelect_course().getSelect_course() == null){
                    Intent intent = new Intent(this,SelectGradeActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
                break;
            case Constants.REQUEST_VERIFICATIONCODE:
                GetVerificationCodeModel model = (GetVerificationCodeModel) msg.obj;
                verify_id = model.getData().getVerify_id();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        ToastUtil.showToast(error);
        LogUtil.e("__error"+reqType,error);
    }

    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        super.onDestroy();
    }
}
