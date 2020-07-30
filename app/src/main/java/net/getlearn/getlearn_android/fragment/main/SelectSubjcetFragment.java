package net.getlearn.getlearn_android.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.SearchActivity;
import net.getlearn.getlearn_android.activity.SyncSubjectActivity;
import net.getlearn.getlearn_android.activity.VipCenterActivity;
import net.getlearn.getlearn_android.adapter.RvAdapterMainPopup;
import net.getlearn.getlearn_android.adapter.TabPagerAdapterList;
import net.getlearn.getlearn_android.fragment.BaseFragment;
import net.getlearn.getlearn_android.fragment.SelectSubjectListFragment;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.ClassifyModel;
import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.model.bean.SubjectModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.SelectGradeDialog;
import net.getlearn.getlearn_android.view.SelectGradePopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/13------更新------
 *选课
 */

public class SelectSubjcetFragment extends BaseFragment implements RvAdapterMainPopup.OnPopupItemClickListener, BaseHelper.IHttpCallback {
    Unbinder unbinder;
    private String TAG = "__Fragment2";
    @BindView(R.id.rl_title_bar)
    RelativeLayout mRlTitleBar;
    //顶部分类按钮1
    @BindView(R.id.rl_classify_1)
    RelativeLayout mRlClassify1;
    @BindView(R.id.iv_classify_1)
    ImageView mIvClassify1;
    @BindView(R.id.tv_classify_1)
    TextView mTvClassify1;
    //顶部分类按钮2
    @BindView(R.id.rl_classify_2)
    RelativeLayout mRlClassify2;
    @BindView(R.id.iv_classify_2)
    ImageView mIvClassify2;
    @BindView(R.id.tv_classify_2)
    TextView mTvClassify2;
    //顶部分类按钮3
    @BindView(R.id.rl_classify_3)
    RelativeLayout mRlClassify3;
    @BindView(R.id.iv_classify_3)
    ImageView mIvClassify3;
    @BindView(R.id.tv_classify_3)
    TextView mTvClassify3;

