package com.reandroid.apk;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ApkModuleCoder {
    public static final String ROOT_DIRECTORY_NAME = "root";
    public static final String SIGNATURE_DIRECTORY_NAME = "signatures";
    private APKLogger apkLogger;

    public APKLogger getApkLogger() {
        return this.apkLogger;
    }

    public abstract ApkModule getApkModule();

    public void logError(String str, Throwable th) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            if (str == null && th == null) {
                return;
            }
            aPKLogger.logError(str, th);
        }
    }

    public void logMessage(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logMessage(str);
        }
    }

    public void logVerbose(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logVerbose(str);
        }
    }

    public void setApkLogger(APKLogger aPKLogger) {
        this.apkLogger = aPKLogger;
    }
}
