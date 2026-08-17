package org.jetbrains.kotlin.incremental.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.IncrementalJvmCacheKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\t\n\u0002\u0010\u0012\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"hashToLong", "", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UtilsKt {
    public static final long hashToLong(byte[] bArr) {
        bArr.getClass();
        return IncrementalJvmCacheKt.md5(bArr);
    }
}
