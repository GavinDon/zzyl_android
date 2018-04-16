package com.nanyixuan.zzyl_andorid.api;

import com.nanyixuan.zzyl_andorid.bean.BaseData;
import com.nanyixuan.zzyl_andorid.bean.BeaconBean;
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
import com.nanyixuan.zzyl_andorid.bean.SaleTicketBean;
import com.nanyixuan.zzyl_andorid.bean.ServiceInfo;
import com.nanyixuan.zzyl_andorid.bean.TourRouteData;
import com.nanyixuan.zzyl_andorid.bean.UpdateAppBean;
import com.nanyixuan.zzyl_andorid.bean.UserInfoData;
import com.nanyixuan.zzyl_andorid.bean.WeatherBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by YixuanNan on 2017/3/23.
 *
 * @author YixuanNan
 */

public interface ApiService {
    String BASEURL = "http://192.168.0.107:8080/";

    @GET("zzwhe/resources/grzxHy/login")
    Observable<BaseData<UserInfoData>> getUserInfo(@Query("phone") String phone);

    @GET("zzwhe/resources/syLb/list")
    Observable<BaseData<PagerData<List<MainLooperData>>>> getMainLooper(@Query("page") int page, @Query("size") int size);

    @GET("zzwhe/resources/tjlx/list")
    Observable<BaseData<PagerData<List<TourRouteData>>>> getTourToute(@Query("page") int page, @Query("size") int size);

    @GET("zzwhe/resources/ybzx/list")
    Observable<BaseData<PagerData<List<NewsData>>>> getNews(@Query("lx") int lx, @Query("page") int page, @Query("size") int size);

    @GET("zzwhe/resources/shp/list")
    Observable<BaseData<PagerData<List<GoodsData>>>> getGoodsList(@Query("id") String id, @Query("page") int page, @Query("size") int size);

    @GET("zzwhe/resources/ybzx/hotList")
    Observable<MainListData> getMainBottomList(@Query("page") int page, @Query("size") int size);

//    @GET("zzwhe/resources/yqdlGcyq/list")
//    Observable<BaseData<PagerData<List<VenueData>>>> getVenueDataList(@Query("lx") int lx, @Query("page") int page, @Query("size") int size);

    @GET("zzwhe/resources/zwbkFl/simpleList")
    Observable<BaseData<List<EncyclopediaData>>> getEncyclopediaTypeListDataList();

    @GET("zzwhe/resources/zwbkFl/simpleList")
    Observable<BaseData<List<EncyclopediaData>>> getEncyclopediaTypeListDataList(@Query("id") String id);

    @GET("zzwhe/resources/zwbkZw/list")
    Observable<BaseData<PagerData<List<EncyclopediaInfoData>>>> getEncyclopediaInfoListDataList(@Query("fjid") String id, @Query("page") int page, @Query("size") int size);

    @GET("zzwhe/resources/fwxx/list")
    Observable<BaseData<PagerData<List<ServiceInfo>>>> getServiceInfoListDataList(@Query("page") int page, @Query("size") int size);

    @GET("zzwhe/resources/GrzxWdzj/list")
    Observable<BaseData<PagerData<List<HistoryInfoData>>>> getHistoryInfoListDataList(@Query("id") String id, @Query("page") int page, @Query("size") int size);

    /**
     * 更新APP
     */
    @GET("zhcomplaint/version/getVersionInfo?type=android")
    Observable<UpdateAppBean> getUpdateInfo();

    /**
     * 下载apk
     *
     * @param url
     * @return
     */
    @GET
    Observable<ResponseBody> downloadApp(@Url String url);

    /**
     * 生成订单
     */
    @FormUrlEncoded
    @POST("zhcomplaint/ticket/createOrder")
    Observable<RespCommon> getOrder(@FieldMap Map<String, String> params);

    /**
     * 票种查询
     */
    @GET("zhcomplaint/ticket/findTicketType")
    Observable<RespCommon> getTicketClass();

    /**
     * 查询订单详情
     */
    @GET("zhcomplaint/ticket/findOrderDetail")
    Observable<RespCommon> getOrderDetail(@Query("orderId") String orderId);

    /**
     * 微信支付宝支付
     */
    @FormUrlEncoded
    @POST("zhcomplaint/wft/pay")
    Observable<RespCommon> getPrepay(@FieldMap Map<String, String> params);

    /**
     * 微信支付宝支付成功
     */
    @FormUrlEncoded
    @POST("zhcomplaint/ticket/paySuccess")
    Observable<RespCommon> getPayResult(@FieldMap Map<String, String> params);


    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @POST("zhcomplaint/sms/sendCode")
    Call<ResponseBody> getSms(@Field("phone") String phone, @Field("content") String smsCode);

    /**
     * 注册帐号
     */
    @FormUrlEncoded
    @POST("zhcomplaint/appUser/userRegister")
    Call<ResponseBody> getRegister(@Field("username") String phone, @Field("password") String smsCode);

