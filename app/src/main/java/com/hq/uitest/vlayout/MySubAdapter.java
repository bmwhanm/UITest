package com.hq.uitest.vlayout;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.hq.uitest.R;

/**
 * Created by heqiang on 17/9/19.
 */

public class MySubAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private LayoutHelper mLayoutHelper;
    private Context mContext;

    public MySubAdapter(Context context, LayoutHelper layoutHelper){
        mContext = context;
        mLayoutHelper = layoutHelper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_string, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder h = (ItemViewHolder) holder;
            h.itemView.setBackgroundColor(Color.RED);
            h.tv_content.setText("第" + position + "项");
        }
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_rv_str);
        }
    }
}
