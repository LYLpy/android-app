package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * ------${CYP}--------2019/6/21------更新------
 * 同步课程
 */

public class LvAdapterMoreClass extends BaseAdapter {
    private MoreClassModel.DatabeanX mData;
    private Context mContext;

    public LvAdapterMoreClass(MoreClassModel.DatabeanX mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mData.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return mData.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        //判断是否有缓存
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_recommend_subject_home2, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //得到缓存的布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MoreClassModel.DatabeanX.Databean databean = mData.getData().get(position);
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
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(viewHolder.iv);
        viewHolder.tvTitle.setText("        " + name);
//        viewHolder.tvAmount.setText("课时：" + databean.getOkResCount() + "课");
        viewHolder.tvAmount.setText("名师辅导·免费试看·共" + databean.getOkResCount() + "课");
        String source = RichtextUtil.getBlack(String.valueOf(databean.getClick())) + "人已学" ;
        Spanned spanned =  Html.fromHtml(source);
        viewHolder.tvViews.setText(spanned);

        if (name.contains("语文")){
            viewHolder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_red_home_subject));
            viewHolder.tvLabel.setText("语文");

        }else if(name.contains("数学")){
            viewHolder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_green_home_subject));
            viewHolder.tvLabel.setText("数学");

        }else if(name.contains("英语")){
            viewHolder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue_home_subject));
            viewHolder.tvLabel.setText("英语");
        }else {
            viewHolder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_red_home_hot));
            viewHolder.tvLabel.setText("热门");
        }
        return convertView;
    }

    public class ViewHolder {
        View itemView;
        public ImageView iv;
        public TextView tvTitle;
        public TextView tvAmount;
        public TextView tvViews;
        public TextView tvVipFree;
        public TextView tvLabel;
        public ViewHolder(@NonNull View itemView) {
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvViews = itemView.findViewById(R.id.views_search_lv);
            tvVipFree = itemView.findViewById(R.id.tv_vip_free);
            tvLabel = itemView.findViewById(R.id.tv_label);
        }
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
    public MoreClassModel.DatabeanX getData() {
        return mData;
    }

    public void setData(MoreClassModel.DatabeanX data) {
        mData = data;
    }
}
