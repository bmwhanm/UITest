package com.hq.uitest.calendarevent;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hq.uitest.R;
import com.hq.uitest.base.BaseActivity;
import com.hq.uitest.base.PermissionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TestCalendarEventActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_common, btn_repeat, btn_read, btn_delete;


    @Override
    protected int bindLayoutId() {
        return R.layout.activity_test_calendar_event;
    }

    @Override
    protected void initView() {
        btn_common = (Button) findViewById(R.id.btn_event_common);
        btn_repeat = (Button) findViewById(R.id.btn_event_repeat);
        btn_read = (Button) findViewById(R.id.btn_event_read);
        btn_delete = findViewId(R.id.btn_event_delete);

        btn_common.setOnClickListener(this);
        btn_repeat.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_event_common:
                clickCommon();
                break;
            case R.id.btn_event_repeat:
                addRepeatEvent();
                break;
            case R.id.btn_event_read:
                checkData();
                break;
            case R.id.btn_event_delete:
                deleteEvent();
                break;
            default:
                break;
        }
    }

    private int deleteEvent() {

        String title = "青团社早起打卡";
        String desc = "早起打卡获得青豆";
        ContentResolver cr = getContentResolver();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            return -1;
        }
        int roews = cr.delete(CalendarContract.Events.CONTENT_URI, " title=?  and description = ? ", new String[]{title, desc});
        Log.e("TAG","rows:"+roews);
        Cursor cursor = null;
        try {
            cursor = cr.query(CalendarContract.Events.CONTENT_URI, null, " title=?  and description = ? ", new String[]{title, desc}, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return 0;
            }else{
                if(cursor.moveToFirst()){
                    do {
                        long eventID = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events._ID));
                        Uri deleteUri = null;
                        deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
                        int rows = cr.delete(deleteUri, null, null);
                        Log.e("TAG","rows:"+rows);
                    }while (cursor.moveToNext());
                }

                cursor = cr.query(CalendarContract.Events.CONTENT_URI, null, " title=?  and description = ? ", new String[]{title, desc}, null);
                if (cursor == null || !cursor.moveToFirst()) {
                    return 0;
                }

            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return -1;
    }

    private void clickCommon() {
        Date today=new Date();
        SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");
        String time= f.format(today);
        try {
            Date date = f.parse("12:12:32");
            Log.e("TAG","date:"+f.format(date));
            int hour = date.getHours();
            int minute = date.getMinutes();
            int second = date.getSeconds();
            Log.e("TAG","  hour:"+hour+"  minute: "+minute+"  second:"+second);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.e("TAG","time:"+time);
    }


    public void checkData() {
//        ContentResolver cr = getContentResolver();
//        Cursor cursor = null;

        requestRunPermisssion(new String[]{Manifest.permission.WRITE_CALENDAR}, new PermissionListener() {
            @Override
            public void onGranted() {

                Cursor cursor = null;
                ContentResolver cr = getContentResolver();

                if (ActivityCompat.checkSelfPermission(TestCalendarEventActivity.this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                cursor = cr.query(CalendarContract.Events.CONTENT_URI, null, null, null, null, null);
                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.RRULE, "FREQ=DAILY;WKST=MO");

                if (cursor != null) {
                    //开始读取其中的数据
                    if (cursor.moveToFirst()) {
                        do {

                            String rrule = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.RRULE));
                            String rDate = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.RDATE));
                            String DTSTART = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTSTART));
                            String DTEND = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTEND));
                            String TITLE = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
                            String desc = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
                            String EVENT_TIMEZONE = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.EVENT_TIMEZONE));
                            String CALENDAR_ID = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.CALENDAR_ID));
                            String HAS_ALARM = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.HAS_ALARM));

                            Log.e("TAG", "rrule :" + rrule + "   rDate:" + rDate + "   DTSTART:" + DTSTART
                                    + "   DTEND:" + DTEND
                                    + "   TITLE:" + TITLE
                                    + "   desc:" + desc
                                    + "   EVENT_TIMEZONE:" + EVENT_TIMEZONE
                                    + "   HAS_ALARM:" + HAS_ALARM
                                    + "   CALENDAR_ID:" + CALENDAR_ID);

                        } while (cursor.moveToNext());
                    }
                }
            }

            @Override
            public void onDenied(List<String> deniedPermission) {

            }
        });
