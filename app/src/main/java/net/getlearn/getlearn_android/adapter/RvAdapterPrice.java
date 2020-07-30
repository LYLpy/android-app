package net.getlearn.getlearn_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.PriceModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * 会员套餐RecyclerView的Adapter
 */

public class RvAdapterPrice extends RecyclerView.Adapter<RvAdapterPrice.Holder>{


    private Context mContext;
    private PriceModel.DataBeanX mDatas;
    private LayoutInflater inflater;
    private int selectedPosition = 0;//当前选中位置
    public RvAdapterPrice(Context context,PriceModel.DataBeanX datas) {
        mContext = context;
        mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RvAdapterPrice.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_price, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterPrice.Holder viewHolder, @SuppressLint("RecyclerView") int i) {
        PriceModel.DataBeanX.DataBean model = mDatas.getData().get(i);
        int discount = model.getDiscount();
        if (discount == 0){
            viewHolder.ic_discount.setVisibility(View.GONE);
            viewHolder.tvPrice.setText(model.getPrice());

        }else if (discount == 1){
            viewHolder.ic_discount.setVisibility(View.VISIBLE);
            viewHolder.tvPrice.setText(model.getDiscount_price());
            viewHolder.tvDiscount.setText(model.getDiscount_text());
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    if (discount== 0) {
                        String price = model.getPrice();
                        itemClickListener.onItemClick(i, price);
                        setSelectedPosition(i);
                    }else if (discount ==1 ){
                        String discount_price = model.getDiscount_price();
                        itemClickListener.onItemClick(i,discount_price);
                        setSelectedPosition(i);
                    }
                }
            }
        });
        if(selectedPosition == i){
            viewHolder.itemView.setSelected(true);
            viewHolder.tvPrice.setTextColor(mContext.getResources().getColor(R.color.black_gold));
            viewHolder.tvPriceMark.setTextColor(mContext.getResources().getColor(R.color.black_gold));
            viewHolder.tvMonths.setTextColor(mContext.getResources().getColor(R.color.black_gold));
        }else {
            viewHolder.itemView.setSelected(false);
            viewHolder.tvPrice.setTextColor(mContext.getResources().getColor(R.color.red_deep));
            viewHolder.tvPriceMark.setTextColor(mContext.getResources().getColor(R.color.red_deep));
            viewHolder.tvMonths.setTextColor(mContext.getResources().getColor(R.color.black));
        }
//        首月特惠
//        if (i == 0){
//            viewHolder.tvFirstMonthLabel.setVisibility(View.VISIBLE);
//        }else {
//            viewHolder.tvFirstMonthLabel.setVisibility(View.GONE);
//        }

        viewHolder.tvMonths.setText(model.getDuration());

        //添加删除线
        viewHolder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.tvOriginalPrice.setText("¥" + model.getVm_price());
    }

    @Override
    public int getItemCount() {
        return mDatas.getData().size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder{
        View itemView;
//        ImageView ic_discount;
        RelativeLayout ic_discount;
        TextView tvPrice;
        TextView tvMonths;
        TextView tvFirstMonthLabel;
        TextView tvOriginalPrice;
        TextView tvPriceMark;
        TextView tvDiscount;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ic_discount = itemView.findViewById(R.id.ic_discount);
            tvFirstMonthLabel = itemView.findViewById(R.id.tv_first_month_label);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvMonths = itemView.findViewById(R.id.tv_months);
            tvOriginalPrice = itemView.findViewById(R.id.tv_original_price);
            tvPriceMark = itemView.findViewById(R.id.tv_price_mark);
            tvDiscount = itemView.findViewById(R.id.tv_discount_text);
        }
    }

    private OnItemClickListener itemClickListener;
    public void setItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position,String price);
    }

    public PriceModel.DataBeanX getDatas() {
        return mDatas;
    }

    public void setDatas(PriceModel.DataBeanX datas) {
        mDatas = datas;
    }
    public void removeData(){
        if (mDatas!=null){
            mDatas.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
