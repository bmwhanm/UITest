package com.hq.uitest.canlender;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.SelfDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by heqiang on 17/9/12.
 */

public class SecondCalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Calendar mCalendar;
    private int mMonth, mDay, mYear, mDayLength, mExtraDay;
    private int mCurrentMonth, mLastMonth;
    private int mLastDay;
    private List<SelfDate> mDatas;

    public SecondCalAdapter(Calendar calendar) {
//        mCalendar = calendar;
//        mMonth = mCalendar.get(Calendar.MONTH);
//        mYear = mCalendar.get(Calendar.YEAR);
//        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
//        mDayLength = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
//        mExtraDay = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
//        mCurrentMonth = mMonth;
//        Log.e("TAG", "mExtraDay:" + mExtraDay);

        mDatas = new ArrayList<>();

        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());
        for (int i = 0; i < 1000; i++) {
            SelfDate item = new SelfDate();
            int month = mCalendar.get(Calendar.MONTH);
            int year = mCalendar.get(Calendar.YEAR);
            item.setmMonth(mCalendar.get(Calendar.MONTH));
            item.setmYear(mCalendar.get(Calendar.YEAR));
            item.setmDay(mCalendar.get(Calendar.DAY_OF_MONTH));

            item.setmDayLength(mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

            mCalendar.set(Calendar.DAY_OF_MONTH, 1);
            item.setmExtraDay(mCalendar.get(Calendar.DAY_OF_WEEK) - 1);
            mDatas.add(item);
            if(month >= 11){
                month = 0;
                year += 1;
            }else{
                month += 1;
            }

            mCalendar.setTime(new Date(year - 1900, month, 1));

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_second_cal_item2, parent, false);
        return new SecondCalViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
//        if(holder1 instanceof SecondCalViewHolder){
//            SecondCalViewHolder holder = (SecondCalViewHolder) holder1;
//            if(position == 0 ){
//                for (int i = 0; i < mExtraDay; i++) {
//                    holder.tv[i].setText("");
//                }
//                for (int i = mExtraDay; i <= 6 ; i++) {
//                    holder.tv[i].setText((i - mExtraDay + 1) + "");
//                }
//                mLastDay = 7 - mExtraDay;
//            }
//            else{
//                for (int i = 0; i <= 6 ; i++) {
//                    if(mLastDay > mDayLength){
//                        holder.tv[i].setText("");
//                    }else{
//                        holder.tv[i].setText(""+ ++mLastDay);
//                    }
//                }
//                if(mLastDay > mDayLength){
//                    mLastDay = 0;
//                }
//            }
//
//
//        }


        if(holder1 instanceof SecondCalViewHolder2){
            SecondCalViewHolder2 holder = (SecondCalViewHolder2) holder1;
            SelfDate item = mDatas.get(position);
            holder.tv_title.setText(item.getmYear()+"年 "+(item.getmMonth()+1)+"月"+item.getmDay()+"日");
            holder.rv.setAdapter(new CalenderAdapter2(mDatas.get(position)));

        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class SecondCalViewHolder extends RecyclerView.ViewHolder {
        TextView[] tv = new TextView[7];

        public SecondCalViewHolder(View itemView) {
            super(itemView);
            tv[0] = (TextView) itemView.findViewById(R.id.tv_second_cal_1);
            tv[1] = (TextView) itemView.findViewById(R.id.tv_second_cal_2);
            tv[2] = (TextView) itemView.findViewById(R.id.tv_second_cal_3);
            tv[3] = (TextView) itemView.findViewById(R.id.tv_second_cal_4);
            tv[4] = (TextView) itemView.findViewById(R.id.tv_second_cal_5);
            tv[5] = (TextView) itemView.findViewById(R.id.tv_second_cal_6);
            tv[6] = (TextView) itemView.findViewById(R.id.tv_second_cal_7);
        }
    }

    class SecondCalViewHolder2 extends RecyclerView.ViewHolder {
        RecyclerView rv;
        TextView tv_title;

        public SecondCalViewHolder2(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.rv_second_cal_item);
            tv_title = (TextView) itemView.findViewById(R.id.tv_rv_cal_title);
            rv.setLayoutManager(new GridLayoutManager(itemView.getContext(),7,GridLayoutManager.VERTICAL,false));
        }
    }

}
