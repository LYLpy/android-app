package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
* *
*
* 兑换码兑换成功
* */
public class ExchangeCodeSuccessActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_success)
    ImageView imgSuccess;
    @BindView(R.id.tv_exchange_name)
    TextView tvExchangeName;
    @BindView(R.id.tv_exchange_time)
    TextView tvExchangeTime;
    @BindView(R.id.btn_home)
    Button btnHome;
    private  String code;
    @Override
    protected void initData() {

    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
    sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
    Date date = new Date();// 获取当前时间
    tvExchangeTime.setText(sdf.format(date)+""); // 输出已经格式化的现在时间（24小时制）
    Intent getcode = getIntent();
    code = getcode.getStringExtra("code");
    tvExchangeName.setText(code);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_exchange_code_success;
    }

    @Override
    protected void initView() {
        imgBack.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                ActivityCollector.finishAll();
                break;
            default:
                break;
        }
    }

    /**
     * 请求成功
     *
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的javabean数据
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    /**
     * 请求失败
     *
     * @param reqType 区分调用的是哪一个接口
     * @param error   请求失败的原因
     */
    @Override
    public void onHttpError(int reqType, String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
