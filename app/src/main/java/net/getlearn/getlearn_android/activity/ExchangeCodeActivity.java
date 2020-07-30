package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.ExchangeCodeModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeCodeActivity extends BaseActivity {

    Intent intent;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_code)
    EditText tvCode;
    @BindView(R.id.iv_clean_edit)
    ImageView ivCleanEdit;
    @BindView(R.id.btn_exchange_code)
    Button btnExchangeCode;
    @BindView(R.id.tv_exchange_records)
    TextView tvExchangeRecords;
    @BindView(R.id.tv_exchange_mag)
    TextView tvExchangeMag;

    private String code;

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_exchange_code;
    }

    @Override
    protected void initView() {
        ivBack.setOnClickListener(this);
        btnExchangeCode.setOnClickListener(this);
        ivCleanEdit.setOnClickListener(this);
        tvExchangeRecords.setOnClickListener(this);
        tvExchangeMag.setVisibility(View.GONE);
    }

    public void setView() {
        code = tvCode.getText().toString();
        Log.e("__code", code);
        if (code == null || code.equals("") || code == "") {
            tvExchangeMag.setVisibility(View.VISIBLE);
            tvExchangeMag.setText("请输入兑换码");
            tvExchangeMag.setTextColor(this.getResources().getColor(R.color.hot_red_home));

        } else {
//            intent = new Intent(this,ExchangeCodeSuccessActivity.class);
//            startActivity(intent);
            mHttpHelper.exchangeCode(ExchangeCodeActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), code);
            LogUtil.e("mHttpHelper", mHttpHelper + "");

        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Nullable
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_exchange_code:
                setView();
                break;
            case R.id.iv_clean_edit:
                tvCode.setText("");
                tvExchangeMag.setVisibility(View.GONE);
                break;
            case R.id.tv_exchange_records:
                intent = new Intent(this, ExchangeRecordsActivity.class);
                startActivity(intent);
                break;
        }
    }


    /**
     * 请求成功
     *
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的javabean数据
     */
    @Nullable
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_EXCHANGE_CODE:
                ExchangeCodeModel exchangeCodeModel = (ExchangeCodeModel) msg.obj;

                code = exchangeCodeModel.getData().getName();
                Intent intent = new Intent(this, ExchangeCodeSuccessActivity.class);
                intent.putExtra("code", code);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 请求失败
     *
     * @param reqType 区分调用的是哪一个接口
     * @param error   请求失败的原因
     */
    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_GET_EXCHANGE_CODE:
                tvExchangeMag.setVisibility(View.VISIBLE);
                tvExchangeMag.setText(error);
                tvExchangeMag.setTextColor(this.getResources().getColor(R.color.hot_red_home));
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
