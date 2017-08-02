package com.hq.uitest.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hq.uitest.R;

public class ScrollActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout lay_container;
    private TextView tv_content;
    private Button btn_to,btn_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        lay_container = (LinearLayout) findViewById(R.id.lay_scroll_container);
        tv_content = (TextView) findViewById(R.id.tv_scroll_content);
        btn_to = (Button) findViewById(R.id.btn_scroll_to);
        btn_by = (Button) findViewById(R.id.btn_scroll_by);


        btn_to.setOnClickListener(this);
        btn_by.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scroll_to:
                lay_container.scrollTo(100,100);
                break;
            case R.id.btn_scroll_by:
                lay_container.scrollBy(-10,-10);
                break;
        }

        Log.e("TAG","lay:   left:" + lay_container.getLeft()+"  ,right:"+lay_container.getRight()+",top:"+lay_container.getTop()+"  ,X:"+lay_container.getX()+"  ,Y:"+lay_container.getY()+" ScrollX:"+lay_container.getScrollX()+"  ScrollY:"+lay_container.getScrollY());
        Log.e("TAG","tv_content :  left:"+tv_content.getLeft()+"  ,right:"+tv_content.getRight()+" ,top:"+tv_content.getTop()+"  ,X:"+tv_content.getX()+"  ,Y:"+tv_content.getY()+" ScrollX:"+tv_content.getScrollX()+"  ScrollY:"+tv_content.getScrollY());





    }
}
