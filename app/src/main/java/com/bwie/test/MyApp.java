package com.bwie.test;

import android.app.Application;
import android.content.Context;

import com.bwie.test.API.API;
import com.mob.MobSDK;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * User: 张亚博
 * Date: 2017-08-30 09:03
 * Description：
 */
public class MyApp extends Application {
    /**
     * 配置平台信息
     */
    {
        //QQ平台信息配置
        PlatformConfig.setQQZone("100424468","c7394704798a158208a74ab60104f0ba");
    }
    public static Context Mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        Mcontext=this;
        //初始化imageloader
        initImageloader();
        //初始化xutils
        initXutils();
        //mob
        MobSDK.init(this, API.KEY,API.SECRET );
        //初始化sdk
        UMShareAPI.get(this);
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG=true;

        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }

    /**
     * 初始化Xutils
     */
    private void initXutils() {
        x.Ext.init(this);
    }
    /**
     * 初始化Imageloader
     */
    private void initImageloader() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

}
