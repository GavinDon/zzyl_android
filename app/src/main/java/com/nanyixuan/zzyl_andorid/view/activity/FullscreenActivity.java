package com.nanyixuan.zzyl_andorid.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.nanyixuan.zzyl_andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FullscreenActivity extends Activity {
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            show();
        }
    };
    @BindView(R.id.iv_bg)
    ImageView ivBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);
        handler.postDelayed(runnable,3000);
    }

    @OnClick(R.id.btn_show)
    public void onViewClicked() {
        handler.removeCallbacks(runnable);
        show();
    }

    private void show(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

}