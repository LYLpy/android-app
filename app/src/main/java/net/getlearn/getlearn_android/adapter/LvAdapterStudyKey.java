package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.StudyKeyModel;
import net.getlearn.getlearn_android.utils.RichtextUtil;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/25------更新------
 *
 */

public class LvAdapterStudyKey extends BaseAdapter {
    private List<StudyKeyModel> mData;
    private Context mContext;

    public LvAdapterStudyKey(List<StudyKeyModel> mData, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_study_key, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //得到缓存的布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置内容
        viewHolder.tvTitle.setText(mData.get(position).getTitle());
        String source = RichtextUtil.getBlack(String.valueOf(mData.get(position).getViews())) + "人已学" ;
        Spanned spanned =  Html.fromHtml(source);
        viewHolder.tvViews.setText(spanned);
        return convertView;
    }

    public class ViewHolder {
        public ImageView iv;
        public TextView tvTitle;
        public TextView tvViews;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.iv_study_key);
            tvTitle = view.findViewById(R.id.tv_title_study_key);
            tvViews = view.findViewById(R.id.tv_views_study_key);
        }
    }

}
