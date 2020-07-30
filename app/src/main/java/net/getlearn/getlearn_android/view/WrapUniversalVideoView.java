package net.getlearn.getlearn_android.view;

import android.content.Context;
import android.util.AttributeSet;

import com.universalvideoview.UniversalVideoView;

import net.getlearn.getlearn_android.utils.LogUtil;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/12------更新------
 * 适配全面屏的UniversalVideoView
 */

public class WrapUniversalVideoView extends UniversalVideoView {
    public WrapUniversalVideoView(Context context) {
        super(context);
    }

    public WrapUniversalVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapUniversalVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //此处设置的默认值可随意,因为getDefaultSize中的size是有值的
        int width = getDefaultSize(0,widthMeasureSpec);
        int height = getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,height);
        LogUtil.e("","");
        System.out.println("======onMeasure===width==="+width);
        System.out.println("======onMeasure===height==="+height);
    }

}
