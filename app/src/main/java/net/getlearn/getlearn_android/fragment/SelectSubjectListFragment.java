package net.getlearn.getlearn_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.SyncSubjectActivity;
import net.getlearn.getlearn_android.activity.VideoPlayActivity;
import net.getlearn.getlearn_android.adapter.RvAdapterSelectSubject;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.SearchResultModel;
import net.getlearn.getlearn_android.model.bean.SelectSubjectListModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/26------更新------
 * 选课fragment中的课程列表fragment
 */

public class SelectSubjectListFragment extends BaseFragment implements RvAdapterSelectSubject.OnItemClickListener,BaseHelper.IHttpCallback {
    private LinearLayout llNotData;
    private RecyclerView mRv;
    private int subject_id;
    private int grade_id;
    private List<SelectSubjectListModel.Databean> mData;
    private List<SearchResultModel> mDataInner;
    private RvAdapterSelectSubject mAdapter;
    private SpringView springView;
    private boolean isLoadedData;//是否已经加载过数据
    public static SelectSubjectListFragment newInstance(int subject_id,int grade_id) {
        SelectSubjectListFragment f = new SelectSubjectListFragment();
        Bundle b = new Bundle();
        b.putInt("subject_id", subject_id);
        b.putInt("grade_id", grade_id);
        f.setArguments(b);
        return f;
    }


    @Override
    protected void initData() {
        LogUtil.e("__setGradeId_initData","grade_id_1:" +grade_id + "");
        LogUtil.e("__setGradeId_initData","subject_id_1:" +subject_id + "");
        Bundle args = getArguments();
        if (args != null && grade_id == 0) {//避免Tablayout滑动过程重新去拿之前的grade_id
            subject_id = args.getInt("subject_id");
            grade_id = args.getInt("grade_id");
            LogUtil.e("__setGradeId_initData","grade_id_2:" +grade_id + "");
            LogUtil.e("__setGradeId_initData","subject_id_2:" + subject_id);
            }
        LogUtil.e("__SelectSubjectListFragment","initData");
        mHttpHelper.getSelectSubjectList(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),grade_id,0,0,subject_id);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recommend_course;
    }

    public void setGradeId(int gradeId,int subjectId){
        grade_id = gradeId;
        subject_id = subjectId;
        LogUtil.e("__setGradeId",grade_id + "");
        LogUtil.e("__setGradeId","subject_id:" + subject_id);
        mHttpHelper.getSelectSubjectList(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),grade_id,0,0,subject_id);
    }
    @Override
    protected void initView(View view) {
        llNotData = view.findViewById(R.id.ll_not_data);
        mRv = view.findViewById(R.id.rv);
//        mRv.setNestedScrollingEnabled(false);
        initSpringView(view);
    }
    private void initSpringView(View view) {
        springView = view.findViewById(R.id.spring_view);
        springView.setHeader(new DefaultHeader(getActivity()));
//        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {

            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getSelectSubjectList(SelectSubjectListFragment.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),grade_id,0,0,subject_id);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onClick(View view) {

    }
    public void setData(){
        if(llNotData != null && mData != null ||mData.size() != 0){
            llNotData.setVisibility(View.GONE);
        }else {
            llNotData.setVisibility(View.VISIBLE);
        }
        mAdapter = new RvAdapterSelectSubject(getActivity(),mData);
        mRv.setAdapter(mAdapter);
        mRv.setHasFixedSize(true);
        mRv.setNestedScrollingEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setSmoothScrollbarEnabled(true);
//        manager.setAutoMeasureEnabled(true);
        mRv.setLayoutManager(manager);
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onInnerItemClick(int position, int positionInner) {
        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
        intent.putExtra("classify_id",mData.get(position).getResCategoryId());
        intent.putExtra("course_id",mData.get(position).getClassify().get(positionInner).getId());
        intent.putExtra("subject_id",subject_id);
        getActivity().startActivity(intent);
        LogUtil.e("__InnerItem","外部：" + position + "内部：" + positionInner);
    }

    @Override
    public void onItemMoreClick(int position) {
        Intent intent = new Intent(getActivity(), SyncSubjectActivity.class);
        intent.putExtra("classify_id",mData.get(position).getResCategoryId());
        intent.putExtra("subject_id",subject_id);
        intent.putExtra("grade_id",grade_id);

        intent.putExtra("classify_name",mData.get(position).getCategName());
        getActivity().startActivity(intent);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_SELECT_SUBJECT_LIST:
                SelectSubjectListModel selectSubjectListModel = (SelectSubjectListModel) msg.obj;
                mData = selectSubjectListModel.getData();
                setData();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        if(llNotData != null){
            llNotData.setVisibility(View.VISIBLE);
        }
    }
}
