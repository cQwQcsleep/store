package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import java.util.function.Function;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RW extends WK {
    public static final /* synthetic */ boolean e = true;
    public final C2543rl0[] d;

    public RW(SK sk, AS as) {
        super(as);
        int i = sk.i + sk.k;
        this.d = new C2543rl0[i];
        if (this.a) {
            for (int i2 = 0; i2 < i; i2++) {
                this.b.a();
            }
        }
    }

    @Override // com.android.tools.r8.internal.WK
    public final C2543rl0 a(int i, AbstractC2624sj0 abstractC2624sj0, Function function) {
        C0230j0 c0230j0 = (C0230j0) function.apply(Integer.valueOf(i));
        C2543rl0 c2543rl0 = this.d[i];
        if (c2543rl0 == null) {
            C2543rl0 c2543rl1 = new C2543rl0(a(i), abstractC2624sj0, c0230j0);
            this.d[i] = c2543rl1;
            return c2543rl1;
        }
        c2543rl0.a(abstractC2624sj0);
        if (c0230j0 != null) {
            if (!c2543rl0.y()) {
                c2543rl0.a(c0230j0);
            }
            if (!e && c0230j0 != c2543rl0.r()) {
                x1f.a();
                return null;
            }
        }
        return c2543rl0;
    }

    @Override // com.android.tools.r8.internal.WK
    public final C2543rl0 a(int i, AbstractC2624sj0 abstractC2624sj0) {
        return new C2543rl0(i, abstractC2624sj0, null);
    }

    @Override // com.android.tools.r8.internal.WK
    public final C2543rl0 a(Object obj, AbstractC2420qL abstractC2420qL) {
        int iIntValue = ((Integer) obj).intValue();
        C2543rl0 c2543rl0 = this.d[iIntValue];
        if (c2543rl0 != null) {
            return c2543rl0;
        }
        C2543rl0 c2543rl1 = new C2543rl0(a(iIntValue), AbstractC2624sj0.f(), null);
        this.d[iIntValue] = c2543rl1;
        return c2543rl1;
    }

    @Override // com.android.tools.r8.internal.WK
    public final PW a(int i, IntFunction intFunction, C1720i7 c1720i7, Function function, AbstractC2420qL abstractC2420qL) {
        PW pw = new PW(a(i), (H5) intFunction.apply(i), c1720i7, (C0230j0) function.apply(Integer.valueOf(i)), PW.a.b);
        C2543rl0 c2543rl0 = this.d[i];
        if (c2543rl0 != null) {
            if (!e && c2543rl0.j()) {
                x1f.a();
                return null;
            }
            c2543rl0.f(pw);
        }
        this.d[i] = pw;
        return pw;
    }
}
