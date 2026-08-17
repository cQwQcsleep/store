package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.MappingPartition;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NM implements MappingPartition {
    public final String a;
    public final byte[] b;

    public NM(String str, byte[] bArr) {
        this.a = str;
        this.b = bArr;
    }

    @Override // com.android.tools.r8.retrace.MappingPartition
    public final String getKey() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.MappingPartition
    public final byte[] getPayload() {
        return this.b;
    }
}
