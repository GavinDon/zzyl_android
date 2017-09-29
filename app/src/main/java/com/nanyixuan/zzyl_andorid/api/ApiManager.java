package com.nanyixuan.zzyl_andorid.api;

import android.content.Context;

import com.nanyixuan.zzyl_andorid.bean.BaseData;
import com.nanyixuan.zzyl_andorid.bean.EncyclopediaData;
import com.nanyixuan.zzyl_andorid.bean.EncyclopediaInfoData;
import com.nanyixuan.zzyl_andorid.bean.GoodsData;
import com.nanyixuan.zzyl_andorid.bean.HistoryInfoData;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.MainListData;
import com.nanyixuan.zzyl_andorid.bean.MainLooperData;
import com.nanyixuan.zzyl_andorid.bean.NewsData;
import com.nanyixuan.zzyl_andorid.bean.PagerData;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.bean.ServiceInfo;
import com.nanyixuan.zzyl_andorid.bean.TourRouteData;
import com.nanyixuan.zzyl_andorid.bean.UserInfoData;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YixuanNan on 2017/3/23.
 *
 * @author YixuanNan
 *         网络请求管理类。
 */

public class ApiManager {
    public static final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(Constant.URL.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private static final ApiService apiService = mRetrofit.create(ApiService.class);


    /**
     * 获取用户信息。
     *
     * @param phone       用户手机号。
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getUserInfo(String phone, Context context, ApiObserver<BaseData<UserInfoData>> apiObserver) {
        Observable<BaseData<UserInfoData>> observable =
                apiService.getUserInfo(phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver
                .setContext(context);
        apiObserver
                .setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取场馆详情。
     *
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     * @param id          场馆id。
     * @param userId      用户id。
     */

/*    public static void getVenueInfo(String id, String userId, Context context, ApiObserver<BaseData<VenueData>> apiObserver) {
        Observable<BaseData<VenueData>> observable =
                apiService.getVenueInfo(id, userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver
                .setContext(context);
        apiObserver
                .setObservable(observable);
        observable.subscribe(apiObserver);
    }*/

    /**
     * 获取首页轮播图数据。
     *
     * @param page        页数。
     * @param size        每页多少条数据。
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getMainLooper(int page, int size, Context context, ApiObserver<BaseData<PagerData<List<MainLooperData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<MainLooperData>>>> observable =
                apiService.getMainLooper(page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver
                .setContext(context);
        apiObserver
                .setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取推荐路线数据。
     *
     * @param page        页数。
     * @param size        每页多少条数据。
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getTourRoute(int page, int size, Context context, ApiObserver<BaseData<PagerData<List<TourRouteData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<TourRouteData>>>> observable =
                apiService.getTourToute(page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver
                .setContext(context);
        apiObserver
                .setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取园博资讯数据。
     *
     * @param lx          类型 （0：新闻资讯 1：园区活动 2：热门推荐）
     * @param page        页数。
     * @param size        每页多少条数据。
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getNews(int lx, int page, int size, Context context, ApiObserver<BaseData<PagerData<List<NewsData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<NewsData>>>> observable =
                apiService.getNews(lx, page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取商品列表数据。
     *
     * @param id          商品分类id。
     * @param page        页数。
     * @param size        每页多少条数据。
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getGoodsList(String id, int page, int size, Context context, ApiObserver<BaseData<PagerData<List<GoodsData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<GoodsData>>>> observable =
                apiService.getGoodsList(id, page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取首页底部列表数据。
     *
     * @param page        页数。
     * @param size        每页多少条数据。
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getMainBottomList(int page, int size, Context context, ApiObserver<BaseData<PagerData<List<MainListData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<MainListData>>>> observable =
                apiService.getMainBottomList(page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取场馆、展园等列表列表数据。
     *
     * @param lx          类型（0： 场馆） （ 1：展园） （ 2：拥堵情况）
     * @param page        页数。
     * @param size        每页多少条数据。
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
  /*  public static void getVenueDataList(int lx, int page, int size, Context context, ApiObserver<BaseData<PagerData<List<VenueData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<VenueData>>>> observable =
                apiService.getVenueDataList(lx, page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }*/

    /**
     * 获取植物百科分类。默认返回第一级分类。
     *
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getEncyclopediaTypeListDataList(Context context, ApiObserver<BaseData<List<EncyclopediaData>>> apiObserver) {
        Observable<BaseData<List<EncyclopediaData>>> observable =
                apiService.getEncyclopediaTypeListDataList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取植物百科分类。
     *
     * @param id          需要取得的植物id
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getEncyclopediaTypeListDataList(String id, Context context, ApiObserver<BaseData<List<EncyclopediaData>>> apiObserver) {
        Observable<BaseData<List<EncyclopediaData>>> observable =
                apiService.getEncyclopediaTypeListDataList(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 获取植物百科详情列表。
     *
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getEncyclopediaInfoTypeListDataList(String fjid, int page, int size, Context context, ApiObserver<BaseData<PagerData<List<EncyclopediaInfoData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<EncyclopediaInfoData>>>> observable =
                apiService.getEncyclopediaInfoListDataList(fjid, page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 服务信息列表。
     *
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getServiceInfoListDataList(int page, int size, Context context, ApiObserver<BaseData<PagerData<List<ServiceInfo>>>> apiObserver) {
        Observable<BaseData<PagerData<List<ServiceInfo>>>> observable =
                apiService.getServiceInfoListDataList(page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    /**
     * 足迹列表。
     *
     * @param apiObserver 网络监听。
     * @param context     Context你懂得。
     */
    public static void getHistoryInfoListDataList(String id, int page, int size, Context context, ApiObserver<BaseData<PagerData<List<HistoryInfoData>>>> apiObserver) {
        Observable<BaseData<PagerData<List<HistoryInfoData>>>> observable =
                apiService.getHistoryInfoListDataList(id, page, size)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);

    }

    /**
     * 创建订单
     */
    public static void getPayData(HashMap<String, String> map, Context context, ApiObserver<RespCommon> apiObserver) {
        Observable<RespCommon> observable = apiService.getOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);

    }

    /**
     * 获取预支付
     */
    public static void getPrepayData(Map<String, String> map, Context context, ApiObserver<RespCommon> apiObserver) {
        Observable<RespCommon> observable = apiService.getPrepay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setShowDialog(false);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }
//
//    /**
//     * 登录
//     */
//    public static void getLogin(String username, String psw, Context context, ApiObserver<LoginBean> apiObserver) {
//        Observable<LoginBean> observable = apiService.getLogin(username, psw)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        apiObserver.setContext(context);
//        apiObserver.setObservable(observable);
//        observable.subscribe(apiObserver);
//    }

    /**
     * 支付结果
     */
    public static void getPayResult(Map<String, String> map, Context context, ApiObserver<RespCommon> apiObserver) {
        Observable<RespCommon> observable = apiService.getPayResult(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        apiObserver.setContext(context);
        apiObserver.setObservable(observable);
        observable.subscribe(apiObserver);
    }

    @SuppressWarnings("unchecked")
    public static void getCommonResult( String funName,Map<String,String>params,  Context context,  ApiObserver<LoginBean> apiObserver) {
                Class<?> serviceClass = ApiService.class;
                try {
                    Method method = serviceClass.getDeclaredMethod(funName,Map.class);
                    Observable<LoginBean> observable = (Observable<LoginBean>) method.invoke(apiService, params);
                    observable.observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io());
                    apiObserver.setContext(context);
                    apiObserver.setObservable(observable);
                    observable.subscribe(apiObserver);

                } catch (Exception e) {
                    e.printStackTrace();
                }

    }

}