package com.reandroid.apk;

import com.reandroid.apk.framework.FrameworkManager;
import com.reandroid.apk.framework.InternalFrameworks;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class AndroidFrameworks {
    private static FrameworkManager frameworkManager;
    private static FrameworkApk mCurrent;

    public static FrameworkApk getBestMatch(int i) {
        return getFrameworkManager().getBestMatch(i);
    }

    public static FrameworkApk getCurrent() {
        FrameworkApk frameworkApk = mCurrent;
        if (frameworkApk == null) {
            return null;
        }
        if (!frameworkApk.isDestroyed()) {
            return frameworkApk;
        }
        mCurrent = null;
        return null;
    }

    public static FrameworkManager getFrameworkManager() {
        FrameworkManager frameworkManager2;
        synchronized (AndroidFrameworks.class) {
            try {
                frameworkManager2 = frameworkManager;
                if (frameworkManager2 == null) {
                    frameworkManager2 = InternalFrameworks.INSTANCE;
                    frameworkManager = frameworkManager2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return frameworkManager2;
    }

    public static FrameworkApk getLatest() {
        return getFrameworkManager().getLatest();
    }

    public static void setCurrent(FrameworkApk frameworkApk) {
        synchronized (AndroidFrameworks.class) {
            mCurrent = frameworkApk;
        }
    }

    public static void setFrameworkManager(FrameworkManager frameworkManager2) {
        synchronized (AndroidFrameworks.class) {
            frameworkManager = frameworkManager2;
        }
    }
}
