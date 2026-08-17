package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0299t0;
import com.android.tools.r8.utils.structural.A;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0299t0 extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<C0299t0> {
    public static final C0299t0[] d = new C0299t0[0];
    public static final /* synthetic */ boolean e = true;
    public final H2 b;
    public final O2 c;

    public C0299t0(H2 h2, O2 o2) {
        this.b = h2;
        this.c = o2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: rbi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0299t0) obj).b;
            }
        }).e(new Function() { // from class: sbi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0299t0) obj).c;
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0299t0) {
            C0299t0 c0299t0 = (C0299t0) obj;
            if (this.b.equals(c0299t0.b) && this.c.equals(c0299t0.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.c.hashCode() * 3) + this.b.hashCode();
    }

    public final H2 n0() {
        return this.b;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: vbi
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0299t0.a(a);
            }
        };
    }

    public O2 o0() {
        return this.c;
    }

    public final String toString() {
        return this.b + "=" + this.c;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        if (e) {
            return;
        }
        x1f.a();
    }
}
