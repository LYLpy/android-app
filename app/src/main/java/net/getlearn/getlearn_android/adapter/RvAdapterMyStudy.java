package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.MyStudyModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;
import net.getlearn.getlearn_android.view.CircleProgressView;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * 我的学习
 */

public class RvAdapterMyStudy extends RecyclerView.Adapter<RvAdapterMyStudy.Holder>{

    private LayoutInflater inflater;
    private Context mContext;
    private MyStudyModel.DatabeanX mData;
    public RvAdapterMyStudy(Context context,MyStudyModel.DatabeanX datas) {
        this.mContext = context;
        this.mData = datas;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_my_study, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        MyStudyModel.DatabeanX.Databean databean = mData.getData().get(i);
        int progress = databean.getRate();//进度
        if (progress > 100){
            progress = 100;
        }
        if (progress == 100){
            holder.circleProgress.setProgressColor(mContext.getResources().getColor(R.color.blue5));
            holder.tvProgress.setTextColor(mContext.getResources().getColor(R.color.blue5));
        }else {
            holder.circleProgress.setProgressColor(mContext.getResources().getColor(R.color.yellow));
            holder.tvProgress.setTextColor(mContext.getResources().getColor(R.color.yellow));
        }
        //开锁执行动画效果
        holder.circleProgress.startAnimProgress(progress,2);
        //监听进度条进度
        holder.circleProgress.setOnAnimProgressListener(new CircleProgressView.OnAnimProgressListener() {
            @Override
            public void valueUpdate(int progress) {
                holder.tvProgress.setText(progress + "%");
            }
        });
        holder.tvProgress.setText(progress + "%");
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(i);
                }
            }
        });
        holder.llContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (itemLongClickListener != null){
                    itemLongClickListener.onItemLongClick(i);
                }
                return false;
            }
        });
        if (progress == 0){
            holder.tvCompletion.setText("当前进度：暂未开始学习");
        }else {
            holder.tvCompletion.setText("当前进度：" + progress + "%");
        }
        holder.tvSubjectName.setText(databean.getClass_name());
        String source = databean.getClick() + RichtextUtil.getGray("人已学习");
        Spanned spanned =  Html.fromHtml(source);
        holder.tvViews.setText(spanned);
    }

    @Override
    public int getItemCount() {
        return mData.getData().size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        View itemView;
        public CircleProgressView circleProgress;
        public TextView tvSubjectName;
        public TextView tvCompletion;
        public TextView tvViews;
        public View viewDivider;
        public LinearLayout llTitle;
        public LinearLayout llContent;
        public TextView tvProgress;
        public TextView getAllSubject;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            circleProgress = itemView.findViewById(R.id.circle_progress);
            tvSubjectName = itemView.findViewById(R.id.tv_subject_name);
            tvCompletion = itemView.findViewById(R.id.tv_completion);
            tvViews = itemView.findViewById(R.id.tv_views);
            getAllSubject = itemView.findViewById(R.id.get_all_subject);
            llTitle = itemView.findViewById(R.id.ll_title);
            llContent = itemView.findViewById(R.id.ll_content);
            tvProgress = itemView.findViewById(R.id.tv_progress);
            viewDivider = itemView.findViewById(R.id.view_divider);
        }
    }

    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onGetAllClick();
    }

    private OnItemLongClickListener itemLongClickListener;
    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        itemLongClickListener = listener;
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }

    public MyStudyModel.DatabeanX getData() {
        return mData;
    }

    public void setData(MyStudyModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
