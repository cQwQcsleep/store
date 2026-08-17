package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3185zI {
    public int a;
    public final String b;
    public final ArrayList c;
    public C3100yI d;
    public C3100yI e;
    public final ArrayList f;
    public final ArrayList g;

    public C3185zI(int i, String str) {
        KB.c(str, TypeBlock.NAME_name);
        this.a = i;
        this.b = str;
        this.c = new ArrayList(0);
        this.f = new ArrayList(0);
        this.g = new ArrayList(0);
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((FD) ((InterfaceC1654hO) it.next())).f();
        }
    }

    public final void a(C3100yI c3100yI) {
        KB.c(c3100yI, "<set-?>");
        this.e = c3100yI;
    }

    public final void b(C3100yI c3100yI) {
        KB.c(c3100yI, "<set-?>");
        this.d = c3100yI;
    }

    public final String c() {
        return this.b;
    }

    public final List<DI> d() {
        return this.c;
    }

    public final ArrayList e() {
        return this.g;
    }

    public final List<C3015xH> a() {
        return this.f;
    }

    public final int b() {
        return this.a;
    }
}
