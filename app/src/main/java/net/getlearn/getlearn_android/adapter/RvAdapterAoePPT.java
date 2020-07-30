package net.getlearn.getlearn_android.adapter;


import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.AoePPTModel;

import java.util.ArrayList;

/*
* 专题页面
* */
public class RvAdapterAoePPT extends RecyclerView.Adapter<RvAdapterAoePPT.ViewHolder>  {

    private AoePPTModel.DataBeanX aoeList;
    private Context mContext;

    public RvAdapterAoePPT(Context context,AoePPTModel.DataBeanX aoeList) {
        mContext = context;
        this.aoeList = aoeList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  View view;

        ImageView ivItemImage;
        TextView tvTitle;
        TextView tvTitletow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            ivItemImage = view.findViewById(R.id.iv_item_img);
            tvTitle = view.findViewById(R.id.tv_title);
            tvTitletow  = view.findViewById(R.id.tv_titletow);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.item_aoe_ppt,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AoePPTModel.DataBeanX.DataBean aoePPTModel = aoeList.getData().get(i);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.img_cache2)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(aoePPTModel.getBackground()).apply(options).into(viewHolder.ivItemImage);
        viewHolder.tvTitle.setText(aoePPTModel.getSpecial_title());
        viewHolder.tvTitletow.setText(aoePPTModel.getLevel_title());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemButtonClickListener!=null){
                    mOnItemButtonClickListener.onItemButtonClick(i);
                }
            }
        });


    }
    public void setOnItemButtonClickListener(RvAdapterAoePPT.OnItemButtonClickListener onItemButtonClickListener) {
        mOnItemButtonClickListener = onItemButtonClickListener;
    }

    private RvAdapterAoePPT.OnItemButtonClickListener mOnItemButtonClickListener;
    public interface OnItemButtonClickListener {
        void onItemButtonClick(int position);
    }


    @Override
    public int getItemCount() {
        return aoeList.getData().size();
    }

    public AoePPTModel.DataBeanX getData() {
        return aoeList;
    }

    public void setData(AoePPTModel.DataBeanX data) {
        aoeList = data;
    }
    public void removeData(){
        if (aoeList!=null){
            aoeList.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
