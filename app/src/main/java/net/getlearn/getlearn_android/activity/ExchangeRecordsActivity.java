package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapdaterExchangeRecorde;
import net.getlearn.getlearn_android.model.bean.ExchangeCodeRecordsModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *
 * 兑换记录
 * */
public class ExchangeRecordsActivity extends BaseActivity {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.rv_exchange_recorde)
    RecyclerView rvExchangeRecorde;
    @BindView(R.id.tv_text)
    TextView tvText;
    private ExchangeCodeRecordsModel.DataBeanX codeList;
    private RvAdapdaterExchangeRecorde mAdapater;

    @Override
    protected void initData() {

        mHttpHelper.exchangeCodeLog(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_exchange_records;
    }

    @Override
    protected void initView() {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 请求成功
     *
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的javabean数据
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_EXCHANGE_LOG:
                ExchangeCodeRecordsModel exchangeRecordsModel = (ExchangeCodeRecordsModel) msg.obj;
                setData(exchangeRecordsModel.getData());

                break;
            default:
                break;
        }

    }

    public void setData(ExchangeCodeRecordsModel.DataBeanX exchangeRecordsModel) {
//        if (codeList == null) {
            codeList = exchangeRecordsModel;
            if (codeList == null || codeList.equals("")) {
                tvText.setVisibility(View.VISIBLE);
            } else {
                tvText.setVisibility(View.GONE);
                mAdapater = new RvAdapdaterExchangeRecorde(this, codeList);
                rvExchangeRecorde.setAdapter(mAdapater);
                rvExchangeRecorde.setLayoutManager(new GridLayoutManager(this, 1));
                mAdapater.setOnItemClickLitener(new RvAdapdaterExchangeRecorde.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), VipCenterActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
//        }else {
//            List<ExchangeCodeRecordsModel.DataBeanX.AppExchangeListBean.DataBean> dataBeans = codeList.getAppExchangeList().getData();
//
//        }
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
            case Constants.REQUEST_GET_EXCHANGE_LOG:
                LogUtil.e("__json72onHttpError", error);
                break;
            default:
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
