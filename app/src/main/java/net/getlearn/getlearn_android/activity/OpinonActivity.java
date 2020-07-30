package net.getlearn.getlearn_android.activity;


import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcw.library.imagepicker.ImagePicker;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterSelectImg;
import net.getlearn.getlearn_android.model.bean.FeedbackModel;
import net.getlearn.getlearn_android.model.bean.UploadImgModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.GlideLoader;
import net.getlearn.getlearn_android.utils.ImgUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SoftKeyBoardListener;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
* 意见反馈
* */
public class OpinonActivity extends BaseActivity {

    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.ll_main)
    LinearLayout mLlMain;
    @BindView(R.id.sz)
    TextView mSz;
    @BindView(R.id.tv_img_amount)
    TextView mTvImgAmount;
    @BindView(R.id.tv_text_amount)
    TextView mTvTextAmount;
    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.ll_phone_title)
    LinearLayout mLlPhoneTitle;
    @BindView(R.id.btn_commint)
    Button mBtnCommint;
    @BindView(R.id.view_divider)
    View mViewDivider;

    private ArrayList<String> mData = new ArrayList<>();//选择的图片路径集合
    private ArrayList<String> mDataNewPaths;//压缩后的图片路径集合

    private RvAdapterSelectImg mAdapter;
    private IOSAlertDialog mDialog;
    private String[] mImgBase64Arr;//base64格式图片数组
    private boolean mHasFocus;
    private int id;//提交文字成功之后能获取到ID，在提交图片的时候，需要把id放进去才能是同一个反馈
    public String phone;
    private String  content;
    private UploadImgModel mUploadImgModel;
    private int imgSuccessCount;//提交成功的图片数量（注意：是提交不是上传）
    private int imgFailCount;//提交失败的图片数量（注意：是提交不是上传）
    @Override
    public void onClick(View view) {
        if (mAdapter.isShowDelIv()){
            mAdapter.setShowDelIv(false);
            return;
        }
        switch (view.getId()) {
            case R.id.fl_back:
                finish();
                break;
            case R.id.btn_commint:
                id = 0;
                content = mEditText.getText().toString().replace(" ","");
                phone = mEditPhone.getText().toString();
                LogUtil.e("__mEditPhone",mEditPhone.getText()+"");
                LogUtil.e("__mEditPhone1",phone);
                if (content == null || content.length() < 5 ){
                    ToastUtil.showToast("内容不能为空或少于5个字");
                    return;
                }else if (phone.equals("")||phone==null){
                    ToastUtil.showToast("手机号码不能为空");
                    return;
                }else if (phone.length()!= 11){
                     ToastUtil.showToast("请输入11位手机号码");
                     return;
                }else {
                    mHttpHelper.feedback(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(), content, "", phone, id);

                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        mEditPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                mHasFocus = hasFocus;
                if (hasFocus) {
                    LogUtil.e("__onFocusChange", "edtUserName获取到焦点了。。。。。。");
                } else {
                    LogUtil.e("__onFocusChange", "edtUserName失去焦点了。。。。。。");
                }
            }
        });
        //设置监听用户是否在输入是手机号码，如果是的话，隐藏其他空间，不然看不到手机号码输入框
        SoftKeyBoardListener.setListener(OpinonActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                LogUtil.e("__keyBoardShow","键盘显示 高度" + height);
                if (mHasFocus){
                    mLlTop.setVisibility(View.GONE);
                    mBtnCommint.setVisibility(View.GONE);
                }else {
                    mLlTop.setVisibility(View.VISIBLE);
                    mBtnCommint.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void keyBoardHide(int height) {
                LogUtil.e("__keyBoardHide","键盘隐藏 高度" + height);
                mLlTop.setVisibility(View.VISIBLE);
                mBtnCommint.setVisibility(View.VISIBLE);
            }
        });
        RecyclerView.LayoutManager manager = new GridLayoutManager(this,3);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new RvAdapterSelectImg(this,mData,4);
        mRv.setLayoutManager(manager);
        mRv.setNestedScrollingEnabled(false);
        mAdapter.setOnItemClickListener(new RvAdapterSelectImg.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onAddImgClick() {
                showSelectImg();
            }
        });

        mAdapter.setOnItemDelClickListener(new RvAdapterSelectImg.OnItemDelClickListener() {
            @Override
            public void onItemDelClick(int position) {
                mDialog = new IOSAlertDialog(OpinonActivity.this).builder();
                mDialog.setMsg("是否删除图片").setNegativeButton("取消",null).setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mData.remove(position);
                        mAdapter.notifyDataSetChanged();
                        mTvImgAmount.setText(mData.size() + "/4");
                    }
                }).show();
            }
        });
        mRv.setAdapter(mAdapter);
        mTvImgAmount.setText(mData.size() + "/4");

    }

    private void uploadImg(){
        showCustomProgressDialog("图片处理上传中，请稍等",(mData.size() + 1) * 4000);
        try {
            mImgBase64Arr = new String[mData.size()];
            mDataNewPaths = new ArrayList<>();
            ImgUtils.compressImg(this,mData,mDataNewPaths);
            //压缩需要时间，设置延迟
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LogUtil.e("__imgmDataNewPaths_s",mDataNewPaths.size()+"");
                    for (int i = 0; i < mDataNewPaths.size(); i++) {
                        String path = mDataNewPaths.get(i);
//                String imgBase64 = ImgUtils.imageToBase64(path);
                        ImgUtils.getBase64ImgByPath(OpinonActivity.this,path,mImgBase64Arr,i);
//                        LogUtil.e("__imgBase64",imgBase64);
//                        if (imgBase64 == null){
//                    ToastUtil.showToast("图片转换格式失败");
//                        }else {
//                    mImgBase64Arr.add("imgBase64iii" + i);
//                            mImgBase64Arr[i] = imgBase64;
//                        }
                        //单张上传
//                        mHttpHelper.uploadImg(OpinonActivity.this,CommonUtils.getCurrentTimeStampInt(),
//                                imgBase64);

                    }
                    //多张上传
//                    mHttpHelper.uploadImgs(OpinonActivity.this,CommonUtils.getCurrentTimeStampInt(),
//                            mImgBase64Arr);
                }
            }, mData.size() * 1100);
            //转换成base64需要时间，设置延迟
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LogUtil.e("__imgmDataNewPaths_s",mImgBase64Arr.length + "");
                    for (int i = 0; i < mImgBase64Arr.length; i++) {
                        LogUtil.e("__imgmDataNewPaths_" + i,String.valueOf(mImgBase64Arr[i]));
                    }
                    //多张上传
                    mHttpHelper.uploadImgs(OpinonActivity.this,CommonUtils.getCurrentTimeStampInt(),
                            mImgBase64Arr);
                }
            }, (mData.size() + 1) * 1700);

        }catch (Exception e){
            LogUtil.e("__uploadImg_e",e.getMessage());
        }

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_opinon;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mBtnCommint.setOnClickListener(this);
        mLlMain.setOnClickListener(this);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTvTextAmount.setText(charSequence.length() + "/500");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
            switch (reqType){
                case Constants.REQUEST_FEEDBACK_MSG:
                    LogUtil.e("__onHttpSuccess","提交信息成功");
                    id = 0;
                    FeedbackModel feedbackModel  = (FeedbackModel) msg.obj;
                    if (feedbackModel.getData().getFeedBack().getId() != null && !feedbackModel.getData().getFeedBack().getId().equals("")){
                        try {
                            id = Integer.valueOf(feedbackModel.getData().getFeedBack().getId());
                        }catch (Exception e){

                        }
                    }
                    if (mData.size() == 0){
                        ToastUtil.showToast("提交成功");
                        startActivity(new Intent(this,MyFeedbackListActivity.class));
                        finish();
                    }else if (mData.size() > 0) {
                        uploadImg();
                    }
                    break;
                case Constants.REQUEST_FEEDBACK_IMG://提交意见反馈--图片
                    imgSuccessCount ++;
                    LogUtil.e("__success","REQUEST_FEEDBACK_IMG_1" + imgSuccessCount);
                    //说明目前没有提交失败的图片
                    if (imgFailCount == 0){
                        //说明所有上传成功的图片,都提交反馈成功了（上传和提交意见反馈是不一样的，图片都是先上传到后台，拿到URL之后再提交意见反馈的）
                        if (imgSuccessCount ==  mUploadImgModel.getData().getImages().size()){
                            LogUtil.e("__success","REQUEST_FEEDBACK_IMG_2" + imgSuccessCount);
                            dismissProgressDialog();
                            //再验证一下是否有的没有上传成功
                            if (mUploadImgModel != null && mUploadImgModel.getData().getImages().size() == mData.size()){
                                LogUtil.e("__success","REQUEST_FEEDBACK_IMG_3" + imgSuccessCount);
                                ToastUtil.showToast("图片上传成功");
                            }else {
                                LogUtil.e("__success","REQUEST_FEEDBACK_IMG_4" + (mData.size() - mUploadImgModel.getData().getImages().size()) +  "张图片提交失败");
                                ToastUtil.showToast(mData.size() - mUploadImgModel.getData().getImages().size() +  "张图片提交失败");
                            }
                            imgSuccessCount = 0;
                            imgFailCount = 0;
                            finish();
                            startActivity(new Intent(this,MyFeedbackListActivity.class));
                        }
                    }else {//imgFailCount不等于0的情况，说明有的提交没成功
                        LogUtil.e("__success","REQUEST_FEEDBACK_IMG_5" + imgSuccessCount);
                        //说明有的提交没成功
                        if (imgSuccessCount + imgFailCount ==  mUploadImgModel.getData().getImages().size()){
                            dismissProgressDialog();
                            //证明部分上传成功的图片，提交意见反馈失败了
                            ToastUtil.showToast(mData.size() - imgSuccessCount +  "张图片提交失败");
                            LogUtil.e("__success","REQUEST_FEEDBACK_IMG_6" + (mData.size() - imgSuccessCount) + "张图片提交失败");
                            imgSuccessCount = 0;
                            imgFailCount = 0;
                            finish();
                            startActivity(new Intent(this,MyFeedbackListActivity.class));
                        }
                    }
                    break;
                case Constants.REQUEST_UPLOAD_IMG_SINGLE:
                    LogUtil.e("__success","REQUEST_UPLOAD_IMG_SINGLE");
                break;
                //上传多图片接口,BASE64格式
                case Constants.REQUEST_UPLOAD_IMGS:
                    LogUtil.e("__success","REQUEST_UPLOAD_IMGS");
                    //上传成功图片成功，提交反馈
                    mUploadImgModel = (UploadImgModel) msg.obj;
                    List<String> list= mUploadImgModel.getData().getImages();
                    imgSuccessCount = 0;
                    imgFailCount = 0;
                    for (int i = 0; i < list.size(); i++) {
                        mHttpHelper.feedback(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                                "",list.get(i),phone,id);
                        LogUtil.e("__id",id+"");
                    }
                    break;
                    default:break;
            }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_FEEDBACK_MSG:
                LogUtil.e("__onHttpError","意见反馈，提交信息失败");
                ToastUtil.showToast("意见反馈，提交信息失败");
                break;
            case Constants.REQUEST_FEEDBACK_IMG:
                LogUtil.e("__onHttpError","意见反馈，提交图片失败");
                imgFailCount++;
                break;
            case Constants.REQUEST_UPLOAD_IMGS:
