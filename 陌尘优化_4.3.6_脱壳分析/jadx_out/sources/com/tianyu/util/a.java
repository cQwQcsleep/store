package com.tianyu.util;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class a extends AppComponentFactory {
    private static Object sAppComponentFactory;

    private Object getTargetAppComponentFactory(ClassLoader classLoader) throws IllegalAccessException, InstantiationException {
        if (sAppComponentFactory == null) {
            String targetClassName = getTargetClassName();
            if (!TextUtils.isEmpty(targetClassName)) {
                try {
                    Object objNewInstance = Class.forName(targetClassName, true, classLoader).newInstance();
                    sAppComponentFactory = objNewInstance;
                    return objNewInstance;
                } catch (Exception unused) {
                }
            }
        }
        return sAppComponentFactory;
    }

    private String getTargetClassName() {
        return DtcLoader.rcf();
    }

    @Override // android.app.AppComponentFactory
    public Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException {
        Object targetAppComponentFactory = getTargetAppComponentFactory(classLoader);
        if (targetAppComponentFactory != null) {
            try {
                return (Activity) targetAppComponentFactory.getClass().getMethod(Configuration.METHOD_INSTANTIATE_ACTIVITY, ClassLoader.class, String.class, Intent.class).invoke(targetAppComponentFactory, classLoader, str, intent);
            } catch (Exception unused) {
            }
        }
        return super.instantiateActivity(classLoader, str, intent);
    }

    @Override // android.app.AppComponentFactory
    public Application instantiateApplication(ClassLoader classLoader, String str) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, ClassNotFoundException, SecurityException, IOException, IllegalArgumentException, InvocationTargetException {
        Object targetAppComponentFactory;
        if (!Configuration.sIsReplacedClassLoader) {
            ApplicationInfo applicationInfo = DtcLoader.getApplicationInfo();
            if (applicationInfo == null) {
                throw new NullPointerException("application info is null");
            }
            DtcLoader.unzipLibs(applicationInfo.sourceDir, applicationInfo.dataDir);
            DtcLoader.loadShellLibs(applicationInfo.dataDir);
        }
        DtcLoader.ia();
        String strRapn = DtcLoader.rapn();
        if (Configuration.sIsReplacedClassLoader) {
            targetAppComponentFactory = getTargetAppComponentFactory(classLoader);
        } else {
            DtcLoader.cbde(classLoader);
            Configuration.sIsReplacedClassLoader = true;
            targetAppComponentFactory = getTargetAppComponentFactory(classLoader);
        }
        Configuration.sNeedCalledApplication = false;
        if (targetAppComponentFactory != null) {
            try {
                Method declaredMethod = targetAppComponentFactory.getClass().getDeclaredMethod(Configuration.METHOD_INSTANTIATE_APPLICATION, ClassLoader.class, String.class);
                return !TextUtils.isEmpty(strRapn) ? (Application) declaredMethod.invoke(targetAppComponentFactory, classLoader, strRapn) : (Application) declaredMethod.invoke(targetAppComponentFactory, classLoader, str);
            } catch (Exception unused) {
            }
        }
        if (TextUtils.isEmpty(strRapn)) {
            return super.instantiateApplication(classLoader, str);
        }
        try {
            Class.forName(strRapn, false, classLoader);
        } catch (ClassNotFoundException unused2) {
            ApplicationInfo applicationInfo2 = DtcLoader.getApplicationInfo();
            if (applicationInfo2 != null) {
                strRapn = applicationInfo2.packageName + "." + strRapn;
            }
        }
        return super.instantiateApplication(classLoader, strRapn);
    }

    @Override // android.app.AppComponentFactory
    public ClassLoader instantiateClassLoader(ClassLoader classLoader, ApplicationInfo applicationInfo) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, IllegalArgumentException, InvocationTargetException {
        DtcLoader.unzipLibs(applicationInfo.sourceDir, applicationInfo.dataDir);
        DtcLoader.loadShellLibs(applicationInfo.dataDir);
        DtcLoader.ia();
        ClassLoader classLoaderDcl = DtcLoader.dcl(classLoader, applicationInfo.nativeLibraryDir);
        if (classLoaderDcl != null) {
            classLoader = classLoaderDcl;
        }
        Object targetAppComponentFactory = getTargetAppComponentFactory(classLoader);
        Configuration.sIsReplacedClassLoader = true;
        if (targetAppComponentFactory != null) {
            try {
                return (ClassLoader) targetAppComponentFactory.getClass().getMethod(Configuration.METHOD_INSTANTIATE_CLASS_LOADER, ClassLoader.class, ApplicationInfo.class).invoke(targetAppComponentFactory, classLoader, applicationInfo);
            } catch (Exception unused) {
            }
        }
        return classLoader;
    }

    @Override // android.app.AppComponentFactory
    public ContentProvider instantiateProvider(ClassLoader classLoader, String str) throws IllegalAccessException, InstantiationException {
        Object targetAppComponentFactory = getTargetAppComponentFactory(classLoader);
        if (targetAppComponentFactory != null) {
            try {
                return (ContentProvider) targetAppComponentFactory.getClass().getMethod(Configuration.METHOD_INSTANTIATE_PROVIDER, ClassLoader.class, String.class).invoke(targetAppComponentFactory, classLoader, str);
            } catch (Exception unused) {
            }
        }
        return super.instantiateProvider(classLoader, str);
    }

    @Override // android.app.AppComponentFactory
    public BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException {
        Object targetAppComponentFactory = getTargetAppComponentFactory(classLoader);
        if (targetAppComponentFactory != null) {
            try {
                return (BroadcastReceiver) targetAppComponentFactory.getClass().getMethod(Configuration.METHOD_INSTANTIATE_RECEIVER, ClassLoader.class, String.class, Intent.class).invoke(targetAppComponentFactory, classLoader, str, intent);
            } catch (Exception unused) {
            }
        }
        return super.instantiateReceiver(classLoader, str, intent);
    }

    @Override // android.app.AppComponentFactory
    public Service instantiateService(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException {
        Object targetAppComponentFactory = getTargetAppComponentFactory(classLoader);
        if (targetAppComponentFactory != null) {
            try {
                return (Service) targetAppComponentFactory.getClass().getMethod(Configuration.METHOD_INSTANTIATE_SERVICE, ClassLoader.class, String.class, Intent.class).invoke(targetAppComponentFactory, classLoader, str, intent);
            } catch (Exception unused) {
            }
        }
        return super.instantiateService(classLoader, str, intent);
    }
}
