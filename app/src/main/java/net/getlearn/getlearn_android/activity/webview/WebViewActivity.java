package net.getlearn.getlearn_android.activity.webview;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.activity.BaseActivity;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * 加载webView的activity
 */

public class WebViewActivity extends BaseActivity {
//    字符串常量，作为android与js通信的接口，即字符串映射对象
    public static final String JAVAINTERFACE = "appObject";
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.webview)
    WebView mWebview;
    private String mUrl = "";

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mWebview.loadUrl(mUrl);
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

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }


}
