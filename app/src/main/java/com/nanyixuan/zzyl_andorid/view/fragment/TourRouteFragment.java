package com.nanyixuan.zzyl_andorid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.TourRouteData;
import com.nanyixuan.zzyl_andorid.presenter.TourRouteFragmentPresenter;
import com.nanyixuan.zzyl_andorid.view.activity.UrlContentActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * 园区导览
 */
public class TourRouteFragment extends BaseActivity implements TourRouteFragmentController {
    public static final String TAG = "TourRouteFragment";
    TourRouteFragmentPresenter tourRouteFragmentPresenter;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.listView)
    ListView listView;

//    public static TourRouteFragment newInstance() {
//        Bundle args = new Bundle();
//        TourRouteFragment fragment = new TourRouteFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.fragment_tour_route, container, false);
//        ButterKnife.bind(this, view);
//        tourRouteFragmentPresenter = new TourRouteFragmentPresenter(this, getActivity());
//        tourRouteFragmentPresenter.init();
//        return view;
//    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("线路推荐");
        tourRouteFragmentPresenter = new TourRouteFragmentPresenter(this, this);
        tourRouteFragmentPresenter.init();
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_tour_route;
    }
//
//    @Override
//    public void initViews(View self, Bundle savedInstanceState) {
//        tourRouteFragmentPresenter = new TourRouteFragmentPresenter(this, this);
//        tourRouteFragmentPresenter.init();
//    }

    @Override
    public void setTourRouteList(final List<TourRouteData> tourRouteList) {
        listView.setAdapter(new CommonAdapter<TourRouteData>(this, R.layout.item_tour_route, tourRouteList) {
            @Override
            protected void convert(ViewHolder viewHolder, TourRouteData item, int position) {
                SimpleDraweeView simpleDraweeView = viewHolder.getView(R.id.simpleDraweeView);
                simpleDraweeView.setImageURI(Constant.URL.BASEURL_HTML + item.getTp());
                TextView textView = viewHolder.getView(R.id.textView);
                textView.setText(item.getMc());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constant.URL.tourRaouteInfo + "?id=" + tourRouteList.get(i).getId());
                Intent intent = new Intent(TourRouteFragment.this, UrlContentActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);

            }
        });
    }
//
//    @OnClick(R.id.btn_showSideMenu)
//    public void onViewClicked() {
//        FragmentUtils.popFragment(getFragmentManager());
//    }

}