package com.hq.uitest.aroute;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;


@Interceptor(priority = 1,name = "第二个拦截器")
public class SecondInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e("TAG","SecondInterceptor:2");
        if(postcard.getPath().equals("/activity/withparams")){
//            callback.onInterrupt(new Exception("this is a Interceptor Test"));
//            postcard.setPath("/activity/third");
            postcard.setDestination(ArouteThirdActivity.class);
            callback.onContinue(postcard);
        }
        else{
            callback.onContinue(postcard);
        }
//        callback.onContinue(postcard);

    }

    @Override
    public void init(Context context) {
        Log.e("TAG","SecondInterceptor : 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次");

    }
}
