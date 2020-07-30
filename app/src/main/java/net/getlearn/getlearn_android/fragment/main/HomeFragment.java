package net.getlearn.getlearn_android.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hitomi.cslibrary.CrazyShadow;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.ZoomOutPageTransformer;
import net.getlearn.getlearn_android.activity.AoePPTActivity;
import net.getlearn.getlearn_android.activity.FamousTeacherActivity;
import net.getlearn.getlearn_android.activity.FamousTeacherIntroductionActivity;
import net.getlearn.getlearn_android.activity.ReadActivity;
import net.getlearn.getlearn_android.activity.SearchActivity;
import net.getlearn.getlearn_android.activity.SelectGradeActivity;
import net.getlearn.getlearn_android.activity.StudyKeyActivity;
import net.getlearn.getlearn_android.activity.VideoPlayActivity;
import net.getlearn.getlearn_android.activity.VipCenterActivity;
import net.getlearn.getlearn_android.activity.webview.InvitationWebViewActivity;
import net.getlearn.getlearn_android.activity.webview.WebViewActivity;
import net.getlearn.getlearn_android.adapter.BannerAdapter;
import net.getlearn.getlearn_android.adapter.RvAdapterFamousTeacherHome2;
import net.getlearn.getlearn_android.adapter.RvAdapterMainPopup;
import net.getlearn.getlearn_android.adapter.RvAdapterRecommendSubjectHome2;
import net.getlearn.getlearn_android.adapter.RvAdapterStudyKeyHome2;
import net.getlearn.getlearn_android.fragment.BaseLazyFragment;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.BannerModel;
import net.getlearn.getlearn_android.model.bean.FamousTeacherModelHome;
import net.getlearn.getlearn_android.model.bean.GetActiveModel;
import net.getlearn.getlearn_android.model.bean.GetPersonalInfoModel;
import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.model.bean.RecommendSubjectHomeModel;
import net.getlearn.getlearn_android.model.bean.StudyKeyModelHome;
import net.getlearn.getlearn_android.model.bean.SyncSubjectHomeModel;
import net.getlearn.getlearn_android.model.bean.UserInfoModel;
import net.getlearn.getlearn_android.model.bean.UserSelectedVersionModel;
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

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/13------更新------
 */

public class HomeFragment extends BaseLazyFragment implements ViewPager.OnPageChangeListener, BaseHelper.IHttpCallback, RvAdapterMainPopup.OnPopupItemClickListener {


