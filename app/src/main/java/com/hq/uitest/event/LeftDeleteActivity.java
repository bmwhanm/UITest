package com.hq.uitest.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hq.uitest.R;

public class LeftDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private DeleteLinearLayout lay_delete;
    private Button btn_open,btn_close,btn_can,btn_not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_delete);

        lay_delete = (DeleteLinearLayout) findViewById(R.id.lay_left_delete);

        btn_open = (Button) findViewById(R.id.btn_open);
        btn_close = (Button) findViewById(R.id.btn_close);
        btn_can = (Button) findViewById(R.id.btn_can);
        btn_not = (Button) findViewById(R.id.btn_not);



        btn_open.setOnClickListener(this);
        btn_close.setOnClickListener(this);
//        btn_not.setOnClickListener(this);
//        btn_can.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_open:
                lay_delete.openLeft();
                break;
            case R.id.btn_close:
                lay_delete.closeLeft();
                break;
            case R.id.btn_can:
//                lay_delete.setCanScroll(true);
                break;
            case R.id.btn_not:
//                lay_delete.setCanScroll(false);
                break;
        }
    }
}
