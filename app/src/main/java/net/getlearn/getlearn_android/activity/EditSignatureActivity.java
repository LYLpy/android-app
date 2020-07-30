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
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import butterknife.BindView;
/*
* 修改个性签名
* */
public class EditSignatureActivity extends BaseActivity {


    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.edit)
    EditText mEdit;
    @BindView(R.id.iv_clean_edit)
    ImageView mIvCleanEdit;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    private String signature;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        signature = intent.getStringExtra("signature");
        if (signature!=null){
            mEdit.setText(signature);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_exdit_signature;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mIvCleanEdit.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                String content = mEdit.getText().toString().replace(" ","");
                LogUtil.e("__initData",String.valueOf(mEdit.getText()) + "XXX");
                LogUtil.e("__initData_tostr",String.valueOf(mEdit.getText().toString()) + "XXX");
                if (content.equals("")){
                    ToastUtil.showToast("个性签名不能为空");
                    return;
                }
                mHttpHelper.editSignature(this, CommonUtils.getCurrentTimeStampInt(),
                        UserManager.getInstance().getToken(),content);
                break;
            case R.id.iv_clean_edit:
                mEdit.setText("");
                break;
            case R.id.fl_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_EDIT_SIGNATURE:
                ToastUtil.showToast("修改成功");
                finish();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_EDIT_SIGNATURE:
                ToastUtil.showToast("修改失败");
                break;
        }
    }

}
