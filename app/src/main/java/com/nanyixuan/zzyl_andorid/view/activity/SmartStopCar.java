package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.AddCarNubBean;
import com.nanyixuan.zzyl_andorid.bean.BindCarListBean;
import com.nanyixuan.zzyl_andorid.bean.FindParksBean;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.PayValidateBean;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.bean.WeatherBean;
import com.nanyixuan.zzyl_andorid.utils.A2bigA;
import com.nanyixuan.zzyl_andorid.utils.DisplayUtil;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.utils.ValidateUtil;
import com.nanyixuan.zzyl_andorid.view.adapter.BindCarListAdapter;
import com.nanyixuan.zzyl_andorid.view.adapter.CarNamPrefixAdapter;
import com.nanyixuan.zzyl_andorid.widgets.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;

/**
 * 智慧停车
 */
public class SmartStopCar extends BaseActivity {

    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.ll_banner)
    LinearLayout llBanner;
    @BindView(R.id.ad_up)
    ImageView imgUp; //轮播下拉箭头
    private boolean isShow;
    private List<String> adInfo=new ArrayList<>(); // 广告轮播集合
    @BindView(R.id.ll_bindcar)
    LinearLayout llBindcar;
    @BindView(R.id.et_bindcar)
    AppCompatEditText etBindCar;
    @BindView(R.id.rv_bind_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.weather_background)
    ImageView ivWeatherBG;
    @BindView(R.id.iv_weather_icon)
    SimpleDraweeView ivweatherIcon;
    @BindView(R.id.tv_weather_T)
    TextView tvWeatherT;
    @BindView(R.id.tv_weather_state)
    TextView tvWeatherState;
    @BindView(R.id.tv_car_wash)
    TextView tvWash;
    @BindView(R.id.tv_yua)
    TextView tvCarnamPrefix; //车牌归属地(陕A)


    private LoginBean loginBean;
    private List<BindCarListBean> bindcarList;//车牌号列表
    private BindCarListAdapter mAdapter;
    private String strCarNum;
    private boolean isLogin;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("智慧停车");
        getWeatherInfo();
        initBanner();
        isLogin = SPUtils.getInstance().getBoolean(Constant.SP_LOGIN); //获取是否登录
        if (!isLogin) {
            startActivityForResult(new Intent(this, LoginActivity.class), Constant.REQUEST_LOGIN);
        } else {
            reqBindCarList();
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BindCarListAdapter(R.layout.item_bindcar, null);
        mRecyclerView.setAdapter(mAdapter);
        ivWeatherBG.getBackground().setAlpha(150);
        etBindCar.setTransformationMethod(new A2bigA());

    }

    private void getWeatherInfo() {
        RetrofitHelper.getInstance().creat().getWeatherInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<WeatherBean>() {
                    @Override
                    public void onNext(WeatherBean response) {
                        if (response != null) {
                            String uri = Constant.URL.BASEURL_HTML + "/zhcomplaint/weatherImage/" + response.getCode() + ".png";
                            ivweatherIcon.setImageURI(uri);
                            tvWeatherT.setText(String.format("%s℃", response.getTemperature()));
                            tvWeatherState.setText(response.getText());
                            tvWash.setText(String.format("洗车指数:%s", response.getBrief()));
                        }
                    }
                    @Override
                    public void onError(String msg) {

                    }
                }));
    }

    /**
     * 停车广告轮播
     */
    private void initBanner() {
        RetrofitHelper.getInstance().creat().getFindParks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon.getRetCode().equals("0")) {
                            List<FindParksBean> findParksBean = fromJson(respCommon.getRetData(), new TypeToken<List<FindParksBean>>() {
                            }.getType());
                            if (findParksBean != null) {
                                for (int i = 0; i < findParksBean.size(); i++) {
                                    adInfo.add(findParksBean.get(i).getParkname() + "剩余车位" + findParksBean.get(i).getBespeaknum());
                                }
                                marqueeView.startWithList(adInfo); //启动轮播
                            }
                        } else if (respCommon.getRetCode().equals("112")) {
                            ToastUtils.showShort("网络繁忙,请稍后再试");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);

                    }
                }));

    }


    @OnClick({R.id.ad_up, R.id.btn_bind, R.id.btn_pay, R.id.btn_subscriber, R.id.tv_yua})
    public void onclick(View v) {
        boolean isLogin = SPUtils.getInstance().getBoolean(Constant.SP_LOGIN);
        switch (v.getId()) {
            case R.id.ad_up:
                showMoreBanner();
                break;
            case R.id.btn_subscriber:
                if (!isLogin) {
                    //如果没有登录跳转登陆界面
                    gotoActivity(LoginActivity.class);
                } else {
                    Bundle bundle = new Bundle();
                    for (int i = 0; i < mAdapter.getItemCount(); i++) {
                        //取出默认选中的车牌号来进行预约
                        if (mAdapter.getData().get(i).getIs_check().equals("1")) {
                            strCarNum = mAdapter.getData().get(i).getCar_num();
                        }
                    }
                    bundle.putString("carNum", strCarNum);
                    gotoActivity(SubscriberCarActivity.class, false, bundle);
                }
                break;
            case R.id.btn_bind:
                if (!isLogin) {
                    gotoActivity(LoginActivity.class);
                } else {
                    String strCarNum = etBindCar.getText().toString().trim();
                    reqBindCar(tvCarnamPrefix.getText() + strCarNum.toUpperCase());
                }
                KeyboardUtils.hideSoftInput(this);
                break;
            case R.id.btn_pay: //停车缴费
                reqTokenAndpayCheck();
                break;
            case R.id.tv_yua: //弹出车牌选择器
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                View view = LayoutInflater.from(this).inflate(R.layout.bottom_dialog_carnum, null, false);
                RecyclerView bottomRecyclerView = (RecyclerView) view.findViewById(R.id.rv_carNam_prefix);
                bottomSheetDialog.setContentView(view);
                bottomRecyclerView.setHasFixedSize(true);
                //当手机宽度大于1080p 时设置为10列
                if (DisplayUtil.getWindowWidth(this) >= 1080) {
                    bottomRecyclerView.setLayoutManager(new GridLayoutManager(this, 10));
                } else {
                    bottomRecyclerView.setLayoutManager(new GridLayoutManager(this, 8));
                }
                List<String> carLst = new ArrayList<>();
                String[] prefix = {"京", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋", "蒙", "陕", "津",
                        "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"};
                for (String car : prefix) {
                    carLst.add(car);
                }
                CarNamPrefixAdapter mAdapter = new CarNamPrefixAdapter(R.layout.carprefix_bottom_dialog_item, carLst);
                bottomRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        tvCarnamPrefix.setText(adapter.getItem(position).toString());
                        bottomSheetDialog.dismiss();
                    }
                });

                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                } else {
                    bottomSheetDialog.show();
                }
                break;
        }

    }


    /**
     * 获取绑定车牌号列表
     */
    private String standardId; //默认的Id
    private List<String> lstIscheck = new ArrayList<>();

    private void reqBindCarList() {
        isLogin = SPUtils.getInstance().getBoolean(Constant.SP_LOGIN); //获取是否登录
        if (!isLogin) {
//            startActivityForResult(new Intent(this, LoginActivity.class), Constant.REQUEST_LOGIN);
        } else {
            String userInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
            loginBean = fromJson(userInfo, LoginBean.class);
            llBindcar.removeAllViews();
            RetrofitHelper.getInstance().creat().getcarNumlist(String.valueOf(loginBean.getUser().getUsername()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                        @Override
                        public void onNext(RespCommon respCommon) {
                            if (respCommon.getRetCode().equals("0")) {
                                bindcarList = fromJson(respCommon.getRetData(), new TypeToken<List<BindCarListBean>>() {
                                }.getType());
                                if (bindcarList != null && bindcarList.size() > 0) {
                                    //遍历取出默认选中并加到list中
                                    for (int i = 0; i < bindcarList.size(); i++) {
                                        String isCheck = bindcarList.get(i).getIs_check();
                                        if (isCheck.equals("1")) {
                                            standardId = bindcarList.get(i).getId();
                                        }
                                        lstIscheck.add(isCheck);
                                    }
                                    //判断List中是否有包含1的值
                                    if (!lstIscheck.contains("1")) {
                                        bindcarList.get(0).setIs_check("1");
                                        standardId = bindcarList.get(0).getId();
                                        strCarNum = bindcarList.get(0).getCar_num();
                                        updateDefaultCarNum("", standardId); //更新默认车牌
                                    }

                                    mAdapter.setNewData(bindcarList);
                                    mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                            BindCarListBean b = (BindCarListBean) adapter.getItem(position);
                                            if (view.getId() == R.id.item_iv_close) {
                                                if (b.getIs_check().equals("1")) {
                                                    ToastUtils.showShort("请先绑定一个默认的车牌再进行删除");
                                                } else {
                                                    deleteBindCarNum(b, position); //服务器上删除
                                                }
                                            }
                                        }
                                    });
                                    //改变默认选中车牌号
                                    mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            BindCarListBean bindCarListBean = (BindCarListBean) adapter.getItem(position);

                                            if (bindCarListBean.getIs_check().equals("1")) {
                                                strCarNum = bindCarListBean.getCar_num();
                                                return;
                                            } else {
                                                for (int i = 0; i < mAdapter.getItemCount(); i++) {
                                                    if (position != i) {
                                                        mAdapter.getItem(i).setIs_check("0");
                                                    } else {
                                                        bindCarListBean.setIs_check("1");
                                                        strCarNum = mAdapter.getItem(i).getCar_num();
                                                    }
                                                }
                                                updateDefaultCarNum(standardId, mAdapter.getItem(position).getId());

                                            }
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onError(String msg) {
                            ToastUtils.showShort(msg);
                        }
                    }));

        }
    }


    /**
     * 绑定车牌
     *
     * @param strCarNum 车牌号
     */
    private void reqBindCar(String strCarNum) {
        //判断是否有已经绑定重复车牌
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            if (mAdapter.getItem(i).getCar_num().equals(strCarNum)) {
                ToastUtils.showShort("您已经绑定过此车牌");
                return;
            }
        }
        if (etBindCar.getText().length() == 6) {
            if (mAdapter.getItemCount() >= 3) {
                ToastUtils.showShort("最多只能绑定3辆车");
                return;
            }
            //传值根据车牌号列表中是否有默认值
            String check = (mAdapter.getItemCount() <= 0) ? "1" : "0";
            // 如果是正确的车牌号
            if (ValidateUtil.isPlateNo(strCarNum)) {
                RetrofitHelper.getInstance().creat().getAddcarNum(String.valueOf(loginBean.getUser().getUsername()), strCarNum, check)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                            @Override
                            public void onNext(RespCommon respCommon) {
                                if (respCommon.getRetCode().equals("0")) {
                                    etBindCar.setText(""); //绑定成功置空输入框
                                    AddCarNubBean addCarNubBean = JsonUtil.fromJson(respCommon.getRetData(), AddCarNubBean.class);
                                    BindCarListBean bindCarListBean = new BindCarListBean();
                                    bindCarListBean.setIs_check(addCarNubBean.getIs_check());
                                    bindCarListBean.setCar_num(addCarNubBean.getCar_num());
                                    bindCarListBean.setCreate_date(addCarNubBean.getCreate_date());
                                    bindCarListBean.setId(addCarNubBean.getId());
                                    bindCarListBean.setUser_id(addCarNubBean.getUser_id());
                                    mAdapter.addData(bindCarListBean);
                                } else {
                                    ToastUtils.showShort("绑定失败");
                                }
                            }

                            @Override
                            public void onError(String msg) {

                            }
                        }));
            } else {
                ToastUtils.showShort("车牌号不正确");
            }
        } else {
            ToastUtils.showShort("车牌号位数不正确");
        }
    }

    /**
     * 删除车牌
     *
     * @param pos 删除的索引
     */
    private void deleteBindCarNum(final BindCarListBean b, final int pos) {
        RetrofitHelper.getInstance().creat().getdeleteCarNum(b.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon.getRetCode().equals("0")) {
//                            ToastUtils.showShort("删除成功");
                            mAdapter.remove(pos); //移除列表指定的Item
                            mAdapter.notifyItemRemoved(pos);

                            //如果删除的是默认的车牌则指定索引为0的Item 为默认车牌
//                            if (b.getIs_check().equals("1") && bindcarList.size() > 1) {
//                                mAdapter.getItem(0).setIs_check("1");
//                                mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount() - pos); //更新item
//                                updateDefaultCarNum(b.getId(), mAdapter.getItem(0).getId()); //更新默认车牌
//                            }
                        } else {
                            ToastUtils.showShort("删除失败");
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));

    }

    /**
     * 修改默认车牌
     */
    private void updateDefaultCarNum(String oldId, final String newId) {
        RetrofitHelper.getInstance().creat().getchangeDefaultCarNum(oldId, newId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon.getRetCode().equals("0")) {
                            standardId = newId;//默认车牌更换
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));
    }

    @BindColor(R.color.black_alpha_128)
    int bannerColor;

    /**
     * 展示更多的广告
     */
    private void showMoreBanner() {
        //展开更多
        if (!isShow) {
            llBanner.removeAllViews();
            for (int i = 0; i < adInfo.size(); i++) {
                final TextView tvBanner = new TextView(SmartStopCar.this);
                tvBanner.setText(adInfo.get(i));
                tvBanner.setTextColor(bannerColor);
                tvBanner.setTextSize(14);
                tvBanner.setPadding(0, 6, 0, 6);
                tvBanner.setTag(i);
                tvBanner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        ToastUtils.showShort(tvBanner.getTag() + "==");
                    }
                });
                llBanner.addView(tvBanner);
                MyTools.rotationExpandIcon(imgUp, 0, 180);
