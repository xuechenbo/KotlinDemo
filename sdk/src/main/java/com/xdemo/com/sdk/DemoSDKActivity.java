package com.xdemo.com.sdk;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class DemoSDKActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initMySDK();
    }

    private String initMySDK() {

        return "测试测试测试";
    }
}
