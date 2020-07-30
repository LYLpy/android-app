package net.getlearn.getlearn_android.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lcw.library.imagepicker.ImagePicker;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterUserInfoVersion;
import net.getlearn.getlearn_android.model.bean.EditHead;
import net.getlearn.getlearn_android.model.bean.GetPersonalInfoModel;
import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.model.bean.JsonBean;
import net.getlearn.getlearn_android.model.bean.SubjectVersionModel;
import net.getlearn.getlearn_android.model.bean.UploadImgModel;
import net.getlearn.getlearn_android.model.bean.UserInfoModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.GetJsonDataUtil;
import net.getlearn.getlearn_android.utils.GlideLoader;
import net.getlearn.getlearn_android.utils.ImgUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.ScaleUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;
import net.getlearn.getlearn_android.view.SelectGradeDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import cn.sharesdk.onekeyshare.OnekeyShare;


public class UserInfoActivity extends BaseActivity implements RvAdapterUserInfoVersion.OnItemClickListener {

    @BindView(R.id.iv_portrait)
    ImageView mIvPortrait;
    @BindView(R.id.tv_signatrue)
    TextView mTvSignatrue;

    private final int REQUEST_1 = 1;
    private final int REQUEST_2 = 2;
    private final int REQUEST_3 = 3;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.rl_portrait)
    RelativeLayout mRlPortrait;
    @BindView(R.id.rl_signatrue)
    RelativeLayout mRlSignatrue;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.rl_nickname)
    RelativeLayout mRlNickname;
    @BindView(R.id.tv_grade)
    TextView mTvGrade;
    @BindView(R.id.rl_grade)
    RelativeLayout mRlGrade;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.rl_area)
    RelativeLayout mRlArea;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout mRlPhone;
    @BindView(R.id.spring_view)
    SpringView mSpringView;

    private OptionsPickerView pvOptions;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private final int REQUEST_EDIT_PHONE = 1;//编辑电话
    private final int REQUEST_EDIT_NICK_NAME = 2;//编辑昵称
    private final int REQUEST_EDIT_SIGNATURE = 3;//编辑签名
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private static boolean isLoaded = false;
    private SelectGradeDialog mGradeDialog;
    private GetPersonalInfoModel.Databean mData;//用户信息
    private RvAdapterUserInfoVersion mRvAdapter;//用户信息adapter

    private List<SubjectVersionModel.Databean> mDataSubject;
    private int grade_id;
    private List<GradeModel.Databean> mGradeModelList;
    private final int REQUEST_SELECT_IMAGES_CODE = 1;
    private ArrayList<String> mImgPaths = new ArrayList<>();//选择的图片路径集合
    private ArrayList<String> mDataNewPaths;//压缩后的图片路径集合
    private String[] mImgBase64Arr;//base64格式图片数组

    @Override
    protected void initData() {
        initSpringView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_user_information;
    }

    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(this));
