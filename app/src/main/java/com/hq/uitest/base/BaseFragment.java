package com.hq.uitest.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hq.uitest.R;

/**
 *
 * @author heqiang
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected View mContentView;
    protected Context mContext;
    protected LoadingProgress progressDialog;
    private FrameLayout lay_root;
    private View lay_content;
    private ViewStub lay_error;
    private ImageView iv_err;
    private String mErrorInfo;
    private int mErrorResId = -1;
    private TextView tv_refresh_error,tv_err;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.activity_base, container, false);
        lay_root = (FrameLayout) mContentView.findViewById(R.id.lay_base_root);
        inflater.inflate(bindLayoutId(), lay_root, true);
//        lay_content = lay_root.getChildAt(1);
        lay_error = (ViewStub) mContentView.findViewById(R.id.lay_base_error);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        registerReceiver();
        initListener();
        initData();
        initPresenter();
        mContext = getActivity();
    }

    /**
     * 绑定布局ID
     *
     * @return
     */
    protected abstract int bindLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化Data
     */
    protected abstract void initData();

    /**
     * 初始化监听等事件
     */
    protected abstract void initListener();

    /**
     * 初始化Presenter,用MVP
     */
    protected abstract void initPresenter();

    /**
     * 注册广播
     */
    protected void registerReceiver() {
    }

    /**
     * 注销广播
     */
    protected void unRegisterReceiver() {
    }


    /**
     * 显示载入框
     *
     * @param msg
     */
    public void showLoading(String msg) {
        if(mContext == null) return;
        if (progressDialog == null) {
            progressDialog = new LoadingProgress(mContext, msg);
        }
        progressDialog.show();

    }

    public void showLoading() {
        if(mContext == null) return;
        if (progressDialog == null) {
            progressDialog = new LoadingProgress(mContext, "努力加载中....");
        }
        progressDialog.show();

    }

    /**
     * 取消载入框
     */
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
//            progressDialog = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unRegisterReceiver();
    }


    protected void showError() {
//        lay_content.setVisibility(View.GONE);
        lay_error.setVisibility(View.VISIBLE);
        tv_refresh_error = findViewId(R.id.tv_base_refresh);
        tv_err = findViewId(R.id.tv_base_err);
        iv_err = findViewId(R.id.iv_base_err);
        if(mErrorResId != -1) {
            iv_err.setImageResource(mErrorResId);
        }
        if(mErrorInfo != null) {
            tv_err.setText(mErrorInfo);
        }
        tv_refresh_error.setOnClickListener(this);
    }

    public void setErrorInfo(String errorInfo) {
        this.mErrorInfo = errorInfo;
    }

    public void setErrorResId(int errorResId) {
        this.mErrorResId = errorResId;
    }

    protected abstract void clickErrorRefresh();

    protected void hideError() {
        lay_error.setVisibility(View.GONE);
//        lay_content.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_refresh:
                clickErrorRefresh();
                break;
        }
    }

    protected <T extends View> T findViewId(int resId) {
        return (T) mContentView.findViewById(resId);
    }



}
