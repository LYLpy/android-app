package net.getlearn.getlearn_android.model;

import android.content.Context;
import android.util.Log;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.model.bean.AddCollectionModel;
import net.getlearn.getlearn_android.model.bean.AoePPTModel;
import net.getlearn.getlearn_android.model.bean.BannerModel;
import net.getlearn.getlearn_android.model.bean.ClassifyModel;
import net.getlearn.getlearn_android.model.bean.CreateOrderAndPayModel;
import net.getlearn.getlearn_android.model.bean.CreateStudyPlanModel;
import net.getlearn.getlearn_android.model.bean.ExchangeCodeModel;
import net.getlearn.getlearn_android.model.bean.EditAddressModel;
import net.getlearn.getlearn_android.model.bean.EditGradeAndVersionModel;
import net.getlearn.getlearn_android.model.bean.EditHead;
import net.getlearn.getlearn_android.model.bean.EditNicknameModel;
import net.getlearn.getlearn_android.model.bean.EditPhoneGetVerifyCodeModel;
import net.getlearn.getlearn_android.model.bean.EditPhoneModel;
import net.getlearn.getlearn_android.model.bean.EditSignatureModel;
import net.getlearn.getlearn_android.model.bean.ExchangeCodeRecordsModel;
import net.getlearn.getlearn_android.model.bean.FamousTeacherIntroductionModel;
import net.getlearn.getlearn_android.model.bean.FamousTeacherModel;
import net.getlearn.getlearn_android.model.bean.FamousTeacherModelHome;
import net.getlearn.getlearn_android.model.bean.FeedbackModel;
import net.getlearn.getlearn_android.model.bean.GetActiveModel;
import net.getlearn.getlearn_android.model.bean.GetCouponModel;
import net.getlearn.getlearn_android.model.bean.GetPersonalInfoModel;
import net.getlearn.getlearn_android.model.bean.GetVerificationCodeModel;
import net.getlearn.getlearn_android.model.bean.GetVersion;
import net.getlearn.getlearn_android.model.bean.GiftExchangeModel;
import net.getlearn.getlearn_android.model.bean.GiftListModel;
import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.model.bean.LoginModel;
import net.getlearn.getlearn_android.model.bean.MakePosterModel;
import net.getlearn.getlearn_android.model.bean.MoreClassModel;
import net.getlearn.getlearn_android.model.bean.MyColletcModel;
import net.getlearn.getlearn_android.model.bean.MyCouponModel;
import net.getlearn.getlearn_android.model.bean.MyFeedbackListModel;
import net.getlearn.getlearn_android.model.bean.MyFeedbackMsgModel;
import net.getlearn.getlearn_android.model.bean.MyGiftListModel;
import net.getlearn.getlearn_android.model.bean.MyOrderModel;
import net.getlearn.getlearn_android.model.bean.MyStudyModel;
import net.getlearn.getlearn_android.model.bean.MyStudyModelNew;
import net.getlearn.getlearn_android.model.bean.OrderDetailModel;
import net.getlearn.getlearn_android.model.bean.PersionalHomeModel;
import net.getlearn.getlearn_android.model.bean.PriceModel;
import net.getlearn.getlearn_android.model.bean.RecommendSubjectHomeModel;
import net.getlearn.getlearn_android.model.bean.SaveSubjectAndVersionModel;
import net.getlearn.getlearn_android.model.bean.SearchResultModel;
import net.getlearn.getlearn_android.model.bean.SelectSubjectListModel;
import net.getlearn.getlearn_android.model.bean.ShareModel;
import net.getlearn.getlearn_android.model.bean.StudentCommentModel;
import net.getlearn.getlearn_android.model.bean.StudyIndexModel;
import net.getlearn.getlearn_android.model.bean.StudyKeyDetailModel;
import net.getlearn.getlearn_android.model.bean.StudyKeyLikeModel;
import net.getlearn.getlearn_android.model.bean.StudyKeyModelHome;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.model.bean.SubjectIntroduceModel;
import net.getlearn.getlearn_android.model.bean.SubjectListModel;
import net.getlearn.getlearn_android.model.bean.SubjectModel;
import net.getlearn.getlearn_android.model.bean.SubjectVersionModel;
import net.getlearn.getlearn_android.model.bean.SubmitComment;
import net.getlearn.getlearn_android.model.bean.SyncSubjectHomeModel;
import net.getlearn.getlearn_android.model.bean.TodayChallengeModel;
import net.getlearn.getlearn_android.model.bean.UploadImgModel;
import net.getlearn.getlearn_android.model.bean.UploadSingleImgModel;
import net.getlearn.getlearn_android.model.bean.UserInfoModel;
import net.getlearn.getlearn_android.model.bean.UserProtocolModel;
import net.getlearn.getlearn_android.model.bean.UserSelectedVersionModel;
import net.getlearn.getlearn_android.model.bean.UserSignModel;
import net.getlearn.getlearn_android.model.bean.VideoUrlModel;
import net.getlearn.getlearn_android.model.bean.WXLoginInfoModel;
import net.getlearn.getlearn_android.model.bean.WXLoginModel;
import net.getlearn.getlearn_android.model.bean.WeekChallengeModel;
import net.getlearn.getlearn_android.model.bean.WeekSignModel;
import net.getlearn.getlearn_android.utils.APKVersionCodeUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/22------更新------
 * 网络请求
 */

public class HttpHelper extends BaseHelper{

