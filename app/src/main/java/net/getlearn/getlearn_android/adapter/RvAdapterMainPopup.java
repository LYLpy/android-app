package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.GradeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/26------更新------
 */

public class RvAdapterMainPopup extends RecyclerView.Adapter<RvAdapterMainPopup.MainPopupHolder> {
    private LayoutInflater inflater;
    private Context mContext;
    private List<GradeModel.Databean> mDatas;
    private int selected = -1;//当前选中item

    /**
     * 设置选中的item的position
     * @param position
     */
    public void setSelection(int position){
        selected = position;
        notifyDataSetChanged();
    }
    //创建构造参数
    public RvAdapterMainPopup(Context context , List<GradeModel.Databean> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MainPopupHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = inflater.inflate(R.layout.item_popup_main, viewGroup, false);
        MainPopupHolder holder = new MainPopupHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainPopupHolder mainPopupHolder, int position) {
        //将position设置为tag
        mainPopupHolder.tv.setTag(position);
        mainPopupHolder.tv.setText(mDatas.get(position).getOption());
        if(selected == position){
            mainPopupHolder.tv.setSelected(true);
            mainPopupHolder.tv.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            mainPopupHolder.tv.setSelected(false);
            mainPopupHolder.tv.setTextColor(mContext.getResources().getColor(R.color.black));
        }

        //设置监听
        mainPopupHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null){
                    if (mListener !=null){
                        mListener.onPopupItemClick(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MainPopupHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MainPopupHolder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item_popup);
        }
    }

    private OnPopupItemClickListener mListener;
    public void setOnPopupItemClickLIstener(OnPopupItemClickListener itemClickLIstener){
        mListener = itemClickLIstener;
    }
    public interface OnPopupItemClickListener {
        void onPopupItemClick(int position);
    }

    public List<GradeModel.Databean> getDatas() {
        return mDatas;
    }

    public void setDatas(List<GradeModel.Databean> datas) {
        mDatas = datas;
    }

    public void removeData(){
            mDatas = new ArrayList<>();
        notifyDataSetChanged();
    }
}
