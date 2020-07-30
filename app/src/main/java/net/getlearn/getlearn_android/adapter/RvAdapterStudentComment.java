package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.StudentCommentModel;
import net.getlearn.getlearn_android.view.emoji.SmileyParser;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/4------更新------
 * 学员评论adapter
 */

public class RvAdapterStudentComment extends RecyclerView.Adapter<RvAdapterStudentComment.ViewHolder>{

    private Context mContext;
    private StudentCommentModel.DatabeanX mData;
    private LayoutInflater inflater;
    private SmileyParser mParser;
    public RvAdapterStudentComment(Context context,StudentCommentModel.DatabeanX data) {
        mContext = context;
        mData = data;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.item_student_comment,viewGroup,false));
    }

    public void setParser(SmileyParser parser) {
        mParser = parser;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        StudentCommentModel.DatabeanX.Databean databeanX = mData.getData().get(i);
//        RoundedCorners roundedCorners= new RoundedCorners(40);
        //Glide加载圆形头像
        RequestOptions options = new RequestOptions().circleCrop()
                .placeholder(R.drawable.img_touxiang)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databeanX.getHeadimgurl()).apply(options).into(viewHolder.ivIcon);
        if(databeanX.getWechatnickname()==null||databeanX.getWechatnickname().equals("")){
            viewHolder.tvNickName.setText(databeanX.getWechatnickname()+"格灵用户");
        }else{
            viewHolder.tvNickName.setText(databeanX.getWechatnickname());
        }

        if (mParser != null){
            viewHolder.tvComment.setText(mParser.addSmileySpans(databeanX.getContent()));
        }else {
            viewHolder.tvComment.setText(databeanX.getContent());
        }
        viewHolder.tvCommentTime.setText(databeanX.getAdd_time());
        if (databeanX.getReplay() == null || databeanX.getReplay().size() == 0){
            viewHolder.rlReply.setVisibility(View.GONE);
        }else {
            viewHolder.rlReply.setVisibility(View.VISIBLE);
            if (mParser != null){
                viewHolder.tvReplyContent.setText(mParser.addSmileySpans(databeanX.getReplay().get(0).getContent()));
            }else {
                viewHolder.tvReplyContent.setText(databeanX.getReplay().get(0).getContent());
            }
            viewHolder.tvReplyTime.setText(databeanX.getReplay().get(0).getAdd_time());
            viewHolder.tvReplyPerson.setText(databeanX.getReplay().get(0).getManager() + ":");
        }
    }

    @Override
    public int getItemCount() {
        return mData.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        TextView tvNickName;
        TextView tvComment;
        TextView tvCommentTime;
        TextView tvReplyContent;
        TextView tvReplyTime;
        TextView tvReplyPerson;
        RelativeLayout rlReply;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvNickName = itemView.findViewById(R.id.tv_nick_name);
            tvComment = itemView.findViewById(R.id.tv_comment);
            tvCommentTime = itemView.findViewById(R.id.tv_comment_time);
            tvReplyContent = itemView.findViewById(R.id.tv_reply_content);
            tvReplyTime = itemView.findViewById(R.id.tv_reply_time);
            tvReplyPerson = itemView.findViewById(R.id.tv_reply_person);
            rlReply = itemView.findViewById(R.id.rl_teacher_reply);
        }
    }

    public StudentCommentModel.DatabeanX getData() {
        return mData;
    }

    public void setData(StudentCommentModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
