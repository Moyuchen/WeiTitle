package com.bwie.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bwie.test.Adapter.recycleAdapter;
import com.bwie.test.Bean.NewsType;
import com.bwie.test.Utils.NetWorkInfoUtils;
import com.bwie.test.Utils.SQLiteUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class LiXianXiaZaiActivity extends AppCompatActivity {

    private RecyclerView recycle;
    private Button but_download;
    private List<NewsType> list;
    private SQLiteUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_xian_xia_zai);
        initView();
        initData();

    }

    private void initData() {
        list = new ArrayList<>();

        NewsType newstype1=new NewsType();
        newstype1.newstype="头条";
        list.add(newstype1);
        NewsType newstype2=new NewsType();
        newstype2.newstype="社会";
        list.add(newstype2);
        NewsType newstype3=new NewsType();
        newstype3.newstype="国内";
        list.add(newstype3);
        NewsType newstype4=new NewsType();
        newstype4.newstype="国际";
        list.add(newstype4);

        NewsType newstype6=new NewsType();
        newstype6.newstype="娱乐";
        list.add(newstype6);
        NewsType newstype7=new NewsType();
        newstype7.newstype="体育";
        list.add(newstype7);
        NewsType newstype8=new NewsType();
        newstype8.newstype="军事";
        list.add(newstype8);
        NewsType newstype9=new NewsType();
        newstype9.newstype="科技";
        list.add(newstype9);
        NewsType newstype10=new NewsType();
        newstype10.newstype="财经";
        list.add(newstype10);
        NewsType newstype11=new NewsType();
        newstype11.newstype="时尚";
        list.add(newstype11);

        recycleAdapter ad=new recycleAdapter(this,list);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(ad);
        ad.setOnRecycleViewItemClickListener(new recycleAdapter.OnRecycleViewItemClickedListener() {
            @Override
            public void onRecycleViewItem(int positon, View view) {
                CheckBox recy_checkb = view.findViewById(R.id.recy_checkb);
                NewsType newsType = list.get(positon);
                if (recy_checkb.isChecked()) {
                    recy_checkb.setChecked(false);
                    newsType.IsSelected=false;
                }else{
                    recy_checkb.setChecked(true);
                    newsType.IsSelected=true;
                }
            list.set(positon,newsType);
            }
        });


    }

    private void initView() {
        utils = new SQLiteUtils(LiXianXiaZaiActivity.this);
        recycle = (RecyclerView) findViewById(R.id.recyclelistview);
        but_download = (Button) findViewById(R.id.but_download);
        but_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <list.size() ; i++) {
                    NewsType newsType = list.get(i);
                    if (newsType.IsSelected) {
                        //加载网络数据
                        RequestData(newsType.newstype);
                    }

                }
            }
        });


    }

    /**
     * 请求数据前的网络判断
     */
    private void RequestData(final String type) {
        //判断网络状态，如果目前网络为WiFi，则执行下载操作，否则不执行
        new NetWorkInfoUtils().NetworkJudgment(LiXianXiaZaiActivity.this, new NetWorkInfoUtils.NetWork() {
            @Override
            public void NetWiFiListener() {
                RequestedData(type);
            }

            @Override
            public void NetMobileListener() {
            RequestedData(type);
            }

            @Override
            public void NetNoListener() {

            }
        });






    }

    /**
     * 请求数据
     * @param type
     */
    private void RequestedData(final String type) {
        String url="http://v.juhe.cn/toutiao/index?type="+type+"&key=dddd304ffb60fd2987da4f6e939b0c2e";

        RequestParams params=new RequestParams(url);
        params.addQueryStringParameter("username","abc");
        params.addQueryStringParameter("password","123");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {

                utils.add(type,result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LiXianXiaZaiActivity.this,result,Toast.LENGTH_LONG).show();
                    }
                });

                //解析数据
//                parseData(type);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

//    private void parseData(String type) {
//        Cursor select = utils.Select(type);
//        while (select.moveToNext()) {
//            String content = select.getString(select.getColumnIndex("content"));
//            Toast.makeText(LiXianXiaZaiActivity.this,content,Toast.LENGTH_LONG).show();
//
//            System.out.print("content"+content);
//        }
//    }
}
