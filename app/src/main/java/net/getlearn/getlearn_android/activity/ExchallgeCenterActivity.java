package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterGiftList;
import net.getlearn.getlearn_android.model.bean.GiftListModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.GiftExchangeAlertDialog;

import java.util.List;

import butterknife.BindView;

/**
 * 兑换中心
 */
public class ExchallgeCenterActivity extends BaseActivity implements RvAdapterGiftList.OnItemClickListener {


    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_integral_title)
    TextView mTvIntegralTitle;
    @BindView(R.id.tv_integrals)
    TextView mTvIntegrals;
    @BindView(R.id.tv_my_gift)
    TextView mTvMyGift;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    private GiftListModel.DatabeanX mData;
    private RvAdapterGiftList mAdapter;
    private int page;
    private int per_page = 20;
    @Override
    protected void initData() {
        page = 1;
        mData = null;
        mHttpHelper.getGiftList(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_integral;
    }

    @Override
    protected void initView() {
//        android.support.v7.app.ActionBar actionBar =getSupportActionBar();
//        if (actionBar != null){
//            actionBar.hide();
//        }
        mTvMyGift.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
        initSpringView();
    }

    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(this));
        mSpringView.setFooter(new DefaultFooter(this));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mData = null;
                        if (mAdapter!=null){
                            mAdapter.removeData();
                        }
                        mHttpHelper.getGiftList(ExchallgeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getGiftList(ExchallgeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_gift:
                startActivity(new Intent(this,MyGiftActivity.class));
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
            case Constants.REQUEST_GET_GIFT_LIST:
                GiftListModel myOrderModel = (GiftListModel) msg.obj;
                setGiftData(myOrderModel);
                break;
            case Constants.REQUEST_GIFT_EXCHANGE:
                ToastUtil.showToast("兑换成功");
                page = 1;
                mData = null;
                if (mAdapter != null){
                    mAdapter.notifyDataSetChanged();
                }
                mHttpHelper.getGiftList(ExchallgeCenterActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);
                mSpringView.onFinishFreshAndLoad();
                break;
        }
    }

    private void setGiftData(GiftListModel giftListModel) {
        mTvIntegrals.setText(String.valueOf(giftListModel.getData().getUserIntegral()));
        page++;
        //证明是第一次加载
        if (mData == null){
            mData = giftListModel.getData();
            mAdapter = new RvAdapterGiftList(this,mData);
            mRv.setLayoutManager(new GridLayoutManager(this,2));
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(this);
            mRv.setNestedScrollingEnabled(false);
            //之前已经有数据了，将数据加进去，刷新即可
        }else {
            List<GiftListModel.DatabeanX.AppGiftListbean.Databean> data = mData.getAppGiftList().getData();
            List<GiftListModel.DatabeanX.AppGiftListbean.Databean> newData = giftListModel.getData().getAppGiftList().getData();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }
//        if (mData != null && mData.getData() != null && mData.getData().size() > 0 && mIvNoData != null){
//            mIvNoData.setVisibility(View.GONE);
//        }
    }
    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GIFT_EXCHANGE:
                ToastUtil.showToast(error);
                break;
        }
    }


    @Override
    public void onItemClick(int position) {
        GiftListModel.DatabeanX.AppGiftListbean.Databean databean = mData.getAppGiftList().getData().get(position);
        GiftExchangeAlertDialog alertDialog = new GiftExchangeAlertDialog(this).builder();
        alertDialog.setCancelable(true).setTitle(databean.getGiftName()).setMsg(databean.getPrice() + "积分")
                .setImgUrl(databean.getIcon())
                .setPositiveButton("马上兑换",R.color.white, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mHttpHelper.giftInventedExchange(ExchallgeCenterActivity.this,CommonUtils.getCurrentTimeStampInt(),
                                UserManager.getInstance().getToken(),databean.getId());
                    }
                });
        alertDialog.show();
    }
}
