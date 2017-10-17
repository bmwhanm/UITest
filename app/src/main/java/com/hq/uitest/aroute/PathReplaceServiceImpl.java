package com.hq.uitest.aroute;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

/**
 * Created by heqiang on 17/9/19.
 */
@Route(path = "/aroute/service")
public class PathReplaceServiceImpl implements PathReplaceService {
    @Override
    public String forString(String path) {
        Log.e("TAG","PathReplaceServiceImpl forString(String path):"+path);
        if(path.equals("/activity/testservice")){
            return "/activity/withparams";
        }
        return path;
    }

    @Override
    public Uri forUri(Uri uri) {
        Log.e("TAG","PathReplaceServiceImpl forUri(Uri uri)"+uri.toString());
        return uri;
    }

    @Override
    public void init(Context context) {
        Log.e("TAG","PathReplaceServiceImpl init()");
    }
}
