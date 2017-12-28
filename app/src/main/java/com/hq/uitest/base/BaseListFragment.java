package com.hq.uitest.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hq.uitest.R;


/**
 * @author heqiang
 */
public class BaseListFragment extends BaseFragment {
    protected RecyclerView mRv;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void initView() {
        mRv = findViewId(R.id.rv_base_list);
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
