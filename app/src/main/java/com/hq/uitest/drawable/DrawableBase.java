package com.hq.uitest.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by heqiang on 17/6/15.
 */

public class DrawableBase extends Drawable{
    private Paint mPaint;
    private BitmapDrawable mSelectedDrawable,mUnSelectedDrawable;

    public DrawableBase(BitmapDrawable selectedDrawable, BitmapDrawable unSelectedDrawable){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
//        getPadding()
        mSelectedDrawable = selectedDrawable;
        mUnSelectedDrawable = unSelectedDrawable;

//        setBounds(0,0,50,50);

    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(0,0,400,400,mPaint);
        Log.e("TAG","DrawableBase    :  draw(canvas) ");

        Log.e("TAG","getLevel()    :  "+getLevel());

        int level = getLevel() >10000 ? 10000 : getLevel();
        Log.e("TAG","level  :  "+level);
        int mSelectedWidth = mSelectedDrawable.getIntrinsicWidth() ;
        int mSelectedHeight = mSelectedDrawable.getIntrinsicHeight() ;
        int mUnSelectedWidth = mUnSelectedDrawable.getIntrinsicWidth() ;
        int mUnSelectedHeight = mUnSelectedDrawable.getIntrinsicHeight() ;


        if(level == 0){
            canvas.drawBitmap(mUnSelectedDrawable.getBitmap(),0,0,mPaint);
            return;
        }
        if(level == 10000){
            canvas.drawBitmap(mSelectedDrawable.getBitmap(),0,0,mPaint);
            return;
        }
        if(level > 0 && level < 10000){
            float ratio = (float) level / 10000f;
            canvas.save();
            canvas.clipRect(0,0,mSelectedWidth  * ratio ,mSelectedHeight);
            canvas.drawBitmap(mSelectedDrawable.getBitmap(),0,0, mPaint);
            canvas.restore();

            canvas.save();
            canvas.clipRect(mSelectedWidth  * ratio,0,mSelectedWidth  ,mUnSelectedHeight);
            canvas.drawBitmap(mUnSelectedDrawable.getBitmap(),0,0, mPaint);
            canvas.restore();
        }


//        canvas.save();
//        canvas.clipRect(0,0,mSelectedWidth ,mSelectedHeight);
//        canvas.drawBitmap(mSelectedDrawable.getBitmap(),0,0, mPaint);
//        canvas.restore();
//        canvas.save();
//        canvas.clipRect(mSelectedWidth,0,mSelectedWidth + mUnSelectedWidth,mUnSelectedHeight);
//        canvas.drawBitmap(mUnSelectedDrawable.getBitmap(),0,0, mPaint);
//        canvas.restore();




//
//        canvas.drawCircle(100,100,50,mPaint);


//        Rect mRect = new Rect();
//        mRect.left = 0;
//        mRect.top = 0;
//        mRect.right = 100;
//        mRect.bottom = 100;
//        boolean mPadding = getPadding(mRect);
//        Log.e("TAG","mPadding -- :"+mPadding);
//        Log.e("TAG","mRect -- left:"+mRect.left+",top:"+mRect.top+",right:"+mRect.right+",Bottom:"+mRect.bottom);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    public boolean setState(int[] stateSet) {
        if(stateSet[0] == android.R.attr.state_pressed){
            mPaint.setColor(Color.GREEN);
//            invalidateSelf();
        }
        Log.e("TAG","DrawableBase :  setState() ");
        return super.setState(stateSet);

    }




    @Override
    protected boolean onLevelChange(int level) {
        Log.e("TAG","onLevelChange"+getLevel());
        invalidateSelf();
        return true;
    }


    @Override
    public int getIntrinsicWidth() {
        //得到Drawable的实际宽度
        return Math.max(mSelectedDrawable.getIntrinsicWidth(),
                mUnSelectedDrawable.getIntrinsicWidth());
    }

    @Override
    public int getIntrinsicHeight() {
        //得到Drawable的实际高度
        return Math.max(mSelectedDrawable.getIntrinsicHeight(),
                mUnSelectedDrawable.getIntrinsicHeight());
    }


}
