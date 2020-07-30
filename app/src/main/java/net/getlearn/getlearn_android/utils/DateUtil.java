package net.getlearn.getlearn_android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/5------更新------
 * Date工具类
 */

public class DateUtil {

    /**
     * @param time 单位：毫秒
     * @param format 时间格式
     * @return
     */
    public static String timeStamp2Date(int time, String format) {
        LogUtil.e("__timeStamp2Date",time + "");
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time  * 1000L));
    }


    /**
     * 将String格式的时间转换成Date
     * @param strTime 时间
     * @param formatType 格式
     * @return
     */
    public static Date stringToDate(String strTime, String formatType){
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        }catch (Exception e){

        }

        return date;
    }
}
