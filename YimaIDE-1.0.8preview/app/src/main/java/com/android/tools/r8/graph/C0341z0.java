package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2554rv;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.graph.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0341z0 {
    public final boolean a;
    public final boolean b;
    public final Set c;
    public final Set d;
    public final Set e;

    public C0341z0(boolean z, boolean z2, AbstractC2554rv abstractC2554rv, AbstractC2554rv abstractC2554rv2, AbstractC2554rv abstractC2554rv3) {
        this.a = z;
        this.b = z2;
        this.c = abstractC2554rv;
        this.d = abstractC2554rv2;
        this.e = abstractC2554rv3;
    }

    public final Set a() {
        return this.e;
    }

    public final Set b() {
        return this.d;
    }

    public final boolean c() {
        return !this.e.isEmpty();
    }

    public final boolean d() {
        return this.b;
    }

    public final boolean e() {
        return this.a;
    }

    public final boolean f() {
        return !this.d.isEmpty();
    }
}
