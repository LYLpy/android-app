package net.getlearn.getlearn_android.model;

import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/18------更新------
 */

public class RetrofitManager {

    private static RetrofitManager instance = null;
    private RetrofitManager() {
        initRetrofit();
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }
    private Retrofit retrofit;
    private IHttpService httpService;

    public IHttpService getService() {
        return httpService;
    }

    private void initRetrofit() {

        			/*
           **打印retrofit信息部分
            */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                LogUtil.e("__RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
//                .addInterceptor(loggingInterceptor)
//                .build();
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(loggingInterceptor);//设置日志打印
        client.connectTimeout(10, TimeUnit.SECONDS);    // 连接超时时间:10秒
        retrofit = new Retrofit.Builder()
                .baseUrl(IHttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        httpService = retrofit.create(IHttpService.class);
    }
}
