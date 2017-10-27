// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.fragment;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TourRouteFragment_ViewBinding extends BaseActivity_ViewBinding {
  private TourRouteFragment target;

  @UiThread
  public TourRouteFragment_ViewBinding(TourRouteFragment target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TourRouteFragment_ViewBinding(TourRouteFragment target, View source) {
    super(target, source);

    this.target = target;

    target.titleBar = Utils.findRequiredViewAsType(source, R.id.titleBar, "field 'titleBar'", LinearLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", ListView.class);
  }

  @Override
  public void unbind() {
    TourRouteFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleBar = null;
    target.listView = null;

    super.unbind();
  }
}
