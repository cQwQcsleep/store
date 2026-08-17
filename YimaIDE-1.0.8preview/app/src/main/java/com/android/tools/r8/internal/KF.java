package com.android.tools.r8.internal;

import com.android.tools.r8.internal.SE;
import defpackage.e58;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KF extends VF {
    public static final /* synthetic */ boolean q = true;
    public final C2516rW j;
    public final String k;
    public final InterfaceC1221cG l;
    public final C2329pF m;
    public final RE n;
    public final C2585sF o;
    public final C1475fG p;

    public KF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer, String str) {
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
        a((Object) str, "className");
        a(null, "Lcom/android/tools/r8/keepanno/annotations/KeepItemKind;", "CLASS_AND_MEMBERS");
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.J2
    public final void a() {
        if (!e().c() && f()) {
            J2 j2A = a("memberAccess");
            j2A.a(null, "Lcom/android/tools/r8/keepanno/annotations/MemberAccessFlags;", "PUBLIC");
            j2A.a(null, "Lcom/android/tools/r8/keepanno/annotations/MemberAccessFlags;", "PROTECTED");
        }
        super.a();
        List<AbstractC2757uG> listD = d();
        for (AbstractC2757uG abstractC2757uG : listD) {
            if (abstractC2757uG.f()) {
                this.j.a("cannot reference bindings");
                throw null;
            }
            ME meB = abstractC2757uG.b();
            if (meB == null) {
                if (!q && !abstractC2757uG.g()) {
                    x1f.a();
                    return;
                }
                meB = abstractC2757uG.d().h().b();
            }
            if (!AbstractC1732iG.b(this.k).equals(meB.h().c())) {
                this.j.a("must reference its class context " + this.k);
                throw null;
            }
            if (meB.e() && listD.size() == 1) {
                this.j.a("kind must include its class");
                throw null;
            }
            if (!meB.i().a()) {
                this.j.a("cannot define an 'extends' pattern.");
                throw null;
            }
            this.n.a(C2159nH.a().a(abstractC2757uG).a());
        }
        this.l.accept(this.m.a(this.o.a()).a(this.p.a()).a(this.n.a()).a());
    }

    @Override // com.android.tools.r8.internal.VF
    public final C1475fG b() {
        return this.p;
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        if (str.equals("additionalTargets")) {
            return new QF(this.j.b(str), new InterfaceC1221cG() { // from class: d58
                @Override // com.android.tools.r8.internal.InterfaceC1221cG
                public final void accept(Object obj) {
                    this.a.a((SE) obj);
                }
            }, this.p);
        }
        return super.a(str);
    }

    public final /* synthetic */ void a(SE se) {
        RE re = this.n;
        Objects.requireNonNull(re);
        se.a(new e58(re));
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
}
