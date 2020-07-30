package net.getlearn.getlearn_android.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huawei.agconnect.crash.AGConnectCrash;
import com.huawei.agconnect.exception.AGCException;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.utils.BrandUtil;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.DownloadProgressView;
import net.getlearn.getlearn_android.utils.GlideCacheUtil;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.SystemUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    /* 下载包安装路径 */
    private final String savePath = "/sdcard/GetLearn/";
    private final String saveFileName = savePath + "GetLearn.apk";

    private LinearLayout mLlVersionUpdate;//版本更新
    private LinearLayout linearLayout2;//删除缓存
    private TextView mTvExit;//退出当前用户
    private FrameLayout mFlBack;//返回

    private TextView mTvCache;//返回

    private TextView tvDegree, tvTipsLoading, tvTipsFailed, tvTitleLoading, tvTitleFailed, tvContent;
    private Button btnRetry, btnStartUpdate;
    private ImageView ivCancelUpdate;
    private ViewGroup layoutLoading;
    private int progress;
    private Thread downLoadThread;//下载的线程
    private String downloadUrl;//下载url
    private boolean interceptFlag = false;//是否停止下载
    /* 进度条与通知ui刷新的handler和msg常量 */
    private ProgressBar mProgress;
    private DownloadProgressView downloadProgressView;
    private TextView mTvVersion;
    private TextView mTvBadge;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private static final int DOWN_RETRY = 3;
    private static final int DOWN_FAIL = 4;

    @Override
    protected void initData() {
        if (MainApp.getInstance().getVersionName()!=null && !MainApp.getInstance().getVersionName().equals("")){
            mTvVersion.setText(MainApp.getInstance().getVersionName());
        }
        initUpdateData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        mFlBack =findViewById(R.id.fl_back);
        mTvBadge =findViewById(R.id.tv_badge);
        mTvVersion =findViewById(R.id.tv_version);
        mFlBack.setOnClickListener(this);
        mTvExit = findViewById(R.id.tv_exit);
        mTvExit.setOnClickListener(this);
        mLlVersionUpdate = findViewById(R.id.ll_version_update);
        mLlVersionUpdate.setOnClickListener(this);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout2.setOnClickListener(this);
        mTvCache = findViewById(R.id.tv_cache);
        mTvCache.setText(GlideCacheUtil.getInstance().getCacheSize(this));
        LogUtil.e("__Cache", GlideCacheUtil.getInstance().getCacheSize(this));
//        mTvBadge.setVisibility(View.VISIBLE);
//        mTvBadge.setText("9");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_exit:
                IOSAlertDialog exitDialog = new IOSAlertDialog(this).builder();
//                 iosAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
                exitDialog.setMsg("正在退出账号\n是否确定")
                        .setNegativeButton("取消",R.color.bg_gray4,null).
                        setPositiveButton("确定",R.color.btnblue, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showProgressDialog("正在退出");
                                mHttpHelper.loginout(SettingActivity.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                            }
                        }).show();
                break;
            case R.id.ll_version_update://更新app
                BrandUtil brandUtil = new BrandUtil(this);
                brandUtil.getBrand();
                break;
            case R.id.linearLayout2:
                IOSAlertDialog iosAlertDialog = new IOSAlertDialog(this).builder();
                iosAlertDialog.setMsg("缓存数据将被清除，\n不可恢复，是否继续").
                        setNegativeButton("取消",R.color.bg_gray4,null).
                        setPositiveButton("确定",R.color.btnblue, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GlideCacheUtil.getInstance().clearImageDiskCache(SettingActivity.this);
                        showProgressDialog("缓存清理中");
                        delayHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dismissProgressDialog();
                                ToastUtil.showToast("清理完毕");
                                mTvCache.setText(GlideCacheUtil.getInstance().getCacheSize(SettingActivity.this));
                            }
                        }, 2000);
                    }
                }).show();
                break;
            case R.id.fl_back:
