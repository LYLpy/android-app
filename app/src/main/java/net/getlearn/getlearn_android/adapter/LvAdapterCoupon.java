package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.CouponModel;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 */

public class LvAdapterCoupon extends BaseAdapter {
    private List<CouponModel> mData;
    private Context mContext;
    //优惠券三种类型，正常、已使用、已过期
    private int type = 1;
    public static final int NORMAL = 1;
    public static final int USED = 2;
    public static final int EXPIRE = 3;
    public LvAdapterCoupon(List<CouponModel> mData, Context mContext,int type) {
        this.mData = mData;
        this.mContext = mContext;
        this.type = type;
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
        ViewHolder viewHolder;
        //判断是否有缓存
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_coupon, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //得到缓存的布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置内容
        viewHolder.termOfValidity.setText(mData.get(position).getTermOfValidity());
        viewHolder.tvdescribe.setText(mData.get(position).getDescribe());
        viewHolder.tvPar.setText("¥" + mData.get(position).getPar() );
        viewHolder.btnGoToUse.setTag(position);
        if (type == USED){
            viewHolder.btnGoToUse.setVisibility(View.GONE);
        }else if (type == EXPIRE){
            viewHolder.btnGoToUse.setVisibility(View.GONE);
            viewHolder.tvPar.setBackgroundColor(mContext.getResources().getColor(R.color.bg_gray3));
        }
        viewHolder.btnGoToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onButtonClickListener != null){
                    onButtonClickListener.onButtonClick(view);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder {
        public TextView tvdescribe;
        public TextView tvPar;
        public TextView termOfValidity;
        public TextView btnGoToUse;
        public RelativeLayout mRl;
        public ViewHolder(View view){
            tvdescribe = view.findViewById(R.id.tv_describe);
            tvPar = view.findViewById(R.id.tv_par);
            termOfValidity = view.findViewById(R.id.tv_term_of_validity);
            btnGoToUse = view.findViewById(R.id.btn_go_to_use);
            mRl = view.findViewById(R.id.rl_msg);
        }
    }
    private OnButtonClickListener onButtonClickListener;
    public interface OnButtonClickListener {
        void onButtonClick(View view);
    }
    public void setOnBtnClickListener(OnButtonClickListener onButtonClickListener){
        this.onButtonClickListener = onButtonClickListener;
    }


}
