package com.hq.uitest.qtsCalendar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hq.uitest.R;
import com.hq.uitest.base.BaseActivity;

import java.util.Calendar;

public class QtsCalendarActivity extends BaseActivity {
    private RecyclerView mRvCalendar;
    private LinearLayoutManager mManager;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_qts_calendar;
    }

    @Override
    protected void initView() {
        mRvCalendar = (RecyclerView) findViewById(R.id.rv_qts_calendar);
        mManager = new LinearLayoutManager(this);
        mRvCalendar.setLayoutManager(mManager);
        mRvCalendar.setAdapter(new QtsCalAdapter());

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
