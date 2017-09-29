package com.nanyixuan.zzyl_andorid.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiObserver;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.bean.BaseData;
import com.nanyixuan.zzyl_andorid.bean.EncyclopediaInfoData;
import com.nanyixuan.zzyl_andorid.bean.PagerData;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EncyclopediaInfoListActivity extends Activity {

    @BindView(R.id.btn_showSideMenu)
    ImageButton btnShowSideMenu;
    @BindView(R.id.show_QR)
    Button showQR;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.listView)
    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia_list);
        ButterKnife.bind(this);
        String id = getIntent().getBundleExtra("data").getString("id");
        ApiManager.getEncyclopediaInfoTypeListDataList(id, 1, 10, EncyclopediaInfoListActivity.this, new ApiObserver<BaseData<PagerData<List<EncyclopediaInfoData>>>>() {

            @Override
            public void onSuccess(final BaseData<PagerData<List<EncyclopediaInfoData>>> tBaseData) {
                listView.setAdapter(new CommonAdapter<EncyclopediaInfoData>(EncyclopediaInfoListActivity.this,R.layout.item_news,tBaseData.getData().getContent()) {
                    @Override
                    protected void convert(ViewHolder viewHolder, EncyclopediaInfoData item, int position) {
                        SimpleDraweeView simpleDraweeView = viewHolder.getView(R.id.simpleDraweeView);
                        simpleDraweeView.setImageURI(Constant.URL.BASEURL_HTML + item.getTp());
                        viewHolder.setText(R.id.tv_title, item.getMc());
                        viewHolder.setText(R.id.tv_content, "");
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", Constant.URL.encyclopediaInfo + "?id=" + tBaseData.getData().getContent().get(i).getId());
                        Intent intent = new Intent(EncyclopediaInfoListActivity.this, UrlContentActivity.class);
                        intent.putExtra("data", bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFail(Throwable t) {

            }
        });




    }

    @OnClick(R.id.btn_showSideMenu)
    public void onViewClicked() {
        finish();
    }
}
