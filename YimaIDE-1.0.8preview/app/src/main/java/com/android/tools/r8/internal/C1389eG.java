package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2072mF;
import com.android.tools.r8.internal.C1389eG;
import com.android.tools.r8.internal.SE;
import defpackage.dye;
import defpackage.e58;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1389eG extends K2 {
    public static final /* synthetic */ boolean l = true;
    public final C2516rW c;
    public final InterfaceC1221cG d;
    public final AbstractC2671tG e;
    public final C2329pF f;
    public final C2585sF g;
    public final C1475fG h;
    public final RE i;
    public MF j;
    public final EF k;

    public C1389eG(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer, AG ag) {
        super(c2516rW);
        this.f = new C2329pF();
        C2585sF c2585sF = new C2585sF();
        this.g = c2585sF;
        this.h = new C1475fG();
        this.i = new RE();
        this.j = MF.c;
        this.c = c2516rW;
        this.d = interfaceC1221cG;
        this.e = ag;
        consumer.accept(c2585sF);
        this.k = new EF(c2516rW);
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        MF mf;
        if (!str2.equals("Lcom/android/tools/r8/keepanno/annotations/KeepItemKind;")) {
            super.a(str, str2, str3);
            throw null;
        }
        str3.getClass();
        switch (str3) {
            case "ONLY_FIELDS":
                mf = MF.e;
                break;
            case "ONLY_MEMBERS":
                mf = MF.c;
                break;
            case "ONLY_METHODS":
                mf = MF.d;
                break;
            case "CLASS_AND_MEMBERS":
                mf = MF.f;
                break;
            case "CLASS_AND_METHODS":
                mf = MF.g;
                break;
            case "CLASS_AND_FIELDS":
                mf = MF.h;
                break;
            case "ONLY_CLASS":
                mf = MF.b;
                break;
            default:
                mf = null;
                break;
        }
        if (mf != null) {
            this.j = mf;
        } else {
            super.a(str, str2, str3);
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (str.equals("description") && (obj instanceof String)) {
            C2585sF c2585sF = this.g;
            c2585sF.getClass();
            c2585sF.b = new C2841vF((String) obj);
            return;
        }
        super.a(obj, str);
        throw null;
    }

    public static /* synthetic */ void a(AbstractC2072mF abstractC2072mF) {
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        C3030xW c3030xWB = this.c.b(str);
        if (str.equals("preconditions")) {
            C2329pF c2329pF = this.f;
            Objects.requireNonNull(c2329pF);
            return new WF(c3030xWB, new dye(c2329pF), this.h);
        }
        if (str.equals("additionalTargets")) {
            return new QF(c3030xWB, new InterfaceC1221cG() { // from class: ctg
                @Override // com.android.tools.r8.internal.InterfaceC1221cG
                public final void accept(Object obj) {
                    this.a.a((SE) obj);
                }
            }, this.h);
        }
        EF ef = this.k;
        new Consumer() { // from class: dtg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1389eG.a((AbstractC2072mF) obj);
            }
        };
        J2 j2A = ef.a(str);
        if (j2A != null) {
            return j2A;
        }
        super.a(str);
        throw null;
    }

    public final /* synthetic */ void a(SE se) {
        RE re = this.i;
        Objects.requireNonNull(re);
        se.a(new e58(re));
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        MF mf = this.j;
        MF mf2 = MF.b;
        if (!mf.equals(mf2)) {
            if (!l && !this.e.e()) {
                x1f.a();
                return;
            }
            AG agB = this.e.b();
            MF mf3 = this.j;
            if (mf3.equals(mf2) || mf3.equals(MF.f) || mf3.equals(MF.g) || mf3.equals(MF.h)) {
                RE re = this.i;
                C1986lF c1986lF = C1986lF.b;
                PE pe = agB.a;
                if (pe != null) {
                    re.a.add(new C2159nH(pe, c1986lF));
                } else {
                    defpackage.l0.a("Target must define an item pattern");
                    return;
                }
            }
            a(agB.b);
            RE re2 = this.i;
            re2.a.add(new C2159nH(this.e.f(), this.k.d()));
            InterfaceC1221cG interfaceC1221cG = this.d;
            C2329pF c2329pF = this.f;
            c2329pF.a = this.g.a();
            c2329pF.b = this.h.a.a();
            c2329pF.d = this.i.a();
            interfaceC1221cG.accept(c2329pF.a());
            return;
        }
        C2516rW c2516rW = this.c;
        c2516rW.getClass();
        throw new C3096yE(c2516rW, "kind must include its member");
    }

    public final void a(FG fg) {
        if (!fg.g()) {
            if (fg.h() && !this.j.b()) {
                this.c.a("Kind " + this.j + " cannot be use when annotating a method");
                throw null;
            }
            if (!fg.f() || this.j.a()) {
                return;
            }
            this.c.a("Kind " + this.j + " cannot be use when annotating a field");
            throw null;
        }
        this.c.a("Unexpected general pattern for context.");
        throw null;
    }
}
