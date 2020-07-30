package net.getlearn.getlearn_android.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.huawei.agconnect.crash.AGConnectCrash;

import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.HttpHelper;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import butterknife.ButterKnife;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/17------更新------
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,BaseHelper.IHttpCallback{
    //网络请求

    protected HttpHelper mHttpHelper = new HttpHelper();
    //一些延时操作，比如等待定位，延时，失败提示等
    protected Handler delayHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initView();
        initData();
        ActivityCollector.addActivity(this);//将活动添加到活动收集器
        MainApp.getInstance().mActivityList.add(this);
        setStatusBar();
    }

    private ProgressDialog mPDialog;
    /**
     * 显示加载提示框(可以在子线程调用)
     */
    public void showProgressDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPDialog = new ProgressDialog(BaseActivity.this);
                mPDialog.setMessage(message);
                // 点击外部时不销毁弹出窗
                mPDialog.setCanceledOnTouchOutside(false);
                mPDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
                // activity如果正在销毁或已销毁，不能show Dialog，否则出错。
                if (!isFinishing())
                    mPDialog.show();
            }
        });
        //3秒后自动取消
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        },3000);
    }

    /**
     * 展示自定义时间的等待框
     * @param message
     * @param delay
     */
    public void showCustomProgressDialog(final String message,int delay) {
        LogUtil.e("__showCustomProgressDialog",delay + "");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPDialog = new ProgressDialog(BaseActivity.this);
                mPDialog.setMessage(message);
                // 点击外部时不销毁弹出窗
                mPDialog.setCanceledOnTouchOutside(false);
                mPDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
                // activity如果正在销毁或已销毁，不能show Dialog，否则出错。
                if (!isFinishing())
                    mPDialog.show();
            }
        });
        //延迟X毫秒后自动取消
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        },delay);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止waitProgress被线程调用时，导致Activity xxxx has leaked， that was originally added here异常
        if ( mPDialog != null && mPDialog.isShowing()){
            mPDialog.cancel();
        }
        delayHandler.removeCallbacksAndMessages(null);
        MainApp.getInstance().mActivityList.remove(this);
        ActivityCollector.addActivity(this);//将活动移除活动收集器
    }



    /**
     * 展示任务提示框
     */
    public void showNotifyDia(String content){
        IOSAlertDialog exitDialog = new IOSAlertDialog(this).builder();
//                 iosAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
        exitDialog.setMsg(content).setTitle("学习任务提示").setNegativeButton("取消",null).
                setPositiveButton("前往查看", R.color.blue5, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(BaseActivity.this,StudyPlanActivity2.class));
                    }
                }).show();
    }

    /**
     * 沉浸式状态栏
     */
    protected void setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近
//        StatusBarUtil.setTransparent(this);
        // 延伸显示区域到刘海
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
//        getWindow().setAttributes(lp);
        // 设置页面全屏显示
//        final View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
    /**
     * 销毁加载提示框
     */
    public void dismissProgressDialog() {
        if (mPDialog != null) {
            mPDialog.dismiss();
            mPDialog = null;
        }
    }
    protected abstract void initData();

    /**
     * 获取界面布局
     * @return
     */
    protected abstract int getLayoutRes();
    protected abstract void initView();

    @Override
    public void onTokenError(int reqType, String error) {
        SPUtil.clear();
        ToastUtil.showToast("登录信息失效，请重新登录");
        Intent intent = new Intent(this, GetVerificationCodeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
