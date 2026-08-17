package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1059aO {
    /* JADX WARN: Code duplicated, block: B:100:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:103:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:104:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:121:0x012d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:122:0x0121 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:123:0x013b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:124:0x0137 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:56:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:58:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:60:0x00de  */
    /* JADX WARN: Code duplicated, block: B:62:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:65:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:71:0x010f A[LOOP:1: B:71:0x010f->B:125:0x010f, LOOP_START] */
    /* JADX WARN: Code duplicated, block: B:73:0x0115  */
    /* JADX WARN: Code duplicated, block: B:80:0x013f A[LOOP:2: B:80:0x013f->B:82:0x0145, LOOP_START] */
    /* JADX WARN: Code duplicated, block: B:82:0x0145 A[LOOP:2: B:80:0x013f->B:82:0x0145, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:84:0x015f  */
    /* JADX WARN: Code duplicated, block: B:86:0x0169  */
    /* JADX WARN: Code duplicated, block: B:88:0x016d  */
    /* JADX WARN: Code duplicated, block: B:90:0x0171  */
    /* JADX WARN: Code duplicated, block: B:91:0x0184  */
    /* JADX WARN: Code duplicated, block: B:93:0x0190  */
    /* JADX WARN: Code duplicated, block: B:94:0x0199  */
    /* JADX WARN: Code duplicated, block: B:96:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:98:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:99:0x01a9  */
    public static boolean a(AbstractC0663Md abstractC0663Md, C2285ok0 c2285ok0, C0415Co c0415Co, C0955Xj c0955Xj, ZN zn, int i) {
        boolean z;
        boolean z2;
        boolean z3;
        int iOrdinal;
        Object objA;
        int iF;
        C1515fk c1515fkB;
        int iF2;
        C1515fk c1515fkB2;
        Nm0[] nm0Arr;
        boolean z4 = true;
        C1856jk c1856jk = null;
        Q7 q7D = null;
        if (c0955Xj.b.l().g && i == 11) {
            int iT = 0;
            while (true) {
                int iS = abstractC0663Md.s();
                if (iS == 0) {
                    break;
                }
                if (iS == 16) {
                    iT = abstractC0663Md.t();
                    if (iT != 0 && (c0415Co instanceof C3144yo)) {
                        zn.a((C3144yo) c0415Co, c0955Xj, iT);
                    }
                } else if (iS == 26) {
                    q7D = abstractC0663Md.d();
                } else if (!abstractC0663Md.d(iS)) {
                    break;
                }
            }
            abstractC0663Md.a(12);
            if (q7D != null && iT != 0) {
                int i2 = C2456qk0.f;
                C2371pk0 c2371pk0 = new C2371pk0();
                C2456qk0 c2456qk0 = c2371pk0.a;
                if (c2456qk0.d == null) {
                    c2456qk0.d = new ArrayList();
                }
                c2371pk0.a.d.add(q7D);
                c2285ok0.a(iT, c2371pk0.a());
            }
            return true;
        }
        int i3 = i & 7;
        int i4 = i >>> 3;
        int iBinarySearch = Arrays.binarySearch(c0955Xj.k, i4);
        if (iBinarySearch < 0) {
            iBinarySearch = (~iBinarySearch) - 1;
        }
        if (iBinarySearch < 0 || i4 >= c0955Xj.l[iBinarySearch]) {
            if (zn.a() == 1) {
                C1856jk[] c1856jkArr = c0955Xj.h;
                int length = c1856jkArr.length;
                C1600gk c1600gk = C1856jk.n;
                Logger logger = AbstractC2370pk.a;
                C1600gk c1600gk2 = C1856jk.n;
                int i5 = length - 1;
                int i6 = 0;
                while (true) {
                    if (i6 <= i5) {
                        int i7 = (i6 + i5) / 2;
                        C1856jk c1856jk2 = c1856jkArr[i7];
                        z = z4;
                        int iA = c1600gk2.a(c1856jk2);
                        if (i4 >= iA) {
                            if (i4 <= iA) {
                                c1856jk = c1856jk2;
                                break;
                            }
                            i6 = i7 + 1;
                        } else {
                            i5 = i7 - 1;
                        }
                        z4 = z;
                    }
                }
            }
            if (c1856jk == null) {
                z2 = z;
                z3 = false;
            } else {
                nm0Arr = C1856jk.o;
                if (i3 == nm0Arr[c1856jk.h.ordinal()].c) {
                    z2 = false;
                    z3 = false;
                } else {
                    if (c1856jk.k()) {
                        Nm0 nm0 = nm0Arr[c1856jk.h.ordinal()];
                        if (i3 == 2) {
                            z3 = z;
                            z2 = false;
                        }
                    }
                    z2 = z;
                    z3 = false;
                }
            }
            if (z2) {
                return c2285ok0.a(i, abstractC0663Md);
            }
            if (z3) {
                int iC = abstractC0663Md.c(abstractC0663Md.l());
                if (C1856jk.o[c1856jk.h.ordinal()] == Nm0.e) {
                    while (abstractC0663Md.b() > 0) {
                        iF2 = abstractC0663Md.f();
                        if (c1856jk.e.f() == 3) {
                            zn.b(c1856jk, c1856jk.g().c(iF2));
                        } else {
                            c1515fkB2 = c1856jk.g().b(iF2);
                            if (c1515fkB2 == null) {
                                c2285ok0.a(i4, iF2);
                            } else {
                                zn.b(c1856jk, c1515fkB2);
                            }
                        }
                    }
                } else {
                    while (abstractC0663Md.b() > 0) {
                        zn.b(c1856jk, Rm0.a(abstractC0663Md, C1856jk.o[c1856jk.h.ordinal()], zn.a(c1856jk)));
                    }
                }
                abstractC0663Md.b(iC);
            } else {
                iOrdinal = c1856jk.h.ordinal();
                if (iOrdinal != 9) {
                    objA = zn.a(abstractC0663Md, c0415Co, c1856jk);
                } else if (iOrdinal != 10) {
                    objA = zn.b(abstractC0663Md, c0415Co, c1856jk);
                } else if (iOrdinal != 13) {
                    objA = Rm0.a(abstractC0663Md, C1856jk.o[c1856jk.h.ordinal()], zn.a(c1856jk));
                } else {
                    iF = abstractC0663Md.f();
                    if (c1856jk.e.f() == 3) {
                        objA = c1856jk.g().c(iF);
                    } else {
                        c1515fkB = c1856jk.g().b(iF);
                        if (c1515fkB == null) {
                            c2285ok0.a(i4, iF);
                            return z;
                        }
                        objA = c1515fkB;
                    }
                }
                if (c1856jk.m()) {
                    zn.b(c1856jk, objA);
                } else {
                    zn.a(c1856jk, objA);
                }
            }
            return z;
        }
        if (c0415Co instanceof C3144yo) {
            zn.a((C3144yo) c0415Co, c0955Xj, i4);
        }
        z = z4;
        if (c1856jk == null) {
            z2 = z;
            z3 = false;
        } else {
            nm0Arr = C1856jk.o;
            if (i3 == nm0Arr[c1856jk.h.ordinal()].c) {
                z2 = false;
                z3 = false;
            } else {
                if (c1856jk.k()) {
                    Nm0 nm1 = nm0Arr[c1856jk.h.ordinal()];
                    if (i3 == 2) {
                        z3 = z;
                        z2 = false;
                    }
                }
                z2 = z;
                z3 = false;
            }
        }
        if (z2) {
            return c2285ok0.a(i, abstractC0663Md);
        }
        if (z3) {
            int iC2 = abstractC0663Md.c(abstractC0663Md.l());
            if (C1856jk.o[c1856jk.h.ordinal()] == Nm0.e) {
                while (abstractC0663Md.b() > 0) {
                    iF2 = abstractC0663Md.f();
                    if (c1856jk.e.f() == 3) {
                        zn.b(c1856jk, c1856jk.g().c(iF2));
                    } else {
                        c1515fkB2 = c1856jk.g().b(iF2);
                        if (c1515fkB2 == null) {
                            c2285ok0.a(i4, iF2);
                        } else {
                            zn.b(c1856jk, c1515fkB2);
                        }
                    }
                }
            } else {
                while (abstractC0663Md.b() > 0) {
                    zn.b(c1856jk, Rm0.a(abstractC0663Md, C1856jk.o[c1856jk.h.ordinal()], zn.a(c1856jk)));
                }
            }
            abstractC0663Md.b(iC2);
        } else {
            iOrdinal = c1856jk.h.ordinal();
            if (iOrdinal != 9) {
                objA = zn.a(abstractC0663Md, c0415Co, c1856jk);
            } else if (iOrdinal != 10) {
                objA = zn.b(abstractC0663Md, c0415Co, c1856jk);
            } else if (iOrdinal != 13) {
                objA = Rm0.a(abstractC0663Md, C1856jk.o[c1856jk.h.ordinal()], zn.a(c1856jk));
            } else {
                iF = abstractC0663Md.f();
                if (c1856jk.e.f() == 3) {
                    objA = c1856jk.g().c(iF);
                } else {
                    c1515fkB = c1856jk.g().b(iF);
                    if (c1515fkB == null) {
                        c2285ok0.a(i4, iF);
                        return z;
                    }
                    objA = c1515fkB;
                }
            }
            if (c1856jk.m()) {
                zn.b(c1856jk, objA);
            } else {
                zn.a(c1856jk, objA);
            }
        }
        return z;
    }

    public static void a(WN wn, String str, ArrayList arrayList) {
        Iterator it = Collections.unmodifiableList(Arrays.asList(wn.e().g)).iterator();
        while (true) {
            char c = 0;
            if (!it.hasNext()) {
                break;
            }
            C1856jk c1856jk = (C1856jk) it.next();
            int i = c1856jk.c.h;
            if (i == 1) {
                c = 1;
            } else if (i == 2) {
                c = 2;
            } else if (i == 3) {
                c = 3;
            }
            if ((c != 0 ? c : (char) 1) == 2 && !wn.b(c1856jk)) {
                arrayList.add(str + c1856jk.c.m());
            }
        }
        for (Map.Entry entry : wn.f().entrySet()) {
            C1856jk c1856jk2 = (C1856jk) entry.getKey();
            Object value = entry.getValue();
            if (c1856jk2.h.b == EnumC1686hk.k) {
                if (c1856jk2.m()) {
                    Iterator it2 = ((List) value).iterator();
                    int i2 = 0;
                    while (it2.hasNext()) {
                        a((WN) it2.next(), a(str, c1856jk2, i2), arrayList);
                        i2++;
                    }
                } else if (wn.b(c1856jk2)) {
                    a((WN) value, a(str, c1856jk2, -1), arrayList);
                }
            }
        }
    }

    public static String a(String str, C1856jk c1856jk, int i) {
        StringBuilder sb = new StringBuilder(str);
        if (c1856jk.c.q()) {
            sb.append('(');
            sb.append(c1856jk.d);
            sb.append(')');
        } else {
            sb.append(c1856jk.c.m());
        }
        if (i != -1) {
            sb.append('[');
            sb.append(i);
            sb.append(']');
        }
        sb.append('.');
        return sb.toString();
    }
}
