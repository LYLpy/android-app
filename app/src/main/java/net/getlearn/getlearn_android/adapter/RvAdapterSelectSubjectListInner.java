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
import net.getlearn.getlearn_android.model.bean.SelectSubjectListModel;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * 首页选课fragment中课程列表内部List
 */

public class RvAdapterSelectSubjectListInner extends RecyclerView.Adapter<RvAdapterSelectSubjectListInner.Holder>{

    private LayoutInflater inflater;
    private Context mContext;
    private List<SelectSubjectListModel.Databean.Classifybean> mDatas;
    private int mPosition;//处于外部RecyclerView的位置
    public RvAdapterSelectSubjectListInner(Context context, List<SelectSubjectListModel.Databean.Classifybean> datas, int position) {

        this.mContext = context;
        this.mDatas = datas;
        mPosition = position;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_select_subject_list_inner, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        SelectSubjectListModel.Databean.Classifybean classifybean = mDatas.get(i);
        String name = classifybean.getName();
        holder.tvTitle.setText("        " + name);
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
        Glide.with(mContext).load(mDatas.get(i).getIcon()).apply(options).into(holder.iv);
//        Glide.with(mContext).load(R.drawable.img01).apply(options).into(holder.ivIcon);
//        holder.tvAmount.setText("课时：" + classifybean.getOkResCount() + "课");
        holder.tvAmount.setText("名师辅导·免费试看·共" + classifybean.getOkResCount() + "课");
        String source = RichtextUtil.getBlack(String.valueOf(classifybean.getClick())) + "人已学" ;
        Spanned spanned =  Html.fromHtml(source);
        holder.tvViews.setText(spanned);
        LogUtil.e("__RvAdapterSelectSubject_课时","课时：" + classifybean.getOkResCount() + "课");
        LogUtil.e("__RvAdapterSelectSubjectList_已学","课时：" + classifybean.getOkResCount() + "课");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(mPosition,i);
                }
            }
        });
        holder.tvVipFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(mPosition,i);
                }
            }
        });
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
    }

    @Override
    public int getItemCount() {
       return mDatas.size();
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
        void onItemClick(int position, int positionInner);
    }
    public void setPosition(int position){
        mPosition = position;
    }
    public int getPosition(){
        return mPosition;
    }

    public List<SelectSubjectListModel.Databean.Classifybean> getDatas() {
        return mDatas;
    }

    public void setDatas(List<SelectSubjectListModel.Databean.Classifybean> datas) {
        mDatas = datas;
    }
    public void removeData(){
            mDatas=new ArrayList<>();
        notifyDataSetChanged();
    }
}
