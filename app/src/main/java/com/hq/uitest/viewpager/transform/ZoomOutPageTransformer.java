package com.hq.uitest.viewpager.transform;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by heqiang on 17/9/4.
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        Log.e("TAG","View : "+page+"position:"+position);
    }
}
