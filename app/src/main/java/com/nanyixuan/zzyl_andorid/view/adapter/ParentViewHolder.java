package com.nanyixuan.zzyl_andorid.view.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.bean.DataBean;

/**
 * description:父布局viewHolder
 * Created by liNan on 2017/9/16 15:18
 */

public class ParentViewHolder extends BaseViewHolder {

    private Context mContext;
    private View view;
    private ConstraintLayout containerLayout;
    private TextView parentLeftView;
    private ImageView expand;
    private View parentDashedView;
    public ParentViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final DataBean dataBean, final int pos, final ItemClickListener listener){

        containerLayout = (ConstraintLayout) view.findViewById(R.id.container);
        parentLeftView = (TextView) view.findViewById(R.id.parent_title);
        expand = (ImageView) view.findViewById(R.id.expand);
        parentDashedView = view.findViewById(R.id.divider);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) expand
                .getLayoutParams();
        expand.setLayoutParams(params);
        parentLeftView.setText(dataBean.getParentLeftTxt());

        if (dataBean.isExpand()) {
            expand.setRotation(180);
            parentDashedView.setVisibility(View.INVISIBLE);
        } else {
            expand.setRotation(0);
            parentDashedView.setVisibility(View.VISIBLE);
        }

        //父布局OnClick监听
        containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (dataBean.isExpand()) {
                        listener.onHideChildren(dataBean);
                        dataBean.setExpand(false);
                        parentDashedView.setVisibility(View.VISIBLE);
                        rotationExpandIcon(180, 0);
                    } else {
                        listener.onExpandChildren(dataBean);
                        dataBean.setExpand(true);
                        parentDashedView.setVisibility(View.INVISIBLE);
                        rotationExpandIcon(0, 180);
                    }
                }
            }
        });
    }

    private void rotationExpandIcon(float from, float to) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);//属性动画
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    expand.setRotation((Float) valueAnimator.getAnimatedValue());
                }
            });
            valueAnimator.start();
    }
}
