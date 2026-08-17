package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2673tI extends AbstractC3016xI {
    public static final /* synthetic */ AbstractC2083mQ[] n;
    public int b;
    public final String c;
    public final C2759uI d;
    public final C2759uI e;
    public final int f;
    public final ArrayList g;
    public C3100yI h;
    public final ArrayList i;
    public II j;
    public C3100yI k;
    public final ArrayList l;
    public final ArrayList m;

    static {
        C2168nQ c2168nQ = new C2168nQ(AbstractC2654t40.a(C2673tI.class), "_hasSetter", "get_hasSetter()Z");
        AbstractC2654t40.a.getClass();
        n = new AbstractC2083mQ[]{c2168nQ, new C2168nQ(AbstractC2654t40.a(C2673tI.class), "_hasGetter", "get_hasGetter()Z")};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2673tI(int i, int i2, int i3, String str) {
        super(0);
        KB.c(str, TypeBlock.NAME_name);
        this.b = i;
        this.c = str;
        C2549rq c2549rq = AbstractC2805uq.z;
        KB.b(c2549rq, "HAS_SETTER");
        C2464qq c2464qq = new C2464qq(c2549rq, 1);
        C1950kq c1950kq = C1950kq.i;
        if (c2464qq.b != 1 || c2464qq.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq, " was passed"));
            throw null;
        }
        C2549rq c2549rq2 = AbstractC2805uq.y;
        KB.b(c2549rq2, "HAS_GETTER");
        C2464qq c2464qq2 = new C2464qq(c2549rq2, 1);
        if (c2464qq2.b != 1 || c2464qq2.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq2, " was passed"));
            throw null;
        }
        int i4 = 1 << c2464qq2.a;
        C2759uI c2759uI = new C2759uI(i2);
        AbstractC2083mQ[] abstractC2083mQArr = n;
        KB.c(abstractC2083mQArr[1], "property");
        c1950kq.a(Integer.valueOf(i4 | ((Number) c1950kq.a(this)).intValue()), this);
        this.d = c2759uI;
        KB.c(abstractC2083mQArr[0], "property");
        this.e = ((((Number) c1950kq.a(this)).intValue() >>> c2464qq.a) & ((1 << c2464qq.b) - 1)) == c2464qq.c ? new C2759uI(i3) : null;
        this.f = i3;
        this.g = new ArrayList(0);
        this.i = new ArrayList(0);
        this.l = new ArrayList(0);
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FD) ((InterfaceC1654hO) it.next())).e());
        }
        this.m = arrayList;
    }

    public final void a(AbstractC3016xI abstractC3016xI) {
        II iiA;
        HI hiB;
        KB.c(abstractC3016xI, "visitor");
        for (DI di : this.g) {
            DI diA = abstractC3016xI.a(di.a(), di.c(), di.b(), di.e());
            if (diA != null) {
                di.a(diA);
            }
        }
        C3100yI c3100yI = this.h;
        if (c3100yI != null && (hiB = abstractC3016xI.b(c3100yI.e())) != null) {
            c3100yI.a(hiB);
        }
        for (C3100yI c3100yI2 : this.i) {
            HI hiA = abstractC3016xI.a(c3100yI2.e());
            if (hiA != null) {
                c3100yI2.a(hiA);
            }
        }
        II ii = this.j;
        if (ii != null && (iiA = abstractC3016xI.a(ii.a(), ii.c)) != null) {
            ii.a(iiA);
        }
        HI hiC = abstractC3016xI.c(h().e());
        if (hiC != null) {
            h().a(hiC);
        }
        for (MI mi : this.l) {
            MI miB = abstractC3016xI.b();
            if (miB != null) {
                mi.a(miB);
            }
        }
        for (InterfaceC2930wI interfaceC2930wI : this.m) {
            ((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m) interfaceC2930wI).getClass();
            InterfaceC2930wI interfaceC2930wIA = abstractC3016xI.a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m.b);
            if (interfaceC2930wIA != null) {
                PD pd = (PD) interfaceC2930wI;
                pd.getClass();
                if (!(interfaceC2930wIA instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m)) {
                    w01.a("Failed requirement.");
                    return;
                }
                com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m mVar = (com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m) interfaceC2930wIA;
                mVar.a(pd.c, pd.d, pd.e, pd.f);
                mVar.a(pd.g);
                mVar.b(pd.h);
                mVar.b();
            }
        }
        abstractC3016xI.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final MI b() {
        MI mi = new MI();
        ArrayList arrayList = this.l;
        KB.c(arrayList, "collection");
        arrayList.add(mi);
        return mi;
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final HI c(int i) {
        C3100yI c3100yI = new C3100yI(i);
        this.k = c3100yI;
        return c3100yI;
    }

    public final int d() {
        return this.b;
    }

    public final int e() {
        return this.d.a;
    }

    public final String f() {
        return this.c;
    }

    public final C3100yI g() {
        return this.h;
    }

    public final C3100yI h() {
        C3100yI c3100yI = this.k;
        if (c3100yI != null) {
            return c3100yI;
        }
        KB.a("returnType");
        throw null;
    }

    public final int i() {
        C2759uI c2759uI = this.e;
        return c2759uI != null ? c2759uI.a : this.f;
    }

    public final ArrayList j() {
        return this.l;
    }

    public final List<InterfaceC2844vI> c() {
        return this.m;
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final HI b(int i) {
        C3100yI c3100yI = new C3100yI(i);
        this.h = c3100yI;
        return c3100yI;
    }

    public final void b(C3100yI c3100yI) {
        KB.c(c3100yI, "<set-?>");
        this.k = c3100yI;
    }

    public final void a(II ii) {
        this.j = ii;
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final DI a(int i, String str, int i2, KI ki) {
        KB.c(str, TypeBlock.NAME_name);
        KB.c(ki, "variance");
        DI di = new DI(i, str, i2, ki);
        ArrayList arrayList = this.g;
        KB.c(arrayList, "collection");
        arrayList.add(di);
        return di;
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final HI a(int i) {
        C3100yI c3100yI = new C3100yI(i);
        ArrayList arrayList = this.i;
        KB.c(arrayList, "collection");
        arrayList.add(c3100yI);
        return c3100yI;
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final II a(int i, String str) {
        KB.c(str, TypeBlock.NAME_name);
        II ii = new II(i, str);
        this.j = ii;
        return ii;
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final InterfaceC2930wI a(C1734iI c1734iI) {
        KB.c(c1734iI, "type");
        return (InterfaceC2930wI) AbstractC0441Do.a(this.m, c1734iI);
    }

    public final void a(C3100yI c3100yI) {
        this.h = c3100yI;
    }
}
