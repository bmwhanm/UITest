package com.hq.uitest.aigeselfview;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hq.uitest.R;

public class FirstActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        FirstView firstView = (FirstView) findViewById(R.id.first_view);
        new Thread(firstView).start();
        TextView tv = (TextView) findViewById(R.id.tv_first);
//        tv.setTypeface(Typeface.BOLD);
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

    }
}
