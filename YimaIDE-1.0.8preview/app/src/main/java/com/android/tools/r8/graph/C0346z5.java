package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.shaking.C3380d1;
import com.android.tools.r8.shaking.C3403i;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.z5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0346z5 extends F0 implements A5 {
    public static final /* synthetic */ boolean f = true;

    public C0346z5(D2 d2, C0210g1 c0210g1) {
        super(d2, c0210g1);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final C0346z5 O() {
        return this;
    }

    @Override // com.android.tools.r8.graph.A5
    public final com.android.tools.r8.kotlin.P W() {
        return e().m;
    }

    @Override // com.android.tools.r8.graph.F0
    public final boolean a(C0333y c0333y) {
        final C0205f3 accessFlags = getAccessFlags();
        C0233j3 c0233j3A = ((C3403i) c0333y.g()).s.a(getReference());
        C3380d1 c3380d1A = c0333y.a(this);
        C2752uB c2752uBM = c0333y.M();
        return c3380d1A.c(c2752uBM) && c3380d1A.e(c2752uBM) && !c0233j3A.e() && !c0233j3A.h() && c0233j3A.a(new Predicate() { // from class: ozi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a(accessFlags, (B5) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final E0 b() {
        return a();
    }

    @Override // com.android.tools.r8.graph.F0, com.android.tools.r8.graph.InterfaceC0265o0
    public final F0 d() {
        return this;
    }

    @Override // com.android.tools.r8.graph.F0, com.android.tools.r8.graph.InterfaceC0265o0
    public final C0346z5 d() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0339y5
    public final InterfaceC0265o0 getContext() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final A5 p() {
        return this;
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final D2 b() {
        return a();
    }

    public final /* synthetic */ boolean a(C0205f3 c0205f3, B5 b5) {
        return b5.e().p1() && b5.getAccessFlags().n() == c0205f3.n() && b5.a() == a();
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0331x4, com.android.tools.r8.graph.A5
    public final D2 a() {
        E0 e0 = this.b;
        if (f || e0.a0()) {
            return e0.X();
        }
        x1f.a();
        return null;
    }
}
