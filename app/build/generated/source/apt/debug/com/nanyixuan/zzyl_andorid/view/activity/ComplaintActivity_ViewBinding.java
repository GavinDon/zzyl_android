// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ComplaintActivity_ViewBinding implements Unbinder {
  private ComplaintActivity target;

  private View view2131230785;

  private View view2131231072;

  @UiThread
  public ComplaintActivity_ViewBinding(ComplaintActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ComplaintActivity_ViewBinding(final ComplaintActivity target, View source) {
    this.target = target;

    View view;
    target.etContent = Utils.findRequiredViewAsType(source, R.id.et_content, "field 'etContent'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_showSideMenu, "method 'onViewClicked'");
    view2131230785 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.show_QR, "method 'onViewClicked'");
    view2131231072 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ComplaintActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etContent = null;

    view2131230785.setOnClickListener(null);
    view2131230785 = null;
    view2131231072.setOnClickListener(null);
    view2131231072 = null;
  }
}
