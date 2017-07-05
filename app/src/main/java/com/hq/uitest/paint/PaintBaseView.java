package com.hq.uitest.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by heqiang on 17/6/13.
 */

public class PaintBaseView extends View {

    private Paint mPaint;
    private String str = "this is a Test 中文";

    public PaintBaseView(Context context) {
        this(context, null);
    }

    public PaintBaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        //设置抗锯齿，会影响性能
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);//防抖动
        mPaint.setColor(Color.YELLOW);
        //设置画笔的样式，
        mPaint.setStyle(Paint.Style.STROKE);//描边
//        mPaint.setStyle(Paint.Style.FILL);//填充
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//描边加上填充

        //设置画笔的笔尖的宽度
//        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(50);

        //设置画笔的线帽
        mPaint.setStrokeCap(Paint.Cap.BUTT);//没有线帽
//        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形的线帽
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形的线帽


        //获得字符行间距
        mPaint.getFontSpacing();
        //获得字符之间的间距
//        mPaint.getLetterSpacing();
//        mPaint.setLetterSpacing(float letterSpacing);//设置

        //设置文本删除线
//        mPaint.setStrikeThruText(true);
        //是否设置下划线
//        mPaint.setUnderlineText(true);
        //设置文本大小
//        mPaint.setTextSize(textSize);
//        mPaint.getTextSize();
//        mPaint.setTypeface(Typeface.BOLD);//设置字体类型
//        Typeface.ITALIC
//        Typeface.create(familyName, style)//加载自定义字体
        //文字倾斜 默认0，官方推荐的-0.25f是斜体
//        mPaint.setTextSkewX(-0.25f);

        //文本对齐方式
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.setTextAlign(Paint.Align.LEFT);
//        mPaint.setTextAlign(Paint.Align.RIGHT);


        //计算指定长度的字符串（字符长度、字符个数、显示的时候真实的长度）
        String str = "this is a Test 中文";
        float measuredWidth[] = new float[1];
        int length = mPaint.breakText(str, true, 800, measuredWidth);
        Log.e("TAG", "mPaint.breakText() first --- length:" + length + " , measuredWidth :" + measuredWidth[0]);
        length = mPaint.breakText(str, 0, str.length() - 10, true, 800, measuredWidth);
        Log.e("TAG", "mPaint.breakText() second --- length:" + length + " , measuredWidth :" + measuredWidth[0]);

        float measureWidth = mPaint.measureText(str);
        Log.e("TAG", "mPaint.measureText() first: " + measureWidth);
        measureWidth = mPaint.measureText(str, 0, str.length() - 10);
        Log.e("TAG", "mPaint.measureText() second: " + measureWidth);

        // Rect bounds获取文本的矩形区域（宽高）
        Rect rect = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), rect);
        Log.e("TAG", "mPaint.getTextBounds() first--  top:" + rect.top + "  ,left " + rect.left + "   ,right" + rect.right + "  ,bottom:" + rect.bottom);
        mPaint.getTextBounds(str, 0, str.length() - 10, rect);
        Log.e("TAG", "mPaint.getTextBounds() second--  top:" + rect.top + "  ,left " + rect.left + "   ,right" + rect.right + "  ,bottom:" + rect.bottom);


        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        Log.e("TAG", "mPaint.getFontMetrics()   , top:" + metrics.top + ", ascent:" + metrics.ascent + " ,descent:" + metrics.descent + " , bottom:" + metrics.bottom);

        float[] textWidths = new float[20];
        int textWidth = mPaint.getTextWidths(str, 0, str.length(), textWidths);
        Log.e("TAG", "mPaint.getTextWidths() --- textWidth:" + textWidth + " , textWidths :" + textWidths[textWidth - 1]);

        length = textWidths.length;
        float result = 0;
        for (int i = 0; i < length; i++) {
            result += textWidths[i];
        }
        Log.e("TAG", "result -->:" + result);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一段文本
//        canvas.drawText(str,0,100,mPaint);

        //测试画笔的StrokeJoin属性，两条线的交叉处是否是锐角，还是直线，还是圆弧
//        testPaintStrokeJoin(canvas);

        //练习Paint的画线的方式
        //给出一根中间线，让中间线在所画的文字的中间
        textCenterLine(canvas);

        //测试画笔的线帽
//        testStrokeCap(canvas);
        Log.e("TAG", "PaintBaseView Width :" + getWidth());


    }

    private void testStrokeCap(Canvas canvas) {
        Path path = new Path();
        path.reset();
        path.moveTo(100, 100);
        path.lineTo(600, 100);
        mPaint.setStrokeWidth(30);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPath(path, mPaint);


        path.reset();
        path.moveTo(100, 160);
        path.lineTo(600, 160);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(100, 220);
        path.lineTo(600, 220);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPath(path, mPaint);

        ColorMatrix matrix = new ColorMatrix(new float[]{
                1f, 0, 0, 0, 0,
                0, 1f, 0, 0, 0,
                0, 0, 1f, 0, 0,
                0, 0, 0, 1f, 0

        });
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(matrix);
        mPaint.setColorFilter(colorFilter);
    }

    private void textCenterLine(Canvas canvas) {
        Path path = new Path();
        path.moveTo(200, 200);
        path.lineTo(600, 200);
        mPaint.setColor(Color.RED);
        canvas.drawPath(path, mPaint);


        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        float centerY = 200;
        float baseLienY = (metrics.bottom - metrics.top) / 2 - metrics.bottom + centerY;
        mPaint.setColor(Color.BLUE);
        canvas.drawText(str, 200, baseLienY, mPaint);

    }

    private void testPaintStrokeJoin(Canvas canvas) {
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);//直线
//        mPaint.setStrokeJoin(Paint.Join.MITER);//锐角
//        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧


        mPaint.setStrokeWidth(30);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.reset();
        path.moveTo(40, 40);
        path.lineTo(160, 40);
        path.lineTo(40, 160);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, mPaint);


        path.reset();
        path.moveTo(40, 200);
        path.lineTo(160, 200);
        path.lineTo(40, 360);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, mPaint);


        path.reset();
        path.moveTo(40, 400);
        path.lineTo(160, 400);
        path.lineTo(40, 560);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, mPaint);


    }
}
