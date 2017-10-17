package com.hq.uitest.aroute;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.service.PathReplaceService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hq.uitest.R;

@Route(path = "/xxx/xxx")
public class TestServiceActivity extends AppCompatActivity {

    public static void launch(Context context){
        PathReplaceService service = new PathReplaceServiceImpl();
        ARouter.getInstance().build("/activity/testservice")
                .navigation(context.getApplicationContext(), new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG", "TestServiceActivity onFound:" + postcard.toString());
                        }
                        else {
                            Log.e("TAG","TestServiceActivity onFound...");
                        }
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG","TestServiceActivity onLost:"+postcard.toString());
                        }else{
                            Log.e("TAG","TestServiceActivity onLost:");
                        }
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG","TestServiceActivity onArrival:"+postcard.toString());
                        }else{
                            Log.e("TAG","TestServiceActivity onArrival:");
                        }
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG","TestServiceActivity onInterrupt:"+postcard.toString());
                        }else{
                            Log.e("TAG","TestServiceActivity onInterrupt:");
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
    }
}
