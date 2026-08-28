package com.stub;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.tianyu.util.Configuration;
import com.tianyu.util.DtcLoader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class StubApp extends Application {
    private String realApplicationName = Configuration.EMPTY;
    private Application realApplication = null;

    private void replaceApplication() {
        if (!Configuration.sNeedCalledApplication || TextUtils.isEmpty(this.realApplicationName)) {
            return;
        }
        this.realApplication = (Application) DtcLoader.ra(this.realApplicationName);
        DtcLoader.craoc(this.realApplicationName);
        Configuration.sNeedCalledApplication = false;
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) throws IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException, InvocationTargetException {
        super.attachBaseContext(context);
        if (!Configuration.sIsReplacedClassLoader) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                throw new NullPointerException("application info is null");
            }
            DtcLoader.unzipLibs(applicationInfo.sourceDir, applicationInfo.dataDir);
            DtcLoader.loadShellLibs(applicationInfo.dataDir);
            DtcLoader.ia();
            DtcLoader.cbde(context.getClassLoader());
            Configuration.sIsReplacedClassLoader = true;
        }
        this.realApplicationName = DtcLoader.rapn();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createPackageContext(String str, int i) {
        if (TextUtils.isEmpty(this.realApplicationName)) {
            return super.createPackageContext(str, i);
        }
        replaceApplication();
        return this.realApplication;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        return !TextUtils.isEmpty(this.realApplicationName) ? Configuration.EMPTY : super.getPackageName();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        replaceApplication();
    }
}
