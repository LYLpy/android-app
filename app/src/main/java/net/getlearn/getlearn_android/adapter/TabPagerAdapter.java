package net.getlearn.getlearn_android.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    String[] titles = null;
    private List<Fragment> fragments = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, List<Fragment> fragments, Context context, String[] titles) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }
    /**
     * 添加一页
     */
    public void addPage(Fragment f){
        fragments.add(f);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 返回每个页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

