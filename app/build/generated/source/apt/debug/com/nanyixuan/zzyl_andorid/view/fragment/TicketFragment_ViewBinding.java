// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TicketFragment_ViewBinding implements Unbinder {
  private TicketFragment target;

  private View view2131230785;

  @UiThread
  public TicketFragment_ViewBinding(final TicketFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_showSideMenu, "method 'onViewClicked'");
    view2131230785 = view;
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
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230785.setOnClickListener(null);
    view2131230785 = null;
  }
}
