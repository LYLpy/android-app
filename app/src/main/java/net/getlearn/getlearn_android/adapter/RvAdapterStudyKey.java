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
import net.getlearn.getlearn_android.model.bean.StudyKeyModelHome;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/7------更新------
 * 首页学习干货
 */

public class RvAdapterStudyKey extends RecyclerView.Adapter<RvAdapterStudyKey.ViewHolder>{

    private Context mContext;
    private StudyKeyModelHome.DatabeanX mData;
    private LayoutInflater mInflater;

    public RvAdapterStudyKey(Context context, StudyKeyModelHome.DatabeanX data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);
//        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_study_key,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
//        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(mData.getData().get(i).getIcon()).apply(options).into(viewHolder.iv);
//        Glide.with(mContext).load(R.drawable.img01).apply(options).into(viewHolder.ivIcon);
        viewHolder.tvTitle.setText(mData.getData().get(i).getTitle());
        String source = RichtextUtil.getBlue(mData.getData().get(i).getRead_volume() + "人") + "已读";
        Spanned spanned =  Html.fromHtml(source);
        viewHolder.tvViews.setText(spanned);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvViews;
        ImageView iv;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title_study_key);
            tvViews = itemView.findViewById(R.id.tv_views_study_key);
            iv = itemView.findViewById(R.id.iv_study_key);
        }
    }

    private OnItemClickListener mItemClickListener;
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public StudyKeyModelHome.DatabeanX getData() {
        return mData;
    }

    public void setData(StudyKeyModelHome.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
