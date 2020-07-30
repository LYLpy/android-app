package net.getlearn.getlearn_android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import net.getlearn.getlearn_android.MainApp;


/**
 * Toast管理类
 * Created by Weiss on 2016/11/7.
 */

public class ToastUtil {

    private static Context mContext = MainApp.getContext();
    private static Toast mToast;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static boolean  isShowing;//判断是否正在展示Toast，避免短时间内连续弹出
    private static void initToast(Context context,String msg) {
        mToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        mToast.setText(msg);
    }
    public static void showToast(final String msg) {
        //未初始化，则进行初始化
        if (mToast == null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                initToast(mContext, msg);
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initToast(mContext, msg);
                    }
                });
            }
        }
        if (isShowing){
            return;
        }
        isShowing = true;
        //判断当前代码是否是主线程
        if (Looper.myLooper() == Looper.getMainLooper()) {
            mToast.setText(msg);
            mToast.show();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mToast.setText(msg);
                    mToast.show();
                }
            });
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               isShowing = false;
            }
        }, 3000);
    }
}
