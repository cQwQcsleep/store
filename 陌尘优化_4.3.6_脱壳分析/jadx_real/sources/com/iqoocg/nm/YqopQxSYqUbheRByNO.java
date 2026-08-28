package com.iqoocg.nm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import core.pro.android.notify.i;
import core.pro.android.notify.l2;
import core.pro.android.notify.z0;

@TargetApi(28)
/* loaded from: /workspace/unpacked/classes2.dex */
public class YqopQxSYqUbheRByNO extends AppComponentFactory {
    private static String originAppClassName;
    private static AppComponentFactory originFactory;

    public YqopQxSYqUbheRByNO() {
        try {
            String str = fcRuQsQrcxOAzxwEalcM.originFactoryClassName;
            if (str != null && str.length() != 0 && !l2.decrypt("LBR6F2tClD0sCm5LRVuAUCIXbgpqTp5nCxt9EWtZiQ==\n", "TXoeZQQr8BM=\n").equals(fcRuQsQrcxOAzxwEalcM.originFactoryClassName)) {
                Class<?> clsLoadClass = getClass().getClassLoader().loadClass(fcRuQsQrcxOAzxwEalcM.originFactoryClassName);
                if (AppComponentFactory.class.isAssignableFrom(clsLoadClass)) {
                    originFactory = (AppComponentFactory) clsLoadClass.getDeclaredConstructor(null).newInstance(null);
                    return;
                } else {
                    originFactory = new AppComponentFactory();
                    return;
                }
            }
            originFactory = new AppComponentFactory();
        } catch (Throwable unused) {
            originFactory = new AppComponentFactory();
        }
    }

    public static String getOriginAppClassName() {
        return originAppClassName;
    }

    @Override // android.app.AppComponentFactory
    public Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return z0.a(originFactory, classLoader, str, intent);
    }

    @Override // android.app.AppComponentFactory
    public Application instantiateApplication(ClassLoader classLoader, String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        String str2 = i.APPLICATION;
        if (!str2.equals(str)) {
            originAppClassName = str;
            str = str2;
        }
        return super.instantiateApplication(classLoader, str);
    }

    @Override // android.app.AppComponentFactory
    public ContentProvider instantiateProvider(ClassLoader classLoader, String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return z0.d(originFactory, classLoader, str);
    }

    @Override // android.app.AppComponentFactory
    public BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return z0.c(originFactory, classLoader, str, intent);
    }

    @Override // android.app.AppComponentFactory
    public Service instantiateService(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return z0.b(originFactory, classLoader, str, intent);
    }
}
