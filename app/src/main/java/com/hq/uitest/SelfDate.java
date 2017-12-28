package com.hq.uitest;

import java.io.Serializable;

/**
 * Created by heqiang on 17/9/12.
 */

public class SelfDate implements Serializable {
    private int mMonth;
    private int mYear;
    private int mDay;
    private int mExtraDay;
    private int mDayLength;

    public int getmDayLength() {
        return mDayLength;
    }

    public void setmDayLength(int mDayLength) {
        this.mDayLength = mDayLength;
    }

    public int getmExtraDay() {
        return mExtraDay;
    }

    public void setmExtraDay(int mExtraDay) {
        this.mExtraDay = mExtraDay;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }
}
