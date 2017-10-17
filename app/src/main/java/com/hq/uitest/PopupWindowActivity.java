package com.hq.uitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PopupWindowActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout lay_container;
    private Button btn_show;
    private PopupWindowTest mPopupWindow;
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        lay_container = (LinearLayout) findViewById(R.id.lay_popup_test);

        btn_show = (Button) findViewById(R.id.btn_pop);



        btn_show.setOnClickListener(this);
        mPopupWindow = new PopupWindowTest(this,lay_container);
        mRootView = LayoutInflater.from(this).inflate(R.layout.activity_popup_window,null,false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pop:
//                mPopupWindow.showAsDropDown(mRootView,0,0);
                mPopupWindow.showAtLocation(lay_container,Gravity.BOTTOM,0,0);
                break;
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("TAG","按下了back键   onBackPressed()");
    }


    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.e("TAG","按下了back键   onKeyDown()");
            if(mPopupWindow.isShowing())
            return true;
            else return super.onKeyDown(keyCode, event);
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
