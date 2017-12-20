package com.example.txtled.customcalendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by KomoriWu on 2017/12/20.
 */

public class CircleDrawable extends Drawable {

    private float mWidth,mHeight;
    private Paint mTodayPaint;

    public CircleDrawable(float mWidth, float mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        mTodayPaint = new Paint();
        mTodayPaint.setAntiAlias(true);
        mTodayPaint.setStyle(Paint.Style.FILL);
        mTodayPaint.setDither(true);
        mTodayPaint.setColor(Color.GREEN);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(0,0,(mHeight/2),mTodayPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
