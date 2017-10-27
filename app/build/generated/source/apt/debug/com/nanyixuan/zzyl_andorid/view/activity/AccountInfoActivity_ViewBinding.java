// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AccountInfoActivity_ViewBinding extends BaseActivity_ViewBinding {
  private AccountInfoActivity target;

  @UiThread
  public AccountInfoActivity_ViewBinding(AccountInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccountInfoActivity_ViewBinding(AccountInfoActivity target, View source) {
    super(target, source);

    this.target = target;

    target.tvAccount = Utils.findRequiredViewAsType(source, R.id.tv_account, "field 'tvAccount'", TextView.class);
  }

  @Override
  public void unbind() {
    AccountInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvAccount = null;

    super.unbind();
  }
}
