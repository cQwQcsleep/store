package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import defpackage.qc6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2136n2 {
    public final DB a;
    public C2042lw b;
    public int c;
    public List[] d;
    public C0936Wq[] e;
    public C1849jg0[] f;
    public boolean[] g;
    public int[] h;
    public int i;

    public C2136n2(P5 p5) {
        this.a = p5;
    }

    public final void a(C3106yO c3106yO) {
        G g;
        if ((c3106yO.c & 1280) != 0) {
            this.e = new C0936Wq[0];
            return;
        }
        C2042lw c2042lw = c3106yO.q;
        this.b = c2042lw;
        int i = c2042lw.b;
        this.c = i;
        this.d = new List[i];
        this.e = new C0936Wq[i];
        this.f = new C1849jg0[i];
        this.g = new boolean[i];
        this.h = new int[i];
        this.i = 0;
        for (int i2 = 0; i2 < c3106yO.r.size(); i2++) {
            C2708ti0 c2708ti0 = (C2708ti0) c3106yO.r.get(i2);
            int iB = this.b.b(c2708ti0.b);
            for (int iB2 = this.b.b(c2708ti0.a); iB2 < iB; iB2++) {
                List arrayList = this.d[iB2];
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.d[iB2] = arrayList;
                }
                arrayList.add(c2708ti0);
            }
        }
        int i3 = c3106yO.t;
        C1849jg0 c1849jg0 = null;
        C1849jg0 c1849jg1 = new C1849jg0(null, i3, null);
        ArrayList arrayList2 = new ArrayList();
        a(0, c1849jg1, arrayList2);
        HashMap map = new HashMap();
        while (!arrayList2.isEmpty()) {
            C2839vD c2839vD = (C2839vD) arrayList2.remove(0);
            C1849jg0 c1849jg2 = (C1849jg0) map.get(c2839vD.g);
            if (c1849jg2 == null) {
                C1849jg0 c1849jg3 = new C1849jg0(c2839vD.g, i3, c2839vD);
                map.put(c2839vD.g, c1849jg3);
                a(this.b.b(c2839vD.g), c1849jg3, arrayList2);
            } else {
                c1849jg2.c.add(c2839vD);
            }
        }
        for (int i4 = 0; i4 < this.c; i4++) {
            C1849jg0[] c1849jg0Arr = this.f;
            C1849jg0 c1849jg4 = c1849jg0Arr[i4];
            if (c1849jg4 != null && c1849jg4.a == null) {
                c1849jg0Arr[i4] = null;
            }
        }
        try {
            C0936Wq c0936WqC = c(c3106yO);
            a(0, c0936WqC, (C1849jg0) null);
            d(c3106yO);
            while (true) {
                int i5 = this.i;
                if (i5 <= 0) {
                    return;
                }
                int[] iArr = this.h;
                int i6 = i5 - 1;
                this.i = i6;
                int i7 = iArr[i6];
                C0936Wq c0936Wq = this.e[i7];
                C1849jg0 c1849jg5 = this.f[i7];
                this.g[i7] = false;
                try {
                    try {
                        G gJ = c3106yO.q.j(i7);
                        try {
                            int i8 = gJ.a;
                            int iA = gJ.a();
                            if (iA == 8 || iA == 15 || iA == 14) {
                                a(i7 + 1, c0936Wq, c1849jg5);
                            } else {
                                c0936WqC.a(c0936Wq).a(gJ, this.a);
                                C1849jg0 c1849jg6 = c1849jg5 == null ? c1849jg0 : new C1849jg0(c1849jg5);
                                if (gJ instanceof C2839vD) {
                                    C2839vD c2839vD2 = (C2839vD) gJ;
                                    if (i8 != 167 && i8 != 168) {
                                        a(i7 + 1, c0936WqC, c1849jg6);
                                    }
                                    int iB3 = this.b.b(c2839vD2.g);
                                    XI xi = c2839vD2.g;
                                    if (i8 == 168) {
                                        a(iB3, c0936WqC, new C1849jg0(xi, c3106yO.t, c2839vD2));
                                    } else {
                                        a(iB3, c0936WqC, c1849jg6);
                                    }
                                } else if (gJ instanceof C2421qM) {
                                    C2421qM c2421qM = (C2421qM) gJ;
                                    a(this.b.b(c2421qM.g), c0936WqC, c1849jg6);
                                    for (int i9 = 0; i9 < c2421qM.i.size(); i9++) {
                                        a(this.b.b((XI) c2421qM.i.get(i9)), c0936WqC, c1849jg6);
                                    }
                                } else if (gJ instanceof Mg0) {
                                    Mg0 mg0 = (Mg0) gJ;
                                    a(this.b.b(mg0.i), c0936WqC, c1849jg6);
                                    for (int i10 = 0; i10 < mg0.j.size(); i10++) {
                                        a(this.b.b((XI) mg0.j.get(i10)), c0936WqC, c1849jg6);
                                    }
                                } else if (i8 == 169) {
                                    if (c1849jg6 == null) {
                                        throw new C2222o2(gJ, "RET instruction outside of a subroutine");
                                    }
                                    for (int i11 = 0; i11 < c1849jg6.c.size(); i11++) {
                                        int iB4 = this.b.b((C2839vD) c1849jg6.c.get(i11));
                                        C0936Wq c0936Wq2 = this.e[iB4];
                                        if (c0936Wq2 != null) {
                                            a(iB4 + 1, c0936Wq2, c0936WqC, this.f[iB4], c1849jg6.b);
                                        }
                                    }
                                } else if (i8 != 191 && (i8 < 172 || i8 > 177)) {
                                    if (c1849jg6 != null) {
                                        if (gJ instanceof Ll0) {
                                            int i12 = ((Ll0) gJ).g;
                                            boolean[] zArr = c1849jg6.b;
                                            zArr[i12] = true;
                                            if (i8 == 22 || i8 == 24 || i8 == 55 || i8 == 57) {
                                                zArr[i12 + 1] = true;
                                            }
                                        } else if (gJ instanceof C2383pu) {
                                            c1849jg6.b[((C2383pu) gJ).g] = true;
                                        }
                                    }
                                    a(i7 + 1, c0936WqC, c1849jg6);
                                }
                                c1849jg5 = c1849jg6;
                            }
                            List<C2708ti0> list = this.d[i7];
                            if (list != null) {
                                for (C2708ti0 c2708ti1 : list) {
                                    String str = c2708ti1.d;
                                    C3050xi0 c3050xi0E = str == null ? C3050xi0.e("java/lang/Throwable") : C3050xi0.e(str);
                                    this.b.b(c2708ti1.c);
                                    C0936Wq c0936WqA = a(c0936Wq);
                                    this.a.getClass();
                                    O5 o5A = P5.a(c3050xi0E);
                                    c0936WqA.d = 0;
                                    c0936WqA.a(o5A);
                                    a(this.b.b(c2708ti1.c), c0936WqA, c1849jg5);
                                    C0936Wq c0936WqA2 = a(c0936WqC);
                                    c0936WqA2.d = 0;
                                    c0936WqA2.a(o5A);
                                    a(this.b.b(c2708ti1.c), c0936WqA2, c1849jg5);
                                    c1849jg0 = null;
                                }
                            }
                        } catch (RuntimeException e) {
                            e = e;
                            g = gJ;
                            throw new C2222o2(g, "Error at instruction " + i7 + ": " + e.getMessage(), e);
                        }
                    } catch (C2222o2 e2) {
                        throw new C2222o2(e2.b, "Error at instruction " + i7 + ": " + e2.getMessage(), e2);
                    }
                } catch (RuntimeException e3) {
                    e = e3;
                    g = null;
                }
            }
        } catch (RuntimeException e4) {
            throw new C2222o2(this.b.j(0), F40.a("Error at instruction 0: ", e4.getMessage()), e4);
        }
    }

    public final void b(C3106yO c3106yO) {
        G g;
        int iC = C3050xi0.c(c3106yO.e) >> 2;
        if ((c3106yO.c & 8) != 0) {
            iC--;
        }
        C2042lw c2042lw = c3106yO.q;
        c2042lw.getClass();
        int i = c2042lw.b;
        if (i < 0) {
            qc6.a();
            return;
        }
        if (i == 0) {
            g = null;
        } else {
            g = c2042lw.c;
            G g2 = g.d;
        }
        while (g != null) {
            if (g == null) {
                z0e.a();
                return;
            }
            G g3 = g.e;
            if (g instanceof Ll0) {
                int i2 = ((Ll0) g).g;
                int i3 = g.a;
                iC = Math.max(iC, i2 + ((i3 == 22 || i3 == 24 || i3 == 55 || i3 == 57) ? 2 : 1));
            } else if (g instanceof C2383pu) {
                iC = Math.max(iC, ((C2383pu) g).g + 1);
            }
            g = g3;
        }
        c3106yO.t = iC;
        c3106yO.s = -1;
        a(c3106yO);
        int iMax = 0;
        for (C0936Wq c0936Wq : this.e) {
            if (c0936Wq != null) {
                int iA = 0;
                for (int i4 = 0; i4 < c0936Wq.d; i4++) {
                    iA += ((O5) c0936Wq.b[c0936Wq.c + i4]).a();
                }
                iMax = Math.max(iMax, iA);
            }
        }
        c3106yO.s = iMax;
    }

    public final C0936Wq c(C3106yO c3106yO) {
        int i;
        C0936Wq c0936Wq = new C0936Wq(c3106yO.t, c3106yO.s);
        if ((c3106yO.c & 8) == 0) {
            C3050xi0 c3050xi0E = C3050xi0.e("dummy");
            this.a.getClass();
            c0936Wq.a(0, P5.a(c3050xi0E));
            i = 1;
        } else {
            i = 0;
        }
        for (C3050xi0 c3050xi0 : C3050xi0.b(c3106yO.e)) {
            this.a.getClass();
            c0936Wq.a(i, P5.a(c3050xi0));
            int i2 = i + 1;
            switch (c3050xi0.a) {
                case 0:
                case 1:
                case 2:
                case XmlPullParser.END_TAG /* 3 */:
                case 4:
                case XmlPullParser.CDSECT /* 5 */:
                case XmlPullParser.ENTITY_REF /* 6 */:
                case 9:
                case XmlPullParser.DOCDECL /* 10 */:
                case 12:
                    i = i2;
                    break;
                case 7:
                case 8:
                    this.a.getClass();
                    c0936Wq.a(i2, O5.b);
                    i += 2;
                    break;
                case AndroidSdkVersion.HONEYCOMB /* 11 */:
                default:
                    x1f.a();
                    return null;
            }
        }
        while (true) {
            int i3 = c3106yO.t;
            DB db = this.a;
            if (i >= i3) {
                String str = c3106yO.e;
                C3050xi0 c3050xi0A = C3050xi0.a(C3050xi0.f(str), str.length(), str);
                db.getClass();
                c0936Wq.a = P5.a(c3050xi0A);
                return c0936Wq;
            }
            db.getClass();
            C3050xi0 c3050xi1 = P5.a;
            c0936Wq.a(i, O5.b);
            i++;
        }
    }

    public void d(C3106yO c3106yO) {
    }

    public final void a(int i, C1849jg0 c1849jg0, ArrayList arrayList) throws C2222o2 {
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(i));
        while (!arrayList2.isEmpty()) {
            int iIntValue = ((Integer) arrayList2.remove(arrayList2.size() - 1)).intValue();
            if (iIntValue >= 0 && iIntValue < this.c) {
                C1849jg0[] c1849jg0Arr = this.f;
                if (c1849jg0Arr[iIntValue] == null) {
                    c1849jg0Arr[iIntValue] = new C1849jg0(c1849jg0);
                    G gJ = this.b.j(iIntValue);
                    if (gJ instanceof C2839vD) {
                        if (gJ.a == 168) {
                            arrayList.add(gJ);
                        } else {
                            arrayList2.add(Integer.valueOf(this.b.b(((C2839vD) gJ).g)));
                        }
                    } else if (gJ instanceof Mg0) {
                        Mg0 mg0 = (Mg0) gJ;
                        a(this.b.b(mg0.i), c1849jg0, arrayList);
                        for (int size = mg0.j.size() - 1; size >= 0; size--) {
                            arrayList2.add(Integer.valueOf(this.b.b((XI) mg0.j.get(size))));
                        }
                    } else if (gJ instanceof C2421qM) {
                        C2421qM c2421qM = (C2421qM) gJ;
                        a(this.b.b(c2421qM.g), c1849jg0, arrayList);
                        for (int size2 = c2421qM.i.size() - 1; size2 >= 0; size2--) {
                            arrayList2.add(Integer.valueOf(this.b.b((XI) c2421qM.i.get(size2))));
                        }
                    }
                    List list = this.d[iIntValue];
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            arrayList2.add(Integer.valueOf(this.b.b(((C2708ti0) it.next()).c)));
                        }
                    }
                    int i2 = gJ.a;
                    if (i2 != 167 && i2 != 191) {
                        switch (i2) {
                            case 169:
                            case 170:
                            case 171:
                            case 172:
                            case 173:
                            case 174:
                            case 175:
                            case 176:
                            case 177:
                                break;
                            default:
                                arrayList2.add(Integer.valueOf(iIntValue + 1));
                                break;
                        }
                    }
                }
            } else {
                throw new C2222o2(null, "Execution can fall off the end of the code");
            }
        }
    }

    public static C0936Wq a(C0936Wq c0936Wq) {
        int i = c0936Wq.c;
        C0936Wq c0936Wq2 = new C0936Wq(i, c0936Wq.b.length - i);
        c0936Wq2.a(c0936Wq);
        return c0936Wq2;
    }

    public final void a(int i, C0936Wq c0936Wq, C1849jg0 c1849jg0) throws C2222o2 {
        boolean zA;
        C0936Wq[] c0936WqArr = this.e;
        C0936Wq c0936Wq2 = c0936WqArr[i];
        if (c0936Wq2 == null) {
            c0936WqArr[i] = a(c0936Wq);
            zA = true;
        } else {
            zA = c0936Wq2.a(c0936Wq, this.a);
        }
        C1849jg0[] c1849jg0Arr = this.f;
        C1849jg0 c1849jg1 = c1849jg0Arr[i];
        if (c1849jg1 == null) {
            if (c1849jg0 != null) {
                c1849jg0Arr[i] = new C1849jg0(c1849jg0);
                zA = true;
            }
        } else if (c1849jg0 != null) {
            zA |= c1849jg1.a(c1849jg0);
        }
        if (zA) {
            boolean[] zArr = this.g;
            if (zArr[i]) {
                return;
            }
            zArr[i] = true;
            int[] iArr = this.h;
            int i2 = this.i;
            this.i = i2 + 1;
            iArr[i2] = i;
        }
    }

    public final void a(int i, C0936Wq c0936Wq, C0936Wq c0936Wq2, C1849jg0 c1849jg0, boolean[] zArr) throws C2222o2 {
        boolean zA;
        for (int i2 = 0; i2 < c0936Wq2.c; i2++) {
            if (!zArr[i2] && !c0936Wq2.b[i2].equals(c0936Wq.b[i2])) {
                c0936Wq2.b[i2] = c0936Wq.b[i2];
            }
        }
        C0936Wq[] c0936WqArr = this.e;
        C0936Wq c0936Wq3 = c0936WqArr[i];
        if (c0936Wq3 == null) {
            c0936WqArr[i] = a(c0936Wq2);
            zA = true;
        } else {
            zA = c0936Wq3.a(c0936Wq2, this.a);
        }
        C1849jg0 c1849jg1 = this.f[i];
        if (c1849jg1 != null && c1849jg0 != null) {
            zA |= c1849jg1.a(c1849jg0);
        }
        if (zA) {
            boolean[] zArr2 = this.g;
            if (zArr2[i]) {
                return;
            }
            zArr2[i] = true;
            int[] iArr = this.h;
            int i3 = this.i;
            this.i = i3 + 1;
            iArr[i3] = i;
        }
    }
}
