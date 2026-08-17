package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.InterfaceC0221h5;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.A;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.graph.u0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0306u0 extends E implements com.android.tools.r8.utils.structural.x<C0306u0> {
    public static final C0306u0[] f = new C0306u0[0];
    public static final C0306u0 g = new C0306u0();
    public static final /* synthetic */ boolean h = true;
    public final C0285r0[] d;
    public int e;

    public C0306u0(C0285r0[] c0285r0Arr) {
        this.e = 0;
        if (h || !com.android.tools.r8.internal.R3.a(c0285r0Arr)) {
            this.d = c0285r0Arr;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static C0306u0 o0() {
        return g;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final void a(final AbstractC3345r0 abstractC3345r0) {
        int i = this.e;
        if (i != 0) {
            if (h) {
                return;
            }
            int iHashCode = hashCode();
            if (iHashCode == 0) {
                iHashCode = 1;
            }
            if (i == iHashCode) {
                return;
            }
            x1f.a();
            return;
        }
        Arrays.sort(this.d, new Comparator() { // from class: bei
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((C0285r0) obj).c.b.a((InterfaceC0221h5) ((C0285r0) obj2).c.b, abstractC3345r0);
            }
        });
        for (C0285r0 c0285r0 : this.d) {
            c0285r0.c.o0();
        }
        this.e = hashCode();
    }

    public final C0306u0 b(I2 i2) {
        int i = 0;
        for (C0285r0 c0285r0 : this.d) {
            if (c0285r0.c.b == i2) {
                C0285r0[] c0285r0Arr = this.d;
                int length = c0285r0Arr.length - 1;
                C0285r0[] c0285r0Arr2 = new C0285r0[length];
                System.arraycopy(c0285r0Arr, 0, c0285r0Arr2, 0, i);
                if (i < length) {
                    System.arraycopy(this.d, i + 1, c0285r0Arr2, i, length - i);
                }
                return com.android.tools.r8.internal.R3.a(c0285r0Arr2) ? o0() : new C0306u0(c0285r0Arr2);
            }
            i++;
        }
        return this;
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        if (obj instanceof C0306u0) {
            return Arrays.equals(this.d, ((C0306u0) obj).d);
        }
        return false;
    }

    public final void forEach(Consumer consumer) {
        for (C0285r0 c0285r0 : this.d) {
            consumer.accept(c0285r0);
        }
    }

    public boolean isEmpty() {
        return this.d.length == 0;
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        return Arrays.hashCode(this.d);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: hei
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0306u0.a(a);
            }
        };
    }

    public C0285r0 p0() {
        return this.d[0];
    }

    public int size() {
        return this.d.length;
    }

    public final Stream stream() {
        return Arrays.stream(this.d);
    }

    public final String toString() {
        return Arrays.toString(this.d);
    }

    public C0306u0() {
        this.e = 0;
        this.d = C0285r0.d;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.f(new Function() { // from class: dei
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0306u0) obj).d;
            }
        });
    }

    public static C0306u0 a(C0285r0[] c0285r0Arr) {
        return com.android.tools.r8.internal.R3.a(c0285r0Arr) ? o0() : new C0306u0(c0285r0Arr);
    }

    public static I2 a(List list) {
        Set setC = AbstractC2780ub0.c();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0285r0 c0285r0 = (C0285r0) it.next();
            if (!h && c0285r0.q0()) {
                x1f.a();
                return null;
            }
            if (!setC.add(c0285r0.c.b)) {
                return c0285r0.c.b;
            }
        }
        return null;
    }

    public static I2 b(C0285r0[] c0285r0Arr) {
        return a(Arrays.asList(c0285r0Arr));
    }

    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        for (C0285r0 c0285r0 : this.d) {
            c0285r0.a(c0333y, m);
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        x.a(this);
        AbstractC0259n1.a(x, this.d);
    }

    public final C0285r0 a(I2 i2) {
        for (C0285r0 c0285r0 : this.d) {
            I2 i2O0 = c0285r0.o0();
            i2O0.getClass();
            if (I2.a(i2O0, i2)) {
                return c0285r0;
            }
        }
        return null;
    }

    public static /* synthetic */ C0285r0 a(Predicate predicate, C0285r0 c0285r0) {
        if (predicate.test(c0285r0)) {
            return null;
        }
        return c0285r0;
    }

    public final C0306u0 a(final Predicate predicate) {
        return a(new Function() { // from class: fei
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0306u0.a(predicate, (C0285r0) obj);
            }
        });
    }

    public final C0306u0 a(Function function) {
        C0285r0[] c0285r0Arr;
        return (isEmpty() || (c0285r0Arr = (C0285r0[]) com.android.tools.r8.internal.R3.a((Object[]) this.d, function, (Object[]) C0285r0.d)) == this.d) ? this : a(c0285r0Arr);
    }
}
