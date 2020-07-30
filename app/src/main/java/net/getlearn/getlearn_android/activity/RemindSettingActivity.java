package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.utils.LogUtil;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/14------更新------
 * 到期提醒设置
 */

public class RemindSettingActivity extends BaseActivity {

    @BindView(R.id.rbtn_not_remind)
    RadioButton mRbtnNotRemind;
    @BindView(R.id.rbtn_remind_before_15min)
    RadioButton mRbtnRemindBefore15min;
    @BindView(R.id.rbtn_remind_before_1hour)
    RadioButton mRbtnRemindBefore1hour;
    @BindView(R.id.rbtn_remind_before_3hour)
    RadioButton mRbtnRemindBefore3hour;
    @BindView(R.id.rbtn_remind_before_1day)
    RadioButton mRbtnRemindBefore1Day;

    @BindView(R.id.rbtn_remind_by_app)
    RadioButton mRbtnRemindByApp;
    @BindView(R.id.rbtn_remind_by_msg)
    RadioButton mRbtnRemindByMsg;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.rg)
    RadioGroup mRg;

    //提醒方式：1表示应用内提示 0表示短信提示
    private int mTipsMode;

    //提醒时间方式,如截止前15分钟
    private int mRemindTimeMode;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mTipsMode = intent.getIntExtra("tipsMode",CreateStudyPlanActivity.MODE_REMIND_IN_APP);
        mRemindTimeMode = intent.getIntExtra("remindTimeMode",CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_15_MIN);
        if (mTipsMode == CreateStudyPlanActivity.MODE_REMIND_IN_APP){
            mRbtnRemindByApp.setChecked(true);
        }else if(mTipsMode == CreateStudyPlanActivity.MODE_REMIND_BY_MSG){
            mRbtnRemindByMsg.setChecked(true);
        }
        setRemindTime();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_remind_setting;
    }

    @Override
    protected void initView() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                        case R.id.rbtn_remind_by_app:
                            mTipsMode = CreateStudyPlanActivity.MODE_REMIND_IN_APP;
                            LogUtil.e("__remind_way","APP内提醒");
                            break;
                        case R.id.rbtn_remind_by_msg:
                            mTipsMode = CreateStudyPlanActivity.MODE_REMIND_BY_MSG;
                            LogUtil.e("__remind_way","短信提醒");
                        break;
                }
            }
        });
        mRbtnNotRemind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                   mRbtnRemindBefore15min.setChecked(false);
                    mRbtnRemindBefore1hour.setChecked(false);
                    mRbtnRemindBefore3hour.setChecked(false);
                   mRbtnRemindBefore1Day.setChecked(false);
                    mRemindTimeMode = CreateStudyPlanActivity.TIME_MODE_NOT_REMIND;
                   LogUtil.e("__remind","不提醒");
                }
            }
        });
        mRbtnRemindBefore15min.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mRbtnNotRemind.setChecked(false);
                    mRbtnRemindBefore1hour.setChecked(false);
                    mRbtnRemindBefore3hour.setChecked(false);
                    mRbtnRemindBefore1Day.setChecked(false);
                    mRemindTimeMode = CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_15_MIN;
                    LogUtil.e("__remind","15分钟前提下");
                }
            }
        });
        mRbtnRemindBefore1hour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mRbtnNotRemind.setChecked(false);
                    mRbtnRemindBefore15min.setChecked(false);
                    mRbtnRemindBefore3hour.setChecked(false);
                    mRbtnRemindBefore1Day.setChecked(false);
                    mRemindTimeMode = CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_1_HOUR;
                    LogUtil.e("__remind","1小时前提下");
                }
            }
        });
        mRbtnRemindBefore3hour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mRbtnNotRemind.setChecked(false);
                    mRbtnRemindBefore15min.setChecked(false);
                    mRbtnRemindBefore1hour.setChecked(false);
                    mRbtnRemindBefore1Day.setChecked(false);
                    mRemindTimeMode = CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_3_HOUR;
                    LogUtil.e("__remind","3小时前提下");
                }
            }
        });
        mRbtnRemindBefore1Day.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mRbtnNotRemind.setChecked(false);
                    mRbtnRemindBefore15min.setChecked(false);
                    mRbtnRemindBefore1hour.setChecked(false);
                    mRbtnRemindBefore3hour.setChecked(false);
                    mRemindTimeMode = CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_1_DAY;
                    LogUtil.e("__remind","1天前提下");
                }
            }
        });
        mBtnConfirm.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
    }

    private void setRemindTime() {
        switch (mRemindTimeMode){
            case CreateStudyPlanActivity.TIME_MODE_NOT_REMIND:
                mRbtnNotRemind.setChecked(true);
                break;
            case CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_15_MIN:
                mRbtnRemindBefore15min.setChecked(true);
                break;
            case CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_1_HOUR:
                mRbtnRemindBefore1hour.setChecked(true);
                break;
            case CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_3_HOUR:
                mRbtnRemindBefore3hour.setChecked(true);
                break;
            case CreateStudyPlanActivity.TIME_MODE_REMIND_BEFORE_1_DAY:
                mRbtnRemindBefore1Day.setChecked(true);
                break;
            default:break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                Intent intent = new Intent();
                intent.putExtra("tipsMode", mTipsMode);
                intent.putExtra("remindTimeMode",mRemindTimeMode);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.fl_back:
                finish();
                break;
        }
    }



    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
}
