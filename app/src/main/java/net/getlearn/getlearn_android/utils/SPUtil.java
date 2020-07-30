package net.getlearn.getlearn_android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.model.bean.UserSelectedVersionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * SP简单数据保存工具类
 */
public class SPUtil {

    private static Context mContext;
    /**
     * @param context
     */
    public static void init(Context context) {
        if (null == context) {
            throw new IllegalArgumentException("mContext cannot be null.");
        }
        mContext = context.getApplicationContext();
    }
    // 参数存放的文件名 .xml
    private static final String CONFIG_FILE_NAME = "config";

    public static String getString(String key, String defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_FILE_NAME, //
                Context.MODE_PRIVATE);// 文件模式
        return sp.getString(key, defValue);
    }

    public static void saveString(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_FILE_NAME, Context
				.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void saveBoolean(String key, Boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(//
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_FILE_NAME, Context
				.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void saveInt(String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static Long getLong(String key, Long defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void saveLong(String key, Long value) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static void clear() {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }


    public static String tagGradeList = "gradeList";
    public static String tagUserSelectedVersionList = "userSelectedVersionList";
    public static String tagStudyPlanModel = "userStudyPlanModel";
    /**
     * 保存GradeModel.Databean的List
     * @param datalist
     */
    public static void saveGradeList(List<GradeModel.Databean> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        SharedPreferences sp = mContext.getSharedPreferences(//
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        Editor editor = sp.edit();
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.putString(tagGradeList, strJson);
        editor.commit();
    }
    /**
     * 获取GradeModel.Databean的List
     * @return
     */
    public static  List<GradeModel.Databean> getGradeList() {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        List<GradeModel.Databean> datalist = new ArrayList<>();
        String strJson = sp.getString(tagGradeList, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<GradeModel.Databean>>() {
        }.getType());
        return datalist;
    }

    /**
     * 保存用户选择科目版本的信息
     * @param datalist
     */
    public static void saveUserSelectedVersion(List<UserSelectedVersionModel.Databean> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        SharedPreferences sp = mContext.getSharedPreferences(//
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        Editor editor = sp.edit();
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.putString(tagUserSelectedVersionList, strJson);
        editor.commit();
    }
    /**
     * 获取用户选择科目版本的信息
     * @return
     */
    public static  List<UserSelectedVersionModel.Databean> getUserSelectedVersion() {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        List<UserSelectedVersionModel.Databean> datalist = new ArrayList<>();
        String strJson = sp.getString(tagUserSelectedVersionList, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<UserSelectedVersionModel.Databean>>() {
        }.getType());
        return datalist;
    }

    /**
     * 保存今日任务
     * @param datalist
     */
    public static void saveUserTodayPlans(List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        SharedPreferences sp = mContext.getSharedPreferences(//
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        Editor editor = sp.edit();
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.putString(tagStudyPlanModel, strJson);
        editor.commit();
    }
    /**
     * 获取今日任务
     * @return
     */
    public static  List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> getUserTodayPlans() {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context .MODE_PRIVATE);
        List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> datalist = new ArrayList<>();
        String strJson = sp.getString(tagStudyPlanModel, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean>>() {
        }.getType());
        return datalist;
    }

}
