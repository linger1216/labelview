package com.lid.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LabelViewHelper {

    private static final int LEFT_TOP = 1;
    private static final int RIGHT_TOP = 2;
    private static final int LEFT_BOTTOM = 3;
    private static final int RIGHT_BOTTOM = 4;

    private static final int DEFAULT_DISTANCE = 40;
    private static final int DEFAULT_HEIGHT = 20;
    private static final int DEFAULT_STROKE_WIDTH = 1;
    private static final int DEFAULT_TEXT_SIZE = 14;
    private static final int DEFAULT_BACKGROUND_COLOR = 0x9F27CDC0;
    private static final int DEFAULT_STROKE_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_TEXT_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_ORIENTATION = LEFT_TOP;
    private static final int DEFAULT_TEXT_STYLE = 0;

    private int distance;
    private int height;
    private int strokeWidth;
    private String text;
    private int backgroundColor;
    private int strokeColor;
    private int textSize;
    private int textStyle;
    private int textColor;
    private boolean visual;
    private int orientation;

//    private float startPosX;
//    private float startPosY;
//    private float endPosX;
//    private float endPosY;

    private Paint rectPaint;
    private Paint rectStrokePaint;
    // simulator
    private Path rectPath;
    private Path textPath;
    private Paint textPaint;
    private Rect textBound;

    private Context context;
    private int alpha;

    public LabelViewHelper(Context context, AttributeSet attrs, int defStyleAttr) {

        this.context = context;

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.LabelView, defStyleAttr, 0);
        distance = attributes.getDimensionPixelSize(R.styleable.LabelView_label_distance, dip2Px(DEFAULT_DISTANCE));
        height = attributes.getDimensionPixelSize(R.styleable.LabelView_label_height, dip2Px(DEFAULT_HEIGHT));
        strokeWidth = attributes.getDimensionPixelSize(R.styleable.LabelView_label_strokeWidth, dip2Px(DEFAULT_STROKE_WIDTH));
        text = attributes.getString(R.styleable.LabelView_label_text);
        backgroundColor = attributes.getColor(R.styleable.LabelView_label_backgroundColor, DEFAULT_BACKGROUND_COLOR);
        strokeColor = attributes.getColor(R.styleable.LabelView_label_strokeColor, DEFAULT_STROKE_COLOR);
        textSize = attributes.getDimensionPixelSize(R.styleable.LabelView_label_textSize, dip2Px(DEFAULT_TEXT_SIZE));
        textStyle = attributes.getInt(R.styleable.LabelView_label_textStyle, DEFAULT_TEXT_STYLE);
        textColor = attributes.getColor(R.styleable.LabelView_label_textColor, DEFAULT_TEXT_COLOR);
        visual = attributes.getBoolean(R.styleable.LabelView_label_visual, true);
        orientation = attributes.getInteger(R.styleable.LabelView_label_orientation, DEFAULT_ORIENTATION);
        attributes.recycle();

        rectPaint = new Paint();
        rectPaint.setDither(true);
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Paint.Style.FILL);

        rectStrokePaint = new Paint();
        rectStrokePaint.setDither(true);
        rectStrokePaint.setAntiAlias(true);
        rectStrokePaint.setStyle(Paint.Style.STROKE);

        rectPath = new Path();
        rectPath.reset();

        textPath = new Path();
        textPath.reset();

        textPaint = new Paint();
        textPaint.setDither(true);
        textPaint.setAntiAlias(true);
        textPaint.setStrokeJoin(Paint.Join.ROUND);
        textPaint.setStrokeCap(Paint.Cap.SQUARE);

        textBound = new Rect();
    }

    public void onDraw(Canvas canvas, int measuredWidth, int measuredHeight) {
        if (!visual || text == null) {
            return;
        }

        float actualDistance = distance + height / 2;
        calcOffset(measuredWidth, measuredHeight);

        rectPaint.setColor(backgroundColor);
        if (alpha != 0) {
            rectPaint.setAlpha(alpha);
        }

        rectStrokePaint.setColor(strokeColor);
        rectStrokePaint.setStrokeWidth(strokeWidth);

        canvas.drawPath(rectPath, rectPaint);
        canvas.drawPath(rectPath, rectStrokePaint);

        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.getTextBounds(text, 0, text.length(), textBound);
        textPaint.setTypeface(Typeface.defaultFromStyle(textStyle));

        float begin_w_offset = (1.4142135f * actualDistance) / 2 - textBound.width() / 2;
        if (begin_w_offset < 0) begin_w_offset = 0;

        canvas.drawTextOnPath(text, textPath, begin_w_offset, textBound.height() / 2, textPaint);
    }

    private void calcOffset(int measuredWidth, int measuredHeight) {
        float startPosX = measuredWidth - distance - height;
        float endPosX = measuredWidth;
        float startPosY = measuredHeight - distance - height;
        float endPosY = measuredHeight;

        float middle = height/2;

        switch (orientation) {
            case 1: // LEFT_TOP

                rectPath.reset();
                rectPath.moveTo(0, distance);
                rectPath.lineTo(distance, 0);
                rectPath.lineTo(distance + height, 0);
                rectPath.lineTo(0, distance + height);
                rectPath.close();

                textPath.reset();
                textPath.moveTo(0, distance + middle);
                textPath.lineTo(distance + middle, 0);
                textPath.close();

                break;
            case 2: // RIGHT_TOP

                rectPath.reset();
                rectPath.moveTo(startPosX, 0);
                rectPath.lineTo(startPosX + height, 0);
                rectPath.lineTo(endPosX, distance);
                rectPath.lineTo(endPosX, distance + height);
                rectPath.close();

                textPath.reset();
                textPath.moveTo(startPosX + middle, 0);
                textPath.lineTo(endPosX, distance + middle);
                textPath.close();

                break;
            case 3: // LEFT_BOTTOM

                rectPath.reset();
                rectPath.moveTo(0, startPosY);
                rectPath.lineTo(distance + height, endPosY);
                rectPath.lineTo(distance, endPosY);
                rectPath.lineTo(0, startPosY + height);
                rectPath.close();

                textPath.reset();
                textPath.moveTo(0, startPosY + middle);
                textPath.lineTo(distance + middle, endPosY);
                textPath.close();

                break;
            case 4: // RIGHT_BOTTOM

                rectPath.reset();
                rectPath.moveTo(startPosX, endPosY);
                rectPath.lineTo(measuredWidth, startPosY);
                rectPath.lineTo(measuredWidth, startPosY + height);
                rectPath.lineTo(startPosX + height, endPosY);
                rectPath.close();

                textPath.reset();
                textPath.moveTo(startPosX + middle, endPosY);
                textPath.lineTo(endPosX, startPosY + middle);
                textPath.close();

                break;
        }
    }

    private int dip2Px(float dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    private int px2Dip(float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public void setLabelHeight(View view, int height) {
        if (this.height != dip2Px(height)) {
            this.height = dip2Px(height);
            view.invalidate();
        }
    }

    public int getLabelHeight() {
        return px2Dip(this.height);
    }

    public void setLabelDistance(View view, int distance) {
        if (this.distance != dip2Px(distance)) {
            this.distance = dip2Px(distance);
            view.invalidate();
        }
    }

    public int getLabelStrokeWidth() {
        return px2Dip(this.strokeWidth);
    }

    public void setLabelStrokeWidth(View view, int strokeWidth) {
        if (this.strokeWidth != dip2Px(strokeWidth)) {
            this.strokeWidth = dip2Px(strokeWidth);
            view.invalidate();
        }
    }

    public int getLabelDistance() {
        return px2Dip(this.distance);
    }

    public boolean isLabelVisual() {
        return visual;
    }

    public void setLabelVisual(View view, boolean visual) {
        if (this.visual != visual) {
            this.visual = visual;
            view.invalidate();
        }
    }

    public int getLabelOrientation() {
        return orientation;
    }

    public void setLabelOrientation(View view, int orientation) {
        if (this.orientation != orientation && orientation <= 4 && orientation >= 1) {
            this.orientation = orientation;
            view.invalidate();
        }
    }

    public int getLabelTextColor() {
        return textColor;
    }

    public void setLabelTextColor(View view, int textColor) {
        if (this.textColor != textColor) {
            this.textColor = textColor;
            view.invalidate();
        }
    }

    public int getLabelBackgroundColor() {
        return backgroundColor;
    }

    public void setLabelBackgroundColor(View view, int backgroundColor) {
        if (this.backgroundColor != backgroundColor) {
            this.backgroundColor = backgroundColor;
            view.invalidate();
        }
    }

    public int getLabelStrokeColor() {
        return strokeColor;
    }

    public void setLabelStrokeColor(View view, int strokeColor) {
        if (this.strokeColor != strokeColor) {
            this.strokeColor = strokeColor;
            view.invalidate();
        }
    }


    public void setLabelBackgroundAlpha(View view, int alpha) {
        if (this.alpha != alpha) {
            this.alpha = alpha;
            view.invalidate();
        }
    }

    public String getLabelText() {
        return text;
    }

    public void setLabelText(View view, String text) {
        if (this.text == null || !this.text.equals(text)) {
            this.text = text;
            view.invalidate();
        }
    }

    public int getLabelTextSize() {
        return px2Dip(this.textSize);
    }

    public void setLabelTextSize(View view, int textSize) {
        if (this.textSize != textSize) {
            this.textSize = textSize;
            view.invalidate();
        }
    }

    public int getLabelTextStyle(){
        return textStyle;
    }

    public void setLabelTextStyle(View view, int textStyle){
        if(this.textStyle == textStyle) return;
        this.textStyle = textStyle;
        view.invalidate();
    }
}