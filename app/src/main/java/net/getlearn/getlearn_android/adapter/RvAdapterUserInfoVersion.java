package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hpplay.sdk.source.common.global.Constant;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.Event.CommonIntengEvent;
import net.getlearn.getlearn_android.Event.CommonMessageEvent;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.model.bean.GetPersonalInfoModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/19------更新------
 * 个人信息版本条目
 */

public class RvAdapterUserInfoVersion extends RecyclerView.Adapter<RvAdapterUserInfoVersion.ViewHolder>{

    private Context mContext;
    private GetPersonalInfoModel.Databean mData;
    private LayoutInflater inflater;

    public RvAdapterUserInfoVersion(Context context, GetPersonalInfoModel.Databean data) {
        mContext = context;
        mData = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_user_info_version,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GetPersonalInfoModel.Databean.SelectCoursebean databean = mData.getSelect_course().get(i);
        //CommonIntengEvent commonIntengEvent = databean;

//        int VersionId =databean.getVersion().getId();
//        String VersionName = databean.getVersion().getVersion();

        viewHolder.tvVersion.setText(databean.getVersion().getVersion());
        viewHolder.tvSubject.setText(databean.getSubject() + "教材版本");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(i);

                    //mViewholderInterface.vh(databean);
                }

            }
        });
        if (i == mData.getSelect_course().size()-1){
            viewHolder.viewDivider.setVisibility(View.GONE);
        }else {
            viewHolder.viewDivider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return  mData.getSelect_course().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView tvVersion;//版本
        TextView tvSubject;//科目
        View viewDivider;//分隔线
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvVersion = itemView.findViewById(R.id.tv_version);
            tvSubject = itemView.findViewById(R.id.tv_subject);
            viewDivider = itemView.findViewById(R.id.view_divider);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);

    }

/*    *//*
    * 传递点击的版本号的值
    * *//*
    //创建接口
    public interface ViewholderInterface{
        void vh(CommonIntengEvent versionBean);


    }
    //声明接口名
    private ViewholderInterface mViewholderInterface;
    //暴露方法
    public void setViewholderInterface(ViewholderInterface viewholderInterface){
        mViewholderInterface=viewholderInterface;
    }*/

}
