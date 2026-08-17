package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.A10;
import com.android.tools.r8.internal.AbstractC1451f1;
import com.android.tools.r8.internal.AbstractC1644hE;
import com.android.tools.r8.internal.AbstractC1730iE;
import com.android.tools.r8.internal.AbstractC2015le;
import com.android.tools.r8.internal.AbstractC2071mE;
import com.android.tools.r8.internal.C0389Bo;
import com.android.tools.r8.internal.C0604Jv;
import com.android.tools.r8.internal.C0984Ym;
import com.android.tools.r8.internal.C1197c00;
import com.android.tools.r8.internal.C1320dW;
import com.android.tools.r8.internal.C1450f00;
import com.android.tools.r8.internal.C1491fW;
import com.android.tools.r8.internal.C1516fk0;
import com.android.tools.r8.internal.C1814jE;
import com.android.tools.r8.internal.C2418qI;
import com.android.tools.r8.internal.C2697td;
import com.android.tools.r8.internal.C2903w00;
import com.android.tools.r8.internal.C3100yI;
import com.android.tools.r8.internal.CZ;
import com.android.tools.r8.internal.D00;
import com.android.tools.r8.internal.DI;
import com.android.tools.r8.internal.Ej0;
import com.android.tools.r8.internal.FD;
import com.android.tools.r8.internal.FZ;
import com.android.tools.r8.internal.G00;
import com.android.tools.r8.internal.GD;
import com.android.tools.r8.internal.II;
import com.android.tools.r8.internal.InterfaceC1654hO;
import com.android.tools.r8.internal.J00;
import com.android.tools.r8.internal.KB;
import com.android.tools.r8.internal.ND;
import com.android.tools.r8.internal.Q10;
import com.android.tools.r8.internal.QZ;
import com.android.tools.r8.internal.R00;
import com.android.tools.r8.internal.RH;
import com.android.tools.r8.internal.S10;
import com.android.tools.r8.internal.Sl0;
import com.android.tools.r8.internal.T3;
import com.android.tools.r8.internal.U10;
import com.android.tools.r8.internal.XH;
import com.android.tools.r8.internal.y6;
import com.android.tools.r8.kotlin.Q;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class r {
    public static final int[] b;
    public boolean a;

    static {
        int[] iArr = GD.g.a;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        KB.b(iArrCopyOf, "copyOf(...)");
        b = iArrCopyOf;
    }

    private r() {
        this.a = true;
    }

    public static final r a(Q q) {
        boolean z;
        String string;
        int i;
        if (q.b.length == 0) {
            w01.a("Provided Metadata instance does not have metadataVersion in it and therefore is malformed and cannot be read.");
            return null;
        }
        GD gd = new GD(q.b, (q.e & 8) != 0);
        int i2 = gd.b;
        boolean z2 = i2 > 1 || (i2 >= 1 && ((i = gd.c) > 1 || (i >= 1 && gd.d >= 0)));
        GD gd2 = gd.f ? GD.g : GD.h;
        if ((i2 == 1 && gd.c == 0) || i2 == 0) {
            z = false;
        } else {
            int i3 = gd2.b;
            z = !(i2 > i3 || (i2 >= i3 && gd.c > gd2.c));
        }
        if (!z) {
            if (z2) {
                StringBuilder sb = new StringBuilder("while maximum supported version is ");
                sb.append(gd.f ? GD.g : GD.h);
                sb.append(". To support newer versions, update the kotlinx-metadata-jvm library.");
                string = sb.toString();
            } else {
                string = "while minimum supported version is 1.1.0 (Kotlin 1.0).";
            }
            h0f.a("Provided Metadata instance has version ", gd, ", ", string);
            return null;
        }
        try {
            int i4 = q.a;
            if (i4 == 1) {
                return new a(q);
            }
            if (i4 == 2) {
                b bVar = new b(AbstractC1730iE.a(q), new j(q.b), q.e);
                bVar.a = true;
                return bVar;
            }
            if (i4 == 3) {
                return new t(q);
            }
            if (i4 == 4) {
                c cVar = new c(T3.a(q.c), new j(q.b), q.e);
                cVar.a = true;
                return cVar;
            }
            if (i4 != 5) {
                return new u(q, false);
            }
            d dVar = new d(AbstractC1730iE.a(q), q.f, new j(q.b), q.e);
            dVar.a = true;
            return dVar;
        } catch (Throwable th) {
            if (th instanceof IllegalArgumentException) {
                throw th;
            }
            if (th instanceof VirtualMachineError ? true : th instanceof ThreadDeath) {
                throw th;
            }
            throw new C0604Jv(th);
        }
    }

    public abstract j a();

    public abstract void a(j jVar);

    public abstract h b();

    public static final class a extends r {
        public final RH c;
        public j d;
        public final int e;

        /* JADX WARN: Code duplicated, block: B:106:0x0324  */
        public a(Q q) {
            Sl0 sl0;
            String[] strArr = q.c;
            strArr = strArr.length == 0 ? null : strArr;
            if (strArr == null) {
                throw new C0604Jv("Metadata is missing: kotlin.Metadata.data1 must not be an empty array");
            }
            String[] strArr2 = q.d;
            C0389Bo c0389Bo = AbstractC1644hE.a;
            KB.c(strArr2, "strings");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(y6.a(strArr));
            ND ndA = AbstractC1644hE.a(byteArrayInputStream, strArr2);
            CZ cz = (CZ) AbstractC1451f1.a(CZ.L.a(byteArrayInputStream, AbstractC1644hE.a));
            boolean z = new j(q.b).compareTo(new j(1, 4, 0)) < 0;
            C0984Ym c0984Ym = C0984Ym.b;
            KB.c(cz, "<this>");
            RH rh = new RH();
            G00 g00 = cz.F;
            KB.b(g00, "getTypeTable(...)");
            Ej0 ej0 = new Ej0(g00);
            Sl0 sl1 = Sl0.b;
            R00 r00 = cz.H;
            KB.b(r00, "getVersionRequirementTable(...)");
            if (r00.c.size() == 0) {
                sl0 = Sl0.b;
            } else {
                List list = r00.c;
                KB.b(list, "getRequirementList(...)");
                sl0 = new Sl0(list);
            }
            Q10 q10 = new Q10(ndA, ej0, sl0, z, c0984Ym, 16);
            List list2 = cz.h;
            KB.b(list2, "getTypeParameterList(...)");
            Q10 q10A = q10.a(list2);
            rh.a = cz.e;
            String strA = S10.a(q10A.a, cz.f);
            KB.c(strA, "<set-?>");
            rh.b = strA;
            List<D00> list3 = cz.h;
            KB.b(list3, "getTypeParameterList(...)");
            List<DI> listO = rh.o();
            for (D00 d00 : list3) {
                KB.a(d00);
                listO.add(U10.a(d00, q10A));
            }
            Ej0 ej1 = q10A.b;
            KB.c(ej1, "typeTable");
            List list4 = cz.i;
            list4 = list4.isEmpty() ? null : list4;
            if (list4 == null) {
                List<Integer> list5 = cz.j;
                KB.b(list5, "getSupertypeIdList(...)");
                ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list5));
                for (Integer num : list5) {
                    KB.a(num);
                    arrayList.add((C2903w00) ej1.a.get(num.intValue()));
                }
                list4 = arrayList;
            }
            List<C3100yI> listN = rh.n();
            Iterator it = list4.iterator();
            while (it.hasNext()) {
                listN.add(U10.a((C2903w00) it.next(), q10A));
            }
            List<FZ> list6 = cz.q;
            KB.b(list6, "getConstructorList(...)");
            List<XH> listE = rh.e();
            for (FZ fz : list6) {
                KB.a(fz);
                XH xh = new XH(fz.e);
                List<J00> list7 = fz.f;
                KB.b(list7, "getValueParameterList(...)");
                List<II> listB = xh.b();
                for (J00 j00 : list7) {
                    KB.a(j00);
                    listB.add(U10.a(j00, q10A));
                }
                List<Integer> list8 = fz.g;
                KB.b(list8, "getVersionRequirementList(...)");
                ArrayList arrayList2 = xh.c;
                for (Integer num2 : list8) {
                    KB.a(num2);
                    arrayList2.add(U10.a(num2.intValue(), q10A));
                }
                Iterator it2 = q10A.h.iterator();
                while (it2.hasNext()) {
                    ((FD) ((InterfaceC1654hO) it2.next())).a(xh, fz, q10A);
                }
                listE.add(xh);
            }
            List list9 = cz.r;
            KB.b(list9, "getFunctionList(...)");
            List list10 = cz.s;
            KB.b(list10, "getPropertyList(...)");
            List list11 = cz.t;
            KB.b(list11, "getTypeAliasList(...)");
            U10.a(rh, list9, list10, list11, q10A);
            if ((cz.d & 4) == 4) {
                rh.i = ((ND) q10A.a).a(cz.g);
            }
            List<Integer> list12 = cz.l;
            KB.b(list12, "getNestedClassNameList(...)");
            List<String> listL = rh.l();
            for (Integer num3 : list12) {
                KB.a(num3);
                listL.add(((ND) q10A.a).a(num3.intValue()));
            }
            for (QZ qz : cz.u) {
                if ((qz.d & 1) != 1) {
                    throw new C0604Jv("No name for EnumEntry");
                }
                rh.g().add(((ND) q10A.a).a(qz.e));
            }
            List<Integer> list13 = cz.v;
            KB.b(list13, "getSealedSubclassFqNameList(...)");
            List<String> listM = rh.m();
            for (Integer num4 : list13) {
                KB.a(num4);
                listM.add(S10.a(q10A.a, num4.intValue()));
            }
            if ((cz.d & 8) == 8) {
                rh.m = ((ND) q10A.a).a(cz.x);
            }
            Ej0 ej2 = q10A.b;
            KB.c(ej2, "typeTable");
            int i = cz.d;
            C2903w00 c2903w00A = (i & 16) == 16 ? cz.y : (i & 32) == 32 ? (C2903w00) ej2.a.get(cz.z) : null;
            if (c2903w00A == null) {
                if ((cz.d & 8) == 8) {
                    List list14 = cz.s;
                    KB.b(list14, "getPropertyList(...)");
                    Iterator it3 = list14.iterator();
                    Object obj = null;
                    boolean z2 = false;
                    while (true) {
                        if (!it3.hasNext()) {
                            if (z2) {
                                break;
                            } else {
                                break;
                            }
                        }
                        Object next = it3.next();
                        C1450f00 c1450f00 = (C1450f00) next;
                        KB.a(c1450f00);
                        Ej0 ej3 = q10A.b;
                        KB.c(ej3, "typeTable");
                        int i2 = c1450f00.d;
                        if (((i2 & 32) == 32 ? c1450f00.k : (i2 & 64) == 64 ? (C2903w00) ej3.a.get(c1450f00.l) : null) == null) {
                            if (!((ND) q10A.a).a(c1450f00.g).equals(((ND) q10A.a).a(cz.x))) {
                                continue;
                            } else if (!z2) {
                                z2 = true;
                                obj = next;
                            }
                        }
                        obj = null;
                        break;
                    }
                    C1450f00 c1450f01 = (C1450f00) obj;
                    if (c1450f01 != null) {
                        c2903w00A = A10.a(c1450f01, q10A.b);
                    } else {
                        c2903w00A = null;
                    }
                } else {
                    c2903w00A = null;
                }
            }
            rh.n = c2903w00A != null ? U10.a(c2903w00A, q10A) : null;
            Ej0 ej4 = q10A.b;
            KB.c(ej4, "typeTable");
            List list15 = cz.n;
            list15 = list15.isEmpty() ? null : list15;
            if (list15 == null) {
                List<Integer> list16 = cz.o;
                KB.b(list16, "getContextReceiverTypeIdList(...)");
                ArrayList arrayList3 = new ArrayList(AbstractC2015le.a((Iterable) list16));
                for (Integer num5 : list16) {
                    KB.a(num5);
                    arrayList3.add((C2903w00) ej4.a.get(num5.intValue()));
                }
                list15 = arrayList3;
            }
            ArrayList arrayList4 = rh.o;
            Iterator it4 = list15.iterator();
            while (it4.hasNext()) {
                arrayList4.add(U10.a((C2903w00) it4.next(), q10A));
            }
            List<Integer> list17 = cz.G;
            KB.b(list17, "getVersionRequirementList(...)");
            ArrayList arrayList5 = rh.p;
            for (Integer num6 : list17) {
                KB.a(num6);
                arrayList5.add(U10.a(num6.intValue(), q10A));
            }
            Iterator it5 = q10A.h.iterator();
            while (it5.hasNext()) {
                ((FD) ((InterfaceC1654hO) it5.next())).a(rh, cz, q10A);
            }
            this(rh, new j(q.b), q.e);
            this.a = true;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final void a(j jVar) {
            KB.c(jVar, "<set-?>");
            this.d = jVar;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final h b() {
            s.a("class", this.a);
            s.a(this.d);
            try {
                C2697td c2697td = new C2697td(new C1814jE());
                c2697td.a(this.c);
                CZ czE = c2697td.a.e();
                if (!czE.a()) {
                    throw new C1516fk0();
                }
                C1491fW c1491fWA = AbstractC2071mE.a(czE, c2697td.b);
                String[] strArr = (String[]) c1491fWA.b;
                String[] strArr2 = (String[]) c1491fWA.c;
                j jVar = this.d;
                return i.a((Integer) 1, new int[]{jVar.b, jVar.c, jVar.d}, strArr, strArr2, (String) null, Integer.valueOf(this.e), 48);
            } catch (Throwable th) {
                if (th instanceof IllegalArgumentException) {
                    throw th;
                }
                if (th instanceof VirtualMachineError ? true : th instanceof ThreadDeath) {
                    throw th;
                }
                throw new IllegalArgumentException("Kotlin metadata is not correct and can not be written", th);
            }
        }

        public final RH c() {
            return this.c;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final j a() {
            return this.d;
        }

        public a(RH rh, j jVar, int i) {
            super(0);
            this.c = rh;
            this.d = jVar;
            this.e = i;
        }
    }

    public static final class b extends r {
        public final C2418qI c;
        public j d;
        public final int e;

        public b(C2418qI c2418qI, j jVar, int i) {
            super(0);
            this.c = c2418qI;
            this.d = jVar;
            this.e = i;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final void a(j jVar) {
            KB.c(jVar, "<set-?>");
            this.d = jVar;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final h b() {
            s.a("file facade", this.a);
            s.a(this.d);
            try {
                C1320dW c1320dW = new C1320dW(new C1814jE());
                c1320dW.a(this.c);
                C1197c00 c1197c00E = c1320dW.a.e();
                if (!c1197c00E.a()) {
                    throw new C1516fk0();
                }
                C1491fW c1491fWA = AbstractC2071mE.a(c1197c00E, c1320dW.b);
                String[] strArr = (String[]) c1491fWA.b;
                String[] strArr2 = (String[]) c1491fWA.c;
                j jVar = this.d;
                return i.a((Integer) 2, new int[]{jVar.b, jVar.c, jVar.d}, strArr, strArr2, (String) null, Integer.valueOf(this.e), 48);
            } catch (Throwable th) {
                if (th instanceof IllegalArgumentException) {
                    throw th;
                }
                if (th instanceof VirtualMachineError ? true : th instanceof ThreadDeath) {
                    throw th;
                }
                throw new IllegalArgumentException("Kotlin metadata is not correct and can not be written", th);
            }
        }

        public final C2418qI c() {
            return this.c;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final j a() {
            return this.d;
        }
    }

    public static final class c extends r {
        public final List c;
        public j d;
        public final int e;

        public c(List list, j jVar, int i) {
            super(0);
            this.c = list;
            this.d = jVar;
            this.e = i;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final void a(j jVar) {
            KB.c(jVar, "<set-?>");
            this.d = jVar;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final h b() {
            s.a("multi-file class facade", this.a);
            s.a(this.d);
            j jVar = this.d;
            return i.a((Integer) 4, new int[]{jVar.b, jVar.c, jVar.d}, (String[]) this.c.toArray(new String[0]), (String[]) null, (String) null, Integer.valueOf(this.e), 56);
        }

        public final List<String> c() {
            return this.c;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final j a() {
            return this.d;
        }
    }

    public static final class d extends r {
        public final C2418qI c;
        public final String d;
        public j e;
        public final int f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(C2418qI c2418qI, String str, j jVar, int i) {
            super(0);
            KB.c(str, "facadeClassName");
            this.c = c2418qI;
            this.d = str;
            this.e = jVar;
            this.f = i;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final void a(j jVar) {
            KB.c(jVar, "<set-?>");
            this.e = jVar;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final h b() {
            s.a("multi-file class part", this.a);
            s.a(this.e);
            try {
                C1320dW c1320dW = new C1320dW(new C1814jE());
                c1320dW.a(this.c);
                C1197c00 c1197c00E = c1320dW.a.e();
                if (!c1197c00E.a()) {
                    throw new C1516fk0();
                }
                C1491fW c1491fWA = AbstractC2071mE.a(c1197c00E, c1320dW.b);
                String[] strArr = (String[]) c1491fWA.b;
                String[] strArr2 = (String[]) c1491fWA.c;
                j jVar = this.e;
                return i.a((Integer) 5, new int[]{jVar.b, jVar.c, jVar.d}, strArr, strArr2, this.d, Integer.valueOf(this.f), 32);
            } catch (Throwable th) {
                if (th instanceof IllegalArgumentException) {
                    throw th;
                }
                if (th instanceof VirtualMachineError ? true : th instanceof ThreadDeath) {
                    throw th;
                }
                throw new IllegalArgumentException("Kotlin metadata is not correct and can not be written", th);
            }
        }

        public final C2418qI c() {
            return this.c;
        }

        @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
        public final j a() {
            return this.e;
        }
    }

    public /* synthetic */ r(int i) {
        this();
    }
}
