package com.hq.uitest.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hq.uitest.R;

/**
 * @author heqiang
 */
public abstract class BaseTitleActivity extends BaseActivity {
    protected ImageView iv_back;
    protected TextView tv_title;

    @Override
    protected void initView() {
        iv_back = findViewId(R.id.iv_base_title_back);
        tv_title = findViewId(R.id.tv_base_title);
    }


    protected void setBaseTitle(String str) {
        if (tv_title == null || str == null) {
            return;
        }
        tv_title.setText(str);
    }

    protected void setBaseTitle(int resId) {
        if (tv_title == null) {
            return;
        }
        tv_title.setText(getResources().getString(resId));
    }

    @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_base_title_back:
                finish();
                break;
            default:
                break;
        }

    }
}
