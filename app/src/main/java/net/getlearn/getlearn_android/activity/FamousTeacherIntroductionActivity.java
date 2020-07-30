package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterFamousTeacherIntroduction2;
import net.getlearn.getlearn_android.model.bean.FamousTeacherIntroductionModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/29------更新------
 * 名师主页
 */

public class FamousTeacherIntroductionActivity extends BaseActivity implements RvAdapterFamousTeacherIntroduction2.OnItemClickListener {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.iv_no_data)
    ImageView mIvNodata;
    @BindView(R.id.spring_view)
    SpringView springView;
    private int teacher_id;
    private int page = 1;
    private int limit = 20;
    private FamousTeacherIntroductionModel.Databean mData;
    private RvAdapterFamousTeacherIntroduction2 mRvAdapter;
    @Override
    protected void initData() {
        Intent intent = getIntent();
        teacher_id = intent.getIntExtra("teacher_id",0);
        mHttpHelper.getFamousTeacherIntroduction(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),teacher_id);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_famous_teacher_introduction;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        initSpringView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
        }
    }

    private void initSpringView(){
        springView.setHeader(new DefaultHeader(this));
        //这里数据没有翻页，把上拉备注掉
//        springView.setFooter(new DefaultFooter(this));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        mData = null;
                        if (mRvAdapter!=null){
                            mRvAdapter.removeData();
                        }
                        mHttpHelper.getFamousTeacherIntroduction(FamousTeacherIntroductionActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),teacher_id);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getFamousTeacherIntroduction(FamousTeacherIntroductionActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),teacher_id);
                        springView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onItemClick(int position, View view) {
        Intent intent = new Intent(this,VideoPlayActivity.class);
        intent.putExtra("course_id",mData.getCourse().get(position).getCourse_id());
        startActivity(intent);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_FAMOUS_TEACHER_INTRODUCTION:
                FamousTeacherIntroductionModel famousTeacherIntroductionModel = (FamousTeacherIntroductionModel) msg.obj;
                setData(famousTeacherIntroductionModel);
                break;
        }
    }

    private void setData(FamousTeacherIntroductionModel model) {
        if (model !=null && model.getData()!=null && model.getData().getCourse().size() > 0){
            page++;
            LogUtil.e("__setData","page++");
        }
        if (mData == null){
            LogUtil.e("__setData","mData == null");
            if (model.getData() != null){
                mData = model.getData();
                mIvNodata.setVisibility(View.GONE);
                mRvAdapter = new RvAdapterFamousTeacherIntroduction2(this,mData);
                mRvAdapter.setItemClickListener(this);
                mRv.setLayoutManager(new LinearLayoutManager(this));
                mRv.setAdapter(mRvAdapter);
            }
        }else {
            LogUtil.e("__setData","mData != null");
            List<FamousTeacherIntroductionModel.Databean.Coursebean> data = mData.getCourse();
            List<FamousTeacherIntroductionModel.Databean.Coursebean> newData = model.getData().getCourse();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mRvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
}
