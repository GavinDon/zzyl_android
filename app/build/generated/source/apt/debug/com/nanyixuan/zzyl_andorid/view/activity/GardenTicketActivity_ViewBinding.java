// Generated code from Butter Knife. Do not modify!
package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity_ViewBinding;
import com.nanyixuan.zzyl_andorid.widgets.SubmitButton;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GardenTicketActivity_ViewBinding extends BaseActivity_ViewBinding {
  private GardenTicketActivity target;

  private View view2131230806;

  private View view2131231093;

  private View view2131230885;

  private TextWatcher view2131230885TextWatcher;

  private View view2131230884;

  private TextWatcher view2131230884TextWatcher;

  @UiThread
  public GardenTicketActivity_ViewBinding(GardenTicketActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GardenTicketActivity_ViewBinding(final GardenTicketActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.checkBox, "field 'checkBox' and method 'onclick'");
    target.checkBox = Utils.castView(view, R.id.checkBox, "field 'checkBox'", AppCompatCheckBox.class);
    view2131230806 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.submitBtn, "field 'mSubmitButton' and method 'onclick'");
    target.mSubmitButton = Utils.castView(view, R.id.submitBtn, "field 'mSubmitButton'", SubmitButton.class);
    view2131231093 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.etIdCard, "field 'etIdCard', method 'OnFocusChange', and method 'onTextChanged'");
    target.etIdCard = Utils.castView(view, R.id.etIdCard, "field 'etIdCard'", AppCompatEditText.class);
    view2131230885 = view;
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View p0, boolean p1) {
        target.OnFocusChange(p0, p1);
      }
    });
    view2131230885TextWatcher = new TextWatcher() {
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
    ((TextView) view).addTextChangedListener(view2131230885TextWatcher);
    view = Utils.findRequiredView(source, R.id.etAgainIdCard, "field 'etAgainIdCard', method 'OnFocusChange', and method 'onTextChanged'");
    target.etAgainIdCard = Utils.castView(view, R.id.etAgainIdCard, "field 'etAgainIdCard'", AppCompatEditText.class);
    view2131230884 = view;
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View p0, boolean p1) {
        target.OnFocusChange(p0, p1);
      }
    });
    view2131230884TextWatcher = new TextWatcher() {
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
    ((TextView) view).addTextChangedListener(view2131230884TextWatcher);
    target.ticketDrawView = Utils.findRequiredViewAsType(source, R.id.card01, "field 'ticketDrawView'", SimpleDraweeView.class);
    target.tvyMoney = Utils.findRequiredViewAsType(source, R.id.tv_ticket_paymoney, "field 'tvyMoney'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_ticket_money, "field 'tvMoney'", TextView.class);
    target.underLineMoney = Utils.findRequiredViewAsType(source, R.id.tv_underline_money, "field 'underLineMoney'", TextView.class);
    target.tvUserTime = Utils.findRequiredViewAsType(source, R.id.tv_ticket_userTime, "field 'tvUserTime'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.garden_intro, "field 'mRecyclerView'", RecyclerView.class);
    target.tvTicketName = Utils.findRequiredViewAsType(source, R.id.tv_ticket_name, "field 'tvTicketName'", TextView.class);

    Context context = source.getContext();
    target.lightGreen = ContextCompat.getColor(context, R.color.lightGreen);
  }

  @Override
  public void unbind() {
    GardenTicketActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.checkBox = null;
    target.mSubmitButton = null;
    target.etIdCard = null;
    target.etAgainIdCard = null;
    target.ticketDrawView = null;
    target.tvyMoney = null;
    target.tvMoney = null;
    target.underLineMoney = null;
    target.tvUserTime = null;
    target.mRecyclerView = null;
    target.tvTicketName = null;

    view2131230806.setOnClickListener(null);
    view2131230806 = null;
    view2131231093.setOnClickListener(null);
    view2131231093 = null;
    view2131230885.setOnFocusChangeListener(null);
    ((TextView) view2131230885).removeTextChangedListener(view2131230885TextWatcher);
    view2131230885TextWatcher = null;
    view2131230885 = null;
    view2131230884.setOnFocusChangeListener(null);
    ((TextView) view2131230884).removeTextChangedListener(view2131230884TextWatcher);
    view2131230884TextWatcher = null;
    view2131230884 = null;

    super.unbind();
  }
}
