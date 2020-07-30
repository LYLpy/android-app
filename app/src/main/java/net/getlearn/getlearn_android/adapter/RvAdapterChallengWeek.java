package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.model.bean.WeekChallengeModel;

import java.util.ArrayList;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/13------更新------
 * 本周挑战Adapter
 */

public class RvAdapterChallengWeek extends RecyclerView.Adapter<RvAdapterChallengWeek.ItemViewHolder>{

    private Context mContext;
    private WeekChallengeModel.DatabeanX mData;
    private LayoutInflater mInflater;

    public RvAdapterChallengWeek(Context context,WeekChallengeModel.DatabeanX data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(mInflater.inflate(R.layout.item_challenge_week,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        WeekChallengeModel.DatabeanX.AppWeekChallengesListbean.Databean databean = mData.getAppWeekChallengesList().getData().get(i);
        itemViewHolder.tvTitle.setText(databean.getName());
        itemViewHolder.tvIntegral.setText("奖励" + databean.getIntegral() + "积分");
        itemViewHolder.tvCompletion.setOnClickListener(new View.OnClickListener() {
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

    public WeekChallengeModel.DatabeanX getData() {
        return mData;
    }

    public void setData(WeekChallengeModel.DatabeanX data) {
        mData = data;
    }
    public void removeData(){
        if (mData!=null&&mData.getAppWeekChallengesList()!=null){
            mData.getAppWeekChallengesList().setData(new ArrayList<>());
        }
        notifyDataSetChanged();
    }
}
