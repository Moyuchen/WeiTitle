package com.bwie.test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bwie.test.API.TITLE;
import com.bwie.test.Fragment.LeftFragment;
import com.bwie.test.Fragment.RightFragment;
import com.bwie.test.Fragment.XListViewFragment;
import com.bwie.test.HorizontalScrollView.HorizontalScrollTabhost;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.umeng.socialize.UMShareAPI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.main)
public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {

    @ViewInject(R.id.hsm_container)
    HorizontalScrollTabhost hsm_contianer;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.iv_more)
    ImageView iv_more;
    private SlidingMenu menu;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //修改状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.red));

        x.view().inject(this);
        initView();

        SlidingMenu();
        initData();

    }

    private void initView() {
        iv_back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
    }

    private void initData() {
        List<String> listmenu=new ArrayList<>();
        List<Fragment> listfragment=new ArrayList<>();
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

        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());
        listfragment.add(new XListViewFragment());

        hsm_contianer.diaplay(listmenu,listfragment);
    }

    private void SlidingMenu() {
        menu = getSlidingMenu();

        LeftFragment leftFragment=new LeftFragment();
        setBehindContentView(R.layout.fragment_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,leftFragment).commit();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        menu.setBehindOffsetRes(R.dimen.behindwith);


        RightFragment rightFragment=new RightFragment();
        menu.setSecondaryMenu(R.layout.fragment_layout_right);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_right,rightFragment).commit();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                menu.showMenu();
                break;
            case R.id.iv_more:
                menu.showSecondaryMenu();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
