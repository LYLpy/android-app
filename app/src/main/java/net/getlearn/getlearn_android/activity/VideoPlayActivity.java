package net.getlearn.getlearn_android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.universalvideoview.UniversalVideoView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.TabPagerAdapter;
import net.getlearn.getlearn_android.fragment.StudentCommentFragment;
import net.getlearn.getlearn_android.fragment.SubjectIntroduceFragment;
import net.getlearn.getlearn_android.fragment.SubjectListFragment;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.SubjectIntroduceModel;
import net.getlearn.getlearn_android.model.bean.SubjectListModel;
import net.getlearn.getlearn_android.model.bean.UserInfoModel;
import net.getlearn.getlearn_android.model.bean.VideoUrlModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.InternetUtil;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.CreateStudyPlanDialog;
import net.getlearn.getlearn_android.view.IOSAlertDialog;
import net.getlearn.getlearn_android.view.IOSAlertDialogInternet;
import net.getlearn.getlearn_android.view.MyUniversalMediaController;
import net.getlearn.getlearn_android.view.WrapUniversalVideoView;
import net.getlearn.getlearn_android.view.emoji.ExpressionAdapter;
import net.getlearn.getlearn_android.view.emoji.SmileyParser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/30------更新------
 */

public class VideoPlayActivity extends BaseActivity implements UniversalVideoView.VideoViewCallback, BaseHelper.IHttpCallback {

    private String TAG = "__json" + Constants.REQUEST_GET_SUBJECT_INTRODUCE;
    private int SubjectData; //1为有课程


