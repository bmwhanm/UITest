package com.hq.uitest.qtsCalendar;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.SelfDate;
import com.hq.uitest.canlender.CalenderAdapter2;
import com.hq.uitest.util.ScreenUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by heqiang on 17/9/12.
 */

public class QtsCalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Calendar mCalendar;
    private List<SelfDate> mDatas;

    public QtsCalAdapter() {

        mDatas = new ArrayList<>();

        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());
        int size  = 3;
//        if(mCalendar.get(Calendar.DAY_OF_MONTH) == 1){
//            size = 2;
//        }
        int toDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < size; i++) {
            SelfDate item = new SelfDate();
            int month = mCalendar.get(Calendar.MONTH);
            int year = mCalendar.get(Calendar.YEAR);
            item.setmMonth(mCalendar.get(Calendar.MONTH));
            item.setmYear(mCalendar.get(Calendar.YEAR));

            item.setmDayLength(mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

            if(i == size - 1) {
                mCalendar.set(Calendar.DAY_OF_MONTH,toDay );
            }else{
                mCalendar.set(Calendar.DAY_OF_MONTH, 1);
            }
            item.setmDay(mCalendar.get(Calendar.DAY_OF_MONTH));
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


        if(holder1 instanceof SecondCalViewHolder2){
            SecondCalViewHolder2 holder = (SecondCalViewHolder2) holder1;
            SelfDate item = mDatas.get(position);

            int p = item.getmExtraDay() == 0 ? 1 : item.getmExtraDay();
            float pad = ScreenUtil.getScreenWidth(holder.rv.getContext()) / 7.0f * p;
            pad += ScreenUtil.dp2px(holder.tv_title.getContext(),10);

            holder.tv_title.setPadding((int) pad,0,0,0);
            holder.tv_title.requestLayout();
            holder.rv.setAdapter(new QtsCalenderAdapterDetail(mDatas.get(position),mDatas.get(2).getmDay(),mDatas.get(2).getmMonth(),mDatas.get(2).getmYear(),holder.tv_title));

        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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
