package net.getlearn.getlearn_android.activity;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.RvAdapterMyStudy;
import net.getlearn.getlearn_android.model.bean.MyStudyModel;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/12------更新------
 *我的学习activity
 */

public class MyStudyActivity extends BaseActivity implements RvAdapterMyStudy.OnItemClickListener {

    @BindView(R.id.rv_my_study)
    RecyclerView mRv;
    @BindView(R.id.iv_my_study)
    ImageView mIvMyStudy;
    private RvAdapterMyStudy mAdapter;
    private List<MyStudyModel> mData;
    @Override
    protected void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_study;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(int position) {
        LogUtil.e("__","" + position);
    }

    @Override
    public void onGetAllClick() {
        LogUtil.e("__","查看更多课程");
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
}
