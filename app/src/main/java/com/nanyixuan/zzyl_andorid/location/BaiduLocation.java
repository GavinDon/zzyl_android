/*
package com.nanyixuan.zzyl_andorid.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.nanyixuan.zzyl_andorid.R;

*/
/**
 * description: 基础地图
 * Created by liNan on 2017/8/14 16:04
 *//*


public class BaiduLocation {
    private MapView bMapView;
    private BaiduMap mBaiduMap;
    private Context mContext;
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListener myListener = new MyLocationListener();
    boolean isFirstLoc = true; // 是否首次定位

    public BaiduLocation(Context context) {
        mContext = context;
    }

    public BaiduLocation(Context mContext, MapView bMapView) {
        this.bMapView = bMapView;
        this.mContext = mContext;
        bMapView.removeViewAt(1);//移除百度地图Logo
        bMapView.showScaleControl(false);
        mBaiduMap = bMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setTrafficEnabled(true); //开启交通地图
        getLocation();
    }

    */
/**
     * 定位一系列的初始化
     *//*

    private void getLocation() {
        // 开启定位图层(显示位置的图形)
        mBaiduMap.setIndoorEnable(true);
//        mBaiduMap.setOnMapClickListener(listener);

        // 定位初始化
        mLocClient = new LocationClient(mContext);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(5000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        mLocClient.setLocOption(option);
        option.SetIgnoreCacheException(false);
        mLocClient.start();
    }
    OverlayOptions option;
    */
/**
     * 根据搜索的结果显示位置
     *//*

    public void resetLocation(LatLng latLng) {
        if (mBaiduMap!=null){
            mBaiduMap.clear();
        }
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_gcoding);
        //构建MarkerOption，用于在地图上添加Marker
         option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap);
        //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);

        MapStatus.Builder builder = new MapStatus
                .Builder()
                .target(latLng) //要移动的点
                .zoom(16.0f);  //放大地图到18倍
//      mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build())); 使用animateMapstatus  地图无法移动
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

    }

    */
/**
     * 定位SDK监听函数
     *//*

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || bMapView == null) {
                return;
            }
            mBaiduMap.setMyLocationEnabled(true);
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            //设置位置
            mBaiduMap.setMyLocationData(locData);

            //设置当前位置的图标
            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.icon_gcoding);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
            mBaiduMap.setMyLocationEnabled(false);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus
                        .Builder()
                        .target(ll) //要移动的点
                        .zoom(16.0f);  //放大地图到18倍
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                ((onRecordLocation) mContext).onRecord(location.getAddrStr());

                mBaiduLocation.onLocation(location);
            }
        }
    }

    BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            double latitude = latLng.latitude;
            double longitude = latLng.longitude;
            mBaiduMap.clear();

            LatLng point = new LatLng(latitude, longitude);
            MarkerOptions options = new MarkerOptions().position(point)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding)).animateType(MarkerOptions.MarkerAnimateType.grow);
            mBaiduMap.addOverlay(options);
            final GeoCoder geoCoder = GeoCoder.newInstance();
            //设置反地理编码位置坐标
            ReverseGeoCodeOption op = new ReverseGeoCodeOption();
            op.location(latLng);
            //发起反地理编码请求(经纬度->地址信息)
            geoCoder.reverseGeoCode(op);
            //地理编码：把地名转换成位置信息(经纬度)
            //地理反编码：把位置信息转换成文字
            geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                    String addr = geoCodeResult.getAddress();
                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                }
            });

        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            return false;
        }
    };

    public interface BaiduGetLocation {
        void onLocation(BDLocation location);
    }

    public BaiduGetLocation mBaiduLocation;

    public void setOnBaiduLocation(BaiduGetLocation location) {
        this.mBaiduLocation = location;
    }

}
*/
