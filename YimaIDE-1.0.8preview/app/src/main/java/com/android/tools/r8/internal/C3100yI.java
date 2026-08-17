package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3100yI extends HI {
    public int b;
    public VH c;
    public final ArrayList d;
    public C3100yI e;
    public C3100yI f;
    public C1903kI g;
    public final ArrayList h;

    public C3100yI(int i) {
        super(0);
        this.b = i;
        this.d = new ArrayList(0);
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FD) ((InterfaceC1654hO) it.next())).g());
        }
        this.h = arrayList;
    }

    public final void a(HI hi) {
        HI hiA;
        HI hiB;
        HI hiA2;
        KB.c(hi, "visitor");
        VH vh = this.c;
        if (vh == null) {
            KB.a("classifier");
            throw null;
        }
        if (vh instanceof UH) {
            hi.a(((UH) vh).a);
        } else if (vh instanceof VH.b) {
            hi.c(((VH.b) vh).a());
        } else if (vh instanceof VH.a) {
            hi.b(((VH.a) vh).a);
        }
        for (GI gi : this.d) {
            if (KB.a(gi, GI.c)) {
                hi.b();
            } else {
                KI ki = gi.a;
                C3100yI c3100yI = gi.b;
                if (ki == null || c3100yI == null) {
                    throw new C0604Jv("Variance and type must be set for non-star type projection");
                }
                HI hiA3 = hi.a(c3100yI.b, ki);
                if (hiA3 != null) {
                    c3100yI.a(hiA3);
                }
            }
        }
        C3100yI c3100yI2 = this.e;
        if (c3100yI2 != null && (hiA2 = hi.a(c3100yI2.b)) != null) {
            c3100yI2.a(hiA2);
        }
        C3100yI c3100yI3 = this.f;
        if (c3100yI3 != null && (hiB = hi.b(c3100yI3.b)) != null) {
            c3100yI3.a(hiB);
        }
        C1903kI c1903kI = this.g;
        if (c1903kI != null && (hiA = hi.a(c1903kI.a().b, c1903kI.b())) != null) {
            c1903kI.a().a(hiA);
        }
        for (CI ci : this.h) {
            ((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n) ci).getClass();
            CI ciA = hi.a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n.b);
            if (ciA != null) {
                C1899kE c1899kE = (C1899kE) ci;
                c1899kE.getClass();
                if (!(ciA instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n)) {
                    w01.a("Failed requirement.");
                    return;
                }
                com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n nVar = (com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n) ciA;
                C1899kE c1899kE2 = (C1899kE) nVar;
                c1899kE2.c = c1899kE.c;
                for (C3015xH c3015xH : c1899kE.d) {
                    KB.c(c3015xH, "annotation");
                    c1899kE2.d.add(c3015xH);
                }
                nVar.a();
            }
        }
        hi.a();
    }

    @Override // com.android.tools.r8.internal.HI
    public final void b(String str) {
        KB.c(str, TypeBlock.NAME_name);
        this.c = new VH.a(str);
    }

    @Override // com.android.tools.r8.internal.HI
    public final void c(int i) {
        this.c = new VH.b(i);
    }

    public final List<GI> d() {
        return this.d;
    }

    public final int e() {
        return this.b;
    }

    public final C1903kI f() {
        return this.g;
    }

    public final C3100yI g() {
        return this.f;
    }

    public final C3100yI c() {
        return this.e;
    }

    public final void b(C3100yI c3100yI) {
        this.f = c3100yI;
    }

    @Override // com.android.tools.r8.internal.HI
    public final void b() {
        this.d.add(GI.c);
    }

    @Override // com.android.tools.r8.internal.HI
    public final HI b(int i) {
        C3100yI c3100yI = new C3100yI(i);
        this.f = c3100yI;
        return c3100yI;
    }

    public final void a(C3100yI c3100yI) {
        this.e = c3100yI;
    }

    public final void a(C1903kI c1903kI) {
        this.g = c1903kI;
    }

    @Override // com.android.tools.r8.internal.HI
    public final void a(String str) {
        KB.c(str, TypeBlock.NAME_name);
        this.c = new UH(str);
    }

    @Override // com.android.tools.r8.internal.HI
    public final HI a(int i, KI ki) {
        KB.c(ki, "variance");
        C3100yI c3100yI = new C3100yI(i);
        this.d.add(new GI(ki, c3100yI));
        return c3100yI;
    }

    @Override // com.android.tools.r8.internal.HI
    public final HI a(int i) {
        C3100yI c3100yI = new C3100yI(i);
        this.e = c3100yI;
        return c3100yI;
    }

    @Override // com.android.tools.r8.internal.HI
    public final HI a(int i, String str) {
        C3100yI c3100yI = new C3100yI(i);
        this.g = new C1903kI(c3100yI, str);
        return c3100yI;
    }

    @Override // com.android.tools.r8.internal.HI
    public final CI a(C1734iI c1734iI) {
        KB.c(c1734iI, "type");
        return (CI) AbstractC0441Do.a(this.h, c1734iI);
    }
}
