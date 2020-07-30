package net.getlearn.getlearn_android.fragment;

import android.content.ContentUris;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonIntengEvent;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.BaseActivity;
import net.getlearn.getlearn_android.activity.FamousTeacherIntroductionActivity;
import net.getlearn.getlearn_android.activity.MainActivity;
import net.getlearn.getlearn_android.activity.UserInfoActivity;
import net.getlearn.getlearn_android.activity.VideoPlayActivity;
import net.getlearn.getlearn_android.activity.VipCenterActivity;
import net.getlearn.getlearn_android.adapter.RvAdapterSubjectIntroduce;
import net.getlearn.getlearn_android.model.BaseHelper.IHttpCallback;
import net.getlearn.getlearn_android.model.bean.SubjectIntroduceModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/30------更新------
 * 课程介绍fragment
 */

public class SubjectIntroduceFragment extends BaseFragment implements IHttpCallback {
    public int SubjectNull;
    private IOSAlertDialog mIOSAlertDialog;
    public static SubjectIntroduceFragment getInstanace(int subject_id,int classify_id,int version_id,int course_id,int grade_id,int type){
        SubjectIntroduceFragment fm = new SubjectIntroduceFragment();

        Bundle b = new Bundle();
        b.putInt("subject_id", subject_id);
        b.putInt("classify_id", classify_id);
        b.putInt("version_id", version_id);
        b.putInt("course_id", course_id);
        b.putInt("grade_id", grade_id);
        LogUtil.e("__json7fm接收",b+"");
        fm.setArguments(b);
        fm.type = type;
        return fm;
    }

    private int type;//区分是同步课程还是推荐课程
    private int subject_id;
    private int course_id;
    private int classify_id;
    private int version_id;
    private int grade_id;
    private String TAG="__json"+ Constants.REQUEST_GET_SUBJECT_INTRODUCE;
    private ImageView mIvNoData;
    private RecyclerView mRv;
    private RvAdapterSubjectIntroduce mAdapter;
    private SubjectIntroduceModel mSubjectIntroduceModel;
    private SpringView springView;


    @Override
    protected void initData() {
        Bundle bundle = SubjectIntroduceFragment.this.getArguments();
        bundle.getInt("subject_id");
        bundle.getInt("classify_id");
        bundle.getInt("version_id");
        bundle.getInt("course_id");
        bundle.getInt("grade_id");
        LogUtil.e("__json7bundle",bundle+"");
        if (bundle != null) {
            subject_id = bundle.getInt("subject_id");
            classify_id = bundle.getInt("classify_id");
            version_id = bundle.getInt("version_id");
            course_id = bundle.getInt("course_id");
            grade_id = bundle.getInt("grade_id", UserManager.getInstance().getGradeId());
            mHttpHelper.getSubjectIntroduce(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, classify_id, version_id, subject_id, course_id + "");
        }else {
            LogUtil.e("__json7接收bundle为空");
            mHttpHelper.getSubjectIntroduce(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, classify_id, version_id, subject_id, course_id + "");
        }
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_subject_introduce;
    }

