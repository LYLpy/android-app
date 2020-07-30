package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.StudyPlanModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/3------更新------
 * 学习计划条目adapter,写了一半，后面ui改了
 */

public class RvAdapterStudyPlan extends RecyclerView.Adapter<RvAdapterStudyPlan.ViewHolder>{

    private LayoutInflater mInflater;
    private StudyPlanModel.DatabeanX mData;
    private Context mContext;
    public RvAdapterStudyPlan(StudyPlanModel.DatabeanX data,Context context) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater.inflate(R.layout.item_study_plan,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean databean = mData.getAppStudyPlanList().getData().get(i);
        //判断是否是最后一栏，设置背景图片
        if (i == mData.getAppStudyPlanList().getData().size() -1){
            viewHolder.itemView.setBackgroundResource(R.drawable.bg_white_corner_bottom_5);
            viewHolder.viewDivider.setVisibility(View.GONE);
        }else {
            viewHolder.itemView.setBackgroundResource(R.drawable.bg_white_corner_top_5);
            viewHolder.viewDivider.setVisibility(View.VISIBLE);
        }
//        是否已完成 1表示未完成 0表示已完成
        if (databean.getIsFinish() == 1){
            viewHolder.tvIsComplete.setText("未完成");
        }else {
            viewHolder.tvIsComplete.setText("已完成");
        }
    }

    @Override
    public int getItemCount() {
        return mData.getAppStudyPlanList().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvPlanContent;
        public TextView tvIsComplete;
        public TextView tvEndTime;
        public View itemView;
        public View viewDivider;//分隔线

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvPlanContent = itemView.findViewById(R.id.tv_plan_content);
            tvIsComplete = itemView.findViewById(R.id.tv_is_complete);
            tvEndTime = itemView.findViewById(R.id.tv_end_time);
            viewDivider = itemView.findViewById(R.id.view_divider);
        }
    }


    private setItemClickListener mItemClickListener;

    public void setItemClickListener(setItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface setItemClickListener{
        void onItemClick(int position);
    }

    public StudyPlanModel.DatabeanX getData() {
        return mData;
    }

    public void setData(StudyPlanModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null&&mData.getAppStudyPlanList()!=null){
            mData.getAppStudyPlanList().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
