package com.hq.uitest.recyclerviewt.headandfootrv;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by heqiang on 17/6/9.
 */

public class HeadRecyclerAdapter extends RecyclerView.Adapter {
    private List<View> mHeaderViews;
    private List<View> mFooterViews;
    private RecyclerView.Adapter adapter;
    private final int HEADER = 0x001;
    private final int FOOTER = 0x010;

    public HeadRecyclerAdapter(List<View> header,List<View> footer,Adapter adapter){
        mHeaderViews = header;
        mFooterViews = footer;
        this.adapter = adapter;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case HEADER:
               return  new HeaderFootViewHolder(mHeaderViews.get(0));
            case FOOTER:
                return  new HeaderFootViewHolder(mFooterViews.get(0));
            default:
                return adapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position >= mHeaderViews.size() && position < (mHeaderViews.size() + adapter.getItemCount())){
            adapter.onBindViewHolder(holder,position - mHeaderViews.size() );
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mFooterViews.size() + adapter.getItemCount();
    }


    @Override
    public int getItemViewType(int position) {
        if(position < mHeaderViews.size())return HEADER;
        if(position >= mHeaderViews.size() && position < (mHeaderViews.size() + adapter.getItemCount())){
            return adapter.getItemViewType(position - mHeaderViews.size());
        }

        return FOOTER;
    }


    class HeaderFootViewHolder extends RecyclerView.ViewHolder{

        public HeaderFootViewHolder(View itemView) {
            super(itemView);
        }
    }



}
