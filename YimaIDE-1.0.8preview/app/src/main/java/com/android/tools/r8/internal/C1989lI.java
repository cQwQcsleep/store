package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1989lI extends AbstractC2246oI {
    public int b;
    public final String c;
    public final ArrayList d;
    public C3100yI e;
    public final ArrayList f;
    public final ArrayList g;
    public C3100yI h;
    public final ArrayList i;
    public C1053aI j;
    public final ArrayList k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1989lI(int i, String str) {
        super(0);
        KB.c(str, TypeBlock.NAME_name);
        this.b = i;
        this.c = str;
        this.d = new ArrayList(0);
        this.f = new ArrayList(0);
        this.g = new ArrayList();
        this.i = new ArrayList(0);
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FD) ((InterfaceC1654hO) it.next())).c());
        }
        this.k = arrayList;
    }

    public final void a(AbstractC2246oI abstractC2246oI) {
        C1053aI c1053aIA;
        HI hiB;
        KB.c(abstractC2246oI, "visitor");
        for (DI di : this.d) {
            DI diA = abstractC2246oI.a(di.a(), di.c(), di.b(), di.e());
            if (diA != null) {
                di.a(diA);
            }
        }
        C3100yI c3100yI = this.e;
        if (c3100yI != null && (hiB = abstractC2246oI.b(c3100yI.e())) != null) {
            c3100yI.a(hiB);
        }
        for (C3100yI c3100yI2 : this.f) {
            HI hiA = abstractC2246oI.a(c3100yI2.e());
            if (hiA != null) {
                c3100yI2.a(hiA);
            }
        }
        for (II ii : this.g) {
            II iiA = abstractC2246oI.a(ii.a(), ii.c);
            if (iiA != null) {
                ii.a(iiA);
            }
        }
        HI hiC = abstractC2246oI.c(h().e());
        if (hiC != null) {
            h().a(hiC);
        }
        for (MI mi : this.i) {
            MI miC = abstractC2246oI.c();
            if (miC != null) {
                mi.a(miC);
            }
        }
        C1053aI c1053aI = this.j;
        if (c1053aI != null && (c1053aIA = abstractC2246oI.a()) != null) {
            for (C1307dI c1307dI : c1053aI.a) {
                EnumC1563gI enumC1563gI = c1307dI.a;
                EnumC1477fI enumC1477fI = c1307dI.b;
                KB.c(enumC1563gI, "type");
                C1307dI c1307dI2 = new C1307dI(enumC1563gI, enumC1477fI);
                ArrayList arrayList = c1053aIA.a;
                KB.c(arrayList, "collection");
                arrayList.add(c1307dI2);
                for (C1391eI c1391eI : c1307dI.c) {
                    C1391eI c1391eI2 = new C1391eI();
                    ArrayList arrayList2 = c1307dI2.c;
                    KB.c(arrayList2, "collection");
                    arrayList2.add(c1391eI2);
                    c1391eI.a(c1391eI2);
                }
                C1391eI c1391eI3 = c1307dI.d;
                if (c1391eI3 != null) {
                    C1391eI c1391eI4 = new C1391eI();
                    c1307dI2.d = c1391eI4;
                    c1391eI3.a(c1391eI4);
                }
            }
        }
        for (InterfaceC2160nI interfaceC2160nI : this.k) {
            ((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f) interfaceC2160nI).getClass();
            InterfaceC2160nI interfaceC2160nIA = abstractC2246oI.a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f.b);
            if (interfaceC2160nIA != null) {
                BD bd = (BD) interfaceC2160nI;
                bd.getClass();
                if (!(interfaceC2160nIA instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f)) {
                    w01.a("Failed requirement.");
                    return;
                }
                com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f fVar = (com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.f) interfaceC2160nIA;
                fVar.a(bd.c);
                String str = bd.d;
                if (str != null) {
                    fVar.a(str);
                }
                fVar.a();
            }
        }
        abstractC2246oI.b();
    }

    public final void b(C3100yI c3100yI) {
        KB.c(c3100yI, "<set-?>");
        this.h = c3100yI;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final MI c() {
        MI mi = new MI();
        ArrayList arrayList = this.i;
        KB.c(arrayList, "collection");
        arrayList.add(mi);
        return mi;
    }

    public final C1053aI d() {
        return this.j;
    }

    public final int e() {
        return this.b;
    }

    public final String f() {
        return this.c;
    }

    public final C3100yI g() {
        return this.e;
    }

    public final C3100yI h() {
        C3100yI c3100yI = this.h;
        if (c3100yI != null) {
            return c3100yI;
        }
        KB.a("returnType");
        throw null;
    }

    public final List<DI> i() {
        return this.d;
    }

    public final List<II> j() {
        return this.g;
    }

    public final ArrayList k() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final HI b(int i) {
        C3100yI c3100yI = new C3100yI(i);
        this.e = c3100yI;
        return c3100yI;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final HI c(int i) {
        C3100yI c3100yI = new C3100yI(i);
        this.h = c3100yI;
        return c3100yI;
    }

    public final void a(C1053aI c1053aI) {
        this.j = c1053aI;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final DI a(int i, String str, int i2, KI ki) {
        KB.c(str, TypeBlock.NAME_name);
        KB.c(ki, "variance");
        DI di = new DI(i, str, i2, ki);
        ArrayList arrayList = this.d;
        KB.c(arrayList, "collection");
        arrayList.add(di);
        return di;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final HI a(int i) {
        C3100yI c3100yI = new C3100yI(i);
        ArrayList arrayList = this.f;
        KB.c(arrayList, "collection");
        arrayList.add(c3100yI);
        return c3100yI;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final II a(int i, String str) {
        KB.c(str, TypeBlock.NAME_name);
        II ii = new II(i, str);
        ArrayList arrayList = this.g;
        KB.c(arrayList, "collection");
        arrayList.add(ii);
        return ii;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final C1053aI a() {
        C1053aI c1053aI = new C1053aI();
        this.j = c1053aI;
        return c1053aI;
    }

    @Override // com.android.tools.r8.internal.AbstractC2246oI
    public final InterfaceC2160nI a(C1734iI c1734iI) {
        KB.c(c1734iI, "type");
        return (InterfaceC2160nI) AbstractC0441Do.a(this.k, c1734iI);
    }

    public final void a(C3100yI c3100yI) {
        this.e = c3100yI;
    }
}
