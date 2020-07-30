package net.getlearn.getlearn_android.activity;

import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.MImageGetter;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.UserProtocolModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;

import butterknife.BindView;

public class SecretProtocolActivity extends BaseActivity {

    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.iv_not_data)
    ImageView mIvNotData;
    private UserProtocolModel.Databean mDatabean;
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fl_back:
                finish();
//                Toast.makeText(ReadActivity.this,"触发返回键",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    @Override
    protected void initData() {
        mHttpHelper.getUserSecrecy(this, CommonUtils.getCurrentTimeStampInt());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_secret_protocol;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
    }


    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_USER_SECRECY:
                UserProtocolModel userProtocolModel = (UserProtocolModel) msg.obj;
                mDatabean = userProtocolModel.getData();
                setData();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_USER_SECRECY:
                ToastUtil.showToast("获取协议失败，请重试");
                break;
        }
    }


    private void setData() {
        mIvNotData.setVisibility(View.GONE);
        mTvContent.setText(Html.fromHtml(mDatabean.getProtocol(), new MImageGetter(mTvContent, getApplicationContext()), null));
    }
}
