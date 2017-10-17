package com.hq.uitest.aroute;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hq.uitest.R;
@Route(path = "/aroute/first")
public class ArouteActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_params,btn_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aroute);
//        String uriStr = getIntent().getStringExtra(ARouter.RAW_URI);
//        Log.e("TAG","uri:"+uriStr.toString());

        initView();
    }

    private void initView() {
        btn_params = (Button) findViewById(R.id.btn_aroute_params);
        btn_service = (Button) findViewById(R.id.btn_aroute_service);



        btn_params.setOnClickListener(this);
        btn_service.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_aroute_params:
                ArouteWithParamsActivity.launch(this);
                break;
            case R.id.btn_aroute_service:
                TestServiceActivity.launch(this);
                break;
        }

    }
}
