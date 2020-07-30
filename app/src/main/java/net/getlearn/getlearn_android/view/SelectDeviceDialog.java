package net.getlearn.getlearn_android.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.RvAdapterSelectDevice;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/9/17------更新------
 * 选择网络设备的dialog
 */

public class SelectDeviceDialog {
    private Context mContext;
    private Dialog dialog;
    private List<LelinkServiceInfo> mData;
    private RecyclerView mRv;

    public SelectDeviceDialog(Context context,List<LelinkServiceInfo> data) {
        mContext = context;
        mData = data;
    }
    public SelectDeviceDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_select_device, null);
        mRv = view.findViewById(R.id.rv);
        ImageView iv = view.findViewById(R.id.iv_cancle);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancle();
            }
        });
        initRv();
        dialog = new Dialog(mContext,R.style.new_task_style);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        return this;
    }

    private void initRv(){
        if (mData == null){
            return;
        }
        RvAdapterSelectDevice adapter = new RvAdapterSelectDevice(mContext, mData);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
        adapter.setItemClickListener(new RvAdapterSelectDevice.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mListener != null){
                    mListener.onPopUpItemClick(mData.get(position));
                }
                cancle();
            }
        });
        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);
    }


    public void show() {
        if(dialog!=null)dialog.show();
    }
    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }


    public void setOnPopupItemClickListener(OnPopupItemClickListener listener) {
        mListener = listener;
    }
    private OnPopupItemClickListener mListener;
    public interface OnPopupItemClickListener{
        /**
         * @param serviceInfo
         */
        void onPopUpItemClick(LelinkServiceInfo serviceInfo);
    }
    public void setNewData(List<LelinkServiceInfo> data){
        mData = data;
        initRv();
    }

    public Dialog getDialog() {
        return dialog;
    }
}
