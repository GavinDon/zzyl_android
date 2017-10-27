// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.nanyixuan.zzyl_andorid.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UrlContentActivity_ViewBinding implements Unbinder {
  private UrlContentActivity target;

  private View view2131230927;

  private View view2131231191;

  private View view2131231190;

  @UiThread
  public UrlContentActivity_ViewBinding(UrlContentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UrlContentActivity_ViewBinding(final UrlContentActivity target, View source) {
    this.target = target;

    View view;
    target.mWebView = Utils.findRequiredViewAsType(source, R.id.webView, "field 'mWebView'", WebView.class);
    view = Utils.findRequiredView(source, R.id.ib_qrcode, "field 'btnShowQR' and method 'showQR'");
    target.btnShowQR = Utils.castView(view, R.id.ib_qrcode, "field 'btnShowQR'", ImageButton.class);
    view2131230927 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showQR();
      }
    });
    target.urlTitle = Utils.findRequiredViewAsType(source, R.id.tv_url_title, "field 'urlTitle'", TextView.class);
    target.tvError = Utils.findRequiredViewAsType(source, R.id.tv_error, "field 'tvError'", TextView.class);
    target.avi = Utils.findRequiredViewAsType(source, R.id.loading, "field 'avi'", AVLoadingIndicatorView.class);
    target.mImageViewLoad = Utils.findRequiredViewAsType(source, R.id.ll_loading, "field 'mImageViewLoad'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_url_close, "method 'closeActivity'");
    view2131231191 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeActivity(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_url_back, "method 'closeActivity'");
    view2131231190 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeActivity(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UrlContentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebView = null;
    target.btnShowQR = null;
    target.urlTitle = null;
    target.tvError = null;
    target.avi = null;
    target.mImageViewLoad = null;

    view2131230927.setOnClickListener(null);
    view2131230927 = null;
    view2131231191.setOnClickListener(null);
    view2131231191 = null;
    view2131231190.setOnClickListener(null);
    view2131231190 = null;
  }
}
