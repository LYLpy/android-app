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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.FamousTeacherModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/10------更新------
 * 名师专栏，名师列表中的课程列表
 */

public class RvAdapterFamousTeacherSubjectList extends RecyclerView.Adapter<RvAdapterFamousTeacherSubjectList.Holder>{

    private LayoutInflater inflater;
    private Context mContext;
    private List<FamousTeacherModel.DatabeanX.Databean.Coursebean> mData;
    private int mPosition;//处于外部RecyclerView的位置
    public RvAdapterFamousTeacherSubjectList(Context context, List<FamousTeacherModel.DatabeanX.Databean.Coursebean> data, int position) {
        mContext = context;
        mData = data;
        mPosition = position;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_famous_teacher_subject_list, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        FamousTeacherModel.DatabeanX.Databean.Coursebean coursebean = mData.get(i);
        String name = String.valueOf(coursebean.getName());
        RoundedCorners roundedCorners = new RoundedCorners(30);
        RequestOptions options;
        if (name.contains("语文")){
            options = new RequestOptions().bitmapTransform(roundedCorners)
//                    .placeholder(R.drawable.img_xuanke_chinese)
                    .placeholder(R.drawable.img_xuanke_chinese)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }else if (name.contains("数学") || name.contains("奥数")){
            options = new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.img_xuanke_math)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
//        }else if("英语".contains(name)){
        }else if(name.contains("英语")){
            options = new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.img_xuanke_english)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }else {
            options= new RequestOptions().bitmapTransform(roundedCorners)
                    .placeholder(R.drawable.loading3)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                    .skipMemoryCache(false);//内存缓存
        }
        Glide.with(mContext).load(coursebean.getIcon()).apply(options).into(holder.iv);
        holder.tvTitle.setText(name);
        holder.tvAmount.setText("课时：" + String.valueOf(coursebean.getOkResCount()) + "课");
        String source = RichtextUtil.getBlack(String.valueOf(coursebean.getClick())) + "人已学" ;
        Spanned spanned =  Html.fromHtml(source);
        holder.tvViews.setText(spanned);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(mPosition,i);
                }
            }
        });
        //分隔线
        if (i == mData.size()-1){
            holder.viewDivider.setVisibility(View.GONE);
        }else {
            holder.viewDivider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        View itemView;
        public ImageView iv;
        public View viewDivider;
        public TextView tvTitle;
        public TextView tvAmount;
        public TextView tvViews;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            viewDivider = itemView.findViewById(R.id.view_divider);
            iv = itemView.findViewById(R.id.iv_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvViews = itemView.findViewById(R.id.views_search_lv);
        }
    }

    private OnItemClickListener itemClickListener;
    public void setClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position, int positionInner);
    }
    public void setPosition(int position){
        mPosition = position;
    }
    public int getPosition(){
        return mPosition;
    }

    public List<FamousTeacherModel.DatabeanX.Databean.Coursebean> getData() {
        return mData;
    }

    public void setData(List<FamousTeacherModel.DatabeanX.Databean.Coursebean> data) {
        mData = data;
    }
    public void removeData(){
            mData = new ArrayList();
        notifyDataSetChanged();
    }
}
