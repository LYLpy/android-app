package net.getlearn.getlearn_android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

public class BrandUtil {

    private Context mContext;
    public BrandUtil (Context context){
        this.mContext = context;
    }

    private  String mDeviceBrand = "nullnull";
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

    public  void getBrand() {

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
    //获取手机厂商名字
    private void initUpdateData(){
        mDeviceBrand =  SystemUtil.getDeviceBrand();
        LogUtil.e("__initUpdateData","__brand:" + SystemUtil.getDeviceBrand());
    }

    /**
     * 判断应用市场是否存在的方法
     *
     * @param mContext
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
    public static boolean isAvilible(Context mContext, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = mContext.getPackageManager();
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
        if (isAvilible(mContext, mMarketPacXiaomi)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacXiaomi);
        } else if (isAvilible(mContext, mMarketPacVivo)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacVivo);
        } else if (isAvilible(mContext, mMarketPacHuawei)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacHuawei);
        } else if (isAvilible(mContext, mMarketPacOppo)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacOppo);
        }
//        else if (isAvilible(this, mMarketPacSec)) {
//            launchAppDetail(this, mApplicationId, mMarketPacSec);
//        } else if (isAvilible(this, mMarketPacYulong)) {
//            launchAppDetail(this, mApplicationId, mMarketPacYulong);
//        }
        else if (isAvilible(mContext, mMarketPacMeizu)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacMeizu);
        }
//        else if (isAvilible(this, mMarketPacLenovo)) {
//            launchAppDetail(this, mApplicationId, mMarketPacLenovo);
//        }
        else if (isAvilible(mContext, mMarketPacBaidu)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacBaidu);
        } else if (isAvilible(mContext, mMarketPacTencent)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacTencent);
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
        else if (isAvilible(mContext, mMarketPacWandoujia)) {
            launchAppDetail(mContext, mApplicationId, mMarketPacWandoujia);
        } else {
            Intent intent = new Intent();
            //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse("https://sj.qq.com/myapp/detail.htm?apkName=net.getlearn.getlearn_android");//应用宝下载链接
//                Uri content_url = Uri.parse("https://shouji.baidu.com/software/26360663.html");//百度手机助手
            Uri content_url = Uri.parse("https://shouji.baidu.com/software/26541183.html");//百度手机助手
//                Uri content_url = Uri.parse("http://zhushou.360.cn/detail/index/soft_id/1932794?recrefer=SE_D_%E5%B0%8F%E5%AD%A6%E5%AE%9D");
            intent.setData(content_url);
            mContext.startActivity(intent);
        }

    }

}
