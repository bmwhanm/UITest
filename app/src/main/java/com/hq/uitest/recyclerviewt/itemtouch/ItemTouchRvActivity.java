package com.hq.uitest.recyclerviewt.itemtouch;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.hq.uitest.R;
import com.hq.uitest.StringAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.security.auth.login.LoginException;

public class ItemTouchRvActivity extends AppCompatActivity {

    private RecyclerView rv;
    private StringAdapter adapter;
    private List<String> mData;
    private ItemTouchHelper item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_rv);
        mData = new ArrayList<>();
        adapter = new StringAdapter(mData);

        rv = (RecyclerView) findViewById(R.id.rv_item_touch);


        //调用系统的SimpleCallBack的例子
        simpleExample();

        //调用自己写的类的ItemTouchHelpCallBack 的子类
//        myItemTouch();




        rv.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 100; i++) {
            mData.add("第" + i + "项");
        }


        rv.setAdapter(adapter);

        adapter.setListener(new StringAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String str, RecyclerView.ViewHolder holder) {
//                item.startDrag(holder);
            }

            @Override
            public void onItemTouch(RecyclerView.ViewHolder holder) {
                item.startDrag(holder);
//                holder.itemView.setElevation(100);
//                holder.itemView.invalidate();
//                item.startSwipe(holder);
            }
        });
    }

    private void myItemTouch() {
        MyItemTouchHelper itemTouchHelperCallBack = new MyItemTouchHelper();

        itemTouchHelperCallBack.setListener(new MyItemTouchHelper.ItemMoveListener() {
            @Override
            public void onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int targetPosition = target.getAdapterPosition();
                Log.e("TAG", "from : " + fromPosition + "   to:" + targetPosition);

                Collections.swap(mData, fromPosition, targetPosition);
                adapter.notifyItemMoved(fromPosition, targetPosition);

//                viewHolder.itemView.setElevation(0);
//                viewHolder.itemView.invalidate();
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Log.e("TAG", "swipe Position : " + position);
                mData.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        ItemTouchHelper item = new ItemTouchHelper(itemTouchHelperCallBack);
        item.attachToRecyclerView(rv);
    }

    private void simpleExample() {
        item = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int targetPosition = target.getAdapterPosition();
                Log.e("TAG", "from : " + fromPosition + "   to:" + targetPosition);

                Collections.swap(mData, fromPosition, targetPosition);
                adapter.notifyItemMoved(fromPosition, targetPosition);

//                viewHolder.itemView.setElevation(0);
//                viewHolder.itemView.invalidate();

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Log.e("TAG", "swipe Position : " + position);
                mData.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();
//                return false;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return super.isItemViewSwipeEnabled();
//                return false;
            }

            @Override
            public int getBoundingBoxMargin() {
//                return super.getBoundingBoxMargin();
                return 0;
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
                    viewHolder.itemView.setBackgroundColor(Color.YELLOW);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }
        });
        item.attachToRecyclerView(rv);
    }
}
