package net.getlearn.getlearn_android.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.RvAdapterNewTask;
import net.getlearn.getlearn_android.model.bean.NewTaskModel;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/14------更新------
 * 选择提醒时间的dialog
 */

public class CreateStudyPlanDialog implements TimePickerDialog.TimePickerDialogInterface {
    private Calendar calendr;
    private Context mContext;
    private Dialog dialog;
    private TimePickerDialog mTimePickerDialog;

    public CreateStudyPlanDialog(Context context) {
        mContext = context;
//        calenda = Calendar.getContext();
    }
    public CreateStudyPlanDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.dialog_new_task, null);
        RecyclerView rv = view.findViewById(R.id.rv_new_task);
        List<NewTaskModel> data = new ArrayList<>();
        data.add(new NewTaskModel(0,"今天中午","12:00"));
        data.add(new NewTaskModel(0,"今天下午","18:00"));
        data.add(new NewTaskModel(0,"今天晚上","22:00"));
        data.add(new NewTaskModel(0,"明天晚上","22:00"));
        data.add(new NewTaskModel(0,"自定义",""));
        data.add(new NewTaskModel(0,"待定",""));
        RvAdapterNewTask adapter = new RvAdapterNewTask(mContext,data);
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext,2);
        adapter.setItemClickListener(new RvAdapterNewTask.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                calendr = Calendar.getInstance();
                    switch (position){
                        case 0:
                            calendr.set(Calendar.HOUR_OF_DAY,12);
                            calendr.set(Calendar.MINUTE,00);
                            break;
                        case 1:
                            calendr.set(Calendar.HOUR_OF_DAY,18);
                            calendr.set(Calendar.MINUTE,00);
                            break;
                        case 2:
                            calendr.set(Calendar.HOUR_OF_DAY,22);
                            calendr.set(Calendar.MINUTE,00);
                            break;
                        case 3:
                            calendr.add(Calendar.DAY_OF_MONTH, 1);
                            calendr.set(Calendar.HOUR_OF_DAY,22);
                            calendr.set(Calendar.MINUTE,00);
                            break;
                        case 4:
                            calendr = null;
                            break;
                        case 5:
                            cancle();
                            return;
                        default:break;
                    }
                    if (mListener != null){
                        mListener.onPopUpItemClick(position,calendr);
                    }
                cancle();
                }
        });
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
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

    public void setOnPopupItemClickListener(OnPopupItemClickListener listener) {
        mListener = listener;
    }
    private OnPopupItemClickListener mListener;
    public interface OnPopupItemClickListener{
        /**
         * @param position 点击的位置
         * @param calendar Calendar 对象
         */
        void onPopUpItemClick(int position,Calendar calendar);
    }

    public Dialog getDialog() {
        return dialog;
    }
}
