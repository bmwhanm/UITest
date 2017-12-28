package com.hq.uitest.basetest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.hq.uitest.R;
import com.hq.uitest.base.BaseActivity;

public class TestBaseFragmentActivity extends BaseActivity {
    private FrameLayout lay_root;
    private Fragment mFragment;


    @Override
    protected int bindLayoutId() {
        return R.layout.activity_test_base_fragment;
    }

    @Override
    protected void initView() {
        lay_root = findViewId(R.id.lay_test_frag_root);
    }

    @Override
    protected void initData() {
        mFragment = new TestBaseFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.lay_test_frag_root,mFragment).commitAllowingStateLoss();
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