//                AGConnectCrash.getInstance().testIt();
                finish();
                LogUtil.e("__iv_back","finish");
                break;
            default:break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_LOGIN_OUT:
                dismissProgressDialog();
                SPUtil.clear();
                Intent intent = new Intent(SettingActivity.this, GetVerificationCodeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_LOGIN_OUT:
                dismissProgressDialog();
                ToastUtil.showToast("退出失败");
                break;
        }
    }

    /**
     * 检查更新
     * @param versionName
     */
    private void checkUpDate(String versionName){

    }
    boolean isUpdate;
    /**
     * 是否更新提示框
     * @param versionName
     */

    private void showUpDateDia(String versionName) {
        String posStr;
        String content;
        if (versionName.equals("")) {
            posStr = "确定";
            content = "已是最新版本";
        } else {
            isUpdate = true;
            posStr = "更新";
            content = "检测到新版本：" + versionName;
        }
        IOSAlertDialog upDateDialog = new IOSAlertDialog(this).builder();
//                 iosAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
        if (isUpdate) {
            upDateDialog.setNegativeButton("取消", R.color.bg_gray4, null);
        }
        upDateDialog.setMsg(content).setPositiveButton(posStr, R.color.btnblue, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUpdate) {
                    showDownloadProgressView();
                }
            }
        }).show();
    }

    private void installApk(){
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        //开始安装apk
        Intent i = new Intent(Intent.ACTION_VIEW);
        Uri data;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data = FileProvider.getUriForFile(this, this.getPackageName() + ".fileprovider", apkfile);
            // 给目标应用一个临时授权
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            //Android8.0适配安装
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                if (!hasInstallPermission) {
                    startInstallPermissionSettingActivity();
                }
            }
        } else {
            data = Uri.parse("file://" + apkfile.toString());
        }
        i.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(i);
    }
    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() { //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 下载apk
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            URL url = null;
            try {
                url = new URL(downloadUrl);
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.connect();
            int length = conn.getContentLength();
            InputStream is = conn.getInputStream();

            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdir();
            }
            String apkFile = saveFileName;
            File ApkFile = new File(apkFile);
            FileOutputStream fos = new FileOutputStream(ApkFile);

            int count = 0;
            byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
//                    progress = (int) (((float) count / length) * 100);
//                    // 更新进度
//                    mHandler.sendEmptyMessage(DOWN_UPDATE);
//                    if (numread <= 0) {
//                        // 下载完成通知安装
//                        mHandler.sendEmptyMessage(DOWN_OVER);
//                        break;
//                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);// 点击取消就停止下载.
                fos.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
                //通知下载失败
            }
        }
    };

    AlertDialog dialogLoading;

    private void showDownloadProgressView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialogLoading = builder.show();
        dialogLoading.setCanceledOnTouchOutside(false);
        dialogLoading.getWindow().setContentView(R.layout.dialog_progress_downloading);
        dialogLoading.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    dialogLoading.dismiss();
                    interceptFlag = true;
                    return true;
                }
                return true;
            }
        });
        downloadProgressView = (DownloadProgressView) dialogLoading.findViewById(R.id.dlpv_update_app);
        //进度值
        tvDegree = (TextView) dialogLoading.findViewById(R.id.tv_update_degree);
        tvTitleLoading = (TextView) dialogLoading.findViewById(R.id.tv_update_loading);
        tvTitleFailed = (TextView) dialogLoading.findViewById(R.id.tv_update_failed);
        tvTipsLoading = (TextView) dialogLoading.findViewById(R.id.tv_update_tips_loading);
        tvTipsFailed = (TextView) dialogLoading.findViewById(R.id.tv_update_tips_failed);
        btnRetry = (Button) dialogLoading.findViewById(R.id.btn_update_retry);
        layoutLoading = (ViewGroup) dialogLoading.findViewById(R.id.layout_update_loading);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isOpenNetwork(SettingActivity.this)) {
//                    mHandler.sendEmptyMessage(DOWN_RETRY);
                }
            }
        });
        downloadProgressView.setMaxCount(100.0f);
        downloadProgressView.setCurrentCount(0);
        downloadApk();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
