package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3192zP {
    public final Origin a;
    public final C0394Bt b;
    public final Map c;
    public final Map d;
    public final Map e;

    public C3192zP(Origin origin, C0394Bt c0394Bt, HashMap map, HashMap map2, HashMap map3) {
        this.a = origin;
        this.b = c0394Bt;
        this.c = map;
        this.d = map2;
        this.e = map3;
    }

    public Map<O2, C2552rt> a() {
        return this.c;
    }

    public Map<O2, C2552rt> b() {
        return this.d;
    }

    public Map<O2, C2552rt> c() {
        return this.e;
    }

    public C0394Bt d() {
        return this.b;
    }
}
