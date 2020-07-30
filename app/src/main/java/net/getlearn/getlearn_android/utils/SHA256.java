package net.getlearn.getlearn_android.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Maps;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

public class SHA256 {
//
//
//    public static void getSign() {
//
//        String secret = "0238ac1f05e711eabae6d0946672c4fb";//vivo应用市场密钥
//
//        Map<String, Object> parmas = Maps.newHashMap();
//        parmas.put("account", "test");
//        parmas.put("timestamp", System.currentTimeMillis());
//        parmas.put("method", "get-online-version");
//        parmas.put("bizParam", getBizParam());
//        String sign = HmacSHA256(getUrlParamsFromMap(parmas), secret);
//        System.out.println("得到加密后的验签：" + sign);
//
//    }
//
//    public static Map<String, String> getBizParam() {
//        Map<String, String> bizParams = Maps.newHashMap();
//        bizParams.put("packageName", "net.getlearn.getlearn_android");
//        bizParams.put("androidVersion", "4.0.1");
//        bizParams.put("model", "vivo x20");
//        return bizParams;
//    }
//
//    /*
//     * ** 根据传入的map，把map里的key value转换为接口的请求参数，并给参数按ascii码排序
//     * ** @paramparamsMap 传入的map
//     * * @return按ascii码排序的参数键值对拼接结果
//     */
//    public static String getUrlParamsFromMap(Map<String, Object> paramsMap) {
//        List<String> keysList = new ArrayList<>(paramsMap.keySet());
//        Collections.sort(keysList);//收集排序
//        StringBuilder sb = new StringBuilder();
//        for (String key : keysList) {
//            if ("sign".equals(key)) {
//                continue;
//            }
//            Object object = paramsMap.get(key);
//            if (object == null){
//                continue;
//            }
//            sb.append(key).append("=");
//            if ("bizParam".equals(key)){
//                sb.append(JSON.toJSONString(object)).append("&");
//            }else{
//                sb.append(object).append("&");
//            }
//        }
//        if (sb.length() > 0){
//            sb.deleteCharAt(sb.length() - 1);
//        }
//        return sb.toString();
//    }
//
//    /*
//     * ** HMAC_SHA256 验签加密*
//     */
//    public static String HmacSHA256(String data, String key) {
//
//        try {
//            byte[] secretByte = key.getBytes(Charset.forName("UTF-8"));
//            SecretKeySpec signingKey = new SecretKeySpec(secretByte,"HmacSHA256");//密钥规格
//            Mac mac = Mac.getInstance("HmacSHA256");
//            mac.init(signingKey);
//            byte[] dataByte = data.getBytes(Charset.forName("UTF-8"));
//            byte[] by = mac.doFinal(dataByte);//最后一个字节
//            return byteArr2HexStr(by);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//    private static String byteArr2HexStr(byte[] arrB){
//        int iLen = arrB.length;
//        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
//        StringBuilder sb = new StringBuilder(iLen *2);
//        for (int i = 0; i < iLen; i++) {
//            int intTmp = arrB[i];
//            // 把负数转换为正数
//            while (intTmp<0){
//                intTmp= intTmp+ 256;
//            }
//            // 小于0F的数需要在前面补0
//            if (intTmp<16){
//                sb.append("0");
//            }
//            sb.append(Integer.toString(intTmp,16));
//        }
//        return sb.toString();
//    }

    /**
     ** 跳转到商店详情页自动更新，请按照商店版本要求自行控制调用此方法的前置条件
     ** @paramcontext 上下文
     */
//    public static void jumpToAppStoreDetailUpdate(Context context) {
//        Intent intent = new Intent();
//        String packageName = context.getPackageName();
//        String url = "vivomarket://details?id=" + packageName + "&th_name=self_update";
//        Uri uri = Uri.parse(url);
//        intent.setData(uri);
//        intent.setPackage("com.bbk.appstore");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //context.startActivities(intent);
//    }
}
