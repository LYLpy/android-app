package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.SelectSubjectListModel;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.ScaleUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/27------更新------
 * 选课课程列表
 */

public class RvAdapterSelectSubject extends RecyclerView.Adapter<RvAdapterSelectSubject.Holder> {
    private LayoutInflater inflater;
    private Context mContext;
    private List<SelectSubjectListModel.Databean> mDatas;
    //创建构造参数
    public RvAdapterSelectSubject(Context context, List<SelectSubjectListModel.Databean> datas){
        this.mDatas = datas;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RvAdapterSelectSubject.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_recommend_course, viewGroup, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterSelectSubject.Holder viewHolder, int position) {
        SelectSubjectListModel.Databean databean = mDatas.get(position);

        LogUtil.e("__databean",databean.getCategName());
        LogUtil.e("__databean上一级获取到的SIZE","getClassify().size()" + databean.getClassify().size());
//        if (viewHolder.mRvAdapter == null) {
        if (databean.getClassify().size() > 0){
            viewHolder.tvTitle.setText(mDatas.get(position).getCategName());
            viewHolder.llMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        mListener.onItemMoreClick(position);
                    }
                }
            });
            viewHolder.itemView.setVisibility(View.VISIBLE);
            //当有数据的时候，把间距设置回来
            viewHolder.itemView.setPadding(0, ScaleUtils.dip2px(20),0,0);
            viewHolder.mRvAdapter = new RvAdapterSelectSubjectListInner(mContext,databean.getClassify(),position);
            viewHolder.rv.setLayoutManager(new LinearLayoutManager(mContext));
            viewHolder.rv.setAdapter(viewHolder.mRvAdapter);
            viewHolder.mRvAdapter.setClickListener(new RvAdapterSelectSubjectListInner.OnItemClickListener() {
                @Override
                public void onItemClick(int position, int positionInner) {
                    if (mListener != null){
                        mListener.onInnerItemClick(position,positionInner);
                    }
                }
            });
        }else {
            //设置padding，不然当数据没有的时候，还是能看到一个空白的间距
            viewHolder.itemView.setPadding(0,ScaleUtils.dip2px(-15),0,0);
            viewHolder.itemView.setVisibility(View.GONE);
        }

//        }else {
//            viewHolder.mRvAdapter.setPosition(position);
//            viewHolder.mRvAdapter.notifyDataSetChanged();
//        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        LinearLayout llMore;
        RecyclerView rv;
        View itemView;
        RvAdapterSelectSubjectListInner mRvAdapter;
        public Holder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            llMore  = itemView.findViewById(R.id.ll_more);
            rv  = itemView.findViewById(R.id.rv);
        }
    }
    private OnItemClickListener mListener;
    public void setItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public interface OnItemClickListener {
        /**
         * 内部recyclerView点击事件
         * @param position 位置
         * @param positionInner 内部recyclerView点击位置
         */
        void onInnerItemClick(int position,int positionInner);

        /**
         * 点击更多
         * @param position
         */
        void onItemMoreClick(int position);
    }

    public List<SelectSubjectListModel.Databean> getDatas() {
        return mDatas;
    }

    public void setDatas(List<SelectSubjectListModel.Databean> datas) {
        mDatas = datas;
    }
    public void removeData(){

            mDatas = new ArrayList<>();
        notifyDataSetChanged();
    }
}
