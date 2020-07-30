package net.getlearn.getlearn_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.getlearn.getlearn_android.activity.GetVerificationCodeActivity;
import net.getlearn.getlearn_android.model.BaseHelper;
import net.getlearn.getlearn_android.model.HttpHelper;
import net.getlearn.getlearn_android.utils.SPUtil;
import net.getlearn.getlearn_android.utils.ToastUtil;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/11------更新------
 * 非ViewPager的懒加载fragment基类
 * getLayoutRes()传入fragment要显示的布局ResId
 * initView(View view) 进行view的绑定，view是onLayoutRes()传入的布局
 *initData()进行数据的访问，如访问网络等，调用到此方法的时候，view都已经初始化过了
 */

public abstract class BaseLazyFragment extends Fragment implements View.OnClickListener, BaseHelper.IHttpCallback{
    protected HttpHelper mHttpHelper = new HttpHelper();
    private boolean isDataLoaded; // 数据是否已请求
    private boolean isHidden = true; // 记录当前fragment是否隐藏

    /**
     * fragment再次可见时，是否重新请求数据，默认为flase则只请求一次数据
     *
     * @return
     */
    protected boolean isNeedReload() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }


    /**
     * 使用show()、hide()控制fragment显示、隐藏时回调该方法
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHidden = hidden;
        if (!hidden) {
            tryLoadData();
        }
    }
    /**
     * show()、hide()场景下，尝试请求数据
     */
    public void tryLoadData() {
        if (!isParentHidden() && (isNeedReload() || !isDataLoaded)) {
            loadData();
            isDataLoaded = true;
            dispatchParentHiddenState();
        }
    }

    /**
     * show()、hide()场景下，当前fragment没隐藏，如果其子fragment也没隐藏，则尝试让子fragment请求数据
     */
    private void dispatchParentHiddenState() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment child : fragments) {
            if (child instanceof BaseLazyFragment && !((BaseLazyFragment) child).isHidden) {
                ((BaseLazyFragment) child).tryLoadData();
            }
        }
    }
    /**
     * show()、hide()场景下，父fragment是否隐藏
     *
     * @return
     */
    private boolean isParentHidden() {
        Fragment fragment = getParentFragment();
        if (fragment == null) {
            return false;
        } else if (fragment instanceof BaseLazyFragment && !((BaseLazyFragment) fragment).isHidden) {
            return false;
        }
        return true;
    }

    @Override
    public void onTokenError(int reqType, String error) {
        SPUtil.clear();
        ToastUtil.showToast("登录信息失效，请重新登录");
        Intent intent = new Intent(getActivity(), GetVerificationCodeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }

    public void setDataLoaded(boolean isLoaded){
        isDataLoaded = isLoaded;
    }

    protected abstract void initData();

    protected abstract int getLayoutRes();

    protected abstract void initView(View view);

    // 实现具体的数据请求逻辑
    protected abstract void loadData();
}
