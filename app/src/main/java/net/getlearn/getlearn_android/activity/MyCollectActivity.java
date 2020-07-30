package net.getlearn.getlearn_android.activity;

import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.TabPagerAdapterList;
import net.getlearn.getlearn_android.fragment.MyCollectFragment;
import net.getlearn.getlearn_android.model.bean.SubjectModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/13------更新------
 * 我的收藏
 */

public class MyCollectActivity extends BaseActivity {


    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_explain)
    ImageView mIvExplain;
    @BindView(R.id.toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private List<Fragment> fragments;
    private MyCollectFragment fm1;
    private MyCollectFragment fm2;
    private MyCollectFragment fm3;
    private MyCollectFragment fm4;
    private String[] titles;
    private List<String> mTitlesList;
    private List<SubjectModel.Databean> mSubjectModelDataList;

    @Override
    protected void initData() {
        mHttpHelper.getSubject(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_collect;
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
        switch (reqType) {
            case Constants.REQUEST_GET_SUBJECT:
                SubjectModel subjectModel = (SubjectModel) msg.obj;
                mSubjectModelDataList = subjectModel.getData();
                setSubjectData();
                break;
        }
    }

    private void setSubjectData() {
        mTitlesList = new ArrayList<>();
        mTitlesList.add("全部");
        for (int i = 0; i < mSubjectModelDataList.size(); i++) {
            mTitlesList.add(mSubjectModelDataList.get(i).getSubject());
        }
        fm1 = MyCollectFragment.newInstance(mSubjectModelDataList.get(0).getSubject_id(), true);
        fm2 = MyCollectFragment.newInstance(mSubjectModelDataList.get(0).getSubject_id(), false);
        fm3 = MyCollectFragment.newInstance(mSubjectModelDataList.get(1).getSubject_id(), false);
        fm4 = MyCollectFragment.newInstance(mSubjectModelDataList.get(2).getSubject_id(), false);
        fragments = new ArrayList<>();
        fragments.add(fm1);
        fragments.add(fm2);
        fragments.add(fm3);
        fragments.add(fm4);
        TabPagerAdapterList adapter = new TabPagerAdapterList(getSupportFragmentManager(), fragments, mTitlesList);
        mViewpager.setAdapter(adapter);
        //2.绑定ViewPager和TabLayout
        mTablayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_GET_SUBJECT:
                ToastUtil.showToast("获取科目失败，请重试");
                break;
        }
    }

}