    @Override
    protected void initView(View view) {
        mRv = view.findViewById(R.id.rv_data);
        mIvNoData = view.findViewById(R.id.iv_no_data);
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
                        mHttpHelper.getSubjectIntroduce(SubjectIntroduceFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, classify_id, version_id, subject_id, course_id + "");
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

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){

            case Constants.REQUEST_GET_SUBJECT_INTRODUCE:

                SubjectIntroduceModel mode1 = (SubjectIntroduceModel) msg.obj;
                if (mSubjectIntroduceModel == null){
                    mSubjectIntroduceModel = mode1;
                }
                if (mOnCourseIdGetted != null){
                    if (mSubjectIntroduceModel.getData().getCourse_introduce()== null||mSubjectIntroduceModel.getData().getCourse_introduce().equals("")){
                        SubjectNull=0;
                        LogUtil.e("getCourse_introduce()",mSubjectIntroduceModel.getData().getCourse_introduce()+"");
                        mOnCourseIdGetted.onCourseIdGetted(404);//如果是空的给他404
                    }else {
                        SubjectNull=1;
                        mOnCourseIdGetted.onCourseIdGetted(mSubjectIntroduceModel.getData().getCourse_introduce().getId());

                    }
                    LogUtil.e("SubjectNull",SubjectNull+"");
                }
                setData();
                break;
            default:
                break;
        }
    }

    private void setData() {
        if (mOnDataGettedListener != null) {
            try {
                mOnDataGettedListener.onDataGetted(mSubjectIntroduceModel.getData());
            }catch (Exception e){
                LogUtil.e(TAG,e+"");
            }
        }

        LogUtil.e(TAG,"进入setData1");
        if (mSubjectIntroduceModel.getData().getCourse_introduce()==null||mSubjectIntroduceModel.getData().getCourse_introduce().equals("")||mSubjectIntroduceModel.getData().getCourse_introduce().equals(null) ){
            LogUtil.e(TAG,mSubjectIntroduceModel.getData().getCourse_introduce()+"");
        }

        if (mSubjectIntroduceModel.getData().getFree_list() != null && mSubjectIntroduceModel.getData().getFree_list().size() > 0) {
            mIvNoData.setVisibility(View.GONE);
        }
        try {
            LogUtil.e(TAG,"进入try");
            mAdapter = new RvAdapterSubjectIntroduce(getActivity(), mSubjectIntroduceModel);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            mRv.setLayoutManager(manager);
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new RvAdapterSubjectIntroduce.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(mSubjectIntroduceModel.getData().getFree_list().get(position), position);
                    }
                }
            });
            mAdapter.setOnVipAdvertClickListener(new RvAdapterSubjectIntroduce.OnVipAdvertClickListener() {
                @Override
                public void onVipAdvertClick() {
                    LogUtil.e(TAG, "fragment");
                    Intent intent = new Intent(getActivity(), VipCenterActivity.class);
                    getActivity().startActivity(intent);
                }
            });
            mAdapter.setOnTeacherItemClickListener(new RvAdapterSubjectIntroduce.OnTeacheItemrClickListener() {
                @Override
                public void onTeacherItemClick(int position) {
                    try {
                        LogUtil.e(TAG,"__onTeacherItemClick"+position + "");
                        Intent intent = new Intent(getActivity(), FamousTeacherIntroductionActivity.class);
                        int getTeacher_id = mSubjectIntroduceModel.getData().getTeacher().get(position).getTeacher_id();
                        intent.putExtra("teacher_id", getTeacher_id);
                        LogUtil.e("getTeacher_id()",getTeacher_id+"");
                        startActivity(intent);
                    } catch (Exception e) {
                        ToastUtil.showToast("获取教师数据失败");
                        LogUtil.e(TAG,"__onTeacherItemClick"+"获取教师数据失败:"+e);
                    }
                }
            });
        } catch (Exception e) {
            LogUtil.e("__Exception_getStackTrace_1", e.getStackTrace().toString());
            LogUtil.e("__Exception_getMessage_1", e.getMessage());
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        mIOSAlertDialog = new IOSAlertDialog(getContext()).builder();
        mIOSAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
        mIOSAlertDialog.setMsg("当前年级,该版本下暂无课程!").setTitle("", R.color.black).setNegativeButton("返回首页", R.color.bg_gray5, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), MainActivity.class);
                startActivity(intent1);
                getActivity().finish();
            }
        }).setPositiveButton("更换版本", R.color.btnblue, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UserInfoActivity.class));
                getActivity().finish();
            }
        }).show();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        mItemClickListener = itemClickListener;
    }
    private OnItemClickListener mItemClickListener;

    /**
     * 点击课程回调
     */
    public interface OnItemClickListener{
        /**
         * @param freeListbean 免费课程对象
         * @param position 点击的位置
         */
        void onItemClick(SubjectIntroduceModel.Databean.FreeListbean freeListbean,int position);
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


    public void setOnDataGettedListener(OnDataGettedListener onDataGettedListener) {
        mOnDataGettedListener = onDataGettedListener;
    }


    private OnDataGettedListener mOnDataGettedListener;
    /**
     * 取得课程数据时
     */
    public interface OnDataGettedListener{
        void onDataGetted(SubjectIntroduceModel.Databean data);
    }


    public interface NoNull{

    }
}
