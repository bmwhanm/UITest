package com.hq.uitest.recyclerviewt.itemtouch;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by heqiang on 17/6/9.
 */

public class MyItemTouchHelper extends ItemTouchHelper.Callback {



    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int up = ItemTouchHelper.UP;
        int down = ItemTouchHelper.DOWN;
        int left = ItemTouchHelper.LEFT;
        int right = ItemTouchHelper.RIGHT;
        int flags = makeMovementFlags(up | down, left |right);
        return flags;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(listener != null)listener.onMove(recyclerView,viewHolder,target);

        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(listener != null)listener.onSwiped(viewHolder,direction);
    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Log.e("TAG","onChildDraw()    floatX --:"+dX +"  floatY : "+dY);

        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            float width = viewHolder.itemView.getWidth();
            if(Math.abs(dX) >= width / 2)
                dX = width / 2 * (dX > 0 ? 1 : -1);
//            viewHolder.itemView.setScaleX(1 - Math.abs(dX) / width);
//            viewHolder.itemView.setScaleY(1 - Math.abs(dX) / width);
            viewHolder.itemView.setAlpha(1 - Math.abs(dX) / width);
            viewHolder.itemView.setTranslationX(dX);

        }

    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setScaleX(1);
        viewHolder.itemView.setScaleY(1);
        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setTranslationX(0);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        Log.e("TAG"," onChildDrawOver()   floatX --:"+dX +"  floatY : "+dY);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    private ItemMoveListener listener;

    public void setListener(ItemMoveListener listener) {
        this.listener = listener;
    }

    public interface ItemMoveListener{
        void onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target);
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
    }
}
