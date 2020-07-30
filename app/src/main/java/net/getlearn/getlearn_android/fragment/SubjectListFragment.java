package net.getlearn.getlearn_android.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterSubjectList;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.SubjectListModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/30------更新------
 * 课程列表fragment
 */

public class SubjectListFragment extends BaseFragment implements BaseHelper.IHttpCallback {


    public static SubjectListFragment getInstanace(int subject_id,int classify_id,int version_id,int course_id,int grade_id){
        SubjectListFragment fm = new SubjectListFragment();
        Bundle b = new Bundle();
        b.putInt("subject_id", subject_id);
        b.putInt("classify_id", classify_id);
        b.putInt("version_id", version_id);
        b.putInt("course_id", course_id);
        b.putInt("grade_id", grade_id);
        fm.setArguments(b);
        return fm;
    }

    private int course_id;
    private int subject_id;
    private int classify_id;
    private int version_id;
    private int grade_id;
    private int limit = 20;
    private int page;
    private ImageView mIvNoData;
    private RecyclerView mRv;
    private RvAdapterSubjectList mAdapter;
    private SubjectListModel.DatabeanX mSubjectListModel;
    private LinearLayoutManager mLayoutManager;
    private SpringView springView;

    @Override
    protected void initData() {
        page = 1;
        mSubjectListModel = null;
        Bundle args = getArguments();
        if (args != null && subject_id == 0 && classify_id == 0 && version_id == 0 && course_id == 0 && grade_id == 0) {
                subject_id = args.getInt("subject_id");
                classify_id = args.getInt("classify_id");
                version_id = args.getInt("version_id");
                course_id = args.getInt("course_id");
                grade_id = args.getInt("grade_id",UserManager.getInstance().getGradeId());
                if (course_id != 0){
                    subject_id = 0;
                    classify_id= 0;
                    version_id = 0;
                    grade_id= 0;
                }
        }
//         course_id = subjectIntroduceModel.getData().getCourse_introduce().getId();
        LogUtil.e("__course_id_list",course_id + "");
        LogUtil.e("__subject_id_list",subject_id + "");
        if (course_id != 0){
//                    mHttpHelper.getSubjectList(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
//                grade_id,
//                classify_id,
//                version_id,
//                subject_id,
//                limit,
//                page,course_id + "");
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_subject_introduce;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initView(View view) {
        mRv = view.findViewById(R.id.rv_data);
        mIvNoData = view.findViewById(R.id.iv_no_data);
        initSpringView(view);
    }

    /**
     * 刷新适配器
     */
    public void reFreshAdapter(){
        if (mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initSpringView(View view) {
        springView = view.findViewById(R.id.spring_view);
        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mSubjectListModel = null;
                        if (mAdapter != null){
                            mAdapter.removeData();
                        }
                        mHttpHelper.getSubjectList(SubjectListFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                grade_id,
                                classify_id,
                                version_id,
                                subject_id,
                                limit,
                                page,course_id + "");
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getSubjectList(SubjectListFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                grade_id,
                                classify_id,
                                version_id,
                                subject_id,
                                limit,
                                page,course_id + "");
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
//        if (course_id != 0){
//            subject_id = 0;
//            classify_id= 0;
//            version_id = 0;
//            grade_id= 0;
//        }
        page = 1;
        mSubjectListModel = null;
        if (mAdapter != null){
            mAdapter.removeData();
        }
        LogUtil.e("__Course_id_fm2",course_id + "");
        mHttpHelper.getSubjectList(SubjectListFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                grade_id,
                classify_id,
                version_id,
                subject_id,
                limit,
                page,course_id + "");
    }


    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_SUBJECT_LIST:
                SubjectListModel model = (SubjectListModel) msg.obj;
//                LogUtil.e("__onIsCollectionGetted",model.getData().getGrade_info().getCollection() + "");
                //0没收藏，1为收藏
                if (onIsCollectionGetted != null){
                            onIsCollectionGetted.onIsCollectionGetted(model.getData().getGrade_info().getCollection());
                }
                if (mSubjectListModel == null){
                    mSubjectListModel = model.getData();
                }else {
                    List<SubjectListModel.DatabeanX.ClassListbean.Databean> data = mSubjectListModel.getClass_list().getData();
                    List<SubjectListModel.DatabeanX.ClassListbean.Databean> newData = model.getData().getClass_list().getData();
                    if (newData == null || newData.size() == 0){
//                        ToastUtil.showToast("没有更多了");

                    }
                    data.addAll(newData);
                }
                setData();
                mAdapter.notifyDataSetChanged();
                page ++;
//                setStudyKeyDataList();
                break;
                default:
                    break;
        }
    }
    @Override
    public void onHttpError(int reqType, String error) {

    }

    public void setData(){
        try {
            if (mSubjectListModel.getClass_list().getData() != null && mSubjectListModel.getClass_list().getData().size() > 0){
                mIvNoData.setVisibility(View.GONE);
                mAdapter = new RvAdapterSubjectList(getActivity(), mSubjectListModel);
                mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRv.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new RvAdapterSubjectList.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (clickListener != null){
                            clickListener.onItemClick(mSubjectListModel.getClass_list().getData().get(position),position);
                        }
                    }
                });
            }

        }catch (Exception e){

        }

    }

    /**
     * 点击监听
     */
    public interface OnItemClickListener{
        void onItemClick(SubjectListModel.DatabeanX.ClassListbean.Databean classInfo,int position);
    }
    private OnItemClickListener clickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }

    /**
     * 是否收藏
     */
    public interface OnIsCollectionGetted{
        /**
         * @param isCollection //0没收藏，1为收藏
         */
        void onIsCollectionGetted(int isCollection);
    }
    private OnIsCollectionGetted onIsCollectionGetted;
    public void setOnIsCollectionGettedListener(OnIsCollectionGetted listener){
        onIsCollectionGetted = listener;
    }

    public void setOnCourseIdGetted(OnCourseIdGetted onCourseIdGetted) {
        mOnCourseIdGetted = onCourseIdGetted;
    }

    private OnCourseIdGetted mOnCourseIdGetted;
    /**
     * 取得课程id回调
     */
    public interface OnCourseIdGetted{
        void onCourseIdGetted(int courseId);
    }
    @Override
    public void onClick(View view) {

    }
}
