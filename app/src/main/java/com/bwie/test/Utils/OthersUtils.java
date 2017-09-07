package com.bwie.test.Utils;

import java.util.List;

/**
 * User: 张亚博
 * Date: 2017-09-07 10:00
 * Description：
 */
public class OthersUtils {
    private List<String> list;
    public OthersUtils(List<String> list) {
        this.list=list;
    }

    public static String ReturnType(String s){
        String type=null;


            if (s.equals("头条")) {
                type="top";
            } else if (s.equals("社会")) {
                type="shehui";

            } else if (s.equals("国内")) {
                type="guonei";

            } else if (s.equals("娱乐")) {
                type="yule";

            } else if (s.equals("国际")) {
                type="guoji";

            } else if (s.equals("体育")) {
                type="tiyu";

            } else if (s.equals("军事")) {
                type="junshi";

            } else if (s.equals("科技")) {
                type="keji";

            } else if (s.equals("财经")) {
                type="caijing";

            } else if (s.equals("时尚")) {
                type="shishang";

            }
        return type;
    }
}