    //    测试视频链接
//    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ll_emoji)
    LinearLayout llEmoji;
    @BindView(R.id.rl_toolbar)
    RelativeLayout rlToolbar;
    @BindView(R.id.ll_emoji_layout)
    LinearLayout llEmojiLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.ll_upper_layout)
    LinearLayout mLlUpperLayout;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_collection)
    LinearLayout mLlCollection;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.video_view)
    WrapUniversalVideoView mVideoView;
    @BindView(R.id.media_controller)
    MyUniversalMediaController mMediaController;
    @BindView(R.id.view_divider)
    View mViewDivider;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    @BindView(R.id.video_layout)
    FrameLayout mVideoLayout;
    @BindView(R.id.tv_title_act)
    TextView mTvTitleAct;
    //@BindView(R.id.img_start)
    //ImageView imgStart;
    //输入框文字字数
    @BindView(R.id.tv_word_number)
    TextView mTvWordNumber;
    @BindView(R.id.iv_collection)
    ImageView mIvCollection;
    @BindView(R.id.iv_question_mark)
    ImageView mIvQuestionMark;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.iv_thr_screen)
    ImageView mIvThrScreen;
    @BindView(R.id.img_start)
    ImageView img_Start;

    private boolean isShowQuestionMark;//是否展示切换版本提示按钮
    private SubjectIntroduceModel.Databean subjectIntroduceDta;
    private int cachedHeight;
    boolean isFullscreen;
    private boolean isSetBg = true;//刚进入时，防止视频播放器是黑屏的，开启视频播放，给视频播放界面设置背景图片
    //播放进度
    private int mSeekPosition;
    private String[] titles;
    private List<Fragment> fragments;
    private SubjectIntroduceFragment fm1;
    private SubjectListFragment fm2;
    private StudentCommentFragment fm3;
    private int type = -1;//区分类型，是同步课程还是推荐课程
    private int course_id;
    private int subject_id;
    private int classify_id;
    private int version_id;
    private int grade_id;
    private int mFm1Position;
    private String mVideoUrl;
    private boolean isFm1FirstRequest = true;//是否fm1首次获取链接
    private int mCurrentPager;
    private IOSAlertDialog mIOSAlertDialog;
    private IOSAlertDialogInternet mIOSAlertDialogInternet;
    private int mIsCollection;//是否收藏,0没收藏，1为收藏
    private String title;//标题，也就是这个课程的名字
    private CreateStudyPlanDialog mCialog;


    @Override
    protected void initData() {

        mHttpHelper.getUserInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mIOSAlertDialog = new IOSAlertDialog(this).builder();
        mIOSAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
        mIOSAlertDialog.setMsg("VIP会员专属视频，是否开通VIP").setTitle("VIP专属", R.color.black).setNegativeButton("取消", R.color.bg_gray5, null).setPositiveButton("前往开通", R.color.btnblue, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VideoPlayActivity.this, VipCenterActivity.class));
            }
        });
        List<Integer> data2 = new ArrayList<>();
        for (int i = 0; i < mParser.CAICAI_SMILEY_RES_IDS.length; i++) {
            data2.add(mParser.CAICAI_SMILEY_RES_IDS[i]);
        }
        //把图片数据分页，每页20个表情一个删除键，三行
        caicaiList = splitList(data2, 20);
        expressionTypeList.put(R.drawable.aw, caicaiList);
        //把符号数据分页，每页最多20个表情，加上一个删除键
        // emojiTxtList = splitStringList(Arrays.asList(mParser.arrTextEMoji), 20);
        caicaiTxtList = splitStringList(Arrays.asList(mParser.arrTextCaiCai), 20);
        initEmojiAdapter(caicaiList.size());//表情适配器

        mMediaController.setOnMediaBarStatusChange(new MyUniversalMediaController.OnMediaBarStatusChange() {
            @Override
            public void onMediaBarStatusChange(boolean isShow) {
//                LogUtil.e("__onMediaBarStatusChange",isShow + "");
                if (isShow) {
                    mIvShare.setVisibility(View.VISIBLE);
                    mIvThrScreen.setVisibility(View.VISIBLE);
                } else {
                    mIvShare.setVisibility(View.GONE);
                    mIvThrScreen.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        LogUtil.e("___type:",type+"");
        isShowQuestionMark = intent.getBooleanExtra("isShowQuestionMark", false);
        if (isShowQuestionMark) {
            mIvQuestionMark.setVisibility(View.VISIBLE);
        }
        subject_id = intent.getIntExtra("subject_id", 0);
        classify_id = intent.getIntExtra("classify_id", 0);
        version_id = intent.getIntExtra("version_id", 0);

        course_id = intent.getIntExtra("course_id", 0);

        grade_id = intent.getIntExtra("grade_id", UserManager.getInstance().getGradeId());
        LogUtil.e("__initView_subject_id", subject_id + "");
        LogUtil.e("__initView_classify_id", classify_id + "");
        LogUtil.e("__initView_version_id", version_id + "");
        LogUtil.e("__initView_course_id", course_id + "");
        LogUtil.e("__initView_grade_id", grade_id + "");
        //有course_id的话grade_id就不需要了
        if (course_id != 0) {
            grade_id = 0;
        }
        LogUtil.e("__initView_grade_id_2", grade_id + "");
        HashMap instan = new HashMap();
        instan.put("subject_id",subject_id);
        instan.put("classify_id",classify_id);
        instan.put("version_id",version_id);
        instan.put("course_id",course_id);
        instan.put("grade_id",grade_id);
        LogUtil.e("__json7所传的数值",instan+"");

        //fm1 = SubjectIntroduceFragment.getInstanace(subject_id, classify_id, version_id, course_id, grade_id, 0);

        SubjectIntroduceFragment fm1 = new SubjectIntroduceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("subject_id", subject_id);
        bundle.putInt("classify_id", classify_id);
        bundle.putInt("version_id", version_id);
        bundle.putInt("course_id", course_id);
        bundle.putInt("grade_id", grade_id);
        LogUtil.e("__json7传递",bundle+"");
        fm1.setArguments(bundle);
        fm2 = SubjectListFragment.getInstanace(subject_id, classify_id, version_id, course_id, grade_id);
        fm3 = new StudentCommentFragment();
//        }else if (type == Constants.REQUEST_GET_SELECT_SUBJECT_LIST){
//            classify_id = intent.getIntExtra("classify_id",0);
//            subject_id = intent.getIntExtra("subject_id",0);
//        }else {
//            subject_id = intent.getIntExtra("subject_id",0);
//            classify_id = intent.getIntExtra("classify_id",0);
//            version_id = intent.getIntExtra("version_id",0);
//            subject_id = intent.getIntExtra("subject_id",0);
//        }
        //设置获取到课程id时候回调
        fm1.setOnCourseIdGetted(new SubjectIntroduceFragment.OnCourseIdGetted() {
            @Override
            public void onCourseIdGetted(int courseId) {
                course_id = courseId;
                LogUtil.e("__Course_id_videoplay", course_id + "");
                fm2.setCourse_id(courseId);
//                fm2.setCourse_id();
                fm3.setCourse_id(courseId);

            }
        });

        fm1.setOnDataGettedListener(new SubjectIntroduceFragment.OnDataGettedListener() {
            @Override
            public void onDataGetted(SubjectIntroduceModel.Databean data) {
                subjectIntroduceDta = data;
                if (subjectIntroduceDta != null) {
                    LogUtil.e(VideoPlayActivity.this + "", "subjectIntroduceDta!= null::"+subjectIntroduceDta);
                }
                title = data.getCourse_introduce().getName();
//                mTvTitleAct.setText(title);
                isFm1FirstRequest = true;
                //如果名字带了课程名字，则去掉
                String mediaTitle = data.getFree_list().get(0).getName();
                if (data.getFree_list().get(0).getName().contains(title)) {
                    mediaTitle = mediaTitle.replace(data.getCourse_introduce().getName(), "");
                }
                mMediaController.setTitle(mediaTitle);
                if (mVideoUrl == null || mVideoUrl.equals("")) {
                    mHttpHelper.getVideoUrl(VideoPlayActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), data.getFree_list().get(0).getId(), data.getFree_list().get(0).getIs_free(), Constants.REQUEST_GET_VIDEO_URL);

                }
            }
        });

        fragments = new ArrayList<>();
        fragments.add(fm1);
        fragments.add(fm2);
        fragments.add(fm3);
        titles = getResources().getStringArray(R.array.tab_video_play);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments, this, titles);
        mViewpager.setAdapter(adapter);
        //2.绑定ViewPager和TabLayout
        mTablayout.setupWithViewPager(mViewpager);

        fm1.setOnItemClickListener(new SubjectIntroduceFragment.OnItemClickListener() {
            @Override
            public void onItemClick(SubjectIntroduceModel.Databean.FreeListbean freeListbean, int position) {
                //mVideoUrl空则说明之前第一次请求utl没有请求到，必须再次请求
                if (mVideoUrl == null || position != mFm1Position || !mVideoView.isPlaying() && mSeekPosition == 0) {
                    mFm1Position = position;
                    isFm1FirstRequest = false;
                    //如果名字带了课程名字，则去掉
                    String mediaTitle = freeListbean.getName();
                    if (mediaTitle.contains(title)) {
                        mediaTitle = mediaTitle.replace(title, "");
                    }
                    mMediaController.setTitle(mediaTitle);

                    mHttpHelper.getVideoUrl(VideoPlayActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), freeListbean.getId(), freeListbean.getIs_free(), Constants.REQUEST_GET_VIDEO_URL_AND_PLAY);
                } else {
                    mMediaController.show();
                    mSeekPosition = 0;
                }
            }
        });

        fm2.setOnItemClickListener(new SubjectListFragment.OnItemClickListener() {
            @Override
            public void onItemClick(SubjectListModel.DatabeanX.ClassListbean.Databean classInfo, int position) {
                int currentTimstamp = CommonUtils.getCurrentTimeStampInt();
                LogUtil.e("__onItemClick_isFree", classInfo.getIs_free() + "");
                LogUtil.e("__onItemClick_!getIsVip", !UserManager.getInstance().getIsVip() + "");
                LogUtil.e("__onItemClick_currentTimstamp", currentTimstamp + "");
                LogUtil.e("__onItemClick_UserManager.getContext().getVipTime()", UserManager.getInstance().getVipTime() + "");
                //0为免费，1为收费
                if (classInfo.getIs_free() == 1) {
                    if (!UserManager.getInstance().getIsVip() || currentTimstamp > UserManager.getInstance().getVipTime()) {
                        mIOSAlertDialog.show();
                        return;
                    }
                }
                //如果名字带了课程名字，则去掉
                String mediaTitle = classInfo.getName();
//                LogUtil.e("__mediaTitle_1",String.valueOf(mediaTitle));
                if (mediaTitle.contains(title)) {
                    mediaTitle = mediaTitle.replace(title, "");
                }
//                LogUtil.e("__mediaTitle_2",String.valueOf(mediaTitle));
                mMediaController.setTitle(mediaTitle);
//                LogUtil.e("__fm2Click","免费");
                //mVideoUrl空则说明之前第一次请求utl没有请求到，必须再次请求
                if (mVideoUrl == null || position != mFm1Position || !mVideoView.isPlaying() && mSeekPosition == 0) {
                    mFm1Position = position;
                    mHttpHelper.getVideoUrl(VideoPlayActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), classInfo.getId(), classInfo.getIs_free(), Constants.REQUEST_GET_VIDEO_URL_AND_PLAY);
                } else {
                    mMediaController.show();
                    mSeekPosition = 0;
                }
            }
        });

        fm2.setOnIsCollectionGettedListener(new SubjectListFragment.OnIsCollectionGetted() {
            @Override
            public void onIsCollectionGetted(int isCollection) {
                mIsCollection = isCollection;
                //0为未收藏，1为已收藏
                if (isCollection == 1) {
                    mIvCollection.setSelected(true);
                    mTvCollect.setText("已收藏");
                } else {
                    mIvCollection.setSelected(false);
                    mTvCollect.setText("收藏");
                }
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                // i是当前选中的页面的Position
//                LogUtil.e("__onPageSelected", "onPageSelected------>"+i);
                mCurrentPager = i;
                if (i == 2) {
                    llBottom.setVisibility(View.GONE);
                    editComment.setVisibility(View.VISIBLE);
                    llEmoji.setVisibility(View.VISIBLE);
                } else {
                    llBottom.setVisibility(View.VISIBLE);
                    editComment.setVisibility(View.GONE);
                    llEmoji.setVisibility(View.GONE);
                    keyBoardState = 0;
                    imm.hideSoftInputFromWindow(editComment.getWindowToken(), 0);
                    iv_emoji.setSelected(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            vp_emoji.setVisibility(View.GONE);
                        }
                    }, 50);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        initEmojiView();
        fm3.setParser(mParser);
        rlToolbar.setOnClickListener(this);
        mLlUpperLayout.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
        mLlCollection.setOnClickListener(this);
        mIvQuestionMark.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
        mIvThrScreen.setOnClickListener(this);
        img_Start.setOnClickListener(this);
        //imgStart.setOnClickListener(this);

        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoViewCallback(this);

        setVideoAreaSize();//设置置视频区域大小
        mVideoView.setVideoViewCallback(this);
//        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Log.e("__onCompletion", mp.getCurrentPosition() + "");
//            }
//        });
//        mVideoView.setVideoPath(VIDEO_URL);
        mVideoView.requestFocus();
        //设置选择中间的tablayout
//        mTablayout.getTabAt(1).select();

    }

    private void initEmojiView() {
        activity = this;
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        SmileyParser.init(activity);//初始化正则表达式工具类
        mParser = SmileyParser.getInstance();
        editComment = findViewById(R.id.edit_comment);//输入框
        iv_emoji = findViewById(R.id.iv_emojie);
        gridView = (GridView) getLayoutInflater().inflate(R.layout.gridview_emoji, null);//表情gridView
        vViewPager = findViewById(R.id.viwepager_expression);//viewPager
        vLl_dots = findViewById(R.id.ll_dot_container);//圆点容器
        vp_emoji = findViewById(R.id.vp_emoji);
        editComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyBoardState = 1;
                vp_emoji.setVisibility(View.GONE);
                iv_emoji.setSelected(false);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                imm.showSoftInput(editComment, 0);
//                        imm.showSoftInput(null,0);
//                    }
//                },50);
                return false;
            }
        });
        editComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTvWordNumber.setText(charSequence.length() + "/300");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private SmileyParser mParser;
    private Activity activity;
    private EditText editComment;
    private ImageView iv_emoji;
    private GridView gridView;
    List<List<Integer>> caicaiList = new ArrayList<>();//表情2的数据源
    List<List<String>> caicaiTxtList = new ArrayList<>();//表情2的符号数据源
    Map<Integer, List<List<Integer>>> expressionTypeList = new TreeMap<>();//表情总数据源,以图标为key
    private List<GridView> gridList = new ArrayList<>();
    private ExpressionAdapter mExpressionAdapter;
    private ViewPager vViewPager;
    private LinearLayout vLl_dots;
    private LinearLayout vp_emoji;
    private int keyBoardState = 1;//0为键盘未弹出，1为弹出
    private InputMethodManager imm;
    private String mCuttentTitle;

    /**
     * 初始化表情资源
     */
    private void initEmojiAdapter(int emojiPage) {
        gridList.clear();
        for (int i = 0; i < emojiPage; i++) {
            gridView = (GridView) getLayoutInflater().inflate(R.layout.gridview_emoji, null);
            final List<Integer> emojiResource = caicaiList.get(i);
            final List<String> emojiResourceName = caicaiTxtList.get(i);
            mExpressionAdapter = new ExpressionAdapter(getLayoutInflater(), emojiResource);
            gridView.setAdapter(mExpressionAdapter);
            //点击表情，将表情添加到输入框中。
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    if (position != emojiResource.size()) {
                        editComment.getText().insert(editComment.getSelectionStart(), mParser.addSmileySpansReSize((emojiResourceName.get(position)), 20, 20));
                    } else {
                        int keyCode = KeyEvent.KEYCODE_DEL;
                        KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
                        editComment.dispatchKeyEvent(keyEvent);
                    }
                }
            });
            gridList.add(gridView);
        }
        vViewPager.setAdapter(new EmojiAdapter(gridList));
        gotoInitData(gridList);
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        LogUtil.e("__onScaleChange", isFullscreen + "");
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            rlToolbar.setVisibility(View.GONE);
            mTablayout.setVisibility(View.GONE);
            mTablayout.setVisibility(View.GONE);
            mViewDivider.setVisibility(View.GONE);
            mRlBottom.setVisibility(View.GONE);
            llEmojiLayout.setVisibility(View.GONE);
            //适配全面屏，改在onConfigurationChanged控制宽高了
