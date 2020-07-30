package net.getlearn.getlearn_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.getlearn.getlearn_android.model.HttpHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/20------更新------
 * 用于ViewPager的懒加载Fragment
 */

public abstract class ViewPagerLazyFragment extends Fragment implements View.OnClickListener{

    protected HttpHelper mHttpHelper = new HttpHelper();
    private Unbinder unbinder;
    private boolean isViewCreated; // 界面是否已创建完成
    private boolean isVisibleToUser; // 是否对用户可见
    private boolean isDataLoaded; // 数据是否已请求
    View view;
    // 实现具体的数据请求逻辑
    protected abstract void loadData();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        tryLoadData();
    }

    public void tryLoadData() {
        if (isViewCreated && isVisibleToUser && !isDataLoaded) {
            loadData();
            isDataLoaded = true;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    protected abstract void initData();

    protected abstract int getLayoutRes();

    protected abstract void initView(View view);
}
