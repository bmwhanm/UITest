package com.hq.uitest.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/6/14.
 */

public class PaintMatrixView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Rect mRect;

    public PaintMatrixView(Context context) {
        this(context, null);
    }

    public PaintMatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mRect = new Rect(0, 0, 225, 300);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画一张没有设置颜色矩阵的原图
        mPaint.setColorFilter(null);
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);

        //测试ColorMatrix的相关方法
//        testColorMatrix(canvas);

        //测试LightingColorFilter
        testLightingColorMatrix(canvas);

        //测试PorterDuffColorFilterMatrix
//        testPorterDuffColorFilterMatrix(canvas);


    }
    //测试PorterDuffColorFilterMatrix
    private void testPorterDuffColorFilterMatrix(Canvas canvas) {
        PorterDuffColorFilter  colorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DST_OUT);
        mPaint.setColorFilter(colorFilter);
        canvas.translate(0, 400);
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);

    }

    //测试LightingColorFilter
    private void testLightingColorMatrix(Canvas canvas) {

        /**
         R' = R * colorMultiply.R + colorAdd.R
         G' = G * colorMultiply.G + colorAdd.G
         B' = B * colorMultiply.B + colorAdd.B
         */
        LightingColorFilter colorFilter = new LightingColorFilter(0xffffff,0xff00ff);
//        Log.e("TAG","LightingColorFilter   multiply :" + colorFilter.getColorMultiply() + " add :"+colorFilter.getColorAdd() );
        mPaint.setColorFilter(colorFilter);
        canvas.translate(0, 400);
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);



    }

    //测试ColorMatrix的相关方法
    private void testColorMatrix(Canvas canvas) {

//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                1f, 0, 0, 0, 0,
//                0, 1f, 0, 0, 0,
//                0, 0, 1f, 0, 0,
//                0, 0, 0, 1f, 0
//        });


        //色彩增强，等于美颜
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                1.2f, 0, 0, 0, 0,
//                0, 1.2f, 0, 0, 0,
//                0, 0, 1.2f, 0, 0,
//                0, 0, 0, 1f, 0
//        });

//        全部取成相反的值，就是拿255减去现在的值
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                -1f, 0, 0, 0, 255,
//                0, -1f, 0, 0, 255,
//                0, 0, -1f, 0, 255,
//                0, 0, 0, 1f, 0
//        });

        //把图片处理成黑白照
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                0.213f, 0.715f, 0.072f, 0, 0,
//                0.213f, 0.715f, 0.072f, 0, 0,
//                0.213f, 0.715f, 0.072f, 0, 0,
//                0, 0, 0, 1f, 0
//        });

        //复古风格
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
//                1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
//                1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
//                0, 0, 0, 1f, 0,
//        });


        //直接用ColorMatrix 的Api来增强色彩
//        ColorMatrix matrix = new ColorMatrix();
//        matrix.setScale(1.2f,1.2f,1.2f,1f);

        //设置色彩的饱和度
//        ColorMatrix matrix = new ColorMatrix();
//        matrix.setSaturation(0.3f);

        //设置色彩的旋转
        ColorMatrix matrix = new ColorMatrix();
        matrix.setRotate(0,120);

        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.translate(0, 400);
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);
    }
}
