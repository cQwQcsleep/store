package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1461f6 {
    public final int[] a;
    public final int b;
    public final int c;
    public final int d;
    public final List e;

    public AbstractC1461f6(int... iArr) {
        List listC;
        KB.c(iArr, "numbers");
        this.a = iArr;
        Integer numA = T3.a(iArr, 0);
        this.b = numA != null ? numA.intValue() : -1;
        Integer numA2 = T3.a(iArr, 1);
        this.c = numA2 != null ? numA2.intValue() : -1;
        Integer numA3 = T3.a(iArr, 2);
        this.d = numA3 != null ? numA3.intValue() : -1;
        if (iArr.length <= 3) {
            listC = C0984Ym.b;
        } else {
            if (iArr.length > 1024) {
                throw new IllegalArgumentException("BinaryVersion with length more than 1024 are not supported. Provided length " + iArr.length + '.');
            }
            listC = AbstractC1760ie.c(new V3(iArr).subList(3, iArr.length));
        }
        this.e = listC;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        AbstractC1461f6 abstractC1461f6 = (AbstractC1461f6) obj;
        return this.b == abstractC1461f6.b && this.c == abstractC1461f6.c && this.d == abstractC1461f6.d && KB.a(this.e, abstractC1461f6.e);
    }

    public final int hashCode() {
        int i = this.b;
        int i2 = (i * 31) + this.c + i;
        int i3 = (i2 * 31) + this.d + i2;
        return this.e.hashCode() + (i3 * 31) + i3;
    }

    public final String toString() {
        int[] iArr = this.a;
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i == -1) {
                break;
            }
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList.isEmpty() ? "unknown" : AbstractC1760ie.a(arrayList, ".", null, null, null, 62);
    }
}
