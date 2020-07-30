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
 * 新的我的学习计划adapter
 */

public class RvAdapterStudyPlanNew extends RecyclerView.Adapter<RvAdapterStudyPlanNew.Holder>{

    private LayoutInflater inflater;
    private Context mContext;
    private StudyPlanModel.DatabeanX mDatas;
    public RvAdapterStudyPlanNew(Context context, StudyPlanModel.DatabeanX data) {
        this.mContext = context;
        this.mDatas = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_study_plan_new, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        StudyPlanModel.DatabeanX.AppStudyPlanListbean.Databean databean = mDatas.getAppStudyPlanList().getData().get(i);
        holder.tvContent.setText(databean.getTaskContent());
        holder.tvEndTime.setText("截止：" + databean.getCloseDateTime());
//        是否已完成 1表示未完成 0表示已完成
        if (databean.getIsFinish() == 1){
            holder.tvIsCompletion.setSelected(false);
            holder.tvIsCompletion.setText("未完成");
        }else {
            holder.tvIsCompletion.setSelected(true);
            holder.tvIsCompletion.setText("已完成");
        }
        holder.tvIsCompletion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemBtnClickListener != null){
                    itemBtnClickListener.onItemBtnClick(i);
                }
            }
        });
    }

    public StudyPlanModel.DatabeanX getDatas() {
        return mDatas;
    }

    public void setDatas(StudyPlanModel.DatabeanX datas) {
        mDatas = datas;
    }

    @Override
    public int getItemCount() {
        return mDatas.getAppStudyPlanList().getData().size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        View itemView;
        public TextView tvContent;//计划内容
        public TextView tvEndTime;//截止时间
        public TextView tvIsCompletion;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvContent = itemView.findViewById(R.id.tv_content);
            tvEndTime = itemView.findViewById(R.id.tv_end_time);
            tvIsCompletion = itemView.findViewById(R.id.tv_is_complete);
        }
    }

    private OnItemBtnClickListener itemBtnClickListener;
    public void setClickListener(OnItemBtnClickListener listener){
        itemBtnClickListener = listener;
    }
    public interface OnItemBtnClickListener {
        void onItemBtnClick(int position);
    }
    public void removeData(){
        if (mDatas!=null&&mDatas.getAppStudyPlanList()!=null){
            mDatas.getAppStudyPlanList().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
