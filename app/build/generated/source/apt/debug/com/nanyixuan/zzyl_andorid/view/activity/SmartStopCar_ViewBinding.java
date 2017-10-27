// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import com.nanyixuan.zzyl_andorid.widgets.MarqueeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SmartStopCar_ViewBinding extends BaseActivity_ViewBinding {
  private SmartStopCar target;

  private View view2131230746;

  private View view2131231197;

  private View view2131230773;

  private View view2131230779;

  private View view2131230786;

  @UiThread
  public SmartStopCar_ViewBinding(SmartStopCar target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SmartStopCar_ViewBinding(final SmartStopCar target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.marqueeView = Utils.findRequiredViewAsType(source, R.id.marqueeView, "field 'marqueeView'", MarqueeView.class);
    target.llBanner = Utils.findRequiredViewAsType(source, R.id.ll_banner, "field 'llBanner'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ad_up, "field 'imgUp' and method 'onclick'");
    target.imgUp = Utils.castView(view, R.id.ad_up, "field 'imgUp'", ImageView.class);
    view2131230746 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.llBindcar = Utils.findRequiredViewAsType(source, R.id.ll_bindcar, "field 'llBindcar'", LinearLayout.class);
    target.etBindCar = Utils.findRequiredViewAsType(source, R.id.et_bindcar, "field 'etBindCar'", AppCompatEditText.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_bind_list, "field 'mRecyclerView'", RecyclerView.class);
    target.ivWeatherBG = Utils.findRequiredViewAsType(source, R.id.weather_background, "field 'ivWeatherBG'", ImageView.class);
    target.ivweatherIcon = Utils.findRequiredViewAsType(source, R.id.iv_weather_icon, "field 'ivweatherIcon'", SimpleDraweeView.class);
    target.tvWeatherT = Utils.findRequiredViewAsType(source, R.id.tv_weather_T, "field 'tvWeatherT'", TextView.class);
    target.tvWeatherState = Utils.findRequiredViewAsType(source, R.id.tv_weather_state, "field 'tvWeatherState'", TextView.class);
    target.tvWash = Utils.findRequiredViewAsType(source, R.id.tv_car_wash, "field 'tvWash'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_yua, "field 'tvCarnamPrefix' and method 'onclick'");
    target.tvCarnamPrefix = Utils.castView(view, R.id.tv_yua, "field 'tvCarnamPrefix'", TextView.class);
    view2131231197 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_bind, "method 'onclick'");
    view2131230773 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_pay, "method 'onclick'");
    view2131230779 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_subscriber, "method 'onclick'");
    view2131230786 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });

    Context context = source.getContext();
    target.bannerColor = ContextCompat.getColor(context, R.color.black_alpha_128);
  }

  @Override
  public void unbind() {
    SmartStopCar target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.marqueeView = null;
    target.llBanner = null;
    target.imgUp = null;
    target.llBindcar = null;
    target.etBindCar = null;
    target.mRecyclerView = null;
    target.ivWeatherBG = null;
    target.ivweatherIcon = null;
    target.tvWeatherT = null;
    target.tvWeatherState = null;
    target.tvWash = null;
    target.tvCarnamPrefix = null;

    view2131230746.setOnClickListener(null);
    view2131230746 = null;
    view2131231197.setOnClickListener(null);
    view2131231197 = null;
    view2131230773.setOnClickListener(null);
    view2131230773 = null;
    view2131230779.setOnClickListener(null);
    view2131230779 = null;
    view2131230786.setOnClickListener(null);
    view2131230786 = null;

    super.unbind();
  }
}