//                imgUp.setRotation(180);
                isShow = true;
            }
        } else {
            //折叠
            llBanner.removeAllViews();
            MyTools.rotationExpandIcon(imgUp, 180, 0);
            isShow = false;
        }
    }


    /**
     * 获取缴费验证
     */
    private void reqTokenAndpayCheck() {
        String userInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
        final LoginBean loginBean = fromJson(userInfo, LoginBean.class);
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            if (mAdapter.getData().get(i).getIs_check().equals("1")) {
                strCarNum = mAdapter.getData().get(i).getCar_num();
            }
        }
        if (!TextUtils.isEmpty(strCarNum) && loginBean != null) {
            RetrofitHelper.getInstance().creat().getPayCheck(strCarNum, String.valueOf(loginBean.getUser().getUsername()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                        @Override
                        public void onNext(RespCommon respCommon) {
                            if (respCommon.getRetCode().equals("0")) {
                                PayValidateBean payValidateBean = fromJson(respCommon.getRetData(), PayValidateBean.class);
                                if (payValidateBean != null) {
                                    String strMoney = payValidateBean.getMoney(); //获取支付金额
                                    //如果支付金额大于0进行支付
                                    if (Float.parseFloat(strMoney) > 0) {
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("payInfo", payValidateBean);
                                        gotoActivity(CarPayActivity.class, false, bundle);
                                    } else {
                                        ToastUtils.showShort("您已经支付过,无需再次支付");
                                    }
                                } else {
                                    ToastUtils.showShort(respCommon.getRetData());
                                }
                            } else {
                                ToastUtils.showShort(respCommon.getRetData());
                            }
                        }

                        @Override
                        public void onError(String msg) {
                            ToastUtils.showShort(msg);
                        }
                    }));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    protected void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

    /**
     * 未登陆跳转回来时
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_LOGIN) {
            reqBindCarList();
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_smart_stop_car;
    }

}