    @BindView(R.id.iv_show_popup)
    ImageView mIvShowPopUp;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_current_grade)
    TextView mTvCurrentGrade;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.tv_vip_advert)
    TextView mTvVipAdvert;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private SelectGradePopup mGradePop;
    private SelectSubjectListFragment fm1;
    private SelectSubjectListFragment fm2;
    private SelectSubjectListFragment fm3;
    private boolean isPopShowing = false;//popup是否是展示状态
    List<GradeModel.Databean> mGradeModelList;
    List<ClassifyModel.Databean> mClassifyDataList;
    List<SubjectModel.Databean> mSubjectModelDataList;
    private List<Fragment> fragments;
    private List<String> mTitlesList;

    private SelectGradeDialog mGradeDialog;
    private int grade_id;

    @Override
    protected void initData() {
//        grade_id = UserManager.getInstance().getGradeId();
        mHttpHelper.getClassify(this, CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
        mHttpHelper.getSubject(this, CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
        mGradeModelList = SPUtil.getGradeList();
        if (mGradeModelList.size() == 0) {
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        }
        if (fragments == null){
            //LogUtil.e("__SelectSubjcetFragment","onHiddenChanged:" + "请求数据");
            mHttpHelper.getClassify(this, CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
            mHttpHelper.getSubject(this, CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
        }
        grade_id = UserManager.getInstance().getGradeId();
        initPopup();

        Boolean judge = System.currentTimeMillis() > UserManager.getInstance().getVipTime();
        //判断VIP是否过期
        if (UserManager.getInstance().getIsVip()&&judge){
            mTvVipAdvert.setVisibility(View.GONE);
            Boolean isVip = UserManager.getInstance().getIsVip();
            LogUtil.e("__vip",isVip+"");
        } else {
                mTvVipAdvert.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_select_subject;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.e("__SelectSubjcetFragment","onHiddenChanged:" + hidden + "");
        if(hidden){//不在最前端界面显示，相当于调用了onPause()

        }else{//重新显示到最前端 ,相当于调用了onResume()

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (fragments == null){
            LogUtil.e("__SelectSubjcetFragment","onHiddenChanged:" + "请求数据");
            mHttpHelper.getClassify(this, CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
            mHttpHelper.getSubject(this, CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
        }
        grade_id = UserManager.getInstance().getGradeId();
        initPopup();
        Boolean judge = System.currentTimeMillis() > UserManager.getInstance().getVipTime();
        if (UserManager.getInstance().getIsVip()&&judge){
            mTvVipAdvert.setVisibility(View.GONE);
        } else{
            mTvVipAdvert.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initView(View view) {
        mRlClassify1.setOnClickListener(this);
        mRlClassify2.setOnClickListener(this);
        mRlClassify3.setOnClickListener(this);
        mTvCurrentGrade.setOnClickListener(this);
        mIvShowPopUp.setOnClickListener(this);
        mRlTitleBar.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);
        mTvVipAdvert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.iv_search:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
                intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_title_bar:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
                break;
            case R.id.iv_show_popup:
            case R.id.tv_current_grade:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
//                showPopup();
                showSelectGradeDia();
                break;
            case R.id.rl_classify_1:
                if (mClassifyDataList!=null){
                    intent = new Intent(getActivity(), SyncSubjectActivity.class);
                    intent.putExtra("classify_id",mClassifyDataList.get(0).getResCategoryId());
                    intent.putExtra("classify_name",mClassifyDataList.get(0).getCategName());
                    intent.putExtra("grade_id",grade_id);
                    startActivity(intent);
                }else {
                    ToastUtil.showToast("数据为空，请刷新重试");
                }
                break;
            case R.id.rl_classify_2:
                if (mClassifyDataList!=null){
                    intent = new Intent(getActivity(), SyncSubjectActivity.class);
                    intent.putExtra("classify_id",mClassifyDataList.get(1).getResCategoryId());
                    intent.putExtra("classify_name",mClassifyDataList.get(1).getCategName());
                    intent.putExtra("grade_id",grade_id);
                    startActivity(intent);
                }else {
                    ToastUtil.showToast("数据为空，请刷新重试");
                }
            break;
            case R.id.rl_classify_3:
                if (mClassifyDataList!=null){
                    intent = new Intent(getActivity(), SyncSubjectActivity.class);
                    intent.putExtra("classify_id",mClassifyDataList.get(2).getResCategoryId());
                    intent.putExtra("classify_name",mClassifyDataList.get(2).getCategName());
                    intent.putExtra("grade_id",grade_id);
                    startActivity(intent);
                }else {
                    ToastUtil.showToast("数据为空，请刷新重试");
                }
            break;
            //开通会员
            case R.id.tv_vip_advert:
                intent = new Intent(getActivity(), VipCenterActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }
    private void initPopup() {
        mGradePop = new SelectGradePopup(getActivity(), mGradeModelList, new GridLayoutManager(getActivity(), 3));
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
//                    mGradePop.setSelection(0);
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

    private void showPopup() {
        isPopShowing = true;
        mGradePop.showAsDropDown(mRlTitleBar);
    }

    @Override
    public void onPopupItemClick(int position) {
        mGradePop.setSelection(position);
        grade_id = mGradeModelList.get(position).getId();
        mTvCurrentGrade.setText(mGradeModelList.get(position).getOption());
        if (mGradePop.isShowing()) {
            mGradePop.dismiss();
        }
        if (mSubjectModelDataList!=null){
            fm1.setGradeId(grade_id,mSubjectModelDataList.get(0).getSubject_id());
            fm2.setGradeId(grade_id,mSubjectModelDataList.get(1).getSubject_id());
            fm3.setGradeId(grade_id,mSubjectModelDataList.get(2).getSubject_id());
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_CLASSIFY:
                ClassifyModel classifyModel = (ClassifyModel) msg.obj;
                mClassifyDataList = classifyModel.getData();
                setClassify();
                break;
            case Constants.REQUEST_GET_SUBJECT:
                SubjectModel subjectModel = (SubjectModel) msg.obj;
                mSubjectModelDataList = subjectModel.getData();
                setSubjectData();
                break;
            case Constants.REQUEST_EDIT_GRADE_AND_VERSION:
                //修改年级成功
                ToastUtil.showToast("修改年级成功");
                UserManager.getInstance().setGradeId(grade_id);
                //通知选课界面刷新年级，重新请求数据
                EventBus.getDefault().post(new CommonMessageEvent(Constants.REQUEST_EDIT_GRADE_AND_VERSION,""));
                break;
            case Constants.REQUEST_GET_GRADE:
                GradeModel model = (GradeModel) msg.obj;
                //保存到SP中
                SPUtil.saveGradeList(model.getData());
                mGradeModelList = SPUtil.getGradeList();
                setCurrentGrade();
                break;
        }
    }

    /**
     * 设置科目
     */
    private void setSubjectData() {
        mTitlesList = new ArrayList<>();
        try {
            for (int i = 0; i < mSubjectModelDataList.size(); i++) {
                mTitlesList.add(mSubjectModelDataList.get(i).getSubject());
            }
            fm1 = SelectSubjectListFragment.newInstance(mSubjectModelDataList.get(0).getSubject_id(),grade_id);
            fm2 = SelectSubjectListFragment.newInstance(mSubjectModelDataList.get(1).getSubject_id(),grade_id);
            fm3 = SelectSubjectListFragment.newInstance(mSubjectModelDataList.get(2).getSubject_id(),grade_id);
            fragments = new ArrayList<>();
            fragments.add(fm1);
            fragments.add(fm2);
            fragments.add(fm3);
            TabPagerAdapterList adapter = new TabPagerAdapterList(getChildFragmentManager(), fragments,mTitlesList);
            mViewPager.setAdapter(adapter);
            //2.绑定ViewPager和TabLayout
            mTablayout.setupWithViewPager(mViewPager);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.e("__Exception",e.getMessage());
        }
    }

    /**
     * 设置分类
     */
    private void setClassify() {
        try {
            mTvClassify1.setText(mClassifyDataList.get(0).getCategName());
            mTvClassify2.setText(mClassifyDataList.get(1).getCategName());
            mTvClassify3.setText(mClassifyDataList.get(2).getCategName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_SAVE_SUBJECT_AND_VERSION:
                //修改年级失败
                ToastUtil.showToast("修改年级失败");
                break;
        }
    }
    public boolean onBackPressed(){
        if (isPopShowing){
            mGradePop.dismiss();
            return true;
        }else {

            return false;
        }
    }

    private void showSelectGradeDia(){
        if (mGradeModelList.size() == 0){
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
            ToastUtil.showToast("获取年级列表失败，请重试");
            return;
        }
        mGradeDialog  = new SelectGradeDialog(getActivity(),mGradeModelList).builder();
        mGradeDialog.setOnDiaItemClickListener(new SelectGradeDialog.OnDiaItemClickListener() {
            @Override
            public void onDiaItemClick(int position, int id) {
                grade_id = id;
                LogUtil.e("__onPopupItemClick_grade_id", grade_id + "");
                mTvCurrentGrade.setText(mGradeModelList.get(position).getOption());
                if (mSubjectModelDataList != null){
                    fm1.setGradeId(grade_id,mSubjectModelDataList.get(0).getSubject_id());
                    fm2.setGradeId(grade_id,mSubjectModelDataList.get(1).getSubject_id());
                    fm3.setGradeId(grade_id,mSubjectModelDataList.get(2).getSubject_id());
                }
                //发送到后台修改年级
                mHttpHelper.editSelectGradeAndVersion(SelectSubjcetFragment.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),String.valueOf(grade_id),"");
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
    //EventBus接收修改年级成功消息，刷新界面
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
        if (mMessageEvent.getType() == Constants.REQUEST_EDIT_GRADE_AND_VERSION) {
            grade_id = UserManager.getInstance().getGradeId();
            if (fm1!=null && mSubjectModelDataList!=null){
                fm1.setGradeId(grade_id,mSubjectModelDataList.get(0).getSubject_id());
            }
            if (fm2!=null&& mSubjectModelDataList!=null){
                fm2.setGradeId(grade_id,mSubjectModelDataList.get(1).getSubject_id());
            }
            if (fm3!=null&& mSubjectModelDataList!=null){
                fm3.setGradeId(grade_id,mSubjectModelDataList.get(2).getSubject_id());
            }
            setCurrentGrade();
        }
    }
    private void setCurrentGrade(){
        if (mGradeModelList.size() == 0) {
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        }else {
            for (int i = 0; i < mGradeModelList.size(); i++) {
                try {
                    GradeModel.Databean databean = mGradeModelList.get(i);
                    if (databean.getId() == grade_id) {
                        mTvCurrentGrade.setText(databean.getOption());
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                    if (mGradePop != null) {
                    }
                }
            }
        }
    }
}