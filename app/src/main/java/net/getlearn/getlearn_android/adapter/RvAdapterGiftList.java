package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.GiftListModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 礼品列表
 */

public class RvAdapterGiftList extends RecyclerView.Adapter<RvAdapterGiftList.ViewHolder>{

    private Context mContext;
    private GiftListModel.DatabeanX mData;
    private LayoutInflater inflater;
    public RvAdapterGiftList(Context context,GiftListModel.DatabeanX data) {
        mContext = context;
        mData = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_gift_list,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GiftListModel.DatabeanX.AppGiftListbean.Databean databean = mData.getAppGiftList().getData().get(i);
        RoundedCorners roundedCorners= new RoundedCorners(30);
//        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(viewHolder.ivIcon);
        viewHolder.tvDescribe.setText(databean.getGiftName());
        viewHolder.tvIntegralAmount.setText(databean.getPrice() + "积分");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(i);
                }
            }
        });
    }
    public void removeData(){
        if (mData!=null&&mData.getAppGiftList()!=null){
            mData.getAppGiftList().setData(new ArrayList<>());
        }
       notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return  mData.getAppGiftList().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView tvDescribe;//优惠券描述
        TextView tvIntegralAmount;//兑换需要的积分数量
        ImageView ivIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvDescribe = itemView.findViewById(R.id.tv_describe);
            tvIntegralAmount = itemView.findViewById(R.id.tv_integral_amount);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public GiftListModel.DatabeanX getData() {
        return mData;
    }

    public void setData(GiftListModel.DatabeanX data) {
        mData = data;
    }

}
