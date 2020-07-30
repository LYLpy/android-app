package net.getlearn.getlearn_android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.UserManager;
import net.getlearn.getlearn_android.adapter.LvAdapterSelectVersion;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.bean.SubjectVersionModel;
import net.getlearn.getlearn_android.utils.CommonUtils;
import net.getlearn.getlearn_android.utils.ScaleUtils;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.Date;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/19------更新------
 */

public class SelectVersionActivity extends BaseActivity implements LvAdapterSelectVersion.OnLvItemClick, BaseHelper.IHttpCallback {

    private List<SubjectVersionModel.Databean> mData;
    private LvAdapterSelectVersion mLvAdapter;
    private ListView mListView;
    private String gradeId;
    private FrameLayout mFlBack;
    private SpringView mSpringView;
    private View mHeaderView;
    private View mBootView;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        gradeId = intent.getStringExtra("gradeId");
        mHttpHelper.getSubjectVersionClassification(this, CommonUtils.getTimeStampInt(new Date()), UserManager.getInstance().getToken());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_select_version;
    }

    @Override
    protected void initView() {
        mListView = findViewById(R.id.lv);
        mFlBack= findViewById(R.id.fl_back);
        mSpringView = findViewById(R.id.spring_view);
        mFlBack.setOnClickListener(this);
        initSpringView();
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
                        mListView.removeAllViewsInLayout();
                        mHttpHelper.getSubjectVersionClassification(SelectVersionActivity.this, CommonUtils.getTimeStampInt(new Date()), UserManager.getInstance().getToken());
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(TextView textView, int position) {
        this.textView = textView;
        showSingleChoiceDialog(position);
    }
    TextView textView;
    private String[] array;
    private void showSingleChoiceDialog(int position) {
        SubjectVersionModel.Databean databean = mData.get(position);
        array = new String[databean.getVersion().size()];
        for (int i = 0; i < array.length; i++) {
            SubjectVersionModel.Databean.Versionbean versionbean = databean.getVersion().get(i);
            array[i] = versionbean.getVersion_name();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setGravity(Gravity.CENTER);
        title.setTextSize(20);
        title.setTextColor(getResources().getColor(R.color.black));
        title.setPadding(0, ScaleUtils.dip2px(10),0,0);
//        title.setLayoutParams(layoutParams);
        title.setText("请选择" + databean.getSubject() + "版本");
        builder.setCustomTitle(title);
        builder.setSingleChoiceItems(array,databean.getSelectedPosition(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = array[which];
                textView.setText(str);
                mData.get(position).setSelectedPosition(which);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setData(){

            mHeaderView = getLayoutInflater().inflate(R.layout.head_select_version, null);
            mBootView = getLayoutInflater().inflate(R.layout.bottom_select_version, null);
            TextView tvNext = mBootView.findViewById(R.id.tv_next);
            tvNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    StringBuilder select_course = new StringBuilder();
                    for (int i = 0; i < mData.size(); i++) {
                        if (i == mData.size() -1){
                            SubjectVersionModel.Databean databean = mData.get(i);
                            select_course.append(databean.getId());
                            select_course.append("@");
                            select_course.append(databean.getVersion().get(databean.getSelectedPosition()).getVersion_id());
                        }else {
                            SubjectVersionModel.Databean databean = mData.get(i);
                            select_course.append(databean.getId());
                            select_course.append("@");
                            select_course.append(databean.getVersion().get(databean.getSelectedPosition()).getVersion_id());
                            select_course.append(",");
                        }
                    }
                    mHttpHelper.saveSubjectAndVersion(SelectVersionActivity.this,CommonUtils.getCurrentTimeStampInt(),UserManager.getInstance().getToken(),gradeId,select_course.toString());
                }
            });
            // 添加头部和尾部view：必须放在adapter前面不然会报错
            mListView.addHeaderView(mHeaderView);
            mListView.addFooterView(mBootView);
            mLvAdapter = new LvAdapterSelectVersion(mData,this);
            mLvAdapter.setItemClickLisener(this);
            mListView.setAdapter(mLvAdapter);

    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        if (reqType == Constants.REQUEST_GET_SUBJECT_VERSION_CLASSIFICATION){
            SubjectVersionModel model = (SubjectVersionModel) msg.obj;
            mData = model.getData();
            setData();
        }else if (reqType == Constants.REQUEST_SAVE_SUBJECT_AND_VERSION){
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        ToastUtil.showToast(error);
    }
}
