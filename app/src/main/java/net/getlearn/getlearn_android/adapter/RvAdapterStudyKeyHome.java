package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/7------更新------
 * 首页学习干货
 */

public class RvAdapterStudyKeyHome extends RecyclerView.Adapter<RvAdapterStudyKeyHome.ViewHolder>{

    private Context mContext;
    private StudyKeyModelHome.DatabeanX mData;
    private LayoutInflater mInflater;

    public RvAdapterStudyKeyHome(Context context,StudyKeyModelHome.DatabeanX data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_study_key_home,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(mData.getData().get(i).getIcon()).apply(options).into(viewHolder.iv);
//        Glide.with(mContext).load(R.drawable.img01).apply(options).into(viewHolder.ivIcon);
        viewHolder.tv.setText(mData.getData().get(i).getTitle());
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
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
        TextView tv;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_sign_in);
            iv = itemView.findViewById(R.id.iv);
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
