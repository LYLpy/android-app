package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.MyCouponModel;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 选择优惠券adapter
 */

public class RvAdapterSelectCoupon extends RecyclerView.Adapter<RvAdapterSelectCoupon.ViewHolder>{


    private Context mContext;
    private MyCouponModel.DataBeanX mDatas;
    private LayoutInflater inflater;
    private int coupon_id = -1;//选中的位置
    private float price;
    private float couponCondition;
    private float coupon_amount;//优惠券的金额
    public RvAdapterSelectCoupon(Context context, MyCouponModel.DataBeanX data,int coupon_id,float payPrice) {
        mContext = context;
        mDatas = data;
        this.coupon_id = coupon_id;
        price=payPrice;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_select_coupon2,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        coupon_amount = mDatas.getAppGiftList().getData().get(i).getCouponCurrency();//优惠券的金额
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

////        优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
//        if (databean.getCouponStatus() == 1){
//            viewHolder.tvGotoUse.setVisibility(View.VISIBLE);
//        }else {
//            viewHolder.tvGotoUse.setVisibility(View.GONE);
//        }
//        viewHolder.rbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//        viewHolder.rbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    setCoupon_id(databean.getId());
//                    if (mOnItemButtonClickListener != null){
//                        mOnItemButtonClickListener.onItemButtonClick(i);
//                    }
//                }
//            }
//        });
//
//        if (databean.getId() == coupon_id){
//            viewHolder.rbtn.setChecked(true);
//        }else {
//            viewHolder.rbtn.setChecked(false);
//        }

        LogUtil.e("__priceprice",price+"");
        couponCondition= databean.getStatus();//满减条件

        if (couponCondition == 1){
            viewHolder.ll_couponCondition.setVisibility(View.GONE);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoupon_id(databean.getId());
                    if (mOnItemButtonClickListener != null){
                        mOnItemButtonClickListener.onItemButtonClick(i);
                    }
                }
            });
        }else if (couponCondition == 0){
            viewHolder.ll_couponCondition.setVisibility(View.VISIBLE);
            viewHolder.tvCouponCondition.setText("满" + databean.getCouponCurrency()+"员可以使用");
            viewHolder.tvCouponCondition.setTextColor(mContext.getResources().getColor(R.color.backg));
            viewHolder.tvDescribe.setTextColor(mContext.getResources().getColor(R.color.backg));
            viewHolder.tvCouponCondition.setTextColor(mContext.getResources().getColor(R.color.backg));
            viewHolder.tvTermOfValidity.setTextColor(mContext.getResources().getColor(R.color.backg));
        }else if (price<=coupon_amount){
            viewHolder.ll_couponCondition.setVisibility(View.VISIBLE);
            viewHolder.tvCouponCondition.setText("未满足使用条件");
            viewHolder.tvDescribe.setTextColor(mContext.getResources().getColor(R.color.backg));
            viewHolder.tvTermOfValidity.setTextColor(mContext.getResources().getColor(R.color.backg));
        }
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    @Override
    public int getItemCount() {
        return mDatas.getAppGiftList().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        LinearLayout ll_couponCondition;//满减
        TextView tvDescribe;//描述
        TextView tvCouponCondition;//金额
        TextView tvTermOfValidity;//有效期
        ImageView ivIcon;//图标
        //RadioButton rbtn;//选择按钮
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvDescribe = itemView.findViewById(R.id.tv_describe);
            tvCouponCondition = itemView.findViewById(R.id.tv_couponCondition);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvTermOfValidity = itemView.findViewById(R.id.tv_term_of_validity);
            ll_couponCondition = itemView.findViewById(R.id.ll_couponCondition);
            // rbtn = itemView.findViewById(R.id.rbtn);
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
