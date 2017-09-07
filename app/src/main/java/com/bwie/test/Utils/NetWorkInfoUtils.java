package com.bwie.test.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * User: 张亚博
 * Date: 2017-09-04 18:52
 * Description：
 */
public class NetWorkInfoUtils {
    private Context Mcontext;

    /**
     * 网络判断方法
     * @param context 上下文
     * @param network 网络回调接口
     */
    public void NetworkJudgment(Context context,NetWork network){
        this.Mcontext=context;
        //获取网络连接管理器
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络连接可用对象信息
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        //判断可用对象是否为空，如果不为空，则说明有网
        if (networkinfo!=null) {
            //判断网络连接对象类型为WiFi，或者数据网...2G,3G,4G
            if (networkinfo.getType()==ConnectivityManager.TYPE_MOBILE) {
                //如果为数据网，则不让加载图片
                network.NetMobileListener();

            } else if (networkinfo.getType()==ConnectivityManager.TYPE_WIFI) {
                //如果为WiFi，则加载大图
                network.NetWiFiListener();
            }else{
                network.NetNoListener();
            }
        }else {
            network.NetNoListener();
        }

    }

    /**
     * 网络判断回调接口
     */
    public interface NetWork{
        //有WiFi网络时 执行的操作
        void NetWiFiListener();
        //有数据网络时 执行的操作
        void NetMobileListener();

        //无网时，执行的操作
        void NetNoListener();

}
}

