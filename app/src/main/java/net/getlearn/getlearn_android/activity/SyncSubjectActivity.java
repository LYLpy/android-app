package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.TabPagerAdapterList;
import net.getlearn.getlearn_android.fragment.SyncSubjectListFragment;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.SubjectModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/25------更新------
 * 同步课
 */

public class SyncSubjectActivity extends BaseActivity implements BaseHelper.IHttpCallback {


    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

//    private int course_id;
//    private int subject_id;
//    private int version_id;
    private int classify_id;
    private String classify_name = "";
    private int grade_id;
    private SyncSubjectListFragment fm1;
    private SyncSubjectListFragment fm2;
    private SyncSubjectListFragment fm3;
    private List<String> mTitlesList;
    private List<SubjectModel.Databean> mSubjectModelDataList;
    private List<Fragment> fragments;
    @Override
    protected void initData() {
        Intent intent = getIntent();
        classify_id = intent.getIntExtra("classify_id",0);
        classify_name = intent.getStringExtra("classify_name");
        grade_id = intent.getIntExtra("grade_id",UserManager.getInstance().getGradeId());
//        subject_id = intent.getIntExtra("subject_id",0);
//        version_id = intent.getIntExtra("version_id",0);
//        course_id = intent.getIntExtra("course_id",0);
        if (classify_name != null && !classify_name.equals("")){
            mTvTitle.setText(classify_name);
        }else {
            mTvTitle.setText("同步课");
        }
        if (classify_id != 0){
            mHttpHelper.getSubjectByClassify(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),classify_id);
        }else {
            mHttpHelper.getSubject(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sync_course;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_back:
                finish();
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_SUBJECT:
                SubjectModel subjectModel = (SubjectModel) msg.obj;
                mSubjectModelDataList = subjectModel.getData();
                setSubjectData();
        }

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
    /**
     * 设置科目
     */
    private void setSubjectData() {
        mTitlesList = new ArrayList<>();
        fragments = new ArrayList<>();
        try {
            for (int i = 0; i < mSubjectModelDataList.size(); i++) {
                mTitlesList.add(mSubjectModelDataList.get(i).getSubject());
                fragments.add(SyncSubjectListFragment.newInstance(classify_id,mSubjectModelDataList.get(i).getSubject_id(),grade_id));
            }
//            fm1 = SyncSubjectListFragment.newInstance(classify_id,mSubjectModelDataList.get(0).getSubject_id(),grade_id);
//            fm2 = SyncSubjectListFragment.newInstance(classify_id,mSubjectModelDataList.get(1).getSubject_id(),grade_id);
//            fm3 = SyncSubjectListFragment.newInstance(classify_id,mSubjectModelDataList.get(2).getSubject_id(),grade_id);

            TabPagerAdapterList adapter = new TabPagerAdapterList(getSupportFragmentManager(), fragments,mTitlesList);
            mViewPager.setAdapter(adapter);
            //2.绑定ViewPager和TabLayout
            mTablayout.setupWithViewPager(mViewPager);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.e("__Exception",e.getMessage());
        }
    }
}
