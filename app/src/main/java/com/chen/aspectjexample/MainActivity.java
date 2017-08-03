package com.chen.aspectjexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.chen.aspectj.annotation.LogMethod;
import com.chen.aspectj.annotation.Trace;

import java.lang.reflect.Method;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test(new A(),"param");
    }

    @LogMethod
    @Trace
    private String test(A a, String str) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.run();


        return "123";
    }


    private class A  {
        String name = "czh";
    }

}
