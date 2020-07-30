package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.SubjectListModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/2------更新------
 * 视频播放界面的课程列表
 */

public class RvAdapterSubjectList extends RecyclerView.Adapter<RvAdapterSubjectList.ViewHolder>{


    private Context mContext;
    private LayoutInflater inflater;
    private SubjectListModel.DatabeanX mData;
    public RvAdapterSubjectList(Context context,SubjectListModel.DatabeanX data) {
        mContext = context;
        mData = data;
        this.inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RvAdapterSubjectList.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View contentView;
        RvAdapterSubjectList.ViewHolder holder;
        contentView = inflater.inflate(R.layout.item_subject_list,viewGroup,false);
        holder = new ViewHolder(contentView);
        return holder;
    }

    /**
     * 获取到新的数据之后添加
     * @param newData
     */
    public void setDatas(SubjectListModel.DatabeanX newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterSubjectList.ViewHolder holder, int i) {
        SubjectListModel.DatabeanX.ClassListbean.Databean classInfo = mData.getClass_list().getData().get(i);
        //课程名称
        String subjectName = mData.getGrade_info().getName();
        //当前这节课的名称
        String name = classInfo.getName();
        if (name.contains(subjectName)){
            holder.tvTitle.setText(name.replace(subjectName,""));
        }else {
            holder.tvTitle.setText(name);
        }
        holder.tvFree.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        holder.tvLastTimeWatch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        if (classInfo.getIs_free() == 0){
            holder.tvFree.setVisibility(View.VISIBLE);
            holder.mIvLock.setVisibility(View.GONE);
        }else {
            holder.tvFree.setVisibility(View.GONE);
            holder.mIvLock.setVisibility(View.VISIBLE);
        }
        //0为正常状态，1为上次看到这里
        if (classInfo.getHistory() == 0){
            holder.tvLastTimeWatch.setVisibility(View.GONE);
            //0为免费，1为收费
            if (classInfo.getIs_free() == 0){
                holder.tvFree.setVisibility(View.VISIBLE);
                holder.mIvLock.setVisibility(View.GONE);
            }else {
                holder.tvFree.setVisibility(View.GONE);
                holder.mIvLock.setVisibility(View.VISIBLE);
            }
        } else if (classInfo.getHistory() == 1){
            holder.tvFree.setVisibility(View.GONE);
            holder.mIvLock.setVisibility(View.GONE);
            holder.tvLastTimeWatch.setVisibility(View.VISIBLE);
//            holder.tvLastTimeWatch.setText("上次看到这里");
        }
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null){
                    clickListener.onItemClick(i);
                }
            }
        });

        //vip将锁头隐藏掉
        if(UserManager.getInstance().getIsVip()  && CommonUtils.getCurrentTimeStampInt() < UserManager.getInstance().getVipTime() ){
            //holder.mIvLock.setVisibility(View.GONE);
            holder.mIvLock.setVisibility(View.GONE);

        }else  {
            if (classInfo.getIs_free() == 0){
                holder.mIvLock.setVisibility(View.GONE);
            }else {
                holder.mIvLock.setVisibility(View.VISIBLE);
            }
            //0为正常状态，1为上次看到这里
            if (classInfo.getHistory() == 0){
                holder.tvLastTimeWatch.setVisibility(View.GONE);
                //0为免费，1为收费
                if (classInfo.getIs_free() == 0){
                    holder.tvFree.setVisibility(View.VISIBLE);
                    holder.mIvLock.setVisibility(View.GONE);
                }else {
                    holder.tvFree.setVisibility(View.GONE);
                    holder.mIvLock.setVisibility(View.VISIBLE);
                }
            } else if (classInfo.getHistory() == 1){
                holder.tvFree.setVisibility(View.GONE);
                holder.mIvLock.setVisibility(View.GONE);
                holder.tvLastTimeWatch.setVisibility(View.VISIBLE);
//            holder.tvLastTimeWatch.setText("上次看到这里");
            }

        }
    }

    @Override
    public int getItemCount() {
        try {
            //加上底部
            return  mData.getClass_list().getData().size();
        }catch (Exception e){
            //加上底部
            return  0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;//课程标题
        TextView tvLastTimeWatch;//上次看到
        TextView tvFree;//免费观看
        View mItemView;//课程标题
        ImageView mIvLock;//锁头标志
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLastTimeWatch = itemView.findViewById(R.id.tv_last_time_watch);
            tvFree = itemView.findViewById(R.id.tv_free);
            mIvLock = itemView.findViewById(R.id.iv_lock);
        }
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public SubjectListModel.DatabeanX getData() {
        return mData;
    }

    public void setData(SubjectListModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null&&mData.getClass_list()!=null){
            mData.getClass_list().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
