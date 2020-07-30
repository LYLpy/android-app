package net.getlearn.getlearn_android.activity.webview;

import android.annotation.SuppressLint;
import android.os.Message;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.BaseActivity;
import net.getlearn.getlearn_android.model.bean.GetActiveModel;
import net.getlearn.getlearn_android.model.bean.MakePosterModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * ------author----------日期--------改动-------
 * 邀请好友webView的activity
 */

public class InvitationWebViewActivity extends BaseActivity {

//    字符串常量，作为android与js通信的接口，即字符串映射对象
    public static final String JAVAINTERFACE = "android";
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.toolbar)
    RelativeLayout mToolBar;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    @BindView(R.id.webview)
    WebView mWebview;
    private String mUrl = "";
    private WebSettings mSettings;
    private GetActiveModel.Databean mDatabean;//请求活动链接bean
    private MakePosterModel.Databean mDatabeanPoster;//生成活动海报bean
    @SuppressLint("JavascriptInterface")
    @Override
    protected void initData() {
        mSettings = mWebview.getSettings();
        //设置加载进来的页面自适应手机屏幕，第一个方法设置webview推荐使用的窗口，设置为true。第二个方法是设置webview加载的页面的模式，也设置为true
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        mSettings.setJavaScriptEnabled(true); //允许在WebView中使用js
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                LogUtil.e("__onJsAlert","message:" + message);
                return super.onJsAlert(view, url, message, result);
            }
        });
        mHttpHelper.getActive(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken());
//        mToolBar.setVisibility(View.GONE);
//        showToolBar(false);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_ACTIVE:
                GetActiveModel getActiveModel = (GetActiveModel) msg.obj;
                mDatabean = getActiveModel.getData();
                mWebview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });
                mWebview.loadUrl(mDatabean.getActive_link());
                // 将Android里面定义的类对象JAVAINTERFACE暴露给javascript
                mWebview.addJavascriptInterface(this, JAVAINTERFACE);
//                delayHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        LogUtil.e("__run","javascript:abc");
//                        mWebview.loadUrl("javascript:abc('123')");
//                    }
//                }, 500);
                break;
            case Constants.REQUEST_MAKE_POSTER:
                MakePosterModel makePosterModel = (MakePosterModel) msg.obj;
                mDatabeanPoster = makePosterModel.getData();
                showShare(mDatabeanPoster.getPoster_link());
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_GET_ACTIVE:
                ToastUtil.showToast("获取活动链接失败");
                mToolBar.setVisibility(View.VISIBLE);
                break;
            case Constants.REQUEST_MAKE_POSTER:
                ToastUtil.showToast("生成海报失败");
                break;
        }
    }

    @JavascriptInterface
    public void showToast(String content){
//        ToastUtil.showToast(content);
    }

    /**
     * 关闭界面
     */
    @JavascriptInterface
    public void quit(){
        finish();
    }

    /**
     * 给js调用，生成活动海报
     */
    @JavascriptInterface
    public void makeposter(){
        if (mDatabean == null){
            ToastUtil.showToast("数据错误");
        }else {
            mHttpHelper.makeposter(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),mDatabean.getActive_id());
        }
    }

    /**
     * 展示分享界面，参数是网络图片url
     */
    public void showShare(String url){
        LogUtil.e("__showShare", "url:"+ url);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setImageUrl(String.valueOf(url));//网络图片
        // 启动分享GUI
        oks.show(this);
    }

    //EventBus接收微信分享回调
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
        if (mMessageEvent.getType() == Constants.REQUEST_SHARE){
            //发送到后台记录分享，不需要回调处理
            mHttpHelper.share(null, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                    UserManager.getInstance().getUserId(), CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()),1);
        }
    }
}
