package com.hq.uitest.drawable;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/6/19.
 */

public class DrawAdapter extends RecyclerView.Adapter<DrawAdapter.IvHolder> {
    private int[] mImgIds = new int[7];
    private int mImgIds_active[] = new int[7];
    private Context mContext;
    public DrawAdapter(int [] imgds, int [] imgac, Context context){
        mImgIds = imgds;
        mImgIds_active = imgac;
        mContext = context;
    }


    @Override
    public IvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_draw_base,parent,false);
        return new IvHolder(view);
    }

    @Override
    public void onBindViewHolder(IvHolder holder, int position) {
        BitmapDrawable selected = (BitmapDrawable) mContext.getResources().getDrawable(mImgIds[position]);
        BitmapDrawable unSelected = (BitmapDrawable) mContext.getResources().getDrawable(mImgIds_active[position]);
        final DrawableBase drawableBase = new DrawableBase(selected,unSelected);
        holder.iv.setImageDrawable(drawableBase);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawableBase.setLevel(drawableBase.getLevel() + 200);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mImgIds.length;
    }
    class IvHolder extends RecyclerView.ViewHolder{
        ImageView iv;

        public IvHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_rv_draw_base);
        }
    }
}