//            ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
//            videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            videoLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            rlToolbar.setVisibility(View.VISIBLE);
            mTablayout.setVisibility(View.VISIBLE);
            mTablayout.setVisibility(View.VISIBLE);
            mViewDivider.setVisibility(View.VISIBLE);
            mRlBottom.setVisibility(View.VISIBLE);
            llEmojiLayout.setVisibility(View.VISIBLE);
            //适配全面屏，改在onConfigurationChanged控制宽高了
//            ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
//            videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            videoLayoutParams.height = cachedHeight;
        }
    }

    /**
     * 监听屏幕方向改变
     *
     * @param newConfig 配置文件配置了configChanges才会走次此回调
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //配置文件中设置 android:configChanges="orientation|screenSize|keyboardHidden" 不然横竖屏切换的时候重新创建又重新播放
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
            videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            videoLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);//显示顶部状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
            videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            videoLayoutParams.height = cachedHeight;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏不 显示顶部状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
        LogUtil.e("__onConfigurationChanged", "onConfigurationChanged");
    }

    /**
     * 暂停播放
     *
     * @param mediaPlayer
     */
    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        if (mVideoView != null) {
            mSeekPosition = mVideoView.getCurrentPosition();
            LogUtil.e("__onPause", "onPause mSeekPosition=" + mSeekPosition);
            mVideoView.pause();
            //imgStart.setVisibility(View.VISIBLE);
            img_Start.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 开始播放
     *
     * @param mediaPlayer
     */
    @Override
    public void onStart(MediaPlayer mediaPlayer) {
//        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
//            @Override
//            public void onSeekComplete(MediaPlayer mediaPlayer) {
//                Log.e("__onCompletion", "_onSeekComplete" + mediaPlayer.getCurrentPosition());
//            }
//        });
        img_Start.setVisibility(View.GONE);
        LogUtil.e("__onStart", mediaPlayer.getDuration() + "");
        //imgStart.setVisibility(View.GONE);
        //避免视频播放界面黑屏，让他先播放1秒
//        if (isSetBg){
////            delayHandler.postDelayed(new Runnable() {
////                @Override
////                public void run() {
//
//                    mVideoView.pause();
////                }
////            }, 1000);
//        }else if(isSetBg == true){
//            mVideoView.start();
//        }
    }

    /**
     * 开始缓存
     *
     * @param mediaPlayer
     */
    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        //imgStart.setVisibility(View.GONE);
    }

    /**
     * 结束缓存
     *
     * @param mediaPlayer
     */
    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        //imgStart.setVisibility(View.GONE);
    }

    /*
     * 请求成功
     * */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        LogUtil.e("sssss","请求成功"+reqType);
        VideoUrlModel videoUrlModel;
        LogUtil.e("sssss",subjectIntroduceDta+"");

        switch (reqType) {
            case Constants.REQUEST_GET_VIDEO_URL://视频rul
                videoUrlModel = (VideoUrlModel) msg.obj;
                mVideoUrl = videoUrlModel.getData().getUrl();
                LogUtil.e("__REQUEST_GET_VIDEO_URL",mVideoUrl);
                mVideoView.setVideoPath(videoUrlModel.getData().getUrl());
                mVideoView.seekTo(3000);//设置播放位置在3秒的地方,展示视频的一个画面，避免播放器黑屏
                int netState = InternetUtil.getNetworkState(this);
                //判断有没有使用wifi
                if (netState != InternetUtil.NETWORN_WIFI && netState != InternetUtil.NETWORN_NONE) {
                    Toast.makeText(this, "你正在使用手机流量", Toast.LENGTH_LONG).show();
                    mIOSAlertDialog = new IOSAlertDialog(this).builder();
                    mIOSAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
                    mIOSAlertDialog.setMsg("你正在使用手机流量").setTitle("", R.color.black).setNegativeButton("取消", R.color.bg_gray5, null).setPositiveButton("继续", R.color.btnblue, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //UserManager.setIsPermitMoveNet(true);
                            mVideoView.start();
                            //startActivity(new Intent(VideoPlayActivity.this,VipCenterActivity.class));
                        }
                    }).show();

                } else if (netState == InternetUtil.NETWORN_WIFI && netState != InternetUtil.NETWORN_NONE) {
                    ToastUtil.showToast("你正在使用wifi");
                    mVideoView.start();
                }

                break;
            case Constants.REQUEST_GET_VIDEO_URL_AND_PLAY:
                videoUrlModel = (VideoUrlModel) msg.obj;
                mVideoUrl = videoUrlModel.getData().getUrl();
                mVideoView.setVideoPath(videoUrlModel.getData().getUrl());
                mVideoView.start();

                break;
            case Constants.REQUEST_SUBMIT_COMMENT:
                ToastUtil.showToast("您的评论正在审核中，请耐心等待");
                ToastUtil.showToast("您的评论正在审核中，请耐心等待");
                //刷新学员评论列表
                if (fm3 != null) {
                    fm3.reGetStudentComment();
                }
                editComment.setText("");
                break;
            case Constants.REQUEST_GET_USER_INFO:
                UserInfoModel loginModel = (UserInfoModel) msg.obj;
                UserInfoModel.Databean databean1 = loginModel.getData();
                setUserInfo(databean1);
                break;
            case Constants.REQUEST_ADD_CONLLECTION:
                ToastUtil.showToast("收藏成功");
                mIsCollection = 1;
                mIvCollection.setSelected(true);
                mTvCollect.setText("已收藏");
                break;
            case Constants.REQUEST_DEL_CONLLECTION:
                ToastUtil.showToast("已取消收藏");
                mIsCollection = 0;
                mIvCollection.setSelected(false);
                mTvCollect.setText("收藏");
                break;
            case Constants.REQUEST_SHARE:
                LogUtil.e("__videoAct", "share_success");
                break;
        }

    }

    /**
     * 设置用户信息
     *
     * @param databean
     */
    private void setUserInfo(UserInfoModel.Databean databean) {
        UserManager.getInstance().setToken(databean.getToken());
        UserManager.getInstance().setHeadImgUrl(databean.getHeadimgurl());
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
        }
        if (fm2 != null) {
            fm2.reFreshAdapter();
        }
        LogUtil.e("__setUserInfo_activity_isvip", String.valueOf(UserManager.getInstance().getIsVip()));
        LogUtil.e("__setUserInfo_activity_viptime", String.valueOf(UserManager.getInstance().getVipTime()));
        LogUtil.e("__setUserInfo_activity_currenttime", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
    }

    @Override
    public void onHttpError(int reqType, String error) {
        LogUtil.e("sssss","请求失败"+reqType);
        switch (reqType) {
            case Constants.REQUEST_SUBMIT_COMMENT:
                ToastUtil.showToast("提交评论失败，请重试");
                break;
            case Constants.REQUEST_SHARE:
                LogUtil.e("__videoAct", "share_error");
                break;
            case Constants.REQUEST_GET_VIDEO_URL:
                LogUtil.e("__jsonError"+reqType, error);
                break;
        }
        if (subjectIntroduceDta.getCourse_introduce().equals("")) {
            mIOSAlertDialog = new IOSAlertDialog(this).builder();
            mIOSAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
            mIOSAlertDialog.setMsg("该版本下 暂无课程").setTitle("当前年级", R.color.black).setNegativeButton("返回首页", R.color.bg_gray5, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(VideoPlayActivity.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }).setPositiveButton("前往开通", R.color.btnblue, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(VideoPlayActivity.this, UserInfoActivity.class));

                }
            }).show();
        }
    }


    /**
     * 表情适配器
     */
    public class EmojiAdapter extends PagerAdapter {
        private List<GridView> list;

        public EmojiAdapter(List<GridView> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null && list.size() > 0) {
                return list.size();
            } else {
                return 0;
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((GridView) object);
        }

        @Override
        public GridView instantiateItem(ViewGroup container, int position) {
            GridView GridView = list.get(position);
            container.addView(GridView);
            return GridView;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    /**
     * 初始表情布局下底部圆点
     *
     * @param list
     */
    private void gotoInitData(List<GridView> list) {
        vLl_dots.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(activity);
            if (i == 0) {
                imageView.setImageResource(R.drawable.shape_dot_select);

            } else {
                imageView.setImageResource(R.drawable.shape_dot_nomal);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SmileyParser.dp2px(activity, 8), SmileyParser.dp2px(activity, 8));
            layoutParams.setMargins(20, 0, 0, 0);
            vLl_dots.addView(imageView, layoutParams);

        }
        if (vLl_dots.getChildCount() <= 1) {
            vLl_dots.setVisibility(View.GONE);
        } else {
            vLl_dots.setVisibility(View.VISIBLE);
        }
        vViewPager.setOffscreenPageLimit(6);
        vViewPager.setCurrentItem(0);
        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < vLl_dots.getChildCount(); i++) {
                    if (i != position) {
                        ((ImageView) vLl_dots.getChildAt(i)).setImageResource(R.drawable.shape_dot_nomal);
                    }
                }
                ((ImageView) vLl_dots.getChildAt(position)).setImageResource(R.drawable.shape_dot_select);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 把lista按固定长度分割成若干list
     *
     * @param dataList
     * @param length   每个集合长度
     * @return
     */
    public static List<List<Integer>> splitList(List<Integer> dataList, int length) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i = i + length) {
            int j = i + length;
            if (j > dataList.size()) {
                j = dataList.size();
            }

            List<Integer> insertList = dataList.subList(i, j);
            if (insertList.size() == 0) {
                break;
            }
            lists.add(insertList);
        }
        return lists;
    }

    /**
     * 把lista按固定长度分割成若干list
     *
     * @param dataList
     * @param length   每个集合长度
     * @return
     */
    public static List<List<String>> splitStringList(List<String> dataList, int length) {
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i = i + length) {
            int j = i + length;
            if (j > dataList.size()) {
                j = dataList.size();
            }
            List<String> insertList = dataList.subList(i, j);
            if (insertList.size() == 0) {
                break;
            }
            lists.add(insertList);
        }
        return lists;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_submit://确定
