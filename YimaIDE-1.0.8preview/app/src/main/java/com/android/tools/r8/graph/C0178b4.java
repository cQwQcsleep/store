package com.android.tools.r8.graph;

import com.android.tools.r8.graph.Y3;
import com.android.tools.r8.internal.AbstractC2243oF;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C3050xi0;
import com.android.tools.r8.internal.Jl0;
import defpackage.ubi;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.b4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0178b4 {
    public static final /* synthetic */ boolean h = true;
    public final C2752uB a;
    public final ConcurrentHashMap b;
    public final ConcurrentHashMap c;
    public final ConcurrentHashMap d;
    public final B e;
    public final C0334y0 f;
    public final ArrayList g;

    public C0178b4(C2752uB c2752uB, C0334y0 c0334y0) {
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.d = new ConcurrentHashMap();
        this.g = new ArrayList();
        this.a = c2752uB;
        this.f = c0334y0;
        this.e = B.a(c2752uB);
    }

    public final void a(E0 e0, V v, Y3 y3) {
        boolean z = h;
        if (!z) {
            if (Jl0.a(this.a.a, y3.d())) {
                x1f.a();
                return;
            }
        }
        if (Jl0.a(this.a.a, y3.b())) {
            I2 type = e0.getType();
            if (v == V.c) {
                C0334y0 c0334y0 = this.f;
                synchronized (c0334y0.e) {
                    c0334y0.e.a(type);
                }
            }
            if (z || y3.d() == this.a.a.F2) {
                b(e0.getType(), v);
            } else {
                x1f.a();
            }
        }
    }

    public final void b(I2 i2, V v) {
        if (v == V.c) {
            C0334y0 c0334y0 = this.f;
            synchronized (c0334y0.d) {
                c0334y0.d.a(i2);
            }
        }
    }

    public final E2 c(String str) {
        K2 k2;
        if (!h && !b(str).b().equals(str)) {
            x1f.a();
            return null;
        }
        String strU = C0929Wj.u(str);
        String[] strArrE = C0929Wj.e(str);
        B1 b1 = this.a.a;
        I2 i2E = e(strU);
        if (strArrE.length == 0) {
            k2 = K2.n0();
        } else {
            I2[] i2Arr = new I2[strArrE.length];
            for (int i = 0; i < strArrE.length; i++) {
                i2Arr[i] = e(strArrE[i]);
            }
            k2 = new K2(i2Arr);
        }
        return b1.a(i2E, k2);
    }

    public final H2 d(String str) {
        ConcurrentHashMap concurrentHashMap = this.d;
        B1 b1 = this.a.a;
        Objects.requireNonNull(b1);
        return (H2) concurrentHashMap.computeIfAbsent(str, new ubi(b1));
    }

    public final I2 e(String str) {
        if (h || b(str).b().equals(str)) {
            return this.a.a.c(d(this.e.a(str)));
        }
        x1f.a();
        return null;
    }

    public final I2 f(String str) {
        if (!h) {
            C3050xi0 c3050xi0A = a(str);
            if (!c3050xi0A.b.substring(c3050xi0A.c, c3050xi0A.d).equals(str)) {
                x1f.a();
                return null;
            }
        }
        return e(a(str).b());
    }

    public final C3050xi0 b(String str) {
        return (C3050xi0) this.c.computeIfAbsent(str, new Function() { // from class: ygg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3050xi0.g((String) obj);
            }
        });
    }

    public C0178b4(C2752uB c2752uB) {
        this(c2752uB, new C0334y0());
    }

    public final C3050xi0 a(String str) {
        return (C3050xi0) this.b.computeIfAbsent(str, new Function() { // from class: ahg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3050xi0.e((String) obj);
            }
        });
    }

    public final B1 a() {
        return this.a.a;
    }

    public final K2 a(String[] strArr) {
        if (strArr.length == 0) {
            return K2.n0();
        }
        I2[] i2Arr = new I2[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            i2Arr[i] = f(strArr[i]);
        }
        return new K2(i2Arr);
    }

    public final C0322w2 a(I2 i2, String str, String str2) {
        return this.a.a.a(i2, c(str2), d(str));
    }

    public final D0 a(String str, String str2, C0336y2 c0336y2, ArrayList arrayList) {
        B1 b1 = this.a.a;
        H2 h2D = d(str);
        E2 e2C = c(str2);
        b1.getClass();
        return new D0(h2D, e2C, c0336y2, arrayList);
    }

    public final void a(I2 i2, V v) {
        if (v == V.c) {
            C0334y0 c0334y0 = this.f;
            synchronized (c0334y0.e) {
                c0334y0.e.a(i2);
            }
        }
    }

    public final void a(final E0 e0, final V v) {
        if (this.a.p0()) {
            if (Jl0.a(this.a.a, e0.getType())) {
                I2 type = e0.getType();
                if (v == V.c) {
                    C0334y0 c0334y0 = this.f;
                    synchronized (c0334y0.d) {
                        c0334y0.d.a(type);
                    }
                }
            }
            e0.T0().forEach(new Consumer() { // from class: zgg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(e0, v, (Y3) obj);
                }
            });
        }
    }

    public final void a(AbstractC2243oF abstractC2243oF) {
        synchronized (this.g) {
            this.g.add(abstractC2243oF);
        }
    }
}
