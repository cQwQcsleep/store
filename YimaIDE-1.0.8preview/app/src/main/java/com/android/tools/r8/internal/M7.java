package com.android.tools.r8.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M7 implements O7 {
    @Override // com.android.tools.r8.internal.O7
    public final byte[] a(byte[] bArr, int i, int i2) {
        return Arrays.copyOfRange(bArr, i, i2 + i);
    }
}
