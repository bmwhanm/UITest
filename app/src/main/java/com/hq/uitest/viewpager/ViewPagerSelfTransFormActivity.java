package com.hq.uitest.viewpager;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.base.BaseActivity;
import com.hq.uitest.viewpager.transform.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerSelfTransFormActivity extends BaseActivity {
    private List<View> mViews;
    private MyViewPager vp;
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_view_pager_self_trans_form;
    }

    @Override
    protected void initView() {

        vp = (MyViewPager) findViewById(R.id.vp_self_trans);
        mViews = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.lay_vp,null);
        TextView tv1 = (TextView) view1.findViewById(R.id.tv_vp_content);
        tv1.setText("1");
        mViews.add(view1);

        View view2 = LayoutInflater.from(this).inflate(R.layout.lay_vp,null);
        TextView tv2 = (TextView) view2.findViewById(R.id.tv_vp_content);
        tv2.setText("2");
        mViews.add(view2);

        View view3 = LayoutInflater.from(this).inflate(R.layout.lay_vp,null);
        TextView tv3 = (TextView) view3.findViewById(R.id.tv_vp_content);
        tv3.setText("3");
        mViews.add(view3);

        View view4 = LayoutInflater.from(this).inflate(R.layout.lay_vp,null);
        TextView tv4 = (TextView) view4.findViewById(R.id.tv_vp_content);
        tv4.setText("4");
        mViews.add(view4);

        vp.setPageTransformer(true,new FirstPagerTransFormer());
        vp.setPageMargin(-200);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View view = mViews.get(position);
                container.removeView(view);
            }

            @Override
            public float getPageWidth(int position) {
//                return 0.7f;
                return super.getPageWidth(position);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void clickErrorRefresh() {

    }
}
