package com.app.cattlemanagement.utils;

import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;

import com.app.cattlemanagement.R;

import java.util.Objects;

public class FeedStockDialogue {
    public static void setDefaultDialogProperties(Dialog dg) {
        dg.setCanceledOnTouchOutside(true);
        dg.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dg.getWindow();
        lp.copyFrom(Objects.requireNonNull(window).getAttributes());

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.setAttributes(lp);
        Objects.requireNonNull(dg.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
    }

    public static void initLoadingDialog(Dialog loadingDialog) {
        loadingDialog.setContentView(R.layout.feed_updatr_layout);
        loadingDialog.setCanceledOnTouchOutside(false);
        setDefaultDialogProperties(loadingDialog);
    }
}
