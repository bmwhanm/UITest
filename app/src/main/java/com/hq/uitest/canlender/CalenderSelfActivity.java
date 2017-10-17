package com.hq.uitest.canlender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hq.uitest.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalenderSelfActivity extends AppCompatActivity implements View.OnClickListener {
    private Calendar mCalendar;
    private RecyclerView mRv;
    private Button btn_title,btn_add,btn_reduce;
    private int mMonth,mDay,mYear;
    private List<String> mData;
    private int mExtraDay;
    private CalenderAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_self);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());



//        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
//
//        Log.e("TAG","dayOfMonth:"+dayOfMonth);
//
//        int dayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR);
//        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
//
//        int month = mCalendar.get(Calendar.MONTH);
//        int year = mCalendar.get(Calendar.YEAR);
//        int firstDayOfWeek = mCalendar.getFirstDayOfWeek();
//        int monday = mCalendar.MONDAY;
//        Log.e("TAG","FirstDayOfWeek:"+firstDayOfWeek);
//        Log.e("TAG","monday:"+monday);
//        Log.e("TAG","dayOfYear:"+dayOfYear);
//        Log.e("TAG","dayOfWeek:"+dayOfWeek);
//        Log.e("TAG","month:"+month);
//        Log.e("TAG","year:"+year);
//
//        int maxDayOfMonth = mCalendar.getMaximum(Calendar.DAY_OF_MONTH);
//        int minimalDaysInFirstWeek = mCalendar.getMinimalDaysInFirstWeek();
//        Log.e("TAG","maxDayOfMonth:"+maxDayOfMonth);
//        Log.e("TAG","MinimalDaysInFirstWeek:"+minimalDaysInFirstWeek);
//
////        mCalendar.get(Calendar.WEEK_OF_MONTH);
//        mCalendar.getMinimalDaysInFirstWeek();
//        int minDayOfWeek = mCalendar.getMinimum(Calendar.DAY_OF_WEEK);
//        Log.e("TAG","minDayOfWeek:"+minDayOfWeek);
//
//        mCalendar.setTime(new Date(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH), 1));
//        int week = mCalendar.get(Calendar.DAY_OF_WEEK);
//        Log.e("TAG","week:"+week);



        initView();

    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv_calender);
        mRv.setLayoutManager(new GridLayoutManager(this,7,GridLayoutManager.VERTICAL,false));
        btn_title = (Button) findViewById(R.id.btn_cal_title);
        btn_add = (Button) findViewById(R.id.btn_add_cal);
        btn_reduce = (Button) findViewById(R.id.btn_reduce_cal);
        btn_add.setOnClickListener(this);
        btn_reduce.setOnClickListener(this);

        btn_title.setText(getMonthStr(new Date()));


        mMonth = mCalendar.get(Calendar.MONTH);
        mYear = mCalendar.get(Calendar.YEAR);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

//        Log.e("TAG","  month:"+mMonth+"  year:"+mYear+"  day:"+mDay);

//        int dayLength = mCalendar.getMaximum(Calendar.DAY_OF_MONTH);
//        Date date = new Date();
//        mCalendar.setTime(new Date(mYear,mMonth - 1,1));
//        int mFirstDayOfWeek = mCalendar.getFirstDayOfWeek();
//        mCalendar.set(Calendar.DAY_OF_MONTH,1);
//        int mSelfDayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
//        mExtraDay = mSelfDayOfWeek;
        mAdapter = new CalenderAdapter(mCalendar);
        mRv.setAdapter(mAdapter);







    }
    /**获取月份标题*/
    private String getMonthStr(Date month){
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");
        return df.format(month);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_cal:
                if(mMonth >= 11){
                    mMonth = 0;
                    mYear =mYear + 1;
                }else{
                    mMonth = mMonth +  1;
                }
                Log.e("TAG","add----->"+"mMonth:"+mMonth+"  mYear:"+mYear);

                mCalendar.setTime(new Date(mYear - 1900,mMonth,1));
                btn_title.setText(getMonthStr(mCalendar.getTime()));
                mAdapter = new CalenderAdapter(mCalendar);
                mRv.setAdapter(mAdapter);


                break;
            case R.id.btn_reduce_cal:
                if(mMonth <= 0){
                    mMonth = 11;
                    mYear = mYear - 1;
                }else{
                    mMonth = mMonth - 1;
                }
                Log.e("TAG","reduce----->"+"mMonth:"+mMonth+"  mYear:"+mYear);
                mCalendar.setTime(new Date(mYear - 1900,mMonth,1));
                btn_title.setText(getMonthStr(mCalendar.getTime()));
                mAdapter = new CalenderAdapter(mCalendar);
                mRv.setAdapter(mAdapter);
                break;
        }

    }
}
