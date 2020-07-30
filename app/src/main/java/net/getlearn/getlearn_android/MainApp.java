package net.getlearn.getlearn_android;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.hpplay.sdk.source.browse.api.ILelinkServiceManager;
import com.hpplay.sdk.source.browse.api.LelinkServiceManager;
import com.hpplay.sdk.source.browse.api.LelinkSetting;
import com.mob.MobSDK;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import net.getlearn.getlearn_android.activity.BaseActivity;
import net.getlearn.getlearn_android.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/17------更新------
 */

public class MainApp extends Application {


    public List<BaseActivity> mActivityList;
    private int versionCode;
    private  String versionName;


    private static MainApp mainApp;
    //乐播ServiceManager
    private ILelinkServiceManager mLelinkServiceManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityList = new ArrayList<>();
        mainApp = this;
        initData();
    }

    private void initData() {
        //初始化shareSDK
        MobSDK.init(this);
        SPUtil.init(mainApp);
        try {
            versionCode = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode;
            versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initLeLink();
        initUmeng();
    }

    /**
     * 初始化友盟统计
     */
    private void initUmeng() {
        /**
         * 初始化common库
         * 参数1:上下文，必须的参数，不能为空
         * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
         * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
         * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
         */
        //如果AndroidManifest.xml清单配置中没有设置appkey和channel，则可以在这里设置
//        UMConfigure.init(this, "5d832f780cafb227fc0010c6", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        // 初始化SDK
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"");
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }

    public void initLeLink() {
        String APPID_LE = "12388";
        String APPSECRET_LE = "1addfb5371a06412d17ff855a3c20333";
        //初始化乐播投屏
        LelinkSetting lelinkSetting = new LelinkSetting.LelinkSettingBuilder(APPID_LE, APPSECRET_LE).build();
        mLelinkServiceManager = LelinkServiceManager.getInstance(this);
        mLelinkServiceManager.setLelinkSetting(lelinkSetting);
    }

    /**获取上下文*/
    public static Context getContext()
    {
        return mainApp;
    }

    public static MainApp getInstance()
    {
        return mainApp;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public ILelinkServiceManager getLelinkServiceManager() {
        return mLelinkServiceManager;
    }
}
