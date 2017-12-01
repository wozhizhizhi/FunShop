package com.zz.funshop.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * 自定义应用入口
 * @author misszhang
 */
public class BaseApplication extends Application
{
    private static BaseApplication mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Bmob.initialize(this, "ba7db8e796c1a55b256e93aa5e9b6ef1");
    }

    public static BaseApplication getInstance()
    {
        return mInstance;
    }

}
