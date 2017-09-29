package com.nanyixuan.zzyl_andorid.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.nanyixuan.zzyl_andorid.utils.DisplayUtil;

/**
 * description: 缩进
 * Created by liNan on 2017/8/4 9:34
 */

public class AutoSplitTextView extends AppCompatTextView {

    public AutoSplitTextView(Context context) {
        this(context, null);
    }

    public AutoSplitTextView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public AutoSplitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        autoSplitTextView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void autoSplitTextView() {
        String text = this.getText().toString();
        float textWidth = this.getPaint().measureText(text);
        String[] itemText = text.split("\n");
        int dpW = DisplayUtil.px2dip(this.getContext(), getWidth()); // 获取屏幕宽度 dp
        int textDp = DisplayUtil.px2dip(this.getContext(), this.getTextSize());
        int limit = dpW / textDp;

        if (dpW == 0) {
            throw new RuntimeException("Please set width !");
        } else {

            for (int j = 0; j < itemText.length; j++) {
                int noticeLength = itemText[j].length();
                int size = noticeLength / limit + (noticeLength % limit != 0 ? 1 : 0); //得到行数
                int ulWidth = (int) this.getPaint().measureText("1、");
                for (int i = 0; i < size; i++) {
                    int startIndex = i * limit;
                    int endIndex = ((i + 1) * limit >= noticeLength ? noticeLength : (i + 1) * limit);
                    String mar = itemText[i].substring(startIndex, endIndex);
                    if (i >= 1) {
                        mar = mar + "d";
                    }
                }
            }
        }


    }


}
