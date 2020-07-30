package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.MyStudyModelNew;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * UI改动之后，新的我的学习adapter
 */

public class RvAdapterMyStudy2 extends RecyclerView.Adapter<RvAdapterMyStudy2.Holder>{

    private LayoutInflater inflater;
    private Context mContext;
    private MyStudyModelNew.DatabeanX mDatas;
    public RvAdapterMyStudy2(Context context, MyStudyModelNew.DatabeanX datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_my_study_new, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        MyStudyModelNew.DatabeanX.Databean databean = mDatas.getData().get(i);
        holder.tvSubjectName.setText(databean.getClass_name());
        String source = databean.getClick() + RichtextUtil.getGray("人已学");
        Spanned spanned =  Html.fromHtml(source);
        holder.tvViews.setText(spanned);
        holder.tvGoToStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemButtonClickListener != null){
                    itemButtonClickListener.onItemButtonClick(i);
                }
            }
        });
        if (databean.getClass_name() == null){
            holder.tvSubjectLabel.setVisibility(View.GONE);
        }else {
            if (databean.getClass_name().contains("语文")){
                holder.tvSubjectLabel.setVisibility(View.VISIBLE);
                holder.tvSubjectLabel.setText("语");
                holder.tvSubjectLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_red_my_study));
            } else if (databean.getClass_name().contains("数学")) {
                holder.tvSubjectLabel.setVisibility(View.VISIBLE);
                holder.tvSubjectLabel.setText("数");
                holder.tvSubjectLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue_my_study));
            }else if (databean.getClass_name().contains("英语")) {
                holder.tvSubjectLabel.setVisibility(View.VISIBLE);
                holder.tvSubjectLabel.setText("英");
                holder.tvSubjectLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_green_my_study));
            }else {
                holder.tvSubjectLabel.setVisibility(View.GONE);
//            holder.tvSubjectLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_green_my_study));
            }
//        holder.tvSubjectName.setText(mDatas.get(i).getSubjectName());
//        holder.tvCompletion.setText(mDatas.get(i).getSubjectName());
//        holder.tvViews.setText(mDatas.get(i).getViews() + "人已学习");
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.getData().size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        View itemView;
        public TextView tvSubjectName;//课程名字
        public TextView tvHasViews;//已完成多少课时
        public TextView tvViews;//课时点击量
        public TextView tvGoToStudy;//去学习
        public TextView tvSubjectLabel;//去学习
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvSubjectName = itemView.findViewById(R.id.tv_subject_name);
            tvHasViews = itemView.findViewById(R.id.tv_has_views);
            tvViews = itemView.findViewById(R.id.tv_views);
            tvGoToStudy = itemView.findViewById(R.id.tv_go_to_study);
            tvSubjectLabel = itemView.findViewById(R.id.tv_subject_label);
        }
    }

    private OnItemButtonClickListener itemButtonClickListener;
    public void setOnItemButtonClickListener(OnItemButtonClickListener listener){
        itemButtonClickListener = listener;
    }
    public interface OnItemButtonClickListener{
        void onItemButtonClick(int position);
    }

    public MyStudyModelNew.DatabeanX getDatas() {
        return mDatas;
    }

    public void setDatas(MyStudyModelNew.DatabeanX datas) {
        mDatas = datas;
    }
    public void removeData(){
        if (mDatas!=null){
            mDatas.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
