package com.hq.uitest.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hq.uitest.R;

public class DrawableBaseActivity extends AppCompatActivity {

    private int level = 100;
    private ImageView iv;
    private BitmapDrawable selectedDrawable;
    private BitmapDrawable unSelectedDrawable;
    private DrawableBase baseDrawable;


    private RecyclerView mRv;
    private LinearLayoutManager mManager;

    private int[] mImgIds = new int[] { //7个
            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline,

            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline
    };
    private int[] mImgIds_active = new int[] {
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active,
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_base);


        //测试Drawable的基础用法
        testBase();

        //测试滑动的Drawable
        testDrawableSlide();


    }

    //测试滑动的Drawable
    private void testDrawableSlide() {
        mRv = (RecyclerView) findViewById(R.id.rv_draw_base);
        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(mManager);
        DrawAdapter adapter = new DrawAdapter(mImgIds,mImgIds_active,this);
        mRv.setAdapter(adapter);


        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("TAG","onScrolled   dx:"+dx+"  ,dy:"+dy);
                Log.e("TAG","onScrolled   getScrollX():"+recyclerView.getScrollX());

            }
        });

    }

    //测试Drawable的基础用法
    private void testBase() {

        iv = (ImageView) findViewById(R.id.iv_drawable);
        selectedDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.avft_active);
        unSelectedDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.avft);
        baseDrawable = new DrawableBase(selectedDrawable,unSelectedDrawable);
//        baseDrawable.setState(new int[]{android.R.attr.state_pressed});
//        iv.setImageDrawable(baseDrawable);
        iv.setBackground(baseDrawable);
        iv.setClickable(true);


        iv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//
//
//        baseDrawable.setCallback(new Drawable.Callback() {
//            @Override
//            public void invalidateDrawable(Drawable who) {
//                Log.e("TAG","invalidateDrawable(Drawable who)");
//
//
//            }
//
//            @Override
//            public void scheduleDrawable(Drawable who, Runnable what, long when) {
//                Log.e("TAG","scheduleDrawable(Drawable who, Runnable what, long when)");
//            }
//
//            @Override
//            public void unscheduleDrawable(Drawable who, Runnable what) {
//                Log.e("TAG","unscheduleDrawable(Drawable who, Runnable what)");
//            }
//        });


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDrawable.setState(new int[]{android.R.attr.state_pressed});
//                ViewGroup.LayoutParams lp = iv.getLayoutParams();
                level += 1000;
                iv.setImageLevel(level);
                baseDrawable.setLevel(level);
            }
        });
    }
}
