package com.lid.labelviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class LabelView extends TextView {

    private float _offsetx;
    private float _offsety;
    private float _anchorx;
    private float _anchory;
    private float _angel;

    private Animation _animation = new Animation() {
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            Matrix tran = t.getMatrix();
            tran.postTranslate(_offsetx, _offsety);
            tran.postRotate(_angel, _anchorx, _anchory);
        }
    };

    public enum Gravity{
        LEFT_TOP,RIGHT_TOP
    }

    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();

        _animation.setFillBefore(true);
        _animation.setFillAfter(true);
        _animation.setFillEnabled(true);

    }




    private void init() {

        if (!(getLayoutParams() instanceof ViewGroup.LayoutParams)) {
            LayoutParams layoutParams =
                    new LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            setLayoutParams(layoutParams);
        }

        // the default value
        setPadding(dip2Px(40), dip2Px(2), dip2Px(40), dip2Px(2));
        setTextColor(Color.WHITE);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        setBackgroundColor(Color.BLUE);
    }

    public void setTargetView(View target, int distance, Gravity gravity) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }

        if (target == null) {
            return;
        }

        if (target.getParent() instanceof FrameLayout) {
            ((FrameLayout) target.getParent()).addView(this);

        } else if (target.getParent() instanceof ViewGroup) {
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int groupIndex = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);

            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentlayoutParam = target.getLayoutParams();

            badgeContainer.setLayoutParams(parentlayoutParam);
            target.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parentContainer.addView(badgeContainer, groupIndex, parentlayoutParam);
            badgeContainer.addView(target);

            badgeContainer.addView(this);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        }

        final int d = dip2Px(distance);
        final Gravity g = gravity;
        final View v = target;

        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {

                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int w = getMeasuredWidth();

                float edge = (float) ((w - 2 * d) / (2 * 1.414));
                if(g ==  Gravity.LEFT_TOP)
                {
                    _anchorx = - edge;
                    _offsetx = _anchorx;
                    _angel = -45;
                }
                else if(g ==  Gravity.RIGHT_TOP)
                {
                    _offsetx = v.getMeasuredWidth() + edge - w;
                    _anchorx = v.getMeasuredWidth() + edge;
                    _angel = 45;
                }

                _anchory = (float) (1.414 * d + edge);
                _offsety = _anchory;

                clearAnimation();
                startAnimation(_animation);
            }
        });





    }

    /*
     * converts dip to px
     */
    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

}
