package com.hq.uitest.aigeselfview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/6/7.
 */

public class SecondView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private boolean isClick = false;


    public SecondView(Context context) {
        this(context,null);
    }

    public SecondView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SecondView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setDither(true);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClick){
                    mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF,0x00FF00FF));
                }else{
                    mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF,0x00000000));
                }
                isClick = !isClick;
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,getWidth() / 3,  getHeight() / 3,mPaint);
    }
}
