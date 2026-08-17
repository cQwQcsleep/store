package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3052xj0 {
    public final byte[] a;
    public final int b;

    public C3052xj0(int i, byte[] bArr) {
        this.a = bArr;
        this.b = i;
    }

    public final String toString() {
        byte b = this.a[this.b];
        StringBuilder sb = new StringBuilder(b * 2);
        for (int i = 0; i < b; i++) {
            byte[] bArr = this.a;
            int i2 = (i * 2) + this.b;
            byte b2 = bArr[i2 + 1];
            if (b2 == 0) {
                sb.append('[');
            } else if (b2 == 1) {
                sb.append('.');
            } else if (b2 == 2) {
                sb.append('*');
            } else {
                if (b2 != 3) {
                    x1f.a();
                    return null;
                }
                sb.append((int) bArr[i2 + 2]);
                sb.append(';');
            }
        }
        return sb.toString();
    }
}
