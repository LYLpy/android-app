package net.getlearn.getlearn_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/9------更新------
 * 学习干货界面加载html图片
 */

public class MImageGetter implements Html.ImageGetter {

    Context mContext;
    TextView container;

    public MImageGetter(TextView text,Context c) {
        this.mContext = c;
        this.container = text;
    }
    public Drawable getDrawable(String source) {
        final LevelListDrawable drawable = new LevelListDrawable();
                Glide.with(mContext).asBitmap().load(source).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    if(resource != null) {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resource);
                    drawable.addLevel(1, 1, bitmapDrawable);
                    drawable.setBounds(0, 0, resource.getWidth(),resource.getHeight());
                    drawable.setLevel(1);
                    container.invalidate();
                    container.setText(container.getText());
                }
                    }
                });
        return drawable;
    }
}
