package com.hq.uitest.vlayout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;

import java.util.List;

/**
 * Created by heqiang on 17/9/19.
 */

public class MyAdapter extends VirtualLayoutAdapter<RecyclerView.ViewHolder> {


    public MyAdapter(@NonNull VirtualLayoutManager layoutManager) {
        super(layoutManager);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_string, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder h = (ItemViewHolder) holder;
            h.tv_content.setText("第" + position + "项");
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_rv_str);
        }
    }
}
