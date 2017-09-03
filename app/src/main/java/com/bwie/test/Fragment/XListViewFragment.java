package com.bwie.test.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.API.API;
import com.bwie.test.Adapter.listAdapter;
import com.bwie.test.Bean.News;
import com.bwie.test.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import imageloader.bwie.com.xlistview.XListView;

/**
 * User: 张亚博
 * Date: 2017-09-03 12:14
 * Description：
 */
public class XListViewFragment extends Fragment {

    private View view;
    private XListView xlistview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null) {
                view=inflater.inflate(R.layout.activity_main,container,false);
        }else{
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent!=null) {
                parent.removeView(view);
            }
        }
        initView();
        RequestData();

        return view;
    }

    private void initView() {
        xlistview = view.findViewById(R.id.xlistview);
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
        listAdapter ada=new listAdapter(data,getActivity());
        xlistview.setAdapter(ada);
    }
}
