package com.android.tools.r8.graph;

import java.util.IdentityHashMap;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.graph.g2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0211g2 {
    public static final /* synthetic */ boolean h = true;
    public final E2 a;
    public final E2 b;
    public final E2 c;
    public final Set d;
    public final Set e;
    public final Set f;
    public final /* synthetic */ B1 g;

    public C0211g2(B1 b1) {
        this.g = b1;
        this.a = b1.a(b1.a2, b1.d2);
        this.b = b1.a(b1.E1, b1.d2);
        this.c = b1.a(b1.w1, b1.d2);
        this.d = a("compareAndExchange", "compareAndExchangeAcquire", "compareAndExchangeRelease", b1.n1, "getAcquire", "getAndAdd", "getAndAddAcquire", "getAndAddRelease", "getAndBitwiseAnd", "getAndBitwiseAndAcquire", "getAndBitwiseAndRelease", "getAndBitwiseOr", "getAndBitwiseOrAcquire", "getAndBitwiseOrRelease", "getAndBitwiseXor", "getAndBitwiseXorAcquire", "getAndBitwiseXorRelease", "getAndSet", "getAndSetAcquire", "getAndSetRelease", "getOpaque", "getVolatile");
        this.e = a(b1.o1, "setOpaque", b1.t1, b1.s1);
        this.f = a(b1.p1, b1.q1, "weakCompareAndSetAcquire", "weakCompareAndSetPlain", "weakCompareAndSetRelease");
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0063  */
    public final C0322w2 a(C0322w2 c0322w2) {
        C0322w2 c0322w2A;
        I2 i2 = c0322w2.f;
        B1 b1 = this.g;
        I2 i3 = b1.E2;
        if (i2 == i3) {
            H2 h2 = c0322w2.g;
            if (h2 == b1.F0 || h2 == b1.G0) {
                c0322w2A = b1.a(i3, this.a, h2);
            } else {
                c0322w2A = null;
            }
        } else if (i2 != b1.D2) {
            c0322w2A = null;
        } else if (this.d.contains(c0322w2.g)) {
            B1 b2 = this.g;
            c0322w2A = b2.a(b2.D2, this.a, c0322w2.g);
        } else if (this.e.contains(c0322w2.g)) {
            B1 b3 = this.g;
            c0322w2A = b3.a(b3.D2, this.b, c0322w2.g);
        } else if (this.f.contains(c0322w2.g)) {
            B1 b4 = this.g;
            c0322w2A = b4.a(b4.D2, this.c, c0322w2.g);
        } else {
            c0322w2A = null;
        }
        if (!h) {
            if ((c0322w2A != null) != b(c0322w2)) {
                x1f.a();
                return null;
            }
        }
        return c0322w2A;
    }

    public final boolean b(C0322w2 c0322w2) {
        I2 i2 = c0322w2.f;
        B1 b1 = this.g;
        if (i2 == b1.E2) {
            H2 h2 = c0322w2.g;
            return h2 == b1.F0 || h2 == b1.G0;
        }
        if (i2 == b1.D2) {
            return this.d.contains(c0322w2.g) || this.e.contains(c0322w2.g) || this.f.contains(c0322w2.g);
        }
        return false;
    }

    public final Set a(Object... objArr) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (Object obj : objArr) {
            H2 h2C = obj instanceof String ? this.g.c((String) obj) : (H2) obj;
            identityHashMap.put(h2C, h2C);
        }
        return identityHashMap.keySet();
    }
}
