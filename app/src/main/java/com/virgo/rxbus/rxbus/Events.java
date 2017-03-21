package com.virgo.rxbus.rxbus;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/3/20.
 */
public class Events<T> {
    //所有事件的CODE
    public static final int EVENT_CODE_TAP = 1; //点击事件
    public static final int EVENT_CODE_OTHER = 21; //其它事件

    public static final int EVENT_CODE_MY = 7; //其它事件

    //枚举
    @IntDef({EVENT_CODE_TAP, EVENT_CODE_OTHER,EVENT_CODE_MY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventCode {}


    public @Events.EventCode int code;
    public T content;

    public static <O> Events<O> setContent(O t) {
        Events<O> events = new Events<>();
        events.content = t;
        return events;
    }

    public <T> T getContent() {
        return (T) content;
    }
}