    @BindView(R.id.tv_current_grade)
    TextView mTvCurrentGrade;
    //    @BindView(R.id.tv_1_sync_subject)
//    TextView mTv1SyncSubject;
//    @BindView(R.id.tv_2_sync_subject)
//    TextView mTv2SyncSubject;
//    @BindView(R.id.tv_3_sync_subject)
//    TextView mTv3SyncSubject;
    @BindView(R.id.iv_1_sync_subject)
    ImageView mIv1SyncSubject;
    @BindView(R.id.iv_2_sync_subject)
    ImageView mIv2SyncSubject;
    @BindView(R.id.iv_3_sync_subject)
    ImageView mIv3SyncSubject;
    @BindView(R.id.ll_1_sync_subject)
    LinearLayout mLl1SyncSubject;
    @BindView(R.id.ll_2_sync_subject)
    LinearLayout mLl2SyncSubject;
    @BindView(R.id.ll_3_sync_subject)
    LinearLayout mLl3SyncSubject;
    @BindView(R.id.ll_more)
    LinearLayout mLlMoreStudyKey;
    @BindView(R.id.ll_more_famous_teacher)
    LinearLayout mLlMoreFamousTeacher;
    @BindView(R.id.ll_more_recommend_subject)
    LinearLayout mLlMoreRecommendSubjedt;
    @BindView(R.id.tv_more_study_key)
    TextView mTvMoreStudyKey;
    @BindView(R.id.tv_more_teacher)
    TextView mTvMoreTeacher;
    @BindView(R.id.tv_more_recommend)
    TextView mTvMoreRecommend;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_invitation)
    TextView mTvInvitation;//邀请好友
    @BindView(R.id.rl_main_title_bar)
    RelativeLayout mRlTitleBar;


    Unbinder unbinder;
    private SelectGradeDialog mGradeDialog;
    private SyncSubjectHomeModel.Databean mSyncSubject;
    private SpringView springView;
    private String TAG = "__Fragment1";
    private ArrayList<ImageView> imageViewList = new ArrayList<>();//要展示图片的ImageView
    private boolean isRunning = false;//是否正在轮播图片
    // 在values文件假下创建了pager_image_ids.xml文件，并定义了4张轮播图对应的id，用于点击事件
    private int[] imgae_ids = new int[]{R.id.pager_image1, R.id.pager_image2, R.id.pager_image3, R.id.pager_image4, R.id.pager_image5, R.id.pager_image6};
    //轮播图图片集合
    private int[] IMGIDS;
    private LinearLayout ll_point_container;//存放轮播图圆点指示器的LinearLayout
    private ViewPager viewPager;
    private int previousSelectedPosition = 0;//之前选择的圆点指示器
    private List<BannerModel.DataBean> mImgList;
    private RecyclerView mRvStudyKey;
    private RecyclerView mRvfamousTeacher;
    private RecyclerView mRvRecommendCourse;
    private SelectGradePopup mGradePop;
    private boolean isPopShowing = false;//popup是否是展示状态
    private ImageView ivShowPopup;
    private List<GradeModel.Databean> mGradeModelList;
    private UserSelectedVersionModel mSelectedVersion;
    private int grade_id = UserManager.getInstance().getGradeId();
    private RecommendSubjectHomeModel.DatabeanX mData;
    private StudyKeyModelHome mStudyKeyModelHome;
    private CrazyShadow crazyShadow;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();//手动加载数据，否则首页初次加载fragment时不会加载数据
        EventBus.getDefault().register(this);
        mHttpHelper.getSyncSubject(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
    }

    @Override
    protected void initData() {
        LogUtil.e("__Home_PackageName", MainApp.getContext().getPackageName());
        LogUtil.e("__Home_versionCode", MainApp.getInstance().getVersionCode() + "");
        LogUtil.e("__Home_versionName", MainApp.getInstance().getVersionName());
//        crazyShadow = new CrazyShadow.Builder()
//                .setContext(getActivity())
//                .setDirection(CrazyShadowDirection.BOTTOM)
//                .setShadowRadius(10)
//                .setBaseShadowColor(getResources().getColor(R.color.bg_gray3))
//                .setImpl(CrazyShadow.IMPL_DRAW)
//                .action(mRlTitleBar);

        mLl1SyncSubject.setOnClickListener(this);
        mLl2SyncSubject.setOnClickListener(this);
        mLl3SyncSubject.setOnClickListener(this);
        mTvMoreStudyKey.setOnClickListener(this);
        mTvMoreTeacher.setOnClickListener(this);
        mTvMoreRecommend.setOnClickListener(this);
        ivShowPopup.setOnClickListener(this);
        mRlTitleBar.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);
        mTvCurrentGrade.setOnClickListener(this);
        mTvInvitation.setOnClickListener(this);
        mLlMoreStudyKey.setOnClickListener(this);
        mLlMoreFamousTeacher.setOnClickListener(this);
        mLlMoreRecommendSubjedt.setOnClickListener(this);
        if (grade_id == 0){

        }
        if (mGradeModelList.size() == 0) {
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        } else {
            if (getCurrentGradeModel() != null && getCurrentGradeModel().getOption() != null) {
                mTvCurrentGrade.setText(getCurrentGradeModel().getOption());
                grade_id = getCurrentGradeModel().getId();
                mHttpHelper.getRecommendSubjectHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, 6);
            }
            initPopup();
        }
        mHttpHelper.getActive(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.e("__SelectSubjcetFragment","onHiddenChanged:" + hidden + "");
        if(hidden){//不在最前端界面显示，相当于调用了onPause()

        }else{//重新显示到最前端 ,相当于调用了onResume()
            LogUtil.e("__onHiddenChanged_home",grade_id +  "");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGradeModelList.size() == 0) {
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        } else {
            if (getCurrentGradeModel() != null && getCurrentGradeModel().getOption() != null) {
                mTvCurrentGrade.setText(getCurrentGradeModel().getOption());
                grade_id = getCurrentGradeModel().getId();
            }
            initPopup();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.viewpager);
        ll_point_container = view.findViewById(R.id.ll_point_container);
        mRvStudyKey = view.findViewById(R.id.rv_study_key);
        mRvfamousTeacher = view.findViewById(R.id.rv_famous_teacher);
        mRvRecommendCourse = view.findViewById(R.id.rv_recommend_course);
        ivShowPopup = view.findViewById(R.id.iv_show_popup);
        //========解决滑动卡顿start=======
        mRvStudyKey.setFocusable(false);
        mRvfamousTeacher.setFocusable(false);
        mRvRecommendCourse.setFocusable(false);
        mRvStudyKey.setNestedScrollingEnabled(false);
        mRvfamousTeacher.setNestedScrollingEnabled(false);
        mRvRecommendCourse.setNestedScrollingEnabled(false);
//        ========解决滑动卡顿end=======
        initSpringView(view);
        mTvInvitation.setVisibility(View.GONE);
        if (UserManager.getInstance().getIsVip()) {
//            mTvInvitation.setVisibility(View.GONE);
        } else {
//            mTvInvitation.setVisibility(View.VISIBLE);
        }
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
                        springView.onFinishFreshAndLoad();
                        loadData();
                    }
                }, 600);
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
    protected void loadData() {

        super.setDataLoaded(true);//设置数据已经加载过，避免重复加载
        LogUtil.e("__grade_id", grade_id + "");
        imageViewList.clear();
        mHttpHelper.getPersonalInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getBanner(this, UserManager.getInstance().getToken(), CommonUtils.getCurrentTimeStampInt());
        mHttpHelper.getSyncSubject(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getStudyKeyHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), 1, 2);
        mHttpHelper.getRecommendSubjectHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, 6);
        mHttpHelper.getFamousTeacherHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), 3);
        mHttpHelper.getUserSelectedVersion(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getUserInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mGradeModelList = SPUtil.getGradeList();

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        // 新的条目被选中时调用
        int newPosition = position % imageViewList.size();
        // 把之前的禁用, 把最新的启用, 更新指示器
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);
        // 记录之前的位置
        previousSelectedPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void initAdapter(BannerModel model) {
        mImgList = model.getData();
        if (mImgList.size() == 2) {
            for (int i = 0; i < 2; i++) {
                BannerModel.DataBean imageModel = mImgList.get(i);
                mImgList.add(imageModel);
            }
        } else if (mImgList.size() == 3) {
            for (int i = 0; i < 3; i++) {
                BannerModel.DataBean imageModel = mImgList.get(i);
                mImgList.add(imageModel);
            }
        }
        viewPager.setOnPageChangeListener(this);// 设置页面更新监听
        IMGIDS = new int[]{R.drawable.img01, R.drawable.img01, R.drawable.img01, R.drawable.img01};
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < mImgList.size(); i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(getActivity().getApplication());
            imageView.setId(imgae_ids[i]);
            LogUtil.e("__load" + i, mImgList.get(i).getImg());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //设置图片圆角角度
            RoundedCorners roundedCorners = new RoundedCorners(30);
            //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//            RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
            RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.img_cache1)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
            Glide.with(getActivity()).load(mImgList.get(i).getImg()).apply(options).into(imageView);
            //轮播图点击事件
            imageView.setOnClickListener(new PagerImageOnClick());
            imageViewList.add(imageView);
            // 加小白点, 指示器
            pointView = new View(getActivity().getApplication());
            pointView.setBackgroundResource(R.drawable.selector_bg_point);
            layoutParams = new LinearLayout.LayoutParams(12, 12);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }
        ll_point_container.getChildAt(0).setEnabled(true);
        previousSelectedPosition = 0;
        BannerAdapter bannerAdapter = new BannerAdapter(imageViewList);
        // 设置适配器
        viewPager.setAdapter(bannerAdapter);
        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
