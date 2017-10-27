// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import com.nanyixuan.zzyl_andorid.widgets.SwitchButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding extends BaseActivity_ViewBinding {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    super(target, source);

    this.target = target;

    target.sbGps = Utils.findRequiredViewAsType(source, R.id.sb_gps, "field 'sbGps'", SwitchButton.class);
    target.sbMsgPush = Utils.findRequiredViewAsType(source, R.id.sb_msg_push, "field 'sbMsgPush'", SwitchButton.class);
  }

  @Override
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sbGps = null;
    target.sbMsgPush = null;

    super.unbind();
  }
}
