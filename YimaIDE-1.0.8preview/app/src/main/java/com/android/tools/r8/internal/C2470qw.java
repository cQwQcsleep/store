package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import defpackage.q4i;
import java.util.TreeMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2470qw {
    public static final /* synthetic */ boolean b = true;
    public final TreeMap a = new TreeMap(new q4i());

    public final C2470qw a(C0245l1 c0245l1, InterfaceC2385pw interfaceC2385pw) {
        if (!b && this.a.containsKey(c0245l1)) {
            x1f.a();
            return null;
        }
        if (!interfaceC2385pw.isUnknown()) {
            this.a.put(c0245l1, interfaceC2385pw);
        }
        return this;
    }
}
