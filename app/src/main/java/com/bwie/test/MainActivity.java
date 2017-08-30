package com.bwie.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bwie.test.API.API;
import com.bwie.test.Adapter.listAdapter;
import com.bwie.test.Bean.News;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
@ViewInject(R.id.listview)
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        x.view().inject(this);

        RequestData();


    }

    /**
     * 请求数据
     */
    private void RequestData() {
        RequestParams params=new RequestParams(API.NEWS_UTL);
        params.addBodyParameter("username:","adb");
        params.addParameter("password","123");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                parseData(result);

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

    /**
     * 解析数据
     * @param result
     */
    private void parseData(String result) {
        Gson gson=new Gson();
        News news = gson.fromJson(result, News.class);
        List<News.ResultBean.DataBean> data = news.getResult().getData();
        //将数据映射到listView上
        listAdapter ada=new listAdapter(data,this);
        listview.setAdapter(ada);
    }
}
