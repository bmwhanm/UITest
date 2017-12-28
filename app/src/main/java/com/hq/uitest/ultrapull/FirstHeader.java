package com.hq.uitest.ultrapull;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hq.uitest.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by heqiang on 17/12/8.
 */

public class FirstHeader extends FrameLayout implements PtrUIHandler {
    private View mHeaderView;
    private Context mContext;
    private TextView tvName;

    public FirstHeader(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FirstHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public FirstHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mHeaderView = LayoutInflater.from(mContext).inflate(R.layout.header_refresh,this,true);
        tvName = mHeaderView.findViewById(R.id.tv_first_header_name);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
//        Log.e("TAG","onUIReset ... ");
//        Log.e("TAG","onUIReset ... ");

        tvName.setText("onUIReset。。。。。");
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
//        Log.e("TAG","onUIRefreshPrepare ... ");
//        Log.e("TAG","onUIRefreshPrepare ... ");

        tvName.setText("onUIRefreshPrepare。。。。。");
        Log.e("TAG","onUIRefreshPrepare ...top####"+mHeaderView.getTop() + "... scrollY####"+mHeaderView.getScrollY() + " ... translationY" + mHeaderView.getTranslationY());

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

//        Log.e("TAG","onUIRefreshBegin ... ");
//        Log.e("TAG","onUIRefreshBegin ... ");
        Log.e("TAG","onUIRefreshBegin...top####"+mHeaderView.getTop() + "... scrollY####"+mHeaderView.getScrollY() + " ... translationY" + mHeaderView.getTranslationY());


        tvName.setText("onUIRefreshBegin。。。。。");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        Log.e("TAG","onUIRefreshComplete ... ");
//        Log.e("TAG","onUIRefreshComplete ... ");

        tvName.setText("onUIRefreshComplete。。。。。");

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//        Log.e("TAG","onUIPositionChange ... ");
//        Log.e("TAG","onUIPositionChange ... ");

        tvName.setText("onUIPositionChange。。。。。");
        Log.e("TAG","onUIPositionChange...top####"+mHeaderView.getTop() + "... scrollY####"+mHeaderView.getScrollY() + " ... translationY" + mHeaderView.getTranslationY());

    }
}
