package com.bwie.test;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * User: 张亚博
 * Date: 2017-08-30 09:03
 * Description：
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        initImageloader();
        initXutils();
    }

    private void initXutils() {
        x.Ext.init(this);
    }

    private void initImageloader() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
