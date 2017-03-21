package com.virgo.rxbus.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trello.rxlifecycle.FragmentEvent;
import com.virgo.rxbus.R;
import com.virgo.rxbus.base.BaseFragment;
import com.virgo.rxbus.rxbus.Events;
import com.virgo.rxbus.rxbus.RxBus;
import com.virgo.rxbus.rxbus.UserEvent;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/20.
 * 事件接收 处理
 */
public class EventReceiveFragment extends BaseFragment {
    private TextView textTest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_event_receive, null);
        textTest = (TextView) view.findViewById(R.id.textTest);

        RxBus.with(this)
                .setEvent(Events.EVENT_CODE_TAP)
                .setEndEvent(FragmentEvent.DESTROY_VIEW) //不设置默认与fragment生命周期同步
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        String content = events.getContent();
                        textTest.setText(content);
                    }
                })
                .create();

        RxBus.with(this)
                .setEvent(Events.EVENT_CODE_OTHER)
                .setEndEvent(FragmentEvent.DESTROY_VIEW) //不设置默认与fragment生命周期同步
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        UserEvent event = events.getContent();
                        textTest.setText("Name: " + event.getName() + ",Age: " + event.getAge());
                    }
                })
                .onError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        textTest.setText(throwable.toString());
                    }
                }) // 异常处理，默认捕获异常，不做处理，程序不会crash。
                .create();


        RxBus.with(this)
                .setEvent(Events.EVENT_CODE_MY)
                .setEndEvent(FragmentEvent.DESTROY_VIEW) //不设置默认与fragment生命周期同步
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        UserEvent event = events.getContent();
                        textTest.setText("Name: " + event.getName() + ",Age: " + event.getAge());
                    }
                })
                .create();


        return view;
    }
}
