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
import net.getlearn.getlearn_android.adapter.RvAdapterMoreClass;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.MoreClassModel;
import net.getlearn.getlearn_android.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/26------更新------
 * 同步课列表fragment
 */

public class SyncSubjectListFragment extends BaseFragment implements BaseHelper.IHttpCallback {
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;
//    @BindView(R.id.fl_content)
//    FrameLayout mFlContent;
    private LinearLayout llNotData;
    private RecyclerView mRv;
    private RvAdapterMoreClass mAdapter;
    private int classify_id;
    private int subject_id;
    private int grade_id;
    private SpringView mSpringView;
    private MoreClassModel.DatabeanX mMoreClass;
    private int page = 1;
    private boolean isLoadedData;//是否已经加载过数据
    public static SyncSubjectListFragment newInstance(int classify_id, int subject_id,int grade_id) {
        SyncSubjectListFragment f = new SyncSubjectListFragment();
        Bundle b = new Bundle();
        b.putInt("classify_id", classify_id);
        b.putInt("subject_id", subject_id);
        b.putInt("grade_id", grade_id);
        f.setArguments(b);
        return f;
    }

    @Override
    protected void initData() {
        page = 1;
        mMoreClass = null;
        Bundle args = getArguments();
        if (args != null && !isLoadedData) {
            classify_id = args.getInt("classify_id");
            subject_id = args.getInt("subject_id");
            grade_id = args.getInt("grade_id");
            isLoadedData = true;
        }
        mHttpHelper.getMoreClass(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),grade_id,classify_id,subject_id,page,20);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_sync_subject;
    }

    @Override
    protected void initView(View view) {
        llNotData = view.findViewById(R.id.ll_not_data);
        mRv = view.findViewById(R.id.rv_data);
        initSpringView(view);
    }

    private void initSpringView(View view) {
        mSpringView = view.findViewById(R.id.spring_view);
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
                        mMoreClass = null;
                        if (mAdapter!=null){
                            mAdapter.removeData();
                        }
                        mSpringView.onFinishFreshAndLoad();
                        mHttpHelper.getMoreClass(SyncSubjectListFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),grade_id,classify_id,subject_id,page,20);
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getMoreClass(SyncSubjectListFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),grade_id,classify_id,subject_id,page,20);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onClick(View view) {

    }



    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_MORE_CLASS:
                MoreClassModel moreClassModel = (MoreClassModel) msg.obj;
                setData(moreClassModel.getData());
                mAdapter.notifyDataSetChanged();
                page ++;
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
//        llNotData.setVisibility(View.VISIBLE);
    }
    private void setData(MoreClassModel.DatabeanX moreClassModel){
        if (mMoreClass == null){
            mMoreClass = moreClassModel;
            mAdapter = new RvAdapterMoreClass(getActivity(),mMoreClass);
            mRv.setAdapter(mAdapter);
            mRv.setNestedScrollingEnabled(false);
            mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setClickListener(new RvAdapterMoreClass.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                    intent.putExtra("classify_id",classify_id);
                    intent.putExtra("subject_id",subject_id);
                    intent.putExtra("grade_id",grade_id);
                    intent.putExtra("course_id",mMoreClass.getData().get(position).getId());
                    getActivity().startActivity(intent);
                }
            });
        }else {
            List<MoreClassModel.DatabeanX.Databean> data = mMoreClass.getData();
            List<MoreClassModel.DatabeanX.Databean> newData = moreClassModel.getData();
            if (newData == null || newData.size() == 0 && moreClassModel != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }
        if (mMoreClass.getData() != null && mMoreClass.getData().size() > 0){
            llNotData.setVisibility(View.GONE);
            //设置layout_gravity为Top
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.TOP;
            mRlContent.setLayoutParams(lp);
//            mFlContent.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        }
    }

}
