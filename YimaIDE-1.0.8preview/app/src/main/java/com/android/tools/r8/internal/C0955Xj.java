package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0955Xj extends AbstractC2027lk {
    public final C0487Fi b;
    public final String c;
    public final C1941kk d;
    public final C0955Xj[] e;
    public final C1260ck[] f;
    public final C1856jk[] g;
    public final C1856jk[] h;
    public final C1856jk[] i;
    public final C2198nk[] j;
    public final int[] k;
    public final int[] l;

    public C0955Xj(C0487Fi c0487Fi, C1941kk c1941kk, C0955Xj c0955Xj) throws C1091ak {
        this.b = c0487Fi;
        this.c = AbstractC2370pk.a(c1941kk, c0955Xj, c0487Fi.k());
        this.d = c1941kk;
        this.j = c0487Fi.l.size() > 0 ? new C2198nk[c0487Fi.l.size()] : AbstractC2370pk.g;
        int i = 0;
        for (int i2 = 0; i2 < c0487Fi.l.size(); i2++) {
            this.j[i2] = new C2198nk((C3135yj) c0487Fi.l.get(i2), c1941kk, this, i2);
        }
        this.e = c0487Fi.i.size() > 0 ? new C0955Xj[c0487Fi.i.size()] : AbstractC2370pk.c;
        for (int i3 = 0; i3 < c0487Fi.i.size(); i3++) {
            this.e[i3] = new C0955Xj((C0487Fi) c0487Fi.i.get(i3), c1941kk, this);
        }
        this.f = c0487Fi.j.size() > 0 ? new C1260ck[c0487Fi.j.size()] : AbstractC2370pk.e;
        for (int i4 = 0; i4 < c0487Fi.j.size(); i4++) {
            this.f[i4] = new C1260ck((C0643Li) c0487Fi.j.get(i4), c1941kk, this);
        }
        this.g = c0487Fi.g.size() > 0 ? new C1856jk[c0487Fi.g.size()] : AbstractC2370pk.d;
        for (int i5 = 0; i5 < c0487Fi.g.size(); i5++) {
            this.g[i5] = new C1856jk((C1258cj) c0487Fi.g.get(i5), c1941kk, this, i5, false);
        }
        this.h = c0487Fi.g.size() > 0 ? (C1856jk[]) this.g.clone() : AbstractC2370pk.d;
        this.i = c0487Fi.h.size() > 0 ? new C1856jk[c0487Fi.h.size()] : AbstractC2370pk.d;
        for (int i6 = 0; i6 < c0487Fi.h.size(); i6++) {
            this.i[i6] = new C1856jk((C1258cj) c0487Fi.h.get(i6), c1941kk, this, i6, true);
        }
        for (int i7 = 0; i7 < c0487Fi.l.size(); i7++) {
            C2198nk c2198nk = this.j[i7];
            c2198nk.h = new C1856jk[c2198nk.g];
            c2198nk.g = 0;
        }
        for (int i8 = 0; i8 < c0487Fi.g.size(); i8++) {
            C1856jk c1856jk = this.g[i8];
            C2198nk c2198nk2 = c1856jk.k;
            if (c2198nk2 != null) {
                C1856jk[] c1856jkArr = c2198nk2.h;
                int i9 = c2198nk2.g;
                c2198nk2.g = i9 + 1;
                c1856jkArr[i9] = c1856jk;
            }
        }
        int i10 = 0;
        for (C2198nk c2198nk3 : this.j) {
            if (c2198nk3.f()) {
                i10++;
            } else if (i10 > 0) {
                throw new C1091ak(this, "Synthetic oneofs must come last.");
            }
        }
        int length = this.j.length;
        c1941kk.h.a(this);
        if (c0487Fi.k.size() <= 0) {
            int[] iArr = AbstractC2370pk.b;
            this.k = iArr;
            this.l = iArr;
            return;
        }
        this.k = new int[c0487Fi.k.size()];
        this.l = new int[c0487Fi.k.size()];
        for (C0383Bi c0383Bi : c0487Fi.k) {
            this.k[i] = c0383Bi.f;
            this.l[i] = c0383Bi.g;
            i++;
        }
        Arrays.sort(this.k);
        Arrays.sort(this.l);
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final C1941kk b() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String c() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String d() {
        return this.b.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.b;
    }

    public final void f() {
        for (C0955Xj c0955Xj : this.e) {
            c0955Xj.f();
        }
        for (C1856jk c1856jk : this.g) {
            C1856jk.a(c1856jk);
        }
        Arrays.sort(this.h);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            C1856jk[] c1856jkArr = this.h;
            if (i2 >= c1856jkArr.length) {
                for (C1856jk c1856jk2 : this.i) {
                    C1856jk.a(c1856jk2);
                }
                return;
            }
            C1856jk c1856jk3 = c1856jkArr[i];
            C1856jk c1856jk4 = c1856jkArr[i2];
            if (c1856jk3.c.g == c1856jk4.c.g) {
                throw new C1091ak(c1856jk4, "Field number " + c1856jk4.c.g + " has already been used in \"" + c1856jk4.i.c + "\" by field \"" + c1856jk3.c.m() + "\".");
            }
            i = i2;
        }
    }

    public C0955Xj(String str) {
        String strSubstring;
        String strSubstring2;
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf != -1) {
            strSubstring2 = str.substring(iLastIndexOf + 1);
            strSubstring = str.substring(0, iLastIndexOf);
        } else {
            strSubstring = XmlPullParser.NO_NAMESPACE;
            strSubstring2 = str;
        }
        C3133yi c3133yiD = C0487Fi.q.d();
        c3133yiD.f |= 1;
        c3133yiD.g = strSubstring2;
        c3133yiD.p();
        C0357Ai c0357AiD = C0383Bi.j.d();
        c0357AiD.f |= 1;
        c0357AiD.g = 1;
        c0357AiD.p();
        c0357AiD.f |= 2;
        c0357AiD.h = 536870912;
        c0357AiD.p();
        C0383Bi c0383BiI = c0357AiD.i();
        if (c0383BiI.a()) {
            C2401q50 c2401q50 = c3133yiD.q;
            if (c2401q50 == null) {
                if ((c3133yiD.f & 32) == 0) {
                    c3133yiD.p = new ArrayList(c3133yiD.p);
                    c3133yiD.f |= 32;
                }
                c3133yiD.p.add(c0383BiI);
                c3133yiD.p();
            } else {
                c2401q50.a(c0383BiI);
            }
            C0487Fi c0487FiI = c3133yiD.i();
            if (c0487FiI.a()) {
                this.b = c0487FiI;
                this.c = str;
                this.e = AbstractC2370pk.c;
                this.f = AbstractC2370pk.e;
                C1856jk[] c1856jkArr = AbstractC2370pk.d;
                this.g = c1856jkArr;
                this.h = c1856jkArr;
                this.i = c1856jkArr;
                this.j = AbstractC2370pk.g;
                this.d = new C1941kk(strSubstring, this);
                this.k = new int[]{1};
                this.l = new int[]{536870912};
                return;
            }
            throw H0.c(c0487FiI);
        }
        throw H0.c(c0383BiI);
    }
}
