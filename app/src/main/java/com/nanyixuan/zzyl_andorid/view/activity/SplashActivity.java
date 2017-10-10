package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * 引导页
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Banner welcomeBanner;
    private TextView tvEnter;
    private List<Integer> list; //图片集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        welcomeBanner = (Banner) findViewById(R.id.welcome_banner);
        tvEnter = (TextView) findViewById(R.id.tv_enter);
        //判断是否是第一次进入
        boolean hasEnter = SPUtils.getInstance().getBoolean("hasEnter",false);
        if (hasEnter) {
            Intent i = new Intent(this, FullscreenActivity.class);
            startActivity(i);
        } else {
            initSplash();
            tvEnter.setOnClickListener(this);
        }

    }

    /**
     * 引导页的初始化
     */
    private void initSplash() {
        list = new ArrayList<>();
        list.add(R.mipmap.welcome1);
        list.add(R.mipmap.welcome2);
        list.add(R.mipmap.welcome3);
        welcomeBanner.setImages(list)
                .setImageLoader(new LocalImageHolderView2())
                .start();
        welcomeBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == list.size()) {
                    tvEnter.setVisibility(View.VISIBLE);
                } else {
                    tvEnter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        welcomeBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        welcomeBanner.stopAutoPlay();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_enter) {
            SPUtils.getInstance().put("hasEnter", true);
            Intent intent = new Intent(this, FullscreenActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 使用fresco加载图片
     */
    class LocalImageHolderView2 extends ImageLoader {

        private SimpleDraweeView imageView;

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            imageView.setImageResource((Integer) path);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            imageView = (SimpleDraweeView) View.inflate(context, R.layout.simple_drawee_view, null);
            return imageView;
        }
    }
}
