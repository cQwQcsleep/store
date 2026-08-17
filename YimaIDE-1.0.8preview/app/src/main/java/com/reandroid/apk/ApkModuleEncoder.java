package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.ArchiveInfo;
import com.reandroid.archive.FileInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.archive.block.ApkSignatureBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.ResXmlElement;
import com.reandroid.json.JSONArray;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ApkModuleEncoder extends ApkModuleCoder {
    private DexEncoder mDexEncoder;
    private DexProfileEncoder mDexProfileEncoder;

    private void dropEmptyManifest() {
        ApkModule apkModule = getApkModule();
        if (apkModule.hasAndroidManifest()) {
            ResXmlElement documentElement = apkModule.getAndroidManifest().getDocumentElement();
            if (documentElement.equalsName(AndroidManifest.EMPTY_MANIFEST_TAG) && documentElement.getElementsCount() == 0) {
                apkModule.setManifest(null);
                logMessage("Removed empty: " + AndroidManifest.FILE_NAME);
            }
        }
    }

    private void dropNullTableBlock() {
        ApkModule apkModule = getApkModule();
        if (apkModule.hasTableBlock()) {
            TableBlock loadedTableBlock = apkModule.getLoadedTableBlock();
            TableBlock tableBlock = loadedTableBlock == null ? apkModule.getTableBlock(false) : loadedTableBlock;
            if (!tableBlock.isEmpty() || !tableBlock.isNull()) {
                if (loadedTableBlock == null) {
                    apkModule.discardTableBlockChanges();
                }
            } else {
                apkModule.setTableBlock(null);
                logMessage("Removed empty: " + TableBlock.FILE_NAME);
            }
        }
    }

    private void restorePathMap(File file) throws IOException {
        File file2 = new File(file, PathMap.JSON_FILE);
        if (file2.isFile()) {
            logMessage("Restoring original file paths ...");
            PathMap pathMap = new PathMap();
            pathMap.fromJson(new JSONArray(file2));
            pathMap.restore(getApkModule());
        }
    }

    private void restoreSignatures(File file) throws IOException {
        File file2 = new File(file, "signatures");
        if (file2.isDirectory()) {
            logMessage("Loading signatures ...");
            ApkModule apkModule = getApkModule();
            ApkSignatureBlock apkSignatureBlock = new ApkSignatureBlock();
            apkSignatureBlock.scanSplitFiles(file2);
            apkModule.setApkSignatureBlock(apkSignatureBlock);
        }
    }

    private void scanRootDir(File file) {
        logMessage("Scanning root directory ...");
        File file2 = new File(file, "root");
        ZipEntryMap zipEntryMap = getApkModule().getZipEntryMap();
        for (File file3 : ApkUtil.recursiveFiles(file2)) {
            zipEntryMap.add(new FileInputSource(file3, ApkUtil.toArchivePath(file2, file3)));
        }
    }

    private void sortFiles() {
        logMessage("Sorting files ...");
        getApkModule().getZipEntryMap().autoSortApkFiles();
    }

    public abstract void buildResources(File file) throws IOException;

    public void encodeBinaryManifest(File file) {
        File file2 = new File(file, AndroidManifest.FILE_NAME_BIN);
        if (file2.isFile()) {
            logMessage("Using binary xml: " + file2.getName());
            getApkModule().add(new FileInputSource(file2, AndroidManifest.FILE_NAME));
        }
    }

    public void encodeDexFiles(File file) throws IOException {
        logMessage("Building dex ...");
        List<InputSource> listBuildDexFiles = getRawDexEncoder().buildDexFiles(this, file);
        ApkModule apkModule = getApkModule();
        apkModule.addAll(listBuildDexFiles);
        DexEncoder dexEncoder = getDexEncoder();
        if (dexEncoder != null) {
            apkModule.addAll(dexEncoder.buildDexFiles(this, file));
        }
    }

    public void encodeDexProfile(File file) throws IOException {
        DexProfileEncoder dexProfileEncoder = getDexProfileEncoder();
        if (dexProfileEncoder != null) {
            dexProfileEncoder.encodeDexProfile(file);
        }
    }

    @Override // com.reandroid.apk.ApkModuleCoder
    public abstract ApkModule getApkModule();

    public DexEncoder getDexEncoder() {
        return this.mDexEncoder;
    }

    public DexProfileEncoder getDexProfileEncoder() {
        return this.mDexProfileEncoder;
    }

    public DexEncoder getRawDexEncoder() {
        DexFileRawEncoder dexFileRawEncoder = new DexFileRawEncoder();
        dexFileRawEncoder.setApkLogger(getApkLogger());
        return dexFileRawEncoder;
    }

    public void loadArchiveInfo(File file) throws IOException {
        getApkModule().getZipEntryMap().setArchiveInfo(ArchiveInfo.readJson(file));
    }

    public void loadUncompressedFiles(File file) throws IOException {
        getApkModule().getUncompressedFiles().fromJson(new File(file, UncompressedFiles.JSON_FILE));
    }

    public void onScanDirectoryComplete() {
    }

    public void refreshTable() {
        logMessage("Refreshing resource table ...");
        getApkModule().refreshTable();
        logMessage(getApkModule().getTableBlock().toString());
    }

    public void scanDirectory(File file) throws IOException {
        logMessage("Scanning: " + file.getName());
        encodeBinaryManifest(file);
        loadArchiveInfo(file);
        loadUncompressedFiles(file);
        buildResources(file);
        encodeDexFiles(file);
        encodeDexProfile(file);
        scanRootDir(file);
        restorePathMap(file);
        restoreSignatures(file);
        sortFiles();
        refreshTable();
        dropEmptyManifest();
        dropNullTableBlock();
        onScanDirectoryComplete();
    }

    public void setDexEncoder(DexEncoder dexEncoder) {
        this.mDexEncoder = dexEncoder;
    }

    public void setDexProfileEncoder(DexProfileEncoder dexProfileEncoder) {
        this.mDexProfileEncoder = dexProfileEncoder;
    }
}
