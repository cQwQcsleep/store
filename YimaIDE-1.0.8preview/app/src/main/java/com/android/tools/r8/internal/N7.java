package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N7 extends Q7 {
    public final int f;
    public final int g;

    public N7(byte[] bArr, int i, int i2) {
        super(bArr);
        U7.a(i, i + i2, bArr.length);
        this.f = i;
        this.g = i2;
    }

    @Override // com.android.tools.r8.internal.Q7
    public final int d() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.Q7, com.android.tools.r8.internal.U7
    public final byte j(int i) {
        int i2 = this.g;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.e[this.f + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(CX.a(i, "Index < 0: "));
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }

    @Override // com.android.tools.r8.internal.Q7, com.android.tools.r8.internal.U7
    public final byte k(int i) {
        return this.e[this.f + i];
    }

    @Override // com.android.tools.r8.internal.Q7, com.android.tools.r8.internal.U7
    public final int size() {
        return this.g;
    }
}
