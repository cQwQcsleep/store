package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class II {
    public final II a;
    public int b;
    public final String c;
    public C3100yI d;
    public C3100yI e;
    public final ArrayList f;

    public II(int i, String str) {
        KB.c(str, TypeBlock.NAME_name);
        this.a = null;
        this.b = i;
        this.c = str;
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((FD) ((InterfaceC1654hO) it.next())).i();
        }
        this.f = arrayList;
    }

    public final void a(II ii) {
        C3100yI c3100yI = new C3100yI(c().e());
        ii.d = c3100yI;
        c().a((HI) c3100yI);
        C3100yI c3100yI2 = this.e;
        if (c3100yI2 != null) {
            C3100yI c3100yI3 = new C3100yI(c3100yI2.e());
            ii.e = c3100yI3;
            c3100yI2.a((HI) c3100yI3);
        }
        Iterator it = this.f.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
        ii.e();
    }

    public final String b() {
        return this.c;
    }

    public final C3100yI c() {
        C3100yI c3100yI = this.d;
        if (c3100yI != null) {
            return c3100yI;
        }
        KB.a("type");
        throw null;
    }

    public final C3100yI d() {
        return this.e;
    }

    public final void e() {
        II ii = this.a;
        if (ii != null) {
            ii.e();
        }
    }

    public final void b(C3100yI c3100yI) {
        this.e = c3100yI;
    }

    public final void a(C3100yI c3100yI) {
        KB.c(c3100yI, "<set-?>");
        this.d = c3100yI;
    }

    public final int a() {
        return this.b;
    }
}
