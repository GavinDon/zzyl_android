// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CarPayActivity_ViewBinding extends BaseActivity_ViewBinding {
  private CarPayActivity target;

  private View view2131230792;

  private View view2131230790;

  private View view2131230791;

  @UiThread
  public CarPayActivity_ViewBinding(CarPayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CarPayActivity_ViewBinding(final CarPayActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.car_tv_pay_money, "field 'tvMoney'", TextView.class);
    view = Utils.findRequiredView(source, R.id.car_btn_wechat, "field 'carBtnWechat' and method 'onclick'");
    target.carBtnWechat = Utils.castView(view, R.id.car_btn_wechat, "field 'carBtnWechat'", Button.class);
    view2131230792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.car_btn_alipay, "field 'carBtnAlipay' and method 'onclick'");
    target.carBtnAlipay = Utils.castView(view, R.id.car_btn_alipay, "field 'carBtnAlipay'", Button.class);
    view2131230790 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.car_btn_other, "field 'carBtnOther' and method 'onclick'");
    target.carBtnOther = Utils.castView(view, R.id.car_btn_other, "field 'carBtnOther'", Button.class);
    view2131230791 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });

    Context context = source.getContext();
    target.lightGreen = ContextCompat.getColor(context, R.color.lightGreen);
    target.pressGreen = ContextCompat.getColor(context, R.color.pressGreen);
    target.lightBlue = ContextCompat.getColor(context, R.color.blue_500);
    target.pressBlue = ContextCompat.getColor(context, R.color.blue_800);
    target.anotherColor = ContextCompat.getColor(context, R.color.orange_500);
    target.anotherPressColor = ContextCompat.getColor(context, R.color.orange_500);
  }

  @Override
  public void unbind() {
    CarPayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvMoney = null;
    target.carBtnWechat = null;
    target.carBtnAlipay = null;
    target.carBtnOther = null;

    view2131230792.setOnClickListener(null);
    view2131230792 = null;
    view2131230790.setOnClickListener(null);
    view2131230790 = null;
    view2131230791.setOnClickListener(null);
    view2131230791 = null;

    super.unbind();
  }
}
