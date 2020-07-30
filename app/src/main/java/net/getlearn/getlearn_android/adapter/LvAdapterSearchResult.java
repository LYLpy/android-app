package net.getlearn.getlearn_android.adapter;

import android.content.Context;
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
import net.getlearn.getlearn_android.model.bean.SearchResultModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 */

public class LvAdapterSearchResult extends BaseAdapter {
    private SearchResultModel.DatabeanX mData;
    private Context mContext;

    public LvAdapterSearchResult(SearchResultModel.DatabeanX data, Context mContext) {
        mData = data;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_result, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //得到缓存的布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchResultModel.DatabeanX.Databean databean = mData.getData().get(position);
        String name = databean.getName();
        //设置内容
//        viewHolder.ivIcon.setBackgroundResource(mData.get(mPosition).imageId);
        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options;
        if (name!=null){//非空才做此判断
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
        }else {
            options= new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.loading3)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(viewHolder.iv);
        viewHolder.tvTitle.setText(name);
        viewHolder.tvAmount.setText("课时：" + databean.getOkResCount() + "课");
        String source = RichtextUtil.getBlack(String.valueOf(databean.getClick())) + "人已学";
        Spanned spanned =  Html.fromHtml(source);
        viewHolder.tvViews.setText(spanned);
        return convertView;
    }

    public class ViewHolder {
        public ImageView iv;
        public TextView tvTitle;
        public TextView tvAmount;
        public TextView tvViews;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.iv_icon);
            tvTitle = view.findViewById(R.id.tv_title);
            tvAmount = view.findViewById(R.id.tv_amount);
            tvViews = view.findViewById(R.id.views_search_lv);
        }
    }

    public SearchResultModel.DatabeanX getData() {
        return mData;
    }

    public void setData(SearchResultModel.DatabeanX data) {
        mData = data;
    }
}
