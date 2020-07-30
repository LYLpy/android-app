package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/11------更新------
 * 意见反馈，选择图片
 */

public class RvAdapterSelectImg extends RecyclerView.Adapter<RvAdapterSelectImg.ViewHolder>{

    private Context mContext;
    private ArrayList<String> mData;
    private LayoutInflater inflater;
    private int mMaxCount;
    private boolean showDelIv;//是否展示删除按钮
    public RvAdapterSelectImg(Context context,ArrayList<String> data,int maxCount) {
        mContext = context;
        mData = data;
        mMaxCount = maxCount;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_select_img,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        if (mData.size() == i){
            Glide.with(mContext).load(R.drawable.btn_my_customerservice_feedback_uploadpictures).apply(options).into(viewHolder.ivIcon);
            viewHolder.ivDelete.setVisibility(View.GONE);
            LogUtil.e("__adapte_i",i +"");
            LogUtil.e("__adapte_mMaxCount",mMaxCount +"");
            //大于最大图片数则隐藏添加图片iv
            if (i >= mMaxCount){
                LogUtil.e("__adapter_i >= mMaxCount","true");
                viewHolder.itemView.setVisibility(View.GONE);
            }else {
                viewHolder.itemView.setVisibility(View.VISIBLE);
            }
        }else {
            viewHolder.itemView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mData.get(i)).apply(options).into(viewHolder.ivIcon);
            if (showDelIv){
                viewHolder.ivDelete.setVisibility(View.VISIBLE);
            }else {
                viewHolder.ivDelete.setVisibility(View.GONE);
            }
            viewHolder.ivIcon.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mData.size() != 0 && !showDelIv){
                        setShowDelIv(true);
                    }
                    return false;
                }
            });
            //点击删除图标
            viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemDelClickListener != null){
                        mOnItemDelClickListener.onItemDelClick(i);
                    }
                }
            });
        }
        //图片点击
        viewHolder.ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showDelIv){
                    setShowDelIv(false);
                }else {
                    if (mOnItemClickListener != null){
                        if(mData.size() == i){
                            mOnItemClickListener.onAddImgClick();
                        }else {
                            mOnItemClickListener.onItemClick(i);
                        }
                    }
                }
            }
        });
    }

    /**
     * 设置是否展示删除图标
     * @param showDelIv
     */
    public void setShowDelIv(boolean showDelIv) {
        this.showDelIv = showDelIv;
        notifyDataSetChanged();
    }

    /**
     * @return
     */
    public boolean isShowDelIv() {
        return showDelIv;
    }

    @Override
    public int getItemCount() {
            if (mData.size() == mMaxCount){
                return  mData.size();
            }else {
                return  mData.size() + 1;
            }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView ivIcon;
        ImageView ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_img);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }
    //点击监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        /**
         * 点击了某个图片
         * @param position
         */
        void onItemClick(int position);

        void onAddImgClick();
    }

    //点击删除
    public void setOnItemDelClickListener(OnItemDelClickListener onItemDelClickListener) {
        mOnItemDelClickListener = onItemDelClickListener;
    }

    private OnItemDelClickListener mOnItemDelClickListener;
    public interface OnItemDelClickListener {
        void onItemDelClick(int position);
    }

}
