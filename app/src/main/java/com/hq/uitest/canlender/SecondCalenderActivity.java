package com.hq.uitest.canlender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hq.uitest.R;

import java.util.Calendar;
import java.util.Date;

public class SecondCalenderActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private LinearLayoutManager mManager;
    private Calendar mCalendar;
    private SecondCalAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_calender);
        initView();

    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv_second_cal);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRv.setLayoutManager(mManager);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());
        mAdapter = new SecondCalAdapter(mCalendar);
        mRv.setAdapter(mAdapter);





    }
}
