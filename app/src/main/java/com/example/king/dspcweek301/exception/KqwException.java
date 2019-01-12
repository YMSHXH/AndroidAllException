package com.example.king.dspcweek301.exception;

import android.content.Context;
import android.util.Log;

public class KqwException implements Thread.UncaughtExceptionHandler {


    private static KqwException myCrashHandler;
    private Context mContext;

    private KqwException(Context context) {
        mContext = context;
    }

    public static synchronized KqwException getInstance(Context context) {
        if (null == myCrashHandler) {
            myCrashHandler = new KqwException(context);
        }
        return myCrashHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        long threadId = t.getId();
        String message = e.getMessage();
        String localizedMessage = e.getLocalizedMessage();
        Log.i("KqwException", "------------------------------------------------------");
        Log.i("KqwException", "threadId = " + threadId);
        Log.i("KqwException", "message = " + message);
        Log.i("KqwException", "localizedMessage = " + localizedMessage);
        Log.i("KqwException", "------------------------------------------------------");
        e.printStackTrace();
        Log.i("KqwException", "------------------------------------------------------");

        // TODO 下面捕获到异常以后要做的事情，可以重启应用，获取手机信息上传到服务器等
        Log.i("KqwException", "------------------应用被重启----------------");
        // 重启应用
        mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName()));
        //干掉当前的程序
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
