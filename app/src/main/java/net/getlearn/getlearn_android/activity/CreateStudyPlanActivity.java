package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;
import net.getlearn.getlearn_android.view.CreateStudyPlanDialog;
import net.getlearn.getlearn_android.view.TimePickerDialog;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/14------更新------
 * 新建学习任务
 */

public class CreateStudyPlanActivity extends BaseActivity {

    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.edit_content)
    EditText mEditContent;
    @BindView(R.id.iv_arrow_end_time)
    ImageView mIvArrowEndTime;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.iv_arrow_remind_way)
    ImageView mIvArrowRemindWay;
    @BindView(R.id.tv_remind_way)
    TextView mTvRemindWay;
    private CreateStudyPlanDialog mDialog;
    private TimePickerDialog mTimePickerDialog;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    /*
    * 提醒模式，5种
    * */
    public static final int TIME_MODE_NOT_REMIND = 0;//不提醒
    public static final int TIME_MODE_REMIND_BEFORE_15_MIN = 1;
    public static final int TIME_MODE_REMIND_BEFORE_1_HOUR = 2;
    public static final int TIME_MODE_REMIND_BEFORE_3_HOUR = 3;
    public static final int TIME_MODE_REMIND_BEFORE_1_DAY = 4;

    private final int REQUEST_CODE = 5;

    public static final int MODE_REMIND_IN_APP = 1;//应用内提醒
    public static final int MODE_REMIND_BY_MSG = 0;//短信提醒

    //任务截止时间
    private Calendar mCalendarEnd;

    //任务提醒时间
    private Calendar mCalendarTip;


    //任务内容
    private String taskContent;

    //提醒方式：1表示应用内提示 0表示短信提示
    private int tipsMode;

    //提醒时间方式,如截止前15分钟
    private int mRemindTimeMode;

    @Override
    protected void initData() {
        tipsMode = MODE_REMIND_IN_APP;
        mRemindTimeMode = TIME_MODE_REMIND_BEFORE_15_MIN;
        mCalendarEnd = Calendar.getInstance();
        mCalendarEnd.set(Calendar.HOUR_OF_DAY,22);
        mCalendarEnd.set(Calendar.MINUTE,00);
        mCalendarTip = Calendar.getInstance();
        CalendarUtils.copyCalendar(mCalendarEnd,mCalendarTip);
        CalendarUtils.getMinuteAfterCalendar(mCalendarTip,-15);

        mDialog = new CreateStudyPlanDialog(this).builder();
        mDialog.setOnPopupItemClickListener(new CreateStudyPlanDialog.OnPopupItemClickListener() {
            @Override
            public void onPopUpItemClick(int position,Calendar calendar) {
                if (calendar == null){
                    mTimePickerDialog.showDateAndTimePickerDialog();
                }else {
                    mCalendarEnd = calendar;
                    StringBuilder endTimeStrB = new StringBuilder();
                    endTimeStrB.append(CalendarUtils.getMonthDayStrChinese(mCalendarEnd));
                    endTimeStrB.append(" " + CalendarUtils.getDayOfWeekChinese(mCalendarEnd));
                    endTimeStrB.append(" " + CalendarUtils.getTime(mCalendarEnd));
                    mTvEndTime.setText(endTimeStrB.toString());
                    setRemindTime();
//                    LogUtil.e("__confirm",CalendarUtils.getTimeYearMonthDayStr(mCalendarEnd) + " " + CalendarUtils.getTime(mCalendarEnd));
                }
            }
        });
        mTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.TimePickerDialogInterface() {

            @Override
            public void positiveListener() {
//                ToastUtil.showToast(mTimePickerDialog.getYear() + "-" + mTimePickerDialog.getMonth() +
//                        "-" + mTimePickerDialog.getDay() + " " + mTimePickerDialog.getHour() + ":" + mTimePickerDialog.getMinute());
                LogUtil.e("__positiveListener_getYear",mTimePickerDialog.getYear() + "");
                LogUtil.e("__positiveListener_getMonth",mTimePickerDialog.getMonth() + "");
                LogUtil.e("__positiveListener_getDay",mTimePickerDialog.getDay() + "");
                LogUtil.e("__positiveListener_getHour",mTimePickerDialog.getHour() + "");
                LogUtil.e("__positiveListener_getMinute",mTimePickerDialog.getMinute() + "");
                mCalendarEnd.set(Calendar.YEAR,mTimePickerDialog.getYear());
                mCalendarEnd.set(Calendar.MONTH,mTimePickerDialog.getMonth() - 1);
                mCalendarEnd.set(Calendar.DATE,mTimePickerDialog.getDay());
                mCalendarEnd.set(Calendar.HOUR_OF_DAY,mTimePickerDialog.getHour());
                mCalendarEnd.set(Calendar.MINUTE,mTimePickerDialog.getMinute());
//                LogUtil.e("__positiveListener_getYMD",CalendarUtils.getTimeYearMonthDayStr(mCalendarEnd));
                setShowEndTime();
                setRemindTime();
            }

            @Override
            public void negativeListener() {
                LogUtil.e("__negativeListener","negativeListener");
            }
        });
        setShowEndTime();
    }

    /**
     * 展示截止时间
     */
    private void setShowEndTime(){
        StringBuilder endTimeStrB = new StringBuilder();
        endTimeStrB.append(CalendarUtils.getMonthDayStrChinese(mCalendarEnd));
        endTimeStrB.append(" " + CalendarUtils.getDayOfWeekChinese(mCalendarEnd));
        endTimeStrB.append(" " + CalendarUtils.getTime(mCalendarEnd));
        mTvEndTime.setText(endTimeStrB.toString());
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_new_task;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
        mIvArrowEndTime.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
        mIvArrowRemindWay.setOnClickListener(this);
        mTvRemindWay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_confirm:
                LogUtil.e("__confirm","确定");
                taskContent = mEditContent.getText().toString().replace(" ","");
                if (taskContent == null || taskContent.equals("")){
                    ToastUtil.showToast("任务内容不能为空");
                    return;
                }
//                LogUtil.e("__confirm_CalendarEnd",CalendarUtils.getTimeYearMonthDayStr(mCalendarEnd) + " " + CalendarUtils.getTime(mCalendarEnd));
//                LogUtil.e("__confirm_time_mode",mRemindTimeMode + "");
//                LogUtil.e("__confirm_remide_time",CalendarUtils.getTimeYearMonthDayStr(mCalendarTip) + " " + CalendarUtils.getTime(mCalendarTip));
                //不提示
                if(mRemindTimeMode == 0){
                    mHttpHelper.createStudyPlan(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),taskContent,
                            tipsMode,CalendarUtils.getTimeYearMonthDayStr(mCalendarEnd) + " " + CalendarUtils.getTime(mCalendarEnd),
                            "",1,0,false);
                }else {
                    mHttpHelper.createStudyPlan(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),taskContent,
                            tipsMode,CalendarUtils.getTimeYearMonthDayStr(mCalendarEnd) + " " + CalendarUtils.getTime(mCalendarEnd),
                            CalendarUtils.getTimeYearMonthDayStr(mCalendarTip) + " " + CalendarUtils.getTime(mCalendarTip),1,0,false);
                }
            break;
            case R.id.iv_arrow_end_time:
            case R.id.tv_end_time:
                mDialog.show();
            break;
            case R.id.iv_arrow_remind_way:
            case R.id.tv_remind_way:
                intent =  new Intent(this,RemindSettingActivity.class);
                intent.putExtra("tipsMode",tipsMode);
                intent.putExtra("remindTimeMode",mRemindTimeMode);
                startActivityForResult(intent,REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_CREATE_STUDY_PLAN:
                ToastUtil.showToast("创建成功");
                EventBus.getDefault().post(new CommonMessageEvent(Constants.REQUEST_CREATE_STUDY_PLAN,""));
                finish();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_CREATE_STUDY_PLAN:
                ToastUtil.showToast("创建失败，请重试");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            tipsMode = data.getIntExtra("tipsMode",tipsMode);
            mRemindTimeMode = data.getIntExtra("remindTimeMode",mRemindTimeMode);
            setRemindTime();
        }
    }

    /**
     * 设置提醒时间
     */
    private void setRemindTime() {
        switch (mRemindTimeMode){
            case TIME_MODE_NOT_REMIND:
                mTvRemindWay.setText("不提醒");
                break;
            case TIME_MODE_REMIND_BEFORE_15_MIN:
                CalendarUtils.copyCalendar(mCalendarEnd,mCalendarTip);
                CalendarUtils.getMinuteAfterCalendar(mCalendarTip,-15);
                if (tipsMode == MODE_REMIND_IN_APP){
                    mTvRemindWay.setText("截止前15分钟APP内提醒");
                }else {
                    mTvRemindWay.setText("截止前15分钟短信提醒");
                }
                break;
            case TIME_MODE_REMIND_BEFORE_1_HOUR:
                CalendarUtils.copyCalendar(mCalendarEnd,mCalendarTip);
                CalendarUtils.getHourAfterCalendar(mCalendarTip,-1);
                if (tipsMode == MODE_REMIND_IN_APP){
                    mTvRemindWay.setText("截止前1小时APP内提醒");
                }else {
                    mTvRemindWay.setText("截止前1小时短信提醒");
                }
                break;
            case TIME_MODE_REMIND_BEFORE_3_HOUR:
                CalendarUtils.copyCalendar(mCalendarEnd,mCalendarTip);
                CalendarUtils.getHourAfterCalendar(mCalendarTip,-3);
                if (tipsMode == MODE_REMIND_IN_APP){
                    mTvRemindWay.setText("截止前3小时APP内提醒");
                }else {
                    mTvRemindWay.setText("截止前3小时短信提醒");
                }
                break;
            case TIME_MODE_REMIND_BEFORE_1_DAY:
                CalendarUtils.copyCalendar(mCalendarEnd,mCalendarTip);
                CalendarUtils.getDayOfterCalendar(mCalendarTip,-1);
                if (tipsMode == MODE_REMIND_IN_APP){
                    mTvRemindWay.setText("截止前1天APP内提醒");
                }else {
                    mTvRemindWay.setText("截止前1天短信提醒");
                }
                break;
            default:break;
        }
    }
}
