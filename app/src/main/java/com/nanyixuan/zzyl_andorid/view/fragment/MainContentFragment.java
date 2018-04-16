package com.nanyixuan.zzyl_andorid.view.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseFragment;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.MainListData;
import com.nanyixuan.zzyl_andorid.bean.MainLooperData;
import com.nanyixuan.zzyl_andorid.bean.TourRouteData;
import com.nanyixuan.zzyl_andorid.presenter.MainContentFragmentPresenter;
import com.nanyixuan.zzyl_andorid.presenter.TourRouteFragmentPresenter;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.LocalImageHolderView;
import com.nanyixuan.zzyl_andorid.utils.ToolAES;
import com.nanyixuan.zzyl_andorid.view.activity.LoginActivity;
import com.nanyixuan.zzyl_andorid.view.activity.OrderListActivity;
import com.nanyixuan.zzyl_andorid.view.activity.PersonalActivity;
import com.nanyixuan.zzyl_andorid.view.activity.QRCustomUiActivity;
import com.nanyixuan.zzyl_andorid.view.activity.ServiceActivity;
import com.nanyixuan.zzyl_andorid.view.activity.TicketClassActivity;
import com.nanyixuan.zzyl_andorid.view.activity.UrlContentActivity;
import com.nanyixuan.zzyl_andorid.view.activity.traffic.TrafficInfoActivity;
import com.nanyixuan.zzyl_andorid.view.adapter.HomeGridAdapter;
import com.nanyixuan.zzyl_andorid.widgets.MarqueeView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;


