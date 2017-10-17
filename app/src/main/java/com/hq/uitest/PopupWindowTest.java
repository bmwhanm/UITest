package com.hq.uitest;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by heqiang on 17/9/4.
 */

public class PopupWindowTest extends PopupWindow implements View.OnClickListener{

    private View mContentView;
    private Context mContext;
    private Button btn_take,btn_get,btn_cancel;
    private View view_dis;

    public PopupWindowTest(Context context,ViewGroup view) {
        mContext = context;
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.pop_show,null);
        setContentView(mContentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        btn_take = (Button) mContentView.findViewById(R.id.btn_take);
        btn_get = (Button) mContentView.findViewById(R.id.btn_get);
        btn_cancel = (Button) mContentView.findViewById(R.id.btn_cancel);
        view_dis = mContentView.findViewById(R.id.view_dis);

        btn_cancel.setOnClickListener(this);
        btn_take.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        view_dis.setOnClickListener(this);



//        setFocusable(true);
//        setTouchable(true);
//        setBackgroundDrawable(new BitmapDrawable());
//        setOutsideTouchable(true);

    }

    public PopupWindowTest(View contentView) {
        super(contentView);
    }

    public PopupWindowTest(int width, int height) {
        super(width, height);
    }

    public PopupWindowTest(View contentView, int width, int height) {
        super(contentView, width, height);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_take:
                Toast.makeText(mContext,"拍照",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_get:
                Toast.makeText(mContext,"相册获取",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.view_dis:
                dismiss();
                break;
        }

    }
}
