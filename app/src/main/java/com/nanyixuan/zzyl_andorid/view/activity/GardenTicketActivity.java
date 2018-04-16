package com.nanyixuan.zzyl_andorid.view.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.DataBean;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.OrderBean;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.bean.SaleTicketBean;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.utils.ValidateUtil;
import com.nanyixuan.zzyl_andorid.view.adapter.RecyclerAdapter;
import com.nanyixuan.zzyl_andorid.widgets.SimpleDialog;
import com.nanyixuan.zzyl_andorid.widgets.SubmitButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.nanyixuan.zzyl_andorid.R.id.tv_ticket_money;
import static com.nanyixuan.zzyl_andorid.R.id.tv_ticket_paymoney;
import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;


/**
 * description:网上购票
 * Created by liNan on 2017/7/26 10:40
 */
public class GardenTicketActivity extends BaseActivity {

    @BindView(R.id.checkBox)
    AppCompatCheckBox checkBox;  //购票条款checkBox
    @BindView(R.id.submitBtn)
    SubmitButton mSubmitButton;   //购票按钮
    @BindView(R.id.etIdCard)
    AppCompatEditText etIdCard;   //身份证号editText
    @BindView(R.id.etAgainIdCard)
    AppCompatEditText etAgainIdCard; //再次输入身份证号editText
    @BindView(R.id.card01)
    SimpleDraweeView ticketDrawView;
    @BindView(tv_ticket_paymoney)
    TextView tvyMoney;
    @BindView(tv_ticket_money)
    TextView tvMoney;//优惠价
    @BindView(R.id.tv_underline_money)
    TextView underLineMoney;  //原价
    @BindView(R.id.tv_ticket_userTime)
    TextView tvUserTime; //票使用时间
    @BindView(R.id.garden_intro)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_ticket_name)
    TextView tvTicketName;
    private RecyclerAdapter mAdapter;
    private List<DataBean> dataBeanList;
    private DataBean dataBean;
    @BindColor(R.color.lightGreen)
    int lightGreen;
    private SaleTicketBean.ListBean ticketInfoBean;
    private String idcard;
    private String strLoginInfo;
    private LoginBean mLoginBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_garden_ticket;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        ticketInfoBean = bundle.getParcelable("ticketInfo");
        mSubmitButton.setBackgroundColor(Color.GRAY);
        mSubmitButton.setTag(false); //若勾选同意购票条款则设置Tag为true
        setTitle("售票");
        String s = "我已阅读并同意《购票条款》";
        SpannableString spannableString = new SpannableString(s);
        spannableString.setSpan(new ForegroundColorSpan(lightGreen), 7, s.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE); //从起始下标到终了下标，包括起始下标
        checkBox.setText(spannableString);
        initRecycler();
        if (ticketInfoBean != null) {
            ticketDrawView.setImageURI(ticketInfoBean.getImgurltwo());
            tvTicketName.setText(ticketInfoBean.getTicketname());
            tvyMoney.setText("优惠支付:￥" + ticketInfoBean.getXprice());
            tvMoney.setText("￥" + ticketInfoBean.getXprice());
            underLineMoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            underLineMoney.setText("￥" + ticketInfoBean.getYprice());
            tvUserTime.setText(ticketInfoBean.getExplain());
        }
    }

    /**
     * 使用rv来展示购票须知等
     */
    private void initRecycler() {
        dataBeanList = new ArrayList<>();
        String parentContent[] = {"购买须知:", "温馨提示:","购票条款:"};
        String childContent[] = {getResources().getString(R.string.buy_notice), getResources().getString(R.string.prompt),getResources().getString(R.string.buy_clause)};
        for (int i = 0; i < parentContent.length; i++) {
            dataBean = new DataBean();
            dataBean.setID(i + "");
            dataBean.setType(0);
            dataBean.setParentLeftTxt(parentContent[i]); // 一级内容
            dataBean.setChildLeftTxt(childContent[i]); // 展开内容
            dataBean.setChildBean(dataBean);
            dataBeanList.add(dataBean);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(this, dataBeanList);
        mRecyclerView.setAdapter(mAdapter);
        //滚动监听
        mAdapter.setOnScrollListener(new RecyclerAdapter.OnScrollListener() {
            @Override
            public void scrollTo(int pos) {
                mRecyclerView.scrollToPosition(pos);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        strLoginInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
        mLoginBean = JsonUtil.fromJson(strLoginInfo, LoginBean.class);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({R.id.checkBox, R.id.submitBtn})
    public void onclick(View v) {
        switch (v.getId()) {

            case R.id.checkBox:
                if (!checkBox.isChecked()) {
                    mSubmitButton.setTag(false);
                    mSubmitButton.setBackgroundColor(Color.GRAY);
                } else {
                    mSubmitButton.setTag(true);
                    mSubmitButton.restoreBackGround();
                }
                break;
            case R.id.submitBtn:
                boolean isLogin = SPUtils.getInstance().getBoolean(Constant.SP_LOGIN); //判断是否已经登陆
                if (!isLogin) {
                    gotoActivity(LoginActivity.class);
                    return;
                }
                //只有已经勾选了阅读条款才可进行下一步购票
                if ((Boolean) mSubmitButton.getTag()) {
                    idCardValidate();
                } else {
                    ToastUtils.showShort("您还未同意我们的购票条款");
                }
                break;

        }
    }


    /**
     * 监听输入号码完成之后是否是有效的证件号码
     */
    @OnTextChanged(value = {R.id.etIdCard, R.id.etAgainIdCard}, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 15 || s.length() == 18) {
            if (etIdCard.isFocused()) {
                ValidateUtil.maybeIsIdentityCard(etIdCard);
            } else {
                ValidateUtil.maybeIsIdentityCard(etAgainIdCard);
            }
        }
    }

    /**
     * 监听焦点的变化来判断身份证号码长度是否正确
     *
     * @param view
     * @param hasFocus
     */
    @OnFocusChange(value = {R.id.etIdCard, R.id.etAgainIdCard})
    void OnFocusChange(View view, boolean hasFocus) {
        if (!hasFocus) {
            if (!etIdCard.hasFocus()) {
                if ((etIdCard.getEditableText().toString().trim().length() != 15) && etIdCard.getEditableText().toString().trim().length() != 18) {
                    etIdCard.setError("身份证位数错误");
                }
            } else if (!etAgainIdCard.hasFocus()) {
                if ((etAgainIdCard.getText().toString().trim().length() != 15) && etAgainIdCard.getText().toString().trim().length() != 18) {
                    etAgainIdCard.setError("身份证位数错误");
                }
            }
        }

    }

    /**
     * 购票之前本地的校验
     */
    private void idCardValidate() {
        boolean first = ValidateUtil.isIdentityCard(etIdCard);
        boolean second = ValidateUtil.isIdentityCard(etAgainIdCard);
        if (first && second) {
            if (etIdCard.getText().toString().equalsIgnoreCase(etAgainIdCard.getText().toString())) {
                final SimpleDialog mSimpleDialog = new SimpleDialog(this);
                mSimpleDialog.setContentText(ticketInfoBean.getExplain())
                        .showCancelButton(true)
                        .setConfirmClickListener(new SimpleDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SimpleDialog simpleDialog) {
                                idcard = etIdCard.getText().toString();
                                generateOrder();
                                mSimpleDialog.dismiss();
                            }
                        }).setCancelClickListener(new SimpleDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SimpleDialog simpleDialog) {
                        mSimpleDialog.dismiss();
                    }
                });
            } else {
                ToastUtils.showShort("两次输入不一致");
            }

        } else {
            ToastUtils.showShort("身份证号码格式有误");
        }
    }

    /**
     * 生成订单
     */
    private void generateOrder() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", mLoginBean.getUser().getUsername() + "");
        params.put("ticketTypeId", ticketInfoBean.getTicketsystemid());
        params.put("ticketTypeName", ticketInfoBean.getTicketname());
        params.put("ticketCount", "1");
        params.put("ticketId", String.valueOf(ticketInfoBean.getId()));
        params.put("identityCode", idcard); //身份证号码
        params.put("isLimited", ticketInfoBean.getIs_limited()); // 是否限流
        LogUtils.i(MyTools.jointURL("", params));
        RetrofitHelper.getInstance().creat().getOrder(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon tBaseData) throws IOException {
                        if (tBaseData.getRetCode().equals("0")) {
                            OrderBean orderBean = fromJson(tBaseData.getRetData(), OrderBean.class);
                            if (null != orderBean) {
                                Bundle bundle = new Bundle();
                                bundle.putString("page", "0");
                                bundle.putParcelable("orderBean", orderBean);
                                gotoActivity(PayActivity.class, true, bundle);
                            }
                        } else {
                            ToastUtils.showShort(tBaseData.getRetData() + "");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }));
    }
}
