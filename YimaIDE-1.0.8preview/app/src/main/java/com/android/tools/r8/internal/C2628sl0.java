package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2628sl0 {
    public static final C2628sl0 c;
    public static final /* synthetic */ boolean d = true;
    public final int a;
    public final AbstractC1529fv b;

    static {
        int i = AbstractC1529fv.e;
        c = new C2628sl0(0, V40.l);
    }

    public C2628sl0(int i, AbstractC1529fv abstractC1529fv) {
        this.a = i;
        this.b = abstractC1529fv;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x008e  */
    /* JADX WARN: Code duplicated, block: B:49:0x0091 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x0093  */
    /* JADX WARN: Code duplicated, block: B:51:0x009c  */
    /* JADX WARN: Code duplicated, block: B:55:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:56:0x00b4  */
    public final C2628sl0 a(C2628sl0 c2628sl0) {
        Set setEntrySet;
        AbstractC1529fv abstractC1529fvA;
        int size;
        if (a() || c2628sl0.a()) {
            return c;
        }
        int i = this.a;
        boolean z = d;
        if (!z && c2628sl0.a()) {
            x1f.a();
            return null;
        }
        int i2 = c2628sl0.a + i;
        if (c2628sl0.b.isEmpty()) {
            if (i2 == this.a) {
                return this;
            }
            AbstractC1529fv abstractC1529fv = this.b;
            return abstractC1529fv.size() > 7 ? c : new C2628sl0(i2, abstractC1529fv);
        }
        if (this.b.isEmpty()) {
            if (!z && c2628sl0.a()) {
                x1f.a();
                return null;
            }
            if (i2 == c2628sl0.a) {
                return c2628sl0;
            }
            AbstractC1529fv abstractC1529fv2 = c2628sl0.b;
            return abstractC1529fv2.size() > 7 ? c : new C2628sl0(i2, abstractC1529fv2);
        }
        InterfaceC1231cQ interfaceC1231cQ = new C1273cv().b(this.b).b(c2628sl0.b).a;
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
        return abstractC1529fvA.size() > 7 ? c : new C2628sl0(i2, abstractC1529fvA);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ValueBoxingStatus[");
        if (a()) {
            sb.append("NOT_UNBOXABLE");
        } else {
            sb.append(this.a);
            C1191bv c1191bv = new C1191bv(this.b.entrySet().iterator());
            while (c1191bv.hasNext()) {
                AbstractC1088ai0 abstractC1088ai0 = (AbstractC1088ai0) c1191bv.next();
                sb.append(";");
                sb.append(abstractC1088ai0.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final boolean a() {
        return this == c;
    }
}
