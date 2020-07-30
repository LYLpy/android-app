package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/29------更新------
 * 课程介绍标签
 */

public class RvAdapterSubjectLabel extends RecyclerView.Adapter<RvAdapterSubjectLabel.Holder>{

    private List<String> mDatas;
    private LayoutInflater inFlater;

    public RvAdapterSubjectLabel(Context context, List<String> datas) {
        mDatas = datas;
        inFlater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inFlater.inflate(R.layout.item_subject_introduce_label,viewGroup,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        if (i == 0){
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(holder.tvLabel.getLayoutParams());
//            params.gravity = Gravity.CENTER_VERTICAL;
//            holder.tvLabel.setLayoutParams(params);
            holder.tvDot.setVisibility(View.GONE);
        }else {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(holder.tvLabel.getLayoutParams());
//            params.gravity = Gravity.LEFT;
//            holder.tvLabel.setLayoutParams(params);
            holder.tvDot.setVisibility(View.VISIBLE);
        }
        if (holder.tvLabel != null){
            holder.tvLabel.setText(mDatas.get(i));
        }
        if (mDatas.get(i) == null || mDatas.get(i).equals("")){
            holder.tvLabel.setVisibility(View.GONE);
        }else {
            holder.tvLabel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView tvLabel;
        TextView tvDot;
        View itemView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvLabel = itemView.findViewById(R.id.tv_label);
            tvDot = itemView.findViewById(R.id.tv_dot);
        }
    }

    public List<String> getDatas() {
        return mDatas;
    }

    public void setDatas(List<String> datas) {
        mDatas = datas;
    }
}
