package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

import net.getlearn.getlearn_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/9/17------更新------
 */

public class RvAdapterSelectDevice extends RecyclerView.Adapter<RvAdapterSelectDevice.ViewHolder>{

    private Context mContext;
    private List<LelinkServiceInfo> mData;
    private LayoutInflater mInflater;

    public RvAdapterSelectDevice(Context context, List<LelinkServiceInfo> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater.inflate(R.layout.item_select_device,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LelinkServiceInfo model = mData.get(i);
        viewHolder.tvName.setText(model.getName());
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
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
    private OnItemClickListener mItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public List<LelinkServiceInfo> getData() {
        return mData;
    }

    public void setData(List<LelinkServiceInfo> data) {
        mData = data;
    }
    public void removeData(){
            mData = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setNewData(List<LelinkServiceInfo> data){
        mData = data;
        notifyDataSetChanged();
    }
}
