package com.hq.uitest.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/6/14.
 */

public class PaintShaderView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mRect;


    public PaintShaderView(Context context) {
        this(context,null);
    }

    public PaintShaderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaintShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(5);
//        mPaint.setStyle(Paint.Style.STROKE);
        mRect = new RectF(0,0,200,200);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        //测试BitmapShader的相关属性
        testBitmapShader(canvas);


        //测试LinearGradient
//        testLinearGradient(canvas);

        //测试RadialGradient
//        testRadialGradient(canvas);

        //测试SweepGradient
//        testSweepGradient(canvas);

        //测试ComposeShader
//        testComposeShader(canvas);




    }

    //测试ComposeShader
    private void testComposeShader(Canvas canvas) {
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        LinearGradient linearGradient = new LinearGradient(0,200,200,200,new int[]{Color.RED,Color.YELLOW,Color.GREEN},null, Shader.TileMode.CLAMP);

        ComposeShader composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.OVERLAY);
        mPaint.setShader(composeShader);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    //测试SweepGradient
    private void testSweepGradient(Canvas canvas) {
        SweepGradient gradient = new SweepGradient(200,200,new int[]{Color.RED,Color.YELLOW,Color.GREEN,Color.CYAN},null);
//        SweepGradient gradient = new SweepGradient(200,200,Color.RED,Color.YELLOW);
        mPaint.setShader(gradient);
        canvas.drawCircle(200,200,100,mPaint);
    }

    //测试RadialGradient
    private void testRadialGradient(Canvas canvas) {
        RadialGradient gradient = new RadialGradient(200,200,100,new int[]{Color.RED,Color.YELLOW,Color.GREEN},new float[]{0.1f,0.2f,0.3f}, Shader.TileMode.REPEAT);
//        RadialGradient gradient = new RadialGradient(200,200,100,Color.RED,Color.YELLOW, Shader.TileMode.REPEAT);
        mPaint.setShader(gradient);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }

    //测试LinearGradient
    private void testLinearGradient(Canvas canvas) {
        /**
         * x0,y0,x1,y1 : 代表这个颜色变化的起点位置和终点位置
         * colors: 是颜色的变化的范围
         * positions ：是每个颜色的在总变化的范围所占的所有的值加起来等于1 。类似于权重,可以为null，为null的话就是每个颜色所占的比重相同
         * tile ： 是这个图形的拉伸规则,CLAMP 是拉伸最后的一个像素，MIRROR是镜像，REPEAT是重复
         */
        //LinearGradient(float x0, float y0, float x1, float y1, int colors[], float positions[],
        //Shader.TileMode tile)
        LinearGradient gradient = new LinearGradient(0,200,200,200,new int[]{Color.RED,Color.YELLOW,Color.GREEN},null, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }

    private float dx,dy;
    private void testBitmapShader(Canvas canvas) {

        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
//        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

//        float width = getWidth();
//        float height = getHeight();
//        float scale = Math.max(width, height)*1.0f/Math.min(width, height);
//		Matrix matrix = new Matrix();
//		matrix.setScale(scale, scale);//缩放比例
//		shader.setLocalMatrix(matrix);



        //这里有关于Shader的Matrix 的操作说明
//        Matrix matrix = new Matrix();
//        matrix.setScale(1.5f,0.5f);
//        matrix.setTranslate(50,50);
//        matrix.setRotate(45);
//        shader.setLocalMatrix(matrix);

        mPaint.setShader(shader);

//        canvas.drawRect(0,0,getWidth(),getHeight() ,mPaint);

        //画矩形
//        canvas.drawRect(0,0,width,height,mPaint);
        //画圆形
//        canvas.drawCircle(100,100,100,mPaint);

//        mRect = new RectF(dx - 50,dy -50, dx + 50,dy + 50);
//        canvas.drawOval(mRect,mPaint);
//        canvas.drawRoundRect(mRect,10,10,mPaint);



        if(dx != 0 && dy != 0)
        canvas.drawCircle(dx,dy,150,mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                dx = 0;
                dy = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                dx = event.getX();
                dy = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                dx = 0;
                dy = 0;
                break;
        }
        invalidate();
        return true;
    }
}
