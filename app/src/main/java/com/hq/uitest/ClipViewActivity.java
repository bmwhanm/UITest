package com.hq.uitest;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hq.uitest.base.BaseActivity;

public class ClipViewActivity extends BaseActivity{
    private LinearLayout lay_clip;
    private Button btn_start,btn_content;
    private ImageView iv_show;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_clip_view;
    }

    @Override
    protected void initView() {
        lay_clip = findViewId(R.id.lay_clip_content);
        btn_start = findViewId(R.id.btn_clip_start);
        iv_show = findViewId(R.id.iv_clip_show);
        btn_content = findViewId(R.id.btn_clip_content);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btn_start.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void clickErrorRefresh() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_clip_start:
                btn_content.setText("test Clip");
                lay_clip.setDrawingCacheEnabled(true);
                lay_clip.buildDrawingCache();
                Bitmap viewBitmap = lay_clip.getDrawingCache();

                // 截取当前屏幕，去掉标题栏
                Bitmap bitmap = Bitmap.createBitmap(viewBitmap, 0, lay_clip.getTop(), lay_clip.getWidth(), lay_clip.getHeight() - 200);

                lay_clip.destroyDrawingCache();
                lay_clip.setDrawingCacheEnabled(false);
                btn_content.setText("test Clip View");
                iv_show.setImageBitmap(bitmap);
                break;
        }
    }
}
