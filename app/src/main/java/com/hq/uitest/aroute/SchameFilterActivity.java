package com.hq.uitest.aroute;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hq.uitest.R;

public class SchameFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();

        Toast.makeText(this,"SchameFilterActivity uri: "+uri.toString(),Toast.LENGTH_SHORT).show();
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}
