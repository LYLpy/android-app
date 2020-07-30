package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hpplay.sdk.source.api.IConnectListener;
import com.hpplay.sdk.source.api.LelinkPlayer;
import com.hpplay.sdk.source.api.LelinkPlayerInfo;
import com.hpplay.sdk.source.browse.api.IBrowseListener;
import com.hpplay.sdk.source.browse.api.ILelinkServiceManager;
import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ScaleUtils;
import net.getlearn.getlearn_android.utils.StatusBarUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;
import net.getlearn.getlearn_android.view.SelectDeviceDialog;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/10------更新------
 * 投屏界面
 */

public class ThrScreenActivity extends BaseActivity {

//    @BindView(R.id.iv_loading)
//    ImageView mIvLoading;
    @BindView(R.id.ll_search_device)
    LinearLayout mLlSearchDevice;
    @BindView(R.id.iv_start)
    ImageView mIvStart;
    @BindView(R.id.iv_pause)
    ImageView mIvPause;
    @BindView(R.id.iv_quit)
    ImageView mIvQuit;
    @BindView(R.id.ll_thr_screen_controller)
    LinearLayout mLlThrScreenController;
    private boolean mIsDeviceDiaShowing = false;//选择设备的Dialog是否正在展示
    private String mVideoUrl;
    //    乐播搜索监听
    private IBrowseListener browserListener;
    private SelectDeviceDialog mSelectDeviceDialog;
    private List<LelinkServiceInfo> mServiceInfoData;
    private LelinkPlayer mLelinkPlayer;
    private boolean mIsPlaying;//是否正在投屏
    private LelinkPlayerInfo mLelinkPlayerInfo;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mVideoUrl = intent.getStringExtra("url");
        initLeLink();
        searchDevice();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_thr_screen;
    }

    @Override
    protected void initView() {
        initAnim();
        mIvStart.setOnClickListener(this);
        mIvPause.setOnClickListener(this);
        mIvQuit.setOnClickListener(this);
    }

    private void searchDevice() {
        if (MainApp.getInstance().getLelinkServiceManager() != null){
            MainApp.getInstance().getLelinkServiceManager().setOnBrowseListener(browserListener);
//                    MainApp.getInstance().getLelinkServiceManager().browse(ILelinkServiceManager.TYPE_ALL);
            MainApp.getInstance().getLelinkServiceManager().browse(ILelinkServiceManager.TYPE_DLNA);//只搜索DLNA
//            showProgressDialog("搜索设备中");
        }else {
            ToastUtil.showToast("投屏初始化失败，请重试");
            MainApp.getInstance().initLeLink();
        }
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        //沉浸式状态栏
        StatusBarUtil.setTransparent(this);
        // 加载动画 
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
//                this, R.anim.loading_animation);
        // 使用ImageView显示动画  
//        mIvLoading.startAnimation(hyperspaceJumpAnimation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_quit:
                showQuitDia();
                break;
            case R.id.iv_start:
                mIsPlaying = true;
                if (mLelinkPlayerInfo == null){
                    startThrScreen();
                }else {
                    mLelinkPlayer.resume();
                }
                mIvStart.setVisibility(View.GONE);
                mIvPause.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_pause:
                mIsPlaying = false;
                mLelinkPlayer.pause();
                mIvStart.setVisibility(View.VISIBLE);
                mIvPause.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (MainApp.getInstance().getLelinkServiceManager() != null){
            MainApp.getInstance().getLelinkServiceManager().stopBrowse();
        }
    }

    private void initLeLink() {
        browserListener = new IBrowseListener() {
            @Override
            public void onBrowse(int resultCode, List<LelinkServiceInfo> list) {
                for (int i = 0; i < list.size(); i++) {
                    LelinkServiceInfo serviceInfo = list.get(i);
//                    LogUtil.e("__serviceInfo_getName" + i, serviceInfo.getName());
//                    LogUtil.e("__serviceInfo_getIp" + i, serviceInfo.getIp());
                }
                switch (resultCode) {
                    case IBrowseListener.BROWSE_SUCCESS:
                        LogUtil.e("__serviceInfo_resultCode", "搜索成功");
                        mServiceInfoData = list;
                        showDeviceDia(mServiceInfoData);
                        break;
                    case IBrowseListener.BROWSE_ERROR_AUTH:
                        LogUtil.e("__serviceInfo_resultCode", "搜索失败，**Auth错误，请检查您的网络设置或AppId和AppSecret**");
                        break;
                }
            }
        };
        IConnectListener connectListener = new IConnectListener() {
            @Override
            public void onConnect(LelinkServiceInfo serviceInfo, int extra) {
                //连接成功
                LogUtil.e("__Le_connectListener","onConnect:" + extra);
                //开始投屏
                startThrScreen();
            }

            @Override
            public void onDisconnect(LelinkServiceInfo serviceInfo, int what, int extra) {
//                LogUtil.e("__Le_connectListener","onDisconnect:" + extra);
            }

        };
        mLelinkPlayer = new LelinkPlayer(this);
        mLelinkPlayer.setConnectListener(connectListener);
    }

    private synchronized void showDeviceDia(List<LelinkServiceInfo> data) {
//        dismissProgressDialog();
//        MainApp.getInstance().getLelinkServiceManager().stopBrowse();
        //当界面正在关闭或者用户正在选择是否退出界面时，不弹出选择设备框
        if (isFinishing() || (mExitDialog != null && mExitDialog.isShowing())){
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mSelectDeviceDialog == null) {
                    mSelectDeviceDialog = new SelectDeviceDialog(ThrScreenActivity.this, data).builder();
                    mSelectDeviceDialog.setOnPopupItemClickListener(new SelectDeviceDialog.OnPopupItemClickListener() {
                        @Override
                        public void onPopUpItemClick(LelinkServiceInfo serviceInfo) {
                            LogUtil.e("__onPopUpItemClick_getName", serviceInfo.getName());
                            MainApp.getInstance().getLelinkServiceManager().stopBrowse();//停止搜索
                            //连接设备
                            mLelinkPlayer.connect(serviceInfo);
//                            mIvLoading.clearAnimation();//清除动画
                            //隐藏搜索布局，展示控制布局
                            mLlSearchDevice.setVisibility(View.GONE);
                            mLlThrScreenController.setVisibility(View.VISIBLE);
//                            startThrScreen();
                        }
                    });
                    mSelectDeviceDialog.setNewData(mServiceInfoData);
                    mSelectDeviceDialog.show();
                } else {
                    if (mSelectDeviceDialog.getDialog().isShowing()) {
                        mSelectDeviceDialog.setNewData(mServiceInfoData);
                    } else {
                        mSelectDeviceDialog.show();
                    }
                }
            }
        });
    }


    /**
     * 开始推送（投屏）
     */
    private void startThrScreen(){
        // 实例化播放的媒体信息
        mLelinkPlayerInfo = new LelinkPlayerInfo();
        // 设置媒体类型：LelinkPlayerInfo.TYPE_VIDEO：视频
        //              LelinkPlayerInfo.TYPE_AUDIO：音乐
        //              LelinkPlayerInfo.TYPE_IMAGE：图片
        mLelinkPlayerInfo.setType(LelinkPlayerInfo.TYPE_VIDEO);
        // 设置本地文件path，支持本地推送
        // lelinkPlayerInfo.setLocalPath(localurl);
        // 设置网络url，支持网络推送，与本地推送二选一
        mLelinkPlayerInfo.setUrl(mVideoUrl);
        mLelinkPlayer.setDataSource(mLelinkPlayerInfo);
        mLelinkPlayer.start();
    }

    private IOSAlertDialog mExitDialog;
    private void showQuitDia(){
        if (mExitDialog == null){
            mExitDialog = new IOSAlertDialog(this).builder();
            //                 iosAlertDialog.setTitleStyle(Typeface.defaultFromStyle(Typeface.NORMAL));
            //设置Dia宽度
            ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(ScaleUtils.dip2px(getResources().getDimension(R.dimen.dp100))
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            mExitDialog.setDiaWidth(params)
                    .setMsg("退出将结束投屏\n是否退出")
                    .setNegativeButton("结束投屏", R.color.bg_gray4, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                            mLelinkPlayer.stop();
                        }
                    }).
                    setPositiveButton("继续学习",R.color.btnblue,null);
        }
        if (mExitDialog.getDialog().isShowing()){
            return;
        }
        mExitDialog.show();
    }

    /**
     * 监听按下返回键或者其他方式返回
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        LogUtil.e("__onBackPressed","onBackPressed");
        showQuitDia();
    }
}
