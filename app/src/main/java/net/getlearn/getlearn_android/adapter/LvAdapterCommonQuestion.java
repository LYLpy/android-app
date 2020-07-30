package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.CommonQuestionModel;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/11------更新------
 */

public class LvAdapterCommonQuestion extends BaseAdapter {
    private List<CommonQuestionModel> mData;
    private Context mContext;

    public LvAdapterCommonQuestion(List<CommonQuestionModel> mData, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_common_question_lv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //得到缓存的布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置内容
        viewHolder.tvTitle.setText(mData.get(position).getTitle());
        viewHolder.tvContent.setText(mData.get(position).getContent());
        return convertView;
    }

    public class ViewHolder {
        public TextView tvTitle;
        public TextView tvContent;
        public ViewHolder(View view){
            tvTitle = view.findViewById(R.id.tv_title_common_question);
            tvContent = view.findViewById(R.id.tv_content_common_question);
        }
    }

}
