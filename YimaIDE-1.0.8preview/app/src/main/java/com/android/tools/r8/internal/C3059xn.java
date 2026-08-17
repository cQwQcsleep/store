package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3059xn extends AbstractC1620h0 implements Serializable {
    public final Enum[] b;

    public C3059xn(Enum[] enumArr) {
        this.b = enumArr;
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0
    public final int a() {
        return this.b.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0, java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r5 = (Enum) obj;
        KB.c(r5, "element");
        Enum[] enumArr = this.b;
        int iOrdinal = r5.ordinal();
        KB.c(enumArr, "<this>");
        return ((iOrdinal < 0 || iOrdinal > enumArr.length - 1) ? null : enumArr[iOrdinal]) == r5;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Enum[] enumArr = this.b;
        int length = enumArr.length;
        if (i >= 0 && i < length) {
            return enumArr[i];
        }
        rnd.a("index: ", i, ", size: ", length);
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        KB.c(r4, "element");
        int iOrdinal = r4.ordinal();
        Enum[] enumArr = this.b;
        KB.c(enumArr, "<this>");
        if (((iOrdinal < 0 || iOrdinal > enumArr.length + (-1)) ? null : enumArr[iOrdinal]) == r4) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r2 = (Enum) obj;
        KB.c(r2, "element");
        return indexOf(r2);
    }
}
