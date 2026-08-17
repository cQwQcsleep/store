package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1841jc0;
import com.android.tools.r8.internal.C2525rc0;
import com.android.tools.r8.internal.C2782uc0;
import com.android.tools.r8.internal.C3039xc0;
import com.android.tools.r8.internal.RI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1 {
    public static final /* synthetic */ boolean f = true;
    public final ConcurrentHashMap a = new ConcurrentHashMap();
    public final ConcurrentHashMap b = new ConcurrentHashMap();
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public final ConcurrentHashMap d = new ConcurrentHashMap();
    public final ConcurrentHashMap e = new ConcurrentHashMap();

    public static C2440qc0 b(com.android.tools.r8.graph.I2 i2) {
        if (f || i2.U0()) {
            return C2440qc0.b;
        }
        x01.a(i2);
        return null;
    }

    public final AbstractC1926kc0 a(com.android.tools.r8.graph.I2 i2) {
        if (f || i2.T0() || i2.U0()) {
            return i2.T0() ? b() : b(i2);
        }
        x1f.a();
        return null;
    }

    public final C1841jc0 c(com.android.tools.r8.graph.I2 i2) {
        return (C1841jc0) this.a.computeIfAbsent(i2, new Function() { // from class: q61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C1841jc0((I2) obj);
            }
        });
    }

    public final C2782uc0 b(int i) {
        return (C2782uc0) this.c.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: o61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C2782uc0(((Integer) obj).intValue());
            }
        });
    }

    public C2525rc0 b() {
        return a(0L);
    }

    public static C2440qc0 a() {
        return C2440qc0.b;
    }

    public final B1 a(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return Ak0.a;
        }
        if ((i | i2) == -1) {
            return a(i);
        }
        return new C1682hi(i, i2);
    }

    public final RI a(int i) {
        return (RI) this.e.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: p61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new RI(((Integer) obj).intValue());
            }
        });
    }

    public static C3110yS a(long j, long j2) {
        return new C3110yS(j, j2);
    }

    public static AbstractC2269oc0 a(C0245l1 c0245l1, RU ru) {
        if (ru.e()) {
            return new C2953wc0(c0245l1);
        }
        return new C2867vc0(c0245l1, ru);
    }

    public final C2525rc0 a(long j, AbstractC2624sj0 abstractC2624sj0) {
        if (f || abstractC2624sj0.H()) {
            return a(j);
        }
        x1f.a();
        return null;
    }

    public final C2525rc0 a(long j) {
        return (C2525rc0) this.b.computeIfAbsent(Long.valueOf(j), new Function() { // from class: n61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C2525rc0(((Long) obj).longValue());
            }
        });
    }

    public final C3039xc0 a(com.android.tools.r8.graph.H2 h2) {
        return (C3039xc0) this.d.computeIfAbsent(h2, new Function() { // from class: m61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C3039xc0((H2) obj);
            }
        });
    }
}
