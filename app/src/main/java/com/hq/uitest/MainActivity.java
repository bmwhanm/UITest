package com.hq.uitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hq.uitest.aigeselfview.FirstActivity;
import com.hq.uitest.aigeselfview.PaintAndTextActivity;
import com.hq.uitest.animator.PathInterceptorActivity;
import com.hq.uitest.animator.PropertyActivity;
import com.hq.uitest.animator.TransitionFirstActivity;
import com.hq.uitest.canvas.CanvasActivity;
import com.hq.uitest.drawable.DrawableBaseActivity;
import com.hq.uitest.paint.PaintActivity;
import com.hq.uitest.paint.PaintMatrixActivity;
import com.hq.uitest.paint.PaintShaderActivity;
import com.hq.uitest.paint.PaintShaderPracticeActivity;
import com.hq.uitest.path.PathBaseActivity;
import com.hq.uitest.path.SearchAnimatorActivity;
import com.hq.uitest.recyclerviewt.RecyclerViewStaggerActivity;
import com.hq.uitest.recyclerviewt.headandfootrv.HeaderRvActivity;
import com.hq.uitest.recyclerviewt.itemtouch.ItemTouchRvActivity;
import com.hq.uitest.themeandstyle.ThemeAndStyleActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mData;
    private RecyclerView rv_main;
    private StringAdapter mAdapter;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mData = new ArrayList<>();

        mData.add("Theme and Style MD的主题与特色主要是primary，primaryDark，accent");
        mData.add("爱哥的第一个自定义view");
        mData.add("Paint and Text & FontMetrics");
        mData.add("瀑布流 RecyclerView ");
        mData.add("封装添加头部和底部的RecyclerView");
        mData.add("RecyclerView的ItemTouchHelp类的具体引用");
        mData.add("共享元素的转场动画");
        mData.add("PathInterceptor");
        mData.add("属性动画");
        mData.add("Paint的相关属性基础");
        mData.add("Paint的相关的ColorMatrix相关的");
        mData.add("Paint相关的着色器Shader");
        mData.add("Paint根据Shader制作的图片放大器");
        mData.add("Canvas的基础用法");
        mData.add("Drawable的基础用法");
        mData.add("Path的基础用法");
        mData.add("Path绘制的带有动画效果的SearchView");


        rv_main = (RecyclerView) findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StringAdapter(mData);
        rv_main.setAdapter(mAdapter);
        mAdapter.setListener(new StringAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String str, RecyclerView.ViewHolder holder) {
                switch (str){
                    case "Theme and Style MD的主题与特色主要是primary，primaryDark，accent":
                        mIntent = new Intent(MainActivity.this, ThemeAndStyleActivity.class);
                        startActivity(mIntent);
                        break;
                    case "爱哥的第一个自定义view":
                        mIntent = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Paint and Text & FontMetrics":
                        mIntent = new Intent(MainActivity.this, PaintAndTextActivity.class);
                        startActivity(mIntent);
                        break;
                    case "瀑布流 RecyclerView ":
                        mIntent = new Intent(MainActivity.this, RecyclerViewStaggerActivity.class);
                        startActivity(mIntent);
                        break;
                    case "封装添加头部和底部的RecyclerView":
                        mIntent = new Intent(MainActivity.this, HeaderRvActivity.class);
                        startActivity(mIntent);
                        break;
                    case "RecyclerView的ItemTouchHelp类的具体引用":
                        mIntent = new Intent(MainActivity.this, ItemTouchRvActivity.class);
                        startActivity(mIntent);
                        break;
                    case "共享元素的转场动画":
                        mIntent = new Intent(MainActivity.this, TransitionFirstActivity.class);
                        startActivity(mIntent);
                        break;
                    case "PathInterceptor":
                        mIntent = new Intent(MainActivity.this, PathInterceptorActivity.class);
                        startActivity(mIntent);
                        break;
                    case "属性动画":
                        mIntent = new Intent(MainActivity.this, PropertyActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Paint的相关属性基础":
                        mIntent = new Intent(MainActivity.this, PaintActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Paint的相关的ColorMatrix相关的":
                        mIntent = new Intent(MainActivity.this, PaintMatrixActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Paint相关的着色器Shader":
                        mIntent = new Intent(MainActivity.this, PaintShaderActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Paint根据Shader制作的图片放大器":
                        mIntent = new Intent(MainActivity.this, PaintShaderPracticeActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Canvas的基础用法":
                        mIntent = new Intent(MainActivity.this, CanvasActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Drawable的基础用法":
                        mIntent = new Intent(MainActivity.this, DrawableBaseActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Path的基础用法":
                        mIntent = new Intent(MainActivity.this, PathBaseActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Path绘制的带有动画效果的SearchView":
                        mIntent = new Intent(MainActivity.this, SearchAnimatorActivity.class);
                        startActivity(mIntent);
                        break;


                }
            }

            @Override
            public void onItemTouch(RecyclerView.ViewHolder holder) {

            }
        });



    }
}
