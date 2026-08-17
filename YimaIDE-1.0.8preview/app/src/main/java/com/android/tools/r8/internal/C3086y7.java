package com.android.tools.r8.internal;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3086y7 {
    public final C0749Pl a = new C0749Pl(new HashMap());

    public final boolean a(C3086y7 c3086y7) {
        if (this.a.b.size() > c3086y7.a.b.size()) {
            return false;
        }
        for (Map.Entry entry : this.a.b.entrySet()) {
            if (!((com.android.tools.r8.graph.B2) entry.getValue()).equals(c3086y7.a.b.get((com.android.tools.r8.graph.B2) entry.getKey()))) {
                return false;
            }
        }
        return true;
    }
}
