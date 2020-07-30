package net.getlearn.getlearn_android.activity;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.view.CalendarView.CalendarDay;
import net.getlearn.getlearn_android.view.CalendarView.EventDecorator;
import net.getlearn.getlearn_android.view.CalendarView.MaterialCalendarView;

import java.util.HashMap;
import java.util.Map;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/9------更新------
 */

//public class StudyPlanActivity extends BaseActivity implements OnDateSelectedListener, OnMonthChangedListener, OnDateLongClickListener {
public class StudyPlanActivityTest extends BaseActivity implements MaterialCalendarView.OnMonthChangedListener, MaterialCalendarView.OnDateSelectedListener {


//    @BindView(R.id.back)
//    ImageView mBack;
//    @BindView(R.id.title)
//    TextView mTitle;
//    @BindView(R.id.calendarDateView)
//    CalendarDateView mCalendarDateView;
//    @BindView(R.id.list)
//    ListView mList;
//    @BindView(R.id.activity_main)
//    LinearLayout mActivityMain;
//    private boolean mBoolean = true;
//private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
//        @BindView(R.id.calendarView)
//        MaterialCalendarView calendarView;
//        @BindView(R.id.textView)
//        TextView textView;

    private MaterialCalendarView calendar;
    public Map<String,String> map;
    public EventDecorator decorator;
    private Map<String,String> getMap(){
        Map<String,String> map = new HashMap<>();
        map.put("2016-04-13",1+"");
        map.put("2016-04-14",1+"");
        map.put("2016-04-15",0+"");
        map.put("2016-04-19",0+"");
        return map;
    }
    private Map<String,String> getMap1(String month){
        Map<String,String> map = new HashMap<>();
        map.put("2016-0"+month+"-13", 1+"");
        map.put("2016-0"+month+"-14", 0+"");
        map.put("2016-0"+month+"-12", 1+"");
        map.put("2016-0"+month+"-18", 0+"");
        map.put("2016-0"+month+"-23", 1+"");
        map.put("2016-0"+month+"-21", 0+"");
        map.put("2016-0"+month+"-20", 1+"");
        return map;
    }

    @Override
    protected void initData() {
//        mCalendarDateView.setAdapter(new CaledarAdapter() {
//            @Override
//            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
//                TextView view;
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_calendar, null);
//                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px(48), px(48));
//                    convertView.setLayoutParams(params);
//                }
//                view = convertView.findViewById(R.id.text);
//                view.setText("" + bean.day);
//                if (bean.mothFlag != 0) {
//                    view.setTextColor(0xff9299a1);
//                } else {
//                    view.setTextColor(0xffffffff);
//                }
//                if (mBoolean){
//                    view.setBackground(getResources().getDrawable(R.drawable.bg_blue_oval));
//                    mBoolean = !mBoolean;
//                }else {
//                    view.setBackground(getResources().getDrawable(R.drawable.background_item));
//                    mBoolean = !mBoolean;
//                }
//                return convertView;
//            }
//        });
//        mCalendarDateView.setOnPopupItemClickListener(new CalendarView.OnItemBtnClickListener() {
//            @Override
//            public void onPopupItemClick(View view, int postion, CalendarBean bean) {
//                mTitle.setText(bean.year + "/" + getDisPlayNumber(bean.moth) + "/" + getDisPlayNumber(bean.day));
//            }
//        });
//        int[] data = CalendarUtil.getYMD(new Date());
//        mTitle.setText(data[0] + "/" + data[1] + "/" + data[2]);
//
//        mList.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 100;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(StudyPlanActivity.this).inflate(android.R.layout.simple_list_item_1, null);
//                }
//
//                TextView textView = (TextView) convertView;
//                textView.setText("item" + position);
//
//                return convertView;
//            }
//        });
//        calendarView.setOnDateChangedListener(this);
//        calendarView.setOnDateLongClickListener(this);
//        calendarView.setOnMonthChangedListener(this);
//        Calendar calendar = Calendar.getContext();
////        CalendarDay day = new CalendarDay(calendar.getHourTime().getYear(),calendar.getHourTime().getMonth(),calendar.getHourTime().getDay());
////        CalendarDay day = new CalendarDay(calendar.getHourTime().getYear(),calendar.getHourTime().getMonth(),calendar.getHourTime().getDay());
//        CalendarDay day =CalendarDay.today();
//        calendarView.setSelectedDate(day);//当日选中
//        //Setup initial text
//        textView.setText("No Selection");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_study_plan_test;
    }

    @Override
    protected void initView() {
        ArrayAdapter adapter;
        calendar = findViewById(R.id.calendar);
        calendar.setOnMonthChangedListener(this);
        calendar.setOnDateChangedListener(this);
        decorator = new EventDecorator(getMap());
        calendar.addDecoratorGAC(decorator);
//        calendar.setFirstDayOfWeek(1);
//        LogUtil.e("__",calendar.getFirstDayOfWeek() + "");
    }

    @Override
    public void onClick(View view) {

    }
    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        String str = (date.getMonth()+1)+"";
        print(str);
        decorator.setMap(getMap1(str));
    }

    @Override
    public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {

    }
    private void print(String content){
//        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
        //Log.e("__print",content);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
//    @Override
//    public void onMonthChanged(MaterialCalendarView calendarView, CalendarDay date) {
////        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
//    }
//
//    @Override
//    public void onDateLongClick(@NonNull MaterialCalendarView calendarView, @NonNull CalendarDay date) {
//        final String text = String.format("%s is available", FORMATTER.format(date.getDate()));
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDateSelected(@NonNull MaterialCalendarView calendarView, @NonNull CalendarDay date, boolean selected) {
//        textView.setText(selected ? FORMATTER.format(date.getDate()) : "No Selection");
//    }
}
