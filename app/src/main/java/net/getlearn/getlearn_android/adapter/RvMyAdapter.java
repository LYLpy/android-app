package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.GradeModel;

import java.util.List;

import static net.getlearn.getlearn_android.Constants.ITEM_TYPE_BOTTOM;
import static net.getlearn.getlearn_android.Constants.ITEM_TYPE_CONTENT;
import static net.getlearn.getlearn_android.Constants.ITEM_TYPE_HEADER;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/19------更新------
 * 选择年级activity的adapter
 */

public class RvMyAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener{

    private LayoutInflater inflater;
    private Context mContext;
    private List<GradeModel.Databean> mDatas;
    private RecyclerView mRecyclerView;//用来计算Child位置

    private int selected = -1;//当前选中item
    private OnItemClickListener onItemClickListener;

    public int getHeaderCount() {
        return mHeaderCount;
    }

    public void setSelection(int position){
        this.selected = position;
        notifyDataSetChanged();
    }
    public void setHeaderCount(int headerCount) {
        mHeaderCount = headerCount;
    }

    public int getBottomCount() {
        return mBottomCount;
    }

    public void setBottomCount(int bottomCount) {
        mBottomCount = bottomCount;
    }

    private int mHeaderCount = 0;// 头部的数量
    private int mBottomCount = 0;// 底部的数量

    //创建构造参数
    public RvMyAdapter(Context context , List<GradeModel.Databean> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    //创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View viewStart = inflater.inflate(R.layout.item_select_grade , parent , false);
        View itemView = null;
        switch (viewType) {
            case ITEM_TYPE_HEADER:
                itemView = inflater.inflate(R.layout.head_select_grade, parent, false);
                break;
            case ITEM_TYPE_CONTENT:
                itemView = inflater.inflate(R.layout.item_select_grade, parent, false);
                break;
            //设置监听
        }
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        //导入itemView，为itemView设置点击事件
        itemView.setOnClickListener(this);
        return viewHolder;
    }
    /**
     * 适配器绑定到RecyclerView 的时候，回将绑定适配器的RecyclerView 传递过来
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }
    // 中间内容长度
    public int getContentItemCount(){
        return mDatas.size();
    }

    // 判断当前item是否是头部（根据position来判断）
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    // 判断当前item是否是底部
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }

    //绑定ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        ViewGroup.LayoutParams layoutParams = holder.viewStart.getLayoutParams();
//        layoutParams.height=mDatas.get(position).getHeight();
//        holder.viewStart.setLayoutParams(layoutParams);
        int i = position - mHeaderCount;
        if (position >= mHeaderCount){
                //为textview 赋值
                holder.tv.setText(mDatas.get(i).getOption());
                if(selected == i){
                    holder.tv.setSelected(true);
                    holder.tv.setTextColor(mContext.getResources().getColor(R.color.white));
                } else {
                    holder.tv.setTextColor(mContext.getResources().getColor(R.color.black));
                    holder.tv.setSelected(false);
                }
            }
    }


    @Override
    public int getItemCount() {
        //Log.i("TAG", "mDatas "+mDatas);
        return mDatas.size() + mHeaderCount + mBottomCount;

    }

    //新增item
    public void addData(int pos){
        GradeModel.Databean model = new GradeModel.Databean();
        model.setId(0);
        model.setOption("");
        mDatas.add(model);
        notifyItemInserted(pos);
    }

    //移除item
    public void deleateData(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }
    //会在onCreateViewHolder中获取到ViewType
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            // 头部View
            return ITEM_TYPE_HEADER;
        }else if (isBottomView(position)) {
            // 底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            // 内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public void onClick(View view) {
        //RecyclerView可以计算出这是第几个Child
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition - mHeaderCount;
        if (childAdapterPosition >= mHeaderCount){
            if (onItemClickListener!=null) {
                onItemClickListener.onItemClick(i);
            }
        }

    }
    //对外提供接口初始化方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    /**
     * 接口回调
     * 1、定义接口，定义接口中的方法
     * 2、在数据产生的地方持有接口，并提供初始化方法，在数据产生的时候调用接口的方法
     * 3、在需要处理数据的地方实现接口，实现接口中的方法，并将接口传递到数据产生的地方
     */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tv;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv_rv_item);
//        tvTitle.setTextSize(20);
    }
}