//        viewPager.setCurrentItem(500000); // 设置到某个位置
        viewPager.setPageTransformer(false, new ZoomOutPageTransformer());
//        viewPager.notify();
        autoViewPager();
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        super.onDestroy();
    }

    private void autoViewPager() {
        // 开启轮询
        new Thread() {
            public void run() {
                if (isRunning) {
                    return;
                }
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //避免按返回键退到桌面，再马上进入时，getActivity()为空导致闪退
                    if (getActivity() == null) {
                        return;
                    }
                    // 往下跳一位
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            System.out.println("设置当前位置: " + viewPager.getCurrentItem());
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            };
        }.start();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_search://搜索控件
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
//                showShare();
                intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_show_popup:
            case R.id.tv_current_grade://选择年级
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }

//                showPopup();
//                showProgressDialog("请稍等");
                showSelectGradeDia();
                break;
            case R.id.ll_search:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
                break;
            case R.id.ll_1_sync_subject:
                try {
                    intent = new Intent(getActivity(), VideoPlayActivity.class);
                    intent.putExtra("isShowQuestionMark", true);
                    intent.putExtra("type", Constants.REQUEST_GET_SYNC_SUBJECT_HOME);
                    intent.putExtra("subject_id", mSyncSubject.getSubject().get(0).getId());
                    intent.putExtra("classify_id", mSyncSubject.getClassify().getResCategoryId());
                    intent.putExtra("grade_id", grade_id);
                    for (int i = 0; i < mSelectedVersion.getData().size(); i++) {
                        UserSelectedVersionModel.Databean databean = mSelectedVersion.getData().get(i);
                        if (databean.getSubject_id() == mSyncSubject.getSubject().get(0).getId()) {
                            intent.putExtra("version_id", databean.getVersion().getId());
                            break;
                        }
                    }
                    getActivity().startActivity(intent);
                } catch (Exception e) {
                    LogUtil.e("__ll_1_sync_subject", e.getMessage());
                }
                break;
            case R.id.ll_2_sync_subject:
                try {
                    intent = new Intent(getActivity(), VideoPlayActivity.class);
                    intent.putExtra("isShowQuestionMark", true);
                    intent.putExtra("type", Constants.REQUEST_GET_SYNC_SUBJECT_HOME);
                    intent.putExtra("subject_id", mSyncSubject.getSubject().get(1).getId());
                    intent.putExtra("classify_id", mSyncSubject.getClassify().getResCategoryId());
                    intent.putExtra("grade_id", grade_id);
                    for (int i = 0; i < mSelectedVersion.getData().size(); i++) {
                        UserSelectedVersionModel.Databean databean = mSelectedVersion.getData().get(i);
                        if (databean.getSubject_id() == mSyncSubject.getSubject().get(1).getId()) {
                            intent.putExtra("version_id", databean.getVersion().getId());
                            break;
                        }
                    }
                    getActivity().startActivity(intent);
                } catch (Exception e) {
                    LogUtil.e("__ll_2_sync_subject", e.getMessage());
                }
                break;
            case R.id.ll_3_sync_subject:
                try {
                    intent = new Intent(getActivity(), VideoPlayActivity.class);
                    intent.putExtra("isShowQuestionMark", true);
                    intent.putExtra("type", Constants.REQUEST_GET_SYNC_SUBJECT_HOME);
                    intent.putExtra("subject_id", mSyncSubject.getSubject().get(2).getId());
                    intent.putExtra("classify_id", mSyncSubject.getClassify().getResCategoryId());
                    intent.putExtra("grade_id", grade_id);
                    LogUtil.e("__mSelectedVersion", String.valueOf(mSelectedVersion == null));
                    LogUtil.e("__mSelectedVersion.getData()", String.valueOf(mSelectedVersion.getData() == null));
                    LogUtil.e("__mSelectedVersion.getData().size()", String.valueOf(mSelectedVersion.getData().size()));
                    for (int i = 0; i < mSelectedVersion.getData().size(); i++) {
                        UserSelectedVersionModel.Databean databean = mSelectedVersion.getData().get(i);
                        if (databean.getSubject_id() == mSyncSubject.getSubject().get(2).getId()) {
                            intent.putExtra("version_id", databean.getVersion().getId());
                            break;
                        }
                    }
                    getActivity().startActivity(intent);
                } catch (Exception e) {
                    LogUtil.e("__ll_3_sync_subject", e.getMessage());
                }
                break;
            case R.id.ll_more:
            case R.id.tv_more_study_key:
                intent = new Intent(getActivity(), StudyKeyActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_more_famous_teacher:
            case R.id.tv_more_teacher:
                intent = new Intent(getActivity(), FamousTeacherActivity.class);
                intent.putExtra("grade_id",grade_id);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_more_recommend_subject:
            case R.id.tv_more_recommend:
                if (mOnRecommendMoreClickListener != null) {
                    mOnRecommendMoreClickListener.onRecommendMoreClick();
                }
                break;
            case R.id.tv_invitation:
                intent = new Intent(getActivity(), InvitationWebViewActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_main_title_bar:
                if (isPopShowing) {
                    mGradePop.dismiss();
                    return;
                }
                break;
        }
    }

    public void setOnRecommendMoreClickListener(OnRecommendMoreClickListener onRecommendMoreClickListener) {
        mOnRecommendMoreClickListener = onRecommendMoreClickListener;
    }

    private OnRecommendMoreClickListener mOnRecommendMoreClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public interface OnRecommendMoreClickListener {
        void onRecommendMoreClick();
    }

    /**
     * 网络请求成功回调
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        CommonUtils.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                springView.onFinishFreshAndLoad();
            }
        }, 200);
        try {
            switch (reqType) {
                case Constants.REQUEST_BANNER:
                    BannerModel bannerModel = (BannerModel) msg.obj;
                    initAdapter(bannerModel);
                    break;
                case Constants.REQUEST_GET_SYNC_SUBJECT_HOME:
                    SyncSubjectHomeModel syncSubjectModel = (SyncSubjectHomeModel) msg.obj;
                    mSyncSubject = syncSubjectModel.getData();
                    initSyncSubject();
                    break;
                case Constants.REQUEST_GET_USER_SELECTED_VERSION:
                    LogUtil.e("__data_id", "REQUEST_GET_USER_SELECTED_VERSION");
                    mSelectedVersion = (UserSelectedVersionModel) msg.obj;
                    //没有选择年级则让客户先选择
                    if (mSelectedVersion.getData() == null || mSelectedVersion.getData().size() == 0) {
                        ToastUtil.showToast("请先选择年级和版本");
                        Intent intent = new Intent(getActivity(), SelectGradeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(intent);
                    } else {
                        UserManager.getInstance().setUserSelectedVersion(mSelectedVersion.getData());
//                        UserManager.getContext().setGradeId(mSelectedVersion.get);
                    }
                    break;
                case Constants.REQUEST_GET_STUDY_KEY_HOME:
                    mStudyKeyModelHome = (StudyKeyModelHome) msg.obj;
                    initStudyKeyData();
                    break;
                case Constants.REQUEST_GET_RECOMMEND_SUBJECT_HOME:
                    RecommendSubjectHomeModel data = (RecommendSubjectHomeModel) msg.obj;
                    mData = data.getData();
                    initRecommendSubjectData();
                    break;
                case Constants.REQUEST_GET_FAMOUS_TEACHER_HOME:
                    FamousTeacherModelHome famousTeacherModelHome = (FamousTeacherModelHome) msg.obj;
                    initFamousTeacher(famousTeacherModelHome.getData());
                    break;
                case Constants.REQUEST_GET_GRADE:
                    GradeModel model = (GradeModel) msg.obj;
                    //保存到SP中
                    SPUtil.saveGradeList(model.getData());
                    mGradeModelList = SPUtil.getGradeList();
                    initPopup();
                    if (getCurrentGradeModel() != null && getCurrentGradeModel().getOption() != null) {
                        mTvCurrentGrade.setText(getCurrentGradeModel().getOption());
                        grade_id = getCurrentGradeModel().getId();
                    }
                    break;
                case Constants.REQUEST_GET_USER_INFO:
                    UserInfoModel loginModel = (UserInfoModel) msg.obj;
                    UserInfoModel.Databean databean1 = loginModel.getData();
                    setUserInfo(databean1);
                    break;
                case Constants.REQUEST_GET_PERSONAL_INFO:
                    GetPersonalInfoModel getPersonalInfoModel = (GetPersonalInfoModel) msg.obj;
                    UserManager.getInstance().setGradeId(getPersonalInfoModel.getData().getGrade().getId());
                    grade_id = getPersonalInfoModel.getData().getGrade().getId();
                    //通知选课界面刷新年级，重新请求数据
                    EventBus.getDefault().post(new CommonMessageEvent(Constants.REQUEST_EDIT_GRADE_AND_VERSION,""));
                    if (getCurrentGradeModel() != null && getCurrentGradeModel().getOption() != null) {
                        LogUtil.e("__setCurrentGrad_id",UserManager.getInstance().getGradeId() + "");
                        LogUtil.e("__setCurrentGrad",getCurrentGradeModel().getOption());
                        mTvCurrentGrade.setText(getCurrentGradeModel().getOption());
                    }
                    break;
                case Constants.REQUEST_EDIT_GRADE_AND_VERSION:
                    //修改年级成功
                    ToastUtil.showToast("修改年级成功");
                    UserManager.getInstance().setGradeId(grade_id);
                    //通知选课界面刷新年级，重新请求数据
                    EventBus.getDefault().post(new CommonMessageEvent(Constants.REQUEST_EDIT_GRADE_AND_VERSION,""));
                    break;
                case Constants.REQUEST_GET_ACTIVE:
                    GetActiveModel getActiveModel = (GetActiveModel) msg.obj;
                    if (getActiveModel.getData() != null && getActiveModel.getData().getActive_id() != 0){
                        mTvInvitation.setVisibility(View.VISIBLE);
                    }else {
                        mTvInvitation.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络请求失败或者错误回调
     */
    @Override
    public void onHttpError(int reqType, String error) {
        CommonUtils.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                springView.onFinishFreshAndLoad();
            }
        }, 200);
        LogUtil.e("__onHttpError", error);
        switch (reqType){
            case Constants.REQUEST_SAVE_SUBJECT_AND_VERSION:
                //修改年级失败
                ToastUtil.showToast("修改年级失败");
                break;
            case Constants.REQUEST_GET_ACTIVE:
                mTvInvitation.setVisibility(View.GONE);
                break;
        }
