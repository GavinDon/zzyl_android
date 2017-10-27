// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SubscriberCarActivity_ViewBinding extends BaseActivity_ViewBinding {
  private SubscriberCarActivity target;

  @UiThread
  public SubscriberCarActivity_ViewBinding(SubscriberCarActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SubscriberCarActivity_ViewBinding(SubscriberCarActivity target, View source) {
    super(target, source);

    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_subscriber, "field 'mRecyclerView'", RecyclerView.class);

    Context context = source.getContext();
    target.gray = ContextCompat.getColor(context, R.color.gray);
  }

  @Override
  public void unbind() {
    SubscriberCarActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;

    super.unbind();
  }
}
