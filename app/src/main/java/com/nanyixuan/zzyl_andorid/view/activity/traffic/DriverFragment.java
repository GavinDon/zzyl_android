package com.nanyixuan.zzyl_andorid.view.activity.traffic;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.amap.api.maps.AMap;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.base.BaseFragment;
import com.nanyixuan.zzyl_andorid.utils.AmapTTSController;
import com.nanyixuan.zzyl_andorid.utils.MyTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description: 自驾
 * Created by liNan on 2017/7/31 16:20
 */

public class DriverFragment extends BaseFragment implements  INaviInfoCallback {
    @BindView(R.id.search_lv)
    ListView searchLv; //搜索列表
    @BindView(R.id.searchview)
    SearchView mSearchView;
    @BindView(R.id.replace_edittext)
    EditText mEditText;
//    private BaiduLocation baiduLocation;
    private ArrayAdapter<String> searchAdapter;
    private List<String> searchList = new ArrayList<>();
    private String mCity = "郑州";
    private AmapTTSController amapTTSController;

    @BindView(R.id.gaode_mapview)
    com.amap.api.maps.MapView gaodeMapView;


    @Override
    public int setLayout() {
        return R.layout.fragment_drive;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {
//        startMap();
//        mSearchView.setOnQueryTextListener(this);
        searchAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, searchList);
        searchLv.setAdapter(searchAdapter);
        initNav();
        amapTTSController = AmapTTSController.getInstance(this.getActivity().getApplicationContext());
        amapTTSController.init();
        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AmapNaviPage.getInstance().showRouteActivity(DriverFragment.this.getActivity(), new AmapNaviParams(null), null);
                    mSearchView.requestFocus();//切换焦点
                }
            }
        });

        gaodeMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        AMap aMap = null;
        if (aMap == null) {
            aMap = gaodeMapView.getMap();
            aMap.setMapType(AMap.MAP_TYPE_NAVI);//导航地图模式
            aMap.setMyLocationEnabled(true);
        }
    }


    /**
     * 展示基础地图
     */
//    private void startMap() {
//        RxPermissions permissions = new RxPermissions(this.getActivity());
//        permissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            baiduLocation = new BaiduLocation(DriverFragment.this.getActivity(), mMapView);
//                            baiduLocation.setOnBaiduLocation(DriverFragment.this);
//
//                        } else {
//                            ToastUtils.showShort("您拒绝了授予权限");
//                        }
//                    }
//                });
//
//    }

    /**
     * 定位结果
     *
     * @param location
     */
//    @Override
//    public void onLocation(BDLocation location) {
//        mCity = location.getCity();
//    }

//    /**
//     * poi 在线建议检索
//     */
//    private void startSearch(String keyWord) {
//        mSuggestionSearch = SuggestionSearch.newInstance();
//        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
//        // 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
//        mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
//                .keyword(keyWord)
//                .city(mCity));
//    }

//    /**
//     * poi 回调
//     */
//    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
//        public void onGetSuggestionResult(SuggestionResult res) {
//            if (res == null || res.getAllSuggestions() == null) {
//                return;
//            }
//            //获取在线建议检索结果
//            suggestList.clear();
//            searchList.clear();
//            suggestList = res.getAllSuggestions();
//            for (SuggestionResult.SuggestionInfo s : suggestList) {
//                searchList.add(s.key);
//            }
//            searchAdapter.notifyDataSetChanged();
//
//        }
//    };

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        // 当点击搜索按钮时触发该方法
//        AmapNaviPage.getInstance().showRouteActivity(this.getActivity(), new AmapNaviParams(null), null);
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        // 当文本内容变化时
//        if (TextUtils.isEmpty(newText)) {
//            clearSearch(); //清除搜索结果
//        } else {
//            startSearch(newText);
//        }
//        return true;
//    }

//    @OnItemClick(R.id.search_lv)
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        LatLng latLng = suggestList.get(position).pt;
////        mSearchView.setQuery(suggestList.get(position).key,false); //设置搜索框上显示选中的地点
//        if (null == latLng) {
//            //poi 推荐的地方 latlng有可能为null
//            ToastUtils.showShort("对不起,无法确定位置");
//        } else {
////            baiduLocation.resetLocation(latLng);
//            clearSearch();
//        }
//    }

    /**
     * 清除搜索结果
     */
    private void clearSearch() {
        searchList.clear();
        searchLv.animate();
        searchAdapter.notifyDataSetChanged();
    }

    private void initNav() {
        if (MyTools.isSDCardExists()) {
            File dir = new File(MyTools.getSDPath() + Constant.PATH_NAVI);
            if (dir.exists()) {
            } else {
                dir.mkdir();
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
//        mMapView.onPause();
        gaodeMapView.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
//        mMapView.onResume();
        gaodeMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
//        MapView.setMapCustomEnable(false);
        gaodeMapView.onDestroy();
//        mMapView.onDestroy();
        amapTTSController.destroy();
//        if (null != mSuggestionSearch)
//            mSuggestionSearch.destroy();
    }


    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {
        amapTTSController.onGetNavigationText(s);

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {
    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {
        amapTTSController.stopSpeaking();
    }
}
