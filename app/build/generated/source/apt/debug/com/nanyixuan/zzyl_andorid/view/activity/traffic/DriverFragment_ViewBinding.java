// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity.traffic;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.amap.api.maps.MapView;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DriverFragment_ViewBinding implements Unbinder {
  private DriverFragment target;

  @UiThread
  public DriverFragment_ViewBinding(DriverFragment target, View source) {
    this.target = target;

    target.searchLv = Utils.findRequiredViewAsType(source, R.id.search_lv, "field 'searchLv'", ListView.class);
    target.mSearchView = Utils.findRequiredViewAsType(source, R.id.searchview, "field 'mSearchView'", SearchView.class);
    target.mEditText = Utils.findRequiredViewAsType(source, R.id.replace_edittext, "field 'mEditText'", EditText.class);
    target.gaodeMapView = Utils.findRequiredViewAsType(source, R.id.gaode_mapview, "field 'gaodeMapView'", MapView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DriverFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.searchLv = null;
    target.mSearchView = null;
    target.mEditText = null;
    target.gaodeMapView = null;
  }
}
