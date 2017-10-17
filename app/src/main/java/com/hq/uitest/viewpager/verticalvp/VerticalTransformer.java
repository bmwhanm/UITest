package com.hq.uitest.viewpager.verticalvp;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by heqiang on 17/9/4.
 */

public class VerticalTransformer implements ViewPager.PageTransformer {
    private float yPosition;

    @Override
    public void transformPage(View view, float position) {
        view.setTranslationX(view.getWidth() * -position);
        yPosition = position * view.getHeight();
        view.setTranslationY(yPosition);
    }
}
