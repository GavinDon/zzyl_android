package com.nanyixuan.zzyl_andorid.widgets;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.nanyixuan.zzyl_andorid.R;

/**
 * </封装简单的通用对话框> 
 * Author linan 
 * Date：2016年9月18日 下午1:56:02
 */
public class SimpleDialog extends Dialog implements View.OnClickListener {
	private Context mContext;
	private Button btnConfirm;
	private Button btnCancle;
	private TextView mTitleTextView;
	private TextView mContentTextView;
	private String mTitleText;
	private String mContentText;
	private boolean mShowCancel;
	private boolean mShowContent;
	private AlertDialog mDialog;
	private OnSweetClickListener mConfirmClickListener;
	private OnSweetClickListener mCancelClickListener;

	public  interface OnSweetClickListener {
		 void onClick(SimpleDialog simpleDialog);
	}

	/**
	 * 确定按扭监听
	 */
	public SimpleDialog setCancelClickListener(OnSweetClickListener listener) {
		mCancelClickListener = listener;
		return this;
	}

	/**
	 * 取消按扭监听
	 */
	public SimpleDialog setConfirmClickListener(OnSweetClickListener listener) {
		mConfirmClickListener = listener;
		return this;
	}

	public SimpleDialog(Context context) {
		this(context, 0);
		// this.mContext = context;
		// initView();
	}

	public SimpleDialog(Context context, int theme) {
		super(context, R.style.loadingDialogStyle);
		this.mContext = context;
		initView();
	}

	/**
	 * 初始化对话框，设置参数并显示
	 */
	private void initView() {
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.show();
		Window mWindow = mDialog.getWindow();
		View view = LayoutInflater.from(mContext).inflate(R.layout.custome_dialog, null);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		btnConfirm = (Button) view.findViewById(R.id.dialog_ok);
		btnCancle = (Button) view.findViewById(R.id.dialog_cancel);
		mTitleTextView = (TextView) view.findViewById(R.id.dialog_title);
		mContentTextView = (TextView) view.findViewById(R.id.dialog_text);
		setTitle(mTitleText);
		setContentText(mContentText);
		btnCancle.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		mWindow.setContentView(view, params);
		mWindow.setGravity(Gravity.CENTER);
	}

	/**
	 * 设置标题
	 */
	public SimpleDialog setTitle(String text) {
		mTitleText = text;
		if (mTitleTextView != null && mTitleText != null) {
			mTitleTextView.setText(mTitleText);
		}
		return this;

	}

	/**
	 * 设置对话框显示的内容
	 */
	public SimpleDialog setContentText(String text) {
		mContentText = text;
		if (mContentTextView != null && mContentText != null) {
			showContentText(true);
			mContentTextView.setText(mContentText);
		}
		return this;
	}

	public SimpleDialog showContentText(boolean isShow) {
		mShowContent = isShow;
		if (mContentTextView != null) {
			mContentTextView.setVisibility(mShowContent ? View.VISIBLE : View.GONE);
		}
		return this;
	}

	/**
	 * 是否显示取消按扭
	 */
	public SimpleDialog showCancelButton(boolean isShow) {
		mShowCancel = isShow;
		if (btnCancle != null) {
			btnCancle.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
		}
		return this;
	}

	/**
	 * 是否显示确定按扭
	 */
	public SimpleDialog showConfirmButton(boolean isShow) {
		mShowCancel = isShow;
		if (btnConfirm != null) {
			btnConfirm.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
		}
		return this;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.dialog_cancel) {
			if (mCancelClickListener != null) {
				mCancelClickListener.onClick(SimpleDialog.this);
			} else {
				mDialog.dismiss();
			}
		} else if (v.getId() == R.id.dialog_ok) {
			if (mConfirmClickListener != null) {
				mConfirmClickListener.onClick(SimpleDialog.this);
			} else {
				mDialog.dismiss();
			}
		}

	}

	/**
	 * 让对话框消失
	 */
	public void dismiss() {
		mDialog.cancel();

	}

}
