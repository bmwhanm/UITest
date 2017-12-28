package com.hq.uitest.refreshcontrol;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.NestedScrollingParent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.hq.uitest.StringAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heqiang on 17/8/1.
 */

public class MyLinearLayout extends LinearLayout  {
    private RecyclerView rv;
    private TextView tv;
    private StringAdapter adapter;
    private LinearLayoutManager manager;
    private List<String> mData;
    private Scroller mScroller;
    private int mTouchSlop;
    private int lastY;
    private final int REFRESHING = 0x001;
    private final int FINISHED = 0x002;
    private int state = FINISHED;
    private final int MAXHEIGHT = dipToPx(getContext(),60);
    private MotionEvent lastEvent;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESHING:
                    state = FINISHED;
                    mScroller.startScroll(0,getScrollY(),0,-getScrollY(),200);
                    invalidate();
                break;
                default:
                    break;
            }
        }
    };
    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        mScroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        tv = (TextView) getChildAt(0);
        rv = (RecyclerView) getChildAt(1);
        manager  = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("item"+i);
        }
        adapter = new StringAdapter(mData);
        rv.setAdapter(adapter);

//        GridLayoutManager manager1 = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(lastEvent == null){
            lastEvent = ev;
        }
        if(manager.findFirstCompletelyVisibleItemPosition() == 0 && ev.getAction() == MotionEvent.ACTION_MOVE){
            Log.e("TAG","dispatchTouchEvent firstCompleteVisibleItemPosition == 0");
//            ev.setAction(MotionEvent.ACTION_DOWN);
            int dy = (int) (ev.getY() - lastEvent.getY());
            Log.e("TAG","dy ...."+dy);
            if(dy > 0){
                requestDisallowInterceptTouchEvent(false);
                Log.e("TAG","dispatchTouchEvent false");
            }else{
//                lastEvent = ev;
                requestDisallowInterceptTouchEvent(true);
                Log.e("TAG","dispatchTouchEvent true");
//                lastEvent =  MotionEvent.obtain(ev);
//                return rv.dispatchTouchEvent(ev);
//                return  rv.dispatchTouchEvent(ev);
            }

        }else{
            requestDisallowInterceptTouchEvent(true);
//            lastEvent =  MotionEvent.obtain(ev);
            Log.e("TAG","dispatchTouchEvent true");
//            return rv.dispatchTouchEvent(ev);
        }
        lastEvent =  MotionEvent.obtain(ev);
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void scrollTo(int x, int y) {
        if(getScrollY() < -MAXHEIGHT)y = -MAXHEIGHT;

        if(-y > MAXHEIGHT *2/ 3 )state = REFRESHING;
        else state = FINISHED;
        if(listener!=null){
            if(y ==0 )listener.onFinish();
            else listener.onScroll(y);
        }

        super.scrollTo(x, y);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
            return true;
        }
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int curY = (int) ev.getY();
                int dy = curY - lastY;
                if(dy > 0)intercept = true;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastY = (int) ev.getY();
        Log.e("TAG","onIntercept");
        if(manager.findFirstCompletelyVisibleItemPosition() == 0){
            Log.e("TAG","onInterceptTouchEvent    firstCompleteVisibleItemPosition == 0");
        }
        if(manager.findFirstCompletelyVisibleItemPosition() == 0 && intercept){
            Log.e("TAG","onIntercept return true");
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action  = event.getAction();
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
            return false;
        }
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(getScrollY() == -MAXHEIGHT)scrollBy(0,0);
                else scrollBy(0,-((int)(event.getY() - lastY)));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(state == REFRESHING) {
                    mScroller.startScroll(0, getScrollY(), 0, -MAXHEIGHT -getScrollY(), 100);
                    mHandler.sendEmptyMessageDelayed(REFRESHING,1000);
                }else{
                    mScroller.startScroll(0,getScrollY(),0,-getScrollY(),100);
                }
                invalidate();
                break;
        }
        lastY = (int) event.getY();
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
    private myOnScrollListener listener;

    public void setListener(myOnScrollListener listener) {
        this.listener = listener;
    }

    public interface myOnScrollListener{
        void onScroll(int dY);
        void onFinish();
    }

    private int dipToPx(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
