package com.hq.uitest.ultrapull;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hq.uitest.R;
import com.hq.uitest.drawable.DrawableBase;
import com.hq.uitest.drawable.DrawableBaseVertical;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by heqiang on 17/12/28.
 */

public class QtsHeader   extends FrameLayout implements PtrUIHandler {
    private View mHeaderView;
    private Context mContext;
    private ImageView ivQts;
    private BitmapDrawable selectedDrawable;
    private BitmapDrawable unSelectedDrawable;
    private DrawableBaseVertical baseDrawable;
    private int level = 0;

    public QtsHeader(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }

    public QtsHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public QtsHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mHeaderView = LayoutInflater.from(mContext).inflate(R.layout.header_refresh_qts,this,true);
        ivQts = mHeaderView.findViewById(R.id.iv_refresh_qts);
        selectedDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.avft_active);
        unSelectedDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.avft);
        baseDrawable = new DrawableBaseVertical(selectedDrawable,unSelectedDrawable);
        ivQts.setBackground(baseDrawable);
        ivQts.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ivQts.setImageLevel(level);
        baseDrawable.setLevel(level);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
//        Log.e("TAG","onUIReset ... ");
        Log.e("TAG","onUIReset ... ");
        level = 0;
        ivQts.setImageLevel(level);
        baseDrawable.setLevel(level);

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
//        Log.e("TAG","onUIRefreshPrepare ... ");
        Log.e("TAG","onUIRefreshPrepare ... ");
        level = 0;
        ivQts.setImageLevel(level);
        baseDrawable.setLevel(level);
//        Log.e("TAG","onUIRefreshPrepare ...top####"+mHeaderView.getTop() + "... scrollY####"+mHeaderView.getScrollY() + " ... translationY" + mHeaderView.getTranslationY());

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

//        Log.e("TAG","onUIRefreshBegin ... ");
        Log.e("TAG","onUIRefreshBegin ... ");
        level = 10000;
        ivQts.setImageLevel(level);
        baseDrawable.setLevel(level);
//        Log.e("TAG","onUIRefreshBegin...top####"+mHeaderView.getTop() + "... scrollY####"+mHeaderView.getScrollY() + " ... translationY" + mHeaderView.getTranslationY());


    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        Log.e("TAG","onUIRefreshComplete ... ");
        Log.e("TAG","onUIRefreshComplete ... ");
        level = 10000;
        ivQts.setImageLevel(level);
        baseDrawable.setLevel(level);


    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//        Log.e("TAG","onUIPositionChange ... ");
//        Log.e("TAG","onUIPositionChange ... ");


//        Log.e("TAG","release Enter  ####"+(mHeaderView.getTop() > (-mHeaderView.getHeight() * 3/ 4.0)));
        if(baseDrawable.getLevel() != 10000) {
            if (mHeaderView.getTop() > (-mHeaderView.getHeight() * 3 / 4.0)) {
//                baseDrawable.setState(new int[]{android.R.attr.state_pressed});
                level = (int) ((mHeaderView.getTop() + mHeaderView.getHeight() * 3/ 4.0) * 10000.0 / ( mHeaderView.getHeight()));
                ivQts.setImageLevel(level);
                baseDrawable.setLevel(level);

            }
        }

//        Log.e("TAG","onUIPositionChange...top####"+mHeaderView.getTop() + "... height####"+mHeaderView.getHeight());

    }
}