package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B2;
import com.android.tools.r8.utils.structural.A;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class B2 implements com.android.tools.r8.utils.structural.x {
    public static final /* synthetic */ int b = 0;

    public final A2 a(K2 k2, B1 b1) {
        return new A2(b1.a(b().r0(), k2), a());
    }

    public abstract H2 a();

    public abstract E2 b();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof B2)) {
            return false;
        }
        B2 b2 = (B2) obj;
        H2 h2A = b2.a();
        E2 e2B = b2.b();
        if (a().b(h2A)) {
            E2 e2B2 = b();
            e2B2.getClass();
            if (E2.a(e2B2, e2B)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(a(), b());
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: el0
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                B2.a(a);
            }
        };
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(b().r0().H0());
        sb.append(" ");
        sb.append(a());
        sb.append("(");
        for (int i = 0; i < b().p0(); i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(b().f.b[i].H0());
        }
        sb.append(")");
        return sb.toString();
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: al0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((B2) obj).a();
            }
        }).e(new Function() { // from class: cl0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((B2) obj).b();
            }
        });
    }

    public final A2 a(H2 h2) {
        return new A2(b(), h2);
    }
}
