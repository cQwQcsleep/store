package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0543Hm {
    public final AbstractC3122yc0 a;
    public final C2543rl0 b;
    public final Set c;

    public C0543Hm(C2543rl0 c2543rl0, Set set) {
        this.a = null;
        this.b = c2543rl0;
        this.c = set;
    }

    public final Set a() {
        return this.c;
    }

    public final AbstractC3122yc0 b() {
        return this.a;
    }

    public final C2543rl0 c() {
        return this.b;
    }

    public final boolean d() {
        return this.a != null;
    }

    public final boolean e() {
        return this.b != null;
    }

    public C0543Hm(Set set) {
        this.a = null;
        this.b = null;
        this.c = set;
    }

    public C0543Hm(Set set, AbstractC3122yc0 abstractC3122yc0) {
        this.a = abstractC3122yc0;
        this.b = null;
        this.c = set;
    }
}
