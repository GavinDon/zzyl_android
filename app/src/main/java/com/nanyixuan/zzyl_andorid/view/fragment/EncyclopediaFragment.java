package com.nanyixuan.zzyl_andorid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.FragmentUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.base.BaseFragment;
import com.nanyixuan.zzyl_andorid.bean.EncyclopediaData;
import com.nanyixuan.zzyl_andorid.presenter.EncyclopediaPresenter;
import com.nanyixuan.zzyl_andorid.view.activity.EncyclopediaListActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EncyclopediaFragment extends BaseFragment implements EncyclopediaFragmentController {
    public static final String TAG = "EncyclopediaFragment";
    @BindView(R.id.btn_showSideMenu)
    ImageButton btnShowSideMenu;
    @BindView(R.id.gridView)
    GridView gridView;
    CommonAdapter<EncyclopediaData> commonAdapter;
    EncyclopediaPresenter encyclopediaPresenter;

    public static EncyclopediaFragment newInstance() {
        Bundle args = new Bundle();
        EncyclopediaFragment fragment = new EncyclopediaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        encyclopediaPresenter = new EncyclopediaPresenter(getContext(), this);
        encyclopediaPresenter.init();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_encyclopedia, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    public int setLayout() {
        return R.layout.fragment_encyclopedia;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {

    }


    @Override
    public void setEncyclopediaList(final List<EncyclopediaData> encyclopediaList) {
        commonAdapter = new CommonAdapter<EncyclopediaData>(getActivity(), R.layout.item_gridview_encyclopedia, encyclopediaList) {
            @Override
            protected void convert(ViewHolder viewHolder, EncyclopediaData item, int position) {
                SimpleDraweeView simpleDraweeView = viewHolder.getView(R.id.simpleDraweeView);
                TextView textView = viewHolder.getView(R.id.textView);
                simpleDraweeView.setImageURI(Constant.URL.BASEURL_HTML + item.getTp());
                textView.setText(item.getFlmc());
            }
        };
        gridView.setAdapter(commonAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("id", encyclopediaList.get(i).getId());
                Intent intent = new Intent(getActivity(), EncyclopediaListActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.btn_showSideMenu)
    public void onViewClicked() {
        FragmentUtils.popFragment(getFragmentManager());
    }
}