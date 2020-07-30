package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/9------更新------
 * 学习界面的日历GridView的Adapter；
 */

public class GvAdapterCalendar extends BaseAdapter{

    private List<Calendar> mDataDate;//本周的Calendar集合，从周一开始
    private List<StudyPlanModel.DatabeanX.Datebean> mData;//本周任务数据
    private LayoutInflater layoutInflater;
    private Context mContext;
    private String today;
    private SimpleDateFormat sdf;
    private List<String> mDataDayOfWeek;
    private int selectPosition = 0;
    public GvAdapterCalendar(Context context, List<Calendar> dataDate) {
        mDataDate = dataDate;
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        Calendar calendar = Calendar.getInstance();
        mDataDayOfWeek = new ArrayList<>();
        addWeek(mDataDayOfWeek);
        sdf = new SimpleDateFormat("d");
        today = sdf.format(calendar.getTime());
    }
    @Override
    public int getCount() {
        return mDataDate.size() + 7;
    }

    @Override
    public Object getItem(int i) {
        if (i >= 7) {
            int datePosition = i -7;
            return mDataDate.get(datePosition);
        }else {
            return mDataDayOfWeek.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private void addWeek(List<String> datas) {
        datas.add("日");
        datas.add("一");
        datas.add("二");
        datas.add("三");
        datas.add("四");
        datas.add("五");
        datas.add("六");
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = layoutInflater.inflate(R.layout.item_gridview_calendar, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (i >= 7){
            //减去第一栏之后得到实际calendar位置
            int datePosition = i - 7;
            Calendar calendar = mDataDate.get(datePosition);
            String dayStr = sdf.format(calendar.getTime());
            String yearMonthDayStr = CalendarUtils.getTimeYearMonthDayStr(calendar).replace(" ","");
//            holder.tv.setTextColor(mContext.getResources().getColor(R.color.black_333));
            //本周任务数据
            if (mData != null && mData.size() >= 7){
                StudyPlanModel.DatabeanX.Datebean datebean = mData.get(datePosition);
//                后台返回的日期与日历的日期相符，并且当天有任务，则显示标志；当天 1表有任务 0表示无任务
                LogUtil.e("__Gv_i",i + "");
                LogUtil.e("__Gv_datePosition",datePosition + "");
                LogUtil.e("__Gv_yearMonthDayStr",yearMonthDayStr);
                LogUtil.e("__Gv_datebean.getDay()",datebean.getDay());
                LogUtil.e("__Gv_datebean.getParams()",datebean.getParams() + "");
                LogUtil.e("__Gv_datebean.getParams()",datebean.getParams() + "");
                LogUtil.e("__Gv_dayStr",dayStr + "");
                LogUtil.e("__Gv_toDay", today + "");
                LogUtil.e("__Gv_yearMonthDayStr.equals(datebean.getDay()",String.valueOf(yearMonthDayStr.equals(datebean.getDay())));
                if (yearMonthDayStr.equals(datebean.getDay().replace(" ","")) && datebean.getParams() == 1){
                    holder.tvMark.setVisibility(View.VISIBLE);
                }else {
                    holder.tvMark.setVisibility(View.INVISIBLE);
                }
            }else {
                holder.tvMark.setVisibility(View.INVISIBLE);
            }
            //如果是当天，则显示今
            if (dayStr.equals(today)){
                holder.tv.setText("今");
                holder.tv.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.llContain.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue_oval));
                holder.tvMark.setVisibility(View.GONE);
            }else {
                holder.tv.setText(dayStr);
                holder.tv.setTextColor(mContext.getResources().getColor(R.color.black));
                holder.llContain.setBackground(null);
            }
//            if( datePosition == selectPosition){
//                holder.tv.setTextColor(mContext.getResources().getColor(R.color.white));
//                holder.llContain.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue_oval));
//                holder.tvMark.setVisibility(View.GONE);
//            }else {
//                holder.tv.setTextColor(mContext.getResources().getColor(R.color.black));
//                holder.llContain.setBackground(null);
//            }
        }else {//小于7表示显示今天星期几
            LogUtil.e("__Gv_<7_i",i + "");
            LogUtil.e("__Gv_<7_mDataDayOfWeek", mDataDayOfWeek.get(i));
            holder.tv.setText(mDataDayOfWeek.get(i));
            holder.tv.setTextColor(mContext.getResources().getColor(R.color.bg_gray5));
            holder.tvMark.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    public void setPlanData(List<StudyPlanModel.DatabeanX.Datebean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView tv;
        TextView tvMark;
        LinearLayout llContain;
        View itemView;
        public ViewHolder(View view){
            itemView = view;
            tv = view.findViewById(R.id.tv_text);
            tvMark = view.findViewById(R.id.tv_mark);
            llContain = view.findViewById(R.id.ll_contain);
        }
    }


}
