package com.example.king.dspcweek301;

import android.app.Application;

import com.example.king.dspcweek301.apis.GoodsApi;
import com.example.king.dspcweek301.exception.KqwException;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);

        KqwException handler = KqwException.getInstance(this);
        Thread.setDefaultUncaughtExceptionHandler(handler);

        /*
            注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，
            也需要在App代码中调用初始化接口
            （如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(this, GoodsApi.UMENG_APPKEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        // 打开统计SDK调试模式
        UMConfigure.setLogEnabled(true);
    }
}
