package com.bwie.test.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.bwie.test.LiXianXiaZaiActivity;
import com.bwie.test.R;

/**
 * User: 张亚博
 * Date: 2017-08-30 14:43
 * Description：
 */
public class RightFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout feiwifi;//非WiFi控制布局

    private View view_feiwifi;//非WiFi控制布局View
    private Button but_feiwifi;//非WiFi控制取消按钮
    private View rela_datu;
    private View rela_jisheng;
    private View rela_zhineng;
    private CheckBox checkb_datu;
    private CheckBox checkb_jisheng;
    private CheckBox checkb_zhineng;
    private RelativeLayout rela_lixianxiazai;//离线下载布局

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view=inflater.inflate(R.layout.fragment_right,container,false);
            initView();
        }
            return view;
        }

    private void initView() {
        //非WiFi控制布局
        feiwifi = view.findViewById(R.id.frag_right_feiwifi);
        rela_lixianxiazai = view.findViewById(R.id.rela_lixianxiazai);
        //非WiFi控制布局View
        view_feiwifi = View.inflate(getActivity(), R.layout.feiwifi_layout, null);
        //清除父控件中的子控件，使父view始终保持一个子view
        ViewGroup parent = (ViewGroup) view_feiwifi.getParent();
        if (parent!=null) {
            parent.removeAllViews();
        }
        rela_datu = view_feiwifi.findViewById(R.id.rela_datu);
        checkb_datu = view_feiwifi.findViewById(R.id.checkb_datu);

        rela_jisheng = view_feiwifi.findViewById(R.id.rela_jisheng);
        checkb_jisheng = view_feiwifi.findViewById(R.id.checkb_jisheng);

        rela_zhineng = view_feiwifi.findViewById(R.id.rela_zhineng);
        checkb_zhineng = view_feiwifi.findViewById(R.id.checkb_zhineng);


        but_feiwifi = view_feiwifi.findViewById(R.id.feiwifi_cancle);
        feiwifi.setOnClickListener(this);
        rela_datu.setOnClickListener(this);
        rela_jisheng.setOnClickListener(this);
        rela_zhineng.setOnClickListener(this);
        rela_lixianxiazai.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frag_right_feiwifi:
                frag_right_feiwifi();
                break;
            case R.id.rela_datu:
                        checkb_datu.setChecked(true);
                        checkb_jisheng.setChecked(false);
                        checkb_zhineng.setChecked(false);
                break;
            case R.id.rela_jisheng:
                checkb_jisheng.setChecked(true);
                checkb_datu.setChecked(false);
                checkb_zhineng.setChecked(false);
                break;
            case R.id.rela_zhineng:
                checkb_zhineng.setChecked(true);
                checkb_jisheng.setChecked(false);
                checkb_datu.setChecked(false);
                break;
            case R.id.rela_lixianxiazai:
                lixianxiazai();
                break;
        }
    }

    private void lixianxiazai() {
        Intent in=new Intent(getActivity(), LiXianXiaZaiActivity.class);
        getActivity().startActivity(in);
    }

    /**
     * 非WiFi网络控制方法
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void frag_right_feiwifi() {


        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view_feiwifi)
                .show();


        //取消弹窗
        but_feiwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });




    }
}
