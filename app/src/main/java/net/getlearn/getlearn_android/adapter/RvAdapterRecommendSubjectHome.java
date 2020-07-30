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
import com.hitomi.cslibrary.CrazyShadow;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.RecommendSubjectHomeModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * 首页推荐课程
 */

public class RvAdapterRecommendSubjectHome extends RecyclerView.Adapter<RvAdapterRecommendSubjectHome.Holder>{

    private LayoutInflater inflater;
    private Context mContext;
    private RecommendSubjectHomeModel.DatabeanX mData;
    private CrazyShadow crazyShadow;
    public RvAdapterRecommendSubjectHome(Context context,RecommendSubjectHomeModel.DatabeanX datas) {
        this.mContext = context;
        this.mData = datas;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_recommend_subject_home, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        RecommendSubjectHomeModel.DatabeanX.Databean databean = mData.getData().get(i);
        String name = String.valueOf(databean.getName());
        holder.tvTitle.setText(name);
//        holder.tvTitle.setText(mData.getData().get(i).getName() + mData.getData().get(i).getName());

        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options;

        if (name.contains("语文")){
            options = new RequestOptions().bitmapTransform(roundedCorners)
//                    .placeholder(R.drawable.img_xuanke_chinese)
                    .placeholder(R.drawable.img_xuanke_chinese)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }else if (name.contains("数学")|| name.contains("奥数") ){
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
        Glide.with(mContext).load(mData.getData().get(i).getIcon()).apply(options).into(holder.iv);
//        Glide.with(mContext).load(R.drawable.img01).apply(options).into(holder.ivIcon);
        holder.tvAmount.setText("课时：" + mData.getData().get(i).getOkResCount() + "课");
        String source = RichtextUtil.getBlack(String.valueOf(mData.getData().get(i).getClick())) + "人已学" ;
        Spanned spanned =  Html.fromHtml(source);
        holder.tvViews.setText(spanned);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(i);
                }
            }
        });
//        crazyShadow = new CrazyShadow.Builder()
//                .setContext(mContext)
//                .setDirection(CrazyShadowDirection.ALL)
//                .setShadowRadius(30)
//                .setBaseShadowColor(mContext.getResources().getColor(R.color.bg_gray2))
//                .setImpl(CrazyShadow.IMPL_DRAW)
//                .action(holder.itemView);
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
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvViews = itemView.findViewById(R.id.views_search_lv);
        }
    }

    private OnItemClickListener itemClickListener;
    public void setClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public RecommendSubjectHomeModel.DatabeanX getData() {
        return mData;
    }

    public void setData(RecommendSubjectHomeModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
