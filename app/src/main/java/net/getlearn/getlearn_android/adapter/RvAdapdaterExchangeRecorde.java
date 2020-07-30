package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.content.Intent;
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
import net.getlearn.getlearn_android.activity.VipCenterActivity;
import net.getlearn.getlearn_android.model.bean.ExchangeCodeRecordsModel;
import net.getlearn.getlearn_android.model.bean.ExchangeRecordsModel;

import java.util.ArrayList;
import java.util.List;

/*
* 兑换记录adapater
* */
public class RvAdapdaterExchangeRecorde extends RecyclerView.Adapter<RvAdapdaterExchangeRecorde.ViewHolder> {
    private Context mContext;
    private ExchangeCodeRecordsModel.DataBeanX mList ;
    public OnItemClickLitener   mOnItemClickLitener;
    public RvAdapdaterExchangeRecorde(Context context,ExchangeCodeRecordsModel.DataBeanX list){
        this.mContext = context;
        this.mList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        ImageView img_exchange;
        TextView tvExchange_name;
        TextView tvExchange_time;
        TextView tvExchange_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            img_exchange = view.findViewById(R.id.img_exchange);
            tvExchange_name = view.findViewById(R.id.tv_name);
            tvExchange_time = view.findViewById(R.id.tv_time);
            tvExchange_type = view.findViewById(R.id.tv_type);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exchange_reccess,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapdaterExchangeRecorde.ViewHolder viewHolder, int i) {
         ExchangeCodeRecordsModel.DataBeanX.AppExchangeListBean.DataBean exchangeRecords = mList.getAppExchangeList().getData().get(i);

        RequestOptions  options = new RequestOptions()
                .placeholder(R.drawable.img_cache2)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(exchangeRecords.getIcon()).apply(options).into(viewHolder.img_exchange);
        viewHolder.tvExchange_time.setText(exchangeRecords.getUse_time());
        viewHolder.tvExchange_name.setText(exchangeRecords.getName());
        viewHolder.tvExchange_type.setText("立即查看");
        if (exchangeRecords.getType()==1) {
            viewHolder.tvExchange_type.setText("立即查看");
        }else if (exchangeRecords.getType()==0){
            viewHolder.tvExchange_type.setText("立即使用");
        }
        viewHolder.tvExchange_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickLitener.onItemClick(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.getAppExchangeList().getData().size();
    }

  //设置回调接口
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


}
