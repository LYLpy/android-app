package net.getlearn.getlearn_android.utils;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class NetworkChang extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);;
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isAvailable())
        {
            Toast.makeText(context,"网络已连接",Toast.LENGTH_SHORT).show();
            //Log.e("sssss","网络已连接");
        }else
        {
            Toast.makeText(context,"网络断开",Toast.LENGTH_SHORT).show();
            //Log.e("sssss","网络断开");
        }
    }
}