//        mSpringView.setFooter(new DefaultFooter(this));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mGradeModelList.size() == 0) {
                            mHttpHelper.getGrade(UserInfoActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                        }
                        mHttpHelper.getPersonalInfo(UserInfoActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                        mHttpHelper.getSubjectVersionClassification(UserInfoActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());

                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        mGradeModelList = SPUtil.getGradeList();
        if (mGradeModelList.size() == 0) {
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        }
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        mHttpHelper.getPersonalInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
        mHttpHelper.getSubjectVersionClassification(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mFlBack.setOnClickListener(this);
        mRlPortrait.setOnClickListener(this);
        mRlSignatrue.setOnClickListener(this);
        mRlArea.setOnClickListener(this);
        mRlPhone.setOnClickListener(this);
        mRlGrade.setOnClickListener(this);
        mRlNickname.setOnClickListener(this);
    }

    //EventBus接收微信分享返回
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void handleData(CommonMessageEvent mMessageEvent) {
//            LogUtil.e("__handleData_getType",mMessageEvent.getType() + "");
        if (mMessageEvent.getType() == Constants.REQUEST_SHARE) {
            LogUtil.e("__handleData_readAct", Constants.REQUEST_SHARE + "");
//            LogUtil.e("__handleData_getContent",mMessageEvent.getContent());
//            mHttpHelper.WXLogin(this,CommonUtils.getCurrentTimeStampInt(),mMessageEvent.getContent());
        }

    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fl_back:
                finish();
                break;
            case R.id.rl_area:
                showPickerView();
                break;
            case R.id.rl_grade:
                showSelectGradeDia();
                break;
            case R.id.rl_nickname:
                //表明没有获取到数据，需要用户重试
                if (mData == null) {
                    ToastUtil.showToast("没有数据，请刷新重试");
                    return;
                }
                intent = new Intent(this, EditNicknameActivity.class);
                if (mData.getWechatnickname() == null || mData.getWechatnickname().equals("")) {
                    intent.putExtra("nickname", "");
                } else {
                    intent.putExtra("nickname", mData.getWechatnickname());
                }
                startActivity(intent);
                break;
            case R.id.rl_phone:
                if (mData == null) {
                    ToastUtil.showToast("没有数据，请刷新重试");
                    return;
                }
                //手机为空则跳转到绑定手机界面
                if (mData.getPhone() == null || mData.getPhone().equals("")) {
//                    intent = new Intent(this, CompleteRegisterActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                    intent = new Intent(this, EditPhoneActivity.class);
                    intent.putExtra("phone", "");
                    startActivity(intent);
                } else {
                    intent = new Intent(this, EditPhoneActivity.class);
                    intent.putExtra("phone", mData.getPhone());
                    startActivity(intent);
                }
                break;
            case R.id.rl_signatrue:
                if (mData == null) {
                    ToastUtil.showToast("没有数据，请刷新重试");
                    return;
                }
                intent = new Intent(this, EditSignatureActivity.class);
                //手机为空则跳转到绑定手机界面
                if (mData.getSignature() == null || mData.getSignature().equals("")) {
                    intent.putExtra("signature", "好好学习，天天向上");
                } else {
                    intent.putExtra("signature", mData.getSignature());
                }
                startActivity(intent);
                break;
            case R.id.rl_portrait:
                if (mData == null) {
                    ToastUtil.showToast("没有数据，请刷新重试");
                    return;
                }
                IOSAlertDialog iosAlertDialog = new IOSAlertDialog(this).builder();
                iosAlertDialog.setMsg("是否修改头像").setNegativeButton("取消", null).setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSelectImg();
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    private void showSelectImg() {
        ImagePicker.getInstance().setTitle("请选择图片")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                .setImagePaths(mImgPaths)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(this, REQUEST_SELECT_IMAGES_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == RESULT_OK) {
            ArrayList<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            mImgPaths = imagePaths;
            uploadImg();
        }
    }

    /**
     * 上传头像
     */
    private void uploadImg() {
        showCustomProgressDialog("图片处理上传中，请稍等", (mImgPaths.size() + 1) * 4000);
        try {
            mImgBase64Arr = new String[mImgPaths.size()];
            mDataNewPaths = new ArrayList<>();
            ImgUtils.compressImg(this, mImgPaths, mDataNewPaths);
            //压缩需要时间，设置延迟
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LogUtil.e("__imgmDataNewPaths_s", mDataNewPaths.size() + "");
                    for (int i = 0; i < mDataNewPaths.size(); i++) {
                        String path = mDataNewPaths.get(i);
                        //把图片变成base64编码的字符串
                        ImgUtils.getBase64ImgByPath(UserInfoActivity.this, path, mImgBase64Arr, i);
                    }

                }
            }, mImgPaths.size() * 1100);
            //转换成base64需要时间，设置延迟
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                    LogUtil.e("__imgmDataNewPaths_s",mImgBase64Arr.length + "");
//                    for (int i = 0; i < mImgBase64Arr.length; i++) {
//                        LogUtil.e("__imgmDataNewPaths_" + i,String.valueOf(mImgBase64Arr[i]));
//                    }
                    //多张上传
                    mHttpHelper.uploadImgs(UserInfoActivity.this, CommonUtils.getCurrentTimeStampInt(), mImgBase64Arr);
                }
            }, (mImgPaths.size() + 1) * 1700);

        } catch (Exception e) {
            LogUtil.e("__uploadImg_e", e.getMessage());
        }

    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case Constants.REQUEST_GET_PERSONAL_INFO:
                GetPersonalInfoModel userInfoModel = (GetPersonalInfoModel) msg.obj;
                mData = userInfoModel.getData();
                setData();
                break;
            case Constants.REQUEST_GET_SUBJECT_VERSION_CLASSIFICATION:
                SubjectVersionModel model = (SubjectVersionModel) msg.obj;
                mDataSubject = model.getData();
                break;
            case Constants.REQUEST_SAVE_SUBJECT_AND_VERSION:
                //修改年级/版本成功
                ToastUtil.showToast("修改成功");
                mHttpHelper.getUserInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                mHttpHelper.getPersonalInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                break;
            case Constants.REQUEST_GET_GRADE:
                GradeModel gradeModel = (GradeModel) msg.obj;
                //保存到SP中
                SPUtil.saveGradeList(gradeModel.getData());
                mGradeModelList = gradeModel.getData();
                break;
            case Constants.REQUEST_EDIT_ADDRESS:
                ToastUtil.showToast("修改地区成功");
                mHttpHelper.getPersonalInfo(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
                break;
            case Constants.REQUEST_GET_USER_INFO:
                UserInfoModel loginModel = (UserInfoModel) msg.obj;
                UserInfoModel.Databean databean1 = loginModel.getData();
                setUserInfo(databean1);
                break;
            case Constants.REQUEST_UPLOAD_IMGS:
                //上传图片成功，提交修改头像
                UploadImgModel uploadImgModel = (UploadImgModel) msg.obj;
                List<String> list = uploadImgModel.getData().getImages();
                for (int i = 0; i < list.size(); i++) {
                    mHttpHelper.editHead(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), list.get(i));
                }
                break;
            case Constants.REQUEST_EDIT_HEAD:
                dismissProgressDialog();
                //修改头像成功
                EditHead editHead = (EditHead) msg.obj;
                ToastUtil.showToast("修改头像成功");
                loadData();
                break;
            case Constants.REQUEST_EDIT_GRADE_AND_VERSION:
                ToastUtil.showToast("修改成功");
                //通知首页和选课界面刷新年级，重新请求数据
                EventBus.getDefault().post(new CommonMessageEvent(Constants.REQUEST_EDIT_GRADE_AND_VERSION, ""));
                break;
        }
    }
    /**
     * 设置用户信息
     * @param databean
     */
    private void setUserInfo(UserInfoModel.Databean databean){
        UserManager.getInstance().setToken(databean.getToken());
        UserManager.getInstance().setHeadImgUrl(databean.getHeadimgurl());
        UserManager.getInstance().setIntegral(databean.getIntegral());
        UserManager.getInstance().setSex(databean.getSex());
        if (databean.getCountry() != null){
            UserManager.getInstance().setCountry(databean.getCountry());
        }
        if (databean.getProvince() != null){
            UserManager.getInstance().setProvince(databean.getProvince());
        }
        if (databean.getCity() != null){
            UserManager.getInstance().setCity(databean.getCity());
        }
        UserManager.getInstance().setVipTime(databean.getVip_time());
//        UserManager.getContext().setVipTime(CommonUtils.getCurrentTimeStampInt());
        UserManager.getInstance().setUserPhone(databean.getPhone());
        UserManager.getInstance().setUserIcon(databean.getHeadimgurl());
        //0表示不是VIP,1表示是VIP
        if (databean.getIsVip() == 0){
            UserManager.getInstance().setIsVip(false);
        }else {
            UserManager.getInstance().setIsVip(true);
        }
    }

    private void setData() {
        //Glide加载圆形头像
        RequestOptions options = new RequestOptions().circleCrop()
                .placeholder(R.drawable.img_user)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(this).load(mData.getHeadimgurl()).apply(options).into(mIvPortrait);
        if(mData.getSignature()!=null && !mData.getSignature().equals("")){
            mTvSignatrue.setText(mData.getSignature());
        }
        if(mData.getWechatnickname()!=null && !mData.getWechatnickname().equals("")){
            mTvNickname.setText(mData.getWechatnickname());
        }else {
            mTvNickname.setText("未设置");
        }
        if(mData.getPhone()!=null && !mData.getPhone().equals("")){
            mTvPhone.setText(mData.getPhone());
        }
        if(mData.getCity()!=null && !mData.getCity().equals("")){
            mTvArea.setText(mData.getCity());
        }
        if (mData.getGrade()!= null && !mData.getGrade().getOption().equals("")){
            mTvGrade.setText(mData.getGrade().getOption());
            grade_id = mData.getGrade().getId();
        }
        if (mData.getSelect_course()!=null&&mData.getSelect_course().size()>0){
            mRvAdapter = new RvAdapterUserInfoVersion(this,mData);
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mRv.setNestedScrollingEnabled(false);
            mRv.setAdapter(mRvAdapter);
            mRvAdapter.setOnItemClickListener(this);
        }
        UserManager.getInstance().setGradeId(mData.getGrade().getId());
        //通知首页和选课界面刷新年级，重新请求数据
        EventBus.getDefault().post(new CommonMessageEvent(Constants.REQUEST_EDIT_GRADE_AND_VERSION,""));
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_SAVE_SUBJECT_AND_VERSION:
                ToastUtil.showToast("修改失败");
                break;
            case Constants.REQUEST_EDIT_HEAD:
                dismissProgressDialog();
                ToastUtil.showToast("修改头像失败");
                break;
            case Constants.REQUEST_UPLOAD_IMGS:
                dismissProgressDialog();
                ToastUtil.showToast("上传头像失败");
                break;
            case Constants.REQUEST_EDIT_GRADE_AND_VERSION:
                ToastUtil.showToast("修改失败");
                break;
        }
    }

    private void showPickerView() {//条件选择器初始化

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
//                btn_Options.setText(tx);
                mHttpHelper.editAddress(UserInfoActivity.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                        options1Items.get(options1).getPickerViewText(),options2Items.get(options1).get(options2));
                LogUtil.e("__onOptionsSelect", "options1Items:" + options1Items.get(options1).getPickerViewText());
                LogUtil.e("__onOptionsSelect", "options2Items" + options2Items.get(options1).get(options2));
                LogUtil.e("__onOptionsSelect", tx);
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.bg_gray5))
                .setCancelColor(getResources().getColor(R.color.bg_gray5))
                .setSubmitColor(getResources().getColor(R.color.blue_subject_home))
                .setTextColorCenter(getResources().getColor(R.color.black))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("省", "市", "区")
                .setOutSideColor(getResources().getColor(R.color.bg_gray_translucent)) //设置外部遮罩颜色
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
//                        Toast.makeText(UserInfoActivity.this, str, Toast.LENGTH_SHORT).show();

                    }
                })
                .build();

