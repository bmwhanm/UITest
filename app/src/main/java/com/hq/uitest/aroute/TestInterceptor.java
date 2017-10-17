package com.hq.uitest.aroute;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Created by heqiang on 17/9/18.
 */
@Interceptor(priority = 2,name = "测试拦截器")
public class TestInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e("TAG","TestInterceptor:process");
        postcard.getExtras();
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        Log.e("TAG","TestInterceptor : 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次");

    }
}
