package com.hq.uitest.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqiang on 17/7/24.
 */

public class MaterialDesignFragment extends Fragment {
    private LinearLayoutManager manager;
    private RecyclerView rv;
    private StringAdapter mAdapter;
    private List<String> mData;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_material,container,false);
        rv = (RecyclerView) view.findViewById(R.id.rv_material);
        manager = new LinearLayoutManager(container.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Item");
            sb.append(i);
            mData.add(sb.toString());
        }
        mAdapter = new StringAdapter(mData);
        rv.setAdapter(mAdapter);
        return view;
    }
}
