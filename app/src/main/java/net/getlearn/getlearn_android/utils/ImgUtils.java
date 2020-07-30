package net.getlearn.getlearn_android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/13------更新------
 * 图片操作工具类
 */

public class ImgUtils {

    public static Bitmap bm;
    /**
     * 根据图片路径，把图片变成Base64编码的字符串
     * @param path
     * @return
     */
    public static void getBase64ImgByPath(Context context,String path,String[] mImgBase64Arr,int position) {
        LogUtil.e("__img_getBase64","getBase64");
        bm = null;
        StringBuilder result = new StringBuilder("data:image/jpeg;base64,");
        Glide.with(context).asBitmap().load(path).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                LogUtil.e("__img_resource== null",String.valueOf(resource == null));
                if(resource != null) {
                    bm = resource;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    resource.compress(Bitmap.CompressFormat.JPEG, 40, baos);
                    byte[] b = baos.toByteArray();
                    result.append(Base64.encodeToString(b, Base64.DEFAULT));
                    mImgBase64Arr[position] = result.toString();
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        if (bm == null){
            LogUtil.e("__Img_bm == null","bm == null");
//            return null;
        }else {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
//            byte[] b = baos.toByteArray();
//            result.append(Base64.encodeToString(b, Base64.DEFAULT));
//            LogUtil.e("__Img_bm_result",result.toString());
//            return result.toString();
        }
    }

    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        LogUtil.e("__imageToBase64_path",path);
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
            if (path.contains("png")){
                result = "data:image/png;base64," + result;
            }else if (path.contains("jpg")){
                result = "data:image/jpeg;base64," + result;
            }else if (path.contains("jpeg")){
                result = "data:image/jpeg;base64," + result;
            }else if (path.contains("bmp")){
                result = "data:image/bmp;base64," + result;
            }else if (path.contains("gif")){
                result = "data:image/gif;base64," + result;
            }else if (path.contains("pjpeg")){
                result = "data:image/pjpeg;base64," + result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * @param context
     * @param oldPaths 原图片路径集合
     * @param newPaths 压缩之后的图片路径集合
     */
    public static void compressImg(Context context, List<String> oldPaths,List<String> newPaths){
        Luban.with(context)
                .load(oldPaths)                                   // 传人要压缩的图片列表
                .ignoreBy(100)                                  // 忽略不压缩图片的大小，这里表示100k
                .setTargetDir(context.getCacheDir() + "/"+ InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR) // 设置压缩后文件存储位置,与Glide同一个路径
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        //  压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // 压缩成功后调用，返回压缩后的图片文件
                        newPaths.add(file.getPath());
                        LogUtil.e("__img_a_path",file.getAbsolutePath());
                        LogUtil.e("___path",file.getPath());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //  当压缩过程出现问题时调用
                        LogUtil.e("__img_pathcom_e",e.getMessage());
                    }
                }).launch();    //启动压缩
    }

//    FutureTarget<File> future = Glide.with(mContext)
//            .load("url")
//            .downloadOnly(500, 500);
//            try {
//        File cacheFile = future.get();
//        String path = cacheFile.getAbsolutePath();
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    } catch (ExecutionException e) {
//        e.printStackTrace();
//    }


    /** 保存Bitmap到本地 */
    public void saveBitmap(Context context,Bitmap bitmap,String picName) {
        LogUtil.e("__saveBitmap", "保存图片");
        File f = new File(context.getCacheDir() + "/"+ InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR, picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            LogUtil.e("__saveBitmap", "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
