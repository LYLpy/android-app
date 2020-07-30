package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.WeekSignModel;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/12------更新------
 * 挑战中心，本周签到情况
 */

public class RvAdapterSignIn extends RecyclerView.Adapter<RvAdapterSignIn.ViewHolder>{
    private Context mContext;
    private WeekSignModel.Databean mData;
    private LayoutInflater mInflater;

    public RvAdapterSignIn(Context context,WeekSignModel.Databean data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0){
//            return Constants.ITEM_TYPE_HEADER;
//        }else {
//            return Constants.ITEM_TYPE_CONTENT;
//        }
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_sign_in,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WeekSignModel.Databean.Weekbean databean = mData.getWeek().get(i);
            if (i == 0){
                viewHolder.viewStart.setBackgroundColor(mContext.getResources().getColor(R.color.limpid));
                viewHolder.viewEnd.setBackgroundColor(mContext.getResources().getColor(R.color.blue3));
            }else if(i == mData.getWeek().size() -1){
                viewHolder.viewStart.setBackgroundColor(mContext.getResources().getColor(R.color.blue3));
                viewHolder.viewEnd.setBackgroundColor(mContext.getResources().getColor(R.color.limpid));
            }else {
                viewHolder.viewStart.setBackgroundColor(mContext.getResources().getColor(R.color.blue3));
                viewHolder.viewEnd.setBackgroundColor(mContext.getResources().getColor(R.color.blue3));
            }
            if (databean.getParams() == 0){
                viewHolder.tvSignIn.setText("+" +databean.getIntegral());
                viewHolder.tvSignIn.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue3_oval));
            }else {
                viewHolder.tvSignIn.setText("已领\n" + "+" +mData.getCurrentDaySignIntegral());
                viewHolder.tvSignIn.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue5_oval));
            }
            viewHolder.tvSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //有点击监听并且是未签到的情况则回调
                    if (itemClickListener != null && databean.getParams() == 0){
//                        if (databean.getParams() == 0){
                            itemClickListener.onItemClick(i);
//                        }else {
//                            ToastUtil.showToast("已经领取过了哦");
//                        }
                    }
                }
            });
        viewHolder.tvDay.setText(i + 1 + "天");
    }

    @Override
    public int getItemCount() {
        return mData.getWeek().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSignIn;
        TextView tvDay;
        View viewStart;
        View viewEnd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSignIn = itemView.findViewById(R.id.tv_sign_in);
            tvDay = itemView.findViewById(R.id.tv_day);
            viewStart = itemView.findViewById(R.id.view_start);
            viewEnd = itemView.findViewById(R.id.view_end);
        }
    }
    private OnItemClickListener itemClickListener;
    public void setItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public WeekSignModel.Databean getData() {
        return mData;
    }

    public void setData(WeekSignModel.Databean data) {
        mData = data;
    }

}
