package com.hq.uitest.recyclerviewt;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hq.uitest.R;
import java.util.ArrayList;
import java.util.List;


public class MyStaggerAdapter extends RecyclerView.Adapter<MyStaggerAdapter.ItemViewHolder> {

    private List<String> mData;
    private List<Integer> mHeights;
    private static int BIND_COUNT = 1;

    public MyStaggerAdapter(List<String> data){
        mData = data;
        mHeights = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            mHeights.add(100 +(int) (Math.random() * 200));
        }
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_stagger,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.tv.getLayoutParams();
        params.height = mHeights.get(position);
        holder.tv.setLayoutParams(params);
        holder.tv.setBackgroundColor(Color.rgb((int)(Math.random() * 255),(int)(Math.random() * 255) ,(int)(Math.random() * 255)));
        holder.tv.setText(mData.get(position));
        Log.e("TAG","count -- > "+ BIND_COUNT ++);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void addItem(int position){
        mData.add(position,"addItem" + position);
        mHeights.add(position,100 +(int) (Math.random() * 200));
//        notifyItemInserted(position);
        notifyItemRangeInserted(position,1);
//        notifyDataSetChanged();
    }

    public void removeItem(int position){
        mData.remove(position);
        mHeights.remove(mHeights.get(position));
//        notifyItemRemoved(position);
//        notifyDataSetChanged();
        notifyItemRangeRemoved(position,1);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_rv_str);
        }
    }
}
