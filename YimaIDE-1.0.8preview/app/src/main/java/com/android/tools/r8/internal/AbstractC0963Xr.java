package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0963Xr extends AbstractC0911Vr {
    public C0442Dp f;

    public AbstractC0963Xr() {
        super(null);
    }

    public final void a(AbstractC1102as abstractC1102as) {
        Rc0 rc0;
        if (abstractC1102as.e != null) {
            if (this.f == null) {
                C0520Gp c0520Gp = C0520Gp.d;
                this.f = new C0442Dp();
            }
            C0442Dp c0442Dp = this.f;
            C0520Gp c0520Gp2 = abstractC1102as.e;
            if (!c0442Dp.b) {
                c0442Dp.a = C0520Gp.a(c0442Dp.a, true);
                c0442Dp.b = true;
            }
            int i = 0;
            while (true) {
                int size = c0520Gp2.a.c.size();
                rc0 = c0520Gp2.a;
                if (i >= size) {
                    break;
                }
                c0442Dp.a((Map.Entry) rc0.c.get(i));
                i++;
            }
            Iterator it = rc0.i().iterator();
            while (it.hasNext()) {
                c0442Dp.a((Map.Entry) it.next());
            }
            p();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr, com.android.tools.r8.internal.WN
    public final boolean b(C1856jk c1856jk) {
        if (!c1856jk.c.q()) {
            return C2123ms.a(n(), c1856jk).a(this);
        }
        if (c1856jk.i != e()) {
            w01.a("FieldDescriptor does not match message type.");
            return false;
        }
        C0442Dp c0442Dp = this.f;
        if (c0442Dp != null) {
            if (c1856jk.m()) {
                w01.a("hasField() can only be called on non-repeated fields.");
                return false;
            }
            if (c0442Dp.a.get(c1856jk) != null) {
                return true;
            }
        }
        return false;
    }

    public final AbstractC0963Xr c(C1856jk c1856jk, Object obj) {
        List arrayList;
        if (!c1856jk.c.q()) {
            C2123ms.a(n(), c1856jk).a(this, obj);
            return this;
        }
        if (c1856jk.i != e()) {
            w01.a("FieldDescriptor does not match message type.");
            return null;
        }
        if (this.f == null) {
            C0520Gp c0520Gp = C0520Gp.d;
            this.f = new C0442Dp();
        }
        C0442Dp c0442Dp = this.f;
        boolean z = true;
        if (!c0442Dp.b) {
            c0442Dp.a = C0520Gp.a(c0442Dp.a, true);
            c0442Dp.b = true;
        }
        if (!c1856jk.m()) {
            w01.a("addRepeatedField() can only be called on repeated fields.");
            return null;
        }
        if (!c0442Dp.c && !(obj instanceof SN)) {
            z = false;
        }
        c0442Dp.c = z;
        C0442Dp.b(c1856jk, obj);
        Object objA = C0442Dp.a(c1856jk, c0442Dp.a.get(c1856jk));
        if (objA == null) {
            arrayList = new ArrayList();
            c0442Dp.a.a(c1856jk, arrayList);
        } else {
            arrayList = (List) objA;
        }
        arrayList.add(obj);
        p();
        return this;
    }

    public final AbstractC0963Xr d(C1856jk c1856jk, Object obj) {
        if (!c1856jk.c.q()) {
            C2123ms.a(n(), c1856jk).b(this, obj);
            return this;
        }
        if (c1856jk.i != e()) {
            w01.a("FieldDescriptor does not match message type.");
            return null;
        }
        if (this.f == null) {
            C0520Gp c0520Gp = C0520Gp.d;
            this.f = new C0442Dp();
        }
        C0442Dp c0442Dp = this.f;
        boolean z = true;
        if (!c0442Dp.b) {
            c0442Dp.a = C0520Gp.a(c0442Dp.a, true);
            c0442Dp.b = true;
        }
        if (!c1856jk.m()) {
            C0442Dp.b(c1856jk, obj);
        } else {
            if (!(obj instanceof List)) {
                w01.a("Wrong object type used with protocol message reflection.");
                return null;
            }
            ArrayList arrayList = new ArrayList((List) obj);
            for (Object obj2 : arrayList) {
                C0442Dp.b(c1856jk, obj2);
                c0442Dp.c = c0442Dp.c || (obj2 instanceof SN);
            }
            obj = arrayList;
        }
        if (!c0442Dp.c && !(obj instanceof SN)) {
            z = false;
        }
        c0442Dp.c = z;
        c0442Dp.a.a(c1856jk, obj);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr, com.android.tools.r8.internal.WN
    public final Map f() {
        Map mapUnmodifiableMap;
        TreeMap treeMapL = l();
        C0442Dp c0442Dp = this.f;
        if (c0442Dp != null) {
            Rc0 rc0 = c0442Dp.a;
            if (!rc0.e) {
                mapUnmodifiableMap = rc0;
                mapUnmodifiableMap = Collections.unmodifiableMap(rc0);
            }
            mapUnmodifiableMap = rc0;
            treeMapL.putAll(mapUnmodifiableMap);
        }
        return Collections.unmodifiableMap(treeMapL);
    }

    public AbstractC0963Xr(InterfaceC0937Wr interfaceC0937Wr) {
        super(interfaceC0937Wr);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr, com.android.tools.r8.internal.WN
    public final Object a(C1856jk c1856jk) {
        if (c1856jk.c.q()) {
            if (c1856jk.i == e()) {
                C0442Dp c0442Dp = this.f;
                Object objA = c0442Dp != null ? C0442Dp.a(c1856jk, c0442Dp.a.get(c1856jk)) : null;
                if (objA != null) {
                    return objA;
                }
                if (c1856jk.h.b == EnumC1686hk.k) {
                    return C0413Cm.a(c1856jk.i());
                }
                return c1856jk.f();
            }
            w01.a("FieldDescriptor does not match message type.");
            return null;
        }
        return super.a(c1856jk);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr, com.android.tools.r8.internal.H0
    public final H0 c(C1856jk c1856jk) {
        if (c1856jk.c.q()) {
            return new C0387Bm(c1856jk.i());
        }
        return C2123ms.a(n(), c1856jk).a();
    }
}
