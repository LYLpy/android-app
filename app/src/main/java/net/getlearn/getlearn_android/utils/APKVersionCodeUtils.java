package net.getlearn.getlearn_android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import net.getlearn.getlearn_android.activity.AoePPTActivity;

/*
 * apk的版本号和名称
 * */
public class APKVersionCodeUtils {
    //获取当前本地apk的版本
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }
    public static String getVerName (Context context){
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     *
     * @param context
     * @return
     */
    public static synchronized String getAppNane(Context context){
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param context
     * @param key_channel 获取当前apk来源的渠道
     * @return
     */
    public static synchronized String getAPK_UMENG_CHANNEL_VALUE(Context context,String key_channel){

        String UMENG_CHANNEL = null;
        try {
            ApplicationInfo applicationInfo= context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
            if (applicationInfo != null){
                if (applicationInfo.metaData!= null){
                    UMENG_CHANNEL = applicationInfo.metaData.getString(key_channel);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return UMENG_CHANNEL;

    }

}
