package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ug0 implements Vg0 {
    public final /* synthetic */ byte[] a;

    public Ug0(byte[] bArr) {
        this.a = bArr;
    }

    @Override // com.android.tools.r8.internal.Vg0
    public final byte a(int i) {
        return this.a[i];
    }

    @Override // com.android.tools.r8.internal.Vg0
    public final int size() {
        return this.a.length;
    }
}
