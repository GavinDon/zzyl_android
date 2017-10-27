// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ServiceActivity target;

  private View view2131231136;

  private View view2131230957;

  @UiThread
  public ServiceActivity_ViewBinding(ServiceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ServiceActivity_ViewBinding(final ServiceActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tvPhone2, "field 'tvPhoneNum' and method 'onclick'");
    target.tvPhoneNum = Utils.castView(view, R.id.tvPhone2, "field 'tvPhoneNum'", AppCompatTextView.class);
    view2131231136 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_suggest, "method 'onclick'");
    view2131230957 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    ServiceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPhoneNum = null;

    view2131231136.setOnClickListener(null);
    view2131231136 = null;
    view2131230957.setOnClickListener(null);
    view2131230957 = null;

    super.unbind();
  }
}
