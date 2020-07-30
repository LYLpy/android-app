package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.BaseActivity;
import net.getlearn.getlearn_android.model.bean.EditPhoneGetVerifyCodeModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;

import butterknife.BindView;
/*
* 修改手机
* */
public class EditPhoneActivity extends BaseActivity {

    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.iv_clean_edit_phone)
    ImageView mIvCleanEditPhone;
    @BindView(R.id.edit_verify_code)
    EditText mEditVerifyCode;
    @BindView(R.id.btn_verify_code)
    Button mBtnVerifyCode;
    @BindView(R.id.btn_binding)
    Button mBtnBinding;
    private String phone;
    private String verify_id;//验证码ID
    private String verify_code;//验证码

    @Override
    protected void initData() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        if (phone !=null){
            mEditPhone.setText(phone);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_binding:
                phone = mEditPhone.getText().toString().replace(" ","");
                verify_code = mEditVerifyCode.getText().toString().replace(" ","");
                if (phone.equals("")){
                    ToastUtil.showToast("手机号码不能为空");
                    return;
                }
                if (phone.length() != 11){
                    ToastUtil.showToast("手机号码必须为11位数");
                    return;
                }
                if (verify_code.equals("")){
                    ToastUtil.showToast("验证码不能为空");
                    return;
                }
                if (verify_code.length() < 4){
                    ToastUtil.showToast("验证码不能少于4位数");
                    return;
                }
                mHttpHelper.editPhone(this, CommonUtils.getCurrentTimeStampInt(),
                        UserManager.getInstance().getToken(),phone,verify_id,verify_code);
                break;
            case R.id.fl_back:
                finish();
                break;
            case R.id.btn_verify_code:
                phone = mEditPhone.getText().toString().replace(" ","");
                if (phone.equals("")){
                    ToastUtil.showToast("手机号码不能为空");
                    return;
                }
                if (phone.length() != 11){
                    ToastUtil.showToast("手机号码必须为11位数");
                    return;
                }
                mHttpHelper.editPhoneGetVerifyCode(this, CommonUtils.getCurrentTimeStampInt(),
                        UserManager.getInstance().getToken(),phone);
                break;
            case R.id.iv_clean_edit_phone:
                mEditPhone.setText("");
            default:
                break;
        }
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_phone;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mBtnVerifyCode.setOnClickListener(this);
        mBtnBinding.setOnClickListener(this);
        mIvCleanEditPhone.setOnClickListener(this);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_EDIT_PHONE_GET_VERSIFY_CODE:
                ToastUtil.showToast("验证码已发送");
                EditPhoneGetVerifyCodeModel editPhoneGetVerifyCodeModel  = (EditPhoneGetVerifyCodeModel) msg.obj;
                verify_id = editPhoneGetVerifyCodeModel.getData().getVerify_id();
                break;
            case Constants.REQUEST_EDIT_PHONE:
                ToastUtil.showToast("修改成功");
                finish();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_EDIT_PHONE_GET_VERSIFY_CODE:
                ToastUtil.showToast("获取验证码失败");
                break;
            case Constants.REQUEST_EDIT_PHONE:
                ToastUtil.showToast(error);
                break;
        }
    }

}
