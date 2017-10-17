package com.hq.uitest.vlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.LayoutManagerHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.BaseLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LayoutChunkResult;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.hq.uitest.R;

import java.util.LinkedList;
import java.util.List;

public class VLayoutActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private VirtualLayoutManager mManager;
    private int mRecycledCount = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);

        mRv = (RecyclerView) findViewById(R.id.rv_vlayout);
        mManager = new VirtualLayoutManager(this);
        mRv.setLayoutManager(mManager);


        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRv.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,30);

        //第一种加载数据的方式
        firstMethodLoadData();

        //第二种加载数据的方式
//        secondMethodLoadData();



    }

    private void firstMethodLoadData() {
        DelegateAdapter adapter = new DelegateAdapter(mManager,true);
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        adapter.addAdapter(0,new MySubAdapter(this,new GridLayoutHelper(2)));
        adapter.addAdapter(1,new MySubAdapter2(this,new GridLayoutHelper(4)));
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();

        mRv.setAdapter(adapter);

        mRv.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                if(holder instanceof  MySubAdapter.ItemViewHolder){
                    Log.e("TAG","MySubAdapter.ItemView is Recycled");
                    holder.isRecyclable();
                    Log.e("TAG","RecyclerCount:"+mRecycledCount++);
                }
                if(holder instanceof MySubAdapter2.ItemViewHolder){
                    Log.e("TAG","MySubAdapter2.ItemView is Recycled");
                    Log.e("TAG","RecyclerCount:"+mRecycledCount++);
                }
            }
        });

    }

    private void secondMethodLoadData() {
        List<LayoutHelper> helpers = new LinkedList<>();
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setItemCount(25);
        helpers.add(gridLayoutHelper);

        GridLayoutHelper gridLayoutHelper2 = new GridLayoutHelper(2);
        gridLayoutHelper2.setItemCount(25);
        helpers.add(gridLayoutHelper2);

        MyAdapter adapter = new MyAdapter(mManager);
        adapter.setLayoutHelpers(helpers);
        mRv.setAdapter(adapter);
    }
}
