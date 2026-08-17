package com.android.tools.r8;

import com.android.tools.r8.internal.C2742u50;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class e0 extends d0 {
    public final byte[] c;

    public e0(AndroidResourceInput androidResourceInput, C2742u50 c2742u50, byte[] bArr) {
        super(androidResourceInput, c2742u50);
        this.c = bArr;
    }

    @Override // com.android.tools.r8.AndroidResourceOutput
    public final ByteDataView getByteDataView() {
        return ByteDataView.of(this.c);
    }
}