//				mProgress.setProgress(progress);
                    downloadProgressView.setCurrentCount(progress);
                    tvDegree.setText(progress + "%");
                    break;
                case DOWN_OVER:
                    if (dialogLoading != null) {
                        dialogLoading.dismiss();
                        interceptFlag = true;
                    }
                    IOSAlertDialog exitDialog = new IOSAlertDialog(SettingActivity.this).builder();
//                 iosAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
                    exitDialog.setMsg("已经下载完成")
                            .setNegativeButton("取消",R.color.bg_gray4,null).
                            setPositiveButton("安装",R.color.btnblue, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    installApk();
                                }
                            }).show();
                    break;
                case DOWN_FAIL:
                    tvTipsFailed.setVisibility(View.VISIBLE);
                    tvTitleFailed.setVisibility(View.VISIBLE);
                    btnRetry.setVisibility(View.VISIBLE);
                    tvTipsLoading.setVisibility(View.GONE);
                    tvTitleLoading.setVisibility(View.GONE);
                    layoutLoading.setVisibility(View.GONE);
                    interceptFlag = true;
                    break;
                case DOWN_RETRY:
                    tvTipsFailed.setVisibility(View.GONE);
                    tvTitleFailed.setVisibility(View.GONE);
                    btnRetry.setVisibility(View.GONE);
                    tvTipsLoading.setVisibility(View.VISIBLE);
                    tvTitleLoading.setVisibility(View.VISIBLE);
                    layoutLoading.setVisibility(View.VISIBLE);
                    interceptFlag = false;
                    downloadApk();
                default:
                    break;
            }
        };
    };

    private String mDeviceBrand = "nullnull";
    private String mApplicationId ="net.getlearn.getlearn_android";//包名

    private String mMarketPacXiaomi ="com.xiaomi.market";//小米应用市场包名
    private String mMarketPacVivo ="com.bbk.appstore";//vivo应用市场包名
    private String mMarketPacHuawei ="com.huawei.appmarket";//华为应用市场包名
    private String mMarketPacOppo ="com.oppo.market";//Oppo应用市场包名
    private String mMarketPacMeizu ="com.meizu.mstore";//魅族应用市场包名
//    private String mMarketPacSec ="com.sec.android.app.samsungapps";//三星应用市场包名
//    private String mMarketPacYulong ="com.yulong.android.coolmart";//酷派应用商店包名
//    private String mMarketPacSogou ="com.sogou.androidtool";// //搜狗应用市场
//    private String mMarketPacLenovo ="com.lenovo.leos.appstore";////联想应用商店

    private String mMarketPacTencent ="com.tencent.android.qqdownloader";//腾讯应用宝
    private String mMarketPacBaidu ="com.baidu.appsearch";//百度手机助手
    private String mMarketPacWandoujia ="com.wandoujia.phoenix2";//豌豆荚