//        requestPermissions(new String[]{ Manifest.permission.WRITE_CALENDAR},1);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
////            // TODO: Consider calling
////            //    ActivityCompat#requestPermissions
////            // here to request the missing permissions, and then overriding
////            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////            //                                          int[] grantResults)
////            // to handle the case where the user grants the permission. See the documentation
////            // for ActivityCompat#requestPermissions for more details.
//            cursor = cr.query(CalendarContract.Events.CONTENT_URI, null, null, null, null, null);
//
//        }

    }


    public void addRepeatEvent() {
        requestRunPermisssion(new String[]{Manifest.permission.WRITE_CALENDAR}, new PermissionListener() {
            @Override
            public void onGranted() {

                Cursor cursor = null;
                ContentResolver cr = getContentResolver();

                if (ActivityCompat.checkSelfPermission(TestCalendarEventActivity.this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
//                cursor =  cr.query(CalendarContract.Events.CONTENT_URI, null, null, null, null, null);
                ContentValues values = new ContentValues();
                Calendar calendar = Calendar.getInstance();
                String title = "青团社早起打卡";
                String desc = "打卡获得青豆";
                long startTime = calendar.getTimeInMillis()  +  24 * 60 * 60*1000 ;
                calendar.setTime(new Date(startTime));

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(year,month,day,7,50,0);
                startTime = calendar.getTimeInMillis();
                calendar.set(year,month,day,7,55,0);
                long endTime = calendar.getTimeInMillis();
                String time_zone = "Asia/Shanghai";


                cursor = cr.query(CalendarContract.Events.CONTENT_URI, null, "title=?  and description = ?", new String[]{title, desc}, null);
                if (cursor != null && cursor.moveToFirst()) {
                    values.put(CalendarContract.Events.DTSTART, startTime  +  24 * 60 * 60*1000 );
                    values.put(CalendarContract.Events.DTEND, endTime  +  24 * 60 * 60*1000 );
                    values.put(CalendarContract.Events.RRULE, "FREQ=DAILY;WKST=MO");
                    values.put(CalendarContract.Events.EVENT_TIMEZONE, time_zone);
                    int res = cr.update(CalendarContract.Events.CONTENT_URI, values, "title=?  and description = ?", new String[]{title, desc});
                    if (res > 0) {
                        Log.e("TAG","update Calendar Event Success");
                        return;
                    }
                }
                values.put(CalendarContract.Events.DTSTART, startTime);
                values.put(CalendarContract.Events.DTEND, endTime);
                values.put(CalendarContract.Events.RRULE, "FREQ=DAILY;WKST=MO");
                values.put(CalendarContract.Events.EVENT_TIMEZONE, time_zone);
                long Calender_id = checkCalendarAccount(TestCalendarEventActivity.this);
                values.put(CalendarContract.Events.TITLE, title);
                values.put(CalendarContract.Events.DESCRIPTION, desc);
                values.put(CalendarContract.Events.CALENDAR_ID, Calender_id);
//                int rows = cr.update(CalendarContract.Events.CONTENT_URI, values," "+CalendarContract.Events.CALENDAR_ID+"=? and title=?  and description = ?",new String[]{""+Calender_id,title,desc});
                cr.delete(CalendarContract.Events.CONTENT_URI," title=?  and description = ? ",new String[]{title,desc});
//                Cursor cursor1 = cr.query(CalendarContract.Events.CONTENT_URI,null, " "+CalendarContract.Events.CALENDAR_ID+"=? and title=?  and description = ?",new String[]{""+Calender_id,title,desc},null);
//                long event_id =-1;
//                if(cursor1 != null){
//                    if (cursor1.moveToFirst()) {
//                        event_id = cursor1.getLong(cursor1.getColumnIndex(CalendarContract.Reminders.EVENT_ID));
//                    }
//                }
//                if (event_id == -1) {
//                    return;
//                }
                Uri new_event = cr.insert(CalendarContract.Events.CONTENT_URI,values);
                long event_id = Long.parseLong(new_event.getLastPathSegment());
                ContentValues values1 = new ContentValues();
                values1.put(CalendarContract.Reminders.EVENT_ID, event_id);
                values1.put(CalendarContract.Reminders.MINUTES, 0); //提前3分钟有提醒
                values1.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
                getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values1);
            }

            @Override
            public void onDenied(List<String> deniedPermission) {

            }
        });
    }

    private  int checkCalendarAccount(Context context) {
        if (ActivityCompat.checkSelfPermission(TestCalendarEventActivity.this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return -1 ;
        }
        Cursor userCursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null, null,null);
        try {
            if (userCursor == null)//查询返回空值
                return -1;
            int count = userCursor.getCount();
            if (count > 0) {//存在现有账户，取第一个账户的id返回
                userCursor.moveToFirst();
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }
    }
}
