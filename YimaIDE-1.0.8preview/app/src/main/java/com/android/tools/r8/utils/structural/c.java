package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class c extends AbstractC3519a {
    public static final /* synthetic */ boolean a = true;

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(F2 f2, F2 f3) {
        int i;
        f2.getClass();
        boolean z = f2 instanceof I2;
        int i2 = 3;
        if (z) {
            i = 1;
        } else if (f2.s0()) {
            i = 2;
        } else {
            if (!F2.e && !f2.u0()) {
                x1f.a();
                return 0;
            }
            i = 3;
        }
        f3.getClass();
        if (f3 instanceof I2) {
            i2 = 1;
        } else if (f3.s0()) {
            i2 = 2;
        } else if (!F2.e && !f3.u0()) {
            x1f.a();
            return 0;
        }
        int iCompare = Integer.compare(i, i2);
        if (iCompare != 0) {
            return iCompare;
        }
        if (!a && f2.getClass() != f3.getClass()) {
            x1f.a();
            return 0;
        }
        if (z) {
            return a(f2.r0(), f3.r0());
        }
        return f2.s0() ? a(f2.o0(), f3.o0()) : a(f2.q0(), f3.q0());
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(int i, int i2) {
        return Integer.compare(i, i2);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(long j, long j2) {
        return Long.compare(j, j2);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(float f, float f2) {
        return Float.compare(f, f2);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(double d, double d2) {
        return Double.compare(d, d2);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(Iterator it, Iterator it2, v vVar) {
        int iA = 0;
        while (iA == 0 && it.hasNext() && it2.hasNext()) {
            iA = vVar.a(it.next(), it2.next(), this);
        }
        return iA == 0 ? Boolean.compare(it.hasNext(), it2.hasNext()) : iA;
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public int a(H2 h2, H2 h3) {
        return h2.a(h3);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(boolean z, boolean z2) {
        return Boolean.compare(z, z2);
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(Object obj, Object obj2, y yVar) {
        b bVar = new b(obj, obj2, this);
        yVar.a(bVar);
        return bVar.d;
    }
}
