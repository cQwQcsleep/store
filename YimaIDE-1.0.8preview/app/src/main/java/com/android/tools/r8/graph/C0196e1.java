package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0196e1;
import com.android.tools.r8.graph.C0299t0;
import com.android.tools.r8.utils.structural.A;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0196e1 extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<C0196e1> {
    public static final /* synthetic */ boolean e = true;
    public final I2 b;
    public final C0299t0[] c;
    public int d = 0;

    public C0196e1(I2 i2, C0299t0[] c0299t0Arr) {
        this.b = i2;
        this.c = c0299t0Arr;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final C0196e1 a(Function function, Function function2) {
        I2 i2 = (I2) function.apply(this.b);
        C0299t0[] c0299t0Arr = (C0299t0[]) com.android.tools.r8.internal.R3.a((Object[]) this.c, function2, (Object[]) C0299t0.d);
        return (i2 == this.b && c0299t0Arr == this.c) ? this : new C0196e1(i2, c0299t0Arr);
    }

    public final void d(Consumer consumer) {
        for (C0299t0 c0299t0 : this.c) {
            consumer.accept(c0299t0);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0196e1) {
            C0196e1 c0196e1 = (C0196e1) obj;
            if (c0196e1.b.equals(this.b) && Arrays.equals(c0196e1.c, this.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.b.hashCode() * 7) + Arrays.hashCode(this.c);
    }

    public C0299t0 j(int i) {
        return this.c[i];
    }

    public int n0() {
        return this.c.length;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: yrg
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0196e1.a(a);
            }
        };
    }

    public final void o0() {
        int i = this.d;
        if (i != 0) {
            if (e) {
                return;
            }
            int iHashCode = hashCode();
            if (i == (iHashCode != 0 ? iHashCode : 1)) {
                return;
            }
            x1f.a();
            return;
        }
        Arrays.sort(this.c, new Comparator() { // from class: bsg
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((C0299t0) obj).b.a(((C0299t0) obj2).b);
            }
        });
        for (C0299t0 c0299t0 : this.c) {
            c0299t0.c.c1();
        }
        int iHashCode2 = hashCode();
        this.d = iHashCode2 != 0 ? iHashCode2 : 1;
    }

    public final String toString() {
        return "Encoded annotation " + this.b + " " + Arrays.toString(this.c);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: zrg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0196e1) obj).b;
            }
        }).f(new Function() { // from class: asg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0196e1) obj).c;
            }
        });
    }

    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        this.b.a(c0333y, m);
        for (C0299t0 c0299t0 : this.c) {
            H2 h2 = c0299t0.b;
            h2.getClass();
            m.a(h2);
            c0299t0.c.a(c0333y, m);
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        if (e) {
            return;
        }
        x1f.a();
    }
}
