package com.example.myapplication.TP.customView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.myapplication.R;
import com.example.myapplication.TP.utils.FlashlightCone;

public class TpInterfaceTactileUnView extends SurfaceView implements Runnable {

    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;
    private Path mPathRectangle;
    private SurfaceHolder mSurfaceHolder;
    private float positionX;
    private float positionY;

    public TpInterfaceTactileUnView(Context context) {
        super(context);
    }

    public TpInterfaceTactileUnView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TpInterfaceTactileUnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void run() {
        Canvas canvas;
        while (true) {
            if (mSurfaceHolder.getSurface().isValid()) {
                canvas = mSurfaceHolder.lockCanvas();
                canvas.save();
                mPathRectangle.addRect(mViewWidth / 3, mViewHeight / 3, 2 * mViewWidth / 3, 2 * mViewHeight / 3, Path.Direction.CCW);

                if (true) {
                    mPaint.setColor(Color.RED);
                } else {
                    mPaint.setColor(Color.BLUE);
                }
                canvas.drawPath(mPathRectangle, mPaint);

                mPathRectangle.rewind();
                canvas.restore();
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE); // Win!
        mPathRectangle = new Path();
        positionX = w/2;
        positionY = h/2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        positionX = event.getX();
        positionY = event.getY();
        return true;
    }
}
