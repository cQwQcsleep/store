package com.reandroid.apk;

import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface DexProfileDecoder {
    void decodeDexProfile(ApkModule apkModule, File file) throws IOException;
}
