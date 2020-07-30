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
import net.getlearn.getlearn_android.activity.VideoPlayActivity;
import net.getlearn.getlearn_android.adapter.RvAdapterMyCollect2;
import net.getlearn.getlearn_android.model.bean.MyColletcModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/13------更新------
 */

public class MyCollectFragment extends BaseFragment{

    @BindView(R.id.ll_not_data)
    LinearLayout mLlNoData;
    @BindView(R.id.rv_data)
    RecyclerView mRv;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    private RvAdapterMyCollect2 mAdapter;
    private IOSAlertDialog mDialog;
    private MyColletcModel.DatabeanX mData;
    private int subject;
    private int page;
    private int per_page = 20;
    private boolean isGetAllCollect = true;//是否获取全部收藏

    public static MyCollectFragment newInstance(int subject, boolean isGetAllCollect) {
        MyCollectFragment fm = new MyCollectFragment();
        Bundle b = new Bundle();
        b.putInt("subject", subject);
        b.putBoolean("isGetAllCollect", isGetAllCollect);
        fm.setArguments(b);
        return fm;
    }



    @Override
    protected void initData() {
        Bundle arg = getArguments();
        subject = arg.getInt("subject");
        isGetAllCollect = arg.getBoolean("isGetAllCollect");
        page = 1;
        mData = null;
        mDialog = new IOSAlertDialog(getActivity()).builder();
        mHttpHelper.getMyConllection(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                page,per_page,isGetAllCollect,subject);
        initSpringView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_collect;
    }

    @Override
    protected void initView(View view) {

    }

    /**删除课程
     * @param position
     */
    private void deleteSubject(int position) {
        mHttpHelper.delConllection(this,CommonUtils.getCurrentTimeStampInt(),
                UserManager.getInstance().getToken(),
                mData.getAppUserCollectionList().getData().get(position).getId());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_MY_CONLLECTION:
                MyColletcModel myColletcModel = (MyColletcModel) msg.obj;
                setData(myColletcModel);
                break;
                case Constants.REQUEST_DEL_CONLLECTION:
                    page = 1;
                    mData = null;
                    if (mAdapter != null){
                        mAdapter.removeData();
                    }
                    mHttpHelper.getMyConllection(MyCollectFragment.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                            page,per_page,isGetAllCollect,subject);
                    ToastUtil.showToast("删除成功");
                    break;
        }
    }

    private void setData(MyColletcModel myColletcModel) {
        if(myColletcModel.getData()!=null && myColletcModel.getData().getAppUserCollectionList()!=null && myColletcModel.getData().getAppUserCollectionList().getData().size()>0){
            page++;
        }
            //证明是第一次加载
            if (mData == null){
                mData = myColletcModel.getData();
                mAdapter = new RvAdapterMyCollect2(getActivity(),mData);
                mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRv.setAdapter(mAdapter);
                mRv.setNestedScrollingEnabled(false);
                mAdapter.setOnItemClickListener(new RvAdapterMyCollect2.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                        intent.putExtra("course_id",mData.getAppUserCollectionList().getData().get(position).getId());
                        getActivity().startActivity(intent);
                    }
                });
                mAdapter.setOnItemLongClickListener(new RvAdapterMyCollect2.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(int position) {
                        showDelDia(position);
                    }
                });
                //之前已经有数据了，将数据加进去，刷新即可
            }else {
                List<MyColletcModel.DatabeanX.AppUserCollectionListbean.Databean> data = mData.getAppUserCollectionList().getData();
                List<MyColletcModel.DatabeanX.AppUserCollectionListbean.Databean> newData = myColletcModel.getData().getAppUserCollectionList().getData();
                if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
                }
                data.addAll(newData);
                mAdapter.notifyDataSetChanged();
            }
            if (mData != null && mData.getAppUserCollectionList() != null && mData.getAppUserCollectionList().getData().size() > 0){
                mLlNoData.setVisibility(View.GONE);
                //设置layout_gravity为Top
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.TOP;
                mRlContent.setLayoutParams(lp);
                mFlContent.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_MY_CONLLECTION:
                break;
        }

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
                        if (mAdapter != null){
                            mAdapter.removeData();
                        }
                        mHttpHelper.getMyConllection(MyCollectFragment.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                page,per_page,isGetAllCollect,subject);

                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getMyConllection(MyCollectFragment.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                page,per_page,isGetAllCollect,subject);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    private void showDelDia(int position){
        mDialog.setGone().setMsg("是否删除本课程")
                .setNegativeButton("取消", R.color.black, null)
                .setPositiveButton("确定", R.color.action_sheet_blue, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteSubject(position);
                    }
                }).show();
    }
}
