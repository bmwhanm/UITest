package com.hq.uitest.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.hq.uitest.R;

public class PropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);


        //基础的ObjectAnimator的用法
        Button btn = (Button) findViewById(R.id.btn_property_basic);


        ObjectAnimator oa = ObjectAnimator.ofFloat(btn,"translationX",0f,200f);
        oa.setDuration(2000);
        oa.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator ob = ObjectAnimator.ofFloat(btn, View.TRANSLATION_Y,0f,200f);
        ob.setDuration(2000);
        ObjectAnimator oc = ObjectAnimator.ofFloat(btn,View.ALPHA,0f,1f);
        oc.setDuration(2000);
        final AnimatorSet set = new AnimatorSet();
//        set.playTogether(oa,ob,oc);
        set.playSequentially(oa,ob,oc);
//        set.start();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set.start();
            }
        });

        //为动画的执行过程添加监听
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        //为动画的自信过程添加监听，可以添加自己想监听的动画执行周期
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });

        //为动画添加动画执行过程中的监听
        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });




        //基础的ValueAnimator的用法
        final Button btn2 = (Button) findViewById(R.id.btn_property_basic_value);
        final ValueAnimator va = ValueAnimator.ofFloat(0f,200f);
        va.setDuration(2000);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                btn2.setTranslationX(value);
                btn2.setTranslationY(value);
                btn2.setAlpha(value/200);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                va.start();
            }
        });


        //基础的PropertyValuesHolder
        Button btn_holder = (Button) findViewById(R.id.btn_property_holder);
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat(View.ROTATION_X,0f,180f,0f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat(View.ROTATION_Y,0f,360f,0f);
        final ObjectAnimator od = ObjectAnimator.ofPropertyValuesHolder(btn_holder,holder1,holder2);
        od.setDuration(2000);
        btn_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                od.start();
            }
        });



        //Evaluator的基础用法
        Button btn_evaluator = (Button) findViewById(R.id.btn_property_evaluator);
        final TextView tv_circle = (TextView) findViewById(R.id.tv_property_circle);
        PointF start = new PointF(0f,0f);
        PointF end = new PointF(10f,10f);
        final ValueAnimator vb = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {

            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF pointF = new PointF();
                Log.e("TAG","fraction -- > :"+fraction);
                pointF.x = 10f*(fraction*20);
                pointF.y = 10f*(fraction*fraction * 40);//*(fraction*20);
				return pointF;
            }
        },start,end);
        vb.setDuration(500);

        vb.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                tv_circle.setX(pointF.x);
                tv_circle.setY(pointF.y);
            }
        });
        btn_evaluator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.start();
            }
        });


    }
}
