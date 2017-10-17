package com.hq.uitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hq.uitest.aigeselfview.FirstActivity;
import com.hq.uitest.aigeselfview.PaintAndTextActivity;
import com.hq.uitest.animator.PathInterceptorActivity;
import com.hq.uitest.animator.PropertyActivity;
import com.hq.uitest.animator.TransitionFirstActivity;
import com.hq.uitest.camera.TakePhotoActivity;
import com.hq.uitest.canlender.CalenderSelfActivity;
import com.hq.uitest.canlender.SecondCalenderActivity;
import com.hq.uitest.canvas.CanvasActivity;
import com.hq.uitest.drawable.DrawableBaseActivity;
import com.hq.uitest.event.LeftDeleteActivity;
import com.hq.uitest.event.ScrollActivity;
import com.hq.uitest.loopview.LoopViewActivity;
import com.hq.uitest.materialdesign.AppBarCoordinatorActivity;
import com.hq.uitest.paint.PaintActivity;
import com.hq.uitest.paint.PaintMatrixActivity;
import com.hq.uitest.paint.PaintShaderActivity;
import com.hq.uitest.paint.PaintShaderPracticeActivity;
import com.hq.uitest.path.BeisaierPathActivity;
import com.hq.uitest.path.PathBaseActivity;
import com.hq.uitest.path.SearchAnimatorActivity;
import com.hq.uitest.permission.RunTimePermissionActivity;
import com.hq.uitest.recyclerviewt.RecyclerViewStaggerActivity;
import com.hq.uitest.recyclerviewt.headandfootrv.HeaderRvActivity;
import com.hq.uitest.recyclerviewt.itemtouch.ItemTouchRvActivity;
import com.hq.uitest.refreshcontrol.RefreshControlSelfActivity;
import com.hq.uitest.themeandstyle.ThemeAndStyleActivity;
import com.hq.uitest.viewpager.transform.TransformActivity;
import com.hq.uitest.viewpager.verticalvp.VerticalViewPagerActivity;
import com.hq.uitest.vlayout.VLayoutActivity;

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
        ARouter.init(getApplication());


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
        mData.add("基于LinearLayout的侧滑删除");
        mData.add("View调用scrollTo 和scrollBy后view的坐标关系");
        mData.add("AppBarLayout加CoordinatorLayout加CollapsingToolbarLayout");
        mData.add("根据贝塞尔曲线画出一个圆形的滑动图标");
        mData.add("下拉刷新上拉加载更多");
        mData.add("popupWindow Test");
        mData.add("VerticalViewPager Test");
        mData.add("Viewpager转场动画 PagerTransform");
        mData.add("自定义日历控件");
        mData.add("第二种自定义日历控件");
        mData.add("阿里ARoute路由跳转");
        mData.add("VLayout使用");
        mData.add("拍照以及选取相册");
        mData.add("测试LoopView");
        mData.add("运行时动态申请权限");



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
                    case "基于LinearLayout的侧滑删除":
                        mIntent = new Intent(MainActivity.this, LeftDeleteActivity.class);
                        startActivity(mIntent);
                        break;
                    case "View调用scrollTo 和scrollBy后view的坐标关系":
                        mIntent = new Intent(MainActivity.this, ScrollActivity.class);
                        startActivity(mIntent);
                        break;
                    case "AppBarLayout加CoordinatorLayout加CollapsingToolbarLayout":
                        mIntent = new Intent(MainActivity.this, AppBarCoordinatorActivity.class);
                        startActivity(mIntent);
                        break;
                    case "根据贝塞尔曲线画出一个圆形的滑动图标":
                        mIntent = new Intent(MainActivity.this, BeisaierPathActivity.class);
                        startActivity(mIntent);
                        break;
                    case "下拉刷新上拉加载更多":
                        mIntent = new Intent(MainActivity.this, RefreshControlSelfActivity.class);
                        startActivity(mIntent);
                        break;
                    case "popupWindow Test":
                        mIntent = new Intent(MainActivity.this,PopupWindowActivity.class);
                        startActivity(mIntent);
                        break;
                    case "VerticalViewPager Test":
                        mIntent = new Intent(MainActivity.this, VerticalViewPagerActivity.class);
                        startActivity(mIntent);
                        break;
                    case "Viewpager转场动画 PagerTransform":
                        mIntent = new Intent(MainActivity.this, TransformActivity.class);
                        startActivity(mIntent);
                        break;
                    case "自定义日历控件":
                        mIntent = new Intent(MainActivity.this, CalenderSelfActivity.class);
                        startActivity(mIntent);
                        break;
                    case "第二种自定义日历控件":
                        mIntent = new Intent(MainActivity.this, SecondCalenderActivity.class);
                        startActivity(mIntent);
                        break;
                    case "阿里ARoute路由跳转":
                        ARouter.getInstance().build("/aroute/first").navigation(getApplicationContext(), new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                if(postcard != null) {
                                    Log.e("TAG", "onFound:" + postcard.toString());
                                }
                                else {
                                    Log.e("TAG","onFound...");
                                }
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                if(postcard != null) {
                                    Log.e("TAG","onLost:"+postcard.toString());
                                }else{
                                    Log.e("TAG","onLost:");
                                }
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                if(postcard != null) {
                                    Log.e("TAG","onArrival:"+postcard.toString());
                                }else{
                                    Log.e("TAG","onArrival:");
                                }
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                if(postcard != null) {
                                    Log.e("TAG","onInterrupt:"+postcard.toString());
                                }else{
                                    Log.e("TAG","onInterrupt:");
                                }
                            }

                        } );
                        break;
                    case "VLayout使用":
                        mIntent = new Intent(MainActivity.this, VLayoutActivity.class);
                        startActivity(mIntent);
                        break;
                    case "拍照以及选取相册":
                        mIntent = new Intent(MainActivity.this, TakePhotoActivity.class);
                        startActivity(mIntent);
                        break;
                    case "测试LoopView":
                        mIntent = new Intent(MainActivity.this, LoopViewActivity.class);
                        startActivity(mIntent);
                        break;
                    case "运行时动态申请权限":
                        mIntent = new Intent(MainActivity.this, RunTimePermissionActivity.class);
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
