package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import java.util.Date;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 */

public class LoginActivity extends BaseActivity implements BaseHelper.IHttpCallback {

    @BindView(R.id.tv_send_to)
    TextView mTvSendTo;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.rl_input_verification_code)
    RelativeLayout mRlVerificationCode;
    @BindView(R.id.rl_input_invite_code)
    RelativeLayout mRlInviteCode;
    @BindView(R.id.iv_clean_verification_code)
    ImageView mIvCleanVerificationCode;
    @BindView(R.id.iv_clean_invite_code)
    ImageView mIvCleanInviteCode;
    @BindView(R.id.edit_verification_code)
    EditText mEditVerificationCode;
    @BindView(R.id.edit_invite_code)
    EditText mEditInviteCode;
    @BindView(R.id.tv_invite_code_title)
    TextView tvInviteCodeTitle;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    private ButtonCountDownTimerUtil mCountDownTimer;
    private String phone;
    private String verify_id;
    private boolean is_old;//是否老用户
    private Intent mIntent;
    String mInviteCode = "";
    @Override
    protected void initData() {

        mCountDownTimer = new ButtonCountDownTimerUtil(mTvGetVerificationCode,60 * 1000, 1000);
        mCountDownTimer.setCallBack(new ButtonCountDownTimerUtil.CallBack() {
            @Override
            public void onTick() {
                mTvGetVerificationCode.setBackground(LoginActivity.this.getResources().getDrawable(R.drawable.shape_code_gray3));
            }
            @Override
            public void onFinish() {
                mTvGetVerificationCode.setBackground(LoginActivity.this.getResources().getDrawable(R.drawable.selector_code));
            }
        });
        try {
            mIntent = getIntent();
            if (mIntent.getExtras() != null){
                phone = mIntent.getExtras().getString("phone");
                verify_id = mIntent.getExtras().getString("verify_id");
                is_old = mIntent.getExtras().getBoolean("is_old");
                LogUtil.e("__phone",phone);
                LogUtil.e("__verify_id_preAct",verify_id);
            }
            if (is_old){
                tvInviteCodeTitle.setVisibility(View.GONE);
                mRlInviteCode.setVisibility(View.GONE);
            }
            if (phone != null){
                mTvSendTo.setVisibility(View.VISIBLE);
                mTvSendTo.setText("已发送至" + phone);
                mCountDownTimer.start();
            }else {
//                ToastUtil.showToast("没有手机号码");
                finish();
            }
        }catch (Exception e){
            e.getStackTrace();
            ToastUtil.showToast("数据错误");
            finish();
        }

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mTvGetVerificationCode.setOnClickListener(this);
        mIvCleanVerificationCode.setOnClickListener(this);
        mIvCleanInviteCode.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_verification_code:
                mHttpHelper.getVerificationCode(this,phone,CommonUtils.getTimeStampInt(new Date()));
                mCountDownTimer.start();
                mTvSendTo.setText("已发送至" + phone);
                break;
            case R.id.iv_clean_verification_code:
                mEditVerificationCode.setText("");
                break;
            case R.id.iv_clean_invite_code:
                mEditInviteCode.setText("");
                break;
            case R.id.fl_back:
                finish();
                break;
            case R.id.btn_confirm:
                String codeStr = mEditVerificationCode.getText().toString().replace(" ","");
                 if (codeStr.length() < 4){
                    ToastUtil.showToast("请输入4位以上验证码");
                    return;
                }
//                if (codeStr.length() != 4){
//                    ToastUtil.showToast("请输入4位验证码");
//                    return;
//                }
                try {
                    if (mEditInviteCode.getText() != null){
                        mInviteCode = mEditInviteCode.getText().toString().trim();
                    }
                    LogUtil.e("__verify_id_request",verify_id);
                    mHttpHelper.login(this,verify_id, CommonUtils.getTimeStampInt(new Date()),codeStr,phone,"",this,mInviteCode);
                }catch (Exception e){
                    ToastUtil.showToast("请输入4位数字验证码");
                }
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        try {
        if ( reqType == Constants.REQUEST_LOGIN){
            LoginModel model = (LoginModel) msg.obj;
            UserManager.getInstance().setToken(model.getData().getToken());
            UserManager.getInstance().setUserId(model.getData().getUid());
            UserManager.getInstance().setHeadImgUrl(model.getData().getHeadimgurl());
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

            LogUtil.e("__course() == null",String.valueOf(model.getData().getSelect_course() == null));
            UserManager.getInstance().setFirstUse(false);
            if (model.getData().getSelect_course() == null || model.getData().getSelect_course().getSelect_course() == null){
                Intent intent = new Intent(this,SelectGradeActivity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }else if (reqType == Constants.REQUEST_VERIFICATIONCODE){
            GetVerificationCodeModel model = (GetVerificationCodeModel) msg.obj;
            verify_id = model.getData().getVerify_id();
            LogUtil.e("__verify_id_respond",verify_id);
        }
        }catch (Exception e){
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_VERIFICATIONCODE:
                ToastUtil.showToast(String.valueOf("获取验证码失败，请稍后重试"));
                break;
                default:
                    LogUtil.e("__onHttpError",String.valueOf(error));
                    ToastUtil.showToast(error);
                    break;
        }

    }

    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        LogUtil.e("__Login","onDestroy");
        super.onDestroy();
    }
}
