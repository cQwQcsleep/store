package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Cm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0413Cm extends J0 {
    public final C0955Xj d;
    public final C0520Gp e;
    public final C1856jk[] f;
    public final C2712tk0 g;
    public int h = -1;

    public C0413Cm(C0955Xj c0955Xj, C0520Gp c0520Gp, C1856jk[] c1856jkArr, C2712tk0 c2712tk0) {
        this.d = c0955Xj;
        this.e = c0520Gp;
        this.f = c1856jkArr;
        this.g = c2712tk0;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        Rc0 rc0;
        Rc0 rc1;
        boolean z = this.d.b.l().g;
        C0520Gp c0520Gp = this.e;
        int i = 0;
        if (!z) {
            while (true) {
                int size = c0520Gp.a.c.size();
                rc0 = c0520Gp.a;
                if (i >= size) {
                    break;
                }
                Map.Entry entry = (Map.Entry) rc0.c.get(i);
                C0520Gp.a((InterfaceC0468Ep) entry.getKey(), entry.getValue(), abstractC0793Rd);
                i++;
            }
            for (Map.Entry entry2 : rc0.i()) {
                C0520Gp.a((InterfaceC0468Ep) entry2.getKey(), entry2.getValue(), abstractC0793Rd);
            }
            this.g.a(abstractC0793Rd);
            return;
        }
        int i2 = 0;
        while (true) {
            int size2 = c0520Gp.a.c.size();
            rc1 = c0520Gp.a;
            if (i2 >= size2) {
                break;
            }
            C0520Gp.a((Map.Entry) rc1.c.get(i2), abstractC0793Rd);
            i2++;
        }
        Iterator it = rc1.i().iterator();
        while (it.hasNext()) {
            C0520Gp.a((Map.Entry) it.next(), abstractC0793Rd);
        }
        for (Map.Entry entry3 : this.g.b.entrySet()) {
            C2456qk0 c2456qk0 = (C2456qk0) entry3.getValue();
            int iIntValue = ((Integer) entry3.getKey()).intValue();
            for (U7 u7 : c2456qk0.d) {
                C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
                c0689Nd.c(1, 3);
                c0689Nd.c(2, 0);
                c0689Nd.f(iIntValue);
                c0689Nd.c(3, 2);
                c0689Nd.b(u7);
                c0689Nd.c(1, 4);
            }
        }
    }

    @Override // com.android.tools.r8.internal.WN
    public final boolean b(C1856jk c1856jk) {
        if (c1856jk.i == this.d) {
            return this.e.b(c1856jk);
        }
        w01.a("FieldDescriptor does not match message type.");
        return false;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iC;
        Rc0 rc0;
        int i = this.h;
        if (i != -1) {
            return i;
        }
        boolean z = this.d.b.l().g;
        C0520Gp c0520Gp = this.e;
        if (z) {
            int i2 = 0;
            int iA = 0;
            while (true) {
                int size = c0520Gp.a.c.size();
                rc0 = c0520Gp.a;
                if (i2 >= size) {
                    break;
                }
                iA += C0520Gp.a((Map.Entry) rc0.c.get(i2));
                i2++;
            }
            Iterator it = rc0.i().iterator();
            while (it.hasNext()) {
                iA += C0520Gp.a((Map.Entry) it.next());
            }
            int i3 = 0;
            for (Map.Entry entry : this.g.b.entrySet()) {
                C2456qk0 c2456qk0 = (C2456qk0) entry.getValue();
                int iIntValue = ((Integer) entry.getKey()).intValue();
                Iterator it2 = c2456qk0.d.iterator();
                int iA2 = 0;
                while (it2.hasNext()) {
                    iA2 += AbstractC0793Rd.a((U7) it2.next()) + AbstractC0793Rd.b(3) + AbstractC0484Ff.a(iIntValue, AbstractC0793Rd.b(2), AbstractC0793Rd.b(1) * 2);
                }
                i3 += iA2;
            }
            iC = iA + i3;
        } else {
            iC = this.g.c() + c0520Gp.b();
        }
        this.h = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return new C0387Bm(this.d).a(this);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.WN
    public final Map f() {
        return this.e.a();
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return new C0387Bm(this.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return a(this.d);
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        return a(this.d, this.e);
    }

    public static C0413Cm a(C0955Xj c0955Xj) {
        return new C0413Cm(c0955Xj, C0520Gp.d, new C1856jk[c0955Xj.b.l.size()], C2712tk0.c);
    }

    public static boolean a(C0955Xj c0955Xj, C0520Gp c0520Gp) {
        char c;
        for (C1856jk c1856jk : Collections.unmodifiableList(Arrays.asList(c0955Xj.g))) {
            int i = c1856jk.c.h;
            if (i == 1) {
                c = 1;
            } else if (i != 2) {
                c = 3;
                if (i != 3) {
                    c = 0;
                }
            } else {
                c = 2;
            }
            if ((c != 0 ? c : (char) 1) == 2 && !c0520Gp.b(c1856jk)) {
                return false;
            }
        }
        return c0520Gp.c();
    }

    @Override // com.android.tools.r8.internal.WN
    public final Object a(C1856jk c1856jk) {
        if (c1856jk.i == this.d) {
            Object objA = this.e.a((InterfaceC0468Ep) c1856jk);
            if (objA != null) {
                return objA;
            }
            if (c1856jk.m()) {
                return Collections.EMPTY_LIST;
            }
            if (c1856jk.h.b == EnumC1686hk.k) {
                return a(c1856jk.i());
            }
            return c1856jk.f();
        }
        w01.a("FieldDescriptor does not match message type.");
        return null;
    }
}
