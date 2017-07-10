package com.lanshifu.myapp2017.view.popumenu;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by HanHailong on 16/2/17.
 */
public class PoPItem {

    private String title;
    private Drawable drawable;

    public PoPItem(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public PoPItem(Context context, String title, int resoucreId) {
        this.title = title;
        this.drawable = context.getResources().getDrawable(resoucreId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
