package net.getlearn.getlearn_android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.BaseActivity;
import net.getlearn.getlearn_android.activity.StudyPlanActivity2;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.utils.DateUtil;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/25------更新------
 * 学习任务通知service
 */

public class NotifyPlanService extends Service{

    private NotificationManager notificationManager;
    private Notification noti;
    private Notification.Builder mBuilder;
    private String content = "格灵同步培优内容";
    //分组（可选）
    //groupId要唯一
    String groupId = "group_001";
    NotificationChannelGroup group;
    private NotificationChannel channel;
    private String channel_ID_001 = "channel_001";
    private boolean isRun;//是否执行任务
    private int notifyId = 1985;
    private Handler mHandler;
    private Runnable mRunnable;
    private int deLay = 60 * 1000;
    private List<StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean> datalist;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRun = true;
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                datalist = UserManager.getInstance().getUserTodayPlan();
                LogUtil.e("__datalist.size_ser_run",datalist.size() + "");
                for (int i = 0; i < datalist.size(); i++) {
                    StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean databean = datalist.get(i);
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date tipDate = DateUtil.stringToDate(databean.getTipsDateTime(),"yyyy-MM-dd HH:mm:ss");
                    String currentTimeStr = sf.format(Calendar.getInstance().getTime());
                    String tipDateTimeStr = "";
                    if (tipDate != null){
                        tipDateTimeStr = sf.format(tipDate);
                    }
                    LogUtil.e("__datalist.tipDateTime",tipDateTimeStr);
                    LogUtil.e("__datalist_currentTime", currentTimeStr);
                    LogUtil.e("__datalist_currentTime ==", String.valueOf(currentTimeStr.equals(tipDateTimeStr)));
                    //时间一致，发送通知
                    if (currentTimeStr.equals(tipDateTimeStr)){
                        notifiPlan(databean);
                        if (MainApp.getInstance().mActivityList.size() > 0 ){
                            BaseActivity baseActivity = MainApp.getInstance().mActivityList.get(MainApp.getInstance().mActivityList.size() - 1);
                            if (baseActivity != null){
                                baseActivity.showNotifyDia(databean.getTaskContent());
                            }
                        }
                    }
                }
                if (isRun){
                    mHandler.postDelayed(mRunnable,deLay);
                }
            }
        };
        mHandler.post(mRunnable);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                notifiPlan();
//            }
//        }, 5000);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                notifiPlan();
//            }
//        }, 10000);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                notifiPlan();
//            }
//        }, 15000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRun = false;
        mHandler.removeCallbacksAndMessages(null);
    }

    private void notifiPlan(StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean databean){
        Intent notificationIntent = new Intent(this,StudyPlanActivity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        /**
         *  创建通知栏管理工具
         */
        notificationManager = (NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);

        /**
         *  实例化通知栏构造器
         */
        mBuilder = new Notification.Builder(this);
        /**
         *  设置Builder
         */
        //设置标题
        mBuilder.setContentTitle("学习任务提醒")
                //设置内容
                .setContentText(databean.getTaskContent())
                //设置小图标
                .setSmallIcon(R.drawable.app_icon)
                //设置通知时间
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)//设置跳转界面
                .setAutoCancel(true)//设置点击取消，Android5.1
                .setPriority(Notification.PRIORITY_MAX);
        noti = mBuilder.build();
        noti.flags = Notification.FLAG_AUTO_CANCEL;//设置点击取消，非Android5.1
        //使用默认的声音、振动、闪光
        noti.defaults = Notification.DEFAULT_ALL;
        if (Build.VERSION.SDK_INT >= 26) {
            group = new NotificationChannelGroup(groupId, "学习任务提醒组");
//            //创建group
            notificationManager.createNotificationChannelGroup(group);
            channel = new NotificationChannel(channel_ID_001, "学习任务提醒", NotificationManager.IMPORTANCE_LOW);
//            //补充channel的含义（可选）
//            channel.setDescription("基于手机网络进行位置上报");
            channel.setGroup(groupId);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channel_ID_001);
        }
        notificationManager.notify(notifyId, noti);
        //发送通知请求
        notificationManager.notify(notifyId, mBuilder.setWhen(System.currentTimeMillis()).build());
        notifyId++;
    }
}
