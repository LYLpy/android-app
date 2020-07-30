package net.getlearn.getlearn_android.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.RvAdapterMainPopup;
import net.getlearn.getlearn_android.model.bean.GradeModel;

import java.util.List;


/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/14------更新------
 * 首页选择年级popup
 */

public class SelectGradePopup extends PopupWindow{

    private View conentView;
    private RecyclerView rv1;
    private Context mContext;
    private RvAdapterMainPopup mRvAdapter;
    private List<GradeModel.Databean> mData;
    private RecyclerView.LayoutManager mManager;

    public SelectGradePopup(Context context, List<GradeModel.Databean> data, RecyclerView.LayoutManager manager) {
        super(context);
        mContext = context;
        mData = data;
        mManager = manager;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_layout, null);
        initPopupView(conentView);
        initRecyclerView(conentView);
    }

    private void initPopupView(View contentView) {

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectGradePopup.this.dismiss();
            }
        });
        // 2.2、设置视图
        setContentView(contentView);
        // 3、设置宽度
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 4、设置高度
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow是否能响应外部点击事件(点击外部隐藏popup)
        setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件(再次点击隐藏popup)
        setTouchable(true);
        setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.bg_gray_translucent)));//new ColorDrawable(0)透明背景，去掉黑色边框
    }
    private void initRecyclerView(View conentView) {
        //初始化recycle
        rv1 = conentView.findViewById(R.id.rv1);
        rv1.setLayoutManager(mManager);
        mRvAdapter = new RvAdapterMainPopup(mContext, mData);
        rv1.setAdapter(mRvAdapter);
    }

    public void setOnPopupItemClickListener(RvAdapterMainPopup.OnPopupItemClickListener mOnItemClickListener){
        mRvAdapter.setOnPopupItemClickLIstener(mOnItemClickListener);
    }
    /**
     * 设置选中的item的position
     * @param position
     */
    public void setSelection(int position){
        mRvAdapter.setSelection(position);
    }
}
