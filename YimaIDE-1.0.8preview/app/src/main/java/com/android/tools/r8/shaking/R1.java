package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.I5;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.InterfaceC2706th0;
import com.android.tools.r8.internal.SJ;
import com.android.tools.r8.shaking.R1;
import com.android.tools.r8.synthesis.C3515y;
import defpackage.j6h;
import defpackage.x2c;
import defpackage.y2c;
import defpackage.z2c;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class R1 {
    public static final R1 f;
    public static final /* synthetic */ boolean g = true;
    public final Set a;
    public final Set b;
    public Set c;
    public final Set d;
    public boolean e;

    static {
        Set set = Collections.EMPTY_SET;
        f = new R1(set, set, set, set, false);
    }

    public R1(Set set, Set set2, Set set3, Set set4, boolean z) {
        this.a = set;
        this.b = set2;
        this.c = set3;
        this.d = set4;
        this.e = z;
        if (g) {
            return;
        }
        Stream stream = set4.stream();
        Objects.requireNonNull(set2);
        if (stream.noneMatch(new j6h(set2))) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final boolean a(C0333y c0333y, InterfaceC0332x5 interfaceC0332x5, B5 b5) {
        final com.android.tools.r8.synthesis.J jG;
        Q1 q1A;
        if (interfaceC0332x5.z() != b5.s() && (q1A = a(interfaceC0332x5.getReference(), (jG = c0333y.a.g()))) != Q1.e && q1A != Q1.d) {
            if (q1A == Q1.b) {
                return O1.a(c0333y, b5, new Predicate() { // from class: u2c
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.b.a(jG, (I2) obj);
                    }
                }) != null;
            }
            if (!g && q1A != Q1.c) {
                x1f.a();
                return false;
            }
            if (O1.a(c0333y, b5, new Predicate() { // from class: w2c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.b(jG, (I2) obj);
                }
            }) != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final R1 a(final com.android.tools.r8.synthesis.J j, final AbstractC3148ys abstractC3148ys) {
        final Set setC = AbstractC2780ub0.c();
        this.a.forEach(new Consumer() { // from class: j2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                R1.a(abstractC3148ys, setC, (I2) obj);
            }
        });
        final P1 p1 = new P1(this.e);
        this.b.forEach(new Consumer() { // from class: k2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                R1.a(abstractC3148ys, p1, (I2) obj);
            }
        });
        this.c.forEach(new Consumer() { // from class: l2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                R1.a(p1, abstractC3148ys, (C0322w2) obj);
            }
        });
        this.d.forEach(new Consumer() { // from class: m2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                R1.a(abstractC3148ys, p1, j, (I2) obj);
            }
        });
        return p1.a(setC);
    }

    public static R1 b() {
        return f;
    }

    public final void b(Set set, P1 p1, com.android.tools.r8.graph.I2 i2) {
        Objects.requireNonNull(p1);
        x2c x2cVar = new x2c(p1);
        if (set.contains(i2)) {
            return;
        }
        x2cVar.accept(i2);
    }

    public final boolean b(com.android.tools.r8.synthesis.J j, com.android.tools.r8.graph.I2 i2) {
        return !a(i2, this.b, j);
    }

    public final Q1 a(com.android.tools.r8.graph.F2 f2, com.android.tools.r8.synthesis.J j) {
        if (a(f2, this.a, j)) {
            return Q1.b;
        }
        if (a(f2, this.b, j)) {
            return Q1.c;
        }
        if (this.d.contains(f2.z())) {
            return Q1.d;
        }
        return Q1.e;
    }

    public final boolean a(com.android.tools.r8.synthesis.J j, com.android.tools.r8.graph.I2 i2) {
        return !a(i2, this.a, j);
    }

    public final boolean a(InterfaceC0332x5 interfaceC0332x5, com.android.tools.r8.synthesis.J j) {
        return a(interfaceC0332x5.z(), this.b, j);
    }

    public static boolean a(com.android.tools.r8.graph.F2 f2, Set set, com.android.tools.r8.synthesis.J j) {
        if (set.isEmpty()) {
            return false;
        }
        com.android.tools.r8.graph.I2 i2Z = f2.z();
        Iterator<E> it = j.b(i2Z).iterator();
        while (it.hasNext()) {
            if (set.contains((com.android.tools.r8.graph.I2) it.next())) {
                return true;
            }
        }
        return set.contains(i2Z);
    }

    public final void a() {
        this.e = true;
        this.c = AbstractC2780ub0.c();
    }

    public final boolean a(B5 b5, com.android.tools.r8.graph.F2 f2, com.android.tools.r8.synthesis.J j) {
        Q1 q1A = a(b5.getReference(), j);
        if (q1A == Q1.e || q1A == Q1.d) {
            return true;
        }
        if (q1A == Q1.b) {
            return false;
        }
        if (g || q1A == Q1.c) {
            return a(f2, j) == Q1.c;
        }
        x1f.a();
        return false;
    }

    public final void a(Consumer consumer) {
        Set setC = AbstractC2780ub0.c();
        this.a.forEach(C0822Sg.a(consumer, setC));
        this.b.forEach(C0822Sg.a(consumer, setC));
        this.d.forEach(C0822Sg.a(consumer, setC));
    }

    public final R1 a(I5 i5) {
        if (i5.d()) {
            return this;
        }
        final Set set = i5.e;
        final Set setC = AbstractC2780ub0.c();
        this.a.forEach(new Consumer() { // from class: g2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(set, setC, (I2) obj);
            }
        });
        final P1 p1 = new P1(this.e);
        this.b.forEach(new Consumer() { // from class: o2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(set, p1, (I2) obj);
            }
        });
        this.c.forEach(new Consumer() { // from class: q2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(set, p1, (C0322w2) obj);
            }
        });
        this.d.forEach(new Consumer() { // from class: s2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(set, p1, (I2) obj);
            }
        });
        return p1.a(setC);
    }

    public final void a(Set set, Set set2, com.android.tools.r8.graph.I2 i2) {
        Objects.requireNonNull(set2);
        y2c y2cVar = new y2c(set2);
        if (set.contains(i2)) {
            return;
        }
        y2cVar.accept(i2);
    }

    public final void a(Set set, P1 p1, com.android.tools.r8.graph.I2 i2) {
        Objects.requireNonNull(p1);
        z2c z2cVar = new z2c(p1);
        if (set.contains(i2)) {
            return;
        }
        z2cVar.accept(i2);
    }

    public final void a(Set set, final P1 p1, final C0322w2 c0322w2) {
        com.android.tools.r8.graph.I2 i2W0 = c0322w2.w0();
        Consumer consumer = new Consumer() { // from class: i2c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                R1.a(p1, c0322w2, (I2) obj);
            }
        };
        if (set.contains(i2W0)) {
            return;
        }
        consumer.accept(i2W0);
    }

    public final R1 a(final com.android.tools.r8.synthesis.J j, final AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        return (R1) ch0.a("Rewrite MainDexInfo", new InterfaceC2706th0() { // from class: n2c
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(j, abstractC3148ys);
            }
        });
    }

    public static /* synthetic */ void a(AbstractC3148ys abstractC3148ys, Set set, com.android.tools.r8.graph.I2 i2) {
        Objects.requireNonNull(set);
        SJ.a(abstractC3148ys, i2, new y2c(set));
    }

    public static /* synthetic */ void a(AbstractC3148ys abstractC3148ys, P1 p1, com.android.tools.r8.graph.I2 i2) {
        Objects.requireNonNull(p1);
        SJ.a(abstractC3148ys, i2, new z2c(p1));
    }

    public static void a(P1 p1, AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        abstractC3148ys.getClass();
        p1.c.add(abstractC3148ys.d(AbstractC3148ys.g(), c0322w2));
    }

    public static void a(AbstractC3148ys abstractC3148ys, final P1 p1, com.android.tools.r8.synthesis.J j, com.android.tools.r8.graph.I2 i2) {
        abstractC3148ys.getClass();
        if (abstractC3148ys instanceof C3515y) {
            Objects.requireNonNull(p1);
            SJ.a(abstractC3148ys, i2, new Consumer() { // from class: a3c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    p1.b((I2) obj);
                }
            });
        } else if (j.c()) {
            SJ.a(abstractC3148ys, i2, p1.a(j));
        } else {
            Objects.requireNonNull(p1);
            SJ.a(abstractC3148ys, i2, new x2c(p1));
        }
    }

    public static void a(P1 p1, C0322w2 c0322w2, com.android.tools.r8.graph.I2 i2) {
        p1.c.add(c0322w2);
    }
}
