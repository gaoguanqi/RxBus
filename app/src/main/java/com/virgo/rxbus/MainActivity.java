package com.virgo.rxbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.virgo.rxbus.base.BaseActivity;
import com.virgo.rxbus.test.EventSendActivity;
import com.virgo.rxbus.test.PhotoViewActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventSendActivity.class));
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhotoViewActivity.class));
            }
        });
    }
}
