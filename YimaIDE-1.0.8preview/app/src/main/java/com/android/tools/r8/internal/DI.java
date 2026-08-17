package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DI {
    public final DI a;
    public int b;
    public final String c;
    public final int d;
    public final KI e;
    public final ArrayList f;
    public final ArrayList g;

    public DI(int i, String str, int i2, KI ki) {
        KB.c(str, TypeBlock.NAME_name);
        KB.c(ki, "variance");
        this.a = null;
        this.b = i;
        this.c = str;
        this.d = i2;
        this.e = ki;
        this.f = new ArrayList(1);
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FD) ((InterfaceC1654hO) it.next())).h());
        }
        this.g = arrayList;
    }

    public final void a(DI di) {
        for (C3100yI c3100yI : this.f) {
            C3100yI c3100yI2 = new C3100yI(c3100yI.e());
            ArrayList arrayList = di.f;
            KB.c(arrayList, "collection");
            arrayList.add(c3100yI2);
            c3100yI.a((HI) c3100yI2);
        }
        for (FI fi : this.g) {
            ((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o) fi).getClass();
            C1734iI c1734iI = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o.b;
            KB.c(c1734iI, "type");
            FI fi2 = (FI) AbstractC0441Do.a(di.g, c1734iI);
            C1985lE c1985lE = (C1985lE) fi;
            c1985lE.getClass();
            if (!(fi2 instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o)) {
                w01.a("Failed requirement.");
                return;
            }
            com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o oVar = (com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o) fi2;
            for (C3015xH c3015xH : c1985lE.c) {
                KB.c(c3015xH, "annotation");
                ((C1985lE) oVar).c.add(c3015xH);
            }
            oVar.a();
        }
        di.f();
    }

    public final int b() {
        return this.d;
    }

    public final String c() {
        return this.c;
    }

    public final List<C3100yI> d() {
        return this.f;
    }

    public final KI e() {
        return this.e;
    }

    public final void f() {
        DI di = this.a;
        if (di != null) {
            di.f();
        }
    }

    public final int a() {
        return this.b;
    }
}
