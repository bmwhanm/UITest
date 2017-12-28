package com.hq.uitest.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author heqiang
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHold(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        showViewHolder(holder,position);
    }

     protected abstract BaseViewHolder createViewHold(ViewGroup parent,int viewType);
     protected abstract void showViewHolder(BaseViewHolder holder,int position);

    protected <T extends View> T findViewId(View view,int resId){
        return (T) view.findViewById(resId);
    }
}
