package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.SearchResultModel;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 */

public class LvAdapterMyCollect extends BaseAdapter {
    private List<SearchResultModel> mData;
    private Context mContext;

    public LvAdapterMyCollect(List<SearchResultModel> mData, Context mContext) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        //判断是否有缓存
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_my_collect, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //得到缓存的布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置内容
//        viewHolder.ivIcon.setBackgroundResource(mData.get(mPosition).imageId);
        viewHolder.iv.setBackgroundResource(R.drawable.img01);
//        viewHolder.tvTitle.setText(mData.get(position).getTitle());
//        viewHolder.tvAmount.setText("课时：" + mData.get(position).getAmount() + "课");
//        viewHolder.tvViews.setText(mData.get(position).getViews() + "人已学");
        return convertView;
    }

    public class ViewHolder {
        public ImageView iv;
        public TextView tvTitle;
        public TextView tvAmount;
        public TextView tvViews;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.iv_icon);
            tvTitle = view.findViewById(R.id.tv_title);
            tvAmount = view.findViewById(R.id.tv_amount);
            tvViews = view.findViewById(R.id.views_search_lv);
        }
    }

    public List<SearchResultModel> getData() {
        return mData;
    }

    public void setData(List<SearchResultModel> data) {
        mData = data;
    }
}
