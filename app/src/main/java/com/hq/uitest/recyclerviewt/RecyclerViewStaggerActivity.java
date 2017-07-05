package com.hq.uitest.recyclerviewt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewStaggerActivity extends AppCompatActivity implements View.OnClickListener {
    private MyStaggerAdapter mAdapter;
    private RecyclerView rv;
    private List<String> mData;
    private Button btn_list,btn_grid,btn_stagger;
    private StringAdapter mStringAdapter;
    private Button btn_add,btn_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_stagger);

        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("第" + i + "项");
        }

        rv = (RecyclerView) findViewById(R.id.rv_stagger);
        rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MyStaggerAdapter(mData);
        rv.setAdapter(mAdapter);
        mStringAdapter = new StringAdapter(mData);


        btn_list = (Button) findViewById(R.id.btn_list);
        btn_grid = (Button) findViewById(R.id.btn_grid);
        btn_stagger = (Button) findViewById(R.id.btn_stagger);
        btn_add = (Button) findViewById(R.id.btn_add_item);
        btn_remove = (Button) findViewById(R.id.btn_remove_item);

        rv.addItemDecoration(new MyDividerItemDecoration(this));


        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.setAdapter(mStringAdapter);
                rv.setLayoutManager(new LinearLayoutManager(RecyclerViewStaggerActivity.this));
            }
        });
        btn_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.setAdapter(mStringAdapter);
                rv.setLayoutManager(new GridLayoutManager(RecyclerViewStaggerActivity.this,3));

            }
        });
        btn_stagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.setAdapter(mAdapter);
                rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
            }
        });


        btn_add.setOnClickListener(this);
        btn_remove.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_item:
                mAdapter.addItem(0);
                break;
            case R.id.btn_remove_item:
                mAdapter.removeItem(0);
                break;
        }
    }
}
