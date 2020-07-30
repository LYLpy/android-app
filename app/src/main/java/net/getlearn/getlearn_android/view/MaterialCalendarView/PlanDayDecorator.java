package net.getlearn.getlearn_android.view.MaterialCalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/10------更新------
 */

public class PlanDayDecorator implements DayViewDecorator {
//    @Override
//    public boolean shouldDecorate(CalendarDay day) {
//        return false;
//    }
//
//    @Override
//    public void decorate(DayViewFacade view) {
//
//    }
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Calendar calendar = Calendar.getInstance();
//        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getHourTime());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Date date = null;
        try {
             date=sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Date date = new Date();
//        String dateStr = TimeUtils.date2String(date, "yyyy-MM-dd");
//        Date parse = TimeUtils.string2Date(dateStr, "yyyy-MM-dd");
        if (day.getDate().equals(date)) {
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
//        view.addSpan(new CircleBackGroundSpan());
//        view.addSpan(new BackgroundColorSpan(Color.parseColor("#fd755c")));
        view.addSpan( new BackgroundPlanDaySpan());
    }
}
