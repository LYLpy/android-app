package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.MyFeedbackMsgModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/18------更新------
 * 我的反馈(对话框里面)
 */

public class RvAdapterMyFeedBackMsg extends RecyclerView.Adapter<RvAdapterMyFeedBackMsg.ViewHolder>{

    private Context mContext;
    private MyFeedbackMsgModel.Databean mDatas;
    private LayoutInflater inflater;
    public RvAdapterMyFeedBackMsg(Context context,MyFeedbackMsgModel.Databean data) {
        mContext = context;
        mDatas = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_rv_my_feedback_msg,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyFeedbackMsgModel.Databean.FeedBackListbean databean = mDatas.getFeedBackList().get(i);
        RequestOptions options = new RequestOptions().circleCrop()
                .placeholder(R.drawable.img_touxiang)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        //id与用户id相同表明是自己发送的
        if (databean.getUserId() == UserManager.getInstance().getUserId()){
//        if (databean.getUserId() == UserManager.getInstance().getUserId()){
            viewHolder.llMsgUser.setVisibility(View.GONE);
            viewHolder.llMsgMy.setVisibility(View.VISIBLE);
//            Glide.with(mContext).load("").apply(options).into(viewHolder.ivIconPortaitMy);
            Glide.with(mContext).load(databean.getUserImage()).apply(options).into(viewHolder.ivIconPortaitMy);
            //没有图片，表明是文字消息
            if (databean.getImage() == null || databean.getImage().equals("")){
                viewHolder.tvMsgMy.setText(databean.getMessage());
//                viewHolder.ivMsgMy.setVisibility(View.GONE);
                viewHolder.rlImgMsgMy.setVisibility(View.GONE);
//                viewHolder.tvMsgMy.setVisibility(View.VISIBLE);
                viewHolder.rlTextMsgMy.setVisibility(View.VISIBLE);
            }else {
//                viewHolder.ivMsgMy.setVisibility(View.VISIBLE);
                viewHolder.rlImgMsgMy.setVisibility(View.VISIBLE);
//                viewHolder.tvMsgMy.setVisibility(View.GONE);
                viewHolder.rlTextMsgMy.setVisibility(View.GONE);
//                Glide.with(mContext).load(UserManager.getInstance().getHeadImgUrl()).apply(options).into(viewHolder.ivIconPortaitMy);
                RequestOptions optionsImg = new RequestOptions()
                        .placeholder(R.drawable.loading3)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                        .skipMemoryCache(false);//内存缓存
                Glide.with(mContext).load(databean.getImage()).apply(optionsImg).into(viewHolder.ivMsgMy);
            }
            viewHolder.tvTimeMy.setVisibility(View.VISIBLE);
            viewHolder.tvTimeMy.setText(databean.getCreateDateTime());
        }else {
            //对方发的消息
            viewHolder.llMsgUser.setVisibility(View.VISIBLE);
            viewHolder.llMsgMy.setVisibility(View.GONE);
            Glide.with(mContext).load(databean.getUserImage()).apply(options).into(viewHolder.ivIconPortaitUser);
            //没有图片，表明是文字消息
            if (databean.getImage()== null || databean.getImage().equals("")){
                viewHolder.tvMsgUser.setText(databean.getMessage());
//                viewHolder.ivMsgUser.setVisibility(View.GONE);
                viewHolder.llImgMsgUser.setVisibility(View.GONE);
//                viewHolder.tvMsgMy.setVisibility(View.VISIBLE);
            }else {
//                viewHolder.ivMsgUser.setVisibility(View.VISIBLE);
                viewHolder.llImgMsgUser.setVisibility(View.VISIBLE);
                viewHolder.tvMsgUser.setVisibility(View.GONE);
//                Glide.with(mContext).load(UserManager.getInstance().getHeadImgUrl()).apply(options).into(viewHolder.ivIconPortaitUser);
                RequestOptions optionsImg = new RequestOptions()
                        .placeholder(R.drawable.loading3)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                        .skipMemoryCache(false);//内存缓存
                Glide.with(mContext).load(databean.getImage()).apply(optionsImg).into(viewHolder.ivMsgUser);
            }
            viewHolder.tvTimeUser.setVisibility(View.VISIBLE);
            viewHolder.tvTimeUser.setText(databean.getCreateDateTime());
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.getFeedBackList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        TextView tvTitle;//标题
        TextView tvTimeUser;//对方时间
        TextView tvTimeMy;//我的时间
        ImageView ivIconPortaitUser;//对方头像
        ImageView ivIconPortaitMy;//我的头像
        TextView tvMsgUser;//对方消息
        TextView tvMsgMy;//我的消息
        ImageView ivMsgUser;//对方图片消息
        ImageView ivMsgMy;//我的图片消息
        LinearLayout llMsgUser;//对方消息布局
        LinearLayout llTextMsgUser;//对方文字消息
        LinearLayout llImgMsgUser;//对方图片消息
        LinearLayout llMsgMy;//我的消息布局
        RelativeLayout rlImgMsgMy;//我的图片消息layout
        RelativeLayout rlTextMsgMy;//我的文字消息layout
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivIconPortaitUser = itemView.findViewById(R.id.iv_portrait_user);
            ivIconPortaitMy = itemView.findViewById(R.id.iv_portrait_my);
            tvTimeUser = itemView.findViewById(R.id.tv_time_user);
            tvTimeMy = itemView.findViewById(R.id.tv_time_my);
            tvMsgUser = itemView.findViewById(R.id.tv_msg_user);
            tvMsgMy = itemView.findViewById(R.id.tv_msg_my);
            ivMsgUser = itemView.findViewById(R.id.iv_msg_user);
            ivMsgMy = itemView.findViewById(R.id.iv_msg_my);
            llMsgUser = itemView.findViewById(R.id.ll_msg_user);
            llTextMsgUser = itemView.findViewById(R.id.ll_text_msg_user);
            llImgMsgUser = itemView.findViewById(R.id.ll_img_msg_user);
            llMsgMy = itemView.findViewById(R.id.ll_msg_my);
            rlImgMsgMy = itemView.findViewById(R.id.rl_img_msg_my);
            rlTextMsgMy = itemView.findViewById(R.id.rl_text_msg_my);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //图片点击监听
    public void setOnImgMsgClickListener(OnImgMsgClickListener onImgMsgClickListener) {
        mOnImgMsgClickListener = onImgMsgClickListener;
    }

    private OnImgMsgClickListener mOnImgMsgClickListener;
    public interface OnImgMsgClickListener {
        void onImgMsgClick(int position);
    }

    public MyFeedbackMsgModel.Databean getDatas() {
        return mDatas;
    }

    public void setDatas(MyFeedbackMsgModel.Databean datas) {
        mDatas = datas;
    }
    public void removeData(){
        if (mDatas!=null){
            mDatas.setFeedBackList(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
