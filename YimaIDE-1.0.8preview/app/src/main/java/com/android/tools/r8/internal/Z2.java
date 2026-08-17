package com.android.tools.r8.internal;

import com.android.tools.r8.ByteDataView;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z2 implements Comparable {
    public final String b;
    public final ByteDataView c;
    public final boolean d;
    public final boolean e;

    public Z2(String str, ByteDataView byteDataView, boolean z, boolean z2) {
        this.b = str;
        this.c = byteDataView;
        this.d = z;
        this.e = z2;
    }

    public static Z2 a(String str, ByteDataView byteDataView) {
        return new Z2(str, byteDataView, false, true);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        Z2 z2 = (Z2) obj;
        String str = this.b;
        return z2 == null ? str.compareTo((String) null) : str.compareTo(z2.b);
    }
}
