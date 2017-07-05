package com.hq.uitest.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/6/14.
 */

public class PaintShaderPracticeImageView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private ShapeDrawable mShapeDrawable;
    private static final int FACTOR = 3 ;
    private static final int RADIUS = 100 ;
    private Matrix mMatrix;
    private BitmapShader mShader;



    public PaintShaderPracticeImageView(Context context) {
        this(context,null);
    }

    public PaintShaderPracticeImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaintShaderPracticeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        Bitmap bmp = mBitmap;
        mMatrix = new Matrix();
        bmp = Bitmap.createScaledBitmap(bmp,mBitmap.getWidth() * FACTOR ,mBitmap.getHeight() * FACTOR,true);

        mShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mShapeDrawable = new ShapeDrawable(new OvalShape());
        mShapeDrawable.getPaint().setShader(mShader);
        mShapeDrawable.setBounds(0,0,RADIUS * 2,RADIUS * 2);




        mPaint.setShader(mShader);

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBitmap.getWidth(),mBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        //画制作好的圆形图片
        if(!(dx == 0 && dy ==0 ) && dx< getWidth()&&dy<getHeight() ) {
//            mShapeDrawable.draw(canvas);

            Matrix matrix = new Matrix();
            matrix.setTranslate(-dx * (FACTOR - 1) , -dy * (FACTOR -1 ) );
            mShader.setLocalMatrix(matrix);
            mPaint.setShader(mShader);
            canvas.drawCircle(dx,dy,RADIUS,mPaint);
        }

    }


    private int dx,dy;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                dx = (int) event.getX();
                dy = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                dx = 0;
                dy = 0;
                break;
        }
//        mMatrix.setTranslate(RADIUS - dx*FACTOR, RADIUS - dy*FACTOR);
//        mMatrix.setTranslate(dx * FACTOR - RADIUS, dy * FACTOR -RADIUS);
//        mMatrix.setScale(-FACTOR,-FACTOR);
//        mMatrix.setTranslate(-dx*FACTOR +RADIUS,-dy*FACTOR +RADIUS);
        Log.e("TAG","onTouchEvent   dx  :" + dx+" ,dy:"+dy+" ,RADIUS :"+RADIUS);
//        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
//
//        mShapeDrawable.setBounds(dx - RADIUS, dy-RADIUS, dx+RADIUS, dy+RADIUS);

        invalidate();
        return true;

    }
}
