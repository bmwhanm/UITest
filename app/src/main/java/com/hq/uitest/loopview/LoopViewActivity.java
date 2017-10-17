package com.hq.uitest.loopview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.viewpager.verticalvp.VerticalTransformer;
import com.hq.uitest.viewpager.verticalvp.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class LoopViewActivity extends AppCompatActivity {
    private LoopView mLoopView;
    private List<String> mData;
    private WheelView mWheelView;
    private VerticalViewPager verticalViewPager;
    private List<View> mViews;
    private LayoutInflater mInflater;
    private WrapLinearlayout lay_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_view);

        mLoopView = (LoopView) findViewById(R.id.loop_test);
        mLoopView.setCanLoop(true);
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("第" + i + "项");
        }
        mLoopView.setDataList(mData);
        mLoopView.setInitPosition(0);
        mLoopView.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {
                Log.e("TAG","LoopScrollListener ...... +" +item);
            }
        });

        mWheelView = (WheelView) findViewById(R.id.loop_wheel);
        mWheelView.setTextSize(16);
        mWheelView.setCenterItem(0);
        for (int i = 0; i < 100; i++) {
            mWheelView.addData(mData.get(i));
        }

        verticalViewPager = (VerticalViewPager) findViewById(R.id.loop_vertical_vp);
        mInflater = LayoutInflater.from(this);
        lay_root = (WrapLinearlayout) findViewById(R.id.loop_root);

        mViews = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            View view = mInflater.inflate(R.layout.rv_string,lay_root,false);
            mViews.add(view);
        }
        verticalViewPager.setPageTransformer(true,new VerticalTransformer());
        verticalViewPager.setOffscreenPageLimit(20);
        verticalViewPager.setPageMargin(20);
        lay_root.setViewPgaer(verticalViewPager);
        verticalViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mData.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                super.instantiateItem(container,position);
                int p1 = position % mViews.size();
                View  view = mViews.get(p1);
//                if(view.getParent() != null) {
//                    container.removeView(view);
//                }
                TextView tv = (TextView) view.findViewById(R.id.tv_rv_str);
                tv.setText(mData.get(position));
                if(view.getParent() == null) {
                    container.addView(view);
                }
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                int p1 = position % mViews.size();
                View view = mViews.get(p1);
                container.removeView(view);
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }
        });

        verticalViewPager.clearOnPageChangeListeners();
        verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("TAG","position .. "+position + "  positionOffset ... "+positionOffset + "   positionOffsetPixels .. "+positionOffsetPixels);
                if(positionOffset == 0){

                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("TAG","onPageSelected ... Position:"+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
