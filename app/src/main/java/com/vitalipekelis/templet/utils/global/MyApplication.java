package com.vitalipekelis.templet.utils.global;

import android.app.Application;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */
public class MyApplication extends Application{

    private static MyApplication mApplicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationInstance = MyApplication.this;
        //TODO:
    }

    public static final MyApplication getInstance(){
        return mApplicationInstance;
    }
}
