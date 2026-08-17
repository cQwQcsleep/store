package com.android.tools.r8.internal;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pf0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2361pf0 {
    public final Map a;
    public final InterfaceC2702tf0 b;
    public final Set c;
    public final Set d;
    public final Set e;
    public final Map f;
    public final Map g;
    public final IdentityHashMap h = new IdentityHashMap();
    public final Supplier i;

    public C2361pf0(IdentityHashMap identityHashMap, Set set, Set set2, Set set3, IdentityHashMap identityHashMap2, IdentityHashMap identityHashMap3, InterfaceC2702tf0 interfaceC2702tf0, Supplier supplier) {
        this.a = identityHashMap;
        this.c = set;
        this.d = set2;
        this.e = set3;
        this.f = identityHashMap2;
        this.g = identityHashMap3;
        this.b = interfaceC2702tf0;
        this.i = supplier;
    }

    public final C2543rl0 a() {
        return (C2543rl0) this.i.get();
    }
}
