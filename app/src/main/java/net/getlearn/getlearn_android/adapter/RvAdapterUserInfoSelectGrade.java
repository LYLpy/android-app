package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.GradeModel;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/19------更新------
 * 个人信息选择年级dialog
 */

public class RvAdapterUserInfoSelectGrade extends RecyclerView.Adapter<RvAdapterUserInfoSelectGrade.ViewHolder>{

    private Context mContext;
    private List<GradeModel.Databean> mData;
    private LayoutInflater inflater;
    private int selected = -1;//当前选中item
    public RvAdapterUserInfoSelectGrade(Context context,List<GradeModel.Databean> data) {
        mContext = context;
        mData = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_select_grade_user_info,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GradeModel.Databean databean = mData.get(i);
        viewHolder.tvItem.setText(databean.getOption());
        if(selected == i){
            viewHolder.tvItem.setSelected(true);
        }else {
            viewHolder.tvItem.setSelected(false);
        }
        viewHolder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
