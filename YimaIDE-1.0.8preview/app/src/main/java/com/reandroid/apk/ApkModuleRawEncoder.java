package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.FileInputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import java.io.File;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkModuleRawEncoder extends ApkModuleEncoder {
    private final ApkModule apkModule;
    private boolean mKeepOriginal;

    public ApkModuleRawEncoder() {
        ZipEntryMap zipEntryMap = new ZipEntryMap();
        this.apkModule = new ApkModule("encoded_raw".concat(String.valueOf(zipEntryMap.hashCode()).substring(1)), zipEntryMap);
    }

    private void addTableBlock(File file) {
        String str = TableBlock.FILE_NAME;
        File file2 = new File(file, str);
        if (!file2.isFile()) {
            logMessage("Warn: File not found: " + str);
            return;
        }
        getApkModule().setLoadDefaultFramework(false);
        FileInputSource fileInputSource = new FileInputSource(file2, str);
        ApkModule apkModule = getApkModule();
        apkModule.add(fileInputSource);
        if (isKeepOriginal()) {
            apkModule.discardManifestChanges();
        }
    }

    @Override // com.reandroid.apk.ApkModuleEncoder
    public void buildResources(File file) {
        addTableBlock(file);
    }

    @Override // com.reandroid.apk.ApkModuleEncoder
    public void encodeBinaryManifest(File file) {
        String str = AndroidManifest.FILE_NAME_BIN;
        File file2 = new File(file, str);
        if (!file2.isFile()) {
            file2 = new File(file, AndroidManifest.FILE_NAME);
            if (!file2.isFile() || !ResXmlDocument.isResXmlBlock(file2)) {
                logMessage("WARN: Missing file " + str);
                return;
            }
        }
        logMessage("Loaded binary manifest: " + file2.getName());
        FileInputSource fileInputSource = new FileInputSource(file2, AndroidManifest.FILE_NAME);
        ApkModule apkModule = getApkModule();
        apkModule.add(fileInputSource);
        if (isKeepOriginal()) {
            apkModule.discardManifestChanges();
        }
    }

    @Override // com.reandroid.apk.ApkModuleEncoder, com.reandroid.apk.ApkModuleCoder
    public ApkModule getApkModule() {
        return this.apkModule;
    }

    public boolean isKeepOriginal() {
        return this.mKeepOriginal;
    }

    @Override // com.reandroid.apk.ApkModuleEncoder
    public void onScanDirectoryComplete() {
        if (isKeepOriginal()) {
            ApkModule apkModule = getApkModule();
            apkModule.discardTableBlockChanges();
            apkModule.discardManifestChanges();
        }
    }

    @Override // com.reandroid.apk.ApkModuleEncoder
    public void refreshTable() {
        if (isKeepOriginal() || getApkModule().getLoadedTableBlock() == null) {
            return;
        }
        super.refreshTable();
    }

    public void setKeepOriginal(boolean z) {
        this.mKeepOriginal = z;
    }
}
