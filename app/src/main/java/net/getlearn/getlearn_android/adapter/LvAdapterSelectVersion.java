package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.SubjectVersionModel;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/19------更新------
 */

public class LvAdapterSelectVersion extends BaseAdapter {
    private List<SubjectVersionModel.Databean> mData;
    private Context mContext;
    private OnLvItemClick mOnLvItemClick;

    public LvAdapterSelectVersion(List<SubjectVersionModel.Databean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        SubjectVersionModel.Databean model = mData.get(position);
        SubjectVersionModel.Databean.Versionbean versionbean = null;
        ViewHolder viewHolder;
        //判断是否有缓存
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_select_version, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //得到缓存的布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            versionbean = model.getVersion().get(model.getSelectedPosition());
        //设置内容
        viewHolder.tv.setText(model.getSubject());
        viewHolder.tvSelectVersion.setText(versionbean.getVersion_name());
        //设置点击
        viewHolder.llSelectVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnLvItemClick!=null){
                    mOnLvItemClick.onItemClick(viewHolder.tvSelectVersion ,position);
                }
            }
        });
            if (versionbean.getVersion_id() == 0){
                viewHolder.itemView.setVisibility(View.GONE);
            }else {
                viewHolder.itemView.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            ToastUtil.showToast("版本内容错误");
        }
        return convertView;

    }
    public void setItemClickLisener(OnLvItemClick itemClickLisener){
        this.mOnLvItemClick = itemClickLisener;
    }
    public class ViewHolder {
        public View itemView;
        public TextView tv;
        public TextView tvSelectVersion;
        public LinearLayout llSelectVersion;
        public ViewHolder(View view){
            itemView = view;
            tv = view.findViewById(R.id.tv_subject);
            tvSelectVersion = view.findViewById(R.id.tv_select_version);
            llSelectVersion = view.findViewById(R.id.ll_select_version);
        }
    }
    //设置点击
    public interface OnLvItemClick {
         void onItemClick(TextView textView,int position);
    }

    public List<SubjectVersionModel.Databean> getData() {
        return mData;
    }

    public void setData(List<SubjectVersionModel.Databean> data) {
        mData = data;
    }
}
