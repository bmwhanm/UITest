package com.hq.uitest.tangram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.hq.uitest.R;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;

public class TangramActivity extends AppCompatActivity {
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangram);

        initTangram();
    }

    private void initTangram() {
        TangramBuilder.InnerBuilder builder = TangramBuilder.newInnerBuilder(TangramActivity.this);
        TangramEngine engine = builder.build();
        mRv = (RecyclerView) findViewById(R.id.rv_tangram);

        engine.bindView(mRv);
    }
}
