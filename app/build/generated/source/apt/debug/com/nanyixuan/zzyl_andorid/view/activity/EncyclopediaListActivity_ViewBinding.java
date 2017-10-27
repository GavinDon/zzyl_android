// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EncyclopediaListActivity_ViewBinding implements Unbinder {
  private EncyclopediaListActivity target;

  private View view2131230785;

  @UiThread
  public EncyclopediaListActivity_ViewBinding(EncyclopediaListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EncyclopediaListActivity_ViewBinding(final EncyclopediaListActivity target, View source) {
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
    target.showQR = Utils.findRequiredViewAsType(source, R.id.show_QR, "field 'showQR'", Button.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.titleBar, "field 'titleBar'", LinearLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EncyclopediaListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnShowSideMenu = null;
    target.showQR = null;
    target.titleBar = null;
    target.listView = null;

    view2131230785.setOnClickListener(null);
    view2131230785 = null;
  }
}
