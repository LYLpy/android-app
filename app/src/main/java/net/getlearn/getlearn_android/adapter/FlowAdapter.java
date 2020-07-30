package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.view.FlowLayout;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/27------更新------
 * 流式布局适配器
 */

public class FlowAdapter implements FlowLayout.FlowLayoutAdapter {

    private List<String> mList;
    private Context mContext;
    private int dp15, dp12, dp6,dp25;
    public FlowAdapter(List<String> list,Context context) {
        this.mList = list;
        mContext = context;
        dp15 = mContext.getResources().getDimensionPixelSize(R.dimen.dp15);
        dp12 = mContext.getResources().getDimensionPixelSize(R.dimen.dp12);
        dp6 = mContext.getResources().getDimensionPixelSize(R.dimen.dp6);
        dp25 = mContext.getResources().getDimensionPixelSize(R.dimen.dp25);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        final TextView textView = new TextView(mContext);
        textView.setText(mList.get(position));
        textView.setTextSize(15);
        textView.setTextColor(mContext.getResources().getColor(R.color.bg_gray4));
        textView.setPadding(dp15, dp6, dp15, dp6);

        //设置背景
        textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.btn_selector_20));
        //click listenr
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnFlowItemClickListener != null){
                    mOnFlowItemClickListener.onFlowItemClick(v);
                }
            }
        });
        return textView;
    }
    private OnFlowItemClickListener mOnFlowItemClickListener;
    public interface OnFlowItemClickListener{
        void onFlowItemClick(View v);
    }
    public void setOnFlowItemClickListener(OnFlowItemClickListener onFlowItemClickListener) {
        mOnFlowItemClickListener = onFlowItemClickListener;
    }
}
