package net.getlearn.getlearn_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.getlearn.getlearn_android.activity.GetVerificationCodeActivity;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.HttpHelper;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener, BaseHelper.IHttpCallback {
    protected HttpHelper mHttpHelper = new HttpHelper();
    private Unbinder unbinder;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTokenError(int reqType, String error) {
        SPUtil.clear();
        ToastUtil.showToast("登录信息失效，请重新登录");
        Intent intent = new Intent(getActivity(), GetVerificationCodeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }
    protected abstract void initData();

    protected abstract int getLayoutRes();

    protected abstract void initView(View view);
}
