package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.C3461t3;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.m3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3427m3 extends C3432n3 {

    /* JADX INFO: renamed from: com.android.tools.r8.shaking.m3$a */
    public static class a extends C3432n3.a<C3427m3, a> {
        @Override // com.android.tools.r8.shaking.I2.a
        public final I2.a c() {
            return this;
        }

        @Override // com.android.tools.r8.shaking.I2.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public C3427m3 a() {
            Origin origin = this.a;
            Position positionB = b();
            String str = this.d;
            AbstractC0551Hu abstractC0551HuA = this.e.a();
            C3470v2 c3470v2 = this.f;
            C3470v2 c3470v3 = this.g;
            boolean z = this.h;
            O2 o2 = this.i;
            F2 f2 = this.j;
            AbstractC0551Hu abstractC0551HuA2 = this.k.a();
            K3 k3 = this.l;
            boolean z2 = this.m;
            List list = this.n;
            EnumC3447q3 enumC3447q3 = this.p;
            C3437o3.a aVar = this.q;
            return new C3427m3(origin, positionB, str, abstractC0551HuA, c3470v2, c3470v3, z, o2, f2, abstractC0551HuA2, k3, z2, list, enumC3447q3, new C3437o3(aVar.a, aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, aVar.g, aVar.h));
        }
    }

    public C3427m3(Origin origin, Position position, String str, List list, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, List list2, K3 k3, boolean z2, List list3, EnumC3447q3 enumC3447q3, C3437o3 c3437o3) {
        super(origin, position, str, list, c3470v2, c3470v3, z, o2, f2, list2, k3, z2, list3, enumC3447q3, c3437o3);
    }

    public static a H() {
        return new a();
    }

    public C3427m3 a(final com.android.tools.r8.graph.B1 b1) {
        K3 k3A;
        List list;
        Origin originI = i();
        Position position = this.b;
        String strJ = j();
        List listA = K3.a(b(), b1);
        C3470v2 c3470v2A = a();
        C3470v2 c3470v2H = h();
        boolean z = this.g;
        O2 o2D = d();
        F2 f2A = c() == null ? null : c().a(b1);
        List listA2 = K3.a(this.j, b1);
        if (e() == null) {
            k3A = null;
            list = null;
        } else {
            k3A = e().a(b1);
            list = null;
        }
        boolean zF = f();
        if (g() != null) {
            list = (List) g().stream().map(new Function() { // from class: rkh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((C3461t3) obj).a(b1);
                }
            }).collect(Collectors.toList());
        }
        return new C3427m3(originI, position, strJ, listA, c3470v2A, c3470v2H, z, o2D, f2A, listA2, k3A, zF, list, G(), F());
    }

    @Override // com.android.tools.r8.shaking.C3432n3, com.android.tools.r8.shaking.Y2, com.android.tools.r8.shaking.I2
    public final boolean equals(Object obj) {
        if (obj instanceof C3427m3) {
            return super.equals((C3427m3) obj);
        }
        return false;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final C3427m3 q() {
        return this;
    }

    public static void a(StringBuilder sb, String str, Object obj) {
        if (obj == null) {
            return;
        }
        String string = obj.toString();
        if (string.isEmpty()) {
            return;
        }
        if (str != null) {
            sb.append(str);
        }
        sb.append(string);
        sb.append(" ");
    }

    public static C3427m3 a(Consumer<C3437o3.a> consumer) {
        a aVarH = H();
        aVarH.a = new C3422l3(Origin.root());
        aVarH.a(O2.c);
        aVarH.a(F2.a(L3.b));
        C3461t3.a aVar = new C3461t3.a();
        aVar.a(EnumC3476w3.d);
        aVarH.a(new Bc0(aVar.a()));
        aVarH.a(EnumC3447q3.b);
        consumer.accept(aVarH.q);
        C3427m3 c3427m3A = aVarH.a();
        c3427m3A.o = true;
        return c3427m3A;
    }
}
