package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.naming.B;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B extends C implements Cloneable {
    public final R0 d;
    public final W e;
    public final BiPredicate f;

    public B(C0333y c0333y, W w, final R0 r0, IdentityHashMap identityHashMap) {
        super(c0333y, identityHashMap);
        this.d = r0;
        this.e = w;
        this.f = new BiPredicate() { // from class: vj0
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return B.a(r0, (H2) obj, (C0346z5) obj2);
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004b  */
    /* JADX WARN: Code duplicated, block: B:36:0x0095  */
    public static boolean a(R0 r0, H2 h2, C0346z5 c0346z5) {
        H2 h3;
        boolean z;
        I2 i2 = c0346z5.getReference().i;
        Q0 q0 = (Q0) r0.c.get(r0.b.a().E1);
        H2 h4 = null;
        if (q0 == null) {
            h3 = null;
        } else {
            h3 = (H2) q0.a.get(h2);
            if (h3 == null) {
                h3 = (H2) q0.a.get(h2);
            }
        }
        if (h3 == null) {
            R0 r1 = r0.d;
            if (r1 == null) {
                h3 = null;
            } else {
                Q0 q1 = (Q0) r1.c.get(r1.b.a().E1);
                if (q1 == null) {
                    h3 = null;
                } else {
                    h3 = (H2) q1.a.get(h2);
                    if (h3 == null) {
                        h3 = (H2) q1.a.get(h2);
                    }
                }
            }
        }
        if (h3 != null) {
            z = true;
        } else {
            R0 r2 = r0.d;
            if (r2 != null) {
                Q0 q2 = (Q0) r2.c.get(r2.b.a().E1);
                if (q2 != null && (h4 = (H2) q2.a.get(h2)) == null) {
                    h4 = (H2) q2.a.get(h2);
                }
            }
            if (h4 != null) {
                z = true;
            } else {
                z = false;
            }
        }
        return !z;
    }

    public final Object clone() {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (Map.Entry entry : this.c.entrySet()) {
            I2 i2 = (I2) entry.getKey();
            A a = (A) entry.getValue();
            identityHashMap.put(i2, new A(a.d, a.c, a.b));
        }
        return new B(this.b, this.e, this.d, identityHashMap);
    }

    public B(C0333y c0333y, C3336m0 c3336m0) {
        this(c0333y, c3336m0, new R0(c0333y), new IdentityHashMap());
    }

    @Override // com.android.tools.r8.naming.C
    public final Object a() {
        return new A(this);
    }
}
