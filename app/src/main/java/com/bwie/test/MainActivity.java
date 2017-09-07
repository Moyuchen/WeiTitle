package com.bwie.test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bwie.test.API.TITLE;
import com.bwie.test.Fragment.LeftFragment;
import com.bwie.test.Fragment.RightFragment;
import com.bwie.test.Fragment.XListViewFragment;
import com.bwie.test.HorizontalScrollView.HorizontalScrollTabhost;
import com.bwie.test.Utils.SQLiteUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMShareAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<ChannelBean> list;
    private String jsonStr;
    @ViewInject(R.id.hsm_container)
    HorizontalScrollTabhost hsm_contianer;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.iv_more)
    ImageView iv_more;
    private SlidingMenu menu;
    private ImageView pindaojia;
    private SQLiteUtils utils;
    List<String> listmenu = new ArrayList<>();
    List<Fragment> listfragment = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //取消标题栏，必须放在super上面
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        //修改状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.red));

        x.view().inject(this);
        initView();

        SlidingMenu();
        initData();

    }

    private void initView() {
        utils = new SQLiteUtils(this);
        pindaojia = (ImageView) findViewById(R.id.pindaojia);

        pindaojia.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
    }

    private void initData() {


        listmenu.add(TITLE.top);
        listmenu.add(TITLE.caijing);
        listmenu.add(TITLE.guoji);
        listmenu.add(TITLE.guonei);
        listmenu.add(TITLE.junshi);
        listmenu.add(TITLE.keji);
        listmenu.add(TITLE.shehui);
        listmenu.add(TITLE.shishang);
        listmenu.add(TITLE.tiyu);
        listmenu.add(TITLE.yule);
        for (int j = 0; j < listmenu.size(); j++) {
            listfragment.add(new XListViewFragment(listmenu.get(j)));

        }



        hsm_contianer.diaplay(listmenu, listfragment);
    }

    private void SlidingMenu() {


        menu = new SlidingMenu(this);
        LeftFragment leftFragment = new LeftFragment();
        menu.setMenu(R.layout.fragment_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, leftFragment).commit();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        menu.setBehindOffsetRes(R.dimen.behindwith);


        RightFragment rightFragment = new RightFragment();
        menu.setSecondaryMenu(R.layout.fragment_layout_right);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_right, rightFragment).commit();
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                menu.showMenu();
                break;
            case R.id.iv_more:
                menu.showSecondaryMenu();
                break;
            case R.id.pindaojia:
                pindaojia();
                break;
        }
    }

    private void pindaojia() {
        if (jsonStr == null) {
            if (list == null) {//判断集合中是否已有数据，没有则创建
                list = new ArrayList<>();
                //第一个是显示的条目，第二个参数是否显示
                list.add(new ChannelBean(TITLE.top, true));
                list.add(new ChannelBean(TITLE.caijing, true));
                list.add(new ChannelBean(TITLE.guoji, true));
                list.add(new ChannelBean(TITLE.guonei, true));
                list.add(new ChannelBean(TITLE.junshi, true));
                list.add(new ChannelBean(TITLE.keji, false));
                list.add(new ChannelBean(TITLE.shehui, false));
                list.add(new ChannelBean(TITLE.shishang, false));
                list.add(new ChannelBean(TITLE.tiyu, false));
                list.add(new ChannelBean(TITLE.yule, false));
            }
        } else {
            list.clear();
            //解析jsonstr
            list = parseJson(jsonStr);

        }
        ChannelActivity.startChannelActivity(MainActivity.this, list);

    }

    //解析Json串
    private List<ChannelBean> parseJson(String jsonStr) {
        list.clear();
        try {
            JSONArray arr = new JSONArray(jsonStr);
            for (int i = 0; i < arr.length(); i++) {

                JSONObject o = (JSONObject) arr.get(i);
                String name = o.getString("name");
                boolean isSelect = o.getBoolean("isSelect");
                ChannelBean bean = new ChannelBean(name, isSelect);
                list.add(bean);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == ChannelActivity.REQUEST_CODE && resultCode == ChannelActivity.RESULT_CODE) {
            jsonStr = data.getStringExtra("json");


            List<String> listmenu2=new ArrayList<>();
            List<Fragment> listfragment2=new ArrayList<>();

            listmenu.clear();
            listfragment.clear();

            List<ChannelBean> beanList = parseJson(jsonStr);

            if (beanList!=null) {
                for (int i = 0; i < beanList.size(); i++) {
                    ChannelBean channelBean = beanList.get(i);
                    if (channelBean.isSelect()) {
                        listmenu2.add(channelBean.getName());
                        listfragment2.add(new XListViewFragment(listmenu2.get(i)));
                    }
                }
            }

            hsm_contianer.diaplay(listmenu2, listfragment2);


        }
    }

}

