package net.getlearn.getlearn_android.fragment.main;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.activity.StudyPlanActivity2;
import net.getlearn.getlearn_android.activity.VideoPlayActivity;
import net.getlearn.getlearn_android.adapter.GvAdapterCalendar;
import net.getlearn.getlearn_android.adapter.RvAdapterMyStudy2;
import net.getlearn.getlearn_android.fragment.BaseLazyFragment;
import net.getlearn.getlearn_android.model.bean.MyStudyModelNew;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;
import net.getlearn.getlearn_android.utils.CalendarUtils;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.view.GridViewForScrollView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/13------更新------
 * 首页学习fragment
 */

public class StudyFragment2 extends BaseLazyFragment implements RvAdapterMyStudy2.OnItemButtonClickListener {

//    private List<String> mDataDate;
    private List<Calendar> mDataDate;
    private StudyPlanModel.DatabeanX mDataStudyPlan;
    private MyStudyModelNew.DatabeanX mDataMyStudy;
    private GvAdapterCalendar mGvAdapter;
    private GridViewForScrollView mGv;
    private ImageView mIvStudyPlan;
    private RecyclerView mRv;
    private RvAdapterMyStudy2 mAdapterMyStudy;
    private SpringView springView;
    private TextView mTvCompletion;
    private int page;
    private int limit = 20;
    @Override
    protected void initData() {
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
        }
//        mTvCompletion.setText("今日任务：0%");
        mGvAdapter = new GvAdapterCalendar(getActivity(), mDataDate);
        mGv.setAdapter(mGvAdapter);
        mHttpHelper.getStudyPlan(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                1,20, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
        mHttpHelper.getMyStudyListNew(this,CommonUtils.getCurrentTimeStampInt(),
                UserManager.getInstance().getToken(),page,limit);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHttpHelper.getStudyPlan(this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                1,20, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initView(View view) {
        mGv = view.findViewById(R.id.gv);
        mRv = view.findViewById(R.id.rv);
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
                            mAdapterMyStudy.notifyDataSetChanged();
                        }
                        mHttpHelper.getStudyPlan(StudyFragment2.this,CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),
                                1,20, CalendarUtils.getTimeYearMonthDayStr(Calendar.getInstance()));
                        mHttpHelper.getMyStudyListNew(StudyFragment2.this,CommonUtils.getCurrentTimeStampInt(),
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
                        mHttpHelper.getMyStudyListNew(StudyFragment2.this,CommonUtils.getCurrentTimeStampInt(),
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
                MyStudyModelNew myStudyModelNew = (MyStudyModelNew) msg.obj;
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
    private void setMyStudyData(MyStudyModelNew.DatabeanX databeanX){
        if (databeanX != null && databeanX.getData()!= null && databeanX.getData().size() > 0){
            page ++;
        }
        //证明是第一次加载
        if (mDataMyStudy == null){
            mDataMyStudy = databeanX;
            mAdapterMyStudy = new RvAdapterMyStudy2(getActivity(),mDataMyStudy);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRv.setLayoutManager(manager);
            mRv.setNestedScrollingEnabled(false);
            mRv.setAdapter(mAdapterMyStudy);
            mAdapterMyStudy.setOnItemButtonClickListener(this);
            //之前已经有数据了，将数据加进去，刷新即可
        }else {
            List<MyStudyModelNew.DatabeanX.Databean> data = mDataMyStudy.getData();
            List<MyStudyModelNew.DatabeanX.Databean> newData = databeanX.getData();
            if (newData == null || newData.size() == 0 && mDataMyStudy != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mAdapterMyStudy.notifyDataSetChanged();
        }
        if (mDataMyStudy != null && mDataMyStudy.getData() != null && mDataMyStudy.getData().size() > 0){
//            mTvNoData.setVisibility(View.GONE);
        }

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }

    @Override
    public void onItemButtonClick(int position) {
        Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
        intent.putExtra("course_id",mDataMyStudy.getData().get(position).getCourse_id());
        getActivity().startActivity(intent);
    }
}