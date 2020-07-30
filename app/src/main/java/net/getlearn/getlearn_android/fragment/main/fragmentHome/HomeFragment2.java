package net.getlearn.getlearn_android.fragment.main.fragmentHome;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.getlearn.getlearn_android.R;
import net.getlearn.getlearn_android.fragment.BaseFragment;
import net.getlearn.getlearn_android.fragment.BaseLazyFragment;

public class HomeFragment2 extends BaseLazyFragment {




    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home_fragment2;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadData() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 请求成功
     *
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的javabean数据
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {

    }

    /**
     * 请求失败
     *
     * @param reqType 区分调用的是哪一个接口
     * @param error   请求失败的原因
     */
    @Override
    public void onHttpError(int reqType, String error) {

    }
}
