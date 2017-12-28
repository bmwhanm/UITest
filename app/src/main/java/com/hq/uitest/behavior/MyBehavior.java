package com.hq.uitest.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/12/27.
 */

public class MyBehavior extends CoordinatorLayout.Behavior<View> {
    private int totalY = 0;

    public MyBehavior() {
        init();
    }


    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

    }


//    @Override
//    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//        Log.e("TAG", "layoutDependsOn ###");
//        return dependency.getId() == R.id.rv_self_behavior;
//    }
//
//
//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//        Log.e("TAG", "onDependentViewChanged ###");
//        return true;
//    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.e("TAG", "    dx###" + dx + "    dy###" + dy + "   consumed###" + consumed);

//        consumed[0] = consumed[0] + 10;
//        consumed[1] = consumed[1] + 10;

//        totalY += dy;
//        int height = child.getHeight();
//        if (totalY > height) {
//            totalY = child.getHeight();
//        }
//        if (totalY < 0) {
//            totalY = 0;
//        }
//        child.setTranslationY(-totalY);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        totalY += dyConsumed;
        int height = child.getHeight();
//        if (totalY > height) {
//            totalY = child.getHeight();
//        }
        if (totalY < 0 ) {
            totalY = 0;
        }
        Log.e("TAG", "    dxConsumed###" + dxConsumed + "    dyConsumed###" + dyConsumed + "   dxUnconsumed###" + dxUnconsumed + "   dyUnconsumed###" + dyUnconsumed + "totalY###" +totalY+"    targetTop:"+target.getTop());
//        if((dyConsumed > 0 )||(dyConsumed == dyUnconsumed)){
            child.setTranslationY(-totalY);
//        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {

        Log.e("TAG", "   onStartNestedScroll   ");
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }
}
