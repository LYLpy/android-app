package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.MImageGetter;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.StudyKeyDetailModel;
import net.getlearn.getlearn_android.model.bean.StudyKeyLikeModel;
import net.getlearn.getlearn_android.utils.BrandUtil;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;
import retrofit2.http.Url;

public class ReadActivity extends BaseActivity {

//    @BindView(R.id.return_text)
//    LinearLayout mReturnText;
@BindView(R.id.fl_back)
FrameLayout mFlBack;
    @BindView(R.id.lv_image_share)
    LinearLayout mLvImageShare;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_click)
    TextView mTvClick;
    @BindView(R.id.tv_article)
    TextView mTvArticle;
    @BindView(R.id.tv_like)
    TextView mTvLike;
    @BindView(R.id.ll_like)
    LinearLayout mLlLike;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    private int id;
    private LevelListDrawable mDrawable = new LevelListDrawable();
    private StudyKeyDetailModel.Databean mDatabean;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            case R.id.button1 :
//                String str = max.getText()+"";
//                int num=0;
//                num++;
//                str=num+"";
//                max.setText(str);
////                Toast.makeText(ReadActivity.this,"点赞",Toast.LENGTH_LONG).show();
//                break;
//            case R.id.button2:
////                Toast.makeText(ReadActivity.this,"分享",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(ReadActivity.this,OpinonActivity.class);
//                startActivity(intent);
//                break;
            case R.id.ll_share:
                showShare();
                LogUtil.e("__ll_share","ll_share");
                break;
            case R.id.ll_like:
                mHttpHelper.studyKeyLike(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),id);
                break;
            case R.id.fl_back:
                finish();
//                Toast.makeText(ReadActivity.this,"触发返回键",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    /**
     * 展示分享界面
     */
    private void showShare() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon, options);
        String title;//分享标题
        String content = "更多内容，敬请下载格灵同步培优";//分享内容
//        String shareUrl = "https://sj.qq.com/myapp/detail.htm?apkName=net.getlearn.getlearn_android";//应用宝下载链接
//        String shareUrl = "https://shouji.baidu.com/software/26541183.html";//百度手机助手

        String shareUrl = "https://shouji.baidu.com/software/26526059.html";//百度手机助手
        if (mDatabean!=null && mDatabean.getTitle() !=null && !mDatabean.getTitle().equals("")){
            title = mDatabean.getTitle();
        }else {
            title = "格灵同步培优";
        }
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle(getString(R.string.share));
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/storage/emulated/0/Pictures/IMG_1565532166852.jpg");//确保SDcard下面存在此张图片
//        oks.setImageUrl("https://e.topthink.com/Uploads/Picture/2018-10-29/5bd6d218d381b.jpg");//网络图片
        oks.setImageData(bitmap);//Bitmap图片
        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");

        oks.setUrl(shareUrl);

        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        oks.setPlatform();
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        mHttpHelper.getStudyKeyDetail(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), id);
        mFlBack.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_article_text;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mLlLike.setOnClickListener(this);
        mLlShare.setOnClickListener(this);
    }

    /**
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的javabean数据
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_STUDY_KEY_DETAIL:
                StudyKeyDetailModel studyKeyModel = (StudyKeyDetailModel) msg.obj;
                mDatabean = studyKeyModel.getData();
                LogUtil.e("__STUDY_KEY_DETAIL", studyKeyModel.getData().getContent());
                setData();
                break;
            case Constants.REQUEST_STUDY_KEY_LIKE:
                StudyKeyLikeModel studyKeyLikeModel = (StudyKeyLikeModel) msg.obj;
                mTvLike.setText("赞（" + studyKeyLikeModel.getData().getFabulous() + "）");
                if(studyKeyLikeModel.getData().getStatus().equals("cancel")){
//                if(studyKeyLikeModel.getData().getStatus().equals("click")){
                    ToastUtil.showToast("取消成功");
                }else {
                    ToastUtil.showToast("点赞成功");
                }
                break;
            case Constants.REQUEST_SHARE:
                LogUtil.e("__readAct","share_success");
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_GET_STUDY_KEY_DETAIL:
                ToastUtil.showToast("获取文章内容失败，请重试");
                break;
            case Constants.REQUEST_STUDY_KEY_LIKE:
                ToastUtil.showToast("点赞失败，请重试");
                break;
            case Constants.REQUEST_SHARE:
                LogUtil.e("__readAct","share_error");
                break;
        }
    }

    public int loadImg = 111;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == loadImg) {
                Bitmap bitmap = (Bitmap) msg.obj;
                BitmapDrawable drawable = new BitmapDrawable(null, bitmap);
                mDrawable.addLevel(1, 1, drawable);
//                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());

                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);

                CharSequence charSequence = mTvArticle.getText();
                mTvArticle.setText(charSequence);
                mTvArticle.invalidate();
            }
        }
    };
    private void setData() {
        mTvClick.setText(mDatabean.getRead_volume() + "人已读");
        mTvTime.setText(mDatabean.getAddtime());
        mTvTitle.setText(mDatabean.getTitle());
//        if (mDatabean.isStatus()){
//            mTvLike.setText("已赞（" + mDatabean.getFabulous() + "）");
//        }else {
//            mTvLike.setText("赞（" + mDatabean.getFabulous() + "）");
//        }
        mTvLike.setText("赞（" + mDatabean.getFabulous() + "）");
        mTvArticle.setText(Html.fromHtml(mDatabean.getContent(), new MImageGetter(mTvArticle, getApplicationContext()), null));
    }

    //EventBus接收微信分享回调
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
//            LogUtil.e("__handleData_getType",mMessageEvent.getType() + "");
        if (mMessageEvent.getType() == Constants.REQUEST_SHARE){
            LogUtil.e("__handleData_readAct", Constants.REQUEST_SHARE + "");
            //发送到后台记录分享，不需要回调处理
            mHttpHelper.share(null,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                    UserManager.getInstance().getUserId(), CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()),1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