//                if (imgFailCount + imgSuccessCount == mDataNewPaths.size()){
//                    ToastUtil.showToast(imgFailCount + "张图片上传失败");
//                    imgFailCount = 0;
//                    imgSuccessCount = 0;
//                }else {
//                    imgFailCount++;
//                }
//                LogUtil.e("__onHttpError","上传图片失败");
                break;

            case Constants.REQUEST_UPLOAD_IMG_SINGLE:
                LogUtil.e("__onHttpError","上传单张图片失败");
                break;
            default:break;
        }
    }

    private final int REQUEST_SELECT_IMAGES_CODE = 1;

    private void showSelectImg(){
        ImagePicker.getInstance()
                .setTitle("请选择图片")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(4)//设置最大选择图片数目(默认为1，单选)
                .setImagePaths(mData)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(this, REQUEST_SELECT_IMAGES_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == RESULT_OK) {
            ArrayList<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            for (int i = 0; i < imagePaths.size(); i++) {
                LogUtil.e("__select_img_path",imagePaths.get(i));
            }
            mData = imagePaths;
            initData();
        }
    }

    @Override
    public void onBackPressed() {
        if (mAdapter.isShowDelIv()){
            mAdapter.setShowDelIv(false);
        }else {
            super.onBackPressed();
        }
    }
}
