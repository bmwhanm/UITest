package com.hq.uitest.animator;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.hq.uitest.R;

public class TransitionFirstActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView iv;
    Button btn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //允许使用转场动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_first);

        iv = (ImageView) findViewById(R.id.iv_transition_meinv);
        btn = (Button) findViewById(R.id.btn_transition_first);
        toolbar = (Toolbar) findViewById(R.id.toolbar_transition_first);

//        setSupportActionBar(toolbar);


        btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_transition_first:

//                Slide slide = new Slide();
//                slide.setDuration(3000);
//                getWindow().setEnterTransition(slide);//进入的动画
//                getWindow().setExitTransition(slide);// 退出的时候的动画

//                Explode explode =  new Explode();
//                explode.setDuration(3000);
//                getWindow().setEnterTransition(explode);//进入的动画
//                getWindow().setExitTransition(explode);// 退出的时候的动画


                Fade fade = new Fade();
                fade.setDuration(3000);
                getWindow().setEnterTransition(fade);//进入的动画
                getWindow().setExitTransition(fade);// 退出的时候的动画

//                Explode explode = new Explode();
//                getWindow().setSharedElementExitTransition(explode);
//                getWindow().setSharedElementEnterTransition(explode);
//                explode.setDuration(3000);


                ActivityOptionsCompat optionsCompat =  ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        Pair.create((View)iv,"meinv"),
                        Pair.create((View) btn,"btn"));
                Intent mIntent = new Intent(this,TransitionSecondActivity.class) ;
                startActivity(mIntent,optionsCompat.toBundle());
                break;
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