//                Toast.makeText(activity, mParser.addSmileySpans(editComment.getText()), Toast.LENGTH_SHORT).show();
                LogUtil.e("__", "提交");
                String content = editComment.getText().toString().replace(" ", "");
                if (content.length() < 5) {
                    ToastUtil.showToast("评论不能小于5个字符");
                    return;
                }
                if (!UserManager.getInstance().getIsVip()) {
                    ToastUtil.showToast("开通VIP会员才能评论哦");
                    return;
                }
                mHttpHelper.submitComment(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), content, course_id);
                keyBoardState = 0;
                imm.hideSoftInputFromWindow(editComment.getWindowToken(), 0);
                vp_emoji.setVisibility(View.GONE);
                break;
            case R.id.iv_emojie://表情
                editComment.setFocusable(true);
                editComment.setFocusableInTouchMode(true);
                editComment.requestFocus();
                if (keyBoardState == 0) {//弹出键盘
                    showSoftInput();
                } else {//关闭键盘
                    hideSoftInput();
                }
                break;
            case R.id.rl_toolbar:
            case R.id.ll_upper_layout:
                keyBoardState = 0;
                imm.hideSoftInputFromWindow(editComment.getWindowToken(), 0);
                vp_emoji.setVisibility(View.GONE);
                break;
            case R.id.fl_back:
                finish();
                break;
            case R.id.ll_customer_service:
                intent = new Intent(this, CustomerServiceCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_collection:
                //现在3秒后才能点击
                mLlCollection.setClickable(false);
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLlCollection.setClickable(true);
                    }
                }, 3000);
                if (mIsCollection == 0) {
                    mHttpHelper.addConllection(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), course_id);
                } else {
                    mHttpHelper.delConllection(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), course_id);
                }
                break;
            case R.id.tv_buy:
                LogUtil.e("__", "back");
                intent = new Intent(this, VipCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_share:
                showShare();//分享界面
//                if (MainApp.getInstance().getLelinkServiceManager() != null){
//                    MainApp.getInstance().getLelinkServiceManager().setOnBrowseListener(browserListener);
//                    MainApp.getInstance().getLelinkServiceManager().browse(ILelinkServiceManager.TYPE_ALL);
//                    mVideoView.pause();
//                    showProgressDialog("搜索设备中");
//                }else {
//                    ToastUtil.showToast("投屏初始化失败，请重试");
//                    MainApp.getInstance().initLeLink();
//                }
                for (int i = 0; i < 3; i++) {
//                    if(mCDialog == null){
//                        mCDialog = new CreateStudyPlanDialog(VideoPlayActivity.this).builder();
//                        mCDialog.setOnPopupItemClickListener(new CreateStudyPlanDialog.OnPopupItemClickListener() {
//                            @Override
//                            public void onPopUpItemClick(int position, Calendar calendar) {
//
//                            }
//                        });
//                        mCDialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
//
//                            @Override
//                            public void onDismiss(DialogInterface dialogInterface) {
//                                mIsCDiaShowing = false;
//                            }
//                        });
//                        mIsCDiaShowing = true;
//                        mCDialog.show();
//                    }else {
//                        if (mIsCDiaShowing){
////                            mIsCDiaShowing = true;
//                        }else {
//                            mIsCDiaShowing = true;
//                            mCDialog.show();
//                        }
//                    }
                }
                break;
            case R.id.iv_thr_screen://投屏点击
                if (TextUtils.isEmpty(mVideoUrl)){
                    ToastUtil.showToast("获取URL失败,请重试");
                }else {
                    intent = new Intent(this,ThrScreenActivity.class);
                    intent.putExtra("url",mVideoUrl);
                    startActivity(intent);
                    mVideoView.pause();
                }
                break;
            case R.id.iv_question_mark:
                showChangVersionDia();
                break;
            case R.id.img_start://点击暂停图标
                mVideoView.start();
                break;
            default:break;
        }
    }


