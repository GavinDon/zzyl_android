package com.nanyixuan.zzyl_andorid.api;

/**
 * Created by YixuanNan on 2017/3/23.
 *
 * @author YixuanNan
 */

public class Constant {

    /**
     * 文件存储路径
     */
    public final static String PATH_APP_BASE = "/zzyl";
    // 崩溃异常信息文件路径
    public final static String PATH_CRASH = PATH_APP_BASE + "/beat/crash/";
    //导航
    public final static String PATH_NAVI = PATH_APP_BASE + "/beat/navi/";
    //下载apk的路径　
    public final static String PATH_APK = PATH_APP_BASE + "/beat/apk/";
    public final static String PATH_PAY = PATH_APP_BASE + "/beat/ali/";

    //扫二维码 的requestCode
    public static final int REQUEST_CODE = 100;
    //登录
    public static final int REQUEST_LOGIN = 101;
    //支付宝扫码支付
    public static final int REQUEST_ALIPAY = 102;
    //未登陆跳转登陆页面
    public static final int REQUEST_BIND_CAR = 103;
    /**
     * 帐号密码共享参数
     */
    public static final String SP_ACCOUNT = "account";
    public static final String SP_PSW = "password";
    public static final String SP_USER_INFO = "userInfo"; //用户信息
    public static final String SP_LOGIN = "isLogin"; //是否已经登录
    public static final String SP_MSG_PUSH = "toggleMsgPush"; //是否推送消息
    public static final String SP_GPS = "toggleGps"; //是否打开定位

    public static final String UPDATE_URL = "updateUrl"; //更新地址
    public static final String UPDATE_PROGRESS = "com.update.progress"; //apk下载进度广播


    public static class URL {
        public static final String BASEURL = "http://zhihui.expo2017.net.cn/";
//        public static final String BASEURL = "http://daneil0318test.viphk.ngrok.org/"; //链接url

        public static final String BASEURL_HTML = "http://117.159.4.206:8888/";

        public static final String NATIVE_REQ = BASEURL_HTML + "/zzwhe/"; //链接 banner


        //植物详情
        public static final String encyclopediaInfo = BASEURL_HTML + "zwbk/zw/details";

        //-----------------------分割线 ------------------------------
        public static final String tourRaouteInfo = BASEURL_HTML + "zz/xianlutuijianxiangqing.html"; //线路推荐详情
        public static final String GARDEN_NEWS = BASEURL_HTML + "zz/yuanbodongtai.html"; //园博资讯
//        public static final String MARQUUE_VIEW = "http://mp.weixin.qq.com/s/SAp4JaZPYTDGGB3S35fwzg"; //
        public static final String FACILITIES_CLASS = BASEURL_HTML + "zz/zhanyuanfenlei.html"; //场馆分类
        public static final String PLANT_WIKI = BASEURL_HTML + "zz/zhiwubaikelei.html";//植物百科
        public static final String ROUTER_RECOMMEND = BASEURL_HTML + "zz/xianlutuijian.html";//线路推荐
        public static final String LOST_FIND = BASEURL_HTML + "zz/shiwuzhaolinglei.html";//失物招领
        public static final String COMPLAINT_ADVICE = BASEURL_HTML + "zzyb/youkezhuanqu.html";//投诉建议
        public static final String GARDEN_GUIDE = BASEURL_HTML + "zz/youyuandaolan.html";//园区导览
        public static final String ARROUND_SERVER = BASEURL_HTML + "zzzbfw/app.html"; //周边服务
        public static final String FIND_GAME = BASEURL_HTML + "zz/xungenyouxi.html";//寻根游戏http://zhihui.expo2017.net.cn/xg/xungenyouxi.html
        public static final String MY_STEP = BASEURL_HTML + "zz/wodezuji.html";  //我的足迹
        public static final String MY_MSG = BASEURL_HTML + "zz/yijiantousu2.html";  //我的消息
        public static final String AR = "http://117.159.4.206:8888/720vr/index.html";  //虚拟游园

    }


}
