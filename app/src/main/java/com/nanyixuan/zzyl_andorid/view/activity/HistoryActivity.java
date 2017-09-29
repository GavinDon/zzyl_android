/*
package com.nanyixuan.zzyl_andorid.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiObserver;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.UserManager;
import com.nanyixuan.zzyl_andorid.bean.BaseData;
import com.nanyixuan.zzyl_andorid.bean.HistoryInfoData;
import com.nanyixuan.zzyl_andorid.bean.PagerData;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryActivity extends Activity {

    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        String userId = UserManager.getInstance().getUserInfo().getId();
        Log.i("nanyixuan","userid = " + userId);
        ApiManager.getHistoryInfoListDataList(userId, 1, 10, this, new ApiObserver<BaseData<PagerData<List<HistoryInfoData>>>>() {
            @Override
            public void onSuccess(final BaseData<PagerData<List<HistoryInfoData>>> tBaseData) {
                listView.setAdapter(new CommonAdapter<HistoryInfoData>(HistoryActivity.this,R.layout.item_news,tBaseData.getData().getContent()) {
                    @Override
                    protected void convert(ViewHolder viewHolder, HistoryInfoData item, int position) {
                        SimpleDraweeView simpleDraweeView = viewHolder.getView(R.id.simpleDraweeView);
                        simpleDraweeView.setImageURI(Constant.URL.BASEURL + item.getTp());
                        Log.i("nanyixuan",Constant.URL.
                                BASEURL+ item.getTp());
                        viewHolder.setText(R.id.tv_title, item.getTitle());
                        viewHolder.setText(R.id.tv_content, "");
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String userid = "";
                        if (UserManager.getInstance().getUserInfo() != null){
                            userid = UserManager.getInstance().getUserInfo().getId();
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("url", Constant.URL.BASEURL + tBaseData.getData().getContent().get(position).getUrl());
                        Intent intent = new Intent(HistoryActivity.this, UrlContentActivity.class);
                        intent.putExtra("data",bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }

    @OnClick({R.id.btn_showSideMenu, R.id.show_QR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_showSideMenu:
                finish();
                break;
            case R.id.show_QR:
                break;
        }
    }
}*/
