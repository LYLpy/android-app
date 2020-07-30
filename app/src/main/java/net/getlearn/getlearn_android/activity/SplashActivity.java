package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.utils.LogUtil;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/1------更新------
 * APP启动界面
 */

public class SplashActivity extends BaseActivity{


    private static final int GO_HOME = 1000;
    private static final int GO_LOGIN = 1001;
    private static final int GO_SELECT_GRADE = 1002;
    // 延迟2秒
    private static final long SPLASH_DELAY_MILLIS = 2000;
    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_LOGIN:
                    goLogin();
                    break;
                case GO_HOME:
                    goHome();
                    break;
                case GO_SELECT_GRADE:
                    goSelectGrade();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void initData() {
        LogUtil.e("__splash_phone",UserManager.getInstance().getUserPhone());
//        if (UserManager.getInstance().getToken().equals("") || UserManager.getInstance().getUserPhone().equals("")){
        if (UserManager.getInstance().getToken().equals("") ){
            mHandler.sendEmptyMessageDelayed(GO_LOGIN, SPLASH_DELAY_MILLIS);
        }else if(UserManager.getInstance().getUserSelectedVersion().size()==0){
            mHandler.sendEmptyMessageDelayed(GO_SELECT_GRADE, SPLASH_DELAY_MILLIS);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
    private void goLogin(){
        startActivity(new Intent(this,GetVerificationCodeActivity.class));
        finish();
    }
    private void goHome(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    private void goSelectGrade(){
        startActivity(new Intent(this,SelectGradeActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
