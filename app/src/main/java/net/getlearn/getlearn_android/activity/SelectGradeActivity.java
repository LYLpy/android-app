package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.RvMyAdapter;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.GradeModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/19------更新------
 */

public class SelectGradeActivity extends BaseActivity implements RvMyAdapter.OnItemClickListener, BaseHelper.IHttpCallback {

    private RecyclerView recyclerView;
    private RvMyAdapter mRvMyAdapter;
    private List<GradeModel.Databean> mData = new ArrayList<>();
    private SpringView mSpringView;

    @Override
    protected void initData() {
        mHttpHelper.getGrade(this, CommonUtils.getTimeStampInt(new Date()),UserManager.getInstance().getToken());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_select_grade;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        // 设置布局管理一条数据占用几行，如果是头布局则头布局自己占用一行
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                if (postion == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        mSpringView = findViewById(R.id.spring_view);
        initSpringView();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(int position) {
            UserManager.getInstance().setGradeId(mData.get(position).getId());
            LogUtil.e("__选择的年级",position + ":" + mData.get(position).getOption());
            mRvMyAdapter.setSelection(position);
            Intent intent = new Intent(this,SelectVersionActivity.class);
            intent.putExtra("gradeId",String.valueOf(mData.get(position).getId()));
            startActivity(intent);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
            try {
                GradeModel model = (GradeModel) msg.obj;
                //保存到SP中
                SPUtil.saveGradeList(model.getData());
                setData(model.getData());
            }catch (Exception e){
                e.getStackTrace();
            }
    }
    private void initSpringView() {
        mSpringView.setHeader(new DefaultHeader(this));
//        mSpringView.setFooter(new DefaultFooter(this));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {    //下拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData = null;
                        mHttpHelper.getGrade(SelectGradeActivity.this, CommonUtils.getTimeStampInt(new Date()), UserManager.getInstance().getToken());
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
            @Override
            public void onLoadmore() {    //上拉
                CommonUtils.getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 200);
            }
        });
    }
    @Override
    public void onHttpError(int reqType, String error) {
        ToastUtil.showToast(error);
    }
    private void setData(List<GradeModel.Databean> model){
        mData.addAll(model);
        mRvMyAdapter = new RvMyAdapter(this, mData);
        mRvMyAdapter.setHeaderCount(1);
        mRvMyAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mRvMyAdapter);//设置适配器;
    }
}
