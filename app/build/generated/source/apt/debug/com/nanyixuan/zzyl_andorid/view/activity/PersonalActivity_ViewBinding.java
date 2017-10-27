// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PersonalActivity_ViewBinding extends BaseActivity_ViewBinding {
  private PersonalActivity target;

  @UiThread
  public PersonalActivity_ViewBinding(PersonalActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PersonalActivity_ViewBinding(PersonalActivity target, View source) {
    super(target, source);

    this.target = target;

    target.itemRoot = Utils.findRequiredViewAsType(source, R.id.item_root, "field 'itemRoot'", LinearLayout.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tv_login, "field 'tvLogin'", TextView.class);
  }

  @Override
  public void unbind() {
    PersonalActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.itemRoot = null;
    target.tvLogin = null;

    super.unbind();
  }
}
