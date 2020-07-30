package net.getlearn.getlearn_android.model;

import com.google.gson.JsonObject;

import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/18------更新------
 * 封装Retrofit所有get和post请求
 */

public interface IHttpService {

    String KEY = "b6c3b6e7e6c7b909";

   String BASE_URL = "http://xxtbpy.getlearn.cn";//正式域名
//String BASE_URL = "http://test-xxtbpy.getlearn.cn/";//测试域名
//   String BASE_URL = "http://newxxtbpy.getlearn.net/";//新测试域名


    /** 签名算法 */
    int STUDY_INDEX = 2;
        // Call<Home> getHome();
        // 可以设置类型为gson库中的JsonObject, 然后进行手动解析json
    //首页banner图接口，参数用map形式传入
    @GET("api/v1_1/index/banner")
    Call<JsonObject> getBanner(@QueryMap Map<String, Object> map);
    //部编版（专题推荐接口）
    @GET("api/v1_1/special_course/getspecialcourse")
    Call<JsonObject> getAoePPT(@QueryMap Map<String, Object> map);

    //获取登录/注册验证码，参数用map形式传入
    @GET("api/v1_1/login/index")
    Call<JsonObject> getVerificationCode(@QueryMap Map<String, Object> map);

    //获取年级接口，参数用map形式传入
    @GET("api/v1_1/index/getgrade")
    Call<JsonObject> getGrade(@QueryMap Map<String, Object> map);

    //获取科目和版本
    @GET("api/v1_1/index/subjectVersionClassification")
    Call<JsonObject> getSubjectVersionClassification(@QueryMap Map<String, Object> map);

    //首页同步课程接口
    @GET("api/v1_1/index/index")
    Call<JsonObject> getSyncSubject(@QueryMap Map<String, Object> map);

    //用户协议
    @GET("api/v1_1/protocol/protocol")
    Call<JsonObject> getUserProtocol(@QueryMap Map<String, Object> map);

    //常见问题
    @GET("api/v1_1/protocol/question")
    Call<JsonObject> getCommonQuestion(@QueryMap Map<String, Object> map);

    //获取活动连接
    @GET("api/v1_1/share/getActive")
    Call<JsonObject> getActive(@QueryMap Map<String, Object> map);

    //保密协议
    @GET("api/v1_1/protocol/userSecrecy")
    Call<JsonObject> getUserSecrecy(@QueryMap Map<String, Object> map);

    //获取课程介绍
    @GET("api/v1_1/curriculum/getintroduce")
    Call<JsonObject> getSubjectIntroduce(@QueryMap Map<String, Object> map);

    //获取首页推荐课程
    @GET("api/v1_1/curriculum/getrecommendedcourses")
    Call<JsonObject> getRecommendSubjectHome(@QueryMap Map<String, Object> map);

    //获取用户选择的科目版本信息
    @GET("api/v1_1/curriculum/getUserCourse")
    Call<JsonObject> getUserSelectedVersion(@QueryMap Map<String, Object> map);

    //获取课程列表接口
    @GET("api/v1_1/curriculum/getList")
    Call<JsonObject> getSubjectList(@QueryMap Map<String, Object> map);

    //获取课程学员评论
    @GET("api/v1_1/curriculum/getcomment")
    Call<JsonObject> getStudentComment(@QueryMap Map<String, Object> map);

    //首页学习干货
    @GET("api/v1_1/study_goods/index")
    Call<JsonObject> getStudyKeyHome(@QueryMap Map<String, Object> map);

    //选课界面获取分类
    @GET("api/v1_1/curriculum/classify")
    Call<JsonObject> getClassify(@QueryMap Map<String, Object> map);

    //获取选课科目
    @GET("api/v1_1/index/subject")
    Call<JsonObject> getSubject(@QueryMap Map<String, Object> map);

    //获取我的优惠券 会员中心
    @GET("api/v1_1/gift/myCouponList")
    Call<JsonObject> getMyCoupon(@QueryMap Map<String, Object> map);

    //获取我的优惠券 会员中心
    @GET("api/v1_1/gift/myCouponListSortByPrice")
    Call<JsonObject> getMyCoupon1(@QueryMap Map<String, Object> map);

    //获取选课课程列表接口
    @GET("api/v1_1/curriculum/index")
    Call<JsonObject> getSelectSubjectList(@QueryMap Map<String, Object> map);

    //选课获取更多课程接口
    @GET("api/v1_1/curriculum/moreclass")
    Call<JsonObject> getMoreClass(@QueryMap Map<String, Object> map);

    //名师专栏名师列表
    @GET("api/v1_1/famous_teacher/index")
    Call<JsonObject> getFamousTeacher(@QueryMap Map<String, Object> map);

    //获取名师简介
    @GET("api/v1_1/famous_teacher/introduction")
    Call<JsonObject> getFamousTeacherIntroduction(@QueryMap Map<String, Object> map);


    //课程搜索接口
    @GET("api/v1_1/curriculum/classSearch")
    Call<JsonObject> search(@QueryMap Map<String, Object> map);

