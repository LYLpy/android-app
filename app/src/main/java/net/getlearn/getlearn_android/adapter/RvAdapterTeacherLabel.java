package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/29------更新------
 * 名师主页标签
 */

public class RvAdapterTeacherLabel extends RecyclerView.Adapter<RvAdapterTeacherLabel.Holder>{

    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater inFlater;

    public RvAdapterTeacherLabel(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
        inFlater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inFlater.inflate(R.layout.item_teacher_label,viewGroup,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        if (i%3 == 1){
            ((LinearLayout)holder.itemView).setGravity(Gravity.CENTER);
        }
        if (holder.tvLabel != null){
            holder.tvLabel.setText(mDatas.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView tvLabel;
        View itemView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvLabel = itemView.findViewById(R.id.tv_label);
        }
    }

    public List<String> getDatas() {
        return mDatas;
    }

    public void setDatas(List<String> datas) {
        mDatas = datas;
    }
}
