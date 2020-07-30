package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cslibrary.CrazyShadow;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.fragment.main.HomeFragment;
import net.getlearn.getlearn_android.fragment.main.MineFragment;
import net.getlearn.getlearn_android.fragment.main.SelectSubjcetFragment;
import net.getlearn.getlearn_android.fragment.main.StudyFragment1;
import net.getlearn.getlearn_android.model.bean.GetVersion;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.service.NotifyPlanService;
import net.getlearn.getlearn_android.utils.APKVersionCodeUtils;
import net.getlearn.getlearn_android.utils.BrandUtil;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends BaseActivity {

    /*底部导航栏4个按钮*/
    RelativeLayout rl1;
    RelativeLayout rl2;
    RelativeLayout rl3;
    RelativeLayout rl4;

    /*底部导航栏文字*/
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    FragmentManager fmManager = getSupportFragmentManager();

    private FragmentTransaction transaction;

    HomeFragment fm1 = new HomeFragment();
    SelectSubjcetFragment fm2 = new SelectSubjcetFragment();
    StudyFragment1 fm3 = new StudyFragment1();
    MineFragment fm4 = new MineFragment();

    private StudyPlanModel.DatabeanX mData;
    private int page = 1;
    private int  per_page = 100;
    private int requestTimes;
    private CrazyShadow crazyShadow;
    private LinearLayout mLlTabBar;
    private IOSAlertDialog mIOSAlertDialog;
    private int versionCode;
    private String versionName;
    @Override
    protected void initData() {

        versionCode = APKVersionCodeUtils.getVersionCode(this) ;
        //判断有没有可以更新版本
        versionName = APKVersionCodeUtils.getVerName(this);
        LogUtil.e("__json75versionName",""+versionName);
        LogUtil.e("__json75versionCode",""+versionCode);
        mHttpHelper.getVersion(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());

        UserManager.getInstance().setUserTodayPlan(new ArrayList<>());
        mHttpHelper.getStudyPlan(this, CommonUtils.getCurrentTimeStampInt(),
                UserManager.getInstance().getToken(),
                page,per_page, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));

        startService(new Intent(this, NotifyPlanService.class));//开启任务提示服务
        fm1.setOnRecommendMoreClickListener(new HomeFragment.OnRecommendMoreClickListener() {
            @Override
            public void onRecommendMoreClick() {
                rl2.performClick();
            }
        });

//                crazyShadow = new CrazyShadow.Builder()
//                .setContext(this)
//                .setDirection(CrazyShadowDirection.TOP)
//                .setShadowRadius(10)
//                .setBaseShadowColor(getResources().getColor(R.color.bg_gray3))
//                .setImpl(CrazyShadow.IMPL_DRAW)
//                .action(mLlTabBar);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

//        setStatusBar();
        EventBus.getDefault().register(this);
        transaction = fmManager.beginTransaction();
        /*底部导航栏4个按钮*/
        rl1 = findViewById(R.id.tab_1);
        rl2 = findViewById(R.id.tab_2);
        rl3 = findViewById(R.id.tab_3);
        rl4 = findViewById(R.id.tab_4);

        tv1 = findViewById(R.id.menu_tv_1);
        tv2 = findViewById(R.id.menu_tv_2);
        tv3 = findViewById(R.id.menu_tv_3);
        tv4 = findViewById(R.id.menu_tv_4);

        /*设置监听*/
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);

        /*设置监听*/
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);

        mLlTabBar = findViewById(R.id.ll_tab_bar);
        /*设置首页选中*/
        rl1.setSelected(true);
        tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        /*添加4个Fragment*/
        transaction.add(R.id.main_fl, fm1)
                .add(R.id.main_fl, fm2)
                .add(R.id.main_fl, fm3)
                .add(R.id.main_fl, fm4);
        //隐藏另外三个fragment,只保留首页
        transaction.hide(fm2)
                .hide(fm3)
                .hide(fm4)
                .commit();
    }


    @Override
    public void onClick(View view) {
        transaction = fmManager.beginTransaction();
        switch (view.getId()) {
            /*底部导航栏*/
            case R.id.tab_1:
                resetTab();
                rl1.setSelected(true);
                tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                resetFragment(fm1);
                break;
            case R.id.tab_2:
                resetTab();
                rl2.setSelected(true);
                tv2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                resetFragment(fm2);
                break;
            case R.id.tab_3:
                resetTab();
                rl3.setSelected(true);
                tv3.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                resetFragment(fm3);
                break;
            case R.id.tab_4:
                resetTab();
                rl4.setSelected(true);
                tv4.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                resetFragment(fm4);
                break;
        }
    }

    private void resetTab() {
        /*重置底部导航栏4个按钮，全部设置为不选中*/
        rl1.setSelected(false);
        rl2.setSelected(false);
        rl3.setSelected(false);
        rl4.setSelected(false);

        tv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv4.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }

    private void resetFragment(Fragment fm) {
        transaction = fmManager.beginTransaction();
        transaction.hide(fm1)
                .hide(fm2)
                .hide(fm3)
                .hide(fm4)
                .commit();
        transaction = fmManager.beginTransaction();
        transaction.show(fm);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (!fm1.onBackPressed() && !fm2.onBackPressed()){
            super.onBackPressed();
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_VERSION:
                GetVersion getVersion = (GetVersion) msg.obj;
                int code_id = getVersion.getData().getVersion_code();
                String code_name = getVersion.getData().getVersion_name();
                LogUtil.e("__onHttpSuccess_versionCode",versionCode + "");
                LogUtil.e("__onHttpSuccess_code_id",code_id + "");
                LogUtil.e("__onHttpSuccess_versionName",versionName + "");
                LogUtil.e("__onHttpSuccess_code_name",code_name + "");
//                if (versionCode != code_id || !versionName.equals(code_name)) {
                if ( versionCode < code_id ) {
                    mIOSAlertDialog = new IOSAlertDialog(this).builder();
                    mIOSAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
                    mIOSAlertDialog.setMsg("当前版本名为" + versionName + "可升级版本为" + code_name + "有新版可更新").setTitle("", R.color.black).setNegativeButton("取消", R.color.bg_gray5, null).setPositiveButton("更新", R.color.btnblue, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            BrandUtil brandUtil = new BrandUtil(MainActivity.this);
                            brandUtil.getBrand();
                        }
                    }).show();
                }
                break;
            case Constants.REQUEST_GET_STUDY_PLAN:
                StudyPlanModel studyPlanModel = (StudyPlanModel) msg.obj;
                mData = studyPlanModel.getData();
                if (mData.getAppStudyPlanList() != null && mData.getAppStudyPlanList().getData() != null && mData.getAppStudyPlanList().getData().size() > 0) {
                    UserManager.getInstance().setUserTodayPlan(mData.getAppStudyPlanList().getData());
                }
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_CREATE_STUDY_PLAN:
                //请求失败则待会1分钟继续请求,超过10次失败则不再请求
                if (requestTimes < 10){
                    delayHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mHttpHelper.getStudyPlan(MainActivity.this, CommonUtils.getCurrentTimeStampInt(),
                                    UserManager.getInstance().getToken(),
                                    page,per_page, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
                        }
                    }, 60 * 1000);
                }
                requestTimes ++;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //EventBus接收挑战中心要求跳转到选课的通知
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
//            LogUtil.e("__handleData_getType",mMessageEvent.getType() + "");
        if (mMessageEvent.getType() == Constants.REQUEST_GET_CLASSIFY) {
            LogUtil.e("__handleData_mainAct_getClass", Constants.REQUEST_GET_CLASSIFY + "");
            rl2.performClick();
//            LogUtil.e("__handleData_getContent",mMessageEvent.getContent());
//            mHttpHelper.WXLogin(this,CommonUtils.getCurrentTimeStampInt(),mMessageEvent.getContent());
        }else if (mMessageEvent.getType() == Constants.REQUEST_CREATE_STUDY_PLAN){
            LogUtil.e("__handleData_mainAct_createSP", Constants.REQUEST_GET_CLASSIFY + "");
            mHttpHelper.getStudyPlan(this, CommonUtils.getCurrentTimeStampInt(),
                    UserManager.getInstance().getToken(),
                    page,per_page, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
        }
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
//        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
//        StatusBarUtil.setColor(this,getResources().getColor(R.color.white));
    }
}
