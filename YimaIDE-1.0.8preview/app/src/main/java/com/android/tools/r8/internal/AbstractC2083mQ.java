package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2083mQ extends AbstractC2063m8 implements InterfaceC1439er, InterfaceC2156nE {
    public final boolean h;

    public AbstractC2083mQ(Class cls, String str, String str2, int i) {
        super(cls, str, str2, (i & 1) == 1);
        this.h = (i & 2) == 2;
    }

    public abstract Object a(Object obj);

    public abstract void a(Integer num, Object obj);

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        return a(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC2063m8
    public final InterfaceC2156nE c() {
        AbstractC2654t40.a.getClass();
        return this;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = obj instanceof AbstractC2083mQ;
        if (z) {
            AbstractC2083mQ abstractC2083mQ = (AbstractC2083mQ) obj;
            return e().equals(abstractC2083mQ.e()) && d().equals(abstractC2083mQ.d()) && f().equals(abstractC2083mQ.f()) && KB.a(this.c, abstractC2083mQ.c);
        }
        if (z) {
            return obj.equals(g());
        }
        return false;
    }

    public final InterfaceC2156nE g() {
        if (this.h) {
            return this;
        }
        InterfaceC2156nE interfaceC2156nE = this.b;
        if (interfaceC2156nE != null) {
            return interfaceC2156nE;
        }
        InterfaceC2156nE interfaceC2156nEC = c();
        this.b = interfaceC2156nEC;
        return interfaceC2156nEC;
    }

    public final void h() {
        if (this.h) {
            c41.a("Kotlin reflection is not yet supported for synthetic Java properties");
            return;
        }
        InterfaceC2156nE interfaceC2156nEG = g();
        if (interfaceC2156nEG == this) {
            throw new SI();
        }
        ((AbstractC2083mQ) interfaceC2156nEG).h();
    }

    public final int hashCode() {
        return f().hashCode() + ((d().hashCode() + (e().hashCode() * 31)) * 31);
    }

    public final void i() {
        if (this.h) {
            c41.a("Kotlin reflection is not yet supported for synthetic Java properties");
            return;
        }
        InterfaceC2156nE interfaceC2156nEG = g();
        if (interfaceC2156nEG == this) {
            throw new SI();
        }
        ((AbstractC2083mQ) interfaceC2156nEG).i();
    }

    public final String toString() {
        InterfaceC2156nE interfaceC2156nEG = g();
        if (interfaceC2156nEG != this) {
            return interfaceC2156nEG.toString();
        }
        return "property " + d() + " (Kotlin reflection is not available)";
    }

    public AbstractC2083mQ() {
        super(null, null, null, false);
        this.h = false;
    }
}
