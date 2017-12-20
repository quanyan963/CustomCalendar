package com.example.txtled.customcalendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by KomoriWu on 2017/12/19.
 */

@SuppressLint("AppCompatCustomView")
public class CalendarTextView extends TextView {
    public boolean isToday,isTouched;
    private Paint mTodayPaint;
    private Paint mTouchPaint;
    private float radius = 3;
    private float mIncrement = 16f / 200;
    private float mProgress;

    public CalendarTextView(Context context) {
        super(context);
        initView();
    }

    public CalendarTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CalendarTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mTodayPaint = new Paint();
        mTodayPaint.setAntiAlias(true);
        mTodayPaint.setDither(true);
        mTodayPaint.setStyle(Paint.Style.FILL);
        mTodayPaint.setColor(Color.GREEN);

        mTouchPaint = new Paint();
        mTouchPaint.setAntiAlias(true);
        mTouchPaint.setDither(true);
        mTouchPaint.setStyle(Paint.Style.STROKE);
        mTouchPaint.setStrokeWidth(3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isToday){
            setBackground(new CircleDrawable(getWidth(),getHeight()));
        }
        if (isTouched && !isToday){
            mTouchPaint.setColor(Color.GREEN);
            canvas.translate(getWidth()/2,getHeight()/2);
            if (mProgress < 1){
                mProgress = mProgress + mIncrement;
                radius = getProgressValue(0, getHeight()/2,mProgress);
                canvas.drawCircle(0,0,radius - 2,mTouchPaint);
                invalidate();
            }else {
                canvas.drawCircle(0,0,(getHeight()/2) - 2,mTouchPaint);
            }
        }else {
            mTouchPaint.setColor(Color.TRANSPARENT);
            mProgress = radius = 0;
        }

    }

    private float getProgressValue(float start, float end, float progress) {
        return start + (end - start) * progress;
    }
}
