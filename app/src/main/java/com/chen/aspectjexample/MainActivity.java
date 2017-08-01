package com.chen.aspectjexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.chen.aspectj.annotation.LogMethod;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test(new A(),"param");
    }

    @LogMethod
    private String test(A a, String str) {
        Log.d("czh","哈哈哈" + a.name + str);
        return "123";
    }


    private class A  {
        String name = "czh";
    }

}
