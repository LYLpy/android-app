package net.getlearn.getlearn_android.xxtapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aspirecn.xiaoxuntong.sdk.Base;
import com.aspirecn.xiaoxuntong.sdk.BaseResp;
import com.aspirecn.xiaoxuntong.sdk.ConstantsAPI;
import com.aspirecn.xiaoxuntong.sdk.IXXTAPI;
import com.aspirecn.xiaoxuntong.sdk.IXXTHandleCallback;
import com.aspirecn.xiaoxuntong.sdk.XXTAPIFactory;
import com.aspirecn.xiaoxuntong.sdk.XXTApiProtocol;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.activity.MainActivity;

public class XXTEntryActivity extends AppCompatActivity implements IXXTHandleCallback {
    public static final String XXT_PROVINCE_HUNAN = "hunan";
    private  IXXTAPI xxtApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xxtApi = XXTAPIFactory.createXXTAPI(this, XXT_PROVINCE_HUNAN,
                ConstantsAPI.XXT_VERSION_PARENT);
        xxtApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        xxtApi.handleIntent(intent,this);
    }

    @Override
    public void onRes(BaseResp resp) {
        String callbackInfo = null;
        // 授权
        if (Base.XXTApiRequestType.AHTHORIZE.value == resp.getType()) {

            XXTApiProtocol.XXTResp xxtResp = (XXTApiProtocol.XXTResp) resp;

            if ("0".equalsIgnoreCase(xxtResp.errorCode)) {
                callbackInfo = xxtResp.responseString;
            } else {
                callbackInfo = xxtResp.errorMessage;
            }

        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("callback_info", callbackInfo); // MainActivity采用singleTask模式，这里把回调信息传回去，方便显示。
        this.startActivity(intent);
        finish();
//        Intent intent = new Intent(this, MainActivity3.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

}