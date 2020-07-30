package net.getlearn.getlearn_android.activity;

import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.TabPagerAdapter;
import net.getlearn.getlearn_android.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 我的订单
 */

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.iv_explain)
    ImageView mIvExplain;
    @BindView(R.id.toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager_coupon)
    ViewPager mViewpager;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;


    private List<Fragment> fragments;
    private OrderFragment fm1;
    private OrderFragment fm2;
    private OrderFragment fm3;
    private String[] titles;
    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fm1 = OrderFragment.getInstanace(0,true);
        fm2 = OrderFragment.getInstanace(1,false);
        fm3 = OrderFragment.getInstanace(0,false);
        fragments.add(fm1);
        fragments.add(fm2);
        fragments.add(fm3);
        titles = getResources().getStringArray(R.array.tab_my_order);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments, this,titles);
        mViewpager.setAdapter(adapter);
        //2.绑定ViewPager和TabLayout
        mTablayout.setupWithViewPager(mViewpager);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initView() {
        mIvExplain.setVisibility(View.GONE);
        mTvTitle.setText("我的订单");
        mFlBack.setOnClickListener(this);
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

    }

    @Override
    public void onHttpError(int reqType, String error) {

    }
}
