package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FA extends AbstractC2618sg0 {
    public static final /* synthetic */ boolean n = true;
    public final int[] m;

    public FA(C2543rl0 c2543rl0, int[] iArr, int[] iArr2, int i) {
        super(c2543rl0, iArr2, i);
        this.m = iArr;
        boolean z = n;
        if (z) {
            return;
        }
        if (!z) {
            for (int i2 = 0; i2 < L2(); i2++) {
                if (!AbstractC2618sg0.l && this.j[i2] == this.k) {
                    x1f.a();
                    throw null;
                }
            }
        }
        int i3 = 1;
        if (!z && iArr.length < 1) {
            x1f.a();
            throw null;
        }
        if (!z && iArr.length > 65535) {
            x1f.a();
            throw null;
        }
        if (!z && iArr.length != L2()) {
            x1f.a();
            throw null;
        }
        while (true) {
            int[] iArr3 = this.m;
            if (i3 >= iArr3.length) {
                return;
            }
            if (!n && iArr3[i3 - 1] >= iArr3[i3]) {
                x1f.a();
                throw null;
            }
            i3++;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 31;
    }

    public final InterfaceC0763Pz M2() {
        C0944Wy c0944Wy = new C0944Wy();
        int i = 0;
        while (true) {
            int[] iArr = this.m;
            if (i >= iArr.length) {
                return c0944Wy;
            }
            c0944Wy.a(iArr[i], b(i));
            i++;
        }
    }

    public int[] N2() {
        return this.m;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean R1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final FA W() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        K9 k9A = j8.a(K2());
        ArrayList arrayList = new ArrayList(L2());
        List<H5> listT = i().t();
        int i = 0;
        if (!a(AB.d)) {
            int[] iArr = this.j;
            int length = iArr.length;
            while (i < length) {
                arrayList.add(j8.a(listT.get(iArr[i])));
                i++;
            }
            j8.a(new C0920Wa(C0920Wa.a.b, k9A, this.m, arrayList), this);
            return;
        }
        int[] iArr2 = this.m;
        int i2 = iArr2[0];
        int i3 = iArr2[iArr2.length - 1];
        for (long j = i2; j <= i3; j++) {
            if (j == this.m[i]) {
                arrayList.add(j8.a(listT.get(this.j[i])));
                i++;
            } else {
                arrayList.add(k9A);
            }
        }
        if (n || i == L2()) {
            j8.a(new C0920Wa(C0920Wa.a.c, k9A, new int[]{i2}, arrayList), this);
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator());
        for (int i = 0; i < L2(); i++) {
            sb.append("          ");
            sb.append(this.m[i]);
            sb.append(" -> ");
            sb.append(b(i).p());
            sb.append(System.lineSeparator());
        }
        sb.append("          F -> ");
        sb.append(K2().o());
        return sb.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        int iA = c2884vl.d.a((C2543rl0) this.c.get(0), this.e);
        if (a(AB.b)) {
            com.android.tools.r8.dex.code.Y2 y2 = new com.android.tools.r8.dex.code.Y2(iA);
            if (!C2884vl.r && c2884vl.q != K2()) {
                x1f.a();
                return;
            } else {
                c2884vl.g.add(new C2713tl(this, y2));
                c2884vl.a(this, y2);
                return;
            }
        }
        com.android.tools.r8.dex.code.K3 k3 = new com.android.tools.r8.dex.code.K3(iA);
        if (!C2884vl.r && c2884vl.q != K2()) {
            x1f.a();
        } else {
            c2884vl.g.add(new C2713tl(this, k3));
            c2884vl.a(this, k3);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        C2543rl0 c2543rl0 = (C2543rl0) this.c.get(0);
        int[] iArrN2 = N2();
        InterfaceC0763Pz interfaceC0763PzM2 = M2();
        K2();
        lk.getClass();
        int[] iArr = new int[iArrN2.length];
        for (int i = 0; i < iArrN2.length; i++) {
            iArr[i] = lk.h.a((H5) ((C0944Wy) interfaceC0763PzM2).get(iArrN2[i]));
        }
        lk.a(170, Collections.singletonList(new HK(iArrN2, iArr)), Collections.singletonList(c2543rl0));
    }

    public static long a(int[] iArr) {
        return (((long) iArr[iArr.length - 1]) - ((long) iArr[0])) + 1;
    }

    public static boolean a(AB ab, long j) {
        return j <= (ab.a() ? 4294967295L : 65535L);
    }

    public static long a(AB ab, int[] iArr) {
        long j;
        long j2;
        long length = iArr.length;
        long j3 = (ab.a() ? length * 8 : (length * 4) + 2) + ((long) (ab.a() ? 12 : 3));
        if (a(ab, a(iArr))) {
            boolean z = n;
            if (!z && !a(ab, a(iArr))) {
                x1f.a();
                return 0L;
            }
            long jA = a(iArr);
            if (!ab.a()) {
                j2 = (jA * 2) + 4;
            } else {
                if (!z && jA > 4294967295L) {
                    x1f.a();
                    return 0L;
                }
                j2 = jA * 4;
            }
            j = ((long) (ab.a() ? 16 : 3)) + j2;
            if (j < j2) {
                j = 2147483647L;
            }
        } else {
            j = Long.MAX_VALUE;
        }
        return Math.min(j3, j);
    }

    public final boolean a(AB ab) {
        long j;
        if (a(ab, a(this.m))) {
            int[] iArr = this.m;
            boolean z = n;
            if (!z && !a(ab, a(iArr))) {
                x1f.a();
                return false;
            }
            long jA = a(iArr);
            if (!ab.a()) {
                j = (jA * 2) + 4;
            } else {
                if (!z && jA > 4294967295L) {
                    x1f.a();
                    return false;
                }
                j = jA * 4;
            }
            long length = this.m.length;
            if (j <= (ab.a() ? length * 8 : (length * 4) + 2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }
}
