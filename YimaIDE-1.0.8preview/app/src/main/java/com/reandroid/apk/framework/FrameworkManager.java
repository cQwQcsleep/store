package com.reandroid.apk.framework;

import com.reandroid.apk.FrameworkApk;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class FrameworkManager {
    private FrameworkApk mCurrent;

    public abstract FrameworkApk get(int i);

    public abstract FrameworkApk getBestMatch(int i);

    public FrameworkApk getCurrent() {
        synchronized (this) {
            try {
                FrameworkApk frameworkApk = this.mCurrent;
                if (frameworkApk == null) {
                    return null;
                }
                if (!frameworkApk.isDestroyed()) {
                    return frameworkApk;
                }
                this.mCurrent = null;
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract FrameworkApk getLatest();

    public abstract Integer getLatestVersion();

    public abstract Integer getNearestVersion(int i);

    public void setCurrent(FrameworkApk frameworkApk) {
        synchronized (this) {
            this.mCurrent = frameworkApk;
        }
    }
}
