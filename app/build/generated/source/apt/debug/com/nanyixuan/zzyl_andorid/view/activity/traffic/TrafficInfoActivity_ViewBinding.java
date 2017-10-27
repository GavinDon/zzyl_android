// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity.traffic;

import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrafficInfoActivity_ViewBinding extends BaseActivity_ViewBinding {
  private TrafficInfoActivity target;

  @UiThread
  public TrafficInfoActivity_ViewBinding(TrafficInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrafficInfoActivity_ViewBinding(TrafficInfoActivity target, View source) {
    super(target, source);

    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'mViewPager'", ViewPager.class);
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'mTabLayout'", TabLayout.class);
  }

  @Override
  public void unbind() {
    TrafficInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewPager = null;
    target.mTabLayout = null;

    super.unbind();
  }
}
