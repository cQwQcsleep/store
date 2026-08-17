package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1941kk extends AbstractC2027lk {
    public final C1769ij b;
    public final C0955Xj[] c;
    public final C1260ck[] d;
    public final C2284ok[] e;
    public final C1856jk[] f;
    public final C1941kk[] g;
    public final C1007Zj h;

    public C1941kk(C1769ij c1769ij, C1941kk[] c1941kkArr, C1007Zj c1007Zj) throws C1091ak {
        this.h = c1007Zj;
        this.b = c1769ij;
        HashMap map = new HashMap();
        for (C1941kk c1941kk : c1941kkArr) {
            map.put(c1941kk.b.k(), c1941kk);
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            C0945Wz c0945Wz = (C0945Wz) c1769ij.i;
            if (i >= c0945Wz.d) {
                C1941kk[] c1941kkArr2 = new C1941kk[arrayList.size()];
                this.g = c1941kkArr2;
                arrayList.toArray(c1941kkArr2);
                c1007Zj.a(this, this.b.m());
                this.c = c1769ij.k.size() > 0 ? new C0955Xj[c1769ij.k.size()] : AbstractC2370pk.c;
                for (int i2 = 0; i2 < c1769ij.k.size(); i2++) {
                    this.c[i2] = new C0955Xj((C0487Fi) c1769ij.k.get(i2), this, null);
                }
                this.d = c1769ij.l.size() > 0 ? new C1260ck[c1769ij.l.size()] : AbstractC2370pk.e;
                for (int i3 = 0; i3 < c1769ij.l.size(); i3++) {
                    this.d[i3] = new C1260ck((C0643Li) c1769ij.l.get(i3), this, null);
                }
                this.e = c1769ij.m.size() > 0 ? new C2284ok[c1769ij.m.size()] : AbstractC2370pk.f;
                for (int i4 = 0; i4 < c1769ij.m.size(); i4++) {
                    this.e[i4] = new C2284ok((C0462Ej) c1769ij.m.get(i4), this);
                }
                this.f = c1769ij.n.size() > 0 ? new C1856jk[c1769ij.n.size()] : AbstractC2370pk.d;
                for (int i5 = 0; i5 < c1769ij.n.size(); i5++) {
                    this.f[i5] = new C1856jk((C1258cj) c1769ij.n.get(i5), this, null, i5, true);
                }
                return;
            }
            c0945Wz.k(i);
            int i6 = c0945Wz.c[i];
            if (i6 < 0 || i6 >= c1769ij.h.size()) {
                throw new C1091ak(this, "Invalid public dependency index.");
            }
            C1941kk c1941kk2 = (C1941kk) map.get((String) c1769ij.h.get(i6));
            if (c1941kk2 != null) {
                arrayList.add(c1941kk2);
            }
            i++;
        }
    }

    public static C1941kk a(C1769ij c1769ij, C1941kk[] c1941kkArr) throws C1091ak {
        C1941kk c1941kk = new C1941kk(c1769ij, c1941kkArr, new C1007Zj(c1941kkArr));
        for (C0955Xj c0955Xj : c1941kk.c) {
            c0955Xj.f();
        }
        for (C2284ok c2284ok : c1941kk.e) {
            for (C2112mk c2112mk : c2284ok.e) {
                if (!(c2112mk.d.h.a(c2112mk, c2112mk.b.k()) instanceof C0955Xj)) {
                    throw new C1091ak(c2112mk, "\"" + c2112mk.b.k() + "\" is not a message type.");
                }
                if (!(c2112mk.d.h.a(c2112mk, c2112mk.b.n()) instanceof C0955Xj)) {
                    throw new C1091ak(c2112mk, "\"" + c2112mk.b.n() + "\" is not a message type.");
                }
            }
        }
        for (C1856jk c1856jk : c1941kk.f) {
            C1856jk.a(c1856jk);
        }
        return c1941kk;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final C1941kk b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String c() {
        return this.b.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String d() {
        return this.b.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.b;
    }

    public final int f() {
        return "proto3".equals(this.b.o()) ? 3 : 2;
    }

    public static C1941kk a(String[] strArr, C1941kk[] c1941kkArr) {
        byte[] bytes;
        if (strArr.length == 1) {
            bytes = strArr[0].getBytes(AbstractC1556gB.c);
        } else {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
            }
            bytes = sb.toString().getBytes(AbstractC1556gB.c);
        }
        try {
            C1769ij c1769ij = (C1769ij) C1769ij.t.a(bytes);
            try {
                return a(c1769ij, c1941kkArr);
            } catch (C1091ak e) {
                throw new IllegalArgumentException("Invalid embedded descriptor for \"" + c1769ij.k() + "\".", e);
            }
        } catch (RB e2) {
            nrd.a("Failed to parse protocol buffer descriptor for generated code.", e2);
            return null;
        }
    }

    public C1941kk(String str, C0955Xj c0955Xj) throws C1091ak {
        C1007Zj c1007Zj = new C1007Zj(new C1941kk[0]);
        this.h = c1007Zj;
        C1684hj c1684hjQ = C1769ij.s.d();
        String str2 = c0955Xj.c + ".placeholder.proto";
        c1684hjQ.f |= 1;
        c1684hjQ.g = str2;
        c1684hjQ.p();
        str.getClass();
        c1684hjQ.f |= 2;
        c1684hjQ.h = str;
        c1684hjQ.p();
        C0487Fi c0487Fi = c0955Xj.b;
        C2401q50 c2401q50 = c1684hjQ.m;
        if (c2401q50 == null) {
            c0487Fi.getClass();
            if ((c1684hjQ.f & 32) == 0) {
                c1684hjQ.l = new ArrayList(c1684hjQ.l);
                c1684hjQ.f |= 32;
            }
            c1684hjQ.l.add(c0487Fi);
            c1684hjQ.p();
        } else {
            c2401q50.a(c0487Fi);
        }
        C1769ij c1769ijQ = c1684hjQ.i();
        if (c1769ijQ.a()) {
            this.b = c1769ijQ;
            this.g = new C1941kk[0];
            this.c = new C0955Xj[]{c0955Xj};
            this.d = AbstractC2370pk.e;
            this.e = AbstractC2370pk.f;
            this.f = AbstractC2370pk.d;
            c1007Zj.a(this, str);
            c1007Zj.a(c0955Xj);
            return;
        }
        throw H0.c(c1769ijQ);
    }
}
