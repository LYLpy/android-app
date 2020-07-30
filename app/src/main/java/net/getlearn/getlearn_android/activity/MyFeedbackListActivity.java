package net.getlearn.getlearn_android.activity;

import android.content.Intent;
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
import net.getlearn.getlearn_android.adapter.RvAdapterMyFeedBackList;
import net.getlearn.getlearn_android.model.bean.MyFeedbackListModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/19------更新------
 * 我的反馈
 */

public class MyFeedbackListActivity extends BaseActivity {

    @BindView(R.id.iv_no_data)
    ImageView mIvNoData;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.spring_view)
    SpringView mSpringView;

    private MyFeedbackListModel.Databean mData;
    private int page = 1;
    private int per_page = 20;
    private RvAdapterMyFeedBackList mAdapter;
    @Override
    protected void initData() {
        LogUtil.e("__userId_MainActivity", UserManager.getInstance().getUserId() + "");
//        mHttpHelper.myFeedBackList(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page);
        mHttpHelper.myFeedBackMsgList(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,0);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_feedback;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        initSpringView();
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
        switch (reqType){
            case Constants.REQUEST_MY_FEEDBACK_LIST:
                MyFeedbackListModel myFeedbackModel = (MyFeedbackListModel) msg.obj;
                 setData(myFeedbackModel.getData());
                break;
        }
    }

    private void setData(MyFeedbackListModel.Databean myFeedBackModel) {
        if (myFeedBackModel!=null && myFeedBackModel.getFeedBackList()!=null &&
                myFeedBackModel.getFeedBackList().size() > 0){
            page++;
        }

        //证明是第一次加载
        if (mData == null){
            mData = myFeedBackModel;
            mAdapter = new RvAdapterMyFeedBackList(this,mData);
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new RvAdapterMyFeedBackList.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(MyFeedbackListActivity.this,MyFeedbackMsgActivity.class);
//                    intent.putExtra("parent_id",mData.getFeedBackList().get(position).getParentId());
                    intent.putExtra("parent_id",mData.getFeedBackList().get(position).getId());
                    String phone = mData.getFeedBackList().get(position).getPhone()+"";
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                }
            });
            //之前已经有数据了，将数据加进去，刷新即可
        }else {
            List<MyFeedbackListModel.Databean.FeedBackListbean> data = mData.getFeedBackList();
            List<MyFeedbackListModel.Databean.FeedBackListbean> newData = myFeedBackModel.getFeedBackList();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }
        if (mData != null && mData.getFeedBackList() != null && mData.getFeedBackList().size() > 0){
            mIvNoData.setVisibility(View.GONE);
        }
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
                        mHttpHelper.myFeedBackMsgList(MyFeedbackListActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,0);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.myFeedBackMsgList(MyFeedbackListActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,0);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_MY_FEEDBACK_LIST:
                ToastUtil.showToast("获取数据失败");
                break;
        }
    }
}