//        ToastUtil.showToast(error);
    }

    private void initPopup() {
        mGradePop = new SelectGradePopup(getActivity(), mGradeModelList, new GridLayoutManager(getActivity(), 3));
        for (int i = 0; i < mGradeModelList.size(); i++) {
            try {
                GradeModel.Databean databean = mGradeModelList.get(i);
                if (databean.getId() == UserManager.getInstance().getGradeId()) {
                    mGradePop.setSelection(i);
                }
            } catch (Exception e) {
                e.getStackTrace();
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
        //  当用户点击pop选择年级的时候，设置年级，刷新界面数据
        grade_id = mGradeModelList.get(position).getId();
        LogUtil.e("__onPopupItemClick_grade_id", grade_id + "");
        mTvCurrentGrade.setText(mGradeModelList.get(position).getOption());
        //更新推荐课程
        mHttpHelper.getRecommendSubjectHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, 6);
        if (mGradePop.isShowing()) {
            mGradePop.dismiss();
        }
    }
    //根据保存的用户年级ID，获取年级实体对象
    private GradeModel.Databean getCurrentGradeModel() {
        GradeModel.Databean gradeModel = null;
        for (GradeModel.Databean model : mGradeModelList) {
            if (model != null && model.getId() == grade_id) {
                gradeModel = model;
            }
        }
        return gradeModel;
    }

    /**
     * 初始化同步课程
     */
    private void initSyncSubject() {
        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners);
//                .placeholder(R.drawable.loading)
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
//                .skipMemoryCache(false);//内存缓存
        try {
//            mTv1SyncSubject.setText(mSyncSubject.getSubject().get(0).getOption());
//            mTv2SyncSubject.setText(mSyncSubject.getSubject().get(1).getOption());
//            mTv3SyncSubject.setText(mSyncSubject.getSubject().get(2).getOption());
            if (mSyncSubject.getSubject().get(0).getOption().equals("语文")){
                Glide.with(getActivity()).load(R.drawable.sync_chinese_home).apply(options).into(mIv1SyncSubject);
            }else if (mSyncSubject.getSubject().get(0).getOption().equals("数学")){
                Glide.with(getActivity()).load(R.drawable.sync_math_home).apply(options).into(mIv1SyncSubject);
//                mIv1SyncSubject.setBackgroundResource(R.drawable.sync_math_home);
            }else if(mSyncSubject.getSubject().get(0).getOption().equals("英语")){
                Glide.with(getActivity()).load(R.drawable.sync_english_home).apply(options).into(mIv1SyncSubject);
//                mIv1SyncSubject.setBackgroundResource(R.drawable.sync_english_home);
            }
            if (mSyncSubject.getSubject().get(1).getOption().equals("语文")){
                Glide.with(getActivity()).load(R.drawable.sync_chinese_home).apply(options).into(mIv2SyncSubject);
            }else if (mSyncSubject.getSubject().get(1).getOption().equals("数学")){
                Glide.with(getActivity()).load(R.drawable.sync_math_home).apply(options).into(mIv2SyncSubject);
//                mIv1SyncSubject.setBackgroundResource(R.drawable.sync_math_home);
            }else if(mSyncSubject.getSubject().get(1).getOption().equals("英语")){
                Glide.with(getActivity()).load(R.drawable.sync_english_home).apply(options).into(mIv2SyncSubject);
//                mIv1SyncSubject.setBackgroundResource(R.drawable.sync_english_home);
            }
            if (mSyncSubject.getSubject().get(2).getOption().equals("语文")){
                Glide.with(getActivity()).load(R.drawable.sync_chinese_home).apply(options).into(mIv3SyncSubject);
            }else if (mSyncSubject.getSubject().get(2).getOption().equals("数学")){
                Glide.with(getActivity()).load(R.drawable.sync_math_home).apply(options).into(mIv3SyncSubject);
//                mIv1SyncSubject.setBackgroundResource(R.drawable.sync_math_home);
            }else if(mSyncSubject.getSubject().get(2).getOption().equals("英语")){
                Glide.with(getActivity()).load(R.drawable.sync_english_home).apply(options).into(mIv3SyncSubject);
//                mIv1SyncSubject.setBackgroundResource(R.drawable.sync_english_home);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initStudyKeyData() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvStudyKey.setLayoutManager(manager);
        if (mStudyKeyModelHome.getData().getData() != null && mStudyKeyModelHome.getData().getData().size() > 0) {
            RvAdapterStudyKeyHome2 adapter = new RvAdapterStudyKeyHome2(getActivity(), mStudyKeyModelHome.getData());
            mRvStudyKey.setNestedScrollingEnabled(false);
            mRvStudyKey.setAdapter(adapter);
            adapter.setItemClickListener(new RvAdapterStudyKeyHome2.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    LogUtil.e("__StudyKey_position",position + "");
                    Intent intent = new Intent(getActivity(), ReadActivity.class);
                    intent.putExtra("id",mStudyKeyModelHome.getData().getData().get(position).getId());
                    getActivity().startActivity(intent);
                }
            });
        }
    }

    private void initRecommendSubjectData() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRvRecommendCourse.setLayoutManager(manager);
        RvAdapterRecommendSubjectHome2 adapter = new RvAdapterRecommendSubjectHome2(getActivity(), mData);
        mRvRecommendCourse.setNestedScrollingEnabled(false);
        mRvRecommendCourse.setAdapter(adapter);
        adapter.setClickListener(new RvAdapterRecommendSubjectHome2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                intent.putExtra("isShowQuestionMark", true);
                intent.putExtra("type", Constants.REQUEST_GET_RECOMMEND_SUBJECT_HOME);
                intent.putExtra("course_id", mData.getData().get(position).getId());


//                intent.putExtra("course_id",grade_id);
                getActivity().startActivity(intent);
                LogUtil.e("__course_id_home", mData.getData().get(position).getId() + "");
                LogUtil.e("__course_name_home", mData.getData().get(position).getName());
            }
        });
    }

    private void initFamousTeacher(FamousTeacherModelHome.DatabeanX data) {
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvfamousTeacher.setLayoutManager(manager);
        RvAdapterFamousTeacherHome2 adapter2 = new RvAdapterFamousTeacherHome2(getActivity(), data);
        mRvfamousTeacher.setNestedScrollingEnabled(false);
        mRvfamousTeacher.setAdapter(adapter2);
        adapter2.setItemClickListener(new RvAdapterFamousTeacherHome2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), FamousTeacherIntroductionActivity.class);
                intent.putExtra("teacher_id",data.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    public boolean onBackPressed() {
        if (isPopShowing) {
            mGradePop.dismiss();
            return true;
        } else {
            return false;
        }
    }


    /**
     * 设置用户信息
     *
     * @param databean
     */
    private void setUserInfo(UserInfoModel.Databean databean) {
        LogUtil.e("__userId_MainActivity", databean.getUid() + "");
        UserManager.getInstance().setToken(databean.getToken());
        UserManager.getInstance().setHeadImgUrl(databean.getHeadimgurl());
        UserManager.getInstance().setUserId(databean.getUid());
        UserManager.getInstance().setIntegral(databean.getIntegral());
        UserManager.getInstance().setSex(databean.getSex());
        if (databean.getCountry() != null) {
            UserManager.getInstance().setCountry(databean.getCountry());
        }
        if (databean.getProvince() != null) {
            UserManager.getInstance().setProvince(databean.getProvince());
        }
        if (databean.getCity() != null) {
            UserManager.getInstance().setCity(databean.getCity());
        }
        UserManager.getInstance().setVipTime(databean.getVip_time());
        UserManager.getInstance().setUserPhone(databean.getPhone());
        UserManager.getInstance().setUserIcon(databean.getHeadimgurl());
        //0表示不是VIP,1表示是VIP
        if (databean.getIsVip() == 0) {
            UserManager.getInstance().setIsVip(false);
        } else {
            UserManager.getInstance().setIsVip(true);
            LogUtil.e("__getVip_time", databean.getVip_time() + "");
        }
    }

    /**
     * 展示年级修改的Dialog
     */
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
                //更新推荐课程
                mHttpHelper.getRecommendSubjectHome(HomeFragment.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, 6);
                //发送到后台修改年级
                mHttpHelper.editSelectGradeAndVersion(HomeFragment.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),String.valueOf(grade_id),"");
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
        if (mMessageEvent.getType() == Constants.REQUEST_EDIT_GRADE_AND_VERSION){
            grade_id = UserManager.getInstance().getGradeId();
            if (getCurrentGradeModel() != null && getCurrentGradeModel().getOption() != null) {
                mTvCurrentGrade.setText(getCurrentGradeModel().getOption());
                //更新推荐课程
                mHttpHelper.getRecommendSubjectHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), grade_id, 6);
                mHttpHelper.getUserSelectedVersion(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                mHttpHelper.getUserInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                mGradeModelList = SPUtil.getGradeList();
            }
        }
    }

    //viewpager点击监听
    //轮播图片点击事件
    class PagerImageOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {

                switch (v.getId()) {
                    case R.id.pager_image1:
                        getTapy(0);
//                        intent = new Intent(getActivity(), AoePPTActivity.class);
//                        getActivity().startActivity(intent);
//                        LogUtil.e("__点击0", mImgList.get(0).getLink());
//                    myDialog.setGone().setMsg("仿iOS的弹窗，看看实际效果如何呢，仿iOS的弹窗，仿iOS的弹窗").setNegativeButton("取消",R.color.colorAccent,null).setPositiveButton("确定",R.color.colorAccent,null).show();
                        break;
                    case R.id.pager_image2:
//                    if (mImgList.get(1).getLink().equals("")) {
//                        intent = new Intent(getActivity(), VipCenterActivity.class);
//                    } else {
//                        intent = new Intent(getActivity(), WebViewActivity.class);
//                        intent.putExtra("url", mImgList.get(1).getLink());
//                    }
                       /* intent = new Intent(getActivity(), VideoPlayActivity.class);
                        intent.putExtra("course_id",Integer.valueOf(mImgList.get(1).getLink()));
                        getActivity().startActivity(intent);
                        LogUtil.e("__点击1", mImgList.get(1).getLink());*/
//                    myDialog.setGone().setMsg("仿iOS的弹窗").setPositiveButton("确定",null).show();
                        getTapy(1);
                        break;
                    case R.id.pager_image3:
//                    if (mImgList.get(2).getLink().equals("")) {
//                        intent = new Intent(getActivity(), VipCenterActivity.class);
//                    } else {
//                        intent = new Intent(getActivity(), WebViewActivity.class);
//                        intent.putExtra("url", mImgList.get(2).getLink());
//                    }
                        /*intent = new Intent(getActivity(), VideoPlayActivity.class);
                        intent.putExtra("course_id",Integer.valueOf(mImgList.get(0).getType()));//传递一个Type
                        getActivity().startActivity(intent);*/
                     /*   intent = new Intent(getActivity(), VideoPlayActivity.class);
                        intent.putExtra("course_id",Integer.valueOf(mImgList.get(2).getLink()));
                        getActivity().startActivity(intent);
                        LogUtil.e("__点击2", mImgList.get(2).getLink());*/

//                    myDialog.setGone().setTitle("提示").setMsg("仿iOS的弹窗").setNegativeButton("取消",null).setPositiveButton("确定", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ToastUtil.showToast("3确认");
//                        }
//                    }).show();
                        getTapy(2);
                        break;
                    case R.id.pager_image4:
//                    if (mImgList.get(3).getLink().equals("")) {
//                        intent = new Intent(getActivity(), VipCenterActivity.class);
//                    } else {
//                        intent = new Intent(getActivity(), WebViewActivity.class);
//                        intent.putExtra("url", mImgList.get(3).getLink());
//                    }
                       /* intent = new Intent(getActivity(),AoePPTActivity.class);
//                        intent = new Intent(getActivity(), VideoPlayActivity.class);
//                        intent.putExtra("course_id",Integer.valueOf(mImgList.get(3).getLink()));
                        getActivity().startActivity(intent);
                        LogUtil.e("__点击3", mImgList.get(3).getLink());*/
                        getTapy(3);
                        break;
                    case R.id.pager_image5:
//                    if (mImgList.get(4).getLink().equals("")) {
//                        intent = new Intent(getActivity(), VipCenterActivity.class);
//                    } else {
//                        intent = new Intent(getActivity(), WebViewActivity.class);
//                        intent.putExtra("url", mImgList.get(4).getLink());
//                    }
                       /* intent = new Intent(getActivity(), VideoPlayActivity.class);
                        intent.putExtra("course_id",Integer.valueOf(mImgList.get(4).getLink()));
                        getActivity().startActivity(intent);
                        LogUtil.e("__点击4", mImgList.get(4).getLink());*/
                        getTapy(4);
                        break;
                    case R.id.pager_image6:
//                    if (mImgList.get(5).getLink().equals("")) {
//                        intent = new Intent(getActivity(), VipCenterActivity.class);
//                    } else {
//                        intent = new Intent(getActivity(), WebViewActivity.class);
//                        intent.putExtra("url", mImgList.get(5).getLink());
//                    }
                       /* intent = new Intent(getActivity(), VideoPlayActivity.class);
                        intent.putExtra("course_id",Integer.valueOf(mImgList.get(5).getLink()));
                        getActivity().startActivity(intent);
                        LogUtil.e("__点击5", mImgList.get(5).getLink())*/;
                        getTapy(5);
                        break;
                    default:
                        break;
                }
            }catch (Exception e){

            }
        }
    }
    //轮播图跳转的类型
    public void getTapy(int position) {
        Intent intent;
        if (mImgList.get(position).getType() == 0) {
            intent = new Intent(getActivity(), VideoPlayActivity.class);
            intent.putExtra("course_id", Integer.valueOf(mImgList.get(position).getLink()));
            getActivity().startActivity(intent);
        } else if (mImgList.get(position).getType() == 1) {
            intent = new Intent(getActivity(), AoePPTActivity.class);
            getActivity().startActivity(intent);
        } else if (mImgList.get(position).getType() == 2) {
            intent = new Intent(getActivity(), ReadActivity.class);
            intent.putExtra("id", mData.getData().get(position).getId());
            int id = Integer.decode(mImgList.get(position).getLink());
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        } else if (mImgList.get(position).getType() == 3) {
            intent = new Intent(getActivity(), VipCenterActivity.class);
            getActivity().startActivity(intent);
        } else if (mImgList.get(position).getType() == 4) {
            intent = new Intent(getActivity(), InvitationWebViewActivity.class);
            intent.putExtra("url", mImgList.get(position).getLink());
            getActivity().startActivity(intent);
        } else if (mImgList.get(position).getType() == 5) {
            intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("url", mImgList.get(position).getLink());
            getActivity().startActivity(intent);
        }

    }

}
