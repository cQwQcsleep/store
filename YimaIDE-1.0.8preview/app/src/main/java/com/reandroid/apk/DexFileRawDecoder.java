package com.reandroid.apk;

import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DexFileRawDecoder implements DexDecoder {
    private APKLogger apkLogger;

    private void logVerbose(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logVerbose(str);
        }
    }

    @Override // com.reandroid.apk.DexDecoder
    public void decodeDex(DexFileInputSource dexFileInputSource, File file) throws IOException {
        logVerbose(dexFileInputSource.getAlias());
        dexFileInputSource.write(dexFileInputSource.toFile(new File(file, DexDecoder.DEX_DIRECTORY_NAME)));
    }

    public void setApkLogger(APKLogger aPKLogger) {
        this.apkLogger = aPKLogger;
    }
}
