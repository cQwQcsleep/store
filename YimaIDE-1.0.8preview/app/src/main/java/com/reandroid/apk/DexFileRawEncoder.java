package com.reandroid.apk;

import com.reandroid.archive.FileInputSource;
import com.reandroid.archive.InputSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DexFileRawEncoder implements DexEncoder {
    private APKLogger apkLogger;

    @Override // com.reandroid.apk.DexEncoder
    public List<InputSource> buildDexFiles(ApkModuleEncoder apkModuleEncoder, File file) throws IOException {
        File file2 = new File(file, DexFileInputSource.DEX_DIRECTORY_NAME);
        if (!file2.isDirectory()) {
            return new ArrayList();
        }
        List<File> listListDexFiles = DexFileInputSource.listDexFiles(file2);
        ArrayList arrayList = new ArrayList(listListDexFiles.size());
        if (listListDexFiles.size() == 0) {
            logMessage("WARN: No dex files found on: " + file2);
            return arrayList;
        }
        for (File file3 : listListDexFiles) {
            String name = file3.getName();
            FileInputSource fileInputSource = new FileInputSource(file3, name);
            fileInputSource.setMethod(0);
            DexFileInputSource dexFileInputSource = new DexFileInputSource(name, fileInputSource);
            dexFileInputSource.setMethod(0);
            arrayList.add(dexFileInputSource);
            logVerbose(name);
        }
        return arrayList;
    }

    public APKLogger getApkLogger() {
        return this.apkLogger;
    }

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
