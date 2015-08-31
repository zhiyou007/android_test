package com.wzm.abc;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzm.abc.api.ApiConstants;
import com.wzm.abc.utils.ImageLoaderHelper;
import com.wzm.abc.utils.VolleyHelper;

/**
 * Created by zhiyou007 on 2015/8/31.
 */
public class WxHotApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.getInstance().init(this);
        ImageLoader.getInstance().init(ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration(ApiConstants.Paths.IMAGE_LOADER_CACHE_PATH));
    }
}
