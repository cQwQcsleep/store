package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkModuleJsonDecoder extends ApkModuleDecoder {
    private final boolean splitTypes;

    public ApkModuleJsonDecoder(ApkModule apkModule, boolean z) {
        super(apkModule);
        this.splitTypes = z;
    }

    private void decodeResFile(File file, ResFile resFile) throws IOException {
        if (resFile.isBinaryXml()) {
            decodeResFileXml(file, resFile);
        }
    }

    private void decodeResFileXml(File file, ResFile resFile) throws IOException {
        InputSource inputSource = resFile.getInputSource();
        String alias = inputSource.getAlias();
        logVerbose(alias);
        File resJson = toResJson(file, alias);
        ResXmlDocument resXmlDocument = new ResXmlDocument();
        resXmlDocument.readBytes(inputSource.openStream());
        resXmlDocument.toJson().write(resJson);
        addDecodedPath(alias);
    }

    private void decodeResFiles(File file) throws IOException {
        ApkModule apkModule = getApkModule();
        logMessage("Decoding res files ...");
        Iterator<ResFile> it = apkModule.listResFiles().iterator();
        while (it.hasNext()) {
            decodeResFile(file, it.next());
        }
    }

    private void decodeTable(File file) throws IOException {
        if (this.splitTypes) {
            writeTableSplit(file);
        } else {
            writeTableSingle(file);
        }
    }

    private File toResJson(File file, String str) {
        return new File(new File(file, TableBlock.RES_JSON_DIRECTORY_NAME), (str + ApkUtil.JSON_FILE_EXTENSION).replace('/', File.separatorChar));
    }

    private void writeTableSingle(File file) throws IOException {
        ApkModule apkModule = getApkModule();
        if (apkModule.hasTableBlock()) {
            TableBlock tableBlock = apkModule.getTableBlock();
            tableBlock.toJson().write(new File(new File(file, TableBlock.DIRECTORY_NAME), TableBlock.FILE_NAME_JSON));
            addDecodedPath(TableBlock.FILE_NAME);
        }
    }

    private void writeTableSplit(File file) throws IOException {
        ApkModule apkModule = getApkModule();
        if (apkModule.hasTableBlock()) {
            TableBlock tableBlock = apkModule.getTableBlock();
            new SplitJsonResourceDecoder(tableBlock).decodeSplitJsonFiles(new File(file, TableBlock.DIRECTORY_NAME));
            addDecodedPath(TableBlock.FILE_NAME);
        }
    }

    @Override // com.reandroid.apk.ApkModuleDecoder
    public void decodeAndroidManifest(File file) throws IOException {
        ApkModule apkModule = getApkModule();
        if (apkModule.hasAndroidManifest()) {
            AndroidManifestBlock androidManifest = apkModule.getAndroidManifest();
            androidManifest.toJson().write(new File(file, AndroidManifest.FILE_NAME_JSON));
            addDecodedPath(AndroidManifest.FILE_NAME);
        }
    }

    @Override // com.reandroid.apk.ApkModuleDecoder
    public void decodeResourceTable(File file) throws IOException {
        decodeTable(file);
        decodeResFiles(file);
    }

    public ApkModuleJsonDecoder(ApkModule apkModule) {
        this(apkModule, false);
    }
}
