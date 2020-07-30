package net.getlearn.getlearn_android.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterAoePPT;
import net.getlearn.getlearn_android.model.bean.AoePPTModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
* 专题课堂
* */
public class AoePPTActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;

    @BindView(R.id.spring_view)
    SpringView mSpringView;
    @BindView(R.id.iv_no_data)
    ImageView mIvNoData;

    private RvAdapterAoePPT adapterAoePPT;
    private AoePPTModel.DataBeanX mDataX;

    //private List<AoePPTModel.DataBeanX.DataBean> aoePPTModelList = new ArrayList<>();
    private int limit = 20;
    private int page = 1;

    //private int per_page = 20;
    @Override
    protected void initData() {
        mHttpHelper.getAoePPT(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), limit, page);
//        for (int data = 0; data <= 15; data++) {
//            AoePPTModel aoePPTModel = new AoePPTModel(R.drawable.img_courseselection_microclass, "语文（xx）" + data, "xxxx");
//            aoePPTModelList.add(aoePPTModel);
//       }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_aoe_ppt;
    }

    @Override
    protected void initView() {
        ivBack.setOnClickListener(this);
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
                        mDataX = null;
                        if (adapterAoePPT != null) {
                            adapterAoePPT.removeData();
                        }
                        mHttpHelper.getAoePPT(AoePPTActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), limit, page);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        mHttpHelper.getAoePPT(AoePPTActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), limit, page);
                        mSpringView.onFinishFreshAndLoad();
//                        ToastUtil.showToast("加载成功");
                    }
                }, 200);
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
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
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
        switch (reqType) {
            case Constants.REQUEST_GET_AOEPPT:
                AoePPTModel aoePPTModel = (AoePPTModel) msg.obj;
                setData(aoePPTModel.getData());
                break;
        }
    }

    public void setData(AoePPTModel.DataBeanX aoePPTModel) {
        //第一次加载
        if (mDataX == null) {
            mDataX = aoePPTModel;
            adapterAoePPT = new RvAdapterAoePPT(this, mDataX);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            rvClass.setAdapter(adapterAoePPT);
            rvClass.setLayoutManager(gridLayoutManager);
            adapterAoePPT.setOnItemButtonClickListener(new RvAdapterAoePPT.OnItemButtonClickListener() {
                @Override
                public void onItemButtonClick(int position) {
                    LogUtil.e("__onItemButtonClick", position + "");
                    Intent intent = new Intent(AoePPTActivity.this, VideoPlayActivity.class);
                    intent.putExtra("course_id", mDataX.getData().get(position).getCourse_id());
                    startActivity(intent);
                }
            });
            //之前已经有数据了，将数据加进去，刷新即可
        } else {
            //Log.e("__mData", "sssss");
            List<AoePPTModel.DataBeanX.DataBean> dataBeans = mDataX.getData();
            List<AoePPTModel.DataBeanX.DataBean> dataBeanList = aoePPTModel.getData();
            if (dataBeanList == null || dataBeanList.size() == 0 && mDataX != null) {
                        ToastUtil.showToast("没有更多了");
            }
            dataBeans.addAll(dataBeanList);
            adapterAoePPT.notifyDataSetChanged();
        }
        if (mDataX != null && mDataX != null && mDataX.getData().size() > 0 && mIvNoData != null) {
            mIvNoData.setVisibility(View.GONE);

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
            case Constants.REQUEST_GET_AOEPPT:
                ToastUtil.showToast("获推荐专题列表失败");
                //Log.e("__推荐专题", error);
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
