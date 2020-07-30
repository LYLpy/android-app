package net.getlearn.getlearn_android;

import android.content.Context;

import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.model.bean.UserSelectedVersionModel;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/18------更新------
 * 用于记录用户信息、状态，比如已选年级，课程等
 */

public class UserManager {
    private static boolean isPermitMoveNet = false;//是否允许移动网络播放视频
    /*
    * 微信登录需要的三个参数，要从后台获取
    * */
    private String scope = "";
    private String state = "";
    private String app_id = "";
    //获取单例
    public static UserManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserManager INSTANCE = new UserManager();
    }
    //是否需要登录
    public static void isSignTokenIntentActivity(Context context){
        if (isSignTokenNULL()==false){
            IntentsUtlis.starSignInActivity(context);
        }
    }
    //判断 token是否为空
    public static boolean isSignTokenNULL(){
        //true = 不为空   fales = 空
        boolean tokenNullBoolean =true;
//        if ( (UserManager.getToken() == null||UserManager.getToken().equals(""))){
//            tokenNullBoolean =false;
//        }
        return tokenNullBoolean;
    }


    /**
     * 设置年级
     * @param gradeId 年级id
     */
    public void setGradeId(int gradeId){
        SPUtil.saveInt("gradeId",gradeId);
    }
    public int getGradeId(){
        return SPUtil.getInt("gradeId",0);
    }
    /**
     * 设置版本
     * @param gradeId 年级id
     */
    public void setVersionId(int gradeId){
        SPUtil.saveInt("versionId",gradeId);
    }
    public int getVersionId(){
        return SPUtil.getInt("versionId",0);
    }

    /**
     * 是否首次使用我们APP
     * @return
     */
    public void setFirstUse(boolean isFirstUse){
        SPUtil.saveBoolean("isFirstUse",isFirstUse);
    }
    public boolean getFirstUse(){
        return SPUtil.getBoolean("isFirstUse",true);
    }

    /**
     * 获取token
     * @return
     */
    public String getToken() {
        LogUtil.e("__token",SPUtil.getString("token",""));
        return SPUtil.getString("token","");
    }
    public void setToken(String token) {
//        token = AESUtils.encrypt(getAesKey(), token);
        SPUtil.saveString("token", token);
    }


    /**
     * 设置是否是VIP
     * @param isVip
     */
    public void setIsVip(boolean isVip){
        SPUtil.saveBoolean("VIP",isVip);
        LogUtil.e("__vip","SPUtil.saveBoolean:"+isVip);
    }
    public boolean getIsVip(){
        return SPUtil.getBoolean("VIP",false);
    }

    /**
     * 设置用户名
     * @param userName
     */
    public void setUserName(String userName){
        SPUtil.saveString("userName",userName);
    }
    public String getUserName(){
        return SPUtil.getString("userName","");
    }

    /**
     * 设置用户昵称
     * @param nickName
     */
    public void setNickName(String nickName){
        SPUtil.saveString("nickName",nickName);
    }
    public String getNickName(){
        return SPUtil.getString("nickName","");
    }
    /**
     * 设置密码
     * @param password
     */
    public void setPassword(String password){
        SPUtil.saveString("password",password);
    }
    public String getPassword(){
        return SPUtil.getString("password","888888");
    }


    /**
     * 用户性别，0未知，1男，2女
     * @param sex
     */
    public void setSex(int sex){
        SPUtil.saveInt("sex",sex);
    }
    public int getSex(){
        return SPUtil.getInt("sex",0);
    }

    /**
     * 接口必传的参数，目前还不知道是什么
     * @param parm
     */
    public void setParm(int parm){
        SPUtil.saveInt("parm",parm);
    }
    public int getParm(){
        return SPUtil.getInt("parm",1);
    }

    /**
     * 用户积分
     * @param integral
     */
    public void setIntegral(int integral){
        SPUtil.saveInt("integral",integral);
    }
    public int getIntegral(){
        return SPUtil.getInt("integral",0);
    }


    /**
     * 用户id
     * @return
     */
    public int getUserId() {
        return SPUtil.getInt("userId",0);
    }

    public void setUserId(int userId) {
        SPUtil.saveInt("userId",userId);
    }
    /**
     * 国家
     * @param country
     */
    public void setCountry(String country){
        SPUtil.saveString("country",country);
    }
    public String getCountry(){
        return SPUtil.getString("country","");
    }

    /**
     * 头像
     * @param headImgUrl
     */
    public void setHeadImgUrl(String headImgUrl){
        SPUtil.saveString("headImgUrl",headImgUrl);
    }
    public String getHeadImgUrl(){
        return SPUtil.getString("headImgUrl","");
    }

    /**
     * 省份
     * @param province
     */
    public void setProvince(String province){
        SPUtil.saveString("province",province);
    }
    public String getProvince(){
        return SPUtil.getString("province","");
    }

    /**
     * 城市
     * @param city
     */
    public void setCity(String city){
        SPUtil.saveString("country",city);
    }
    public String getCity(){
        return SPUtil.getString("country","");
    }

    /**
     * 用户手机
     * @param phone
     */
    public void setUserPhone(String phone){
        SPUtil.saveString("phone",phone);
    }
    public String getUserPhone(){
        return SPUtil.getString("phone","");
    }


    /**
     * 用户选择科目的版本list
     * @param datalist
     */
    public void setUserSelectedVersion(List<UserSelectedVersionModel.Databean> datalist){
        SPUtil.saveUserSelectedVersion(datalist);
    }
    public List<UserSelectedVersionModel.Databean> getUserSelectedVersion(){
        return SPUtil.getUserSelectedVersion();
    }

    /**
     * 保存今日任务
     * @param datalist
     */
    public void setUserTodayPlan(List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> datalist){
        SPUtil.saveUserTodayPlans(datalist);
    }

    public List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> getUserTodayPlan(){
        return SPUtil.getUserTodayPlans();
    }
    /**
     * 设置用户头像Url
     * @param
     */
    public void setUserIcon(String iconUrl){
        SPUtil.saveString("iconUrl",iconUrl);
    }
    public String getUserIcon(){
        return SPUtil.getString("iconUrl","");
    }


    /**
     * 设置vip到期时间
     * @param vipTime 时间戳
     */
    public void setVipTime(int vipTime){
        SPUtil.saveInt("vipTime",vipTime);
    }

    /**
     * 获取vip到期时间的时间戳
     * @return
     */
    public int getVipTime(){
        return SPUtil.getInt("vipTime",0);
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }


    /**
     * 获取用户选择的版本和年级
     * @return
     */
    public String getUserSelectVersion() {
//        LogUtil.e("__token",SPUtil.getString("token",""));
        return SPUtil.getString("userSelectVersion","");
    }
    public void setUserSelectVersion(String userSelectVersion) {
//        token = AESUtils.encrypt(getAesKey(), token);
        SPUtil.saveString("userSelectVersion", userSelectVersion);
    }

    public static boolean isIsPermitMoveNet() {
        return isPermitMoveNet;
    }

    public static void setIsPermitMoveNet(boolean isPermitMoveNet) {
        UserManager.isPermitMoveNet = isPermitMoveNet;
    }
}
