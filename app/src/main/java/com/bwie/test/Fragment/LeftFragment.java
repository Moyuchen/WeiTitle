package com.bwie.test.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.test.LoginActivity;
import com.bwie.test.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * User: 张亚博
 * Date: 2017-08-30 14:37
 * Description：
 */
public class LeftFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView Phone_login;
    private ImageView WeiXin_login;
    private ImageView Sina_login;
    private ImageView QQ_login;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null){
            view=inflater.inflate(R.layout.fragment_left,container,false);
        }
        //获取布局中的控件
        initView();
        return view;
    }



    /**
     * 获取控件,及给控件设置监听
     */
    private void initView() {
        Phone_login = view.findViewById(R.id.Phone_login);
        WeiXin_login = view.findViewById(R.id.WeiXin_login);
        QQ_login = view.findViewById(R.id.QQ_login);
        Sina_login = view.findViewById(R.id.Sina_login);

        Phone_login.setOnClickListener(this);
        WeiXin_login.setOnClickListener(this);
        QQ_login.setOnClickListener(this);
        Sina_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Phone_login:
                //跳转到登录页面
                Phonelogin();
                break;
            case R.id.WeiXin_login:
                WeiXinlogin();
                break;
            case R.id.QQ_login:
                qqlogin();
                break;
            case R.id.Sina_login:
                Sinalogin();
                break;
        }

    }

    /**
     * 新浪授权登录
     */
    private void Sinalogin() {

    }

    /**
     * QQ授权登录
     */
    private void qqlogin() {
        //授权
        authorization(SHARE_MEDIA.QQ);

    }

    /**
     * 授权方法
     * @param sharemedia 授权参数
     */
    private void authorization(SHARE_MEDIA sharemedia) {
        UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), sharemedia, new UMAuthListener() {
            //授权开始
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }
            //授权完成
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//                Toast.makeText(getActivity(),"授权完成",Toast.LENGTH_LONG).show();

                String name = map.get("name");
               Toast.makeText(getActivity(),"授权者为："+name,Toast.LENGTH_LONG).show();
                System.out.println("授权者为："+name);
            }
            //授权错误
            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }
            //授权取消
            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });

    }

    /**
     * WeiXin授权登录
     */
    private void WeiXinlogin() {

    }

    /**
     * 跳转到Phonelogin登录页面
     *
     */
    private void Phonelogin() {
        Intent in_phone=new Intent(getActivity(),LoginActivity.class);
        getActivity().startActivity(in_phone);
    }
}
