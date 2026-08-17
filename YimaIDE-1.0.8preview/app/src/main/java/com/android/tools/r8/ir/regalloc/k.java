package com.android.tools.r8.ir.regalloc;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C2543rl0;
import defpackage.gk0;
import java.util.Arrays;
import java.util.BitSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class k extends j {
    public static final /* synthetic */ boolean g = true;
    public final int a;
    public int[] b = new int[16];
    public final BitSet c;
    public final BitSet d;
    public final BitSet e;
    public final BitSet f;

    public k(int i) {
        this.a = i;
        for (int i2 = 0; i2 < 16; i2++) {
            this.b[i2] = Integer.MAX_VALUE;
        }
        this.c = new BitSet(i);
        this.d = new BitSet(i);
        this.e = new BitSet(i);
        this.f = new BitSet(i);
    }

    public final void a(int i, int i2, c cVar) {
        int[] iArr = this.b;
        if (i >= iArr.length) {
            int i3 = i + 1;
            int length = iArr.length;
            while (length < i3) {
                length *= 2;
            }
            int iMin = Math.min(length, this.a);
            int[] iArr2 = this.b;
            this.b = Arrays.copyOf(iArr2, iMin);
            for (int length2 = iArr2.length; length2 < iMin; length2++) {
                this.b[length2] = Integer.MAX_VALUE;
            }
        }
        this.b[i] = i2;
        BitSet bitSet = this.c;
        C2543rl0 c2543rl0 = cVar.b;
        boolean z = false;
        bitSet.set(i, c2543rl0.c != null && c2543rl0.H());
        this.d.set(i, cVar.o);
        BitSet bitSet2 = this.e;
        AbstractC0890Uw abstractC0890Uw = cVar.b.c;
        if (abstractC0890Uw != null && abstractC0890Uw.o2() && !cVar.b.c.t0().j) {
            z = true;
        }
        bitSet2.set(i, z);
    }

    @Override // com.android.tools.r8.ir.regalloc.j
    public final boolean b(int i) {
        return this.f.get(i);
    }

    @Override // com.android.tools.r8.ir.regalloc.j
    public final boolean a(int i, i iVar) {
        if (!g && this.f.get(i)) {
            x1f.a();
            return false;
        }
        int iOrdinal = iVar.ordinal();
        if (iOrdinal == 0) {
            return this.d.get(i);
        }
        if (iOrdinal == 1) {
            return this.c.get(i);
        }
        if (iOrdinal == 2) {
            return (this.d.get(i) || this.c.get(i) || this.e.get(i)) ? false : true;
        }
        if (iOrdinal == 3) {
            return true;
        }
        gk0.a("Unexpected register position type: ", iVar);
        return false;
    }

    @Override // com.android.tools.r8.ir.regalloc.j
    public final int a(int i) {
        boolean z = g;
        if (!z && this.f.get(i)) {
            x1f.a();
            return 0;
        }
        int[] iArr = this.b;
        if (i < iArr.length) {
            return iArr[i];
        }
        if (z || i < this.a) {
            return Integer.MAX_VALUE;
        }
        x1f.a();
        return 0;
    }
}
