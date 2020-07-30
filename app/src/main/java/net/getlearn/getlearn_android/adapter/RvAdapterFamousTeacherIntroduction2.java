package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.FamousTeacherIntroductionModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/28------更新------
 * 名师主页
 */

public class RvAdapterFamousTeacherIntroduction2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private Context mContext;
    private FamousTeacherIntroductionModel.Databean mData;
    private LayoutInflater inflater;

    public RvAdapterFamousTeacherIntroduction2(Context context, FamousTeacherIntroductionModel.Databean data) {
        mContext = context;
        mData = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (position == 0){
            viewType = Constants.ITEM_TYPE_HEADER;
        }else {
            viewType = Constants.ITEM_TYPE_CONTENT;
        }
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == Constants.ITEM_TYPE_HEADER){
            View itemView = inflater.inflate(R.layout.head_famous_teacher_introduction, viewGroup, false);
            HeadHolder holder = new HeadHolder(itemView);
            return holder;
        }else {
            View itemView = inflater.inflate(R.layout.item_famous_teacher_introduction2, viewGroup, false);
            ItemHolder holder = new ItemHolder(itemView);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = viewHolder.getItemViewType();
        if (viewType == Constants.ITEM_TYPE_HEADER){
            HeadHolder holder = (HeadHolder)viewHolder;
            holder.tvName.setText(mData.getName());
            holder.tvTitle.setText(mData.getTitle());
            holder.tvProfile.setText(mData.getContent());
            RequestOptions options = new RequestOptions().circleCrop()
                    .placeholder(R.drawable.img_touxiang)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
            Glide.with(mContext).load(mData.getImage()).apply(options).into(holder.ivIcon);
            if (mData.getSubject().equals("语文")){
                holder.tvCourseLabel.setVisibility(View.VISIBLE);
                holder.tvCourseLabel.setText("语");
            }else if(mData.getSubject().equals("数学")){
                holder.tvCourseLabel.setVisibility(View.VISIBLE);
                holder.tvCourseLabel.setText("数");
            }else if(mData.getSubject().equals("英语")){
                holder.tvCourseLabel.setVisibility(View.VISIBLE);
                holder.tvCourseLabel.setText("英");
            }else {
                holder.tvCourseLabel.setVisibility(View.GONE);
            }
            if (mData.getTag() != null && mData.getTag().size() > 0){
                holder.rvAdapterLabel = new RvAdapterTeacherLabel(mContext,mData.getTag());
                holder.rvLabel.setNestedScrollingEnabled(false);
                holder.rvLabel.setAdapter(holder.rvAdapterLabel);
                holder.rvLabel.setLayoutManager(new GridLayoutManager(mContext,3));
            }
        }else {
            int position = i - 1;//由于多了头部，课程position需要减1
            FamousTeacherIntroductionModel.Databean.Coursebean coursebean = mData.getCourse().get(position);
            ItemHolder holder = (ItemHolder)viewHolder;
            RoundedCorners roundedCorners= new RoundedCorners(30);
            RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.loading3)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
            Glide.with(mContext).load(coursebean.getIcon()).apply(options).into(holder.iv);
            holder.tvTitle.setText("        " +  coursebean.getName());
//            holder.tvAmount.setText("课时：" + coursebean.getOkResCount() + "课");
            holder.tvAmount.setText("名师辅导·免费试看·共" +String.valueOf(coursebean.getOkResCount()) + "课");
            String source = RichtextUtil.getBlack(String.valueOf(coursebean.getOkResCount())) + "人已学" ;
            Spanned spanned =  Html.fromHtml(source);
            holder.tvViews.setText(spanned);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(position,view);
                    }
                }
            });
            holder.tvVipFree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(position,view);
                    }
                }
            });
            if (String.valueOf(coursebean.getName()).contains("语文")){
                holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_red_home_subject));
                holder.tvLabel.setText("语文");

            }else if(String.valueOf(coursebean.getName()).contains("数学")){
                holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_green_home_subject));
                holder.tvLabel.setText("数学");
            }else if(String.valueOf(coursebean.getName()).contains("英语")){
                holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_blue_home_subject));
                holder.tvLabel.setText("英语");
            }else {
                holder.tvLabel.setBackground(mContext.getResources().getDrawable(R.drawable.bg_red_home_hot));
                holder.tvLabel.setText("热门");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mData.getCourse() == null || mData.getCourse().size() ==0){
            return 1;
        }else {
            return mData.getCourse().size() + 1;
        }

    }


    public class HeadHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView ivIcon;
        TextView tvName;
        TextView tvTitle;//老师职称，比如小学高级教师
        TextView tvCourseLabel;
        TextView tvProfile;
        RecyclerView rvLabel;
        RvAdapterTeacherLabel rvAdapterLabel;
        RecyclerView.LayoutManager mManager;
        public HeadHolder(@NonNull View view) {
            super(view);
            this.view = view;
            tvName = view.findViewById(R.id.tv_name);
            tvTitle = view.findViewById(R.id.tv_title);
            tvProfile = view.findViewById(R.id.tv_profile);
            tvCourseLabel = view.findViewById(R.id.tv_course_label);
            rvLabel = view.findViewById(R.id.rv_label);
            ivIcon = view.findViewById(R.id.iv_icon);
        }
    }
    public class ItemHolder extends RecyclerView.ViewHolder{
        View itemView;
        public ImageView iv;
        public TextView tvTitle;
        public TextView tvAmount;
        public TextView tvViews;
        public TextView tvVipFree;
        public TextView tvLabel;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvViews = itemView.findViewById(R.id.views_search_lv);
            tvVipFree = itemView.findViewById(R.id.tv_vip_free);
            tvLabel = itemView.findViewById(R.id.tv_label);
        }
    }

    private OnItemClickListener itemClickListener;
    public void setItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position, View view);
    }

    public FamousTeacherIntroductionModel.Databean getData() {
        return mData;
    }

    public void setData(FamousTeacherIntroductionModel.Databean data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setCourse(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
