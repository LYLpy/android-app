package net.getlearn.getlearn_android.model;

import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.utils.AES128;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/26------更新------
 * 网络请求基类
 */

public class BaseHelper {
    private IOSAlertDialog mIOSAlertDialog;
    public IHttpService getService() {
        return RetrofitManager.getInstance().getService();
    }

    /**
     * Retrofit请求
     *
     * @param call Retrofit接口返回的Call对象
     * @param callback 请求回调
     * @param reqType 调用哪一个接口
     * @param clazz 请求数据后解析得到的javabean
     * @param what 用来区分请求的类型，例如：用来区分是列表的下拉还是上拉。大部分情况用不到此参数.
     * @param <T>
     */
    public <T> void enqueue(Call<JsonObject> call,
                            final IHttpCallback callback,
                            final int reqType,
                            final Class<T> clazz,
                            final int what) {
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                String res= response.body()+"";
                LogUtil.e("__res"+ reqType,res);
                try {
                    if (res.equals("null")){
                        LogUtil.e("__json_null"+ reqType,response.body()+"");
                    }
                    String respondMsg;
                    // 服务器返回的加密的json字符串
                    Object body =  response.body();
                    String jsonSrc = body.toString();

                    LogUtil.e("__jsonSrc" + reqType,jsonSrc);
                    JSONObject jsonObjectSrc = new JSONObject(jsonSrc);
                    String contentSrc = jsonObjectSrc.getString("content");
                    //解密
                    String json = AES128.Decrypt(contentSrc,IHttpService.KEY);
                    LogUtil.e("__json"+ reqType,json);
                    JSONObject jsonObject = new JSONObject(json);
                    LogUtil.e("__json"+reqType,jsonObject+"");
                    int status = jsonObject.getInt("status");
                    int error = jsonObject.getInt("error");
                    LogUtil.e("__json"+reqType,"status:"+ status);
                    LogUtil.e("__json"+reqType,"error:"+error);
                    //请求成功
                    if (status == 200) {
                        if (error != 10000) {
                            if (callback != null) {
                                //token失效
                                if (error == 10003) {
                                    SPUtil.clear();
                                    callback.onTokenError(reqType, "登录信息失效，请重新登录");
                                }
                                switch (reqType) {
                                    case Constants.REQUEST_GET_EXCHANGE_CODE:
                                        respondMsg = jsonObject.getString("msg");
                                        callback.onHttpError(reqType, respondMsg);
                                        break;
                                    case Constants.REQUEST_VERIFICATIONCODE:
                                        callback.onHttpError(reqType, "请求过于频繁，请稍候重试");
                                        break;
                                    case Constants.REQUEST_BANNER:
                                        LogUtil.e("__获取轮播图失败");
                                        callback.onHttpError(reqType, "获取轮播图失败");
                                        break;
                                    case Constants.REQUEST_LOGIN:
                                        respondMsg = jsonObject.getString("msg");
                                        callback.onHttpError(reqType, respondMsg);
                                        break;
                                    case Constants.REQUEST_GET_GRADE:
                                        respondMsg = jsonObject.getString("msg");
                                        callback.onHttpError(reqType, respondMsg);
                                        break;
                                    default:
                                        respondMsg = jsonObject.getString("msg");
                                        callback.onHttpError(reqType, respondMsg);
                                        break;
                                }
                            }
                        } else if (error == 10000) {
                            LogUtil.e("__json" + reqType + "error", error + "");
                            Gson gson = new Gson();
                            T bean = gson.fromJson(json, clazz);
                            if (callback != null) {   // 求成功的回调
                                Message msg = new Message();
                                msg.what = what;
                                msg.obj = bean;
                                callback.onHttpSuccess(reqType, msg);
                            }
                        }
                    } else {
                        if (callback != null) {   // 请求失败的回调
                            callback.onHttpError(reqType, "请求错误");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    // 请求出错
                    if (callback != null) {   // 请求失败的回调
                        callback.onHttpError(reqType, e.getMessage());
                        callback.onHttpError(reqType, "数据解析错误");
                        LogUtil.e("__Exception_baseHelper","数据解析错误" + reqType + ":" + e.getMessage());

                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                LogUtil.e("__onFailure",t.getMessage());
                if (callback!=null){
                    callback.onHttpError(reqType, "网络错误");
                }
            }
        });
    }
    public <T> void enqueue(Call<JsonObject> call,
                            final IHttpCallback callback,
                            final int reqType,
                            final Class<T> clazz) {
        enqueue(call,callback,reqType,clazz,-1);
    }

    public interface IHttpCallback {
        /**
         * 请求成功
         *
         * @param reqType 区分调用的是哪一个接口
         * @param msg 请求成功后返回的javabean数据
         */
        void onHttpSuccess(int reqType,Message msg);
        /**
         * 请求失败
         *
         * @param reqType 区分调用的是哪一个接口
         * @param error 请求失败的原因
         */
        void onHttpError(int reqType, String error);


        /**
         * token错误，跳转到登陆界面
         * @param reqType
         * @param error
         */
        void onTokenError(int reqType, String error);

    }
}
