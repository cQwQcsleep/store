package com.reandroid.arsc.chunk;

import com.reandroid.arsc.pool.StringPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ParentChunk {
    MainChunk getMainChunk();

    PackageBlock getPackageBlock();

    /* JADX INFO: renamed from: getSpecStringPool */
    StringPool<?> mo35getSpecStringPool();
}
