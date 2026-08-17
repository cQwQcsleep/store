package com.android.tools.r8.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1271cs {
    public final C0955Xj a;
    public final Method b;
    public final Method c;
    public final C1856jk d;

    public C1271cs(C0955Xj c0955Xj, int i, String str, Class cls, Class cls2) {
        this.a = c0955Xj;
        C2198nk c2198nk = (C2198nk) Collections.unmodifiableList(Arrays.asList(c0955Xj.j)).get(i);
        if (c2198nk.f()) {
            this.b = null;
            this.c = null;
            this.d = (C1856jk) Collections.unmodifiableList(Arrays.asList(c2198nk.h)).get(0);
        } else {
            this.b = AbstractC2209ns.a(cls, C40.a("get", str, "Case"), new Class[0]);
            this.c = AbstractC2209ns.a(cls2, C40.a("get", str, "Case"), new Class[0]);
            this.d = null;
        }
        AbstractC2209ns.a(cls2, F40.a("clear", str), new Class[0]);
    }
}
