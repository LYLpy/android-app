package net.getlearn.getlearn_android;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/2------更新------
 * 常量
 */

public interface Constants {

    // 标记rv的item的类型，头部和底部
    int ITEM_TYPE_HEADER = 0;
    int ITEM_TYPE_CONTENT = 1;
    int ITEM_TYPE_BOTTOM = 2;

    //网络请求类型
    int REQUEST_VERIFICATIONCODE = 0;//获取验证码
    int REQUEST_LOGIN = 1;//登录
    int REQUEST_BANNER = 2;//轮播图
    int REQUEST_GET_GRADE = 3;//获取年级
    int REQUEST_GET_SUBJECT_VERSION_CLASSIFICATION = 4;//获取科目和版本
    int REQUEST_SAVE_SUBJECT_AND_VERSION = 5;//保存用户选择科目和版本

    int REQUEST_GET_USER_SELECTED_VERSION = 6;//获取用户选择的科目版本信息
    int REQUEST_GET_SUBJECT_INTRODUCE = 7;//获取课程介绍
    int REQUEST_GET_SUBJECT_LIST = 8;//获取课程列表
    int REQUEST_GET_STUDENT_COMMENT = 9;//获取学员评论
    int REQUEST_GET_STUDY_KEY_HOME = 10;//获取首页学习干货
    int REQUEST_GET_RECOMMEND_SUBJECT_HOME = 11;//获取首页推荐课程
    int REQUEST_GET_FAMOUS_TEACHER_HOME = 12;//获取首页名师专栏
    int REQUEST_GET_VIDEO_URL = 13;//获取视频播放Url

    int REQUEST_GET_CLASSIFY = 14;//选课界面获取课程分类
    int REQUEST_GET_SUBJECT = 15;//选课界面获取科目
    int REQUEST_GET_SELECT_SUBJECT_LIST = 16;//获取选课列表
    int REQUEST_GET_MORE_CLASS = 17;//选课获取更多课程
    int REQUEST_SEARCH = 18;//搜索课程

    int REQUEST_WX_LOGIN = 19;//微信登录
    int REQUEST_GET_PRICE = 20;//获取会员优惠套餐
    int REQUEST_CREATE_ORDER_AND_PAY = 21;//下单并支付
    int REQUEST_GET_MY_ORDER = 22;//获取我的订单
    int REQUEST_GET_ORDER_DETAIL = 23;//获取我的订单
    int REQUEST_GET_WXLOGIN_INFO = 24;//获取微信登录信息
    int REQUEST_PAY = 25;//单独支付
    int REQUEST_GET_SYNC_SUBJECT_HOME = 26;//首页获取同步课程
    int REQUEST_CREATE_STUDY_PLAN = 27;//新建学习计划
    int REQUEST_SUBMIT_COMMENT = 28;//提交评论
    int REQUEST_GET_STUDY_PLAN = 29;//获取学习计划
    int REQUEST_GET_USER_INFO = 30;//获取个人信息接口
    int REQUEST_GET_PERSONAL_HOME = 31;//首页--我的
    int REQUEST_GET_MY_CONLLECTION = 32;//我的收藏
    int REQUEST_GET_MY_COUPON = 33;//我的优惠券
    int REQUEST_GET_GIFT_LIST = 34;//获取礼品列表
    int REQUEST_GIFT_EXCHANGE = 35;//兑换礼品
    int REQUEST_ADD_CONLLECTION = 36;//添加收藏
    int REQUEST_GET_PERSONAL_INFO = 37;//获取个人信息接口
    int REQUEST_DEL_CONLLECTION = 38;//删除收藏
    int REQUEST_GET_MY_STUDY_LIST = 39;//获取我的学习列表
    int REQUEST_GET_STUDY_KEY_DETAIL = 40;//获取学习干货文章
    int REQUEST_GET_FAMOUS_TEACHER = 41;//获取名师专栏
    int REQUEST_GET_FAMOUS_TEACHER_INTRODUCTION = 42;//获取名师简介
    int REQUEST_GET_VIDEO_URL_AND_PLAY = 43;//获取视频播放Url
    int REQUEST_UPLOAD_IMGS = 44;//获取上传多图片接口,BASE64格式
    int REQUEST_FEEDBACK_MSG = 45;//提交意见反馈--文字
    int REQUEST_FEEDBACK_IMG = 46;//提交意见反馈--图片
    int REQUEST_UPLOAD_IMG_SINGLE = 47;//上传单张图片接口,BASE64格式
    int REQUEST_STUDY_KEY_LIKE = 48;//学习干货点赞
    int REQUEST_TODAY_CHALLENGE = 49;//今日挑战
    int REQUEST_WEEK_CHALLENGE = 50;//本周挑战
    int REQUEST_GET_WEEK_SIGN = 51;//本周签到情况
    int REQUEST_MY_GIFT_LIST = 52;//我的礼品
    int REQUEST_MY_FEEDBACK_LIST = 53;//我的反馈(外面)
    int REQUEST_MY_FEEDBACK_MSG = 54;//我的反馈(对话框里面)
    int REQUEST_USER_SIGN = 55;//签到接口
    int REQUEST_EDIT_ADDRESS = 56;//修改用户地址
    int REQUEST_EDIT_SIGNATURE = 57;//修改个性签名
    int REQUEST_EDIT_NICKNAME = 58;//修改昵称
    int REQUEST_EDIT_PHONE_GET_VERSIFY_CODE = 59;//修改手机号码获取验证码接口
    int REQUEST_EDIT_PHONE = 60;//修改手机号码接口
    int REQUEST_SHARE = 61;//分享
    int REQUEST_EDIT_HEAD = 62;//修改头像
    int REQUEST_EDIT_GRADE_AND_VERSION = 63;//修改年级和版本
    int REQUEST_USER_PROTOCOL = 64;//用户协议
    int REQUEST_USER_SECRECY = 65;//保密协议
    int REQUEST_COMMON_QUESTION = 66;//常见问题
    int REQUEST_LOGIN_OUT = 67;//退出登录
    int REQUEST_GET_ACTIVE = 68;//获取活动连接
    int REQUEST_MAKE_POSTER = 69;//生成活动海报
    int REQUEST_GET_AOEPPT = 70;//获取部编版页面（专题推荐）接口
    int REQUEST_GET_EXCHANGE_CODE = 71;//兑换码
    int REQUEST_GET_EXCHANGE_LOG = 72;//兑换码
    int REQUEST_GET_COUPON = 73;//推荐优惠券
    int REQUEST_GET_MY_COUPON1 =74;
    int REQUEST_GET_VERSION =75;//获取后台版本号
    //默认
    int DEFAULT = -1;
    //优惠券状态
     int  COUPON_STATUS_NOT_USED = 1;//未使用
     int  COUPON_STATUS_USED = 0;//已使用
     int  COUPON_STATUS_EXPIRED = -1;//已过期
     int  ALL = 2;//全部

}
