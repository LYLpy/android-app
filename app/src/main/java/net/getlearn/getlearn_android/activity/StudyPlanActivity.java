package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.view.MaterialCalendarView.CurrentDayDecorator;
import net.getlearn.getlearn_android.view.MaterialCalendarView.PlanDayDecorator;

import java.util.Calendar;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/9------更新------
 * 旧的学习计划界面，留着参考
 */

public class StudyPlanActivity extends BaseActivity implements OnDateSelectedListener, OnMonthChangedListener {

        @BindView(R.id.calendarView)
        MaterialCalendarView calendarView;
        @BindView(R.id.rv_study_plan)
        RecyclerView mRv;
        @BindView(R.id.tv_do_plan)
        TextView mTvDoPlan;
        @BindView(R.id.fl_back)
        FrameLayout mFlBack;
        private StudyPlanModel.DatabeanX mData;
        private boolean isWeekMode = false;
        private CalendarDay day;//选中的日期
        private int page;
        private int per_page;
        private Calendar mCalendar;
        private String current_date;
    @Override
    protected void initData() {
        page = 1;
        per_page = 20;
        mCalendar = Calendar.getInstance();
        calendarView.setOnDateChangedListener(this);
//        calendarView.setOnDateLongClickListener(this);
        calendarView.setOnMonthChangedListener(this);
        calendarView.setWeekDayLabels(new String[]{"日","一", "二", "三", "四", "五", "六"});
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
//                .setCalendarDisplayMode(CalendarMode.WEEKS)
                .commit();
        calendarView.setSelectionColor(getResources().getColor(R.color.bg_gray3));
        calendarView.addDecorators(new CurrentDayDecorator(),new PlanDayDecorator());
        day = new CalendarDay();
        current_date = CalendarUtils.getTimeYearMonthDayStr(mCalendar);
        mHttpHelper.getStudyPlan(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                page,per_page,current_date);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_study_plan;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        mTvDoPlan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_do_plan:
                startActivity(new Intent(this,CreateStudyPlanActivity.class));
                if(day == null){
//                    ToastUtil.showToast("请先选择日期");
                }
                break;

        }
//        if (isWeekMode){
//            isWeekMode = !isWeekMode;
//            calendarView.state().edit()
//                    .setCalendarDisplayMode(CalendarMode.MONTHS)
//                    .commit();
//        }else {
//            isWeekMode = !isWeekMode;
//            calendarView.state().edit()
//                    .setCalendarDisplayMode(CalendarMode.WEEKS)
//                    .commit();
//        }
    }
    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }


    @Override
    public void onDateSelected(@NonNull com.prolificinteractive.materialcalendarview.MaterialCalendarView widget, @NonNull com.prolificinteractive.materialcalendarview.CalendarDay date, boolean selected) {
        Calendar cd = date.getCalendar();
        int YYYY = cd.get(Calendar.YEAR) ;
        int MM = cd.get(Calendar.MONTH)+1;
        int DD = cd.get(Calendar.DATE);
        int HH = cd.get(Calendar.HOUR);
        int NN = cd.get(Calendar.MINUTE);
        int SS = cd.get(Calendar.SECOND);
        int MI = cd.get(Calendar.MILLISECOND);
        LogUtil.e("__date_YYYY",YYYY + "");
        LogUtil.e("__date_MM",MM + "");
        LogUtil.e("__date_DD",DD + "");
        LogUtil.e("__date_HH",HH + "");
        LogUtil.e("__date_NN",NN + "");
        LogUtil.e("__date_SS",SS + "");
        LogUtil.e("__date_MI",MI + "");
        LogUtil.e("__date_getYear",date.getYear() + "");
        LogUtil.e("__date_getMonth + 1",date.getMonth() + 1 + "");
        LogUtil.e("__date_getDay",date.getDay() + "");
    }

    @Override
    public void onMonthChanged(com.prolificinteractive.materialcalendarview.MaterialCalendarView widget, com.prolificinteractive.materialcalendarview.CalendarDay date) {

    }



    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_STUDY_PLAN:
                StudyPlanModel studyPlanModel = (StudyPlanModel) msg.obj;
                StudyPlanModel.DatabeanX databean = studyPlanModel.getData();
                LogUtil.e("__onHttpSuccess","请求成功");
                setData(databean);
                break;

        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case Constants.REQUEST_GET_STUDY_PLAN:
                LogUtil.e("__onHttpError","失败");
//                setData();
                break;

        }
    }
    private void setData(StudyPlanModel.DatabeanX databean){

    }
}
