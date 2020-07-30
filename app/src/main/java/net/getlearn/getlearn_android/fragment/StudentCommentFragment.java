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
import net.getlearn.getlearn_android.adapter.RvAdapterStudentComment;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.StudentCommentModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.view.emoji.SmileyParser;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/30------更新------
 * 学员评论fragment
 */

public class StudentCommentFragment extends BaseFragment implements BaseHelper.IHttpCallback {


    public static SubjectListFragment getInstanace(int course_id){
        SubjectListFragment fm = new SubjectListFragment();
        Bundle b = new Bundle();
        b.putInt("course_id", course_id);
        fm.setArguments(b);
        return fm;
    }
    private int course_id;
    private ImageView mTvNoData;
    private RecyclerView mRv;
    private RvAdapterStudentComment mAdapter;
    private StudentCommentModel.DatabeanX mData;
    private SpringView springView;
    private int page;
    private SmileyParser mParser;
    @Override
    protected void initData() {
        page = 1;
        Bundle args = getArguments();
        if (args != null) {
            course_id = args.getInt("course_id");
        }
        mHttpHelper.getStudentComment(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),course_id,page);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_student_comment;
    }

    public void setParser(SmileyParser parser) {
        mParser = parser;
    }

    @Override
    protected void initView(View view) {
        mRv = view.findViewById(R.id.rv);
        mTvNoData = view.findViewById(R.id.iv_no_data);
        initSpringView(view);
    }

    /**
     * 重新获取学员评论，正常是在提交评论成功之后
     */
    public void reGetStudentComment(){

        page = 1;
        mData = null;
        if (mAdapter != null){
            mAdapter.removeData();
        }
        mHttpHelper.getStudentComment(StudentCommentFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),course_id,page);

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
                        mData = null;
                        if (mAdapter != null){
                         mAdapter.removeData();
                        }
                        mHttpHelper.getStudentComment(StudentCommentFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),course_id,page);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getStudentComment(StudentCommentFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),course_id,page);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    public void setData(StudentCommentModel.DatabeanX databeanX){
        if (mData == null){
            mData = databeanX;
            mAdapter = new RvAdapterStudentComment(getActivity(),mData);
            mAdapter.setParser(mParser);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRv.setLayoutManager(manager);
            mRv.setAdapter(mAdapter);
        }else {
            List<StudentCommentModel.DatabeanX.Databean> data = mData.getData();
            List<StudentCommentModel.DatabeanX.Databean> newData = databeanX.getData();
            if (mData!=null && newData == null || newData.size() == 0){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }
        page ++;
    }

    @Override
    public void onClick(View view) {

    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_STUDENT_COMMENT:
                StudentCommentModel model = (StudentCommentModel) msg.obj;
                StudentCommentModel.DatabeanX databeanX = model.getData();
                setData(databeanX);
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
}
