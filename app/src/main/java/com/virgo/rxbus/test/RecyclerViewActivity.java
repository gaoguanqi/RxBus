package com.virgo.rxbus.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.trello.rxlifecycle.FragmentEvent;
import com.virgo.rxbus.R;
import com.virgo.rxbus.base.BaseActivity;
import com.virgo.rxbus.rxbus.Events;
import com.virgo.rxbus.rxbus.RxBus;
import com.virgo.rxbus.rxbus.UserEvent;
import com.virgo.rxbus.utils.common.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/22.
 */
public class RecyclerViewActivity extends BaseActivity{
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        datas = new ArrayList<String>(){};
        datas.add("111");
        datas.add("22");
        datas.add("333");
        datas.add("44");
        datas.add("5555");

        myAdapter = new MyAdapter(this,datas);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画。
        recyclerView.setAdapter(myAdapter);





        RxBus.with(this)
                .setEvent(Events.EVENT_CODE_MY)
                .setEndEvent(FragmentEvent.DESTROY_VIEW) //不设置默认与fragment生命周期同步
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        UserEvent event = events.getContent();
                      if(datas.get(event.getAge()).equals(event.getName())){
                          ToastUtils.showToast(RecyclerViewActivity.this,event.getName());
                      }
                    }
                })
                .create();

    }
}
