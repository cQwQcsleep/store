package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.C0290r5;
import java.nio.ShortBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class B1 extends N {
    public AbstractC0138z1[] a(ShortBuffer shortBuffer, int i, int i2, C0290r5 c0290r5) {
        A1 a1 = new A1(i, i2, shortBuffer);
        ArrayList arrayList = new ArrayList(i2);
        while (a1.a - a1.d > 0) {
            arrayList.add(N.a(a1.a(), a1.a(), a1, c0290r5));
        }
        return (AbstractC0138z1[]) arrayList.toArray(AbstractC0138z1.c);
    }
}
