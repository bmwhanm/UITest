package com.hq.uitest.canlender;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hq.uitest.R;
import com.hq.uitest.SelfDate;

import java.util.Calendar;

/**
 * Created by heqiang on 17/9/11.
 */

public class CalenderAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Calendar mCalendar;
    private int mMonth,mDay,mYear,mDayLength,mExtraDay;

    public CalenderAdapter2(Calendar calendar ){
        mCalendar = calendar;
        mMonth = mCalendar.get(Calendar.MONTH) ;
        mYear = mCalendar.get(Calendar.YEAR);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDayLength = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        mCalendar.set(Calendar.DAY_OF_MONTH,1);
        mExtraDay = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        Log.e("TAG","mExtraDay:"+mExtraDay);
    }

    public CalenderAdapter2(SelfDate selfDate){
        mCalendar = Calendar.getInstance();
        mCalendar.set(selfDate.getmYear(),selfDate.getmMonth(),selfDate.getmDay());
        mMonth = mCalendar.get(Calendar.MONTH) ;
        mYear = mCalendar.get(Calendar.YEAR);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDayLength = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        mCalendar.set(Calendar.DAY_OF_MONTH,1);
        mExtraDay = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        Log.e("TAG","mExtraDay:"+mExtraDay);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_calender,parent,false);
        return new CalenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        if(holder1 instanceof CalenderViewHolder) {
            final CalenderViewHolder holder = (CalenderViewHolder) holder1;
//            switch (position){
//                case 0:
//                    holder.tv_cal.setText("日");
//                    break;
//                case 1:
//                    holder.tv_cal.setText("一");
//                    break;
//                case 2:
//                    holder.tv_cal.setText("二");
//                    break;
//                case 3:
//                    holder.tv_cal.setText("三");
//                    break;
//                case 4:
//                    holder.tv_cal.setText("四");
//                    break;
//                case 5:
//                    holder.tv_cal.setText("五");
//                    break;
//                case 6:
//                    holder.tv_cal.setText("六");
//                    break;
//                default:
                    if(position < mExtraDay  ){
                        holder.tv_cal.setText("");
                    }else{
                        holder.tv_cal.setText((position- mExtraDay + 1 ) + "");
                    }
//                    break;
//            }
            holder.tv_cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.tv_cal.getText().toString()  != "")
                    Toast.makeText(v.getContext(),holder.tv_cal.getText().toString(),Toast.LENGTH_SHORT).show();
                    v.setBackground(v.getContext().getResources().getDrawable(R.drawable.cal_background));
                }
            });
        }

    }

    public void onMonthAdd(){
        if(mMonth >= 12){
            mMonth = 1;
            mYear += 1;
        }else{
            mMonth += 1;
        }
    }

    public void onMonthReduce(){
        if(mMonth <= 1){
            mMonth = 12;
            mYear -= 1;
        }else{
            mMonth -= 1;
        }
    }

    @Override
    public int getItemCount() {
        return mDayLength + mExtraDay ;
    }

    class CalenderViewHolder extends RecyclerView.ViewHolder{

        TextView tv_cal;
        public CalenderViewHolder(View itemView) {
            super(itemView);
            tv_cal = (TextView) itemView.findViewById(R.id.tv_rv_cal);
        }
    }

}
