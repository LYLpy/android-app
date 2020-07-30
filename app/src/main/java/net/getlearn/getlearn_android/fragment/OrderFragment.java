package net.getlearn.getlearn_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.OrderDetailActivity;
import net.getlearn.getlearn_android.adapter.RvAdapterMyOrder;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.MyOrderModel;
import net.getlearn.getlearn_android.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 订单fragment
 */

public class OrderFragment extends BaseFragment implements BaseHelper.IHttpCallback {


    @BindView(R.id.ll_not_data)
    LinearLayout mLlNotData;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;
    @BindView(R.id.rv_data)
    RecyclerView mRv;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    private RvAdapterMyOrder mRvAdapter;

    public static OrderFragment getInstanace(int status,boolean getAllOrder) {
        OrderFragment fm = new OrderFragment();
        Bundle b = new Bundle();
        b.putInt("status", status);
        b.putBoolean("getAllOrder", getAllOrder);
        fm.setArguments(b);
        return fm;
    }
    private MyOrderModel.DatabeanX mData;
    private int status;
    private int page = 1;
    private int limit = 20;
    private boolean getAllOrder = true;//是否获取全部订单

    @Override
    protected void initData() {
        Bundle args = getArguments();
        status = args.getInt("status");
        getAllOrder = args.getBoolean("getAllOrder");
        page = 1;
        mData = null;
        mHttpHelper.getMyOrder(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), getAllOrder,status, page, limit);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_order;
    }

    @Override
    protected void initView(View view) {
        initSpringView();
    }

    @Override
    public void onClick(View view) {

    }
    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(getActivity()));
        mSpringView.setFooter(new DefaultFooter(getActivity()));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mData = null;
                        if (mRvAdapter != null){
                            mRvAdapter.removeData();
                        }
                        mHttpHelper.getMyOrder(OrderFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),getAllOrder, status, page, limit);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getMyOrder(OrderFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), getAllOrder,status, page, limit);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    /**
     * 网络访问成功
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的javabean数据
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_MY_ORDER:
                MyOrderModel myOrderModel = (MyOrderModel) msg.obj;
                setData(myOrderModel);
                break;
        }
    }

    private void setData(MyOrderModel myOrderModel) {
        page++;
        //证明是第一次加载
        if (mData == null){
            mData = myOrderModel.getData();
            mRvAdapter = new RvAdapterMyOrder(getActivity(),mData);
            mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRv.setNestedScrollingEnabled(false);
            mRv.setAdapter(mRvAdapter);
            mRvAdapter.setOnButtonPayClickListener(new RvAdapterMyOrder.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                    intent.putExtra("order_id",mData.getData().get(position).getOrder_id());
                    getActivity().startActivity(intent);
//                    LogUtil.e("__",position + "");
                }
            });
            //之前已经有数据了，将数据加进去，刷新即可
        }else {
            List<MyOrderModel.DatabeanX.Databean> data = mData.getData();
            List<MyOrderModel.DatabeanX.Databean> newData = myOrderModel.getData().getData();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mRvAdapter.notifyDataSetChanged();
        }
        if (mData != null && mData.getData() != null && mData.getData().size() > 0){
//            mLlNoData.setVisibility(View.GONE);
            mLlNotData.setVisibility(View.GONE);
            //设置layout_gravity
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.TOP;
            mRlContent.setLayoutParams(lp);
        }
    }

    /**
     * 网络访问失败
     *
     * @param reqType 区分调用的是哪一个接口
     * @param error   请求失败的原因
     */
    @Override
    public void onHttpError(int reqType, String error) {

    }

}
