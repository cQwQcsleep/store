package com.reandroid.apk;

import com.reandroid.apk.xmlencoder.XMLEncodeSource;
import com.reandroid.apk.xmlencoder.XMLTableBlockEncoder;
import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.Archive;
import com.reandroid.archive.FileInputSource;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.value.Entry;
import com.reandroid.xml.source.XMLFileParserSource;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkModuleXmlEncoder extends ApkModuleEncoder {
    private final XMLTableBlockEncoder tableBlockEncoder;

    public ApkModuleXmlEncoder() {
        this.tableBlockEncoder = new XMLTableBlockEncoder();
    }

    private void buildTableBlock(File file) throws IOException {
        this.tableBlockEncoder.scanMainDirectory(file);
    }

    private void encodeManifestBinary(File file) {
        String str = AndroidManifest.FILE_NAME_BIN;
        File file2 = new File(file, str);
        if (file2.isFile()) {
            logMessage("Encode binary manifest: " + file2.getName());
            FileInputSource fileInputSource = new FileInputSource(file2, str);
            fileInputSource.setMethod(Archive.STORED);
            fileInputSource.setSort(0);
            getApkModule().add(fileInputSource);
        }
    }

    private void encodeManifestXml(File file) {
        if (file == null) {
            return;
        }
        String str = AndroidManifest.FILE_NAME;
        File file2 = new File(file, str);
        if (file2.isFile()) {
            logMessage("Add manifest: " + file2.getName());
            TableBlock tableBlock = getApkModule().getTableBlock();
            Integer mainPackageId = this.tableBlockEncoder.getMainPackageId();
            PackageBlock packageBlockPickOne = mainPackageId != null ? tableBlock.pickOne(mainPackageId.intValue()) : tableBlock.pickOne();
            if (packageBlockPickOne != null) {
                tableBlock.setCurrentPackage(packageBlockPickOne);
            }
            XMLEncodeSource xMLEncodeSource = new XMLEncodeSource(tableBlock.pickOne(), new XMLFileParserSource(str, file2));
            xMLEncodeSource.setApkLogger(getApkLogger());
            xMLEncodeSource.setMethod(Archive.STORED);
            xMLEncodeSource.setSort(0);
            getApkModule().add(xMLEncodeSource);
        }
    }

    private void encodeResFile(File file, File file2) {
        String archivePath = ApkUtil.toArchivePath(file, file2);
        logVerbose(archivePath);
        Entry entry = getEntry(archivePath);
        if (entry == null) {
            logMessage("Un registered file: " + file2);
        } else if (!file2.getName().endsWith(".xml")) {
            getApkModule().add(new FileInputSource(file2, archivePath));
        } else {
            XMLEncodeSource xMLEncodeSource = new XMLEncodeSource(entry.getPackageBlock(), new XMLFileParserSource(archivePath, file2));
            xMLEncodeSource.setApkLogger(getApkLogger());
            getApkModule().add(xMLEncodeSource);
        }
    }

    private Entry getEntry(String str) {
        List<Entry> listListReferencedEntries = getApkModule().listReferencedEntries(str);
        if (listListReferencedEntries.size() > 0) {
            return listListReferencedEntries.get(0);
        }
        return null;
    }

    private void scanResFilesDirectory(File file) {
        File file2 = new File(file, TableBlock.RES_FILES_DIRECTORY_NAME);
        if (file2.isDirectory()) {
            logMessage("Searching files: " + file2.getName());
            Iterator<File> it = ApkUtil.recursiveFiles(file2).iterator();
            while (it.hasNext()) {
                encodeResFile(file2, it.next());
            }
        }
    }

    @Override // com.reandroid.apk.ApkModuleEncoder
    public void buildResources(File file) throws IOException {
        encodeManifestBinary(file);
        buildTableBlock(file);
        encodeManifestXml(file);
        scanResFilesDirectory(file);
    }

    @Override // com.reandroid.apk.ApkModuleEncoder, com.reandroid.apk.ApkModuleCoder
    public ApkModule getApkModule() {
        return this.tableBlockEncoder.getApkModule();
    }

    @Override // com.reandroid.apk.ApkModuleCoder
    public void setApkLogger(APKLogger aPKLogger) {
        super.setApkLogger(aPKLogger);
        this.tableBlockEncoder.setApkLogger(aPKLogger);
    }

    public ApkModuleXmlEncoder(ApkModule apkModule, TableBlock tableBlock) {
        this.tableBlockEncoder = new XMLTableBlockEncoder(apkModule, tableBlock);
    }
}
