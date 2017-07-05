package com.hq.uitest.recyclerviewt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/6/8.
 */

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration{
    private Bitmap mBitmap;
    private Context mContext;
    private Drawable mDivider;

    public MyDividerItemDecoration(Context context){
        mContext = context;
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        mDivider = mContext.getResources().getDrawable(R.drawable.rv_divider);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c,parent);
        drawVertical(c,parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int top = child.getBottom() + params.bottomMargin;
            int right = left + child.getWidth() + params.rightMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int top = child.getBottom() + params.bottomMargin;
            int right = left + child.getWidth() + params.rightMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(10,50,10,10);
    }


}
