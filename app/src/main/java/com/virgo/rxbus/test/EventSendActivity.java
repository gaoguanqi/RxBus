package com.virgo.rxbus.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.virgo.rxbus.R;
import com.virgo.rxbus.base.BaseActivity;
import com.virgo.rxbus.rxbus.Events;
import com.virgo.rxbus.rxbus.RxBus;
import com.virgo.rxbus.rxbus.UserEvent;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/20.
 * 事件发送
 */
public class EventSendActivity extends BaseActivity implements View.OnClickListener {

    private Button btn4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_send);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        btn4 = (Button)findViewById(R.id.btn4);



        //我们这些”RxView”的行为还是要在onCreate里执行了（尽管用户不点不触发，但是你还得像平时setListener那样先把行为注册掉）
        throttleFirst();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                RxBus.getInstance().send(Events.EVENT_CODE_TAP, "Tap传了一个String");
                break;
            case R.id.btn2:
                RxBus.getInstance().send(Events.EVENT_CODE_OTHER, new UserEvent("小明",14));
                break;
            case R.id.btn3:
                RxBus.getInstance().send(Events.EVENT_CODE_MY, new UserEvent("A",14));
                break;

        }
    }

    // throttleFirst操作符：仅发送指定时间段内的第一个信号
    private void throttleFirst() {
        RxView.clicks(btn4)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(EventSendActivity.this, "11111", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EventSendActivity.this,LoginActivity.class));
                    }
                });
    }
}
