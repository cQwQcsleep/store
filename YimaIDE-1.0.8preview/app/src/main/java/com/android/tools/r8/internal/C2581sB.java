package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.AbstractC1133bC;
import com.android.tools.r8.internal.C2581sB;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2581sB extends AbstractC1832jW {
    public static final /* synthetic */ boolean g = true;
    public final Set a;
    public final Set b;
    public final Set c;
    public final boolean d;
    public final boolean e;
    public final boolean f;

    public C2581sB(AbstractC2554rv abstractC2554rv, AbstractC2554rv abstractC2554rv2, AbstractC2554rv abstractC2554rv3, boolean z, boolean z2, boolean z3) {
        if (!g && abstractC2554rv.isEmpty() && abstractC2554rv2.isEmpty() && abstractC2554rv3.isEmpty() && !z && !z2 && !z3) {
            x1f.a();
            throw null;
        }
        this.a = abstractC2554rv;
        this.b = abstractC2554rv2;
        this.c = abstractC2554rv3;
        this.d = z;
        this.e = z2;
        this.f = z3;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(com.android.tools.r8.graph.I2 i2) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) this.a);
        c1870jv.a(i2);
        AbstractC2554rv abstractC2554rvA = c1870jv.a();
        Set set = this.b;
        return new C2581sB(abstractC2554rvA, (AbstractC2554rv) set, (AbstractC2554rv) this.c, this.d, this.e, this.f);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x002d  */
    /* JADX WARN: Code duplicated, block: B:11:0x0036  */
    /* JADX WARN: Code duplicated, block: B:15:0x004b  */
    /* JADX WARN: Code duplicated, block: B:16:0x004e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0028  */
    /* JADX WARN: Code duplicated, block: B:9:0x002b A[DONT_INVERT] */
    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW c() {
        Set setEntrySet;
        AbstractC1529fv abstractC1529fvA;
        int size;
        int i = AbstractC1529fv.e;
        final C1273cv c1273cv = new C1273cv();
        this.c.forEach(new Consumer() { // from class: pai
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2581sB.a(c1273cv, (AbstractC1133bC) obj);
            }
        });
        Set set = this.a;
        Set set2 = this.b;
        InterfaceC1231cQ interfaceC1231cQ = c1273cv.a;
        if (interfaceC1231cQ instanceof AbstractC1529fv) {
            abstractC1529fvA = (AbstractC1529fv) interfaceC1231cQ;
            if (abstractC1529fvA.e()) {
                if (interfaceC1231cQ == null) {
                    if (interfaceC1231cQ != null) {
                        size = interfaceC1231cQ.F().size();
                    } else {
                        size = 11;
                    }
                    C1736iK c1736iK = new C1736iK(size);
                    AbstractC3179zC.a((Iterable) interfaceC1231cQ, (Collection) c1736iK);
                    interfaceC1231cQ = c1736iK;
                }
                setEntrySet = interfaceC1231cQ.entrySet();
                if (setEntrySet.isEmpty()) {
                    abstractC1529fvA = V40.l;
                } else {
                    abstractC1529fvA = V40.a((Collection) setEntrySet);
                }
            }
        } else {
            if (interfaceC1231cQ == null) {
                if (interfaceC1231cQ != null) {
                    size = interfaceC1231cQ.F().size();
                } else {
                    size = 11;
                }
                C1736iK c1736iK2 = new C1736iK(size);
                AbstractC3179zC.a((Iterable) interfaceC1231cQ, (Collection) c1736iK2);
                interfaceC1231cQ = c1736iK2;
            }
            setEntrySet = interfaceC1231cQ.entrySet();
            if (setEntrySet.isEmpty()) {
                abstractC1529fvA = V40.l;
            } else {
                abstractC1529fvA = V40.a((Collection) setEntrySet);
            }
        }
        return new SR(set, set2, abstractC1529fvA, this.d, this.e, this.f);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final boolean d() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW e() {
        if (this.d) {
            return this;
        }
        Set set = this.a;
        Set set2 = this.b;
        return new C2581sB((AbstractC2554rv) set, (AbstractC2554rv) set2, (AbstractC2554rv) this.c, true, this.e, this.f);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == C2581sB.class) {
            C2581sB c2581sB = (C2581sB) obj;
            if (this.d == c2581sB.d && this.e == c2581sB.e && this.f == c2581sB.f && this.a.equals(c2581sB.a) && this.b.equals(c2581sB.b) && this.c.equals(c2581sB.c)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW f() {
        if (this.e) {
            return this;
        }
        Set set = this.a;
        Set set2 = this.b;
        return new C2581sB((AbstractC2554rv) set, (AbstractC2554rv) set2, (AbstractC2554rv) this.c, this.d, true, this.f);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW g() {
        if (this.f) {
            return this;
        }
        Set set = this.a;
        Set set2 = this.b;
        return new C2581sB((AbstractC2554rv) set, (AbstractC2554rv) set2, (AbstractC2554rv) this.c, this.d, this.e, true);
    }

    public final int hashCode() {
        int iHashCode = this.c.hashCode() + ((this.b.hashCode() + ((this.a.hashCode() + 31) * 31)) * 31);
        if (g || iHashCode == Objects.hash(this.a, this.b, this.c)) {
            return Y6.a(this.f) | (((((iHashCode << 1) | Y6.a(this.d)) << 1) | Y6.a(this.e)) << 1);
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final C2581sB a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(C0245l1 c0245l1) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) this.b);
        c1870jv.a(c0245l1);
        Set set = this.a;
        return new C2581sB((AbstractC2554rv) set, c1870jv.a(), (AbstractC2554rv) this.c, this.d, this.e, this.f);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(AbstractC1133bC abstractC1133bC) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) this.c);
        c1870jv.a(abstractC1133bC);
        Set set = this.a;
        return new C2581sB((AbstractC2554rv) set, (AbstractC2554rv) this.b, c1870jv.a(), this.d, this.e, this.f);
    }

    public static void a(C1273cv c1273cv, AbstractC1133bC abstractC1133bC) {
        C0322w2 c0322w2U2 = abstractC1133bC.U2();
        InterfaceC1231cQ interfaceC1231cQ = c1273cv.a;
        c0322w2U2.getClass();
        interfaceC1231cQ.add(c0322w2U2);
    }
}
