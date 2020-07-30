package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
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
import net.getlearn.getlearn_android.model.bean.MoreClassModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * 同步课程
 */

public class RvAdapterMoreClass extends RecyclerView.Adapter<RvAdapterMoreClass.Holder>{

    private LayoutInflater inflater;
    private Context mContext;
    private MoreClassModel.DatabeanX mData;
    public RvAdapterMoreClass(Context context, MoreClassModel.DatabeanX datas) {
        this.mContext = context;
        this.mData = datas;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_recommend_subject_home2, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        MoreClassModel.DatabeanX.Databean databean = mData.getData().get(i);
        String name = databean.getName();
        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options;

        if (name.contains("语文")){
            options = new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.img_xuanke_chinese)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }else if (name.contains("数学") || name.contains("奥数")){
            options = new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.img_xuanke_math)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
//        }else if("英语".contains(name)){
        }else if(name.contains("英语")){
            options = new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.img_xuanke_english)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }else {
            options= new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.loading3)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }
//        Glide.with(mContext).load(mDatas.get(i).getImgUrl()).apply(options).into(viewHolder.ivIcon);
        //设置内容
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(holder.iv);
        holder.tvTitle.setText("        " + name);
//        viewHolder.tvAmount.setText("课时：" + databean.getOkResCount() + "课");
        holder.tvAmount.setText("名师辅导·免费试看·共" + databean.getOkResCount() + "课");
        String source = RichtextUtil.getBlack(String.valueOf(databean.getClick())) + "人已学" ;
        Spanned spanned =  Html.fromHtml(source);
        holder.tvViews.setText(spanned);

        if (name.contains("语文")){
            holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_red_home_subject));
            holder.tvLabel.setText("语文");

        }else if(name.contains("数学")){
            holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_green_home_subject));
            holder.tvLabel.setText("数学");

        }else if(name.contains("英语")){
            holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue_home_subject));
            holder.tvLabel.setText("英语");
        }else {
            holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_red_home_hot));
            holder.tvLabel.setText("热门");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(i);
                }
            }
        });
        holder.tvVipFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.getData().size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        View itemView;
        public ImageView iv;
        public TextView tvTitle;
        public TextView tvAmount;
        public TextView tvViews;
        public TextView tvVipFree;
        public TextView tvLabel;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvViews = itemView.findViewById(R.id.views_search_lv);
            tvVipFree = itemView.findViewById(R.id.tv_vip_free);
            tvLabel = itemView.findViewById(R.id.tv_label);
        }
    }

    private OnItemClickListener itemClickListener;
    public void setClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public MoreClassModel.DatabeanX getData() {
        return mData;
    }

    public void setData(MoreClassModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
