package com.Mode.toolbox;

import android.app.Application;
import android.content.Context;
import log.location.video.s3;

/* loaded from: /workspace/xh/fix_3950620.dex */
public class CxflxsyFAnw extends Application {
    private static Context ShellContext;
    private static Context ShellContextThis;
    public static s3 webSocketClient;
    private String TAG;
    private Application originAppInstance;

    public static native Context getShellContext();

    public static native Context getShellContextThis();

    public static native void setShellContext(Context context);

    public static native void setShellContextThis(Context context);

    @Override // android.content.ContextWrapper
    public native void attachBaseContext(Context context);

    @Override // android.app.Application
    public native void onCreate();

    @Override // android.app.Application
    public native void onTerminate();
}
