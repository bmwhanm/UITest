package com.hq.uitest.materialdesign;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.hq.uitest.R;

import java.util.ArrayList;
import java.util.List;

public class AppBarCoordinatorActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<Fragment> mFragments;
    private TabLayout mTab;
    private List<String> mTitles;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_app_bar_coordinator);

//        mToolbar = (Toolbar) findViewById(R.id.toolbar_app_tool);
//
//        setSupportActionBar(mToolbar);

        mTab = (TabLayout) findViewById(R.id.tab_app_tool);

        mTitles = new ArrayList<>();
        mTitles.add("条目一");
        mTitles.add("条目二");
        mTitles.add("条目三");
        vp = (ViewPager) findViewById(R.id.vp_app_tool);
        mFragments = new ArrayList<>();
        mFragments.add(new MaterialDesignFragment());
        mFragments.add(new MaterialDesignFragment());
        mFragments.add(new MaterialDesignFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);

        vp.setAdapter(adapter);
        mTab.setupWithViewPager(vp);




    }
}
