package com.hq.uitest.refreshcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;

public class RefreshControlSelfActivity extends AppCompatActivity {
    private RecyclerView rv_refresh;
    private TextView tv_refresh;
    private MyLinearLayout lay_content;
    private LinearLayoutManager manager;
    private StringAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_control);
        initView();
    }

    private void initView() {
        rv_refresh = (RecyclerView) findViewById(R.id.rv_refresh_control);
        tv_refresh = (TextView) findViewById(R.id.tv_refresh_control);
        lay_content = (MyLinearLayout) findViewById(R.id.lay_refresh_control);
//        lay_content.scrollTo(0,lay_content.getMeasuredHeight());

//        lay_content.setListener(new MyLinearLayout.myOnScrollListener() {
//            @Override
//            public void onScroll(int dY) {
//                if(dY < -100)tv_refresh.setText("松开就刷新");
//                else tv_refresh.setText("继续下拉才刷新");
////                Log.e("TAG","onScrollY :" +dY);
//            }
//
//            @Override
//            public void onFinish() {
//                tv_refresh.setText("继续下拉才刷新");
//            }
//        });




    }
}
