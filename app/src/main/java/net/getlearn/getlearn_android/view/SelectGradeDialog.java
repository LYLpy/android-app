package net.getlearn.getlearn_android.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.RvAdapterUserInfoSelectGrade;
import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/19------更新------
 * 选择年级的dialog
 */

public class SelectGradeDialog implements TimePickerDialog.TimePickerDialogInterface {
    private Context mContext;
    private Dialog dialog;
    private TimePickerDialog mTimePickerDialog;
    List<GradeModel.Databean> mData;
    private RvAdapterUserInfoSelectGrade mAdapter;

    public SelectGradeDialog(Context context,List<GradeModel.Databean> data) {
        mContext = context;
        mData = data;
//        calenda = Calendar.getContext();
    }

    /**
     * @return
     */
    public SelectGradeDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.dialog_select_grade, null);
        RecyclerView rv = view.findViewById(R.id.rv);
        mAdapter = new RvAdapterUserInfoSelectGrade(mContext,mData);
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext,2);
        mAdapter.setOnItemClickListener(new RvAdapterUserInfoSelectGrade.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                    if (mListener != null){
                        mListener.onDiaItemClick(position,mData.get(position).getId());
                    }
                cancle();
                }
        });
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        dialog = new Dialog(mContext,R.style.new_task_style);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        mTimePickerDialog = new TimePickerDialog(mContext,this);
        return this;
    }
    public void show() {
        if(dialog!=null)dialog.show();
    }
    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }

    @Override
    public void positiveListener() {
        LogUtil.e("__","确定");
    }

    @Override
    public void negativeListener() {
        LogUtil.e("__","取消");
    }

    public void setOnDiaItemClickListener(OnDiaItemClickListener listener) {
        mListener = listener;
    }
    private OnDiaItemClickListener mListener;
    public interface OnDiaItemClickListener {
        /**
         * @param position 点击的位置
         * @param id
         */
        void onDiaItemClick(int position, int id);
    }
    /**
     * 设置选中的item的position
     * @param position
     */
    public void setSelection(int position){
        mAdapter.setSelected(position);
    }

}
