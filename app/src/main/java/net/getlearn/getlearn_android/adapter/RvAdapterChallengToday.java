package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.TodayChallengeModel;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/13------更新------
 * 今日挑战Adapter
 */

public class RvAdapterChallengToday extends RecyclerView.Adapter<RvAdapterChallengToday.ItemViewHolder>{

    private Context mContext;
    private TodayChallengeModel.DatabeanX mData;
    private LayoutInflater mInflater;

    public RvAdapterChallengToday(Context context,TodayChallengeModel.DatabeanX data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(mInflater.inflate(R.layout.item_challenge_today,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int i) {
        TodayChallengeModel.DatabeanX.AppWeekChallengesListbean.Databean databean = mData.getAppWeekChallengesList().getData().get(i);
        LogUtil.e("__today_adapter" + i,databean.getName());
        LogUtil.e("__today_adapter"+ i ,"奖励" + databean.getIntegral() + "积分");
        viewHolder.tvTitle.setText(databean.getName());
        viewHolder.tvIntegral.setText("奖励" + databean.getIntegral() + "积分");

        viewHolder.tvCompletion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mButtonClickListener != null){
                    mButtonClickListener.onButtonClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        LogUtil.e("__today_adapter_getItemCount" ,mData.getAppWeekChallengesList().getData().size() + "");
        return mData.getAppWeekChallengesList().getData().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvIntegral;
        public TextView tvCompletion;
        public View itemView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvIntegral = itemView.findViewById(R.id.tv_integral);
            tvCompletion = itemView.findViewById(R.id.tv_is_complete);
        }
    }
    private OnButtonClickListener mButtonClickListener;

    public void setButtonClickListener(OnButtonClickListener buttonClickListener) {
        mButtonClickListener = buttonClickListener;
    }

    public interface OnButtonClickListener{
        void onButtonClick(int position);
    }


    public void removeData(){
        if (mData!=null && mData.getAppWeekChallengesList()!=null){
            mData.getAppWeekChallengesList().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
