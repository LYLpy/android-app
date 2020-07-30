package net.getlearn.getlearn_android.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.lcw.library.imagepicker.utils.ImageLoader;

import net.getlearn.getlearn_android.MainApp;
import net.getlearn.getlearn_android.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/11------更新------
 * 自定义图片加载
 */

public class GlideLoader implements ImageLoader {

    private RequestOptions mOptions = new RequestOptions()
            .centerCrop()
            .dontAnimate()
            .format(DecodeFormat.PREFER_RGB_565)
            .placeholder(R.mipmap.icon_image_default)
            .error(R.drawable.loading3);

    private RequestOptions mPreOptions = new RequestOptions()
            .skipMemoryCache(true)
            .error(R.drawable.loading3);

    @Override
    public void loadImage(ImageView imageView, String imagePath) {
        //小图加载
        Glide.with(imageView.getContext()).load(imagePath).apply(mOptions).into(imageView);
    }

    @Override
    public void loadPreImage(ImageView imageView, String imagePath) {
        //大图加载
        Glide.with(imageView.getContext()).load(imagePath).apply(mPreOptions).into(imageView);

    }

    @Override
    public void clearMemoryCache() {
        //清理缓存
        Glide.get(MainApp.getContext()).clearMemory();
    }
}
