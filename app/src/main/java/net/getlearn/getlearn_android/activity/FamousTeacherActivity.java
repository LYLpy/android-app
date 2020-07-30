package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterFamousTeacher;
import net.getlearn.getlearn_android.adapter.RvAdapterMainPopup;
import net.getlearn.getlearn_android.model.bean.FamousTeacherModel;
import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.SelectGradeDialog;
import net.getlearn.getlearn_android.view.SelectGradePopup;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * 名师专栏
 */

public class FamousTeacherActivity extends BaseActivity implements RvAdapterFamousTeacher.OnItemClickListener, RvAdapterMainPopup.OnPopupItemClickListener {

    @BindView(R.id.tv_search_course)
    TextView mTvSearchCourse;
    @BindView(R.id.tv_current_grade)
    TextView mTvCurrentGrade;
    @BindView(R.id.iv_show_popup)
    ImageView mIvShowPopup;
    @BindView(R.id.toolbar)
    LinearLayout mToolbar;
    @BindView(R.id.ll_search)
    LinearLayout mllSearch;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.spring_view)
    SpringView springView;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;

    private RvAdapterFamousTeacher mRvAdapter;
    private FamousTeacherModel.DatabeanX mData;
    private int grade_id;
    private int limit = 20;
    private int page = 1;
    private SelectGradePopup mGradePop;
    private RecyclerView.LayoutManager mManager = new GridLayoutManager(this, 3);
    private List<GradeModel.Databean> mGradeModelList;
    private boolean isPopShowing = false;//popup是否是展示状态
    private SelectGradeDialog mGradeDialog;
    @Override
    protected void initData() {
        Intent intent = getIntent();
        LogUtil.e("__grade_sp","" + UserManager.getInstance().getGradeId());
        grade_id = intent.getIntExtra("grade_id",UserManager.getInstance().getGradeId());
        mGradeModelList = SPUtil.getGradeList();
        if (mGradeModelList.size() == 0) {
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        } else {
            if (getCurrentGradeModel() != null && getCurrentGradeModel().getOption() != null) {
                mTvCurrentGrade.setText(getCurrentGradeModel().getOption());
            }
            initPopup();
        }
        mHttpHelper.getFamousTeacher(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                grade_id,limit,page);
    }
    //根据年级ID，获取年级实体对象
    private GradeModel.Databean getCurrentGradeModel() {
        GradeModel.Databean gradeModel = null;
        for (GradeModel.Databean model : mGradeModelList) {
            if (model != null && model.getId() == grade_id) {
                LogUtil.e("__grade_id",grade_id + "");
                LogUtil.e("__grade_option",model.getOption() + "");
                gradeModel = model;
            }
        }
        return gradeModel;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_famous_teacher;
    }

    @Override
    protected void initView() {
        mTvCurrentGrade.setOnClickListener(this);
        mIvShowPopup.setOnClickListener(this);
        mllSearch.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
        initSpringView();
    }
    private void initPopup() {
        mGradePop = new SelectGradePopup(this, mGradeModelList, mManager);
        for (int i = 0; i < mGradeModelList.size(); i++) {
            try {
                GradeModel.Databean databean = mGradeModelList.get(i);
                if (databean.getId() == grade_id) {
                    mTvCurrentGrade.setText(databean.getOption());
                    mGradePop.setSelection(i);
                }
            } catch (Exception e) {
                e.getStackTrace();
                if(mGradePop != null){
                    //如发生错误，默认选中第一个
                    mGradePop.setSelection(0);
                }
            }
        }
        mGradePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isPopShowing = false;
            }
        });
        mGradePop.setOnPopupItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.iv_show_popup:
            case R.id.tv_current_grade:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