    //我的学习列表
    @GET("api/v1_1/study/myclasslist")
    Call<JsonObject> getMyClassList(@QueryMap Map<String, Object> map);


    //首页名师专栏
    @GET("api/v1_1/famous_teacher/getFamousTeacher")
    Call<JsonObject> getFamousTeacherHome(@QueryMap Map<String, Object> map);

    //获取视频播放地址
    @GET("api/v1_1/curriculum/getplayurl")
    Call<JsonObject> getVideoUrl(@QueryMap Map<String, Object> map);


    //获取会员优惠套餐
    @GET("api/v1_1/app_set_meal/getmeal")
    Call<JsonObject> getPrice(@QueryMap Map<String, Object> map);

    //我的订单
    @GET("api/v1_1/order/index")
    Call<JsonObject> getMyOrder(@QueryMap Map<String, Object> map);

    //订单详情
    @GET("api/v1_1/order/detail")
    Call<JsonObject> getOrderDetail(@QueryMap Map<String, Object> map);

    //获取微信登录信息
    @GET("api/v1_1/wx_login/wxlogin")
    Call<JsonObject> getWxLoginInfo(@QueryMap Map<String, Object> map);

    //获取礼品列表
    @GET("api/v1_1/gift/giftList")
    Call<JsonObject> getGiftList(@QueryMap Map<String, Object> map);


    //获取学习计划
    @GET("api/v1_1/study/index")
    Call<JsonObject> getStudyPlan(@QueryMap Map<String, Object> map);

    //获取个人信息接口
    @GET("api/v1_1/personal/getuserinfo")
    Call<JsonObject> getUserInfo(@QueryMap Map<String, Object> map);

    //首页我的
    @GET("api/v1_1/personal/index")
    Call<JsonObject> getPersonalHome(@QueryMap Map<String, Object> map);

    //我的收藏
    @GET("api/v1_1/Conllection/index")
    Call<JsonObject> getMyConllection(@QueryMap Map<String, Object> map);

    //删除收藏
    @DELETE("api/v1_1/Conllection/delConllection")
    Call<JsonObject> delConllection(@QueryMap Map<String, Object> map);

    //获取个人信息
    @GET("api/v1_1/personal/info")
    Call<JsonObject> getPersonalInfo(@QueryMap Map<String, Object> map);

    //获取我的学习列表
    @GET("api/v1_1/study/myclasslist")
    Call<JsonObject> getMyStudyList(@QueryMap Map<String, Object> map);

    //获取学习干货文章接口
    @GET("api/v1_1/study_goods/detail")
    Call<JsonObject> getStudyKeyDetail(@QueryMap Map<String, Object> map);


    //获取本周签到情况
    @POST("api/v1_1/sign/index")
    @FormUrlEncoded
    Call<JsonObject> getWeekSign(@FieldMap Map<String, Object> map);

    //生成活动海报
    @POST("api/v1_1/share/makeposter")
    @FormUrlEncoded
    Call<JsonObject> makeposter(@FieldMap Map<String, Object> map);


    //退出登录
    @POST("api/v1_1/personal/loginout")
    @FormUrlEncoded
    Call<JsonObject> loginout(@FieldMap Map<String, Object> map);


    //分享记录接口
    @POST("api/v1_1/sign/finishShare")
    @FormUrlEncoded
    Call<JsonObject> share(@FieldMap Map<String, Object> map);

    //我的礼品
    @GET("api/v1_1/gift/myGiftList")
    Call<JsonObject> myGiftList(@QueryMap Map<String, Object> map);


    //签名算法接口，测试用
    @POST("study/index")
    @FormUrlEncoded
    Call<JsonObject> getStudyIndex(@FieldMap Map<String, Object> map);

    //签到接口
    @POST("api/v1_1/sign/userSign")
    @FormUrlEncoded
    Call<JsonObject> userSign(@FieldMap Map<String, Object> map);

    //获取我的反馈列表(外面)
    @GET("api/v1_1/Feedback/messageOutsideList")
    Call<JsonObject> myFeedBackList(@QueryMap Map<String, Object> map);

    //获取我的反馈(对话框里面)
    @GET("api/v1_1/Feedback/messageList")
    Call<JsonObject> myFeedBackMsgList(@QueryMap Map<String, Object> map);

    //登录,参数用map形式传入
    @POST("api/v1_1/login/index")
    @FormUrlEncoded
    Call<JsonObject> login(@FieldMap Map<String, Object> map);

    //新增意见反馈
    @POST("api/v1_1/Feedback/messageAdd")
    @FormUrlEncoded
    Call<JsonObject> feedback(@FieldMap Map<String, Object> map);

    //新增意见反馈(多图)
    @POST("api/v1_1/Feedback/messageAdd")
    @FormUrlEncoded
    Call<JsonObject> feedbackimgs(@FieldMap Map<String, Object> map);


