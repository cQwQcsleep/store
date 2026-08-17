package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3106yO extends XO {
    public final int c;
    public final String d;
    public final String e;
    public ArrayList f;
    public List g;
    public List h;
    public List i;
    public List j;
    public List k;
    public Object l;
    public int m;
    public List[] n;
    public int o;
    public List[] p;
    public C2042lw q;
    public List r;
    public int s;
    public int t;
    public List u;
    public List v;
    public List w;
    public boolean x;

    public C3106yO(int i, int i2, String str, String str2, String[] strArr) {
        super(i, null);
        this.c = i2;
        this.d = str;
        this.e = str2;
        AbstractC2287ol0.a(strArr);
        if ((i2 & Fcntl.S_ISGID) == 0) {
            this.u = new ArrayList(5);
        }
        this.r = new ArrayList();
        this.q = new C2042lw();
    }

    public final void a(XO xo) {
        XO xo2;
        ArrayList arrayList = this.f;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C1576gW c1576gW = (C1576gW) this.f.get(i);
                xo.b(c1576gW.b, c1576gW.a);
            }
        }
        if (this.l != null) {
            J2 j2A = xo.a();
            D2.a(j2A, (String) null, this.l);
            if (j2A != null) {
                j2A.a();
            }
        }
        List list = this.g;
        if (list != null) {
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                D2 d2 = (D2) this.g.get(i2);
                d2.a(xo.a(d2.b, true));
            }
        }
        List list2 = this.h;
        if (list2 != null) {
            int size3 = list2.size();
            for (int i3 = 0; i3 < size3; i3++) {
                D2 d3 = (D2) this.h.get(i3);
                d3.a(xo.a(d3.b, false));
            }
        }
        List list3 = this.i;
        if (list3 != null) {
            int size4 = list3.size();
            for (int i4 = 0; i4 < size4; i4++) {
                C2369pj0 c2369pj0 = (C2369pj0) this.i.get(i4);
                c2369pj0.a(xo.c(c2369pj0.d, c2369pj0.e, c2369pj0.b, true));
            }
        }
        List list4 = this.j;
        if (list4 != null) {
            int size5 = list4.size();
            for (int i5 = 0; i5 < size5; i5++) {
                C2369pj0 c2369pj1 = (C2369pj0) this.j.get(i5);
                c2369pj1.a(xo.c(c2369pj1.d, c2369pj1.e, c2369pj1.b, false));
            }
        }
        int i6 = this.m;
        if (i6 > 0) {
            xo.a(i6, true);
        }
        List[] listArr = this.n;
        if (listArr != null) {
            int length = listArr.length;
            for (int i7 = 0; i7 < length; i7++) {
                List list5 = this.n[i7];
                if (list5 != null) {
                    int size6 = list5.size();
                    for (int i8 = 0; i8 < size6; i8++) {
                        D2 d4 = (D2) list5.get(i8);
                        d4.a(xo.a(i7, d4.b, true));
                    }
                }
            }
        }
        int i9 = this.o;
        if (i9 > 0) {
            xo.a(i9, false);
        }
        List[] listArr2 = this.p;
        if (listArr2 != null) {
            int length2 = listArr2.length;
            for (int i10 = 0; i10 < length2; i10++) {
                List list6 = this.p[i10];
                if (list6 != null) {
                    int size7 = list6.size();
                    for (int i11 = 0; i11 < size7; i11++) {
                        D2 d5 = (D2) list6.get(i11);
                        d5.a(xo.a(i10, d5.b, false));
                    }
                }
            }
        }
        if (this.x) {
            for (G g = this.q.c; g != null; g = g.e) {
                if (g instanceof XI) {
                    ((XI) g).g = null;
                }
            }
        }
        List list7 = this.k;
        if (list7 != null) {
            int size8 = list7.size();
            for (int i12 = 0; i12 < size8; i12++) {
                xo.a((H4) this.k.get(i12));
            }
        }
        if (this.q.b > 0) {
            xo.b();
            List list8 = this.r;
            if (list8 != null) {
                int size9 = list8.size();
                for (int i13 = 0; i13 < size9; i13++) {
                    C2708ti0 c2708ti0 = (C2708ti0) this.r.get(i13);
                    int i14 = (i13 << 8) | 1107296256;
                    List list9 = c2708ti0.e;
                    if (list9 != null) {
                        int size10 = list9.size();
                        for (int i15 = 0; i15 < size10; i15++) {
                            ((C2369pj0) c2708ti0.e.get(i15)).d = i14;
                        }
                    }
                    List list10 = c2708ti0.f;
                    if (list10 != null) {
                        int size11 = list10.size();
                        for (int i16 = 0; i16 < size11; i16++) {
                            ((C2369pj0) c2708ti0.f.get(i16)).d = i14;
                        }
                    }
                    ((C2708ti0) this.r.get(i13)).a(xo);
                }
            }
            for (G g2 = this.q.c; g2 != null; g2 = g2.e) {
                g2.a(xo);
            }
            List list11 = this.u;
            if (list11 != null) {
                int size12 = list11.size();
                for (int i17 = 0; i17 < size12; i17++) {
                    NL nl = (NL) this.u.get(i17);
                    xo.a(nl.a, nl.b, nl.c, nl.d.b(), nl.e.b(), nl.f);
                }
            }
            xo2 = xo;
            List list12 = this.v;
            if (list12 != null) {
                int size13 = list12.size();
                for (int i18 = 0; i18 < size13; i18++) {
                    ((ML) this.v.get(i18)).a(xo2, true);
                }
            }
            List list13 = this.w;
            if (list13 != null) {
                int size14 = list13.size();
                for (int i19 = 0; i19 < size14; i19++) {
                    ((ML) this.w.get(i19)).a(xo2, false);
                }
            }
            xo2.c(this.s, this.t);
            this.x = true;
        } else {
            xo2 = xo;
        }
        xo2.c();
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 b(int i, C3052xj0 c3052xj0, String str, boolean z) {
        C2708ti0 c2708ti0 = (C2708ti0) this.r.get((16776960 & i) >> 8);
        C2369pj0 c2369pj0 = new C2369pj0(i, c3052xj0, str);
        if (z) {
            c2708ti0.e = AbstractC2287ol0.a(c2708ti0.e, c2369pj0);
            return c2369pj0;
        }
        c2708ti0.f = AbstractC2287ol0.a(c2708ti0.f, c2369pj0);
        return c2369pj0;
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 c(int i, C3052xj0 c3052xj0, String str, boolean z) {
        C2369pj0 c2369pj0 = new C2369pj0(i, c3052xj0, str);
        if (z) {
            this.i = AbstractC2287ol0.a(this.i, c2369pj0);
            return c2369pj0;
        }
        this.j = AbstractC2287ol0.a(this.j, c2369pj0);
        return c2369pj0;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void d(int i, int i2) {
        this.q.a(new Ll0(i, i2));
    }

    @Override // com.android.tools.r8.internal.XO
    public void c() {
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, String str) {
        this.q.a(new C2710tj0(i, str));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void c(int i, int i2) {
        this.s = i;
        this.t = i2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, String str) {
        if (this.f == null) {
            this.f = new ArrayList(5);
        }
        this.f.add(new C1576gW(i, str));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, int i2) {
        this.q.a(new C1555gA(i, i2));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b() {
    }

    @Override // com.android.tools.r8.internal.XO
    public final void b(int i, WI wi) {
        this.q.a(new C1479fK(i, b(wi)));
    }

    public static XI b(WI wi) {
        if (wi.a == null) {
            wi.a = new XI();
        }
        return wi.a;
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(String str, boolean z) {
        D2 d2 = new D2(str);
        if (z) {
            this.g = AbstractC2287ol0.a(this.g, d2);
            return d2;
        }
        this.h = AbstractC2287ol0.a(this.h, d2);
        return d2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, boolean z) {
        if (z) {
            this.m = i;
        } else {
            this.o = i;
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, String str, boolean z) {
        D2 d2 = new D2(str);
        if (z) {
            if (this.n == null) {
                this.n = new List[C3050xi0.a(this.e)];
            }
            List[] listArr = this.n;
            listArr[i] = AbstractC2287ol0.a(listArr[i], d2);
            return d2;
        }
        if (this.p == null) {
            this.p = new List[C3050xi0.a(this.e)];
        }
        List[] listArr2 = this.p;
        listArr2[i] = AbstractC2287ol0.a(listArr2[i], d2);
        return d2;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(H4 h4) {
        this.k = AbstractC2287ol0.a(this.k, h4);
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        Object[] objArr3;
        C2042lw c2042lw = this.q;
        Object[] objArr4 = null;
        if (objArr == null) {
            objArr3 = null;
        } else {
            objArr3 = new Object[objArr.length];
            int length = objArr.length;
            for (int i4 = 0; i4 < length; i4++) {
                Object objB = objArr[i4];
                if (objB instanceof WI) {
                    objB = b((WI) objB);
                }
                objArr3[i4] = objB;
            }
        }
        if (objArr2 != null) {
            objArr4 = new Object[objArr2.length];
            int length2 = objArr2.length;
            for (int i5 = 0; i5 < length2; i5++) {
                Object objB2 = objArr2[i5];
                if (objB2 instanceof WI) {
                    objB2 = b((WI) objB2);
                }
                objArr4[i5] = objB2;
            }
        }
        c2042lw.a(new C0988Yq(i, i2, objArr3, i3, objArr4));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i) {
        this.q.a(new C2127mw(i));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3) {
        this.q.a(new C1779ip(i, str, str2, str3));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str, String str2, String str3, boolean z) {
        if (this.a < 327680 && (i & Fcntl.S_IRUSR) == 0) {
            super.a(i, str, str2, str3, z);
        } else {
            this.q.a(new C2594sO(i & (-257), str, str2, str3, z));
        }
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        this.q.a(new WB(str, str2, c0497Fs, objArr));
    }

    @Override // com.android.tools.r8.internal.XO
    public void a(int i, WI wi) {
        this.q.a(new C2839vD(i, b(wi)));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi) {
        this.q.a(b(wi));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(Object obj) {
        this.q.a(new BJ(obj));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2) {
        this.q.a(new C2383pu(i, i2));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, int i2, WI wi, WI... wiArr) {
        this.q.a(new Mg0(i, i2, b(wi), a(wiArr)));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, int[] iArr, WI[] wiArr) {
        this.q.a(new C2421qM(b(wi), iArr, a(wiArr)));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(int i, String str) {
        this.q.a(new C3107yP(i, str));
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        G g = this.q.d;
        while (g.a == -1) {
            g = g.d;
        }
        C2369pj0 c2369pj0 = new C2369pj0(i, c3052xj0, str);
        if (z) {
            g.b = AbstractC2287ol0.a(g.b, c2369pj0);
            return c2369pj0;
        }
        g.c = AbstractC2287ol0.a(g.c, c2369pj0);
        return c2369pj0;
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(WI wi, WI wi2, WI wi3, String str) {
        this.r = AbstractC2287ol0.a(this.r, new C2708ti0(b(wi), b(wi2), b(wi3), str));
    }

    @Override // com.android.tools.r8.internal.XO
    public final void a(String str, String str2, String str3, WI wi, WI wi2, int i) {
        this.u = AbstractC2287ol0.a(this.u, new NL(str, str2, str3, b(wi), b(wi2), i));
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(int i, C3052xj0 c3052xj0, WI[] wiArr, WI[] wiArr2, int[] iArr, String str, boolean z) {
        ML ml = new ML(i, c3052xj0, a(wiArr), a(wiArr2), iArr, str);
        if (z) {
            this.v = AbstractC2287ol0.a(this.v, ml);
            return ml;
        }
        this.w = AbstractC2287ol0.a(this.w, ml);
        return ml;
    }

    public static XI[] a(WI[] wiArr) {
        XI[] xiArr = new XI[wiArr.length];
        int length = wiArr.length;
        for (int i = 0; i < length; i++) {
            xiArr[i] = b(wiArr[i]);
        }
        return xiArr;
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a() {
        return new D2(new C3022xO(this));
    }
}
