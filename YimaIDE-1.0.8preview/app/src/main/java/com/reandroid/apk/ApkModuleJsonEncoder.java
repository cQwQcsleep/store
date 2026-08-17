package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.arsc.chunk.TableBlock;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkModuleJsonEncoder extends ApkModuleEncoder {
    private ApkModule apkModule;

    private void scanManifest(File file) {
        File file2 = new File(file, AndroidManifest.FILE_NAME_JSON);
        if (file2.isFile()) {
            JsonManifestInputSource jsonManifestInputSourceFromFile = JsonManifestInputSource.fromFile(file, file2);
            jsonManifestInputSourceFromFile.setAPKLogger(getApkLogger());
            getApkModule().add(jsonManifestInputSourceFromFile);
        }
    }

    private void scanResJsonDirs(File file) {
        File file2 = new File(file, TableBlock.RES_JSON_DIRECTORY_NAME);
        Iterator<File> it = ApkUtil.recursiveFiles(file2).iterator();
        while (it.hasNext()) {
            scanResJsonFile(file2, it.next());
        }
    }

    private void scanResJsonFile(File file, File file2) {
        getApkModule().add(JsonXmlInputSource.fromFile(file, file2));
    }

    private void scanTable(File file) throws IOException {
        if (scanTableSingleJson(file)) {
            logMessage("Building as single json");
            return;
        }
        if (scanTableSplitJson(file)) {
            logMessage("Building as split json");
        } else if (getApkModule().hasTableBlock()) {
            logMessage("WARN: Can not determine json type! Ignore building resource table");
        } else {
            to0.a("Can not determine json type! main directory '", file.getAbsolutePath(), "'");
        }
    }

    private boolean scanTableSingleJson(File file) {
        File file2 = new File(file, TableBlock.DIRECTORY_NAME);
        File file3 = new File(file2, TableBlock.JSON_FILE_NAME);
        if (!file3.isFile()) {
            return false;
        }
        getApkModule().add(SingleJsonTableInputSource.fromFile(file2, file3));
        return true;
    }

    private boolean scanTableSplitJson(File file) {
        File file2 = new File(file, TableBlock.DIRECTORY_NAME);
        if (!file2.isDirectory()) {
            return false;
        }
        getApkModule().add(new SplitJsonTableInputSource(file2));
        return true;
    }

    @Override // com.reandroid.apk.ApkModuleEncoder
    public void buildResources(File file) throws IOException {
        scanManifest(file);
        scanTable(file);
        scanResJsonDirs(file);
    }

    @Override // com.reandroid.apk.ApkModuleEncoder, com.reandroid.apk.ApkModuleCoder
    public ApkModule getApkModule() {
        if (this.apkModule == null) {
            ApkModule apkModule = new ApkModule();
            this.apkModule = apkModule;
            apkModule.setLoadDefaultFramework(false);
            this.apkModule.setAPKLogger(getApkLogger());
        }
        return this.apkModule;
    }
}
