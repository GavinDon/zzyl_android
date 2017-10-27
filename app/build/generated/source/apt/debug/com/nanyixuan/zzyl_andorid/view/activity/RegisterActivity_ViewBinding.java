// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding extends BaseActivity_ViewBinding {
  private RegisterActivity target;

  private View view2131230891;

  private TextWatcher view2131230891TextWatcher;

  private View view2131230774;

  private View view2131231182;

  private View view2131230775;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.et_register_phone, "field 'etRegisterPhone' and method 'onTextChanged'");
    target.etRegisterPhone = Utils.castView(view, R.id.et_register_phone, "field 'etRegisterPhone'", AppCompatEditText.class);
    view2131230891 = view;
    view2131230891TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onTextChanged(p0, p1, p2, p3);
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131230891TextWatcher);
    view = Utils.findRequiredView(source, R.id.btn_close, "field 'btnClose' and method 'onclick'");
    target.btnClose = Utils.castView(view, R.id.btn_close, "field 'btnClose'", ImageView.class);
    view2131230774 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.etRegisterSms = Utils.findRequiredViewAsType(source, R.id.et_register_sms, "field 'etRegisterSms'", AppCompatEditText.class);
    view = Utils.findRequiredView(source, R.id.tv_sms_timer, "field 'tvSmsTimer' and method 'onclick'");
    target.tvSmsTimer = Utils.castView(view, R.id.tv_sms_timer, "field 'tvSmsTimer'", TextView.class);
    view2131231182 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    target.etRegisterPsw = Utils.findRequiredViewAsType(source, R.id.et_register_psw, "field 'etRegisterPsw'", AppCompatEditText.class);
    target.etRegisterAgainPsw = Utils.findRequiredViewAsType(source, R.id.et_register_againPsw, "field 'etRegisterAgainPsw'", AppCompatEditText.class);
    view = Utils.findRequiredView(source, R.id.btn_confirm, "field 'btnRegisterConfirm' and method 'onclick'");
    target.btnRegisterConfirm = Utils.castView(view, R.id.btn_confirm, "field 'btnRegisterConfirm'", Button.class);
    view2131230775 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });

    Context context = source.getContext();
    target.lightGreen = ContextCompat.getColor(context, R.color.lightGreen);
    target.pressGreen = ContextCompat.getColor(context, R.color.pressGreen);
  }

  @Override
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etRegisterPhone = null;
    target.btnClose = null;
    target.etRegisterSms = null;
    target.tvSmsTimer = null;
    target.etRegisterPsw = null;
    target.etRegisterAgainPsw = null;
    target.btnRegisterConfirm = null;

    ((TextView) view2131230891).removeTextChangedListener(view2131230891TextWatcher);
    view2131230891TextWatcher = null;
    view2131230891 = null;
    view2131230774.setOnClickListener(null);
    view2131230774 = null;
    view2131231182.setOnClickListener(null);
    view2131231182 = null;
    view2131230775.setOnClickListener(null);
    view2131230775 = null;

    super.unbind();
  }
}
