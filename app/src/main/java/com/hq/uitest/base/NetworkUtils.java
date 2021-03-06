package com.hq.uitest.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
public class NetworkUtils {

    public final static String NETWORK_CMNET = "CMNET";
    public final static String NETWORK_CMWAP = "CMWAP";
    public final static String NETWORK_WIFI = "WIFI";
    public final static String TAG = "NetworkUtils";
    private static NetworkInfo networkInfo = null;
    private Context mContext = null;

    private NetworkUtils() {
    }

    public static NetworkUtils getInstance() {
        return SingletonHolder.instance;
    }

    public NetworkUtils init(Context context){
        this.mContext = context;
        return this;
    }

    /**
     * 判断网络是否可用
     *
     * @return 是/否
     */
    public boolean isAvailable() {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (null == manager) {
            return false;
        }
        networkInfo = manager.getActiveNetworkInfo();
        if (null == networkInfo || !networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    /**
     * 判断网络是否已连接
     *
     * @return 是/否
     */
    public boolean isConnected() {
        if (!isAvailable()) {
            return false;
        }
        if (!networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    /**
     * 检查当前环境网络是否可用，不可用跳转至开启网络界面,不设置网络强制关闭当前Activity
     */
    public void validateNetWork() {

        if (!isConnected()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            dialogBuilder.setTitle("网络设置");
            dialogBuilder.setMessage("网络不可用，是否现在设置网络？");
            dialogBuilder.setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ((Activity) mContext).startActivityForResult(
                                    new Intent(
                                            Settings.ACTION_SETTINGS),
                                    which);
                        }
                    });
            dialogBuilder.setNegativeButton(android.R.string.cancel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialogBuilder.create();
            dialogBuilder.show();
        }
    }

    /**
     * 获取网络连接信息</br> 无网络：</br> WIFI网络：WIFI</br> WAP网络：CMWAP</br>
     * NET网络：CMNET</br>
     *
     * @return
     */
    public String getNetworkType() {
        if (isConnected()) {
            int type = networkInfo.getType();
            if (ConnectivityManager.TYPE_MOBILE == type) {
                Log.i(TAG,
                        "networkInfo.getExtraInfo()-->"
                                + networkInfo.getExtraInfo());
                if (NETWORK_CMWAP.equals(networkInfo.getExtraInfo()
                        .toLowerCase())) {
                    return NETWORK_CMWAP;
                } else {
                    return NETWORK_CMNET;
                }
            } else if (ConnectivityManager.TYPE_WIFI == type) {
                return NETWORK_WIFI;
            }
        }

        return "";
    }

    private static class SingletonHolder {
        private static NetworkUtils instance = new NetworkUtils();
    }
}