    public void getBanner(IHttpCallback httpCallback,
                                  String token,
                                  int timestamp){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getBanner(map),httpCallback,Constants.REQUEST_BANNER, BannerModel.class);
    }

    /**签名算法
     * @param httpCallback
     * @param sign
     * @param token
     * @param timestamp
     * @param name
     * @param password
     * @param sex
     * @param parm
     */
    public void getStudyIndexSign(IHttpCallback httpCallback,
                                  String sign,
                                  String token,
                                  int timestamp,
                                  String name,
                                  String password,
                                  int sex,
                                  int parm){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        map.put("sex",sex);
        map.put("parm",parm);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getStudyIndex(map),httpCallback,IHttpService.STUDY_INDEX, StudyIndexModel.class);
    }

    /**获取登录/注册验证码
     * @param httpCallback
     * @param phone
     * @param timestamp
     */
    public void getVerificationCode(IHttpCallback httpCallback,
                          String phone,
                          int timestamp){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("phone",phone);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("phone",phone);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getVerificationCode(map),httpCallback, Constants.REQUEST_VERIFICATIONCODE, GetVerificationCodeModel.class);
    }

    public void login(IHttpCallback httpCallback,
                                    String verify_id,
                                    int timestamp,
                                    String verify_code,
                                    String phone,
                                    String token,
                                    Context context,
                                    String invitation_code){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("verify_id",verify_id);
        mapGetSign.put("verify_code",verify_code);
        mapGetSign.put("phone",phone);
        if (APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL")!=null){
            String channel = APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL");
            mapGetSign.put("channel",channel);
        }
        mapGetSign.put("invitation_code",invitation_code);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("invitation_code",invitation_code);
        map.put("phone",phone);
        map.put("timestamp",timestamp);
        map.put("verify_id",verify_id);
        if (APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL")!=null){
            String channel = APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL");
            map.put("channel",channel);
        }
        map.put("verify_code",verify_code);
        if (!token.equals("")){
            map.put("token",token);
        }
        super.enqueue(super.getService().login(map),httpCallback, Constants.REQUEST_LOGIN, LoginModel.class);
    }

    /**
     * 获取年级对象列表
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getGrade(IHttpCallback httpCallback,
                      int timestamp,
                      String token){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        super.enqueue(super.getService().getGrade(map),httpCallback, Constants.REQUEST_GET_GRADE, GradeModel.class);
    }

    /**
     * 获取名师简介
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getFamousTeacherIntroduction(IHttpCallback httpCallback,
                         int timestamp,
                         String token,
                         int teacher_id){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("teacher_id",teacher_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("teacher_id",teacher_id);
        map.put("token",token);
        super.enqueue(super.getService().getFamousTeacherIntroduction(map),httpCallback, Constants.REQUEST_GET_FAMOUS_TEACHER_INTRODUCTION, FamousTeacherIntroductionModel.class);
    }

    /**
     * 根据分类id获取科目
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getSubjectVersionClassification(IHttpCallback httpCallback,
                         int timestamp,
                         String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        super.enqueue(super.getService().getSubjectVersionClassification(map),httpCallback, Constants.REQUEST_GET_SUBJECT_VERSION_CLASSIFICATION, SubjectVersionModel.class);
    }

    /**
     * 保存用户选择科目和版本
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param grade_id
     * @param select_course
     */
    public void saveSubjectAndVersion(IHttpCallback httpCallback,
                                                int timestamp,
                                                String token,
                                                String grade_id,
                                                String select_course){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        if (select_course!=null && !select_course.equals("")){
            mapGetSign.put("select_course",select_course);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        if (select_course != null && !select_course.equals("")){
            map.put("select_course",select_course);
            LogUtil.e("__saveSubjectAndVersion","select_course:" + String.valueOf("+" + select_course));
        }
        map.put("grade_id",grade_id);

        LogUtil.e("__saveSubjectAndVersion","sign:" + sign);
        LogUtil.e("__saveSubjectAndVersion","timestamp:" + timestamp);
        LogUtil.e("__saveSubjectAndVersion","token:" + token);
        LogUtil.e("__saveSubjectAndVersion","grade_id:" + grade_id);

        super.enqueue(super.getService().saveSubjectAndVersion(map),httpCallback, Constants.REQUEST_SAVE_SUBJECT_AND_VERSION, SaveSubjectAndVersionModel.class);
    }


    /**
     * //修改年级和和版本
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param grade_id
     * @param select_course
     */
    public void editSelectGradeAndVersion(IHttpCallback httpCallback,
                                      int timestamp,
                                      String token,
                                      String grade_id,
                                      String select_course){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        if (select_course!=null && !select_course.equals("")){
            mapGetSign.put("select_course",select_course);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        if (select_course != null && !select_course.equals("")){
            map.put("select_course",select_course);
            LogUtil.e("__saveSubjectAndVersion","select_course:" + String.valueOf("+" + select_course));
        }
        map.put("grade_id",grade_id);

        LogUtil.e("__editSelectGradeAndVersion","sign:" + sign);
        LogUtil.e("__editSelectGradeAndVersion","timestamp:" + timestamp);
        LogUtil.e("__editSelectGradeAndVersion","token:" + token);
        LogUtil.e("__editSelectGradeAndVersion","grade_id:" + grade_id);
        super.enqueue(super.getService().editSelectGradeAndVersion(map),httpCallback, Constants.REQUEST_EDIT_GRADE_AND_VERSION, EditGradeAndVersionModel.class);
    }

    /**
     * 获取首页同步课程
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getSyncSubject(IHttpCallback httpCallback,
                                      int timestamp,
                                      String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        for (Map.Entry<String,Object> item : map.entrySet()){
            LogUtil.e("__json"+Constants.REQUEST_GET_SYNC_SUBJECT_HOME,item.getKey()+":"+item.getValue());
        }

        super.enqueue(super.getService().getSyncSubject(map),httpCallback, Constants.REQUEST_GET_SYNC_SUBJECT_HOME, SyncSubjectHomeModel.class);
    }

    /**
     * 获取课程介绍
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getSubjectIntroduce(IHttpCallback httpCallback,
                                    int timestamp,
                                    String token,
                                    int grade_id,
                                    int classify_id,
                                    int version_id,
                                    int subject_id,
                                    String course_id){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        mapGetSign.put("classify_id",classify_id);
        mapGetSign.put("version_id",version_id);
        mapGetSign.put("subject_id",subject_id);
        mapGetSign.put("course_id",course_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("grade_id",grade_id);
        map.put("classify_id",classify_id);
        map.put("version_id",version_id);
        map.put("subject_id",subject_id);
        map.put("course_id",course_id);

        LogUtil.e("__json"+Constants.REQUEST_GET_SUBJECT_INTRODUCE,map+"");

        for(Map.Entry<String, Object> a:map.entrySet()){
            LogUtil.e("__json"+Constants.REQUEST_GET_SUBJECT_INTRODUCE,a.getKey()+":" + a.getValue());
//            System.out.println("键是"+a.getKey());
//            LogUtil.e("__aaa值是",a.getValue()+"");

        }

//        for (Map.Entry<String, Object> entry : map.entrySet()) {
////            LogUtil.e("key = " + entry.getKey().toString() + ", value = " + entry.getValue().toString());
////            Log.e("__ssss","key = " + entry.getKey().toString() + ", value = " + entry.getValue().toString());
//        }


        super.enqueue(super.getService().getSubjectIntroduce(map),httpCallback, Constants.REQUEST_GET_SUBJECT_INTRODUCE, SubjectIntroduceModel.class);
    }

    /**
     * 获取用户选择的科目版本信息
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getUserSelectedVersion(IHttpCallback httpCallback,
                                 int timestamp,
                                 String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        super.enqueue(super.getService().getUserSelectedVersion(map),httpCallback, Constants.REQUEST_GET_USER_SELECTED_VERSION, UserSelectedVersionModel.class);
    }

    /**
     * 获取课程列表接口
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getSubjectList(IHttpCallback httpCallback,
                               int timestamp,
                               String token,
                               int grade_id,
                               int classify_id,
                               int version_id,
                               int subject_id,
                               int limit,
                               int page ,
                               String course_id){

        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        mapGetSign.put("classify_id",classify_id);
        mapGetSign.put("version_id",version_id);
        mapGetSign.put("subject_id",subject_id);
        mapGetSign.put("limit",limit);
        mapGetSign.put("course_id",course_id);
        mapGetSign.put("page",page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("grade_id",grade_id);
        map.put("classify_id",classify_id);
        map.put("version_id",version_id);
        map.put("subject_id",subject_id);
        map.put("limit",limit);
        map.put("page",page);
        map.put("course_id",course_id);
        for (Map.Entry<String,Object> item : map.entrySet()){
            LogUtil.e("__json"+Constants.REQUEST_GET_SUBJECT_LIST,item.getKey()+":"+item.getValue());
        }
        LogUtil.e("__json"+Constants.REQUEST_GET_SUBJECT_LIST,map+"");
        super.enqueue(super.getService().getSubjectList(map),httpCallback, Constants.REQUEST_GET_SUBJECT_LIST, SubjectListModel.class);
    }

    /**
     * 获取学员评论
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getStudentComment(IHttpCallback httpCallback,
                               int timestamp,
                               String token,
                               int course_id,
                                int page){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("course_id",course_id);
        mapGetSign.put("page",page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("course_id",course_id);
        map.put("page",page);
        LogUtil.e("__json"+Constants.REQUEST_GET_STUDENT_COMMENT+"求",map+"");
        for (Map.Entry<String,Object>  StudentComment: map.entrySet()){
            LogUtil.e("__json"+Constants.REQUEST_GET_STUDENT_COMMENT,StudentComment.getKey()+":"+StudentComment.getValue());
        }

        super.enqueue(super.getService().getStudentComment(map),httpCallback, Constants.REQUEST_GET_STUDENT_COMMENT, StudentCommentModel.class);
    }

    /**
     * 获取个人信息
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getPersonalInfo(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        super.enqueue(super.getService().getPersonalInfo(map),httpCallback, Constants.REQUEST_GET_PERSONAL_INFO, GetPersonalInfoModel.class);
    }

    /**
     * 获取首页学习干货
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getStudyKeyHome(IHttpCallback httpCallback,
                                int timestamp,
                                String token,
                                int page,
                                int limit){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("limit",limit);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("page",page);
        map.put("limit",limit);
        super.enqueue(super.getService().getStudyKeyHome(map),httpCallback, Constants.REQUEST_GET_STUDY_KEY_HOME, StudyKeyModelHome.class);
    }


    /**用户协议
     * @param httpCallback
     * @param timestamp
     */
    public void getUserProtocol(IHttpCallback httpCallback,
                                int timestamp){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getUserProtocol(map),httpCallback, Constants.REQUEST_USER_PROTOCOL, UserProtocolModel.class);
    }

    /**保密协议
     * @param httpCallback
     * @param timestamp
     */
    public void getUserSecrecy(IHttpCallback httpCallback,
                                int timestamp){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getUserSecrecy(map),httpCallback, Constants.REQUEST_USER_SECRECY, UserProtocolModel.class);
    }

    /**常见问题
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getCommonQuestion(IHttpCallback httpCallback,
                               int timestamp,
                               String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getCommonQuestion(map),httpCallback, Constants.REQUEST_COMMON_QUESTION, UserProtocolModel.class);
    }

    /**
     * 我的礼品
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page
     * @param per_page
     */
    public void myGiftList(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token,
                                  int page,
                                  int per_page){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("page",page);
        map.put("per_page",per_page);
        LogUtil.e("__json52求",map+"");
        super.enqueue(super.getService().myGiftList(map),httpCallback, Constants.REQUEST_MY_GIFT_LIST, MyGiftListModel.class);
    }

    /**
     * 新的，获取我的学习列表
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page
     * @param limit
     */
    public void getMyStudyListNew(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token,
                                  int page,
                                  int limit){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("limit",limit);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("page",page);
        map.put("limit",limit);
        super.enqueue(super.getService().getMyStudyList(map),httpCallback, Constants.REQUEST_GET_MY_STUDY_LIST, MyStudyModelNew.class);
    }
    /**
     * 旧的，获取我的学习列表
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page
     * @param limit
     */
    public void getMyStudyList(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token,
                                  int page,
                                  int limit){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("limit",limit);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("page",page);
        map.put("limit",limit);

        super.enqueue(super.getService().getMyStudyList(map),httpCallback, Constants.REQUEST_GET_MY_STUDY_LIST, MyStudyModel.class);
    }
    /**
     * 获取首页推荐课程
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getRecommendSubjectHome(IHttpCallback httpCallback,
                                int timestamp,
                                String token,
                                int grade_id,
                                int limit){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        mapGetSign.put("limit",limit);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("grade_id",grade_id);
        map.put("limit",limit);
        LogUtil.e("__Recommend_sign",sign);
        LogUtil.e("__Recommend_timestamp",timestamp + "");
        LogUtil.e("__Recommend_token",token);
        LogUtil.e("__Recommend_grade_id",grade_id + "");
        LogUtil.e("__Recommend_limit",limit + "");


        super.enqueue(super.getService().getRecommendSubjectHome(map),httpCallback, Constants.REQUEST_GET_RECOMMEND_SUBJECT_HOME, RecommendSubjectHomeModel.class);
    }

    /**
     * 获取
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param grade_id
     * @param limit
     * @param page
     */
    public void getFamousTeacher(IHttpCallback httpCallback,
                                        int timestamp,
                                        String token,
                                        int grade_id,
                                        int limit,
                                        int page){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        mapGetSign.put("limit",limit);
        mapGetSign.put("page",page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("grade_id",grade_id);
        map.put("limit",limit);
        map.put("page",page);
        super.enqueue(super.getService().getFamousTeacher(map),httpCallback, Constants.REQUEST_GET_FAMOUS_TEACHER, FamousTeacherModel.class);
    }

    /**
     * 获取首页名师专栏
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getFamousTeacherHome(IHttpCallback httpCallback,
                                        int timestamp,
                                        String token,
                                        int limit){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("limit",limit);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("limit",limit);
        super.enqueue(super.getService().getFamousTeacherHome(map),httpCallback, Constants.REQUEST_GET_FAMOUS_TEACHER_HOME, FamousTeacherModelHome.class);
    }
    /**
     * 获取视频播放地址
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param video_id
     * @param isFree 是否免费0为免费，1为收费
     * @param reqType 请求类型，只获取URL不播放/获取URL并播放
     */
    public void getVideoUrl(IHttpCallback httpCallback,
                                        int timestamp,
                                        String token,
                                        int video_id,
                                        int isFree,
                                        int reqType){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("video_id",video_id);
        mapGetSign.put("isFree",isFree);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("video_id",video_id);
        map.put("isFree",isFree);
        LogUtil.e("sign",sign);
        LogUtil.e("__video_id",video_id + "");
        LogUtil.e("timestamp",timestamp + "");
        LogUtil.e("__token",token + "");
        super.enqueue(super.getService().getVideoUrl(map),httpCallback,Constants.REQUEST_GET_VIDEO_URL, VideoUrlModel.class);
    }


    /**
     * 选课界面获取课程分类
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getClassify(IHttpCallback httpCallback,
                                       int timestamp,
                                       String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        super.enqueue(super.getService().getClassify(map),httpCallback, Constants.REQUEST_GET_CLASSIFY, ClassifyModel.class);
    }

    /**
     * 首页我的
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getPersonalHome(IHttpCallback httpCallback,
                            int timestamp,
                            String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        super.enqueue(super.getService().getPersonalHome(map),httpCallback, Constants.REQUEST_GET_PERSONAL_HOME, PersionalHomeModel.class);
    }
    /**
     * 选课界面获取科目
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getSubject(IHttpCallback httpCallback,
                            int timestamp,
                            String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        super.enqueue(super.getService().getSubject(map),httpCallback, Constants.REQUEST_GET_SUBJECT, SubjectModel.class);
    }

    /**
     * 根据分类ID,获取科目
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param classify_id 分类ID
     */
    public void getSubjectByClassify(IHttpCallback httpCallback,
                           int timestamp,
                           String token,
                           int classify_id){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("classify_id",classify_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("classify_id",classify_id);
        super.enqueue(super.getService().getSubject(map),httpCallback, Constants.REQUEST_GET_SUBJECT, SubjectModel.class);
    }

    /**
     * 上传多张图片接口，用base64格式上传，后台会返回图片url，用于辅助新增意见反馈提交
     * @param httpCallback
     * @param timestamp
     */
    public void uploadImgs(IHttpCallback httpCallback,
                           int timestamp,
                           String[] img){
        //这里个接口比较特殊，签名单独计算
        StringBuilder signBuilder = new StringBuilder();
//        signBuilder.append("key=");
        signBuilder.append(IHttpService.KEY);
//        signBuilder.append("&");
//        signBuilder.append("timestamp=");
        signBuilder.append(timestamp);
        String sign = CommonUtils.md5(signBuilder.toString());
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < img.size(); i++) {
//            builder.append(img.get(i));
//            if (i != img.size() - 1){
//                builder.append(",");
//            }
//        }
//        map.put("img[]",builder.toString());
        LogUtil.e("__HttpHelper__uploadImg","timestamp:" + timestamp);
        LogUtil.e("__HttpHelper__uploadImg","signBuilder:" + signBuilder);
        LogUtil.e("__HttpHelper__uploadImg","sign:" + sign);
        LogUtil.e("__HttpHelper__uploadImg","img[]:" + img);
//        LogUtil.e("__HttpHelper__uploadImg","img:" + img.get(0));
        super.enqueue(super.getService().uploadsImg(img,sign,timestamp),httpCallback, Constants.REQUEST_UPLOAD_IMGS, UploadImgModel.class);
    }

    /**
     * 提交单张图片接口，用base64格式上传，后台会返回图片url，用于辅助新增意见反馈提交
     * @param httpCallback
     * @param timestamp
     * @param img
     */
    public void uploadImg(IHttpCallback httpCallback,
                           int timestamp,
                           String img){
        //这里个接口比较特殊，签名单独计算
        StringBuilder signBuilder = new StringBuilder();
//        signBuilder.append("key=");
        signBuilder.append(IHttpService.KEY);
//        signBuilder.append("&");
//        signBuilder.append("timestamp=");
        signBuilder.append(timestamp);
        String sign = CommonUtils.md5(signBuilder.toString());
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < img.size(); i++) {
//            builder.append(img.get(i));
//            if (i != img.size() - 1){
//                builder.append(",");
//            }
//        }
        map.put("img",img);
        LogUtil.e("__HttpHelper__uploadImg","timestamp:" + timestamp);
        LogUtil.e("__HttpHelper__uploadImg","signBuilder:" + signBuilder);
        LogUtil.e("__HttpHelper__uploadImg","sign:" + sign);
        LogUtil.e("__HttpHelper__uploadImg","img:" + img);
//        LogUtil.e("__HttpHelper__uploadImg","img:" + img.get(0));
        super.enqueue(super.getService().uploadImg(map),httpCallback, Constants.REQUEST_UPLOAD_IMG_SINGLE, UploadSingleImgModel.class);
    }
    /**
     * 意见反馈
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param message
     * @param image
     */
    public void feedback(IHttpCallback httpCallback,
                         int timestamp,
                         String token,
                         String message,
                         String image,
                         String phone,
                         int parentId){
        //获取sign的map
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        if (!message.equals("")){
            mapGetSign.put("message",message);
            mapGetSign.put("phone",phone);
        }
        if (!image.equals("")){
            mapGetSign.put("image",image);
            mapGetSign.put("phone",phone);
        }
        if (parentId!=0){
            mapGetSign.put("parentId",parentId);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        if (!message.equals("")){
            map.put("message",message);
            map.put("phone",phone);
        }
        if (!image.equals("")){
            map.put("image",image);
            map.put("phone",phone);
        }
        if (parentId!=0){
            map.put("parentId",parentId);
        }
        if (!image.equals("")){
            map.put("image",image);
            LogUtil.e("__json"+Constants.REQUEST_FEEDBACK_IMG+"求",map+"");
            super.enqueue(super.getService().feedback(map),httpCallback, Constants.REQUEST_FEEDBACK_IMG, FeedbackModel.class);
        }else{
            LogUtil.e("__json"+Constants.REQUEST_FEEDBACK_MSG+"求",map+"");
            super.enqueue(super.getService().feedback(map),httpCallback, Constants.REQUEST_FEEDBACK_MSG, FeedbackModel.class);
        }

    }

    /**
     * 修改头像接口，需要先调用上传图片接口，上传成功拿到URL之后，再调用此接口
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param img
     */
    public void editHead(IHttpCallback httpCallback,
                         int timestamp,
                         String token,
                         String img){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("img",img);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("img",img);
        super.enqueue(super.getService().editHead(map),httpCallback, Constants.REQUEST_EDIT_HEAD, EditHead.class);
    }


    /**
     * 分享记录接口
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param userId
     * @param shareDate date类型（不含时分秒）
     * @param shareCount 分享次数，目前全部传1
     */
    public void share(IHttpCallback httpCallback,
                         int timestamp,
                         String token,
                         int userId,
                          String shareDate,
                          int shareCount){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("userId",userId);
        mapGetSign.put("shareDate",shareDate);
        mapGetSign.put("shareCount",shareCount);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("userId",userId);
        map.put("shareDate",shareDate);
        map.put("shareCount",shareCount);
        for(Map.Entry<String, Object> a:map.entrySet()){
            LogUtil.e("__share：值",a.getKey()+":" + a.getValue());
//            System.out.println("键是"+a.getKey());
//            LogUtil.e("__aaa值是",a.getValue()+"");

        }
        super.enqueue(super.getService().share(map),httpCallback, Constants.REQUEST_SHARE, ShareModel.class);
    }


    /**
     * 获取选课课程列表接口
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getSelectSubjectList(IHttpCallback httpCallback,
                               int timestamp,
                               String token,
                               int grade_id,
                               int classify_id,
                               int version_id,
                               int subject_id){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        mapGetSign.put("classify_id",classify_id);
        mapGetSign.put("version_id",version_id);
        mapGetSign.put("subject_id",subject_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("grade_id",grade_id);
        map.put("classify_id",classify_id);
        map.put("version_id",version_id);
        map.put("subject_id",subject_id);
        LogUtil.e("__jsongetSelectSubjectList",map+"");
//        LogUtil.e("__getSelectSubjectList","sign:" + sign);
//        LogUtil.e("__getSelectSubjectList","timestamp:" + timestamp);
//        LogUtil.e("__getSelectSubjectList","token:" + token);
//        LogUtil.e("__getSelectSubjectList","grade_id:" + grade_id);
//        LogUtil.e("__getSelectSubjectList","classify_id:" + classify_id);
//        LogUtil.e("__getSelectSubjectList","version_id:" + version_id);
//        LogUtil.e("__getSelectSubjectList","subject_id:" + subject_id);
        super.enqueue(super.getService().getSelectSubjectList(map),httpCallback, Constants.REQUEST_GET_SELECT_SUBJECT_LIST, SelectSubjectListModel.class);
    }


    /**
     * 选课获取更多课程接口
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param grade_id 年级id
     * @param classify_id 分类id
     * @param subject_id 科目id
     * @param page
     * @param limit
     */
    public void getMoreClass(IHttpCallback httpCallback,
                                     int timestamp,
                                     String token,
                                     int grade_id,
                                     int classify_id,
                                     int subject_id,
                                     int page,
                                     int limit){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("grade_id",grade_id);
        mapGetSign.put("classify_id",classify_id);
        mapGetSign.put("subject_id",subject_id);
        mapGetSign.put("limit",limit);
        mapGetSign.put("page",page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("grade_id",grade_id);
        map.put("classify_id",classify_id);
        map.put("subject_id",subject_id);
        map.put("limit",limit);
        map.put("page",page);
        super.enqueue(super.getService().getMoreClass(map),httpCallback, Constants.REQUEST_GET_MORE_CLASS, MoreClassModel.class);
    }


    /**
     * 课程搜索接口
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param content 搜索关键词
     * @param page 页码
     */
    public void search(IHttpCallback httpCallback,
                             int timestamp,
                             String token,
                             String content,
                             int page){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("content",content);
        mapGetSign.put("page",page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("content",content);
        map.put("page",page);
        super.enqueue(super.getService().search(map),httpCallback, Constants.REQUEST_SEARCH, SearchResultModel.class);
    }


    /**
     * 今日挑战
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param per_page 请求条数
     * @param current_page 当前页码
     */
    public void getTodayChallenge(IHttpCallback httpCallback,
                       int timestamp,
                       String token,
                       int per_page,
                       int current_page){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("per_page",per_page);
        mapGetSign.put("current_page",current_page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("per_page",per_page);
        map.put("current_page",current_page);
        LogUtil.e("__WeekChallenge","sign:" + sign);
        LogUtil.e("__WeekChallenge","timestamp:" + timestamp);
        LogUtil.e("__WeekChallenge","token:" + token);
        LogUtil.e("__WeekChallenge","per_page:" + per_page);
        LogUtil.e("__WeekChallenge","current_page:" + current_page);
        for(Map.Entry<String, Object> a:map.entrySet()){
            LogUtil.e("__json"+Constants.REQUEST_TODAY_CHALLENGE,a.getKey()+":" + a.getValue());
//            System.out.println("键是"+a.getKey());
//            LogUtil.e("__aaa值是",a.getValue()+"");

        }
        super.enqueue(super.getService().getTodayChallenge(map),httpCallback, Constants.REQUEST_TODAY_CHALLENGE, TodayChallengeModel.class);
    }

    /**
     * 本周挑战任务
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param per_page 请求条数
     * @param current_page 当前页码
     */
    public void getWeekChallenge(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token,
                                  int per_page,
                                  int current_page){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("per_page",per_page);
        mapGetSign.put("current_page",current_page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("per_page",per_page);
        map.put("current_page",current_page);
        LogUtil.e("__WeekChallenge","sign:" + sign);
        LogUtil.e("__WeekChallenge","timestamp:" + timestamp);
        LogUtil.e("__WeekChallenge","token:" + token);
        LogUtil.e("__WeekChallenge","per_page:" + per_page);
        LogUtil.e("__WeekChallenge","current_page:" + current_page);
        super.enqueue(super.getService().getWeekChallenge(map),httpCallback, Constants.REQUEST_WEEK_CHALLENGE, WeekChallengeModel.class);
    }

    /**
     * 获取本周签到情况
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getWeekSign(IHttpCallback httpCallback,
                                 int timestamp,
                                 String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        LogUtil.e("__sign",sign);
        LogUtil.e("__timestamp",timestamp + "");
        LogUtil.e("__token",token);
        super.enqueue(super.getService().getWeekSign(map),httpCallback, Constants.REQUEST_GET_WEEK_SIGN, WeekSignModel.class);
    }

    /**获取活动链接
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getActive(IHttpCallback httpCallback,
                            int timestamp,
                            String token){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        LogUtil.e("__sign",sign);
        LogUtil.e("__timestamp",timestamp + "");
        LogUtil.e("__token",token);
        super.enqueue(super.getService().getActive(map),httpCallback, Constants.REQUEST_GET_ACTIVE, GetActiveModel.class);
    }


    /**
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void makeposter(IHttpCallback httpCallback,
                          int timestamp,
                          String token,
                           int active_id){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("active_id",active_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("active_id",active_id);
        LogUtil.e("__makeposter", "sign:" + sign);
        LogUtil.e("__makeposter","timestamp:" +  timestamp);
        LogUtil.e("__makeposter","token:" +  token);
        LogUtil.e("__makeposter","active_id:" +  active_id);
        super.enqueue(super.getService().makeposter(map),httpCallback, Constants.REQUEST_MAKE_POSTER, MakePosterModel.class);
    }

    /**
     * 微信登录
     * @param httpCallback
     * @param timestamp
     * @param code 调起微信之后，获得的code,通过code访问后台接口实现微信联合登录
     */
    public void WXLogin(IHttpCallback httpCallback,
                       int timestamp,
                       String code,
                        Context context){

        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("code",code);
        if (APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL")!=null){
            String channel = APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL");
            mapGetSign.put("channel",channel);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
//        map.put("token",token);
        map.put("code",code);
        if (APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL")!=null){
            String channel = APKVersionCodeUtils.getAPK_UMENG_CHANNEL_VALUE(context,"UMENG_CHANNEL");
            map.put("channel",channel);
        }
        LogUtil.e("__httphelper_sign",sign);
        LogUtil.e("__httphelper_timestamp",timestamp + "");
//        LogUtil.e("__httphelper_token",token );
        LogUtil.e("__httphelper_code",code);
        LogUtil.e("__json"+Constants.REQUEST_WX_LOGIN,map+"");
        super.enqueue(super.getService().WXLogin(map),httpCallback, Constants.REQUEST_WX_LOGIN, WXLoginModel.class);
    }


    /**
     * 获取会员优惠套餐
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getPrice(IHttpCallback httpCallback,
                         int timestamp,
                         String token){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        LogUtil.e("__json20",map+"");
        super.enqueue(super.getService().getPrice(map),httpCallback, Constants.REQUEST_GET_PRICE, PriceModel.class);
    }

    /**
     * 获取礼品列表
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page 页码
     * @param per_page 获取数据数量
     */
    public void getGiftList(IHttpCallback httpCallback,
                         int timestamp,
                         String token,
                         int page,
                         int per_page){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("page",page);
        map.put("per_page",per_page);
        super.enqueue(super.getService().getGiftList(map),httpCallback, Constants.REQUEST_GET_GIFT_LIST, GiftListModel.class);
    }

    /**
     * 兑换虚拟礼品
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param giftId 礼品ID
     */
    public void giftInventedExchange(IHttpCallback httpCallback,
                                   int timestamp,
                                   String token,
                                   int giftId){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("giftId",giftId);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("giftId",giftId);
        LogUtil.e("__Exchange_sign",sign);
        LogUtil.e("__Exchange_timestamp",timestamp + "");
        LogUtil.e("__Exchange_token",token);
        LogUtil.e("__Exchange_giftId",giftId + "");
        super.enqueue(super.getService().giftExchange(map),httpCallback, Constants.REQUEST_GIFT_EXCHANGE, GiftExchangeModel.class);
    }

    /**
     * 退出登录
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void loginout(IHttpCallback httpCallback,
                                     int timestamp,
                                     String token){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        LogUtil.e("__loginout_sign",sign);
        LogUtil.e("__loginout_timestamp",timestamp + "");
        LogUtil.e("__loginout_token",token);
        super.enqueue(super.getService().loginout(map),httpCallback, Constants.REQUEST_LOGIN_OUT, JSONObject.class);
    }


    /**
     * 兑换实体礼品
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param giftId 礼品ID
     * @param province 省份
     * @param city 城市
     * @param area 区域
     * @param street 街道
     * @param phone 联系人号码
     * @param specificLocation 详细地址
     * @param consigneeName 收货人名字
     */
    public void giftEntityExchange(IHttpCallback httpCallback,
                            int timestamp,
                            String token,
                            int giftId,
                             String province,
                             String city,
                             String area,
                             String street,
                             String phone,
                             String specificLocation,
                             String consigneeName){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",giftId);
        mapGetSign.put("province",province);
        mapGetSign.put("city",city);
        mapGetSign.put("area",area);
        mapGetSign.put("street",street);
        mapGetSign.put("phone",phone);
        mapGetSign.put("specificLocation",specificLocation);
        mapGetSign.put("consigneeName",consigneeName);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("page",giftId);
        map.put("province",province);
        map.put("city",city);
        map.put("area",area);
        map.put("street",street);
        map.put("phone",phone);
        map.put("specificLocation",specificLocation);
        map.put("consigneeName",consigneeName);
        super.enqueue(super.getService().giftExchange(map),httpCallback, Constants.REQUEST_GIFT_EXCHANGE, GiftExchangeModel.class);
    }
/*
* 推荐优惠卷
* */
    public void getCoupon(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token,
                                  float price){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("price",price);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("price",price);
        LogUtil.e("__json73求",map+"");
        super.enqueue(super.getService().getCoupon(map),httpCallback, Constants.REQUEST_GET_COUPON, GetCouponModel.class);
    }

    /**
     * 下单并支付
     * @param httpCallback 回调
     * @param timestamp 时间戳
     * @param token token
     * @param goods_id 商品id
     * @param order_amount 	订单金额
     * @param discount 	套餐类型
     * @param coupon_id  优惠券id，有优惠券则传入优惠券id
     * @param coupon_amount 优惠券金额
     * @param pay_type      支付方式，默认为0微信，1为支付宝
     */
    public void createOrderAndPay(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token,
                                  int goods_id,
                                  String order_amount,
                                  int coupon_id,
                                  float coupon_amount,
                                  int pay_type,
                                  int discount,
                                  int u_gift_id){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("goods_id",goods_id);
        mapGetSign.put("order_amount",order_amount);
        mapGetSign.put("coupon_id",coupon_id);
        mapGetSign.put("coupon_amount",coupon_amount);
        mapGetSign.put("pay_type",pay_type);
        mapGetSign.put("discount",discount);
        mapGetSign.put("u_gift_id",u_gift_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("goods_id",goods_id);
        map.put("order_amount",order_amount);
        map.put("coupon_id",coupon_id);
        map.put("coupon_amount",coupon_amount);
        map.put("pay_type",pay_type);
        map.put("discount",discount);
        map.put("u_gift_id",u_gift_id);
       LogUtil.e("__json21求",map+"");
        super.enqueue(super.getService().createOrderAndPay(map),httpCallback, Constants.REQUEST_CREATE_ORDER_AND_PAY, CreateOrderAndPayModel.class);
    }

    /**
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param getAllOrder 是否获取全部订单，不放status，后台返回的是所有订单
     * @param status 订单状态0为未付款，1为已付款
     * @param page
     * @param limit
     */
    public void getMyOrder(IHttpCallback httpCallback,
                             int timestamp,
                             String token,
                           boolean getAllOrder,
                             int status,
                             int page,
                             int limit){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("status",status);
        mapGetSign.put("limit",limit);
        mapGetSign.put("page",page);
        if (!getAllOrder){
            mapGetSign.put("status",status);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        if (!getAllOrder){
            map.put("status",status);
        }
        map.put("limit",limit);
        map.put("page",page);
        super.enqueue(super.getService().getMyOrder(map),httpCallback, Constants.REQUEST_GET_MY_ORDER, MyOrderModel.class);
    }
    /**
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param order_id 订单id
     */
    public void getOrderDetail(IHttpCallback httpCallback,
                           int timestamp,
                           String token,
                           int order_id){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("order_id",order_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("order_id",order_id);
        LogUtil.e("__getOrderDetail_timestamp",timestamp + "");
        LogUtil.e("__getOrderDetail_order_id",order_id + "");
        LogUtil.e("__getOrderDetail_sign",sign + "");
        LogUtil.e("__getOrderDetail_token",token + "");
        super.enqueue(super.getService().getOrderDetail(map),httpCallback, Constants.REQUEST_GET_ORDER_DETAIL, OrderDetailModel.class);
    }

    /**
     * 获取微信登录信息
     * @param httpCallback
     * @param timestamp
     */
    public void getWxLoginInfo(IHttpCallback httpCallback,
                               int timestamp){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getWxLoginInfo(map),httpCallback, Constants.REQUEST_GET_WXLOGIN_INFO, WXLoginInfoModel.class);
    }

    /**
     * 单独支付接口
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param order_id
     * @param order_amount
     * @param pay_type
     */
    public void pay(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token,
                                  int order_id,
                                  String order_amount,
                                  int pay_type){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("order_id",order_id);
        mapGetSign.put("order_amount",order_amount);
        mapGetSign.put("pay_type",pay_type);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("order_id",order_id);
        map.put("order_amount",order_amount);
        map.put("token",token);
        map.put("pay_type",pay_type);
        LogUtil.e("__pay_sign",sign);
        LogUtil.e("__pay_timestamp",timestamp + "");
        LogUtil.e("__pay_token",token);
        LogUtil.e("__pay_order_id",order_id +"");
        LogUtil.e("__pay_order_amount",order_amount);
//        LogUtil.e("__pay_coupon_id",coupon_id+"");
//        LogUtil.e("__pay_coupon_amount",coupon_amount+"");
        LogUtil.e("__pay_pay_type",pay_type+"");
        super.enqueue(super.getService().pay(map),httpCallback, Constants.REQUEST_PAY, CreateOrderAndPayModel.class);
    }

    /**
     * 修改地址
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param province
     * @param city
     */
    public void editAddress(IHttpCallback httpCallback,
                    int timestamp,
                    String token,
                    String province,
                    String city){
//        ,
//        String country
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("province",province);
        mapGetSign.put("city",city);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("province",province);
        map.put("city",city);
        map.put("token",token);
        LogUtil.e("__json"+ Constants.REQUEST_EDIT_ADDRESS+"求",map+"");
        super.enqueue(super.getService().editAddress(map),httpCallback, Constants.REQUEST_EDIT_ADDRESS, EditAddressModel.class);
    }

    /**
     * 提交评论
     * @param httpCallback 回调
     * @param timestamp 时间戳
     * @param token
     * @param content 评论内容
     * @param course_id 课程id
     */
    public void submitComment(IHttpCallback httpCallback,
                    int timestamp,
                    String token,
                    String content,
                    int course_id){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("content",content);
        mapGetSign.put("course_id",course_id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("content",content);
        map.put("course_id",course_id);
        LogUtil.e("__json"+ Constants.REQUEST_SUBMIT_COMMENT+"求",map+"");
        super.enqueue(super.getService().submitComment(map),httpCallback, Constants.REQUEST_SUBMIT_COMMENT, SubmitComment.class);
    }

    /**
     * 创建/修改学习任务，有放ID则修改，没有则是创建
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param taskContent 任务内容
     * @param tipsMode 	提示方式 1表示应用提示 0表示短信提示
     * @param closeDateTime 截止日期
     * @param tipsDateTime 提示日期，空字符串表示不提示
     * @param isFinish 是否已完成 1表示未完成 0表示已完成，修改需要传该参数，创建不传
     * @param id 不传ID表示新增，传ID会修改
     * @param isRevise 是否修改
     */
    public void createStudyPlan(IHttpCallback httpCallback,
                              int timestamp,
                              String token,
                              String taskContent,
                              int tipsMode,
                              String closeDateTime,
                              String tipsDateTime,
                              int isFinish,
                              int  id,
                              boolean isRevise){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("taskContent",taskContent);
        mapGetSign.put("tipsMode",tipsMode);
        mapGetSign.put("closeDateTime",closeDateTime);
        if (tipsDateTime != null && !tipsDateTime.equals("")){
            mapGetSign.put("tipsDateTime",tipsDateTime);
        }
        if (isRevise){
            mapGetSign.put("isFinish",isFinish);
            mapGetSign.put("id",id);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("taskContent",taskContent);
        map.put("tipsMode",tipsMode);
        map.put("closeDateTime",closeDateTime);
        LogUtil.e("__createStudyPlan","sign:" + sign);
        LogUtil.e("__createStudyPlan_token","token:" + token);
        LogUtil.e("__createStudyPlan_timestamp","timestamp:" + timestamp + "");
        LogUtil.e("__createStudyPlan_taskContent","taskContent:" + taskContent);
        LogUtil.e("__createStudyPlan_tipsMode","tipsMode:" + tipsMode  + "");
        LogUtil.e("__createStudyPlan","closeDateTime:" + closeDateTime);
        LogUtil.e("__createStudyPlan","tipsDateTime:" + tipsDateTime);
        if (tipsDateTime != null && !tipsDateTime.equals("")){
            map.put("tipsDateTime",tipsDateTime);
        }
        if (isRevise){
            map.put("isFinish",isFinish);
            map.put("id",id);
            LogUtil.e("__createStudyPlan","isFinish:" + isFinish);
            LogUtil.e("__createStudyPlan","id:" + id);
        }
        super.enqueue(super.getService().createStudyPlan(map),httpCallback, Constants.REQUEST_CREATE_STUDY_PLAN, CreateStudyPlanModel.class);
    }

    /**
     * 获取学习计划
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page 页码
     * @param per_page 每页大小，跟limit一样
     * @param current_date 当前日期，如2019-02-02
     */
    public void getStudyPlan(IHttpCallback httpCallback,
                                int timestamp,
                                String token,
                                int page,
                                int  per_page,
                                String current_date){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        mapGetSign.put("current_date",current_date);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("page",page);
        map.put("per_page",per_page);
        map.put("current_date",current_date);
        for(Map.Entry<String, Object> a:map.entrySet()){
            LogUtil.e("__StudyPlan：值",a.getKey()+":" + a.getValue());
//            System.out.println("键是"+a.getKey());
//            LogUtil.e("__aaa值是",a.getValue()+"");

        }
        super.enqueue(super.getService().getStudyPlan(map),httpCallback, Constants.REQUEST_GET_STUDY_PLAN,StudyPlanModel.class);
    }


    /**
     * 获取我的反馈列表(外面)
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page
     * @param per_page
     */
    public void myFeedBackList(IHttpCallback httpCallback,
                             int timestamp,
                             String token,
                             int page,
                             int  per_page){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("page",page);
        map.put("per_page",per_page);
        super.enqueue(super.getService().myFeedBackList(map),httpCallback, Constants.REQUEST_MY_FEEDBACK_LIST,JSONObject.class);
        LogUtil.e("__json"+Constants.REQUEST_MY_FEEDBACK_MSG+"求",map.toString());
    }
    /**
     * 获取我的反馈列表(对话框里面)
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page
     * @param per_page
     */
    public void myFeedBackMsgList(IHttpCallback httpCallback,
                               int timestamp,
                               String token,
                               int page,
                               int  per_page,
                               int  parent_id){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        if (parent_id != 0){
            mapGetSign.put("parent_id",parent_id);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("page",page);
        map.put("per_page",per_page);
        if (parent_id == 0){
            super.enqueue(super.getService().myFeedBackMsgList(map),httpCallback, Constants.REQUEST_MY_FEEDBACK_LIST,MyFeedbackListModel.class);
        }else {
            map.put("parent_id",parent_id);
            super.enqueue(super.getService().myFeedBackMsgList(map),httpCallback, Constants.REQUEST_MY_FEEDBACK_MSG,MyFeedbackMsgModel.class);
        }
        LogUtil.e("__json"+Constants.REQUEST_MY_FEEDBACK_MSG+"求",map.toString());
    }


    /**
     * 签到接口
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void userSign(IHttpCallback httpCallback,
                                  int timestamp,
                                  String token){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().userSign(map),httpCallback, Constants.REQUEST_USER_SIGN,UserSignModel.class);
    }
    /**
     * 获取学习干货文章详情
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param id
     */
    public void getStudyKeyDetail(IHttpCallback httpCallback,
                             int timestamp,
                             String token,
                             int id){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("id",id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("id",id);

        super.enqueue(super.getService().getStudyKeyDetail(map),httpCallback, Constants.REQUEST_GET_STUDY_KEY_DETAIL,StudyKeyDetailModel.class);
    }

    /**
     * 我的收藏
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param page 页码
     * @param per_page 每页数量
     * @param isGetAll 是否获取全部，如果获取全部则不放subject
     * @param subject 科目
     */
    public void getMyConllection(IHttpCallback httpCallback,
                             int timestamp,
                             String token,
                             int page,
                             int  per_page,
                             boolean isGetAll,
                             int subject){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        if (!isGetAll){
            mapGetSign.put("subject",subject);
        }
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("page",page);
        map.put("per_page",per_page);
        if (!isGetAll){
            map.put("subject",subject);
        }
        super.enqueue(super.getService().getMyConllection(map),httpCallback, Constants.REQUEST_GET_MY_CONLLECTION,MyColletcModel.class);
    }

    /**
     * 学习干货点赞
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param id
     */
    public void studyKeyLike(IHttpCallback httpCallback,
                             int timestamp,
                             String token,
                             int id){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("id",id);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("id",id);
        LogUtil.e("__getStudyKeyDetail","timestamp:" + timestamp);
        LogUtil.e("__getStudyKeyDetail","sign:" + sign);
        LogUtil.e("__getStudyKeyDetail","id:" + id);
        LogUtil.e("__getStudyKeyDetail","token:" + token);
        super.enqueue(super.getService().studyKeyLike(map),httpCallback, Constants.REQUEST_STUDY_KEY_LIKE, StudyKeyLikeModel.class);
    }

    /**
     * 修改个性签名
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void editSignature(IHttpCallback httpCallback,
                            int timestamp,
                            String token,
                            String editsignature){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("editsignature",editsignature);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("editsignature",editsignature);
        super.enqueue(super.getService().editSignature(map),httpCallback, Constants.REQUEST_EDIT_SIGNATURE, EditSignatureModel.class);
    }

    /**
     * 修改昵称
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param wechatNickName
     */
    public void editNickName(IHttpCallback httpCallback,
                              int timestamp,
                              String token,
                              String wechatNickName){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("wechatNickName",wechatNickName);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("wechatNickName",wechatNickName);
        super.enqueue(super.getService().editNickName(map),httpCallback, Constants.REQUEST_EDIT_NICKNAME, EditNicknameModel.class);
    }

    /**
     * 修改手机号码获取验证码接口
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param phone
     */
    public void editPhoneGetVerifyCode(IHttpCallback httpCallback,
                             int timestamp,
                             String token,
                             String phone){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("phone",phone);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("phone",phone);
        super.enqueue(super.getService().editPhoneGetVerifyCode(map),httpCallback, Constants.REQUEST_EDIT_PHONE_GET_VERSIFY_CODE, EditPhoneGetVerifyCodeModel.class);
    }

    /**
     * 修改手机号码
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param phone
     * @param verify_id 验证码ID
     * @param code 验证码
     */
    public void editPhone(IHttpCallback httpCallback,
                                       int timestamp,
                                       String token,
                                       String phone,
                                        String verify_id,
                                        String code){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("phone",phone);
        mapGetSign.put("verify_id",verify_id);
        mapGetSign.put("code",code);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("phone",phone);
        map.put("verify_id",verify_id);
        map.put("code",code);
        super.enqueue(super.getService().editPhone(map),httpCallback, Constants.REQUEST_EDIT_PHONE, EditPhoneModel.class);
    }


    /**
     * 获取用户信息
     * @param httpCallback
     * @param timestamp
     * @param token
     */
    public void getUserInfo(IHttpCallback httpCallback,
                              int timestamp,
                              String token){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        super.enqueue(super.getService().getUserInfo(map),httpCallback, Constants.REQUEST_GET_USER_INFO, UserInfoModel.class);
    }

    /**
     * 我的收藏
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param couponStatus 优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
     * @param page 页码
     * @param per_page 请求数据数量
     */
    public void getMyCoupon(IHttpCallback httpCallback,
                            int timestamp,
                            String token,
                            int couponStatus,
                            int page,
                            int per_page){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("couponStatus",couponStatus);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("couponStatus",couponStatus);
        map.put("page",page);
        map.put("per_page",per_page);
        super.enqueue(super.getService().getMyCoupon1(map),httpCallback, Constants.REQUEST_GET_MY_COUPON, MyCouponModel.class);
    }
    /**
     * 优惠券 - 排序
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param couponStatus 优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
     * @param page 页码
     * @param per_page 请求数据数量
     */
    public void getMyCoupon1(IHttpCallback httpCallback,
                            int timestamp,
                            String token,
                            int couponStatus,
                            int page,
                            int per_page,
                             float price){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("couponStatus",couponStatus);
        mapGetSign.put("page",page);
        mapGetSign.put("per_page",per_page);
        mapGetSign.put("price",price);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("couponStatus",couponStatus);
        map.put("page",page);
        map.put("per_page",per_page);
        map.put("price",price);

        super.enqueue(super.getService().getMyCoupon1(map),httpCallback, Constants.REQUEST_GET_MY_COUPON1, MyCouponModel.class);
    }

    /*
    * 部编版页面（专题推荐接口）
    * */

    public  void getAoePPT(IHttpCallback httpCallback,
                           int timestamp,
                           String token,
                           int limit,
                           int page){
        //获取sign的map
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("limit",limit);
        mapGetSign.put("page",page);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求参数map
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        map.put("token",token);
        map.put("limit",limit);
        map.put("page",page);

        super.enqueue(super.getService().getAoePPT(map),httpCallback, Constants.REQUEST_GET_AOEPPT, AoePPTModel.class);

    }

    /**
     * 添加收藏
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param courseId 课程ID
     */
    public void addConllection(IHttpCallback httpCallback,
                            int timestamp,
                            String token,
                            int courseId){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("courseId",courseId);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("courseId",courseId);
        LogUtil.e("__add_sign",sign);
        LogUtil.e("__add_token",token);
        LogUtil.e("__add_timestamp",timestamp + "");
        LogUtil.e("__add_courseId",courseId + "");
        super.enqueue(super.getService().addConllection(map),httpCallback, Constants.REQUEST_ADD_CONLLECTION, AddCollectionModel.class);
    }

    /**
     * 删除收藏
     * @param httpCallback
     * @param timestamp
     * @param token
     * @param courseId 课程ID
     */
    public void delConllection(IHttpCallback httpCallback,
                               int timestamp,
                               String token,
                               int courseId){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("courseId",courseId);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("courseId",courseId);
        LogUtil.e("__del_sign",sign);
        LogUtil.e("__del_token",token);
        LogUtil.e("__del_timestamp",timestamp + "");
        LogUtil.e("__add_courseId",courseId + "");
        super.enqueue(super.getService().delConllection(map),httpCallback, Constants.REQUEST_DEL_CONLLECTION, AddCollectionModel.class);
    }

    /*
    * 兑换码
    * */
    public void exchangeCode(IHttpCallback httpCallback,
                               int timestamp,
                               String token,
                               String code){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
//        mapGetSign.put("token",token);
        mapGetSign.put("code",code);
        String sign = CommonUtils.getSign(mapGetSign);
        LogUtil.e("__getSign",sign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("code",code);
        map.put("timestamp", timestamp);
        //map.put("code", code);
        LogUtil.e("__sign",sign);
        LogUtil.e("__timestamp",timestamp + "");
        LogUtil.e("__token",token);
        //LogUtil.e("__code",code);
        Log.e("__json71求", map + "");
        super.enqueue(super.getService().exchangeCode(map), httpCallback, Constants.REQUEST_GET_EXCHANGE_CODE, ExchangeCodeModel.class);
    }

    /*
    * 兑换码记录
    * */
    public void exchangeCodeLog(IHttpCallback httpCallback,
                               int timestamp,
                               String token){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp", timestamp);
        Log.e("__json72求", map + "");
        super.enqueue(super.getService().myExchangeLog(map), httpCallback, Constants.REQUEST_GET_EXCHANGE_LOG, ExchangeCodeRecordsModel.class);
    }
    /*
    * 获取最新的版本号
    * */
    public void getVersion(IHttpCallback httpCallback,
                               int timestamp,
                               String token){
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSign(mapGetSign);
        //请求map放入参数
        HashMap<String,Object> map = new HashMap<>();
        map.put("sign",sign);
        map.put("token",token);
        map.put("timestamp", timestamp);
        Log.e("__json75求", map + "");
        super.enqueue(super.getService().getVersion(map), httpCallback, Constants.REQUEST_GET_VERSION, GetVersion.class);
    }
}
