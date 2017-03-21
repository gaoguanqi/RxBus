package com.virgo.rxbus.test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.CheckBox;

import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.virgo.rxbus.R;
import com.virgo.rxbus.base.BaseActivity;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/20.
 *
 *用户登录界面，勾选同意隐私协议，登录按钮就变高亮
 */
public class LoginActivity extends BaseActivity{

    private Button btnLogin;
    private CheckBox checkBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // 注：需要RxSharedPreferences库支持:https://github.com/f2prateek/rx-preferences
       // RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
       // Preference<Boolean> isSave = rxPreferences.getBoolean("isSave", false);


        RxCompoundButton.checkedChanges(checkBox )
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        btnLogin.setEnabled( aBoolean );
                        //btnLogin.setBackgroundResource( aBoolean ? R.color.colorAccent : R.color.colorPrimaryDark );
                        if(aBoolean){
                            btnLogin.setTextColor(getResources().getColor(R.color.black));
                            btnLogin.setBackgroundResource(R.color.colorPrimaryDark);
                        }else{
                            //灰色
                            btnLogin.setTextColor(getResources().getColor(R.color.wheat));
                            btnLogin.setBackgroundResource(R.color.colorGray);
                        }
                    }
                }) ;
    }
}
