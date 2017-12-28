package com.hq.uitest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by heqiang on 17/6/6.
 */

public class StringAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mData;
    public boolean first = true;
    public StringAdapter(List<String> data){
        mData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_string,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder1, final int position) {

        if(holder1 instanceof ItemViewHolder){
            ItemViewHolder holder = (ItemViewHolder) holder1;
            holder.tv_content.setText(mData.get(position));
            if(mListener!=null){
                holder.tv_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(mData.get(position),holder1);
                    }
                });


                holder.tv_content.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mListener.onItemTouch(holder1);
                        return false;
                    }
                });
            }
//            if(first) {
//                if (position > 5) {
//                    holder.tv_content.setVisibility(View.GONE);
//                } else {
//                    holder.tv_content.setVisibility(View.VISIBLE);
//                }
//            }else{
//                if (position == 0 || position > 5) {
//                    holder.tv_content.setVisibility(View.GONE);
//                } else {
//                    holder.tv_content.setVisibility(View.VISIBLE);
//                }
//            }


        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener{
        void onItemClick(String str, RecyclerView.ViewHolder holder);
        void onItemTouch(RecyclerView.ViewHolder holder);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_content;
//        View view_null;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_rv_str);
//            view_null = itemView.findViewById(R.id.view_null);
        }
    }
}
