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

public class PayActivity_ViewBinding extends BaseActivity_ViewBinding {
  private PayActivity target;

  private View view2131230787;

  private View view2131230771;

  private View view2131230778;

  @UiThread
  public PayActivity_ViewBinding(PayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PayActivity_ViewBinding(final PayActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_wechat, "field 'btnWeChat' and method 'onclick'");
    target.btnWeChat = Utils.castView(view, R.id.btn_wechat, "field 'btnWeChat'", Button.class);
    view2131230787 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_alipay, "field 'btnAlipay' and method 'onclick'");
    target.btnAlipay = Utils.castView(view, R.id.btn_alipay, "field 'btnAlipay'", Button.class);
    view2131230771 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_other, "field 'btnOtherPay' and method 'onclick'");
    target.btnOtherPay = Utils.castView(view, R.id.btn_other, "field 'btnOtherPay'", Button.class);
    view2131230778 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tvPayTotal = Utils.findRequiredViewAsType(source, R.id.tv_pay_money, "field 'tvPayTotal'", TextView.class);
    target.tvPayLocation = Utils.findRequiredViewAsType(source, R.id.tv_pay_location, "field 'tvPayLocation'", TextView.class);

    Context context = source.getContext();
    target.lightGreen = ContextCompat.getColor(context, R.color.lightGreen);
    target.pressGreen = ContextCompat.getColor(context, R.color.pressGreen);
    target.lightBlue = ContextCompat.getColor(context, R.color.blue_500);
    target.pressBlue = ContextCompat.getColor(context, R.color.blue_800);
    target.anotherColor = ContextCompat.getColor(context, R.color.orange_500);
    target.anotherPressColor = ContextCompat.getColor(context, R.color.orange_500);
    target.grayColor = ContextCompat.getColor(context, R.color.gray);
  }

  @Override
  public void unbind() {
    PayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnWeChat = null;
    target.btnAlipay = null;
    target.btnOtherPay = null;
    target.tvPayTotal = null;
    target.tvPayLocation = null;

    view2131230787.setOnClickListener(null);
    view2131230787 = null;
    view2131230771.setOnClickListener(null);
    view2131230771 = null;
    view2131230778.setOnClickListener(null);
    view2131230778 = null;

    super.unbind();
  }
}
