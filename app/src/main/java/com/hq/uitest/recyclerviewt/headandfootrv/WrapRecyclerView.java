package com.hq.uitest.recyclerviewt.headandfootrv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqiang on 17/6/9.
 */

public class WrapRecyclerView extends RecyclerView {
    private List<View> mHeaderViews;
    private List<View> mFooterViews;
    private Adapter mAdapter;

    public WrapRecyclerView(Context context) {
        this(context, null);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }


    public void addHeaderView(View view) {
        mHeaderViews.add(view);
        mAdapter = new HeadRecyclerAdapter(mHeaderViews, mFooterViews, mAdapter);
    }
    public void addFooterView(View view){
        mFooterViews.add(view);
        mAdapter = new HeadRecyclerAdapter(mHeaderViews,mFooterViews,mAdapter);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        if(mHeaderViews.size() != 0 || mFooterViews.size() != 0){
            mAdapter = new HeadRecyclerAdapter(mHeaderViews,mFooterViews,adapter);
        }else{
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }
}
