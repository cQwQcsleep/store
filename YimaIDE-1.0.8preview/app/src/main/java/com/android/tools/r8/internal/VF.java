package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class VF extends K2 {
    public static final /* synthetic */ boolean i = true;
    public final AbstractC3114yW c;
    public String d;
    public MF e;
    public final DF f;
    public final ZF g;
    public AbstractC2757uG h;

    public VF(AbstractC3114yW abstractC3114yW) {
        super(abstractC3114yW);
        this.d = null;
        this.e = null;
        this.h = null;
        this.c = abstractC3114yW;
        this.f = new DF(abstractC3114yW, new Supplier() { // from class: s6f
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.b();
            }
        });
        this.g = new ZF(abstractC3114yW);
    }

    @Override // com.android.tools.r8.internal.J2
    public void a() {
        if (this.d != null) {
            if (this.f.a() || this.g.a() || this.e != null) {
                this.c.a("Cannot define an item explicitly and via a member-binding reference");
                throw null;
            }
            this.h = EE.a(b().a(this.d)).d();
            return;
        }
        FG fgC = this.g.c();
        if (this.e == null) {
            if (fgC == null) {
                this.e = MF.b;
            } else if (fgC.h()) {
                this.e = MF.d;
            } else if (fgC.f()) {
                this.e = MF.e;
            } else if (fgC.g()) {
                this.e = MF.c;
            } else if (!i) {
                x1f.a();
                return;
            }
        }
        if (this.e.c()) {
            if (!this.g.a()) {
                this.h = this.f.c();
                return;
            }
            this.c.a("Item pattern for members is incompatible with kind " + this.e);
            throw null;
        }
        boolean z = i;
        if (!z && !this.e.e()) {
            x1f.a();
            return;
        }
        if (fgC == null) {
            fgC = FG.a();
        }
        if (this.e.f() && !fgC.h()) {
            if (!fgC.g()) {
                if (!z && !fgC.f()) {
                    x1f.a();
                    return;
                }
                this.c.a("Item pattern for fields is incompatible with kind " + this.e);
                throw null;
            }
            fgC = NG.i().a(fgC).a();
        }
        if (this.e.d() && !fgC.f()) {
            if (!fgC.g()) {
                if (!z && !fgC.h()) {
                    x1f.a();
                    return;
                }
                this.c.a("Item pattern for methods is incompatible with kind " + this.e);
                throw null;
            }
            fgC = C2158nG.i().a(fgC).a();
        }
        this.h = AG.g().a(this.f.c()).a(fgC).a().f();
    }

    public abstract C1475fG b();

    public final AbstractC2757uG c() {
        AbstractC2757uG abstractC2757uG = this.h;
        if (abstractC2757uG != null) {
            return abstractC2757uG;
        }
        AbstractC3114yW abstractC3114yW = this.c;
        abstractC3114yW.getClass();
        throw new C3096yE(abstractC3114yW, "Item reference not finalized. Missing call to visitEnd()");
    }

    public final List d() {
        AG agB;
        PE oe;
        AbstractC2757uG abstractC2757uG = this.h;
        if (abstractC2757uG == null) {
            AbstractC3114yW abstractC3114yW = this.c;
            abstractC3114yW.getClass();
            throw new C3096yE(abstractC3114yW, "Item reference not finalized. Missing call to visitEnd()");
        }
        if (abstractC2757uG.f()) {
            return Collections.singletonList(this.h);
        }
        MF mf = this.e;
        if (mf == null) {
            AbstractC3114yW abstractC3114yW2 = this.c;
            abstractC3114yW2.getClass();
            throw new C3096yE(abstractC3114yW2, "Unexpected state: unknown kind for an item pattern");
        }
        MF mf2 = MF.b;
        if ((!mf.equals(mf2) && !mf.equals(MF.f) && !mf.equals(MF.g) && !mf.equals(MF.h)) || mf.equals(mf2)) {
            return Collections.singletonList(this.h);
        }
        if (!i && this.h.f()) {
            x1f.a();
            return null;
        }
        AbstractC2671tG abstractC2671tGC = this.h.c();
        if (abstractC2671tGC.d()) {
            ME meA = abstractC2671tGC.a();
            meA.getClass();
            oe = new OE(meA);
            ME.g();
            agB = new AG(oe, EG.d);
        } else {
            agB = abstractC2671tGC.b();
            oe = agB.a;
        }
        return AbstractC0551Hu.a(oe, new CG(agB));
    }

    public final MF e() {
        return this.e;
    }

    public final boolean f() {
        return !this.g.a();
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public void a(Object obj, String str) {
        if (str.equals("memberFromBinding") && (obj instanceof String)) {
            this.d = (String) obj;
        } else {
            if (this.f.a(obj, str) || this.g.a(obj, str)) {
                return;
            }
            super.a(obj, str);
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        J2 j2A = this.f.a(str, str2);
        if (j2A != null) {
            return j2A;
        }
        J2 j2A2 = this.g.a(str, str2);
        if (j2A2 != null) {
            return j2A2;
        }
        super.a(str, str2);
        throw null;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public J2 a(String str) {
        J2 j2A = this.g.a(str);
        if (j2A != null) {
            return j2A;
        }
        super.a(str);
        throw null;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        MF mf;
        if (str2.equals("Lcom/android/tools/r8/keepanno/annotations/KeepItemKind;")) {
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
                this.e = mf;
                return;
            } else {
                super.a(str, str2, str3);
                throw null;
            }
        }
        super.a(str, str2, str3);
        throw null;
    }
}
