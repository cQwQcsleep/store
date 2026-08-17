package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2072mF;
import com.android.tools.r8.internal.C1305dG;
import com.android.tools.r8.internal.SE;
import defpackage.dye;
import defpackage.e58;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1305dG extends VF {
    public final C2516rW j;
    public final String k;
    public final InterfaceC1221cG l;
    public final C2329pF m;
    public final RE n;
    public final C2585sF o;
    public final C1475fG p;
    public final EF q;

    public C1305dG(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer, String str) {
        super(c2516rW);
        this.m = new C2329pF();
        this.n = new RE();
        C2585sF c2585sF = new C2585sF();
        this.o = c2585sF;
        this.p = new C1475fG();
        this.j = c2516rW;
        this.k = str;
        this.l = interfaceC1221cG;
        consumer.accept(c2585sF);
        this.q = new EF(c2516rW);
        a((Object) str, "className");
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.J2
    public final void a() {
        if (e() == null && !f()) {
            a(null, "Lcom/android/tools/r8/keepanno/annotations/KeepItemKind;", "CLASS_AND_MEMBERS");
        }
        super.a();
        for (AbstractC2757uG abstractC2757uG : d()) {
            if (abstractC2757uG.f()) {
                this.j.a("cannot reference bindings");
                throw null;
            }
            AbstractC2671tG abstractC2671tGC = abstractC2757uG.c();
            ME meA = abstractC2671tGC.d() ? abstractC2671tGC.a() : abstractC2671tGC.b().h().b();
            if (!AbstractC1732iG.b(this.k).equals(meA.h().c())) {
                this.j.a("must reference its class context " + this.k);
                throw null;
            }
            if (!meA.i().a()) {
                this.j.a("cannot define an 'extends' pattern.");
                throw null;
            }
            this.n.a(C2159nH.a().a(abstractC2671tGC).a(this.q.d()).a());
        }
        this.l.accept(this.m.a(this.o.a()).a(this.p.a()).a(this.n.a()).a());
    }

    @Override // com.android.tools.r8.internal.VF
    public final C1475fG b() {
        return this.p;
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (str.equals("description") && (obj instanceof String)) {
            C2585sF c2585sF = this.o;
            c2585sF.getClass();
            c2585sF.b = new C2841vF((String) obj);
            return;
        }
        super.a(obj, str);
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        C3030xW c3030xWB = this.j.b(str);
        if (str.equals("preconditions")) {
            C2329pF c2329pF = this.m;
            Objects.requireNonNull(c2329pF);
            return new WF(c3030xWB, new dye(c2329pF), this.p);
        }
        if (str.equals("additionalTargets")) {
            return new QF(c3030xWB, new InterfaceC1221cG() { // from class: jmg
                @Override // com.android.tools.r8.internal.InterfaceC1221cG
                public final void accept(Object obj) {
                    this.a.a((SE) obj);
                }
            }, this.p);
        }
        EF ef = this.q;
        new Consumer() { // from class: kmg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1305dG.a((AbstractC2072mF) obj);
            }
        };
        J2 j2A = ef.a(str);
        return j2A != null ? j2A : super.a(str);
    }

    public final /* synthetic */ void a(SE se) {
        RE re = this.n;
        Objects.requireNonNull(re);
        se.a(new e58(re));
    }

    public static /* synthetic */ void a(AbstractC2072mF abstractC2072mF) {
    }
}
