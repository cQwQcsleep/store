package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0397Bw {
    public final Map a;
    public final Map b;
    public final C0322w2 c;
    public final List d;
    public final com.android.tools.r8.graph.K2 e;

    public C0397Bw(LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, C0322w2 c0322w2, ArrayList arrayList, com.android.tools.r8.graph.K2 k2) {
        this.a = linkedHashMap;
        this.b = linkedHashMap2;
        this.c = c0322w2;
        this.d = arrayList;
        this.e = k2;
    }

    public final boolean equals(Object obj) {
        if (obj != null && C0397Bw.class == obj.getClass()) {
            C0397Bw c0397Bw = (C0397Bw) obj;
            if (this.a.equals(c0397Bw.a) && this.b.equals(c0397Bw.b) && this.c == c0397Bw.c && this.d.equals(c0397Bw.d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b, this.c, this.d, this.e);
    }
}
