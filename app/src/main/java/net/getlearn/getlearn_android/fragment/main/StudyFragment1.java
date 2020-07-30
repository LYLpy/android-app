package net.getlearn.getlearn_android.fragment.main;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.StudyPlanActivity2;
import net.getlearn.getlearn_android.activity.VideoPlayActivity;
import net.getlearn.getlearn_android.adapter.GvAdapterCalendar;
import net.getlearn.getlearn_android.adapter.RvAdapterMyStudy;
import net.getlearn.getlearn_android.fragment.BaseLazyFragment;
import net.getlearn.getlearn_android.model.bean.MyStudyModel;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.view.GridViewForScrollView;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/13------更新------
 * 首页学习fragment
 */

public class StudyFragment1 extends BaseLazyFragment implements RvAdapterMyStudy.OnItemClickListener, RvAdapterMyStudy.OnItemLongClickListener {

//    private List<String> mDataDate;
    private List<Calendar> mDataDate;
    private StudyPlanModel.DatabeanX mDataStudyPlan;
    private MyStudyModel.DatabeanX mDataMyStudy;
    private GvAdapterCalendar mGvAdapter;
    private GridViewForScrollView mGv;
    private ImageView mIvStudyPlan;
    private RecyclerView mRv;
    private RvAdapterMyStudy mAdapterMyStudy;
    private SpringView springView;
    private TextView mTvCompletion;
    private ImageView mIvNoData;
    private int page;
    private int limit = 20;
    IOSAlertDialog mDialog;
    private String dayStr;
    private String today;
    private SimpleDateFormat sdf = new SimpleDateFormat("d");
    private int selectPosition;
    @Override
    protected void initData() {
        today = sdf.format(Calendar.getInstance().getTime());
        mDialog = new IOSAlertDialog(getActivity()).builder();
        page = 1;
        mDataMyStudy = null;
        mDataDate = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            // 获取本周的第一天
            int firstDayOfWeek = calendar.getFirstDayOfWeek();
            calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek + i);
//            String day1 = new SimpleDateFormat("yyyyMMd").format(calendar.getHourTime());
            String day = new SimpleDateFormat("d").format(calendar.getTime());
//            LogUtil.e("__week:" + i,week);
//            LogUtil.e("__day:" + i,day);
//                mDataDate.add(day);
                mDataDate.add(calendar);
            dayStr = sdf.format(calendar.getTime());
            if (dayStr.equals(today)){
                selectPosition = i;
            }
        }
