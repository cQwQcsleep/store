package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.TableBlock;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkModuleRawDecoder extends ApkModuleDecoder {
    public ApkModuleRawDecoder(ApkModule apkModule) {
        super(apkModule);
        apkModule.setLoadDefaultFramework(false);
    }

    @Override // com.reandroid.apk.ApkModuleDecoder
    public void decodeAndroidManifest(File file) throws IOException {
        ApkModule apkModule = getApkModule();
        apkModule.discardManifestChanges();
        InputSource manifestOriginalSource = apkModule.getManifestOriginalSource();
        if (manifestOriginalSource != null) {
            manifestOriginalSource.write(new File(file, AndroidManifest.FILE_NAME_BIN));
            addDecodedPath(AndroidManifest.FILE_NAME);
        } else {
            logMessage("File NOT found: " + AndroidManifest.FILE_NAME);
        }
    }

    @Override // com.reandroid.apk.ApkModuleDecoder
    public void decodeResourceTable(File file) throws IOException {
        ApkModule apkModule = getApkModule();
        apkModule.discardTableBlockChanges();
        InputSource tableOriginalSource = apkModule.getTableOriginalSource();
        if (tableOriginalSource == null) {
            logMessage("File NOT found: " + TableBlock.FILE_NAME);
        } else {
            String str = TableBlock.FILE_NAME;
            tableOriginalSource.write(new File(file, str));
            addDecodedPath(str);
        }
    }
}
