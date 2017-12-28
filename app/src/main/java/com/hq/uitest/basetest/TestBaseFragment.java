package com.hq.uitest.basetest;

import android.view.View;
import android.widget.Button;

import com.hq.uitest.R;
import com.hq.uitest.base.BaseFragment;

/**
 * Created by heqiang on 17/10/24.
 */

public class TestBaseFragment extends BaseFragment {
    private Button btn_pro,btn_error;

    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_test_base;
    }

    @Override
    protected void initView() {
        btn_pro = findViewId(R.id.btn_test_base_progress);
        btn_error = findViewId(R.id.btn_test_base_error);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btn_error.setOnClickListener(this);
        btn_pro.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void clickErrorRefresh() {
        hideError();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_test_base_progress:
                showLoading();
                break;
            case R.id.btn_test_base_error:
                setErrorResId(R.mipmap.ic_launcher);
                setErrorInfo("testBaseFragment");
                showError();
                break;
        }
    }
}
