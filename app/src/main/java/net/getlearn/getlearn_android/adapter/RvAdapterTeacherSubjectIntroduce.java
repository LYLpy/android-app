package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
import net.getlearn.getlearn_android.model.bean.SubjectIntroduceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/7------更新------
 * 课程介绍界面，老师简介的adapter
 */

public class RvAdapterTeacherSubjectIntroduce extends RecyclerView.Adapter<RvAdapterTeacherSubjectIntroduce.ViewHolder>{

    private Context mContext;
    private List<SubjectIntroduceModel.Databean.Teacherbean> mDatas;
    private LayoutInflater mInflater;

    public RvAdapterTeacherSubjectIntroduce(Context context, List<SubjectIntroduceModel.Databean.Teacherbean> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_teacher_subject_introduce,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RoundedCorners roundedCorners= new RoundedCorners(30);
        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
                .placeholder(R.drawable.img_teach2)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(mDatas.get(i).getHead_img_url()).apply(options).into(viewHolder.ivIcon);
//        Glide.with(mContext).load(R.drawable.img01).apply(options).into(viewHolder.ivIcon);
        viewHolder.tvName.setText(mDatas.get(i).getTeacher());
        viewHolder.tvTeacherTitle.setText(mDatas.get(i).getPosition());
        viewHolder.tvTeacherIntroduce.setText(mDatas.get(i).getTeacher_introduce());
        RvAdapterSubjectLabel adapter = new RvAdapterSubjectLabel(mContext,mDatas.get(i).getLable());
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext,3);
        viewHolder.rvTeacherLabel.setAdapter(adapter);
        viewHolder.rvTeacherLabel.setLayoutManager(manager);
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
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        TextView tvName;
        TextView tvTeacherTitle;
        TextView tvTeacherIntroduce;
        View itemView;
        RecyclerView rvTeacherLabel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_teacher_name);
            tvTeacherIntroduce = itemView.findViewById(R.id.tv_teacher_introduce);
            tvTeacherTitle = itemView.findViewById(R.id.tv_teacher_title);
            rvTeacherLabel = itemView.findViewById(R.id.rv_teacher_label);
        }
    }


    private OnItemClickListener mItemClickListener;
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public List<SubjectIntroduceModel.Databean.Teacherbean> getDatas() {
        return mDatas;
    }

    public void setDatas(List<SubjectIntroduceModel.Databean.Teacherbean> datas) {
        mDatas = datas;
    }
    public void removeData(){
            mDatas=new ArrayList<>();
        notifyDataSetChanged();
    }
}
