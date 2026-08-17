package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RH extends AbstractC1223cI implements InterfaceC1139bI {
    public int a;
    public String b;
    public String i;
    public String m;
    public C3100yI n;
    public final ArrayList q;
    public final ArrayList c = new ArrayList(0);
    public final ArrayList d = new ArrayList(1);
    public final ArrayList e = new ArrayList();
    public final ArrayList f = new ArrayList();
    public final ArrayList g = new ArrayList(0);
    public final ArrayList h = new ArrayList(1);
    public final ArrayList j = new ArrayList(0);
    public final ArrayList k = new ArrayList(0);
    public final ArrayList l = new ArrayList(0);
    public final ArrayList o = new ArrayList(0);
    public final ArrayList p = new ArrayList(0);

    public RH() {
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FD) ((InterfaceC1654hO) it.next())).a());
        }
        this.q = arrayList;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final void b(String str) {
        KB.c(str, "<set-?>");
        this.b = str;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1139bI
    public final List c() {
        return this.e;
    }

    public final String d() {
        return this.i;
    }

    public final List<XH> e() {
        return this.h;
    }

    public final ArrayList f() {
        return this.o;
    }

    public final List<String> g() {
        return this.k;
    }

    public final int h() {
        return this.a;
    }

    public final String i() {
        return this.m;
    }

    public final C3100yI j() {
        return this.n;
    }

    public final String k() {
        String str = this.b;
        if (str != null) {
            return str;
        }
        KB.a(TypeBlock.NAME_name);
        throw null;
    }

    public final List<String> l() {
        return this.j;
    }

    public final List<String> m() {
        return this.l;
    }

    public final List<C3100yI> n() {
        return this.d;
    }

    public final List<DI> o() {
        return this.c;
    }

    public final ArrayList p() {
        return this.p;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1139bI
    public final List a() {
        return this.f;
    }

    public final void a(String str) {
        this.m = str;
    }

    public final void a(C3100yI c3100yI) {
        this.n = c3100yI;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1139bI
    public final List b() {
        return this.g;
    }
}
