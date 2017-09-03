package com.bwie.test;

import android.app.Application;
import android.content.Context;

import com.bwie.test.API.API;
import com.mob.MobSDK;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.xutils.x;

/**
 * User: 张亚博
 * Date: 2017-08-30 09:03
 * Description：
 */
public class MyApp extends Application {
    public static Context Mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        Mcontext=this;
        initImageloader();
        initXutils();
        //mob
        MobSDK.init(this, API.KEY,API.SECRET );
    }

    private void initXutils() {
        x.Ext.init(this);
    }

    private void initImageloader() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(10))
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
