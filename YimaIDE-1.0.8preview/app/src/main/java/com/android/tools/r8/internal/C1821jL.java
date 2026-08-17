package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import defpackage.x0g;
import java.util.function.Function;
import java.util.function.IntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1821jL extends WK {
    public static final /* synthetic */ boolean f = true;
    public final C2543rl0[] d;
    public final int e;

    public C1821jL(SK sk, AS as) {
        int iJ;
        super(as);
        this.d = new C2543rl0[sk.i + sk.k];
        C1056aL it = sk.iterator();
        while (true) {
            if (!it.hasNext()) {
                iJ = -1;
                break;
            }
            ZK next = it.next();
            if (next.b() == 210) {
                iJ = next.j() + sk.i;
                break;
            }
        }
        this.e = iJ;
        int length = this.d.length;
        if (this.a) {
            for (int i = 0; i < length; i++) {
                this.b.a();
            }
        }
    }

    @Override // com.android.tools.r8.internal.WK
    public final PW a(int i, IntFunction intFunction, C1720i7 c1720i7, Function function, AbstractC2420qL abstractC2420qL) {
        C2078mL c2078mL = (C2078mL) abstractC2420qL;
        int i2 = this.e;
        int i3 = 0;
        while (true) {
            int[] iArr = c2078mL.a;
            if (i3 >= iArr.length) {
                x0g.a("Unexpected fall off the end of the phi table");
                return null;
            }
            boolean z = f;
            if (!z && i2 > i) {
                x1f.a();
                return null;
            }
            int i4 = iArr[i3];
            int i5 = iArr[i3 + 1];
            if (!z && i5 <= 0) {
                x1f.a();
                return null;
            }
            int i6 = i5 + i2;
            if (i < i6) {
                int i7 = i - i2;
                if (i4 >= 32768) {
                    throw new C1345dk0("No support for more than 15-bit block index.");
                }
                boolean z2 = W7.a;
                if (!z2 && !W7.b(i4)) {
                    x1f.a();
                    return null;
                }
                int i8 = (i4 & 65535) << 16;
                if (!z2 && !W7.b(i7)) {
                    x1f.a();
                    return null;
                }
                int i9 = (i7 & 65535) | Integer.MIN_VALUE | i8;
                boolean z3 = C2249oL.b;
                if (!z3 && i9 >= 0) {
                    x1f.a();
                    return null;
                }
                C2249oL c2249oL = new C2249oL(i9);
                if (!z3 && !c2249oL.a()) {
                    x1f.a();
                    return null;
                }
                PW pw = new PW(a(i), (H5) intFunction.apply((i9 & Integer.MAX_VALUE) >> 16), c1720i7, (C0230j0) function.apply(c2249oL), PW.a.b);
                C2543rl0 c2543rl0 = this.d[i];
                if (c2543rl0 != null) {
                    if (!z && c2543rl0.j()) {
                        x1f.a();
                        return null;
                    }
                    c2543rl0.f(pw);
                }
                this.d[i] = pw;
                return pw;
            }
            i3 += 2;
            i2 = i6;
        }
    }

    @Override // com.android.tools.r8.internal.WK
    public final C2543rl0 a(int i, AbstractC2624sj0 abstractC2624sj0) {
        return new C2543rl0(i, abstractC2624sj0, null);
    }

    @Override // com.android.tools.r8.internal.WK
    public final C2543rl0 a(int i, AbstractC2624sj0 abstractC2624sj0, Function function) {
        C2249oL c2249oL = new C2249oL(i);
        boolean z = f;
        if (!z && c2249oL.a()) {
            x1f.a();
            return null;
        }
        C0230j0 c0230j0 = (C0230j0) function.apply(c2249oL);
        C2543rl0 c2543rl0 = this.d[i];
        if (c2543rl0 == null) {
            C2543rl0 c2543rl1 = new C2543rl0(a(i), abstractC2624sj0, c0230j0);
            this.d[i] = c2543rl1;
            return c2543rl1;
        }
        c2543rl0.a(abstractC2624sj0);
        if (c0230j0 != null && !c2543rl0.y()) {
            c2543rl0.a(c0230j0);
        }
        if (z || c0230j0 == c2543rl0.r()) {
            return c2543rl0;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.WK
    public final C2543rl0 a(Object obj, AbstractC2420qL abstractC2420qL) {
        int i;
        C2249oL c2249oL = (C2249oL) obj;
        if (!c2249oL.a()) {
            if (!C2249oL.b && c2249oL.a()) {
                x1f.a();
                return null;
            }
            i = c2249oL.a;
        } else {
            C2078mL c2078mL = (C2078mL) abstractC2420qL;
            boolean z = C2249oL.b;
            if (!z && !c2249oL.a()) {
                x1f.a();
                return null;
            }
            int i2 = (c2249oL.a & Integer.MAX_VALUE) >> 16;
            if (!z && !c2249oL.a()) {
                x1f.a();
                return null;
            }
            int i3 = c2249oL.a & 65535;
            if (!f && this.e == -1) {
                x1f.a();
                return null;
            }
            int i4 = this.e;
            int i5 = 0;
            while (true) {
                int[] iArr = c2078mL.a;
                if (i5 < iArr.length) {
                    if (iArr[i5] == i2) {
                        i = i3 + i4;
                        break;
                    }
                    i4 += iArr[i5 + 1];
                    i5 += 2;
                } else {
                    x0g.a("Unexpectedly fell off the end of the phi table");
                    return null;
                }
            }
        }
        C2543rl0 c2543rl0 = this.d[i];
        if (c2543rl0 != null) {
            return c2543rl0;
        }
        C2543rl0 c2543rl1 = new C2543rl0(a(i), AbstractC2624sj0.f(), null);
        this.d[i] = c2543rl1;
        return c2543rl1;
    }
}
