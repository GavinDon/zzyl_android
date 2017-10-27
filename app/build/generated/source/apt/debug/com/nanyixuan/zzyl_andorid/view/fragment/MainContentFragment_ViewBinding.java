// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.widgets.MarqueeView;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainContentFragment_ViewBinding implements Unbinder {
  private MainContentFragment target;

  private View view2131230785;

  private View view2131231072;

  @UiThread
  public MainContentFragment_ViewBinding(final MainContentFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_showSideMenu, "field 'btnShowSideMenu' and method 'onViewClicked'");
    target.btnShowSideMenu = Utils.castView(view, R.id.btn_showSideMenu, "field 'btnShowSideMenu'", ImageButton.class);
    view2131230785 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.titleBar, "field 'titleBar'", LinearLayout.class);
    target.loopView = Utils.findRequiredViewAsType(source, R.id.loopView, "field 'loopView'", Banner.class);
    target.constraintParent = Utils.findRequiredViewAsType(source, R.id.constraintParent, "field 'constraintParent'", ConstraintLayout.class);
    target.gridRv = Utils.findRequiredViewAsType(source, R.id.gridView, "field 'gridRv'", RecyclerView.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", LinearLayout.class);
    target.marqueeView = Utils.findRequiredViewAsType(source, R.id.marqueeView, "field 'marqueeView'", MarqueeView.class);
    target.mNavigationView = Utils.findRequiredViewAsType(source, R.id.navigation, "field 'mNavigationView'", NavigationView.class);
    target.mDrawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'mDrawerLayout'", DrawerLayout.class);
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
    MainContentFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnShowSideMenu = null;
    target.titleBar = null;
    target.loopView = null;
    target.constraintParent = null;
    target.gridRv = null;
    target.listView = null;
    target.marqueeView = null;
    target.mNavigationView = null;
    target.mDrawerLayout = null;

    view2131230785.setOnClickListener(null);
    view2131230785 = null;
    view2131231072.setOnClickListener(null);
    view2131231072 = null;
  }
}
