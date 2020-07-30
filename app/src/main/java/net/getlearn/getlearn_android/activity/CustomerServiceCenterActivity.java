package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.MImageGetter;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.UserProtocolModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 * 客服中心
 */

public class CustomerServiceCenterActivity extends BaseActivity {


    @BindView(R.id.ll_feedback)
    LinearLayout mLlFeedback;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_my_feedback)
    TextView mTvMyFeedback;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.scroll_view)
    ScrollView mScrollView;
    @BindView(R.id.tv_common_question)
    TextView mTvCommonQuestion;
    @BindView(R.id.view_divider_common_question)
    View mViewDividerCommonQuestion;


    private UserProtocolModel.Databean mDatabean;
    @Override
    protected void initData() {
        mHttpHelper.getCommonQuestion(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_customer_service_center;
    }

    @Override
    protected void initView() {

//        mData.add(new CommonQuestionModel("一、课程进度是如何安排的？","根据小学生教学大纲的时间安排教学进度，有时学校" +
//                "" +
//                "内的课程安排时间可能不完全一致，由于课程是教材" +
//                "" +
//                "同步的，不影响学生正常学习，请学生和家长放心使用"));
//        mData.add(new CommonQuestionModel("二、课程进度是如何安排的？","根据小学生教学大纲的时间安排教学进度，有时学校" +
//                "" +
//                "内的课程安排时间可能不完全一致，由于课程是教材" +
//                "" +
//                "同步的，不影响学生正常学习，请学生和家长放心使用"));
//        mData.add(new CommonQuestionModel("三、课程进度是如何安排的？","根据小学生教学大纲的时间安排教学进度，有时学校" +
//                "" +
//                "内的课程安排时间可能不完全一致，由于课程是教材" +
//                "" +
//                "同步的，不影响学生正常学习，请学生和家长放心使用"));
//        mData.add(new CommonQuestionModel("四、课程进度是如何安排的？","根据小学生教学大纲的时间安排教学进度，有时学校" +
//                "" +
//                "内的课程安排时间可能不完全一致，由于课程是教材" +
//                "" +
//                "同步的，不影响学生正常学习，请学生和家长放心使用"));
//        mData.add(new CommonQuestionModel("五、课程进度是如何安排的？","根据小学生教学大纲的时间安排教学进度，有时学校" +
//                "" +
//                "内的课程安排时间可能不完全一致，由于课程是教材" +
//                "" +
//                "同步的，不影响学生正常学习，请学生和家长放心使用"));
//        mData.add(new CommonQuestionModel("六、课程进度是如何安排的？","根据小学生教学大纲的时间安排教学进度，有时学校" +
//                "" +
//                "内的课程安排时间可能不完全一致，由于课程是教材" +
//                "" +
//                "同步的，不影响学生正常学习，请学生和家长放心使用"));
//        mData.add(new CommonQuestionModel("七、课程进度是如何安排的？","根据小学生教学大纲的时间安排教学进度，有时学校" +
//                "" +
//                "内的课程安排时间可能不完全一致，由于课程是教材" +
//                "" +
//                "同步的，不影响学生正常学习，请学生和家长放心使用"));
//
//        mLvAdapter = new LvAdapterCommonQuestion(mData,this);
//        mLv.setAdapter(mLvAdapter);
        mLlFeedback.setOnClickListener(this);
        mTvMyFeedback.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_customer_service:
                LogUtil.e("__onClick","ll_customer_service");
            break;
            case R.id.ll_feedback:
                intent = new Intent(this,OpinonActivity.class);
                startActivity(intent);
            break;
            case R.id.tv_my_feedback:
//                LogUtil.e("__onClick","tv_my_feedback");
                intent = new Intent(this,MyFeedbackListActivity.class);
                startActivity(intent);
            break;
            case R.id.fl_back:
                finish();
                break;

        }
    }


    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_COMMON_QUESTION:
                UserProtocolModel userProtocolModel = (UserProtocolModel) msg.obj;
                mDatabean = userProtocolModel.getData();
                setData();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType) {
            case Constants.REQUEST_COMMON_QUESTION:
//                ToastUtil.showToast("获取常见问题失败，请重试");
                break;
        }
    }

    private void setData() {
        mScrollView.setVisibility(View.VISIBLE);
        mViewDividerCommonQuestion.setVisibility(View.VISIBLE);
        mTvCommonQuestion.setText(Html.fromHtml(mDatabean.getProtocol(), new MImageGetter(mTvCommonQuestion, getApplicationContext()), null));
    }
}
