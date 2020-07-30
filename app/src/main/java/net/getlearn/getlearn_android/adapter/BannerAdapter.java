package net.getlearn.getlearn_android.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/1------更新------
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> imageViewList;//要展示图片的ImageView
    public BannerAdapter(List<ImageView> viewList){
        imageViewList = viewList;
    }
    @Override
    public int getCount() {
        //当轮播图只有一个的时候也能正常显示
        if (imageViewList.size() ==1){
            return imageViewList.size();
        }else {
            return Integer.MAX_VALUE;
        }
    }

    // 3. 指定复用的判断逻辑, 固定写法
    @Override
    public boolean isViewFromObject(View view, Object object) {
//			System.out.println("isViewFromObject: "+(viewStart == object));
        // 当划到新的条目, 又返回来, view是否可以被复用.
        // 返回判断规则
        return view == object;
    }

    // 1. 返回要显示的条目内容, 创建条目
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//            System.out.println("instantiateItem初始化: " + position);
        // container: 容器: ViewPager
        // position: 当前要显示条目的位置 0 -> 4
//			newPosition = position % 5
        int newPosition = position % imageViewList.size();
        ImageView imageView = imageViewList.get(newPosition);
        View view = imageViewList.get(newPosition);
        if (view.getParent() == container) {
            container.removeView(view);
        }
        // a. 把View对象添加到container中
        container.addView(imageView);
        // b. 把View对象返回给框架, 适配器
        return imageView; // 必须重写, 否则报异常
    }

    // 2. 销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // object 要销毁的对象
//            System.out.println("destroyItem销毁: " + position);
        container.removeView((View) object);
    }

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public void setImageViewList(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }
    public void removeData(){
        if (imageViewList!=null){
            imageViewList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }
}
