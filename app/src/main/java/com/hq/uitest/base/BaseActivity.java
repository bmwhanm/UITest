package com.hq.uitest.base;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hq.uitest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heqiang
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewStub lay_error;
    protected LoadingProgress progressDialog;
    private FrameLayout lay_root;
    private TextView tv_refresh_error,tv_err;
    private ImageView iv_err;
    private String mErrorInfo;
    private int mErrorResId = -1;
    private PermissionListener mListener;
    private static final int PERMISSION_REQUESTCODE = 0xffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
        lay_error = findViewId(R.id.lay_base_error);
        lay_root = findViewId(R.id.lay_base_root);
        getLayoutInflater().inflate(bindLayoutId(), lay_root, true);

        registerReceiver();

        initView();
        initListener();
        initData();
        initPresenter();
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
        if (progressDialog == null) {
            progressDialog = new LoadingProgress(this, msg);
        }
        progressDialog.show();

    }

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new LoadingProgress(this, "努力加载中....");
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


    protected void showError() {
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

    protected abstract void clickErrorRefresh() ;

    protected void hideError() {
        lay_error.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_base_refresh:
                clickErrorRefresh();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterReceiver();
    }
    public void requestRunPermisssion(String[] permissions, PermissionListener listener){
        mListener = listener;
        List<String> permissionLists = new ArrayList<>();
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                permissionLists.add(permission);
            }
        }

        if(!permissionLists.isEmpty()){
            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
        }else{
            //表示全都授权了
            mListener.onGranted();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUESTCODE:
                if(grantResults.length > 0){
                    //存放没授权的权限
                    List<String> deniedPermissions = new ArrayList<>();
                    for(int i = 0; i < grantResults.length; i++){
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission);
                        }
                    }
                    if(deniedPermissions.isEmpty()){
                        //说明都授权了
                        mListener.onGranted();
                    }else{
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }


    protected <T extends View> T findViewId(int resId) {
        return (T) findViewById(resId);
    }


}
