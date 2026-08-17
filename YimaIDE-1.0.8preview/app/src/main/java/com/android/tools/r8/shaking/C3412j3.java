package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.C3461t3;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.j3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3412j3 extends C3432n3 {
    public static final C3402h3 w = new C3402h3(Origin.root());
    public static final /* synthetic */ boolean x = true;
    public final com.android.tools.r8.graph.D2 t;
    final C3427m3 u;
    public ConcurrentHashMap v;

    /* JADX WARN: Illegal instructions before constructor call */
    public C3412j3(Origin origin, Position position, String str, List list, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, List list2, K3 k3, boolean z2, List list3, C3427m3 c3427m3, com.android.tools.r8.graph.D2 d2) {
        EnumC3447q3 enumC3447q3 = EnumC3447q3.e;
        C3437o3.a aVarA = C3437o3.a();
        super(origin, position, str, list, c3470v2, c3470v3, z, o2, f2, list2, k3, z2, list3, enumC3447q3, new C3437o3(aVarA.a, aVarA.b, aVarA.c, aVarA.d, aVarA.e, aVarA.f, aVarA.g, aVarA.h));
        this.v = new ConcurrentHashMap();
        this.u = c3427m3;
        this.t = d2;
    }

    @Override // com.android.tools.r8.shaking.C3432n3, com.android.tools.r8.shaking.Y2
    public final String D() {
        return "if";
    }

    public final com.android.tools.r8.graph.D2 H() {
        if (x || this.t != null) {
            return this.t;
        }
        x1f.a();
        return null;
    }

    public final C3427m3 I() {
        return this.u;
    }

    public final C3412j3 a(final com.android.tools.r8.graph.B1 b1, com.android.tools.r8.graph.D2 d2) {
        Origin originI = i();
        Position position = this.b;
        String strJ = j();
        List listA = K3.a(b(), b1);
        C3470v2 c3470v2A = a();
        C3470v2 c3470v2H = h();
        boolean z = this.g;
        O2 o2D = d();
        F2 f2A = c().a(b1);
        List listA2 = K3.a(this.j, b1);
        List list = null;
        K3 k3A = e() == null ? null : e().a(b1);
        boolean zF = f();
        if (g() != null) {
            list = (List) g().stream().map(new Function() { // from class: pbh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((C3461t3) obj).a(b1);
                }
            }).collect(Collectors.toList());
        }
        return new C3412j3(originI, position, strJ, listA, c3470v2A, c3470v2H, z, o2D, f2A, listA2, k3A, zF, list, this.u.a(b1), d2);
    }

    @Override // com.android.tools.r8.shaking.C3432n3, com.android.tools.r8.shaking.Y2, com.android.tools.r8.shaking.I2
    public final boolean equals(Object obj) {
        if ((obj instanceof C3412j3) && this.u.equals(((C3412j3) obj).u)) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.android.tools.r8.shaking.C3432n3, com.android.tools.r8.shaking.Y2, com.android.tools.r8.shaking.I2
    public final int hashCode() {
        return this.u.hashCode() + (super.hashCode() * 3);
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final C3412j3 p() {
        return this;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final Iterable u() {
        return AbstractC0728Oq.a(super.u(), this.u.u());
    }

    public final S0 a(final com.android.tools.r8.graph.B1 b1, R0 r0) {
        if (g() == null || g().isEmpty()) {
            return null;
        }
        return new S0(w, Position.UNKNOWN, null, K3.a(b(), b1), a(), h(), this.g, d(), c().a(b1), K3.a(this.j, b1), e() != null ? e().a(b1) : null, f(), (List) g().stream().filter(new Predicate() { // from class: rbh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C3461t3) obj).h().b();
            }
        }).map(new Function() { // from class: tbh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C3461t3) obj).a(b1);
            }
        }).collect(Collectors.toList()), r0);
    }

    @Override // com.android.tools.r8.shaking.Y2, com.android.tools.r8.shaking.I2
    public final StringBuilder a(StringBuilder sb) {
        super.a(sb);
        sb.append('\n');
        return this.u.a(sb);
    }
}
