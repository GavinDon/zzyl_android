// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderListActivity_ViewBinding extends BaseActivity_ViewBinding {
  private OrderListActivity target;

  @UiThread
  public OrderListActivity_ViewBinding(OrderListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderListActivity_ViewBinding(OrderListActivity target, View source) {
    super(target, source);

    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_order_list, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    OrderListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;

    super.unbind();
  }
}
