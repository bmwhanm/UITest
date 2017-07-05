package com.hq.uitest.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Path;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hq.uitest.R;

public class PathInterceptorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_interceptor);

        ImageView iv = (ImageView) findViewById(R.id.iv_pathinterceptor);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Path path = new Path();
                path.cubicTo(0.2f, 0f, 0.1f, 1f, 0.5f, 1f);
                path.lineTo(1f, 1f);

//                ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 500);
//                ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 500);
////                animator.setInterpolator(PathInterpolatorCompat.create(path));
//                AnimatorSet set = new AnimatorSet();
//                set.playTogether(animator,animator2);
//                set.setDuration(3000);
//                set.setInterpolator(PathInterpolatorCompat.create(path));
//                set.start();


                path.reset();
                path.cubicTo(80f, 40f, 40f, 60f, 120f, 100f);
                path.lineTo(200f, 200f);

                ObjectAnimator animator3 = ObjectAnimator.ofFloat(v,View.X,View.Y,path);
                animator3.setEvaluator(new TypeEvaluator() {
                    @Override
                    public Object evaluate(float fraction, Object startValue, Object endValue) {
                        float position = ((float) endValue - (float) startValue)*fraction;
                        Log.e("TAG","position --: "+position);
                        return null;
                    }
                });
                animator3.setDuration(3000);
                animator3.start();
            }
        });


    }
}
