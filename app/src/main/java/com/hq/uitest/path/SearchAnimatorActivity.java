package com.hq.uitest.path;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.hq.uitest.R;

import static com.hq.uitest.R.id.btn_search_reset;
import static com.hq.uitest.R.id.btn_search_start;
import static com.hq.uitest.R.id.switch1;

public class SearchAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private SearchAnimatorView sv;
    private Button btn_start,btn_reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_animator);


        sv = (SearchAnimatorView) findViewById(R.id.search_view);
        btn_reset = (Button) findViewById(btn_search_reset);
        btn_start = (Button) findViewById(btn_search_start);



        btn_reset.setOnClickListener(this);
        btn_start.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_search_reset:
                sv.resetAnim();
                break;
            case R.id.btn_search_start:
                sv.startAnim();
                break;
        }

    }
}
