package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0304t5;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.utils.structural.A;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.graph.t5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0304t5 extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<C0304t5> {
    public static final C0304t5 d = new C0304t5();
    public static final /* synthetic */ boolean e = true;
    public final C0306u0[] b;
    public final int c;

    public C0304t5(C0306u0[] c0306u0Arr, int i) {
        boolean z = e;
        if (!z && c0306u0Arr == null) {
            x1f.a();
            throw null;
        }
        if (!z && c0306u0Arr.length <= 0) {
            x1f.a();
            throw null;
        }
        if (!z) {
            for (C0306u0 c0306u0 : c0306u0Arr) {
                if (c0306u0.isEmpty()) {
                }
            }
            x1f.a();
            throw null;
        }
        this.b = c0306u0Arr;
        this.c = i;
    }

    public static C0304t5 n0() {
        return d;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final C0304t5 a(final Function function) {
        C0306u0[] c0306u0Arr;
        return (isEmpty() || (c0306u0Arr = (C0306u0[]) com.android.tools.r8.internal.R3.a((Object[]) this.b, new Function() { // from class: kci
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0306u0) obj).a(function);
            }
        }, (Object[]) C0306u0.f)) == this.b) ? this : a(c0306u0Arr, this.c);
    }

    public final void d(Consumer consumer) {
        for (C0306u0 c0306u0 : this.b) {
            for (C0285r0 c0285r0 : c0306u0.d) {
                consumer.accept(c0285r0);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0304t5) {
            return Arrays.equals(this.b, ((C0304t5) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public boolean isEmpty() {
        return this.b.length == 0;
    }

    public C0306u0 j(int i) {
        if (!e && i < 0) {
            x1f.a();
            return null;
        }
        int i2 = i - this.c;
        if (i2 >= 0) {
            C0306u0[] c0306u0Arr = this.b;
            if (i2 < c0306u0Arr.length) {
                return c0306u0Arr[i2];
            }
        }
        return C0306u0.o0();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: jci
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0304t5.a(a);
            }
        };
    }

    public int size() {
        return this.c + this.b.length;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.f(new Function() { // from class: hci
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0304t5) obj).b;
            }
        }).a(new ToIntFunction() { // from class: ici
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C0304t5) obj).c;
            }
        });
    }

    public static C0304t5 a(C0306u0[] c0306u0Arr, int i) {
        if (!com.android.tools.r8.internal.R3.a(c0306u0Arr)) {
            for (C0306u0 c0306u0 : c0306u0Arr) {
                if (!c0306u0.isEmpty()) {
                    return new C0304t5(c0306u0Arr, i);
                }
            }
        }
        return d;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        AbstractC0259n1.a(x, this.b);
        x.a(this);
    }

    public C0304t5() {
        this.b = C0306u0.f;
        this.c = 0;
    }
}
