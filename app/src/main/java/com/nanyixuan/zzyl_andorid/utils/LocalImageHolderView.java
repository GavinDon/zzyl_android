package com.nanyixuan.zzyl_andorid.utils;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.youth.banner.loader.ImageLoader;

/**
 * description: 图片加载器
 * Created by liNan on 2017/7/31 15:51
 */

public class LocalImageHolderView extends ImageLoader {

    private SimpleDraweeView imageView;

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        imageView = (SimpleDraweeView) View.inflate(context, R.layout.simple_drawee_view, null);
        return imageView;
    }
}
