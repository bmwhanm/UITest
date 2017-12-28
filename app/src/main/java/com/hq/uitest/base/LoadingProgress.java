package com.hq.uitest.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.hq.uitest.R;


/**
 * Created by heqiang on 17/10/24.
 */

public class LoadingProgress extends Dialog {

    /**
     * 消息TextView
     */
    private TextView tvMsg;

    public LoadingProgress(Context context, String strMessage) {
        this(context, R.style.CustomProgressDialog, strMessage);
    }

    public LoadingProgress(Context context, int theme, String strMessage) {
        super(context, theme);
        this.setContentView(R.layout.view_progress_dialog);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        tvMsg = (TextView) this.findViewById(R.id.tv_msg);
        setMessage(strMessage);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    /**
     * 设置进度条消息
     *
     * @param strMessage 消息文本
     */
    public void setMessage(String strMessage) {
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
    }
}
