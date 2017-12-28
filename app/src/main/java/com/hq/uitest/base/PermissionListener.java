package com.hq.uitest.base;

import java.util.List;

/**
 * Created by heqiang on 17/10/25.
 */

public interface PermissionListener {
    void onGranted();//已授权

    void onDenied(List<String> deniedPermission);//未授权
}
