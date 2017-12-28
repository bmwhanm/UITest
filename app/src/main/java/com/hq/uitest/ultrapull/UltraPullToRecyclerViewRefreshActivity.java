package com.hq.uitest.ultrapull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class UltraPullToRecyclerViewRefreshActivity extends AppCompatActivity {
    private PtrFrameLayout mPtrFrameLayout;
    private RecyclerView mRv;
    private List<String> mData;
    private StringAdapter mAdapter;
    private View mHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_pull_to_refresh);
        mPtrFrameLayout = findViewById(R.id.ptr_ultra_rv_refresh);
        mRv = findViewById(R.id.rv_ultra);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);

        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String str = "第" + i +"项";
            mData.add(str);
        }

        mAdapter = new StringAdapter(mData);
        mRv.setAdapter(mAdapter);

        //回弹延时
        mPtrFrameLayout.setDurationToClose(300);

        //头部回弹时间
        mPtrFrameLayout.setDurationToCloseHeader(1000);

        //刷新是保持头部
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);

        //下拉刷新 / 释放刷新
        mPtrFrameLayout.setPullToRefresh(false);

        //触发刷新时移动的位置比例
        mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1);


        FirstHeader firstHeader = new FirstHeader(this);
        mPtrFrameLayout.setHeaderView(firstHeader);

        mPtrFrameLayout.addPtrUIHandler(firstHeader);


        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 1800);


                Log.e("TAG","onRefreshBegin。。。。");
            }
        });


//        mHeaderView = getLayoutInflater().inflate(R.layout.header_refresh,null);

//        mPtrFrameLayout.setHeaderView(mHeaderView);







    }
}
