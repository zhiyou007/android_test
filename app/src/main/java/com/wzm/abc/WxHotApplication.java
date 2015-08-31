package com.wzm.abc;

import android.app.Application;

import com.wzm.abc.utils.VolleyHelper;

/**
 * Created by zhiyou007 on 2015/8/31.
 */
public class WxHotApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance().init(this);
    }
}
