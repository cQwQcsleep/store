package com.android.tools.r8.internal;

import com.reandroid.arsc.container.SpecTypePair;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q10 {
    public final InterfaceC2425qQ a;
    public final Ej0 b;
    public final Sl0 c;
    public final boolean d;
    public final Q10 e;
    public final List f;
    public final LinkedHashMap g;
    public final List h;

    public Q10(InterfaceC2425qQ interfaceC2425qQ, Ej0 ej0, Sl0 sl0, boolean z, Q10 q10, List list) {
        KB.c(interfaceC2425qQ, "strings");
        KB.c(ej0, SpecTypePair.NAME_types);
        KB.c(sl0, "versionRequirements");
        KB.c(list, "contextExtensions");
        this.a = interfaceC2425qQ;
        this.b = ej0;
        this.c = sl0;
        this.d = z;
        this.e = q10;
        this.f = list;
        this.g = new LinkedHashMap();
        InterfaceC1654hO.a.getClass();
        this.h = (List) C1568gO.b.a();
    }

    public final Q10 a(List list) {
        KB.c(list, "typeParameters");
        Q10 q10 = new Q10(this.a, this.b, this.c, this.d, this, this.f);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            D00 d00 = (D00) it.next();
            q10.g.put(Integer.valueOf(d00.f), Integer.valueOf(d00.e));
        }
        return q10;
    }

    public final Integer a(int i) {
        Integer num = (Integer) this.g.get(Integer.valueOf(i));
        if (num != null) {
            return num;
        }
        Q10 q10 = this.e;
        if (q10 != null) {
            return q10.a(i);
        }
        return null;
    }

    public /* synthetic */ Q10(ND nd, Ej0 ej0, Sl0 sl0, boolean z, List list, int i) {
        this(nd, ej0, sl0, z, (Q10) null, (i & 32) != 0 ? C0984Ym.b : list);
    }
}
