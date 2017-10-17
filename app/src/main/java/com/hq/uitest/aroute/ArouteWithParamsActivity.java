package com.hq.uitest.aroute;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hq.uitest.R;
@Route(path = "/activity/withparams")
public class ArouteWithParamsActivity extends AppCompatActivity {
//    @Autowired(name = "bool")
    public boolean bool;

//    @Autowired(name = "str1")
    public String str1;


    public static void launch(Context context){
        Bundle bundle = new Bundle();
        Test t = new Test();
        t.setAge(10);
        t.setName("haha");
        t.setSex("男");
        bundle.putParcelable("test",t);
        ARouter.getInstance().build("/activity/withparams")
                .withBoolean("bool",true)
                .withString("str1","this is a test")
                .with(bundle)
                .navigation(context.getApplicationContext(), new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG", "ArouteWithParamsActivity onFound:" + postcard.toString());
                        }
                        else {
                            Log.e("TAG","ArouteWithParamsActivity onFound...");
                        }
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG","ArouteWithParamsActivity onLost:"+postcard.toString());
                        }else{
                            Log.e("TAG","ArouteWithParamsActivity onLost:");
                        }
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG","ArouteWithParamsActivity onArrival:"+postcard.toString());
                        }else{
                            Log.e("TAG","ArouteWithParamsActivity onArrival:");
                        }
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        if(postcard != null) {
                            Log.e("TAG","ArouteWithParamsActivity onInterrupt:"+postcard.toString());
                        }else{
                            Log.e("TAG","ArouteWithParamsActivity onInterrupt:");
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_aroute_with_params);
        Log.e("TAG","bool  value"+bool);

        Bundle bundle = getIntent().getExtras();
        String uri  = getIntent().getStringExtra(ARouter.RAW_URI);
        if(uri != null){
            Log.e("TAG","uri:"+uri.toString());
        }else{
            Log.e("TAG","uri is  null");
        }
        Test t1 = (Test) bundle.get("test");
        if(t1 != null) {
            Log.e("TAG", "name:" + t1.getName() + "  age:" + t1.getAge() + "  sex:" + t1.getSex());
        }

        Log.e("TAG","str1:"+str1);
    }
}
