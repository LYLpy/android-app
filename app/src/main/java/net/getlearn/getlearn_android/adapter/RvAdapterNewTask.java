package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.NewTaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/14------更新------
 */

public class RvAdapterNewTask extends RecyclerView.Adapter<RvAdapterNewTask.ViewHolder>{

    private Context mContext;
    private List<NewTaskModel> mData;
    private LayoutInflater mInflater;

    public RvAdapterNewTask(Context context, List<NewTaskModel> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater.inflate(R.layout.item_new_task,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewTaskModel model = mData.get(i);
        viewHolder.tvDay.setText(model.getDay());
        viewHolder.tvTime.setText(model.getTime());
        //最后一个是自定义时间，把时间隐藏掉
        if (i == mData.size() -1){
            viewHolder.tvTime.setVisibility(View.GONE);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView iv;
        TextView tvDay;
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
    private OnItemClickListener mItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public List<NewTaskModel> getData() {
        return mData;
    }

    public void setData(List<NewTaskModel> data) {
        mData = data;
    }
    public void removeData(){
            mData = new ArrayList<>();
        notifyDataSetChanged();
    }
}
