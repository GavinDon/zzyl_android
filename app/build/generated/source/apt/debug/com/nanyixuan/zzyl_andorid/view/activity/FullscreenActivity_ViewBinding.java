// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FullscreenActivity_ViewBinding implements Unbinder {
  private FullscreenActivity target;

  private View view2131230784;

  @UiThread
  public FullscreenActivity_ViewBinding(FullscreenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FullscreenActivity_ViewBinding(final FullscreenActivity target, View source) {
    this.target = target;

    View view;
    target.ivBg = Utils.findRequiredViewAsType(source, R.id.iv_bg, "field 'ivBg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_show, "method 'onViewClicked'");
    view2131230784 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FullscreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBg = null;

    view2131230784.setOnClickListener(null);
    view2131230784 = null;
  }
}
