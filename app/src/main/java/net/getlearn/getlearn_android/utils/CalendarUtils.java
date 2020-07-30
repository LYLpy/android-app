package net.getlearn.getlearn_android.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/28------更新------
 * 日历工具类
 */

public class CalendarUtils {

    /**
     * 传入一个Calendar对象，获取今天是周几
     * @param c
     * @return
     */
    public static String getDayOfWeekChinese(Calendar c){
        String way  = String.valueOf(c.get(Calendar.DAY_OF_WEEK));;
        if("1".equals(way)){
            way ="周日";
        }else if("2".equals(way)){
            way ="周一";
        }else if("3".equals(way)){
            way ="周二";
        }else if("4".equals(way)){
            way ="周三";
        }else if("5".equals(way)){
            way ="周四";
        }else if("6".equals(way)){
            way ="周五";
        }else if("7".equals(way)){
            way ="周六";
        }
        LogUtil.e("__date_getDayOfWeekChinese",way);
        return way;
    }

    /**
     * 获取今天之后多少天的Calendar对象
     * @param after
     * @return
     */
    public static Calendar getDayOfterToday(int after){
        Calendar calendr = Calendar.getInstance();
        calendr.add(Calendar.DAY_OF_MONTH, after);
        return calendr;
    }

    /**
     * 获取某个Calendar多少天后的时间
     * @param after
     * @return
     */
    public static Calendar getDayOfterCalendar(Calendar calendr,int after){
        calendr.add(Calendar.DAY_OF_MONTH, after);
        return calendr;
    }

    /**
     * 获取某个Calendar多少分钟后的时间
     * @param calendr
     * @param afterMinute
     * @return
     */
    public static Calendar getMinuteAfterCalendar(Calendar calendr,int afterMinute){
        calendr.add(Calendar.MINUTE, afterMinute);
        return calendr;
    }

    /**
     * 获取某个Calendar多少小时后的时间,24小时制
     * @param calendr
     * @param afterHour
     * @return
     */
    public static Calendar getHourAfterCalendar(Calendar calendr,int afterHour){
        calendr.add(Calendar.HOUR_OF_DAY, afterHour);
        return calendr;
    }

    /**
     * 获取某个Calendar的中文X月X日String
     * @param calendar        Calendar对象
     * @return
     */
    public static String getMonthDayStrChinese(Calendar calendar){
        StringBuilder sb = new StringBuilder();
        int YYYY = calendar.get(Calendar.YEAR) ;
        int MM = calendar.get(Calendar.MONTH)+1;
        int DD = calendar.get(Calendar.DATE);
//        int HH = calendar.get(Calendar.HOUR);//12小时制的时间
        int HH = calendar.get(Calendar.HOUR_OF_DAY);//24小时制的时间
        int NN = calendar.get(Calendar.MINUTE);
        int SS = calendar.get(Calendar.SECOND);
//        sb.append(YYYY);
//        sb.append("年");
        sb.append(MM);
        sb.append("月");
        sb.append(DD);
        sb.append("日");
//        sb.append(" ");
//        sb.append(HH);
//        sb.append(":");
//        sb.append(NN);

//        LogUtil.e("__date_YYYY",YYYY + "");
//        LogUtil.e("__date_MM",MM + "");
//        LogUtil.e("__date_DD",DD + "");
//        LogUtil.e("__date_HH",HH + "");
//        LogUtil.e("__date_NN",NN + "");
//        LogUtil.e("__date_SS",SS + "");
        LogUtil.e("__date_getMonthDayStrChinese",sb.toString());
        return sb.toString();
    }

    /**
     * 获取XX:XX格式时间
     * @param calendar
     * @return
     */
    public static String getTime(Calendar calendar){
        StringBuilder sb = new StringBuilder();
        int HH = calendar.get(Calendar.HOUR_OF_DAY);//24小时制的时间
        int NN = calendar.get(Calendar.MINUTE);
        sb.append(HH);
        sb.append(":");
        if (NN < 10){
            sb.append("0");
        }
        sb.append(NN);
        LogUtil.e("__date_getTime",sb.toString());
        return sb.toString();
    }

    /**
     * 获取XXXX-XX-XX格式日期
     * @param calendar
     * @return
     */
    public static String getTimeYearMonthDayStr(Calendar calendar){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = sf.format(calendar.getTime());
//        LogUtil.e("__date_getTimeYearMonthDayStr",timeStr);
        return timeStr;
    }

    /**
     * 复制Calendar对象
     * @param calendar     原Calendar
     * @param copy 复制品Calendar
     */
    public static Calendar copyCalendar(Calendar calendar,Calendar copy){
        if (copy == null){
            copy = Calendar.getInstance();
        }
        copy.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
        copy.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
        copy.set(Calendar.DATE,calendar.get(Calendar.DATE));
        copy.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY));
        copy.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE));
        return copy;
    }
}
