// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.api.newapi;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import com.nanyixuan.zzyl_andorid.R;
import java.lang.Deprecated;
import java.lang.Override;

public class MySubscriber_ViewBinding implements Unbinder {
  /**
   * @deprecated Use {@link #MySubscriber_ViewBinding(MySubscriber, Context)} for direct creation.
   *     Only present for runtime invocation through {@code ButterKnife.bind()}.
   */
  @Deprecated
  @UiThread
  public MySubscriber_ViewBinding(MySubscriber target, View source) {
    this(target, source.getContext());
  }

  @UiThread
  public MySubscriber_ViewBinding(MySubscriber target, Context context) {
    target.lightGreen = ContextCompat.getColor(context, R.color.lightGreen);
  }

  @Override
  @CallSuper
  public void unbind() {
  }
}
