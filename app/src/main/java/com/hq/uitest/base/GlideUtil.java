package com.hq.uitest.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideUtil {
    public static DrawableRequestBuilder display(Context context, String url) {
        return Glide
                .with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static DrawableRequestBuilder display(Fragment fragment, String url) {
        return Glide
                .with(fragment)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static DrawableRequestBuilder display(FragmentActivity activity, String url) {
        return Glide
                .with(activity)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static DrawableRequestBuilder display(Activity activity, String url) {
        return Glide
                .with(activity)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }


}
