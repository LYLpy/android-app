package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvAdapterStudyKey;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.StudyKeyModelHome;
import net.getlearn.getlearn_android.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/25------更新------
 * 学习干货
 */

public class StudyKeyActivity extends BaseActivity implements BaseHelper.IHttpCallback {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.spring_view)
    SpringView mSpringView;
    @BindView(R.id.ll_not_data)
    LinearLayout mLlNoData;

    private RvAdapterStudyKey mRvAdapter;
    private StudyKeyModelHome.DatabeanX mData;
    private int page = 1;
    private int limit = 20;
    @Override
    protected void initData() {
        page = 1;
        mData = null;
        mHttpHelper.getStudyKeyHome(this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,limit);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_study_key;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        initSpringView();
    }
    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(this));
        mSpringView.setFooter(new DefaultFooter(this));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                page = 1;
                mData = null;
                if (mRvAdapter!=null){
                    mRvAdapter.removeData();
                }
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getStudyKeyHome(StudyKeyActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,limit);
                        mSpringView.onFinishFreshAndLoad();
                       }
                }, 200);
            }

            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHttpHelper.getStudyKeyHome(StudyKeyActivity.this, CommonUtils.getCurrentTimeStampInt(), UserManager.getInstance().getToken(),page,limit);
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case Constants.REQUEST_GET_STUDY_KEY_HOME:
                StudyKeyModelHome studyKeyModelHome = (StudyKeyModelHome) msg.obj;
                setData(studyKeyModelHome);
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        if (mLlNoData != null){
            mLlNoData.setVisibility(View.VISIBLE);
        }
    }

    private void setData(StudyKeyModelHome studyKeyModel){
        if (studyKeyModel.getData() != null && studyKeyModel.getData().getData()!=null &&studyKeyModel.getData().getData().size()>0 ){
            page++;
        }
        if (mData == null){
            mData = studyKeyModel.getData();
            mRvAdapter = new RvAdapterStudyKey(this,mData);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mRv.setLayoutManager(manager);
            mRv.setAdapter(mRvAdapter);
            mRvAdapter.setItemClickListener(new RvAdapterStudyKey.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(StudyKeyActivity.this, ReadActivity.class);
                    intent.putExtra("id",mData.getData().get(position).getId());
                    startActivity(intent);
                }
            });
        }else {
            List<StudyKeyModelHome.DatabeanX.Databean> data = mData.getData();
            List<StudyKeyModelHome.DatabeanX.Databean> newData = studyKeyModel.getData().getData();
            if (newData == null || newData.size() == 0 && mData != null){
//                        ToastUtil.showToast("没有更多了");
            }
            data.addAll(newData);
            mRvAdapter.notifyDataSetChanged();
            if (mData == null && mLlNoData != null){
                mLlNoData.setVisibility(View.GONE);
            }
        }
    }
}
