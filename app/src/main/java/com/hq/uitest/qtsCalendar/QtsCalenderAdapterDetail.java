package com.hq.uitest.qtsCalendar;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hq.uitest.R;
import com.hq.uitest.SelfDate;

import java.util.Calendar;

/**
 * Created by heqiang on 17/9/11.
 */

public class QtsCalenderAdapterDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Calendar mCalendar;
    private int mMonth, mDay, mYear, mDayLength, mExtraDay;
    private int nowDay, nowMonth, lastShowMonth, lastShowDay;
    private TextView tvTitle;

    public QtsCalenderAdapterDetail(SelfDate selfDate, int lastDay, int lastMonth, int lastYear, TextView tvTitle) {
        mCalendar = Calendar.getInstance();
        nowMonth = mCalendar.get(Calendar.MONTH);
        if (nowMonth == selfDate.getmMonth()) {
            nowDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        } else {
            nowDay = 1;
        }
        lastShowDay = lastDay;
        lastShowMonth = lastMonth;
        this.tvTitle = tvTitle;


        mCalendar.set(selfDate.getmYear(), selfDate.getmMonth(), selfDate.getmDay());
        mMonth = mCalendar.get(Calendar.MONTH);
        mYear = mCalendar.get(Calendar.YEAR);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDayLength = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        mExtraDay = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        Log.e("TAG", "mExtraDay:" + mExtraDay);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_calender, parent, false);
        return new CalenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        if (holder1 instanceof CalenderViewHolder) {
            final CalenderViewHolder holder = (CalenderViewHolder) holder1;

            if (position < mExtraDay) {
                holder.tv_cal.setVisibility(View.GONE);
                return;
            }

            if (mMonth == lastShowMonth) {
                int toDay = (position - mExtraDay + 1);
                mCalendar.set(Calendar.DAY_OF_MONTH, lastShowDay);
                int firstWeekNum = mCalendar.get(Calendar.WEEK_OF_MONTH);
                mCalendar.set(Calendar.DAY_OF_MONTH, toDay);
                int nowWeekNum = mCalendar.get(Calendar.WEEK_OF_MONTH);
                if (nowWeekNum > firstWeekNum) {
                    holder.tv_cal.setVisibility(View.GONE);
                    return;
                } else {
                    holder.tv_cal.setVisibility(View.VISIBLE);
                    if (toDay <= lastShowDay) {
                        holder.tv_cal.setEnabled(true);
                        holder.tv_cal.setTextColor(holder.tv_cal.getContext().getResources().getColor(R.color.colorAccent));
                    } else {
                        holder.tv_cal.setTextColor(Color.rgb(0, 0, 0));
                        holder.tv_cal.setEnabled(false);
                    }
                }


            } else {
                int toDay = (position - mExtraDay + 1);
                mCalendar.set(Calendar.DAY_OF_MONTH, nowDay);
                int firstWeekNum = mCalendar.get(Calendar.WEEK_OF_MONTH);
                mCalendar.set(Calendar.DAY_OF_MONTH, toDay);
                int nowWeekNum = mCalendar.get(Calendar.WEEK_OF_MONTH);
                if (nowWeekNum < firstWeekNum) {
                    holder.tv_cal.setVisibility(View.GONE);
                    return;
                } else {
                    holder.tv_cal.setVisibility(View.VISIBLE);
                    if (toDay < nowDay) {
                        holder.tv_cal.setTextColor(Color.rgb(0, 0, 0));
                        holder.tv_cal.setEnabled(false);
                    } else {
                        holder.tv_cal.setEnabled(true);
                        holder.tv_cal.setTextColor(holder.tv_cal.getContext().getResources().getColor(R.color.colorAccent));
                    }
                }
            }


            holder.tv_cal.setText((position - mExtraDay + 1) + "");
            holder.tv_cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.tv_cal.getText().toString() != "")
                        Toast.makeText(v.getContext(), holder.tv_cal.getText().toString(), Toast.LENGTH_SHORT).show();
                    v.setBackground(v.getContext().getResources().getDrawable(R.drawable.cal_background));
                }
            });

            int toDay = (position - mExtraDay + 1);
            if (toDay == 1) {
                int month = mMonth + 1;
                tvTitle.setText("" + month + "月");
                tvTitle.scrollTo(holder.lay_root.getLeft(), 0);
                tvTitle.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public int getItemCount() {
        return mDayLength + mExtraDay;
    }

    class CalenderViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cal;
        LinearLayout lay_root;

        public CalenderViewHolder(View itemView) {
            super(itemView);
            tv_cal = (TextView) itemView.findViewById(R.id.tv_rv_cal);
            lay_root = (LinearLayout) itemView.findViewById(R.id.lay_rv_cal_root);
        }
    }

}
