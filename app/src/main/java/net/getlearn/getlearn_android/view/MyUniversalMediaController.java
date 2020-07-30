package net.getlearn.getlearn_android.view;

import android.content.Context;
import android.util.AttributeSet;

import com.universalvideoview.UniversalMediaController;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/18------更新------
 * 自定义UniversalMediaController实现监听两条bar的隐藏和展示
 */

public class MyUniversalMediaController extends UniversalMediaController {

    public MyUniversalMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyUniversalMediaController(Context context) {
        super(context);
    }

    @Override
    public void show(int timeout) {
        super.show(timeout);
        if (mOnMediaBarStatusChange != null){
            mOnMediaBarStatusChange.onMediaBarStatusChange(true);
        }
    }

    @Override
    public void hide() {
        super.hide();
        if (mOnMediaBarStatusChange != null){
            mOnMediaBarStatusChange.onMediaBarStatusChange(false);
        }
    }

    private OnMediaBarStatusChange mOnMediaBarStatusChange;

    /**
     * 监听TitleBar和视频控制Bar显示和隐藏
     */
   public interface OnMediaBarStatusChange{
        /**
         * @param isShow TitleBar和视频控制Bar是否显示
         */
       void onMediaBarStatusChange(boolean isShow);

    }

    public void setOnMediaBarStatusChange(OnMediaBarStatusChange onMediaBarStatusChange) {
        mOnMediaBarStatusChange = onMediaBarStatusChange;
    }



}
