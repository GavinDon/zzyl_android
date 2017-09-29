package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.utils.LocalImageHolderView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
        private Banner welcomeBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        welcomeBanner = (Banner) findViewById(R.id.welcome_banner);
        welcomeBanner.setOffscreenPageLimit(1);
        List<String> imageLst = new ArrayList<>();
        imageLst.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imageLst.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imageLst.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        welcomeBanner.setImages(imageLst)
                .setImageLoader(new LocalImageHolderView())
                .setBannerAnimation(Transformer.ZoomOutSlide)
                .start();
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
}
