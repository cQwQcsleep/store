package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.utils.structural.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3519a {
    public abstract int a(double d, double d2);

    public abstract int a(float f, float f2);

    public abstract int a(int i, int i2);

    public abstract int a(long j, long j2);

    public abstract int a(F2 f2, F2 f3);

    public abstract int a(H2 h2, H2 h3);

    public abstract int a(I2 i2, I2 i3);

    public abstract <S> int a(S s, S s2, y<S> yVar);

    public final int a(Collection collection, Collection collection2) {
        return a(collection.iterator(), collection2.iterator(), new defpackage.e());
    }

    public abstract int a(Iterator it, Iterator it2, v vVar);

    public abstract int a(boolean z, boolean z2);

    public final int a(x[] xVarArr, x[] xVarArr2) {
        return a(Arrays.asList(xVarArr), Arrays.asList(xVarArr2));
    }

    public int a(C0245l1 c0245l1, C0245l1 c0245l2) {
        return a(c0245l1, c0245l2, (y<C0245l1>) c0245l1.o());
    }

    public int a(C0322w2 c0322w2, C0322w2 c0322w3) {
        return a(c0322w2, c0322w3, (y<C0322w2>) c0322w2.o());
    }
}