//        pvOptions.setSelectOptions(1,1);
//        pvOptions.setPicker(options1Items);//一级选择器
        if (options2Items.size() > 0) {
            pvOptions.setPicker(options1Items, options2Items);//二级选择器
        } else {
            //如果没有拿到城市数据，将会展示一个空的选择框，避免闪退
            pvOptions.setPicker(options1Items);
        }
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
//            options3Items.add(province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
//                        Toast.makeText(UserInfoActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        LogUtil.e("__MSG_LOAD_DATA", "Begin Parse Data");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
//                    Toast.makeText(UserInfoActivity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
//                    Toast.makeText(UserInfoActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    /**
     * 选择的课程版本item点击事件
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        if (mData == null){
            ToastUtil.showToast("没有获取到数据，请重试");
            return;
        }
        GetPersonalInfoModel.Databean.SelectCoursebean selectCoursebean = mData.getSelect_course().get(position);
        showSingleChoiceDialog(selectCoursebean,position);
    }
    TextView textView;
    private String[] array;
    /**
     * 展示选择版本dialog
     * @param selectCoursebean 选择的科目对象
     */
    private void showSingleChoiceDialog(GetPersonalInfoModel.Databean.SelectCoursebean selectCoursebean,int position) {
        if (mDataSubject == null){
            ToastUtil.showToast("获取数据失败，请重试");
            mHttpHelper.getSubjectVersionClassification(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
            return;
        }
        SubjectVersionModel.Databean databean = null;
        for (int i = 0; i < mDataSubject.size(); i++) {
            if (mDataSubject.get(i).getId() == selectCoursebean.getSubject_id()){
                databean = mDataSubject.get(i);
            }
        }
        if (databean == null){
            ToastUtil.showToast("数据异常，没有匹配的版本信息");
            return;
        }
        array = new String[databean.getVersion().size()];
        for (int i = 0; i < array.length; i++) {
            SubjectVersionModel.Databean.Versionbean versionbean = databean.getVersion().get(i);
            array[i] = versionbean.getVersion_name();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setGravity(Gravity.CENTER);
        title.setTextSize(20);
        title.setTextColor(getResources().getColor(R.color.black));
        title.setPadding(0, ScaleUtils.dip2px(10),0,0);
//        title.setLayoutParams(layoutParams);
        title.setText("请选择" + databean.getSubject() + "版本");
        builder.setCustomTitle(title);
        builder.setSingleChoiceItems(array,databean.getSelectedPosition(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                String str = array[which];
//                textView.setText(str);
                onDiaVersionSelected(position,which);
                dialog.dismiss();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 版本dialog选择之后
     * @param position rvAdapter的科目位置
     * @param which dialog选择的位置
     */
    private void onDiaVersionSelected(int position, int which){

        GetPersonalInfoModel.Databean.SelectCoursebean coursebean = mData.getSelect_course().get(position);
        for (int i = 0; i < mDataSubject.size(); i++) {
            if (coursebean.getSubject_id() == mDataSubject.get(i).getId()){
                SubjectVersionModel.Databean databean = mDataSubject.get(i);
                coursebean.getVersion().setId(databean.getVersion().get(which).getVersion_id());
                coursebean.getVersion().setVersion(databean.getVersion().get(which).getVersion_name());
            }
        }
        if (mRvAdapter!=null){
            mRvAdapter.notifyDataSetChanged();
        }
        commitVersion();
    }

    /**
     * 提交用户选择的版本/年级到后台
     */
    private void commitVersion(){
        StringBuilder select_course = new StringBuilder();
        List<GetPersonalInfoModel.Databean.SelectCoursebean> courseList = mData.getSelect_course();
        for (int i = 0; i <courseList.size(); i++) {
            if (i == courseList.size() -1){
                GetPersonalInfoModel.Databean.SelectCoursebean databean = courseList.get(i);
                select_course.append(databean.getSubject_id());
                select_course.append("@");
                select_course.append(databean.getVersion().getId());
            }else {
                GetPersonalInfoModel.Databean.SelectCoursebean databean = courseList.get(i);
                select_course.append(databean.getSubject_id());
                select_course.append("@");
                select_course.append(databean.getVersion().getId());
                select_course.append(",");
            }
        }
        mHttpHelper.saveSubjectAndVersion(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),String.valueOf(grade_id),select_course.toString());
    }

    private void showSelectGradeDia(){
        if (mGradeModelList.size() == 0){
            mHttpHelper.getGrade(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
            ToastUtil.showToast("获取年级列表失败，请重试");
            return;
        }
        mGradeDialog  = new SelectGradeDialog(this,mGradeModelList).builder();
        mGradeDialog.setOnDiaItemClickListener(new SelectGradeDialog.OnDiaItemClickListener() {
            @Override
            public void onDiaItemClick(int position, int id) {
                grade_id = id;
//                commitVersion();
                //发送到后台修改年级
                mHttpHelper.editSelectGradeAndVersion(UserInfoActivity.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),String.valueOf(grade_id),"");
            }
        });
        for (int i = 0; i < mGradeModelList.size(); i++) {
            try {
                GradeModel.Databean databean = mGradeModelList.get(i);
                if (databean.getId() == UserManager.getInstance().getGradeId()) {
                    mGradeDialog.setSelection(i);
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        mGradeDialog.show();
    }

    private void showShare() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon, options);
        String title = "格灵同步培优";//分享标题
        String content = "更多内容，敬请下载格灵同步培优";//分享内容
//        String shareUrl = "https://sj.qq.com/myapp/detail.htm?apkName=net.getlearn.getlearn_android";//应用宝下载链接
        String shareUrl = "https://shouji.baidu.com/software/26360663.html";//百度手机助手
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle(getString(R.string.share));
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        oks.setImageUrl("https://e.topthink.com/Uploads/Picture/2018-10-29/5bd6d218d381b.jpg");//网络图片
        oks.setImageData(bitmap);//bitmap图片
        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
        oks.setUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }

}