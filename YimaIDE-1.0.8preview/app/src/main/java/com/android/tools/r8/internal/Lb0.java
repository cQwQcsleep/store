package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Lb0;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Lb0 {
    public final C1933kg[] a = (C1933kg[]) R3.a((Object[]) new C1933kg[5], new IntFunction() { // from class: vx8
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            return this.b.c(i);
        }
    });
    public final C1863jo[] b = (C1863jo[]) R3.a((Object[]) new C1863jo[5], new IntFunction() { // from class: wx8
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            return this.b.d(i);
        }
    });
    public final C1863jo[] c = (C1863jo[]) R3.a((Object[]) new C1863jo[5], new IntFunction() { // from class: xx8
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            return this.b.e(i);
        }
    });
    public final C2342pS[] d = (C2342pS[]) R3.a((Object[]) new C2342pS[5], new IntFunction() { // from class: yx8
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            return this.b.f(i);
        }
    });
    public final C2342pS[] e = (C2342pS[]) R3.a((Object[]) new C2342pS[5], new IntFunction() { // from class: zx8
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            return this.b.g(i);
        }
    });
    public final ConcurrentHashMap f = new ConcurrentHashMap();
    public final ConcurrentHashMap g = new ConcurrentHashMap();
    public final ConcurrentHashMap h = new ConcurrentHashMap();
    public final ConcurrentHashMap i = new ConcurrentHashMap();
    public final ConcurrentHashMap j = new ConcurrentHashMap();

    public final C2342pS a(final int i, final C2427qS c2427qS) {
        return (C2342pS) a(i, c2427qS.e() ? this.e : this.d, c2427qS.e() ? this.j : this.i, new Supplier() { // from class: ay8
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.b(i, c2427qS);
            }
        });
    }

    public final C1933kg b(int i) {
        return new C1933kg(i);
    }

    public final C1933kg c(int i) {
        return new C1933kg(i);
    }

    public final C1863jo d(int i) {
        return new C1863jo(i, false);
    }

    public final C1863jo e(int i) {
        return new C1863jo(i, true);
    }

    public final C2342pS f(int i) {
        return new C2342pS(i, C2427qS.b());
    }

    public final C2342pS g(int i) {
        return new C2342pS(i, C2427qS.c());
    }

    public final C1863jo b(int i, boolean z) {
        return new C1863jo(i, z);
    }

    public final C2342pS b(int i, C2427qS c2427qS) {
        return new C2342pS(i, c2427qS);
    }

    public final C1863jo a(final int i, final boolean z) {
        return (C1863jo) a(i, z ? this.c : this.b, z ? this.h : this.g, new Supplier() { // from class: ux8
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.b(i, z);
            }
        });
    }

    public final C1933kg a(final int i) {
        return (C1933kg) a(i, this.a, this.f, new Supplier() { // from class: tx8
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.b(i);
            }
        });
    }

    public static Fb0 a(int i, Fb0[] fb0Arr, ConcurrentHashMap concurrentHashMap, final Supplier supplier) {
        if (i < fb0Arr.length) {
            return fb0Arr[i];
        }
        return (Fb0) concurrentHashMap.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: by8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Lb0.a(supplier, (Integer) obj);
            }
        });
    }

    public static /* synthetic */ Fb0 a(Supplier supplier, Integer num) {
        return (Fb0) supplier.get();
    }
}
