package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1663hX extends AbstractC2004lX.a {
    public static final /* synthetic */ boolean k = true;
    public final C0841Sz h;
    public C0322w2 i;
    public boolean j;

    public C1663hX() {
        boolean z = C0867Tz.d;
        this.h = new C0841Sz();
    }

    public final C1663hX a(int i, AbstractC2004lX abstractC2004lX) {
        C0841Sz c0841Sz = this.h;
        c0841Sz.a.add(Integer.valueOf(i));
        c0841Sz.b.a(abstractC2004lX);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX.a
    public final AbstractC2004lX.a c() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final C1749iX a() {
        boolean z = k;
        if (!z && !this.f && this.a < 0) {
            x1f.a();
            return null;
        }
        if (!z && !this.g && this.b == null) {
            x1f.a();
            return null;
        }
        int i = this.a;
        C0322w2 c0322w2 = this.b;
        AbstractC2004lX abstractC2004lX = this.c;
        boolean z2 = this.d;
        boolean z3 = this.e;
        C0841Sz c0841Sz = this.h;
        c0841Sz.getClass();
        Object[] array = c0841Sz.a.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = array[i2];
            obj.getClass();
            iArr[i2] = ((Number) obj).intValue();
        }
        return new C1749iX(i, c0322w2, abstractC2004lX, z2, z3, new C0867Tz(iArr, c0841Sz.b.a()), this.i, this.j);
    }
}
