package com.hq.uitest.materialdesign;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by heqiang on 17/7/24.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData;
    private List<String> mTitles;
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments,List<String> titles) {
        super(fm);
        mData = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }


}
