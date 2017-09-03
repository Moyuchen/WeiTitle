package com.bwie.test;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.Bean.LoginUserInfo;
import com.bwie.test.Utils.SharepreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * User: 张亚博
 * Date: 2017-09-01 14:04
 * Description：
 */
@ContentView(R.layout.user_login)
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean boolShowInDialog=true;
    private EventHandler eventhandler;
    private EditText et_phone;
    private TextView send_ya;
    private EditText et_yz;
    private Button but_into;
    private int time=5;
    Handler handler=new Handler();
    private Runnable runnalble=new Runnable() {
        @Override
        public void run() {
            time--;
            if (time==0) {
                handler.removeCallbacks(runnalble);
                time=5;
                send_ya.setEnabled(true);
                send_ya.setText("再次获取");
            }else{
                send_ya.setEnabled(false);
                send_ya.setTextColor(Color.GRAY);
                send_ya.setText(time+"s");
                handler.postDelayed(runnalble,1000);
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //获取控件
        initView();
        //注册短信验证SDK
        registerSDK();

    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.user_login_et_phone);
        send_ya = (TextView) findViewById(R.id.user_login_send_yz);
        et_yz = (EditText) findViewById(R.id.user_login_et_yz);
        but_into = (Button) findViewById(R.id.user_login_but_into);

        send_ya.setOnClickListener(this);
        but_into.setOnClickListener(this);
    }

    /**
     * 注册短信验证方法
     */
    private void registerSDK() {
        //如果希望在读取通信录的时候，可以添加下面的代码，并且在其他代码调用之前，否则不起作用；
        //如果没这个需求，可以不加这行代码
//        SMSSDK.setAskPermisionOnReadContact(boolShowInDialog);

        //创建EventHandler对象
        eventhandler = new EventHandler(){

            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable= (Throwable) data;
                    final String message = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    //只有回服务器验证成功，才能允许用户登录
                    if (result== SMSSDK.RESULT_COMPLETE) {
                        //回调完成，提交验证码成功
                        if (event== SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                //保存用户信息
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this,"服务器验证成功",Toast.LENGTH_LONG).show();
                                            //创建用户对象
                                        LoginUserInfo info=new LoginUserInfo();
                                        info.uid=et_phone.getText().toString();
                                        info.phone=et_phone.getText().toString();
                                            //将用户信心保存起来

                                        SharepreferencesUtils.putSharepreferences("uid",info.uid);
                                        SharepreferencesUtils.putSharepreferences("phone",info.phone);
                                    }
                                });

                        }
                    } else if (result==SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"验证码以发送",Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if (result==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //获取支持的国家列表
                    }
                }
            }
        };
//注册监听器
        SMSSDK.registerEventHandler(eventhandler);
    }

    /**
     *  判断输入框内是否为空，并弹出提示
     */
    private void verify() {
        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_yz.getText().toString())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_login_send_yz:
                String phone = et_phone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(LoginActivity.this,"请输入手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                handler.postDelayed(runnalble,1000);
                SMSSDK.getVerificationCode("86",phone);
                break;
            case R.id.user_login_but_into:
                //判断输入框内是否为空，并弹出提示
                verify();
                SMSSDK.submitVerificationCode("86",et_phone.getText().toString(),et_yz.getText().toString());

                break;
        }
    }
}
