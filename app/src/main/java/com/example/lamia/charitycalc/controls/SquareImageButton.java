package com.example.lamia.charitycalc.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by Lamia on 17-Jun-17.
 */

public class SquareImageButton extends ImageButton {

    public SquareImageButton (Context context) {
        super(context);
    }

    public SquareImageButton (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageButton (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}