package com.hq.uitest.basetest;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hq.uitest.R;
import com.hq.uitest.SelfDate;
import com.hq.uitest.base.BaseApplication;
import com.hq.uitest.base.BaseTitleActivity;

public class TestBaseTitleActivity extends BaseTitleActivity {
    private Button btn_nodata,btn_no_net,btn_error,btn_showProgress;
    private Fragment mFragment;
    private SelfDate mDate ;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_test_base_title;
    }

    @Override
    protected void initView() {
        super.initView();
        setBaseTitle("测试设置标题");
        btn_error = findViewId(R.id.btn_show_error);
        btn_no_net = findViewId(R.id.btn_show_no_network);
        btn_nodata = findViewId(R.id.btn_show_nodata);
        btn_showProgress = findViewId(R.id.btn_show_progress);
    }

    @Override
    protected void initListener() {
        super.initListener();
        btn_error.setOnClickListener(this);
        btn_nodata.setOnClickListener(this);
        btn_no_net.setOnClickListener(this);
        btn_showProgress.setOnClickListener(this);
        mDate = new SelfDate();
        mDate.setmDay(1);
        mDate.setmMonth(2);
        mDate.setmYear(12);
    }

    @Override
    protected void initData() {

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
            case R.id.btn_show_nodata:
                BaseApplication.putValueByKey("SelfDate",mDate);
                break;
            case R.id.btn_show_error:
                SelfDate date = (SelfDate) BaseApplication.getValueByKey("SelfDate");
                Log.e("TAG","Day:"+date.getmDay()+"  Month :"+date.getmMonth()+"  Year:"+date.getmYear());
                break;
            case R.id.btn_show_no_network:
//                setErrorInfo("this is error text");
//                setErrorResId(R.mipmap.ic_launcher);
                showError();
                break;
            case R.id.btn_show_progress:
                showLoading();
                break;
            default:
                break;
        }

    }
}
