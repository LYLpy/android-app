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
import net.getlearn.getlearn_android.model.bean.FamousTeacherModelHome;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/7------更新------
 * 首页名师专栏
 */

public class RvAdapterFamousTeacherHome2 extends RecyclerView.Adapter<RvAdapterFamousTeacherHome2.ViewHolder>{

    private Context mContext;
    private FamousTeacherModelHome.DatabeanX mData;
    private LayoutInflater mInflater;

    public RvAdapterFamousTeacherHome2(Context context, FamousTeacherModelHome.DatabeanX data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_famous_teacher_home2,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FamousTeacherModelHome.DatabeanX.Databean databean = mData.getData().get(i);
//        RoundedCorners roundedCorners= new RoundedCorners(30);
//        RequestOptions options = new RequestOptions().bitmapTransform(roundedCorners)
        RequestOptions options = new RequestOptions().circleCrop()
                .placeholder(R.drawable.img_touxiang)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
                .skipMemoryCache(false);//内存缓存
//        Glide.with(mContext).load(mDatas.get(i).getImgUrl()).apply(options).into(viewHolder.ivIcon);
        Glide.with(mContext).load(databean.getHead()).apply(options).into(viewHolder.iv);
        viewHolder.tvName.setText(databean.getName());
        viewHolder.tvTitle.setText(databean.getPosition());
        if (databean.getHonor() == null || databean.getHonor().equals("")){
            viewHolder.tvHonour.setVisibility(View.GONE);
        }else {
            viewHolder.tvHonour.setVisibility(View.VISIBLE);
            viewHolder.tvHonour.setText(databean.getHonor());
        }
        viewHolder.tvGrade.setText(databean.getGrade());
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
        return mData.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvName;
        TextView tvHonour;
        TextView tvGrade;
        TextView tvTitle;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.tv_name);
            tvHonour = itemView.findViewById(R.id.tv_honour);
            tvGrade = itemView.findViewById(R.id.tv_grade);
            tvTitle = itemView.findViewById(R.id.tv_teacher_title);
        }
    }


    private OnItemClickListener mItemClickListener;
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public FamousTeacherModelHome.DatabeanX getData() {
        return mData;
    }

    public void setData(FamousTeacherModelHome.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null){
            mData.setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
