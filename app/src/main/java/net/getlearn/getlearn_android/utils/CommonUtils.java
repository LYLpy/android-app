package net.getlearn.getlearn_android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import net.getlearn.getlearn_android.model.IHttpService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/26------更新------
 * 公共工具类，常用的方法放这里
 */

public class CommonUtils {

    /**
     * 获取签名值
     * @param map
     * @return
     */

    /**
     * 将参数集合升序排序并且获取签名
     * @param map
     * @return
     */
    public static String getSign(TreeMap<String,Object> map){
        StringBuilder signBuilder = new StringBuilder();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            //it.next()得到的是key，tm.get(key)得到obj
//            System.out.println(map.get(it.next()));
            String temp = it.next().toString();
            //map.get(temp).equals("0")与后台签名算法保持一致，值为0不参与签名运算
            if (map.get(temp) != null && !map.get(temp).equals("") && !map.get(temp).toString().equals("0")){
                signBuilder.append(temp);
                signBuilder.append("=");
                signBuilder.append(map.get(temp));
                signBuilder.append("&");
                LogUtil.e("__getSign",signBuilder+"");
            }
        }
        signBuilder.append("key=");
        signBuilder.append(IHttpService.KEY);
        LogUtil.e("__getSign",signBuilder.toString());
        String md5 = md5(signBuilder.toString()).toUpperCase();

        LogUtil.e("__md5_up",md5);
        return md5;
    }

    /**
     * 获取时间戳String
     * @return
     */
    public static String getTimeStampStr(){
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static int getTimeStampInt(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
//            LogUtil.e("__getTimeStampInt",String.valueOf(Integer.valueOf(timestamp.substring(0,length-3))));
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static int getCurrentTimeStampInt(){
        return getTimeStampInt(new Date());
    }

    /**
     * 获取MD5值
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
//            LogUtil.e("__md5",result);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param name 姓名
     * @param password 密码
     * @param parm   测试参数
     * @param sex      性别
     * @return
     */
    public static String getStudyIndexSign(String name,String password,int parm,int sex,int timestamp){
        TreeMap<String,Object> map = new TreeMap<>();
        map.put("name",name);
        map.put("password",password);
        map.put("sex",sex);//性别
        map.put("timestamp",timestamp);//时间戳
        map.put("parm",parm);
        return getSign(map);
    }

    /**
     * 通过两个date判断是否同一天
     * @param date
     * @param sameDate
     * @return
     */
    public static boolean isSameDay(Date date, Date sameDate) {
        if (null == date || null == sameDate) {
            return false;
        }
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(sameDate);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        if (nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)
                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)
                && nowCalendar.get(Calendar.DATE) == dateCalendar.get(Calendar.DATE)) {
            return true;
        }
        return false;
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 判断当前线程是否是主线程
     * @return true表示当前是在主线程中运行
     */
    public static boolean isMainThread() {
        // 主线程的Looper == 当前线程的Looper
        return Looper.getMainLooper() == Looper.myLooper();
    }
    /** 在主线程运行 */
    public static void runOnMainThread(Runnable run) {
        if (isMainThread()) {
            run.run();
        } else {
            mHandler.post(run);
        }
    }
    public static Handler getMainHandler() {
        return mHandler;
    }

    /**
     * 检查是否有网络
     * @param context
     * @return
     */
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            NetworkInfo netInfo = connManager.getActiveNetworkInfo();
            if (netInfo != null){
                return netInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     * 时间戳转字符串
     * @param milSecond 时间戳
     * @param pattern 日期格式，如"yyyy年MM月dd日 HH:mm:ss"
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    /**
     * 将毫秒转时分秒
     *
     * @param time
     * @return
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02dh%02dm%02ds", hours, minutes, seconds) : String.format("%02dm%02ds", minutes, seconds);
    }

    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String channelNumber = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelNumber;
    }

    /*注：上面所说的key指的是清单文件中你在注册友盟统计时的"UMENG_CHANNEL"（即getChannelNumber(Context context, String key)方法的第二个参数key传入"UMENG_CHANNEL"），而不是"UMENG_APPKEY"，也不是任何一个value值
            <!-- 友盟统计 -->
    <meta-data
    android:name="UMENG_APPKEY"
    android:value="*****************" />
    <meta-data
    android:name="UMENG_CHANNEL"
    android:value="${UMENG_CHANNEL_VALUE}" />*/
    /**
     * 在需要的地方调用上述方法
     * String channelNumber = getAppMetaData(getBaseContext(), "UMENG_CHANNEL");//获取app当前的渠道号
     */

}
