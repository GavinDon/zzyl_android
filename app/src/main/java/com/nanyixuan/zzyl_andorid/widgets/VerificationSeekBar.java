package com.nanyixuan.zzyl_andorid.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**description: 滑动验证
 * Created by linan on 2017\9\27 0027.
 */

public class VerificationSeekBar extends AppCompatSeekBar {
    private int index = 150;
    private boolean k = true;

    public VerificationSeekBar(Context context) {
        super(context);
    }

    public VerificationSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerificationSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            k = true;
            if (x - index > 20) {
                k = false;
                return true;
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            if (!k){
                return true;
            }
        }
        return super.dispatchTouchEvent(event);
    }

}
