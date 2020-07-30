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
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.MyGiftListModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/17------更新------
 * 我的礼品adapter
 */

public class RvAdapterMyGiftList extends RecyclerView.Adapter<RvAdapterMyGiftList.ViewHolder>{


    private Context mContext;
    private MyGiftListModel.DataBeanX mDatas;
    private LayoutInflater inflater;
    public RvAdapterMyGiftList(Context context, MyGiftListModel.DataBeanX data) {
        mContext = context;
        mDatas = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_my_gift,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyGiftListModel.DataBeanX.AppGiftListBean.DataBean databean = mDatas.getAppGiftList().getData().get(i);
//        RoundedCorners roundedCorners= new RoundedCorners(40);
//        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(viewHolder.ivIcon);
        viewHolder.tvExchangeTime.setText(databean.getGetDateTime());
        viewHolder.tvTitle.setText(databean.getGiftName());
        viewHolder.tvTime.setText(databean.getStartCouponDateTime() + "至" + databean.getEndCouponDateTime());
        //TODO  后续确定一下是否写死
        viewHolder.tvGiftCount.setText("1张 | 抵用卷");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemButtonClickListener!=null){
                    mOnItemButtonClickListener.onItemButtonClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.getAppGiftList().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        TextView tvExchangeTime;//兑换时间
        TextView tvTitle;//兑换时间
        TextView tvTime;//有效期
        ImageView ivIcon;//图标
        TextView tvGiftCount;//优惠券数量
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvExchangeTime = itemView.findViewById(R.id.tv_exchange_time);
            tvTitle = itemView.findViewById(R.id.tv_gift_title);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvGiftCount = itemView.findViewById(R.id.tv_gift_count);
        }
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener onItemButtonClickListener) {
        mOnItemButtonClickListener = onItemButtonClickListener;
    }

    private OnItemButtonClickListener mOnItemButtonClickListener;
    public interface OnItemButtonClickListener {
        void onItemButtonClick(int position);
    }

    public MyGiftListModel.DataBeanX getDatas() {
        return mDatas;
    }

    public void setDatas(MyGiftListModel.DataBeanX datas) {
        mDatas = datas;
    }

    public void removeData(){
        if (mDatas!=null&&mDatas.getAppGiftList()!=null){
            mDatas.getAppGiftList().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
