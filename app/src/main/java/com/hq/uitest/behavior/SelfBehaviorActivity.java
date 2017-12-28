package com.hq.uitest.behavior;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;
import com.hq.uitest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqiang on 17/12/27.
 */

public class SelfBehaviorActivity extends BaseActivity {
    private RecyclerView mRv;
    private TextView tvBehavior;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_self_behavior;
    }

    @Override
    protected void initView() {

        mRv = findViewId(R.id.rv_self_behavior);
//        tvBehavior = findViewId(R.id.tv_self_behavior);

        mRv.setLayoutManager(new LinearLayoutManager(this));
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("第" + i + "项");
        }
        StringAdapter adapter = new StringAdapter(mData);
        mRv.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void clickErrorRefresh() {

    }
}