//    private String mMarketPacQihoo ="com.qihoo.appstore";//360手机助手
//    private String mMarketPacHiapk ="com.hiapk.marketpho";//安卓市场
//    private String mMarketTaobao ="com.taobao.appcenter";//淘宝手机助手
//    private String mMarketGoogle ="com.android.vending";//Google Play


    //获取手机厂商名字
    private void initUpdateData(){
        mDeviceBrand =  SystemUtil.getDeviceBrand();
        LogUtil.e("__initUpdateData","__brand:" + SystemUtil.getDeviceBrand());
    }

    /**
     * 判断应用市场是否存在的方法
     *
     * @param context
     * @param packageName
     *
     * 主流应用商店对应的包名
     * com.android.vending    -----Google Play
     * com.tencent.android.qqdownloader     -----应用宝
     * com.qihoo.appstore    -----360手机助手
     * com.baidu.appsearch    -----百度手机助
     * com.xiaomi.market    -----小米应用商店
     * com.wandoujia.phoenix2    -----豌豆荚
     * com.huawei.appmarket    -----华为应用市场
     * com.taobao.appcenter    -----淘宝手机助手
     * com.hiapk.marketpho    -----安卓市场
     * cn.goapk.market        -----安智市场
     * com.bbk.appstore       -----VIVO应用市场
     */
    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> pName = new ArrayList<String>();
        // 从pinfo中将包名字取出
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pf = pinfo.get(i).packageName;
                pName.add(pf);
            }
        }
        // 判断pName中是否有目标程序的包名，有true，没有false
        return pName.contains(packageName);
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面
     */
    public static void launchAppDetail(Context mContext, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAppPkg() {
        if (isAvilible(this, mMarketPacXiaomi)) {
            launchAppDetail(this, mApplicationId, mMarketPacXiaomi);
        } else if (isAvilible(this, mMarketPacVivo)) {
            launchAppDetail(this, mApplicationId, mMarketPacVivo);
        } else if (isAvilible(this, mMarketPacHuawei)) {
            launchAppDetail(this, mApplicationId, mMarketPacHuawei);
        } else if (isAvilible(this, mMarketPacOppo)) {
            launchAppDetail(this, mApplicationId, mMarketPacOppo);
        }
//        else if (isAvilible(this, mMarketPacSec)) {
//            launchAppDetail(this, mApplicationId, mMarketPacSec);
//        } else if (isAvilible(this, mMarketPacYulong)) {
//            launchAppDetail(this, mApplicationId, mMarketPacYulong);
//        }
        else if (isAvilible(this, mMarketPacMeizu)) {
            launchAppDetail(this, mApplicationId, mMarketPacMeizu);
        }
//        else if (isAvilible(this, mMarketPacLenovo)) {
//            launchAppDetail(this, mApplicationId, mMarketPacLenovo);
//        }
        else if (isAvilible(this, mMarketPacBaidu)) {
            launchAppDetail(this, mApplicationId, mMarketPacBaidu);
        } else if (isAvilible(this, mMarketPacTencent)) {
            launchAppDetail(this, mApplicationId, mMarketPacTencent);
        }
//        else if (isAvilible(this, mMarketPacSogou)) {
//            launchAppDetail(this, mApplicationId, mMarketPacSogou);
//        } else if (isAvilible(this, mMarketGoogle)) {
//            launchAppDetail(this, mApplicationId, mMarketGoogle);
//        } else if (isAvilible(this, mMarketTaobao)) {
//            launchAppDetail(this, mApplicationId, mMarketTaobao);
//        }
//        else if (isAvilible(this, mMarketPacHiapk)) {
//            launchAppDetail(this, mApplicationId, mMarketPacHiapk);
//        }
//        else if (isAvilible(this, mMarketPacQihoo)) {
//            launchAppDetail(this, mApplicationId, mMarketPacQihoo);
//        }
        else if (isAvilible(this, mMarketPacWandoujia)) {
            launchAppDetail(this, mApplicationId, mMarketPacWandoujia);
        } else {
            Intent intent = new Intent();
            //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse("https://sj.qq.com/myapp/detail.htm?apkName=net.getlearn.getlearn_android");//应用宝下载链接
//                Uri content_url = Uri.parse("https://shouji.baidu.com/software/26360663.html");//百度手机助手
            Uri content_url = Uri.parse("https://shouji.baidu.com/software/26541183.html");//百度手机助手
//                Uri content_url = Uri.parse("http://zhushou.360.cn/detail/index/soft_id/1932794?recrefer=SE_D_%E5%B0%8F%E5%AD%A6%E5%AE%9D");
            intent.setData(content_url);
            startActivity(intent);
        }

    }
    public void getBrand() {

        String brand = mDeviceBrand.toLowerCase();
        LogUtil.e("__brand", brand);
        if (brand.equals("xiaomi")) {
            getAppPkg();

        } else if (brand.equals("vivo")) {
            getAppPkg();
        } else if (brand.equals("honor") || brand.equals("huawei")) {
            getAppPkg();
        } else if (brand.equals("oppo")) {
            getAppPkg();
        } else if (brand.equals("meizu")) {
            getAppPkg();
        } else if (brand.equals("Sec")) {
            getAppPkg();
        } else {
            getAppPkg();
        }

    }

}
