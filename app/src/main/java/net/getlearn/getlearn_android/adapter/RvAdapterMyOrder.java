package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.MyOrderModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 我的订单界面adapter
 */

public class RvAdapterMyOrder extends RecyclerView.Adapter<RvAdapterMyOrder.ViewHolder>{

    private Context mContext;
    private MyOrderModel.DatabeanX mDatas;
    private LayoutInflater inflater;
    private SparseArray<CountDownTimer> timerArray;
    public RvAdapterMyOrder(Context context,MyOrderModel.DatabeanX datas) {
        mContext = context;
        mDatas = datas;
        inflater = LayoutInflater.from(context);
        timerArray = new SparseArray<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_my_order,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyOrderModel.DatabeanX.Databean databean = mDatas.getData().get(i);
        //Glide加载圆角图片
        RoundedCorners roundedCorners= new RoundedCorners(40);
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(viewHolder.ivIcon);
        viewHolder.tvOrderNum.setText(databean.getOrder_no());
        viewHolder.tvOrderDescribe.setText(databean.getSet_meal_name());
        viewHolder.tvCouponAmount.setText(databean.getCoupon() + "张 | 抵用卷");
        //待支付订单
        if (databean.getOrder_status() == 0){
            viewHolder.tvCreateTime.setVisibility(View.GONE);
            viewHolder.llRealityPay.setVisibility(View.GONE);
            viewHolder.tvPay.setVisibility(View.VISIBLE);
            viewHolder.tvOrderStatus.setText("待付款");
            viewHolder.tvPrice.setText("¥" +databean.getPay_amount());
        }else {//已支付订单
            viewHolder.tvCreateTime.setVisibility(View.VISIBLE);
            viewHolder.llRealityPay.setVisibility(View.VISIBLE);
            viewHolder.tvPay.setVisibility(View.GONE);
            viewHolder.tvRealityPay.setText("¥" + databean.getPay_amount());
            viewHolder.tvPrice.setText("¥" +databean.getOrder_amount());
//            if (databean.getCreate_time().length() >= 11){
//                viewHolder.tvCreateTime.setText(databean.getCreate_time().substring(0,11));
//            }else {
                viewHolder.tvCreateTime.setText(databean.getCreate_time());
//            }

            viewHolder.tvOrderStatus.setText("已付款");
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        return mDatas.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView tvOrderNum;//订单号
        TextView tvOrderDescribe;//订单描述
        TextView tvOrderStatus;//订单描述
        TextView tvCouponAmount;//优惠券数量
        TextView tvPrice;//订单价格
        TextView tvPay;//付款
        TextView tvCreateTime;//付款时间
        TextView tvRealityPay;//实际支付，已付款的订单才有
        ImageView ivIcon;
        LinearLayout llRealityPay;//
        private CountDownTimer countDownTimer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvOrderNum = itemView.findViewById(R.id.tv_order_num);
            tvOrderDescribe = itemView.findViewById(R.id.tv_order_describe);
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            tvCouponAmount = itemView.findViewById(R.id.tv_coupon_amount);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvPay = itemView.findViewById(R.id.tv_pay);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvCreateTime = itemView.findViewById(R.id.tv_create_time);
            tvRealityPay = itemView.findViewById(R.id.tv_reality_pay);
            llRealityPay = itemView.findViewById(R.id.ll_reality_pay);
        }

    }

    public void setOnButtonPayClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public MyOrderModel.DatabeanX getDatas() {
        return mDatas;
    }

    public void setDatas(MyOrderModel.DatabeanX datas) {
        mDatas = datas;
    }
    public void removeData(){
        if (mDatas!=null){
            mDatas.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }


}
