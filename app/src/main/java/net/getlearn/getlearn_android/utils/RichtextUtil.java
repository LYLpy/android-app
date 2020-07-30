package net.getlearn.getlearn_android.utils;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 * 富文本工具类
 */

public class RichtextUtil {
    //红色
    public static String getRed(String source){
        return String.format("<font color='#FF0000'>%s</font>", source);
    }
    //绿色
    public static String getGreen(String source){
        return String.format("<font color='#00FF00'>%s</font>", source);
    }
    //蓝色
    public static String getBlue(String source){
        return String.format("<font color='#037BFF'>%s</font>", source);
    }
    //灰色
    public static String getGray(String source){
        return String.format("<font color='#9f9f9f'>%s</font>", source);
    }
    //黑色
    public static String getBlack(String source){
        return String.format("<font color='#000000'>%s</font>", source);
    }
}
