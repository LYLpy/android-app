package net.getlearn.getlearn_android.anim;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/17------更新------
 * View高度动画的封装
 */

public class HeightAnim {

    ValueAnimator animator = null;
    public HeightAnim(final View view, int start, int end){
        animator = ValueAnimator.ofInt(start, end);
        //监听值的变化过程
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //1.获取动画的值
                int animatedValue = (int) animation.getAnimatedValue();
                //2.将值设置给高度
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = animatedValue;
                view.setLayoutParams(params);

                if(listener!=null){
                    listener.onUpdate(animatedValue);
                }
            }
        });
    }

    /**
     * 开启动画
     * @param duration
     */
    public void start(int duration){
        animator.setDuration(duration).start();
    }

    HeightAnimListener listener;
    public void setHeightAnimListener(HeightAnimListener listener){
        this.listener = listener;
    }
    public interface HeightAnimListener{
        void onUpdate(int value);
    }
}

