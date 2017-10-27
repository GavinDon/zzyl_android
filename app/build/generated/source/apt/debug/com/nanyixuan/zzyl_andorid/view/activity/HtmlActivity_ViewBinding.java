// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import com.nanyixuan.zzyl_andorid.widgets.SubmitButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HtmlActivity_ViewBinding extends BaseActivity_ViewBinding {
  private HtmlActivity target;

  private View view2131230781;

  private View view2131230780;

  @UiThread
  public HtmlActivity_ViewBinding(HtmlActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HtmlActivity_ViewBinding(final HtmlActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.mWebView = Utils.findRequiredViewAsType(source, R.id.webView, "field 'mWebView'", WebView.class);
    view = Utils.findRequiredView(source, R.id.btn_paysuccess, "field 'btnSuccess' and method 'onclick'");
    target.btnSuccess = Utils.castView(view, R.id.btn_paysuccess, "field 'btnSuccess'", SubmitButton.class);
    view2131230781 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_payquestion, "field 'btnQuestion' and method 'onclick'");
    target.btnQuestion = Utils.castView(view, R.id.btn_payquestion, "field 'btnQuestion'", SubmitButton.class);
    view2131230780 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    HtmlActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebView = null;
    target.btnSuccess = null;
    target.btnQuestion = null;

    view2131230781.setOnClickListener(null);
    view2131230781 = null;
    view2131230780.setOnClickListener(null);
    view2131230780 = null;

    super.unbind();
  }
}
