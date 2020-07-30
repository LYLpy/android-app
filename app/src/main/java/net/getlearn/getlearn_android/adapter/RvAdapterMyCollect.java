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
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.MyColletcModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 我的收藏adapter
 */

public class RvAdapterMyCollect extends RecyclerView.Adapter<RvAdapterMyCollect.ViewHolder>{


    private Context mContext;
    private MyColletcModel.DatabeanX mDatas;
    private LayoutInflater inflater;
    public RvAdapterMyCollect(Context context, MyColletcModel.DatabeanX data) {
        mContext = context;
        mDatas = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_my_collect,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyColletcModel.DatabeanX.AppUserCollectionListbean.Databean databean = mDatas.getAppUserCollectionList().getData().get(i);

//        RoundedCorners roundedCorners= new RoundedCorners(40);
//        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading3)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databean.getIcon()).apply(options).into(viewHolder.ivIcon);
        viewHolder.tvTitle.setText(databean.getName());
        viewHolder.tvAmount.setText(databean.getCountClassHour() + "课时");
        String source = RichtextUtil.getBlack(String.valueOf(databean.getCountUser())) + "人已学";
        Spanned spanned =  Html.fromHtml(source);
        viewHolder.tvViews.setText(spanned);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(i);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemLongClickListener != null){
                    mOnItemLongClickListener.onItemLongClick(i);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.getAppUserCollectionList().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        TextView tvTitle;//描述
        TextView tvAmount;//有效期
        ImageView ivIcon;//图标
        TextView tvViews;//去使用按钮
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvViews = itemView.findViewById(R.id.tv_views);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    private OnItemClickListener mOnItemClickListener;

    /**
     * 点击监听
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }



    public void setOnItemLongClickListener(OnItemLongClickListener onItemClickListener) {
        mOnItemLongClickListener = onItemClickListener;
    }

    private OnItemLongClickListener mOnItemLongClickListener;

    /**
     * 长按监听
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public MyColletcModel.DatabeanX getDatas() {
        return mDatas;
    }

    public void setDatas(MyColletcModel.DatabeanX datas) {
        mDatas = datas;
    }
    public void removeData(){
        if (mDatas!=null&&mDatas.getAppUserCollectionList()!=null){
            mDatas.getAppUserCollectionList().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
