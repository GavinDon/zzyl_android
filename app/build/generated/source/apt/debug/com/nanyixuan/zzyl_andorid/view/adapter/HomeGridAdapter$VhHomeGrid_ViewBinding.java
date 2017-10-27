// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeGridAdapter$VhHomeGrid_ViewBinding implements Unbinder {
  private HomeGridAdapter.VhHomeGrid target;

  @UiThread
  public HomeGridAdapter$VhHomeGrid_ViewBinding(HomeGridAdapter.VhHomeGrid target, View source) {
    this.target = target;

    target.adaHomeGridIv = Utils.findRequiredViewAsType(source, R.id.ada_home_grid_iv, "field 'adaHomeGridIv'", ImageView.class);
    target.adaHomeGridTv = Utils.findRequiredViewAsType(source, R.id.ada_home_grid_tv, "field 'adaHomeGridTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeGridAdapter.VhHomeGrid target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.adaHomeGridIv = null;
    target.adaHomeGridTv = null;
  }
}