public class MainContentFragment extends BaseFragment implements MainContentFragmentController, TourRouteFragmentController, HomeGridAdapter.GridOnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainContentFragment";

    MainContentFragmentPresenter mainContentFragmentPresenter;
    @BindView(R.id.btn_showSideMenu)
    ImageButton btnShowSideMenu;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.loopView)
    Banner loopView;
    @BindView(R.id.constraintParent)
    ConstraintLayout constraintParent;
    @BindView(R.id.gridView)
    RecyclerView gridRv;
    @BindView(R.id.listView)
    LinearLayout listView;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;

    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private TextView tvUserName;//用户名

    private boolean isLogin; //是否登陆
    LinearLayout llLogin; // 登陆布局

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static MainContentFragment newInstance() {
        Bundle args = new Bundle();
        MainContentFragment fragment = new MainContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_main_content;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {
        initGrid();
        initNav();
        reqLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (isLogin){
//            return;
//        }
        boolean judgeIsLogin = SPUtils.getInstance().getBoolean(Constant.SP_LOGIN);
        if (judgeIsLogin) {
            String userInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
            LoginBean loginBean = JsonUtil.fromJson(userInfo, LoginBean.class);
            tvUserName.setText(loginBean.getUser().getUsername());
            isLogin = true;
        } else {
            isLogin = false;
            tvUserName.setText("未登陆");
        }
    }

    /**
     * 自动登陆
     */
    private void reqLogin() {
        String userInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
        String passWord = SPUtils.getInstance().getString(Constant.SP_PSW);
        if (!TextUtils.isEmpty(userInfo)) {
            LoginBean bean = fromJson(userInfo, LoginBean.class);
            String account = bean.getUser().getUsername();
            if (account != null && passWord != null) {
                RetrofitHelper.getInstance().creat().getLogin(account, ToolAES.decode(passWord))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MySubscriber<>(this.getActivity(), true, new SubCallback<LoginBean>() {
                            @Override
                            public void onNext(LoginBean tBaseData) {
                                if (tBaseData.isFlag()) {
                                    SPUtils.getInstance().put(Constant.SP_LOGIN, true);//代表登陆成功
                                    SPUtils.getInstance().put(Constant.SP_USER_INFO, JsonUtil.toJson(tBaseData));//用户信息保存起来
                                    isLogin = true;
//                                    ToastUtils.showShort("登录成功");
                                    tvUserName.setText(tBaseData.getUser().getUsername());
                                } else {
                                    isLogin = false;
                                    tvUserName.setText("未登录");
                                    ToastUtils.showShort("登录失败");
                                    SPUtils.getInstance().clear();
                                }
                            }

                            @Override
                            public void onError(String msg) {
                                isLogin = false;
                                SPUtils.getInstance().clear();
                            }
                        }));
            } else {
                SPUtils.getInstance().clear();
            }
        }

        /**
         * 登陆点击事件
         */
        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    //如果已经登陆跳转到帐号信息页面
                    gotoActivity(PersonalActivity.class);
                } else {
                    //没有登陆跳转到登陆页
                    Intent intent = new Intent(MainContentFragment.this.getActivity(), LoginActivity.class);
                    startActivityForResult(intent, Constant.REQUEST_LOGIN);
                }
            }
        });

    }

    /**
     * 抽屉布局的初始化以及属性设置
     */
    private void initNav() {
        mNavigationView.setItemIconTintList(null);
        //获取侧滑菜单的头部
        View headView = mNavigationView.getHeaderView(0);
        llLogin = (LinearLayout) headView.findViewById(R.id.ll_login);
        tvUserName = (TextView) headView.findViewById(R.id.tv_user_nick);
        mNavigationView.setNavigationItemSelectedListener(this);
        // 设置侧滑菜单宽度
        ViewGroup.LayoutParams params = mNavigationView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels * 3 / 5;
        mNavigationView.setLayoutParams(params);
        //修改侧滑菜单默认的文字颜色
        int[][] states = new int[][]{{android.R.attr.state_pressed}, {-android.R.attr.state_pressed}};
        int colors[] = new int[]{ContextCompat.getColor(this.getActivity(), R.color.lightGreen), ContextCompat.getColor(this.getActivity(), R.color.white)};
        ColorStateList mColorStateList = new ColorStateList(states, colors);
        mNavigationView.setItemTextColor(mColorStateList);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainContentFragmentPresenter = new MainContentFragmentPresenter(getActivity(), this);
        mainContentFragmentPresenter.init();
        tourRouteFragmentPresenter = new TourRouteFragmentPresenter(this, getActivity());
        tourRouteFragmentPresenter.init();
    }

    /**
     * 轮播广告
     *
     * @param imgs
     */
    @Override
    public void setLooperView(List<MainLooperData> imgs) {
        List<String> i = new ArrayList<>();
        for (MainLooperData img : imgs) {
            i.add(Constant.URL.NATIVE_REQ + img.getTp());
        }
        loopView.setImages(i)
                .setImageLoader(new LocalImageHolderView())
                .setBannerAnimation(Transformer.ZoomOutSlide)
                .start();
    }

    /**
     * viewFillper 轮播
     *
     * @param mainListDatas
     */
    @Override
    public void setBottomList(final MainListData mainListDatas) {
        List<String> info = new ArrayList<>();
//        for (int i = 0; i < mainListDatas.size(); i++) {
//            info.add(mainListDatas.get(i).getMsg());
//        }
        if (mainListDatas.getMsg().equals("success")) {
            for (int i = 0; i < mainListDatas.getData().getContent().size(); i++) {

                info.add(mainListDatas.getData().getContent().get(i).getMc());
            }
            marqueeView.startWithList(info);
            marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                @Override
                public void onItemClick(int position, TextView textView) {
                    Bundle bundle = new Bundle();
                    String httpURL = mainListDatas.getData().getContent().get(position).getXq();
                    String httpXq[]=httpURL.split("\"");
                    if (httpXq.length>1){
                        bundle.putString("url",httpXq[1]);
                        Intent intent = new Intent(getActivity(), UrlContentActivity.class);
                        intent.putExtra("data", bundle);
                        startActivity(intent);
                    }else {
                        bundle.putString("url",Constant.URL.GARDEN_NEWS);
                        Intent intent = new Intent(getActivity(), UrlContentActivity.class);
                        intent.putExtra("data", bundle);
                        startActivity(intent);
                    }


                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        loopView.startAutoPlay();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        loopView.stopAutoPlay();
        marqueeView.stopFlipping();
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
//        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
//            mDrawerLayout.closeDrawer(Gravity.START);
//        }
    }

    TourRouteFragmentPresenter tourRouteFragmentPresenter;

    /**
     * 底部路线推荐
     *
     * @param tourRouteList
     */
    @Override
    public void setTourRouteList(final List<TourRouteData> tourRouteList) {
        for (TourRouteData item : tourRouteList) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.item_tour_route, listView, false);
            view.setTag(item.getId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", Constant.URL.tourRaouteInfo + "?id=" + view.getTag());
                    Intent intent = new Intent(getActivity(), UrlContentActivity.class);
                    intent.putExtra("data", bundle);
                    startActivity(intent);
                }
            });
            listView.addView(view);

            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.simpleDraweeView);
            simpleDraweeView.setImageURI(Constant.URL.
                    NATIVE_REQ + item.getTp());
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(item.getMc());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    /**
     * 网格模块
     */
    private void initGrid() {
        //网格文字与对应的图片level
        String arrayGrids[][] = {{"园博活动", "20"}, {"游园导览", "60"}, {"植物百科", "50"}, {"展园介绍", "10"}, {"网上购票", "40"}, {"虚拟游园", "30"}, {"智慧停车", "70"}, {"游客专区", "80"}};
        List<SparseArray<String>> gridList = new ArrayList<>();
        for (int i = 0; i < arrayGrids.length; i++) {
            SparseArray<String> sa = new SparseArray<>();
            sa.put(Integer.parseInt(arrayGrids[i][1]), arrayGrids[i][0]);
            gridList.add(sa);
        }
        HomeGridAdapter mAdapter = new HomeGridAdapter(gridList);
        GridLayoutManager glm = new GridLayoutManager(this.getContext(), 4);
        gridRv.setHasFixedSize(true);
        gridRv.setLayoutManager(glm);
        gridRv.setNestedScrollingEnabled(false);
        gridRv.setAdapter(mAdapter);
        mAdapter.setOnclickListener(this);
    }

    /**
     * 扫一扫+抽屉
     *
     * @param view
     */
    @OnClick({R.id.show_QR, R.id.btn_showSideMenu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_showSideMenu:
                toggleLeftSliding();
                break;
            case R.id.show_QR:
                RxPermissions rxPermissions = new RxPermissions(this.getActivity());
                rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            startActivityForResult(new Intent(MainContentFragment.this.getActivity(), QRCustomUiActivity.class), Constant.REQUEST_CODE);
                        } else {
                            Toast.makeText(MainContentFragment.this.getActivity(), "此功能需要摄像头权限。", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
                break;
        }
    }

    /**
     * 扫码完成的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    goWebPage(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this.getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }

        } else if (requestCode == Constant.REQUEST_LOGIN) {
            if (null != data) {
                isLogin = true;
                tvUserName.setText(data.getStringExtra("login"));
            } else {
                isLogin = false;
                tvUserName.setText("未登陆");
            }
        }


    }

    /**
     * 根据扫描完成之后的url打开页面
     *
     * @param url
     */
    private void goWebPage(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Intent intent = new Intent(this.getActivity(), UrlContentActivity.class);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    /**
     * 切换抽屉状态（打开关闭）
     */
    private void toggleLeftSliding() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        } else {
            mDrawerLayout.openDrawer(Gravity.START);
        }
    }

    /**
     * 网格布局的点击事件
     *
     * @param position 代表点击的位置
     */
    @Override
    public void hClickListener(int position) {
        switch (position) {
            case 0:
//                setIntentData(Constant.URL.FACILITIES_CLASS);
                setIntentData(Constant.URL.GARDEN_NEWS); //园博活动
                break;
            case 1:
//                setIntentData(Constant.URL.GARDEN_GUIDE); //游园导览
//                setIntentData("http://zhihui.expo2017.net.cn:8099/main/mobile.html"); //游园导览
                setPermissions();
                break;
            case 2:
                setIntentData(Constant.URL.PLANT_WIKI);//植物百科
                break;
            case 3:
                setIntentData(Constant.URL.FACILITIES_CLASS);//展园介绍
                break;
            case 4:
                gotoActivity(TicketClassActivity.class);//网上购票
                break;
            case 5:
                setIntentData(Constant.URL.AR); //虚拟游园
//                ToastUtils.showShort("720全景正在努力开发中。");
                break;
            case 6: //智慧停车
//                gotoActivity(SmartStopCar.class);
                ToastUtils.showShort("停车场还未开放");
                break;
            case 7: //投诉建议
                setIntentData(Constant.URL.COMPLAINT_ADVICE);
                break;
        }
    }

    /**
     * 侧边栏的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          /*  case R.id.home: //植物百科
                setIntentData(Constant.URL.PLANT_WIKI);
                break;
            case R.id.smart_stop: //智慧停车
                setIntentData(Constant.URL.PARKING);
                break;
            case R.id.online_buy: // 购票
                gotoActivity(GardenTicketActivity.class);
                break;*/
            case R.id.lost_found: //失物招领
                setIntentData(Constant.URL.LOST_FIND);
                break;
            case R.id.route_recommend: //线路推荐
                setIntentData(Constant.URL.ROUTER_RECOMMEND);
                break;
            case R.id.my_order: //订单列表
                gotoActivity(OrderListActivity.class);
                break;
            case R.id.server_info: //服务信息
                gotoActivity(ServiceActivity.class);
                break;
            case R.id.trafficInfo: //交通信息
                gotoActivity(TrafficInfoActivity.class);
                break;
            case R.id.find_game: //寻根游戏
                setIntentData(Constant.URL.FIND_GAME);
                break;
            case R.id.around_server: //周边服务
                setIntentData(Constant.URL.ARROUND_SERVER);
                break;
            case R.id.user_center: //个人中心
                gotoActivity(PersonalActivity.class);
                break;
        }
        return true;
    }

    /**
     * 启动webView并传值
     *
     * @param url
     */
    private void setIntentData(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Intent intent = new Intent(this.getActivity(), UrlContentActivity.class);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    /**
     * 打开权限进行游园导览
     */
    private void setPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this.getActivity());
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            setIntentData("http://zhihui.expo2017.net.cn:8099/main/mobile.html"); //游园导览
                        } else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainContentFragment.this.getActivity()).setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent localIntent = new Intent();
                                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                    localIntent.setData(Uri.fromParts("package", MainContentFragment.this.getActivity().getPackageName(), null));
                                    startActivity(localIntent);
                                }
                            }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setMessage("您拒绝打开定位权限将不能使用地图导览，去设置").setTitle("设置权限");
                            alertDialog.show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort(throwable.getMessage());
                    }
                });
    }

}
