package net.getlearn.getlearn_android.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.FlowAdapter;
import net.getlearn.getlearn_android.utils.LogUtil;
import net.getlearn.getlearn_android.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/20------更新------
 * 搜索
 */

public class SearchActivity extends BaseActivity implements FlowAdapter.OnFlowItemClickListener {
    private FlowLayout flowLayout;
    private int dp15, dp12, dp6,dp25;
    private List<String> list;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.edit_search)
    EditText mEdit;
    private FlowAdapter mFlowAdapter;
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
//        list.add("java");
//        list.add("javaEE");
//        list.add("javaME");
//        list.add("ccccc");
//        list.add("php");
//        list.add("ios");
//        list.add("c++");
//        list.add("c#");
//        list.add("Android");
        list.add("语文");
        list.add("人教版");
        list.add("1年级");
        dp15 = getResources().getDimensionPixelSize(R.dimen.dp15);
        dp12 = getResources().getDimensionPixelSize(R.dimen.dp12);
        dp6 = getResources().getDimensionPixelSize(R.dimen.dp6);
        dp25 = getResources().getDimensionPixelSize(R.dimen.dp25);

        flowLayout = findViewById(R.id.flow_layout);
        flowLayout.setPadding(dp15,dp15,dp15,dp15);
        flowLayout.setHorizontalSpace(dp15);
        flowLayout.setVerticalSpace(dp15);
        //设置右边是否对齐
        flowLayout.setAlignRight(false);
        mFlowAdapter = new FlowAdapter(list,this);
        mFlowAdapter.setOnFlowItemClickListener(this);
        flowLayout.setAdapter(mFlowAdapter);
        mFlBack.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
        mEdit.setFocusable(true);
        mEdit.setFocusableInTouchMode(true);
        mEdit.requestFocus();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_back:
                finish();
                break;
            case R.id.tv_search:
                String content = mEdit.getText().toString().replace(" ","");
                if ( content == null || content.equals("")){
                    content = "";
//                    ToastUtil.showToast("搜索内容不能为空");
//                    return;
                }
                Intent intent = new Intent(this,SearchResultActivity.class);
                intent.putExtra("content",content);
                startActivity(intent);
                finish();
                break;
        }
    }


    @Override
    public void onFlowItemClick(View v) {
        TextView tv = (TextView) v;
        Intent intent = new Intent(this,SearchResultActivity.class);
        intent.putExtra("content",tv.getText().toString());
        startActivity(intent);

        LogUtil.e("__onFlowItemClick",tv.getText().toString());
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
}