//                showPopup();
                showSelectGradeDia();
                break;
            case R.id.toolbar:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
                break;
            case R.id.ll_search:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
                intent = new Intent(this,SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fl_back:
                finish();
                break;
        }
    }
    private void initSpringView() {
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mData = null;
                        if (mRvAdapter!=null){
                            mRvAdapter.removeData();
                        }
                        mHttpHelper.getFamousTeacher(FamousTeacherActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                grade_id,limit,page);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getFamousTeacher(FamousTeacherActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                grade_id,limit,page);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onChildItemClick(int position, int positionChild) {
        Intent intent = new Intent(this, VideoPlayActivity.class);
        intent.putExtra("course_id",mData.getData().get(position).getCourse().get(positionChild).getCourse_id());
        startActivity(intent);
        LogUtil.e("__onChildItemClick" ,"position:" + position + "," + "positionChild:" + positionChild);
    }

    @Override
    public void onItemClick(int position, View view) {
        Intent intent = new Intent(this, FamousTeacherIntroductionActivity.class);
        intent.putExtra("teacher_id",mData.getData().get(position).getId());
        startActivity(intent);
        LogUtil.e("__onChildItemClick" ,"position:" + position);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_FAMOUS_TEACHER:
                FamousTeacherModel famousTeacherModel = (FamousTeacherModel) msg.obj;
                setData(famousTeacherModel.getData());
                break;
            case Constants.REQUEST_GET_GRADE:
                GradeModel model = (GradeModel) msg.obj;
                //保存到SP中
                SPUtil.saveGradeList(model.getData());
                mGradeModelList = SPUtil.getGradeList();
                if (getCurrentGradeModel() != null && getCurrentGradeModel().getOption() != null) {
                    mTvCurrentGrade.setText(getCurrentGradeModel().getOption());
                }
                initPopup();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_FAMOUS_TEACHER:
                ToastUtil.showToast("获取数据失败");
                break;
        }
    }
    private  void setData(FamousTeacherModel.DatabeanX databeanX){
        if (databeanX.getData()!=null && databeanX.getData().size() > 0){
            page++;
        }
        if (mData == null){
            mData = databeanX;
            mRvAdapter = new RvAdapterFamousTeacher(this,mData);
            mRvAdapter.setItemClickListener(this);
            mRv.setAdapter(mRvAdapter);
            mRv.setLayoutManager(new LinearLayoutManager(this));
        }else {
                List<FamousTeacherModel.DatabeanX.Databean> data = mData.getData();
                List<FamousTeacherModel.DatabeanX.Databean> newData = databeanX.getData();
                if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
                }
                data.addAll(newData);
//            mRv.setItemAnimator(null);//避免刷新数据之后失去焦点无法点击
            mRvAdapter.notifyDataSetChanged();
            }
//            if (mDataMyStudy != null && mDataMyStudy.getData() != null && mDataMyStudy.getData().size() > 0){
//            mIvNoData.setVisibility(View.GONE);
//            }

    }
    private void showPopup() {
        isPopShowing = true;
        mGradePop.showAsDropDown(mToolbar);
    }
    @Override
    public void onPopupItemClick(int position) {
            mGradePop.setSelection(position);
            grade_id = mGradeModelList.get(position).getId();
            mTvCurrentGrade.setText(mGradeModelList.get(position).getOption());
            if (mGradePop.isShowing()) {
                mGradePop.dismiss();
            }
        page = 1;
        mData = null;
        if (mRvAdapter != null){
            mRvAdapter.cleanData();
        }
        mHttpHelper.getFamousTeacher(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                grade_id,limit,page);
    }

    @Override
    public void onBackPressed() {
        if (isPopShowing){
            mGradePop.dismiss();
        }else {
            super.onBackPressed();
        }
    }
    private void showSelectGradeDia(){
        if (mGradeModelList.size() == 0){
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
            ToastUtil.showToast("获取年级列表失败，请重试");
            return;
        }
        mGradeDialog  = new SelectGradeDialog(this,mGradeModelList).builder();
        mGradeDialog.setOnDiaItemClickListener(new SelectGradeDialog.OnDiaItemClickListener() {
            @Override
            public void onDiaItemClick(int position, int id) {
                grade_id = id;
                LogUtil.e("__onPopupItemClick_grade_id", grade_id + "");
                mTvCurrentGrade.setText(mGradeModelList.get(position).getOption());
                if (mGradePop.isShowing()) {
                    mGradePop.dismiss();
                }
                page = 1;
                mData = null;
                if (mRvAdapter != null){
                    mRvAdapter.cleanData();
                }
                mHttpHelper.getFamousTeacher(FamousTeacherActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                        grade_id,limit,page);
                }
        });
        for (int i = 0; i < mGradeModelList.size(); i++) {
            try {
                GradeModel.Databean databean = mGradeModelList.get(i);
                if (databean.getId() == grade_id) {
                    mGradeDialog.setSelection(i);
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        mGradeDialog.show();
    }
}
