package com.reandroid.arsc.chunk;

import com.reandroid.arsc.ApkFile;
import com.reandroid.arsc.pool.StringPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MainChunk {
    ApkFile getApkFile();

    StringPool<?> getStringPool();

    TableBlock getTableBlock();

    void setApkFile(ApkFile apkFile);
}
