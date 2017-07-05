package com.hq.uitest.recyclerviewt.headandfootrv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeaderRvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_rv);
        WrapRecyclerView rv_wrap = (WrapRecyclerView) findViewById(R.id.rv_wrap);
        TextView tv = new TextView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        tv.setLayoutParams(params);
        tv.setText("this is Header");
        rv_wrap.addHeaderView(tv);


        TextView tv2 = new TextView(this);
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        tv2.setLayoutParams(params2);
        tv2.setText("this is Footer");
        rv_wrap.addFooterView(tv2);

        rv_wrap.setLayoutManager(new LinearLayoutManager(this));
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("第" + i + "项");
        }
        rv_wrap.setAdapter( new StringAdapter(mData));
    }
}
