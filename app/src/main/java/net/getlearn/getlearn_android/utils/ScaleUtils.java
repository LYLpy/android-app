package net.getlearn.getlearn_android.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;

import net.getlearn.getlearn_android.MainApp;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/10------更新------
 */

public class ScaleUtils {

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static float getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    //dp转px
    public static int dip2px( float dpValue) {
        final float scale = MainApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //px转dp
    public static int px2dip(int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, MainApp.getContext().getResources().getDisplayMetrics()));
    }
}
