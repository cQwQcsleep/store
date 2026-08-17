package com.reandroid.apk;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface APKLogger {
    void logError(String str, Throwable th);

    void logMessage(String str);

    void logVerbose(String str);
}
