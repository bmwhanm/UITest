package com.hq.uitest.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import com.hq.uitest.R;

public class TransitionSecondActivity extends AppCompatActivity {

    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //允许使用转场动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_second);
        toolbar = (Toolbar) findViewById(R.id.toolbar_transition_second);

//        Slide slide = new Slide();
//        slide.setDuration(3000);
//        getWindow().setEnterTransition(slide);
//        getWindow().setExitTransition(slide);
//
//
//        Fade fade = new Fade();
//        fade.setDuration(3000);
//        getWindow().setEnterTransition(fade);
//        getWindow().setExitTransition(fade);


        Explode explode = new Explode();
        explode.setDuration(3000);
        getWindow().setEnterTransition(explode);
        getWindow().setExitTransition(explode);


//        setSupportActionBar(toolbar);
    }
}
