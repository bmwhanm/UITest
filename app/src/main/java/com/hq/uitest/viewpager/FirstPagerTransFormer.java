package com.hq.uitest.viewpager;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by heqiang on 17/11/7.
 */

public class FirstPagerTransFormer implements ViewPager.PageTransformer {
    private final float MIN_SCALE = 0.75f;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void transformPage(View page, float position) {

        if(position < -1){
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }else if(position <= 0){//0 ~ -1
            float scale = 1 - (Math.abs(position) * (1 - MIN_SCALE)) ;
            page.setScaleX(scale);
            page.setScaleY(scale);
//            page.setTranslationZ(-100);

        }else if(position <= 1){//1 ~0
            float scale = (1 - MIN_SCALE) * (1 - Math.abs(position)) + MIN_SCALE;
            page.setScaleX(scale);
            page.setScaleY(scale);
//            page.setTranslationZ(-100);
        }else if(position > 1){
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);

        }

    }
}
