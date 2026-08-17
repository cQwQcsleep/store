package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FD implements InterfaceC1654hO {
    /* JADX WARN: Code duplicated, block: B:43:0x0120  */
    /* JADX WARN: Code duplicated, block: B:46:0x013a  */
    /* JADX WARN: Code duplicated, block: B:53:? A[RETURN, SYNTHETIC] */
    public void a(C1989lI c1989lI, XZ xz, Q10 q10) {
        C2903w00 c2903w00;
        C2903w00 c2903w01;
        String strConcat;
        DD dd;
        Integer num;
        KB.c(c1989lI, "kmFunction");
        KB.c(xz, "proto");
        KB.c(q10, "c");
        BD bdA = AD.a(c1989lI);
        C0389Bo c0389Bo = AbstractC1644hE.a;
        InterfaceC2425qQ interfaceC2425qQ = q10.a;
        Ej0 ej0 = q10.b;
        KB.c(interfaceC2425qQ, "nameResolver");
        KB.c(ej0, "typeTable");
        C0703Nr c0703Nr = AbstractC1559gE.b;
        KB.b(c0703Nr, "methodSignature");
        VD vd = (VD) T00.a(xz, c0703Nr);
        int i = (vd == null || (vd.c & 1) != 1) ? xz.g : vd.d;
        if (vd == null || (vd.c & 2) != 2) {
            int i2 = xz.d;
            if ((i2 & 32) == 32) {
                c2903w00 = xz.k;
            } else if ((i2 & 64) == 64) {
                c2903w00 = (C2903w00) ej0.a.get(xz.l);
            } else {
                c2903w00 = null;
            }
            List listA = AbstractC1929ke.a(c2903w00);
            List<J00> list = xz.p;
            KB.b(list, "getValueParameterList(...)");
            ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
            for (J00 j00 : list) {
                KB.a(j00);
                arrayList.add(A10.a(j00, ej0));
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size() + listA.size());
            arrayList2.addAll(listA);
            arrayList2.addAll(arrayList);
            ArrayList arrayList3 = new ArrayList(AbstractC2015le.a((Iterable) arrayList2));
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    int i3 = xz.d;
                    if ((i3 & 8) == 8) {
                        c2903w01 = xz.h;
                        KB.b(c2903w01, "getReturnType(...)");
                    } else {
                        if ((i3 & 16) != 16) {
                            k2d.a("No returnType in ProtoBuf.Function");
                            return;
                        }
                        c2903w01 = (C2903w00) ej0.a.get(xz.i);
                    }
                    String strA = AbstractC1644hE.a(c2903w01, interfaceC2425qQ);
                    if (strA != null) {
                        strConcat = AbstractC1760ie.a(arrayList3, XmlPullParser.NO_NAMESPACE, "(", ")", null, 56).concat(strA);
                    }
                    bdA.c = dd != null ? new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(dd.a, dd.b) : null;
                    C0703Nr c0703Nr2 = AbstractC1559gE.c;
                    KB.b(c0703Nr2, "lambdaClassOriginName");
                    num = (Integer) T00.a(xz, c0703Nr2);
                    if (num != null) {
                        bdA.d = ((ND) q10.a).a(num.intValue());
                    }
                }
                String strA2 = AbstractC1644hE.a((C2903w00) it.next(), interfaceC2425qQ);
                if (strA2 != null) {
                    arrayList3.add(strA2);
                }
                dd = null;
                bdA.c = dd != null ? new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(dd.a, dd.b) : null;
                C0703Nr c0703Nr3 = AbstractC1559gE.c;
                KB.b(c0703Nr3, "lambdaClassOriginName");
                num = (Integer) T00.a(xz, c0703Nr3);
                if (num != null) {
                    bdA.d = ((ND) q10.a).a(num.intValue());
                }
            }
        }
        strConcat = ((ND) interfaceC2425qQ).a(vd.e);
        dd = new DD(((ND) interfaceC2425qQ).a(i), strConcat);
        bdA.c = dd != null ? new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(dd.a, dd.b) : null;
        C0703Nr c0703Nr4 = AbstractC1559gE.c;
        KB.b(c0703Nr4, "lambdaClassOriginName");
        num = (Integer) T00.a(xz, c0703Nr4);
        if (num != null) {
            bdA.d = ((ND) q10.a).a(num.intValue());
        }
    }

    public YH b() {
        return new C3180zD();
    }

    public InterfaceC2075mI c() {
        return new BD();
    }

    public InterfaceC2502rI d() {
        return new OD();
    }

    public InterfaceC2844vI e() {
        return new PD();
    }

    public AI f() {
        return null;
    }

    public BI g() {
        return new C1899kE();
    }

    public EI h() {
        return new C1985lE();
    }

    public JI i() {
        return null;
    }

    public void a(II ii, J00 j00, Q10 q10) {
        KB.c(ii, "kmValueParameter");
        KB.c(j00, "proto");
        KB.c(q10, "c");
    }

    public void a(C3185zI c3185zI, C3074y00 c3074y00, Um0 um0) {
        KB.c(c3185zI, "typeAlias");
        KB.c(c3074y00, "proto");
        KB.c(um0, "c");
    }

    public void a(II ii, I00 i00, Um0 um0) {
        KB.c(ii, "valueParameter");
        KB.c(i00, "proto");
        KB.c(um0, "c");
    }

    public void a(RH rh, CZ cz, Q10 q10) {
        String strA;
        KB.c(rh, "kmClass");
        KB.c(cz, "proto");
        KB.c(q10, "c");
        C3011xD c3011xDA = AD.a(rh);
        C0703Nr c0703Nr = AbstractC1559gE.k;
        KB.b(c0703Nr, "anonymousObjectOriginName");
        Integer num = (Integer) T00.a(cz, c0703Nr);
        if (num != null) {
            c3011xDA.d = ((ND) q10.a).a(num.intValue());
        }
        for (C1450f00 c1450f00 : (List) cz.a(AbstractC1559gE.j)) {
            ArrayList arrayList = c3011xDA.b;
            KB.a(c1450f00);
            arrayList.add(U10.a(c1450f00, q10));
        }
        C0703Nr c0703Nr2 = AbstractC1559gE.i;
        KB.b(c0703Nr2, "classModuleName");
        Integer num2 = (Integer) T00.a(cz, c0703Nr2);
        if (num2 != null) {
            strA = ((ND) q10.a).a(num2.intValue());
        } else {
            strA = "main";
        }
        c3011xDA.c = strA;
        C0703Nr c0703Nr3 = AbstractC1559gE.l;
        KB.b(c0703Nr3, "jvmClassFlags");
        Integer num3 = (Integer) T00.a(cz, c0703Nr3);
        if (num3 != null) {
            c3011xDA.e = num3.intValue();
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x00ac  */
    public void a(XH xh, FZ fz, Q10 q10) {
        String strA;
        String strA2;
        DD dd;
        KB.c(xh, "kmConstructor");
        KB.c(fz, "proto");
        KB.c(q10, "c");
        C1734iI c1734iI = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.b.a;
        KB.c(c1734iI, "type");
        C3180zD c3180zD = (C3180zD) ((ZH) AbstractC0441Do.a(xh.d, c1734iI));
        C0389Bo c0389Bo = AbstractC1644hE.a;
        InterfaceC2425qQ interfaceC2425qQ = q10.a;
        Ej0 ej0 = q10.b;
        KB.c(interfaceC2425qQ, "nameResolver");
        KB.c(ej0, "typeTable");
        C0703Nr c0703Nr = AbstractC1559gE.a;
        KB.b(c0703Nr, "constructorSignature");
        VD vd = (VD) T00.a(fz, c0703Nr);
        if (vd != null && (vd.c & 1) == 1) {
            strA = ((ND) interfaceC2425qQ).a(vd.d);
        } else {
            strA = "<init>";
        }
        if (vd != null && (vd.c & 2) == 2) {
            strA2 = ((ND) interfaceC2425qQ).a(vd.e);
        } else {
            List list = fz.f;
            KB.b(list, "getValueParameterList(...)");
            ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
            Iterator it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    J00 j00 = (J00) it.next();
                    C0389Bo c0389Bo2 = AbstractC1644hE.a;
                    KB.a(j00);
                    String strA3 = AbstractC1644hE.a(A10.a(j00, ej0), interfaceC2425qQ);
                    if (strA3 == null) {
                        dd = null;
                        break;
                    }
                    arrayList.add(strA3);
                } else {
                    strA2 = AbstractC1760ie.a(arrayList, XmlPullParser.NO_NAMESPACE, "(", ")V", null, 56);
                }
            }
            c3180zD.b = dd != null ? new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(dd.a, dd.b) : null;
        }
        dd = new DD(strA, strA2);
        c3180zD.b = dd != null ? new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(dd.a, dd.b) : null;
    }

    public void a(XH xh, EZ ez, Um0 um0) {
        KB.c(xh, "kmConstructor");
        KB.c(ez, "proto");
        KB.c(um0, "c");
        C1734iI c1734iI = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.b.a;
        KB.c(c1734iI, "type");
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar = ((C3180zD) ((ZH) AbstractC0441Do.a(xh.d, c1734iI))).b;
        if (kVar != null) {
            ez.a(AbstractC1559gE.a, a(kVar, um0));
        }
    }

    public void a(DI di, D00 d00, Q10 q10) {
        KB.c(di, "kmTypeParameter");
        KB.c(d00, "proto");
        KB.c(q10, "c");
        C1734iI c1734iI = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o.b;
        KB.c(c1734iI, "type");
        C1985lE c1985lE = (C1985lE) ((FI) AbstractC0441Do.a(di.g, c1734iI));
        for (C3117yZ c3117yZ : (List) d00.a(AbstractC1559gE.h)) {
            ArrayList arrayList = c1985lE.c;
            KB.a(c3117yZ);
            arrayList.add(S10.a(c3117yZ, q10.a));
        }
    }

    public void a(DI di, B00 b00, Um0 um0) {
        KB.c(di, "kmTypeParameter");
        KB.c(b00, "proto");
        KB.c(um0, "c");
        C1734iI c1734iI = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o.b;
        KB.c(c1734iI, "type");
        for (C3015xH c3015xH : ((C1985lE) ((FI) AbstractC0441Do.a(di.g, c1734iI))).c) {
            C0703Nr c0703Nr = AbstractC1559gE.h;
            C3117yZ c3117yZE = Vm0.a(c3015xH, um0.a).e();
            if (c3117yZE.a()) {
                if (c0703Nr.a == b00.d()) {
                    if (!b00.d) {
                        b00.c = b00.c.m10clone();
                        b00.d = true;
                    }
                    b00.c.a(c0703Nr.d, c0703Nr.b(c3117yZE));
                } else {
                    w01.a("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
                    return;
                }
            } else {
                defpackage.bk.a();
                return;
            }
        }
    }

    public void a(C3100yI c3100yI, C2903w00 c2903w00, Q10 q10) {
        KB.c(c3100yI, "kmType");
        KB.c(c2903w00, "proto");
        KB.c(q10, "c");
        C1899kE c1899kE = (C1899kE) c3100yI.a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n.b);
        Object objA = c2903w00.a(AbstractC1559gE.g);
        KB.b(objA, "getExtension(...)");
        c1899kE.c = ((Boolean) objA).booleanValue();
        for (C3117yZ c3117yZ : (List) c2903w00.a(AbstractC1559gE.f)) {
            ArrayList arrayList = c1899kE.d;
            KB.a(c3117yZ);
            arrayList.add(S10.a(c3117yZ, q10.a));
        }
    }

    public void a(C3100yI c3100yI, C2817v00 c2817v00, Um0 um0) {
        KB.c(c3100yI, "type");
        KB.c(c2817v00, "proto");
        KB.c(um0, "c");
        C1899kE c1899kE = (C1899kE) c3100yI.a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n.b);
        if (c1899kE.c) {
            c2817v00.a(AbstractC1559gE.g, Boolean.TRUE);
        }
        for (C3015xH c3015xH : c1899kE.d) {
            C0703Nr c0703Nr = AbstractC1559gE.f;
            C3117yZ c3117yZE = Vm0.a(c3015xH, um0.a).e();
            if (c3117yZE.a()) {
                if (c0703Nr.a == c2817v00.d()) {
                    if (!c2817v00.d) {
                        c2817v00.c = c2817v00.c.m10clone();
                        c2817v00.d = true;
                    }
                    c2817v00.c.a(c0703Nr.d, c0703Nr.b(c3117yZE));
                } else {
                    w01.a("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
                    return;
                }
            } else {
                defpackage.bk.a();
                return;
            }
        }
    }

    public void a(C2418qI c2418qI, C1197c00 c1197c00, Q10 q10) {
        String strA;
        KB.c(c2418qI, "kmPackage");
        KB.c(c1197c00, "proto");
        KB.c(q10, "c");
        OD odA = AD.a(c2418qI);
        for (C1450f00 c1450f00 : (List) c1197c00.a(AbstractC1559gE.n)) {
            ArrayList arrayList = odA.b;
            KB.a(c1450f00);
            arrayList.add(U10.a(c1450f00, q10));
        }
        C0703Nr c0703Nr = AbstractC1559gE.m;
        KB.b(c0703Nr, "packageModuleName");
        Integer num = (Integer) T00.a(c1197c00, c0703Nr);
        if (num != null) {
            strA = ((ND) q10.a).a(num.intValue());
        } else {
            strA = "main";
        }
        odA.c = strA;
    }

    public void a(C3185zI c3185zI, C3158z00 c3158z00, Q10 q10) {
        KB.c(c3185zI, "kmTypeAlias");
        KB.c(c3158z00, "proto");
        KB.c(q10, "c");
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0064  */
    public void a(C2673tI c2673tI, C1450f00 c1450f00, Q10 q10) {
        CD cd;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar2;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar3;
        int i;
        String strA;
        KB.c(c2673tI, "kmProperty");
        KB.c(c1450f00, "proto");
        KB.c(q10, "c");
        PD pdA = AD.a(c2673tI);
        C0389Bo c0389Bo = AbstractC1644hE.a;
        InterfaceC2425qQ interfaceC2425qQ = q10.a;
        Ej0 ej0 = q10.b;
        KB.c(interfaceC2425qQ, "nameResolver");
        KB.c(ej0, "typeTable");
        C0703Nr c0703Nr = AbstractC1559gE.d;
        KB.b(c0703Nr, "propertySignature");
        YD yd = (YD) T00.a(c1450f00, c0703Nr);
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar4 = null;
        if (yd != null) {
            SD sd = (yd.c & 1) == 1 ? yd.d : null;
            if (sd == null) {
                cd = null;
            } else {
                int i2 = sd.c;
                if ((i2 & 1) == 1) {
                    i = sd.d;
                } else {
                    i = c1450f00.g;
                }
                if ((i2 & 2) == 2) {
                    strA = ((ND) interfaceC2425qQ).a(sd.e);
                } else {
                    strA = AbstractC1644hE.a(A10.a(c1450f00, ej0), interfaceC2425qQ);
                    if (strA == null) {
                        cd = null;
                    }
                }
                cd = new CD(((ND) interfaceC2425qQ).a(i), strA);
            }
        } else {
            cd = null;
        }
        YD yd2 = (YD) T00.a(c1450f00, c0703Nr);
        VD vd = (yd2 == null || (yd2.c & 4) != 4) ? null : yd2.f;
        VD vd2 = (yd2 == null || (yd2.c & 8) != 8) ? null : yd2.g;
        Object objA = c1450f00.a(AbstractC1559gE.e);
        KB.b(objA, "getExtension(...)");
        pdA.c = ((Number) objA).intValue();
        pdA.d = cd != null ? new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.e(cd.a, cd.b) : null;
        if (vd != null) {
            kVar = new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(((ND) q10.a).a(vd.d), ((ND) q10.a).a(vd.e));
        } else {
            kVar = null;
        }
        pdA.e = kVar;
        if (vd2 != null) {
            kVar2 = new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(((ND) q10.a).a(vd2.d), ((ND) q10.a).a(vd2.e));
        } else {
            kVar2 = null;
        }
        pdA.f = kVar2;
        VD vd3 = (yd2 == null || (yd2.c & 2) != 2) ? null : yd2.e;
        if (vd3 != null) {
            kVar3 = new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(((ND) q10.a).a(vd3.d), ((ND) q10.a).a(vd3.e));
        } else {
            kVar3 = null;
        }
        pdA.g = kVar3;
        VD vd4 = (yd2 == null || (yd2.c & 16) != 16) ? null : yd2.h;
        if (vd4 != null) {
            kVar4 = new com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k(((ND) q10.a).a(vd4.d), ((ND) q10.a).a(vd4.e));
        }
        pdA.h = kVar4;
    }

    public void a(RH rh, AZ az, Um0 um0) {
        KB.c(rh, "kmClass");
        KB.c(az, "proto");
        KB.c(um0, "c");
        C3011xD c3011xDA = AD.a(rh);
        String str = c3011xDA.d;
        if (str != null) {
        }
        for (C2673tI c2673tI : c3011xDA.b) {
            C0703Nr c0703Nr = AbstractC1559gE.j;
            C1450f00 c1450f00E = Xm0.a(um0, c2673tI).e();
            if (c1450f00E.a()) {
                if (c0703Nr.a == az.d()) {
                    if (!az.d) {
                        az.c = az.c.m10clone();
                        az.d = true;
                    }
                    az.c.a(c0703Nr.d, c0703Nr.b(c1450f00E));
                } else {
                    w01.a("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
                    return;
                }
            } else {
                defpackage.bk.a();
                return;
            }
        }
        String str2 = c3011xDA.c;
        if (str2 != null && !str2.equals("main")) {
            az.a(AbstractC1559gE.i, Integer.valueOf(um0.a.a(str2)));
        }
        int i = c3011xDA.e;
        if (i != 0) {
            az.a(AbstractC1559gE.l, Integer.valueOf(i));
        }
    }

    public void a(C2418qI c2418qI, C1112b00 c1112b00, Um0 um0) {
        KB.c(c2418qI, "kmPackage");
        KB.c(c1112b00, "proto");
        KB.c(um0, "c");
        OD odA = AD.a(c2418qI);
        for (C2673tI c2673tI : odA.b) {
            C0703Nr c0703Nr = AbstractC1559gE.n;
            C1450f00 c1450f00E = Xm0.a(um0, c2673tI).e();
            if (c1450f00E.a()) {
                if (c0703Nr.a == c1112b00.d()) {
                    if (!c1112b00.d) {
                        c1112b00.c = c1112b00.c.m10clone();
                        c1112b00.d = true;
                    }
                    c1112b00.c.a(c0703Nr.d, c0703Nr.b(c1450f00E));
                } else {
                    w01.a("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
                    return;
                }
            } else {
                defpackage.bk.a();
                return;
            }
        }
        String str = odA.c;
        if (str == null || str.equals("main")) {
            return;
        }
        c1112b00.a(AbstractC1559gE.m, Integer.valueOf(um0.a.a(str)));
    }

    public void a(C1989lI c1989lI, WZ wz, Um0 um0) {
        KB.c(c1989lI, "kmFunction");
        KB.c(wz, "proto");
        KB.c(um0, "c");
        BD bdA = AD.a(c1989lI);
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar = bdA.c;
        if (kVar != null) {
        }
        String str = bdA.d;
        if (str != null) {
        }
    }

    public void a(C2673tI c2673tI, C1364e00 c1364e00, Um0 um0) {
        boolean z;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar;
        KB.c(c2673tI, "kmProperty");
        KB.c(c1364e00, "proto");
        KB.c(um0, "c");
        PD pdA = AD.a(c2673tI);
        YD yd = YD.k;
        XD xd = new XD();
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.e eVar = pdA.d;
        boolean z2 = true;
        if (eVar != null) {
            RD rd = new RD();
            String strD = eVar.d();
            KB.c(strD, "string");
            int iA = um0.a.a(strD);
            rd.c |= 1;
            rd.d = iA;
            com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.e eVar2 = pdA.d;
            KB.a(eVar2);
            String str = eVar2.b;
            KB.c(str, "string");
            int iA2 = um0.a.a(str);
            rd.c |= 2;
            rd.e = iA2;
            SD sdE = rd.e();
            if (sdE.a()) {
                xd.d = sdE;
                xd.c |= 1;
                z = true;
            } else {
                defpackage.bk.a();
                return;
            }
        } else {
            z = false;
        }
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar2 = pdA.e;
        if (kVar2 != null) {
            xd.f = a(kVar2, um0);
            xd.c |= 4;
            z = true;
        }
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVar3 = pdA.f;
        if (kVar3 != null) {
            xd.g = a(kVar3, um0);
            xd.c |= 8;
        } else {
            z2 = z;
        }
        if (z2 && pdA.c() != null) {
            com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.k kVarC = pdA.c();
            KB.a(kVarC);
            xd.e = a(kVarC, um0);
            xd.c |= 2;
        }
        if (z2 && (kVar = pdA.h) != null) {
            xd.h = a(kVar, um0);
            xd.c |= 16;
        }
        int i = pdA.c;
        C1450f00 c1450f00 = C1450f00.v;
        C0703Nr c0703Nr = AbstractC1559gE.e;
        Integer num = (Integer) c1450f00.a(c0703Nr);
        if (num == null || i != num.intValue()) {
            c1364e00.a(c0703Nr, Integer.valueOf(pdA.c));
        }
        if (z2) {
            C0703Nr c0703Nr2 = AbstractC1559gE.d;
            YD ydE = xd.e();
            if (ydE.a()) {
                c1364e00.a(c0703Nr2, ydE);
            } else {
                defpackage.bk.a();
            }
        }
    }

    public SH a() {
        return new C3011xD();
    }

    public static VD a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.g gVar, Um0 um0) {
        VD vd = VD.h;
        UD ud = new UD();
        String strD = gVar.d();
        um0.getClass();
        KB.c(strD, "string");
        int iA = um0.a.a(strD);
        ud.c |= 1;
        ud.d = iA;
        String strC = gVar.c();
        KB.c(strC, "string");
        int iA2 = um0.a.a(strC);
        ud.c |= 2;
        ud.e = iA2;
        VD vdE = ud.e();
        if (vdE.a()) {
            return vdE;
        }
        defpackage.bk.a();
        return null;
    }
}
