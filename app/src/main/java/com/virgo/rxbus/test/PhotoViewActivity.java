package com.virgo.rxbus.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.virgo.rxbus.R;
import com.virgo.rxbus.app.MyApplication;
import com.virgo.rxbus.base.BaseActivity;
import com.virgo.rxbus.utils.common.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/21.
 */
public class PhotoViewActivity extends BaseActivity {
    private MyApplication appCtx;

    private Button btn_phv1;
    private ViewPager viewPage;
    private List<String> urls;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);

        appCtx = MyApplication.getContext();
        btn_phv1 = (Button)findViewById(R.id.btn_phv1);



        urls = new ArrayList<String>(){};
        urls.add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=819201812,3553302270&fm=214&gp=0.jpg");
        urls.add("http://img02.tooopen.com/images/20140504/sy_60294738471.jpg");

         viewPage = (ViewPager) findViewById(R.id.viewPage);
        viewPage.setAdapter(new MyPageAdapter(this,urls));

        throttleFirst1(btn_phv1);

    }



    // throttleFirst操作符：仅发送指定时间段内的第一个信号
    private void throttleFirst1(Button btn) {
        RxView.clicks(btn)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        ToastUtils.showToast(appCtx,"throttleFirst1");
                    }
                });
    }






}
