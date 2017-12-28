package com.hq.uitest.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by heqiang on 17/11/8.
 */

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int p = i;
        int position = getCurrentItem();
//        if (position < 0) {
//            p =  i;
//        } else {
//            if (i == childCount - 1) {//这是最后一个需要刷新的item
////                if (position > i) {
////                    position = i;
////                }
//                p = position;
//            }
//            if (i == position) {//这是原本要在最后一个刷新的item
//                p = childCount - 1;
//            }
//
//        }
//
//        int p = 0;

        if(i == childCount - 1){
            p = position;
        }else if(i == position) {
            p = childCount - 1;
        }
        Log.e("TAG","i --- "+i+"   position:"+position + "   p:"+p);
        return p;


//        int position = getCurrentItem();
//        if(i == position - 1 ){
//            return position - 1;
//        }else if(i == position + 1){
//            return position;
//        }else if( i == position){
//            return position + 1;
//        }
//        return super.getChildDrawingOrder(childCount, i);
    }
}
