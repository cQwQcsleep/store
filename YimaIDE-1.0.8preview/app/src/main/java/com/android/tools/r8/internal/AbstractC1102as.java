package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.as, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1102as extends AbstractC2209ns {
    public final C0520Gp e;

    public AbstractC1102as(AbstractC0963Xr abstractC0963Xr) {
        C0520Gp c0520Gp;
        super(abstractC0963Xr);
        C0442Dp c0442Dp = abstractC0963Xr.f;
        if (c0442Dp == null || c0442Dp.a.isEmpty()) {
            c0520Gp = C0520Gp.d;
        } else {
            c0442Dp.b = false;
            Rc0 rc0A = c0442Dp.a;
            if (c0442Dp.c) {
                rc0A = C0520Gp.a(rc0A, false);
                for (int i = 0; i < rc0A.c.size(); i++) {
                    Map.Entry entry = (Map.Entry) rc0A.c.get(i);
                    entry.setValue(C0442Dp.a((InterfaceC0468Ep) entry.getKey(), entry.getValue()));
                }
                for (Map.Entry entry2 : rc0A.i()) {
                    entry2.setValue(C0442Dp.a((InterfaceC0468Ep) entry2.getKey(), entry2.getValue()));
                }
            }
            c0520Gp = new C0520Gp(rc0A);
            c0520Gp.c = false;
        }
        this.e = c0520Gp;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns, com.android.tools.r8.internal.WN
    public final Object a(C1856jk c1856jk) {
        if (!c1856jk.c.q()) {
            return C2123ms.a(j(), c1856jk).b(this);
        }
        if (c1856jk.i != j().a) {
            w01.a("FieldDescriptor does not match message type.");
            return null;
        }
        Object objA = this.e.a((InterfaceC0468Ep) c1856jk);
        if (objA != null) {
            return objA;
        }
        if (c1856jk.m()) {
            return Collections.EMPTY_LIST;
        }
        return c1856jk.h.b == EnumC1686hk.k ? C0413Cm.a(c1856jk.i()) : c1856jk.f();
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns, com.android.tools.r8.internal.WN
    public final boolean b(C1856jk c1856jk) {
        if (!c1856jk.c.q()) {
            return C2123ms.a(j(), c1856jk).a(this);
        }
        if (c1856jk.i == j().a) {
            return this.e.b(c1856jk);
        }
        w01.a("FieldDescriptor does not match message type.");
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns, com.android.tools.r8.internal.WN
    public final Map f() {
        TreeMap treeMapI = i();
        treeMapI.putAll(this.e.a());
        return Collections.unmodifiableMap(treeMapI);
    }

    public final boolean a(AbstractC0663Md abstractC0663Md, C2285ok0 c2285ok0, C0415Co c0415Co, int i) {
        abstractC0663Md.getClass();
        return AbstractC1059aO.a(abstractC0663Md, c2285ok0, c0415Co, j().a, new YN(this.e), i);
    }

    public AbstractC1102as() {
        this.e = new C0520Gp();
    }
}
