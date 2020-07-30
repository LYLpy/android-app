package net.getlearn.getlearn_android.activity;

import android.graphics.Typeface;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import net.getlearn.getlearn_android.Constants;
import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.adapter.TabPagerAdapter;
import net.getlearn.getlearn_android.fragment.CouponFragment;
import net.getlearn.getlearn_android.view.IOSAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 * 优惠券界面
 */

public class CouponActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    RelativeLayout mToolbarIndex;
    @BindView(R.id.fl_back)
    FrameLayout mFlBack;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager_coupon)
    ViewPager mViewpager;
    private List<Fragment> fragments;
    private CouponFragment fm1;
    private CouponFragment fm2;
    private CouponFragment fm3;
    private IOSAlertDialog mDialog;
    String[] titles;
    @Override
    protected void initData() {
        mDialog = new IOSAlertDialog(this).builder();
        mDialog.setTitle("如何获得优惠券").setMsg("1、参与app签到，并完成任务，可获得积分，积分可  兑换优惠券；\n" +
                "2、参与app内官方活动赠送；\n"+
                "3、关注官方公众号xxxx，不定期活动赠送；").setPositiveButton("我知道了",null).setMsgGravity(Gravity.LEFT)
                .setTitleStyle(Typeface.DEFAULT);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void initView() {
        mFlBack.setOnClickListener(this);
        fragments = new ArrayList<>();
        fm1 = CouponFragment.newInstance(Constants.COUPON_STATUS_NOT_USED);
        fm2 = CouponFragment.newInstance(Constants.COUPON_STATUS_USED);
        fm3 = CouponFragment.newInstance(Constants.COUPON_STATUS_EXPIRED);
        fragments.add(fm1);
        fragments.add(fm2);
        fragments.add(fm3);
        titles = getResources().getStringArray(R.array.tab_names);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments, this,titles);
        mViewpager.setAdapter(adapter);
        //2.绑定ViewPager和TabLayout
        mTablayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_explain:
                mDialog.show();
                break;
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
