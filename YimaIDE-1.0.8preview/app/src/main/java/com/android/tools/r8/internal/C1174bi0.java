package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bi0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1174bi0 extends AbstractC1088ai0 {
    public C1174bi0(C0322w2 c0322w2) {
        super(c0322w2);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1174bi0) {
            return this.a.a(((C1174bi0) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "MethodRet(" + b().w0().G0() + "#" + b().g + ")";
    }
}