    /**
     * 登录
     */
//    @FormUrlEncoded
    @GET("zhcomplaint/appUser/userLogin")
    Observable<LoginBean> getLogin(@Query("username") String phone, @Query("password") String psw);

    /**
     * 找回密码
     */
    @GET("zhcomplaint/appUser/changePassword")
    Call<ResponseBody> getFoegetPsw(@Query("username") String phone, @Query("password") String psw);

    /**
     * 长按下载webview 图片
     */
    @GET
    Observable<ResponseBody> getDownLoadImage(@Url String url);

    /**
     * 生成退单信息
     */
    @FormUrlEncoded
    @POST("zhcomplaint/ticket/createRefund")
    Observable<RespCommon> getCreateRefund(@FieldMap Map<String, String> map);

    /**
     * 退票
     */
    @FormUrlEncoded
    @POST("zhcomplaint/wft/refund")
    Observable<RespCommon> getRefund(@FieldMap Map<String, String> map);

    /**
     * 退票完成
     */
    @FormUrlEncoded
    @POST("zhcomplaint/ticket/refundDone")
    Observable<RespCommon> getRefundDone(@FieldMap Map<String, String> map);

    /**
     * 查看所有订单
     *
     * @param userId 用户id
     */
    @GET("zhcomplaint/ticket/findOrder")
    Observable<RespCommon> getFindOrder(@Query("userId") String userId);

    /**
     * 根据身份证查询有无购买门票
     *
     * @param identityCode 用户身份证号
     */
    @GET("zhcomplaint/ticket/findTicketCountByID")
    Observable<ResponseBody> getFindTicketCountByID(@Query("identityCode") String identityCode);

    /**
     * mob天气
     *
     * @param city     城市
     * @param province 省
     */
    @GET("http://apicloud.mob.com/v1/weather/query")
    Observable<WeatherBean> getWeather(@Query("key") String key, @Query("city") String city, @Query("province") String province);

    @GET("zhcomplaint/version/getWeatherinfo")
    Observable<WeatherBean> getWeatherInfo();

    /**
     * 绑定车牌号列表
     */
    @GET("zhcomplaint/car/carNumList")
    Observable<RespCommon> getcarNumlist(@Query("userId") String userId);

    /**
     * 添加车牌号
     */
    @GET("zhcomplaint/car/addCarNum")
    Observable<RespCommon> getAddcarNum(@Query("userId") String userID, @Query("carNum") String carNum, @Query("isCheck") String isCheck);

    /**
     * 修改默认选中车牌号
     */
    @GET("zhcomplaint/car/changeDefaultCarNum")
    Observable<RespCommon> getchangeDefaultCarNum(@Query("oldId") String oldId, @Query("newId") String newId);

    /**
     * 删除车牌号
     */
    @GET( "zhcomplaint/car/deleteCarNum")
    Observable<RespCommon> getdeleteCarNum(@Query("id") String id);

    /**
     * 获取token
     */
    @GET("zhcomplaint/car/getToken")
    Observable<RespCommon> getToken();

    /**
     * 获取停车场信息
     */
    @GET( "zhcomplaint/car/findParks")
    Observable<RespCommon> getFindParks();

    /**
     * 车辆信息查询
     */
    @GET("zhcomplaint/car/findCarRecord")
    Observable<RespCommon> getFindCarRecord(@Query("carnum") String carnum, @Query("starttime") String starttime, @Query("endtime") String endtime, @Query("token") String token);

    /**
     * 停车场预约
     */
    @GET( "zhcomplaint/car/bookPark")
    Observable<RespCommon> getBookPark(@QueryMap Map<String, String> map);

    /**
     * 停车场缴费验证
     */
    @GET("zhcomplaint/car/payCheck")
    Observable<RespCommon> getPayCheck( @Query("carnum") String carNum, @Query("userid") String userId);

    /**
     * 停车场支付成功后通知
     */
    @GET("zhcomplaint/car/carPaid")
    Observable<RespCommon> getCarPaid(@QueryMap Map<String, String> map);

    /**
     * 预约成功接口
     */
    @FormUrlEncoded
    @POST("zhcomplaint/car/bookSuccess")
    Observable<RespCommon> getBookSuccess(@FieldMap Map<String, String> map);

    /**
     * 根据ID 获取Beacon信息
     */
    @GET("zhcomplaint/version/getCGInfo")
    Observable<BeaconBean> getBeaconInfo(@Query("code") String code);

    /**
     * 获取票样信息
     */
    @POST("zhcomplaint/ticketInfo/findTicket?type=android")
    Observable<SaleTicketBean> getTicketClassInfo();

    /**
     * 取消订单
     */
    @GET("zhcomplaint/ticket/cancelOrder")
    Observable<RespCommon> getCancleOrder(@Query("orderId") String orderID);
}