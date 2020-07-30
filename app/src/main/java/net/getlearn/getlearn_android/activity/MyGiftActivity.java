package net.getlearn.getlearn_android.activity;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterMyGiftList;
import net.getlearn.getlearn_android.model.bean.MyGiftListModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/17------更新------
 * 我的礼品
 */

public class MyGiftActivity extends BaseActivity {

    @BindView(R.id.iv_no_data)
    ImageView mIvNoData;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.spring_view)
    SpringView mSpringView;

    private MyGiftListModel.DataBeanX mData;
    private int page = 1;
    private int per_page = 20;
    private boolean getAllCoupon = true;//是否获取全部优惠券
    private RvAdapterMyGiftList mAdapter;
    @Override
    protected void initData() {
        mHttpHelper.myGiftList(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_gift;
    }

    @Override
    protected void initView() {
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
                        mHttpHelper.myGiftList(MyGiftActivity.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.myGiftList(MyGiftActivity.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);

                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        LogUtil.e("__onHttpSuccess",reqType + "__");
        switch (reqType){
            case Constants.REQUEST_MY_GIFT_LIST:
                MyGiftListModel myGiftListModel = (MyGiftListModel) msg.obj;
                setData(myGiftListModel.getData());
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_AOEPPT:
                ToastUtil.showToast("获取礼品列表失败");
                break;
        }
    }
    private void setData(MyGiftListModel.DataBeanX myGiftListModel) {
        if (myGiftListModel!=null && myGiftListModel.getAppGiftList()!=null &&
                myGiftListModel.getAppGiftList().getData()!=null && myGiftListModel.getAppGiftList().getData().size() > 0){
            page++;
        }
        //证明是第一次加载
        if (mData == null){

            mData = myGiftListModel;
            mAdapter = new RvAdapterMyGiftList(this,mData);
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemButtonClickListener(new RvAdapterMyGiftList.OnItemButtonClickListener() {
                @Override
                public void onItemButtonClick(int position) {
//                    Intent intent = new Intent(MyGiftActivity.this, VipCenterActivity.class);
//                    startActivity(intent);
                    LogUtil.e("__onItemButtonClick", position + "");
                }
            });
            //之前已经有数据了，将数据加进去，刷新即可
        }else {

            List<MyGiftListModel.DataBeanX.AppGiftListBean.DataBean> data = mData.getAppGiftList().getData();
            List<MyGiftListModel.DataBeanX.AppGiftListBean.DataBean> newData = myGiftListModel.getAppGiftList().getData();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }
        if (mData != null && mData.getAppGiftList() != null && mData.getAppGiftList().getData().size() > 0 && mIvNoData != null){
            mIvNoData.setVisibility(View.GONE);
            LogUtil.e("__json52","sssssssssssss");
        }
    }
}
