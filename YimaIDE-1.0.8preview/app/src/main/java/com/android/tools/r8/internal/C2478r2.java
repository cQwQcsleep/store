package com.android.tools.r8.internal;

import java.util.function.BiPredicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2478r2 extends AbstractC2735u2 {
    public final byte[] c;

    public C2478r2(byte[] bArr) {
        this.c = bArr;
    }

    @Override // com.android.tools.r8.internal.AbstractC2735u2
    public final int a(int i, int i2, byte[] bArr, BiPredicate biPredicate) {
        if (this.c.length < i2) {
            return -1;
        }
        for (int i3 = i; i3 < i + i2; i3 += 2) {
            byte[] bArr2 = this.c;
            int iA = MB.a((byte) 0, (byte) 0, bArr2[i3], bArr2[i3 + 1]);
            if (biPredicate.test(Integer.valueOf(iA), bArr)) {
                return iA;
            }
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2735u2
    public final byte b(byte[] bArr, int i, int i2) {
        int i3 = i;
        while (i3 < i + i2) {
            byte[] bArr2 = this.c;
            int iA = MB.a((byte) 0, (byte) 0, bArr2[i3], bArr2[i3 + 1]);
            int i4 = i3 + 2;
            int i5 = i4 + iA;
            if (a(bArr, i4, iA)) {
                return this.c[i5];
            }
            i3 = i5 + 1;
        }
        return (byte) 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2735u2
    public final C2649t2 c(int i) {
        byte[] bArr = this.c;
        C2649t2 c2649t2 = C2649t2.c;
        int iA = MB.a(bArr[i], bArr[i + 1], bArr[i + 2], bArr[i + 3]);
        int iA2 = MB.a((byte) 0, (byte) 0, bArr[i + 4], bArr[i + 5]);
        if (iA == 0 && iA2 == 0) {
            return C2649t2.c;
        }
        if ((iA >= 0 || iA2 <= 0) && (iA <= 0 || iA2 != 0)) {
            return new C2649t2(iA, iA2);
        }
        if (C2649t2.d) {
            return C2649t2.c;
        }
        x01.a("Unexpected position and length");
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2735u2
    public final int d() {
        byte[] bArr = this.c;
        return MB.a(bArr[0], bArr[1], bArr[2], bArr[3]);
    }

    @Override // com.android.tools.r8.internal.AbstractC2735u2
    public final boolean a(byte[] bArr, int i, int i2) {
        if (bArr.length != i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (bArr[i3] != this.c[i3 + i]) {
                return false;
            }
        }
        return true;
    }
}
