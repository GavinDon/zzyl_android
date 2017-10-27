// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.widgets;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.numberprogressbar.NumberProgressBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdateDialog_ViewBinding implements Unbinder {
  private UpdateDialog target;

  private View view2131230851;

  private View view2131230849;

  @UiThread
  public UpdateDialog_ViewBinding(final UpdateDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.dialog_confirm, "field 'btnConfirm' and method 'onclick'");
    target.btnConfirm = Utils.castView(view, R.id.dialog_confirm, "field 'btnConfirm'", SubmitButton.class);
    view2131230851 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.dialog_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.dialog_cancel, "field 'btnCancel' and method 'onclick'");
    target.btnCancel = Utils.castView(view, R.id.dialog_cancel, "field 'btnCancel'", SubmitButton.class);
    view2131230849 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.dialog_content, "field 'tvContent'", TextView.class);
    target.mProgressBar = Utils.findRequiredViewAsType(source, R.id.update_progress, "field 'mProgressBar'", NumberProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UpdateDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnConfirm = null;
    target.tvTitle = null;
    target.btnCancel = null;
    target.tvContent = null;
    target.mProgressBar = null;

    view2131230851.setOnClickListener(null);
    view2131230851 = null;
    view2131230849.setOnClickListener(null);
    view2131230849 = null;
  }
}