    //今日挑战
    @POST("api/v1_1/sign/getTodayChallenge")
    @FormUrlEncoded
    Call<JsonObject> getTodayChallenge(@FieldMap Map<String, Object> map);

    //本周挑战
    @POST("api/v1_1/sign/getWeekChallenge")
    @FormUrlEncoded
    Call<JsonObject> getWeekChallenge(@FieldMap Map<String, Object> map);

    //修改用户地址
    @POST("api/v1_1/personal/editAddress")
    @FormUrlEncoded
    Call<JsonObject> editAddress(@FieldMap Map<String, Object> map);

    /**
     * 单张图片接口
     * @param map
     * @return
     */
    @POST("api/v1_1/uploads/baseupload")
    @FormUrlEncoded
    Call<JsonObject> uploadImg(@FieldMap Map<String, Object> map);


    //上传多张图片图片接口，用base64格式上传，后台会返回图片url，用于辅助新增意见反馈提交
    @POST("api/v1_1/uploads/baseuploads")
    @FormUrlEncoded
    Call<JsonObject> uploadsImg(@Field("img[]")String[] img, @Field("sign")String sign, @Field("timestamp")int timestamp);

    //礼品兑换接口
    @POST("api/v1_1/gift/exchange")
    @FormUrlEncoded
    Call<JsonObject> giftExchange(@FieldMap Map<String, Object> map);

    //添加收藏
    @POST("api/v1_1/Conllection/addConllection")
    @FormUrlEncoded
    Call<JsonObject> addConllection(@FieldMap Map<String, Object> map);

    //学习干货点赞
    @POST("api/v1_1/study_goods/fabulous")
    @FormUrlEncoded
    Call<JsonObject> studyKeyLike(@FieldMap Map<String, Object> map);

    //保存用户选择科目和版本
    @POST("api/v1_1/index/subSelectClassify")
    @FormUrlEncoded
    Call<JsonObject> saveSubjectAndVersion(@FieldMap Map<String, Object> map);


    //修改年级和和版本
    @POST("api/v1_1/personal/editSelectGrade")
    @FormUrlEncoded
    Call<JsonObject> editSelectGradeAndVersion(@FieldMap Map<String, Object> map);

    //微信联合登录
    @POST("api/v1_1/wx_login/index")
    @FormUrlEncoded
    Call<JsonObject> WXLogin(@FieldMap Map<String, Object> map);

    //下单并支付接口
    @POST("api/v1_1/order/createorder")
    @FormUrlEncoded
    Call<JsonObject> createOrderAndPay(@FieldMap Map<String, Object> map);

    //推荐优惠卷接口
    @GET("api/v1_1/gift/getCoupon")
    Call<JsonObject> getCoupon(@QueryMap Map<String, Object> map);

    //单独支付接口
    @POST("api/v1_1/order/pay")
    @FormUrlEncoded
    Call<JsonObject> pay(@FieldMap Map<String, Object> map);

    //新建学习计划
    @POST("api/v1_1/study/studyplan")
    @FormUrlEncoded
    Call<JsonObject> createStudyPlan(@FieldMap Map<String, Object> map);

    //修改个性签名
    @POST("api/v1_1/personal/editsignature")
    @FormUrlEncoded
    Call<JsonObject> editSignature(@FieldMap Map<String, Object> map);

    //修改昵称
    @POST("api/v1_1/personal/editWechatNickName")
    @FormUrlEncoded
    Call<JsonObject> editNickName(@FieldMap Map<String, Object> map);

    //修改头像
    @POST("api/v1_1/personal/editHead")
    @FormUrlEncoded
    Call<JsonObject> editHead(@FieldMap Map<String, Object> map);


    //修改手机号码的验证码接口
    @POST("api/v1_1/personal/getVerificationCode")
    @FormUrlEncoded
    Call<JsonObject> editPhoneGetVerifyCode(@FieldMap Map<String, Object> map);

    //修改手机号码
    @POST("api/v1_1/personal/updatePhone")
    @FormUrlEncoded
    Call<JsonObject> editPhone(@FieldMap Map<String, Object> map);

    //提交评论
    @POST("api/v1_1/curriculum/comment")
    @FormUrlEncoded
    Call<JsonObject> submitComment(@FieldMap Map<String, Object> map);

    //兑换码
    @POST("api/v1_1/exchange_code/exchange")
    @FormUrlEncoded
    Call<JsonObject>exchangeCode(@FieldMap Map<String,Object> map);

    //兑换码记录
    @GET("api/v1_1/exchange_code/myExchangeLog")
    Call<JsonObject>myExchangeLog(@QueryMap Map<String,Object> map);


   //获取后台版本号
   @GET("api/v1_1/app_version/getVersion")
   Call<JsonObject>getVersion(@QueryMap Map<String,Object> map);
    //vivo设置更新接口

//    @POST("https://developer-api.vivo.com.cn/gateway/developer/app/publish/v1/get-online-version")
//    @FormUrlEncoded
//    Call<JsonObject> vivo(@Header("content-type: application/json")  @FieldMap Map<String, Object> map);

}