//    如何更改版本
//    可在【我的】页面，点击【头像】或【昵称】，在【个人信息】里面修改教材版本即可。

    /**
     * 修改版本提示框
     */
    private void showChangVersionDia() {
        IOSAlertDialog iosAlertDialog = new IOSAlertDialog(this).builder();
        iosAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
        iosAlertDialog.setMsg("可在【我的】页面，点击【头像】或【昵称】，在【个人信息】里面修改教材版本即可。").setTitle("如何更改版本", R.color.black_333).setPositiveButton("我知道了", R.color.btnblue, null).show();
    }

    private void showSoftInput() {
        keyBoardState = 1;
        vp_emoji.setVisibility(View.GONE);
        iv_emoji.setSelected(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imm.showSoftInput(editComment, 0);
            }
        }, 50);
    }

    private void hideSoftInput() {
        keyBoardState = 0;
        imm.hideSoftInputFromWindow(editComment.getWindowToken(), 0);
        iv_emoji.setSelected(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                vp_emoji.setVisibility(View.VISIBLE);
            }
        }, 50);
    }

    /**
     * 设置置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 405f / 850.5f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
//                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new CommonMessageEvent(Constants.REQUEST_GET_MY_STUDY_LIST, ""));
        EventBus.getDefault().unregister(this);
        MainApp.getInstance().getLelinkServiceManager().stopBrowse();
    }

    //EventBus接收微信分享回调
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
//            LogUtil.e("__handleData_getType",mMessageEvent.getType() + "");
        if (mMessageEvent.getType() == Constants.REQUEST_SHARE) {
            if (mMessageEvent.getType() == Constants.REQUEST_SHARE) {
                LogUtil.e("__handleData_videoAct", Constants.REQUEST_SHARE + "");
                //发送到后台记录分享不需要回调处理
                mHttpHelper.share(null, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), UserManager.getInstance().getUserId(), CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()), 1);
            }
        }
    }

    /**
     * 展示分享界面
     */
    private void showShare() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon, options);
        String shareTitle = "格灵同步培优";//分享标题
        String content = "更多内容，敬请下载格灵同步培优";//分享内容
//        String shareUrl = "https://sj.qq.com/myapp/detail.htm?apkName=net.getlearn.getlearn_android";//应用宝下载链接
//        String shareUrl = "https://shouji.baidu.com/software/26541183.html";//百度手机助手
        String shareUrl = "https://shouji.baidu.com/software/26526059.html";//百度手机助手
        if (title != null && !title.equals("")) {
            shareTitle = title;
        }
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle(getString(R.string.share));
        oks.setTitle(shareTitle);
        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl("https://e.topthink.com/Uploads/Picture/2018-10-29/5bd6d218d381b.jpg");//网络图片
        oks.setImageData(bitmap);//bitmap图片
        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//       oks.setUrl(shareUrl);
        oks.setUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }
}
