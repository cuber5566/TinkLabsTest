package com.labs.tink.tinklabstechnicalcodingtest.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.labs.tink.tinklabstechnicalcodingtest.R;

public class AutoScaleRelativeLayout extends RelativeLayout {

    int scaleX, scaleY;
    boolean byHeight;
    boolean paddingEnable;

    public AutoScaleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoScaleRelativeLayout);
        scaleX = a.getInteger(R.styleable.AutoScaleRelativeLayout_scaleWidth, 1);
        scaleY = a.getInteger(R.styleable.AutoScaleRelativeLayout_scaleHeight, 1);
        byHeight = a.getBoolean(R.styleable.AutoScaleRelativeLayout_byHeight, false);
        paddingEnable = a.getBoolean(R.styleable.AutoScaleRelativeLayout_paddingEnable, false);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int measureSpecWidth, measureSpecHeight;
        int paddingHeight = getPaddingTop() + getPaddingBottom();
        int paddingWidth = getPaddingLeft() + getPaddingRight();

        if (byHeight) {
            measureSpecWidth = MeasureSpec.makeMeasureSpec((height / scaleY * scaleX) + (paddingEnable ? paddingWidth : 0), MeasureSpec.EXACTLY);
            measureSpecHeight = MeasureSpec.makeMeasureSpec(height + (paddingEnable ? paddingHeight : 0), MeasureSpec.EXACTLY);

        } else {
            measureSpecWidth = MeasureSpec.makeMeasureSpec(width + (paddingEnable ? paddingWidth : 0), MeasureSpec.EXACTLY);
            measureSpecHeight = MeasureSpec.makeMeasureSpec((width * scaleY / scaleX) + (paddingEnable ? paddingHeight : 0), MeasureSpec.EXACTLY);
        }

        super.onMeasure(measureSpecWidth, measureSpecHeight);
    }
}
