package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.FamousTeacherModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 */

public class RvAdapterFamousTeacher extends RecyclerView.Adapter<RvAdapterFamousTeacher.Holder>{


    private Context mContext;
    private FamousTeacherModel.DatabeanX mData;
    private LayoutInflater inflater;
    public RvAdapterFamousTeacher(Context context,FamousTeacherModel.DatabeanX data) {
        mContext = context;
        mData = data;
        inflater = LayoutInflater.from(context);
//        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RvAdapterFamousTeacher.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_famous_teacher, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterFamousTeacher.Holder viewHolder, int i) {
        FamousTeacherModel.DatabeanX.Databean databean = mData.getData().get(i);
        RequestOptions options = new RequestOptions().circleCrop()
                .placeholder(R.drawable.img_touxiang)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
        Glide.with(mContext).load(databean.getImage()).apply(options).into(viewHolder.ivIcon);
        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(i,view);
                }
            }
        };
        viewHolder.ivIcon.setOnClickListener(mClickListener);
        viewHolder.tvName.setOnClickListener(mClickListener);
        viewHolder.tvProfile.setOnClickListener(mClickListener);
        viewHolder.llMore.setOnClickListener(mClickListener);

        viewHolder.tvName.setText(databean.getName());
        viewHolder.tvTitle.setText(databean.getTitle());
        viewHolder.tvProfile.setText(databean.getContent());

        if (databean.getSubject().equals("语文")){
            viewHolder.tvCourseLabel.setVisibility(View.VISIBLE);
            viewHolder.tvCourseLabel.setText("语");
        }else if(databean.getSubject().equals("数学")){
            viewHolder.tvCourseLabel.setVisibility(View.VISIBLE);
            viewHolder.tvCourseLabel.setText("数");
        }else if(databean.getSubject().equals("英语")){
            viewHolder.tvCourseLabel.setVisibility(View.VISIBLE);
            viewHolder.tvCourseLabel.setText("英");
        }else {
            viewHolder.tvCourseLabel.setVisibility(View.GONE);
        }
        if (databean.getTag() != null && databean.getTag().size() > 0){
            viewHolder.rvAdapterLabel = new RvAdapterTeacherLabel(mContext,databean.getTag());
            viewHolder.rvLabel.setNestedScrollingEnabled(false);
            viewHolder.rvLabel.setAdapter(viewHolder.rvAdapterLabel);
            viewHolder.rvLabel.setLayoutManager(new GridLayoutManager(mContext,3));
        }
        if (databean.getCourse() != null && databean.getCourse().size() > 0){
            viewHolder.rvAdapterSubject = new RvAdapterFamousTeacherSubjectList2(mContext,databean.getCourse(),i);
            viewHolder.rvSubjects.setNestedScrollingEnabled(false);
            viewHolder.rvSubjects.setAdapter(viewHolder.rvAdapterSubject);
            viewHolder.rvSubjects.setLayoutManager(new LinearLayoutManager(mContext));
            viewHolder.rvAdapterSubject.setClickListener(new RvAdapterFamousTeacherSubjectList2.OnItemClickListener() {
                @Override
                public void onItemClick(int position, int positionInner) {
                    if (itemClickListener != null){
                        itemClickListener.onChildItemClick(position,positionInner);
                    }
                }
            });
        }
    }

//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    public void cleanData(){
        mData.setData(new ArrayList<>());
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mData.getData().size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        View itemView;
        LinearLayout llMore;
        ImageView ivIcon;
        TextView tvName;
        TextView tvTitle;
        TextView tvCourseLabel;
        TextView tvProfile;
        RecyclerView rvLabel;
        RecyclerView rvSubjects;
        RvAdapterFamousTeacherSubjectList2 rvAdapterSubject;
        RvAdapterTeacherLabel rvAdapterLabel;
        RecyclerView.LayoutManager mManager;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = itemView.findViewById(R.id.tv_name);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvProfile = itemView.findViewById(R.id.tv_profile);
            tvCourseLabel = itemView.findViewById(R.id.tv_course_label);
            rvLabel = itemView.findViewById(R.id.rv_label);
            rvSubjects = itemView.findViewById(R.id.rv_subjects);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            llMore = itemView.findViewById(R.id.ll_more);
        }
    }

    private OnItemClickListener itemClickListener;
    public void setItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onChildItemClick(int position,int positionChild);
        void onItemClick(int position,View view);
    }

    public FamousTeacherModel.DatabeanX getData() {
        return mData;
    }

    public void setData(FamousTeacherModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
