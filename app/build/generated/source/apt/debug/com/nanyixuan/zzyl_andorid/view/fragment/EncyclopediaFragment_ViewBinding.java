// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EncyclopediaFragment_ViewBinding implements Unbinder {
  private EncyclopediaFragment target;

  private View view2131230785;

  @UiThread
  public EncyclopediaFragment_ViewBinding(final EncyclopediaFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_showSideMenu, "field 'btnShowSideMenu' and method 'onViewClicked'");
    target.btnShowSideMenu = Utils.castView(view, R.id.btn_showSideMenu, "field 'btnShowSideMenu'", ImageButton.class);
    view2131230785 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.gridView = Utils.findRequiredViewAsType(source, R.id.gridView, "field 'gridView'", GridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EncyclopediaFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnShowSideMenu = null;
    target.gridView = null;

    view2131230785.setOnClickListener(null);
    view2131230785 = null;
  }
}
