package com.hq.uitest;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.picasso.Picasso;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

/**
 * Created by heqiang on 17/9/18.
 */

public class App extends Application {
    private final boolean isDebug = true;


    @Override
    public void onCreate() {
        super.onCreate();
        if(isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        Log.e("TAG","App is created");
        ARouter.init(this);


        TangramBuilder.init(this, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {
                Picasso.with(getApplicationContext()).load(url).into(view);
            }
        },ImageView.class);
    }
}
