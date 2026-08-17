package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.H0;
import com.android.tools.r8.internal.C0766Qc;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0766Qc {
    public static final C0766Qc d;
    public final C0766Qc a;
    public final AbstractC0551Hu b;
    public final C0792Rc c;

    static {
        int i = AbstractC0551Hu.c;
        d = new C0766Qc(null, P40.e, C0792Rc.c);
    }

    public C0766Qc(C0766Qc c0766Qc, AbstractC0551Hu abstractC0551Hu, C0792Rc c0792Rc) {
        this.a = c0766Qc;
        this.b = abstractC0551Hu;
        this.c = c0792Rc;
    }

    public final boolean a(final com.android.tools.r8.graph.H0 h0) {
        if (C2753uC.a(this.b, new Function() { // from class: d0c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((H0) obj).e();
            }
        }, new Predicate() { // from class: e0c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0766Qc.a(h0, (C0231j1) obj);
            }
        })) {
            return true;
        }
        C0766Qc c0766Qc = this.a;
        return c0766Qc != null && c0766Qc.a(h0);
    }

    public static /* synthetic */ boolean a(com.android.tools.r8.graph.H0 h0, C0231j1 c0231j1) {
        return c0231j1 == h0.e();
    }
}
