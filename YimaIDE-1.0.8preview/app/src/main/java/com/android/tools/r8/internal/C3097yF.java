package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3097yF {
    public static final C3097yF c = new C3097yF(C2756uF.a, C2841vF.b);
    public final C2756uF a;
    public final C2841vF b;

    public C3097yF(C2756uF c2756uF, C2841vF c2841vF) {
        this.a = c2756uF;
        this.b = c2841vF;
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList(3);
        if (!C2756uF.a.equals(this.a)) {
            arrayList.add("context=" + this.a.a());
        }
        if (!C2841vF.b.equals(this.b)) {
            arrayList.add("description=\"" + AbstractC3035xa0.c(this.b.a) + "\"");
        }
        return "MetaInfo{" + String.join(", ", arrayList) + "}";
    }
}
