package com.nanyixuan.zzyl_andorid.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nanyixuan.zzyl_andorid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplaintActivity extends Activity {

    @BindView(R.id.et_content)
    EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_showSideMenu, R.id.show_QR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_showSideMenu:
                finish();
                break;
            case R.id.show_QR:
                //在这里处理提交。

                break;
        }
    }
}
