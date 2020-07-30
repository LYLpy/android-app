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
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.MyFeedbackListModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/18------更新------
 * 我的反馈(我的反馈列表，非对话框里面)
 */

public class RvAdapterMyFeedBackList extends RecyclerView.Adapter<RvAdapterMyFeedBackList.ViewHolder>{

    private Context mContext;
    private MyFeedbackListModel.Databean mDatas;
    private LayoutInflater inflater;
    public RvAdapterMyFeedBackList(Context context, MyFeedbackListModel.Databean data) {
        mContext = context;
        mDatas = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_rv_my_feedback,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyFeedbackListModel.Databean.FeedBackListbean databean = mDatas.getFeedBackList().get(i);
//        RoundedCorners roundedCorners= new RoundedCorners(40);
//        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
        RequestOptions options = new RequestOptions().circleCrop()
                .placeholder(R.drawable.img_touxiang)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databean.getUserImage()).apply(options).into(viewHolder.ivIcon);
        //TODO 后续问问是否写死
        viewHolder.tvTitle.setText("我的反馈");
        viewHolder.tvContent.setText(String.valueOf(databean.getMessage()));
        viewHolder.tvTime.setText(databean.getCreateDateTime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.getFeedBackList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        TextView tvTitle;//标题
        TextView tvTime;//时间
        ImageView ivIcon;//图标
        TextView tvContent;//内容
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public MyFeedbackListModel.Databean getDatas() {
        return mDatas;
    }

    public void setDatas(MyFeedbackListModel.Databean datas) {
        mDatas = datas;
    }
    public void removeData(){
        if (mDatas!=null){
            mDatas.setFeedBackList(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
