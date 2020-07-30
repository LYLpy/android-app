package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcw.library.imagepicker.ImagePicker;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterMyFeedBackMsg;
import net.getlearn.getlearn_android.model.bean.MyFeedbackMsgModel;
import net.getlearn.getlearn_android.model.bean.UploadImgModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.GlideLoader;
import net.getlearn.getlearn_android.utils.ImgUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/19------更新------
 * 我的反馈详情
 */

public class MyFeedbackMsgActivity extends BaseActivity {

    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.iv_select_img)
    ImageView mIvSelectImg;
    @BindView(R.id.edit_comment)
    EditText mEdit;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.tv_send)
    TextView mTvSecd;
    private int parent_id;
    private String phone;
    private MyFeedbackMsgModel.Databean mData;
    private int page = 1;
    private int per_page = 20;
    private RvAdapterMyFeedBackMsg mAdapter;
    private final int REQUEST_SELECT_IMAGES_CODE = 1;
    private ArrayList<String> mImgPaths = new ArrayList<>();//选择的图片路径集合
    private ArrayList<String> mDataNewPaths;//压缩后的图片路径集合
    private String[] mImgBase64Arr;//base64格式图片数组

    @Override
    protected void initData() {
        LogUtil.e("__userId_MyFeedbackMsgActivity", UserManager.getInstance().getUserId() + "");
        Intent intent = getIntent();
        parent_id = intent.getIntExtra("parent_id",0);

        phone = intent.getStringExtra("phone");
        LogUtil.e("__json46_phone",phone);


        phone = intent.getStringExtra("phone");

        mHttpHelper.myFeedBackMsgList(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,parent_id);
    }

    @Override
    protected int getLayoutRes(){
        return R.layout.activity_my_feedback_msg;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mIvSelectImg.setOnClickListener(this);
        mTvSecd.setOnClickListener(this);
        initSpringView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                case R.id.fl_back:
                    finish();
                    break;
            case R.id.tv_send:
                String content = mEdit.getText().toString().replace(" ","");

                if (content.equals("")){
                    ToastUtil.showToast("消息不能为空");
                    return;
                }
                LogUtil.e("__btn_send","发送");
                if (content == null || content.length() < 5){
                    ToastUtil.showToast("内容不能少于5个字");
                    return;
                }
                mHttpHelper.feedback(this,CommonUtils.getCurrentTimeStampInt(),
                        UserManager.getInstance().getToken(),content,"",phone,parent_id);
                break;
            case R.id.iv_select_img:
                showSelectImg();
                break;

        }
    }

    private void setData(MyFeedbackMsgModel.Databean myFeedBackMsgModel) {
        if (myFeedBackMsgModel!=null && myFeedBackMsgModel.getFeedBackList()!=null &&
                myFeedBackMsgModel.getFeedBackList().size() > 0){
            page++;
        }
        //证明是第一次加载
        if (mData == null){
            mData = myFeedBackMsgModel;
            //倒序
//            for (int i = 0; i >= 0; i--) {
//
//            }
            mAdapter = new RvAdapterMyFeedBackMsg(this,mData);
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new RvAdapterMyFeedBackMsg.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
            //滚动到最底部
            mRv.scrollToPosition(mData.getFeedBackList().size() - 1);
            //之前已经有数据了，将数据加进去，刷新即可
        }else {
            List<MyFeedbackMsgModel.Databean.FeedBackListbean> data = mData.getFeedBackList();
            List<MyFeedbackMsgModel.Databean.FeedBackListbean> newData = myFeedBackMsgModel.getFeedBackList();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapter.notifyDataSetChanged();
        }

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
//                        page = 1;
//                        mData = null;
//                        if (mAdapter!=null){
//                            mAdapter.removeData();
//                        }
                        mHttpHelper.myFeedBackMsgList(MyFeedbackMsgActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,parent_id);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.myFeedBackMsgList(MyFeedbackMsgActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,parent_id);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_MY_FEEDBACK_MSG:
                ToastUtil.showToast("获取数据失败");
                break;
            case Constants.REQUEST_UPLOAD_IMGS:
                ToastUtil.showToast("上传图片失败");
                break;
            case Constants.REQUEST_FEEDBACK_IMG:
                ToastUtil.showToast("提交图片失败");
                break;
            case Constants.REQUEST_FEEDBACK_MSG:
                ToastUtil.showToast("消息提交失败");
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_MY_FEEDBACK_MSG:
                MyFeedbackMsgModel myFeedbackMsgModel = (MyFeedbackMsgModel) msg.obj;
                setData(myFeedbackMsgModel.getData());
                break;
            case Constants.REQUEST_UPLOAD_IMGS:
                //上传成功图片成功，提交反馈
                UploadImgModel uploadImgModel = (UploadImgModel) msg.obj;
                List<String> list= uploadImgModel.getData().getImages();
                dismissProgressDialog();
                for (int i = 0; i < list.size(); i++) {
                    mHttpHelper.feedback(this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),
                            "",list.get(i),phone,parent_id);
                }
                break;
            case Constants.REQUEST_FEEDBACK_MSG:
                //消息提交成功，刷新
                ToastUtil.showToast("消息提交成功");
                mEdit.setText("");
                page = 1;
                mData = null;
                if (mAdapter!=null){
                            mAdapter.removeData();
                        }
                mHttpHelper.myFeedBackMsgList(MyFeedbackMsgActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,parent_id);
                break;
            case Constants.REQUEST_FEEDBACK_IMG:
                dismissProgressDialog();
                //图片提交成功，刷新
                ToastUtil.showToast("图片提交成功");
                page = 1;
                mData = null;
                if (mAdapter!=null){
                    mAdapter.removeData();
                }
                mHttpHelper.myFeedBackMsgList(MyFeedbackMsgActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,per_page,parent_id);
                break;
        }
    }

    private void showSelectImg(){
        ImagePicker.getInstance()
                .setTitle("请选择图片")//设置标题
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
    private void uploadImg(){
        showCustomProgressDialog("图片处理上传中，请稍等",(mImgPaths.size() + 1) * 4000);
        try {
            mImgBase64Arr = new String[mImgPaths.size()];
            mDataNewPaths = new ArrayList<>();
            ImgUtils.compressImg(this,mImgPaths,mDataNewPaths);
            //压缩需要时间，设置延迟
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LogUtil.e("__imgmDataNewPaths_s",mDataNewPaths.size()+"");
                    for (int i = 0; i < mDataNewPaths.size(); i++) {
                        String path = mDataNewPaths.get(i);
                        //把图片变成base64编码的字符串
                        ImgUtils.getBase64ImgByPath(MyFeedbackMsgActivity.this,path,mImgBase64Arr,i);
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
                    mHttpHelper.uploadImgs(MyFeedbackMsgActivity.this,CommonUtils.getCurrentTimeStampInt(),
                            mImgBase64Arr);
                }
            }, (mImgPaths.size() + 1) * 1700);

        }catch (Exception e){
            LogUtil.e("__uploadImg_e",e.getMessage());
        }
    }
}
