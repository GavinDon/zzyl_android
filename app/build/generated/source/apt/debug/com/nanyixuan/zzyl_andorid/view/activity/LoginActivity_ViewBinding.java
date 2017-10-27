// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding extends BaseActivity_ViewBinding {
  private LoginActivity target;

  private View view2131230888;

  private View view2131230889;

  private View view2131230777;

  private View view2131231154;

  private View view2131231180;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.et_login_account, "field 'etLoginAccount' and method 'onViewClicked'");
    target.etLoginAccount = Utils.castView(view, R.id.et_login_account, "field 'etLoginAccount'", AppCompatEditText.class);
    view2131230888 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.et_login_psw, "field 'etLoginPsw' and method 'onViewClicked'");
    target.etLoginPsw = Utils.castView(view, R.id.et_login_psw, "field 'etLoginPsw'", AppCompatEditText.class);
    view2131230889 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'btnLogin' and method 'onViewClicked'");
    target.btnLogin = Utils.castView(view, R.id.btn_login, "field 'btnLogin'", AppCompatButton.class);
    view2131230777 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_forget_psw, "field 'tvForgetPsw' and method 'onViewClicked'");
    target.tvForgetPsw = Utils.castView(view, R.id.tv_forget_psw, "field 'tvForgetPsw'", TextView.class);
    view2131231154 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_register, "field 'tvRegister' and method 'onViewClicked'");
    target.tvRegister = Utils.castView(view, R.id.tv_register, "field 'tvRegister'", TextView.class);
    view2131231180 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etLoginAccount = null;
    target.etLoginPsw = null;
    target.btnLogin = null;
    target.tvForgetPsw = null;
    target.tvRegister = null;

    view2131230888.setOnClickListener(null);
    view2131230888 = null;
    view2131230889.setOnClickListener(null);
    view2131230889 = null;
    view2131230777.setOnClickListener(null);
    view2131230777 = null;
    view2131231154.setOnClickListener(null);
    view2131231154 = null;
    view2131231180.setOnClickListener(null);
    view2131231180 = null;

    super.unbind();
  }
}