//        mTvCompletion.setText("今日任务：0%");
        mGvAdapter = new GvAdapterCalendar(getActivity(), mDataDate);
        mGvAdapter.setSelectPosition(selectPosition);
        mGv.setAdapter(mGvAdapter);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 7){
                    int position = i - 7;
                    String plan_date =  CalendarUtils.getTimeYearMonthDayStr(mDataDate.get(position));
                    Intent intent = new Intent(getActivity(), StudyPlanActivity2.class);
                    intent.putExtra("plan_date",plan_date);
                    startActivity(intent);
                }
            }
        });
        mHttpHelper.getStudyPlan(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                1,20, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
        mHttpHelper.getMyStudyList(this,CommonUtils.getCurrentTimeStampInt(),
                UserManager.getInstance().getToken(),page,limit);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHttpHelper.getStudyPlan(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                1,20, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
    }

    //EventBus接收刷新数据通知
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void refreshData(CommonMessageEvent mMessageEvent) {
//            LogUtil.e("__handleData_getType",mMessageEvent.getType() + "");
        LogUtil.e("__refreshData_getContent",mMessageEvent.getContent() + mMessageEvent.getType());
        if (mMessageEvent.getType() == Constants.REQUEST_GET_MY_STUDY_LIST){
            page = 1;
            mDataMyStudy = null;
            if (mAdapterMyStudy != null){
                mAdapterMyStudy.removeData();
            }
//            mHttpHelper.getStudyPlan(StudyFragment1.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
//                    1,20, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
            mHttpHelper.getMyStudyList(StudyFragment1.this,CommonUtils.getCurrentTimeStampInt(),
                    UserManager.getInstance().getToken(),page,limit);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.e("__SelectSubjcetFragment", "onHiddenChanged:" + hidden + "");
        if (hidden) {//不在最前端界面显示，相当于调用了onPause()

        } else {//重新显示到最前端 ,相当于调用了onResume()

        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initView(View view) {
        mGv = view.findViewById(R.id.gv);
        mRv = view.findViewById(R.id.rv);
        mIvNoData = view.findViewById(R.id.iv_no_data);
        mIvStudyPlan = view.findViewById(R.id.iv_study_plan);
        mTvCompletion = view.findViewById(R.id.tv_completion);
        mIvStudyPlan.setOnClickListener(this);
        mTvCompletion.setOnClickListener(this);
        initSpringView(view);
    }
    private void initSpringView(View view) {
        springView = view.findViewById(R.id.spring_view);
        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mDataMyStudy = null;
                        if (mAdapterMyStudy != null){
                            mAdapterMyStudy.removeData();
                        }
                        mHttpHelper.getStudyPlan(StudyFragment1.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                1,20, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
                        mHttpHelper.getMyStudyList(StudyFragment1.this,CommonUtils.getCurrentTimeStampInt(),
                                UserManager.getInstance().getToken(),page,limit);
                        springView.onFinishFreshAndLoad();
//                        loadData();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getMyStudyList(StudyFragment1.this,CommonUtils.getCurrentTimeStampInt(),
                                UserManager.getInstance().getToken(),page,limit);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_study_plan:
            case R.id.tv_completion:
//                CalendarUtils.getMonthDayStrChinese(Calendar.getContext());
//                CalendarUtils.getDayOfWeekChinese(Calendar.getContext());
//                CalendarUtils.getTime(Calendar.getContext());
//                CalendarUtils.getTimeYearMonthDayStr(Calendar.getContext());
                getActivity().startActivity(new Intent(getActivity(), StudyPlanActivity2.class));
            break;
        }
    }
    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }
    public void onClick() {

    }


    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_STUDY_PLAN:
                StudyPlanModel studyPlanModel = (StudyPlanModel) msg.obj;
                mDataStudyPlan = studyPlanModel.getData();
                setStudyPlanData();
                break;
            case Constants.REQUEST_GET_MY_STUDY_LIST:
                MyStudyModel myStudyModelNew = (MyStudyModel) msg.obj;
                setMyStudyData(myStudyModelNew.getData());
                break;
        }
    }

    private void setStudyPlanData() {
        if (mDataStudyPlan != null && mGvAdapter != null){
            mGvAdapter.setPlanData(mDataStudyPlan.getDate());
            mTvCompletion.setText( "今日任务：" + String.valueOf(mDataStudyPlan.getSchedule()));
        }
    }
    private void setMyStudyData(MyStudyModel.DatabeanX databeanX){
        if (databeanX != null && databeanX.getData()!= null && databeanX.getData().size() > 0){
            page ++;
            mIvNoData.setVisibility(View.GONE);
        }
        //证明是第一次加载
        if (mDataMyStudy == null){
            mDataMyStudy = databeanX;
            mAdapterMyStudy = new RvAdapterMyStudy(getActivity(),mDataMyStudy);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRv.setLayoutManager(manager);
            mRv.setNestedScrollingEnabled(false);
            mRv.setAdapter(mAdapterMyStudy);
            mAdapterMyStudy.setOnItemClickListener(this);
            mAdapterMyStudy.setOnItemLongClickListener(this);
            //之前已经有数据了，将数据加进去，刷新即可
        }else {
            List<MyStudyModel.DatabeanX.Databean> data = mDataMyStudy.getData();
            List<MyStudyModel.DatabeanX.Databean> newData = databeanX.getData();
            if (newData == null || newData.size() == 0 && mDataMyStudy != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapterMyStudy.notifyDataSetChanged();
        }
        if (mDataMyStudy != null && mDataMyStudy.getData() != null && mDataMyStudy.getData().size() > 0){
//            mIvNoData.setVisibility(View.GONE);
        }

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
        intent.putExtra("course_id",mDataMyStudy.getData().get(position).getCourse_id());
        getActivity().startActivity(intent);
    }

    @Override
    public void onGetAllClick() {

    }

    @Override
    public void onItemLongClick(int position) {
//        showDelDia(position);
    }

    private void showDelDia(int position){
        mDialog.setGone().setMsg("是否删除此记录")
                .setNegativeButton("取消", R.color.black, null)
                .setPositiveButton("确定", R.color.action_sheet_blue, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteHistory(position);
                    }
                }).show();
    }

    /**删除历史记录
     * @param position
     */
    private void deleteHistory(int position) {
//        ToastUtil.showToast("删除" + mDataMyStudy.getData().get(position).getClass_name() + "成功");
//        LogUtil.e("__deleteHistory","id:" + mDataMyStudy.getData().get(position).getCourse_id());
//        LogUtil.e("__deleteHistory","name:" + mDataMyStudy.getData().get(position).getClass_name());
    }
}