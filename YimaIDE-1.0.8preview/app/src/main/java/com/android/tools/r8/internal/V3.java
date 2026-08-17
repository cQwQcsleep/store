package com.android.tools.r8.internal;

import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V3 extends AbstractC1620h0 implements RandomAccess {
    public final /* synthetic */ int[] b;

    public V3(int[] iArr) {
        this.b = iArr;
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0
    public final int a() {
        return this.b.length;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0022 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0024 A[RETURN] */
    @Override // com.android.tools.r8.internal.AbstractC1620h0, java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.b;
        KB.c(iArr, "<this>");
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            if (iIntValue == iArr[i]) {
                if (i >= 0) {
                    return true;
                }
                return false;
            }
            i++;
        }
        i = -1;
        if (i >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return Integer.valueOf(this.b[i]);
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.b;
        KB.c(iArr, "<this>");
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (iIntValue == iArr[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0, java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.b.length == 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.b;
        KB.c(iArr, "<this>");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (iIntValue == iArr[length]) {
                    return length;
                }
                if (i >= 0) {
                    length = i;
                }
            }
        }
        return -1;
    }
}
