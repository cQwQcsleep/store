package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class YN implements ZN {
    public final C0520Gp a;

    public YN(C0520Gp c0520Gp) {
        this.a = c0520Gp;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final void a(C3144yo c3144yo, C0955Xj c0955Xj, int i) {
        if (c3144yo.d.get(new C3060xo(c0955Xj, i)) != null) {
            throw new ClassCastException();
        }
    }

    @Override // com.android.tools.r8.internal.ZN
    public final ZN b(C1856jk c1856jk, Object obj) {
        List arrayList;
        C0520Gp c0520Gp = this.a;
        c0520Gp.getClass();
        if (!c1856jk.m()) {
            w01.a("addRepeatedField() can only be called on repeated fields.");
            return null;
        }
        C0520Gp.c(c1856jk, obj);
        Object objA = c0520Gp.a((InterfaceC0468Ep) c1856jk);
        if (objA == null) {
            arrayList = new ArrayList();
            c0520Gp.a.a(c1856jk, arrayList);
        } else {
            arrayList = (List) objA;
        }
        arrayList.add(obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final int a() {
        return 2;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final ZN a(C1856jk c1856jk, Object obj) {
        this.a.b(c1856jk, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final J0 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co, C1856jk c1856jk) {
        throw null;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final int a(C1856jk c1856jk) {
        return c1856jk.n() ? 2 : 1;
    }

    @Override // com.android.tools.r8.internal.ZN
    public final J0 b(AbstractC0663Md abstractC0663Md, C0415Co c0415Co, C1856jk c1856jk) {
        throw null;
    }
}
