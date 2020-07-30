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
import net.getlearn.getlearn_android.model.bean.MyCouponModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 我的优惠券adapter
 */

public class RvAdapterCoupon extends RecyclerView.Adapter<RvAdapterCoupon.ViewHolder>{


    private Context mContext;
    private MyCouponModel.DataBeanX mDatas;
    private LayoutInflater inflater;
    public RvAdapterCoupon(Context context,MyCouponModel.DataBeanX data) {
        mContext = context;
        mDatas = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_coupon,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyCouponModel.DataBeanX.AppGiftListBean.DataBean databean = mDatas.getAppGiftList().getData().get(i);
//        RoundedCorners roundedCorners= new RoundedCorners(40);
//        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(viewHolder.ivIcon);
        viewHolder.tvDescribe.setText(databean.getGiftName());
        viewHolder.tvTermOfValidity.setText(databean.getStartCouponDateTime() + "至" + databean.getEndCouponDateTime());
//        优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
        if (databean.getCouponStatus() == 1){
            viewHolder.tvGotoUse.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvGotoUse.setVisibility(View.GONE);
        }
        viewHolder.tvGotoUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemButtonClickListener != null){
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
        TextView tvDescribe;//描述
        TextView tvTermOfValidity;//有效期
        ImageView ivIcon;//图标
        TextView tvGotoUse;//去使用按钮
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvDescribe = itemView.findViewById(R.id.tv_describe);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvTermOfValidity = itemView.findViewById(R.id.tv_term_of_validity);
            tvGotoUse = itemView.findViewById(R.id.btn_go_to_use);
        }
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener onItemButtonClickListener) {
        mOnItemButtonClickListener = onItemButtonClickListener;
    }

    private OnItemButtonClickListener mOnItemButtonClickListener;
    public interface OnItemButtonClickListener {
        void onItemButtonClick(int position);
    }

    public MyCouponModel.DataBeanX getDatas() {
        return mDatas;
    }

    public void setDatas(MyCouponModel.DataBeanX datas) {
        mDatas = datas;
    }
    public void removeData(){
        if (mDatas!=null&&mDatas.getAppGiftList()!=null){
            mDatas.getAppGiftList().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
