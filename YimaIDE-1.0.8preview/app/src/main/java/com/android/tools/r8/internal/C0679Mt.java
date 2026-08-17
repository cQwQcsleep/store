package com.android.tools.r8.internal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0679Mt {
    public static final /* synthetic */ boolean d = true;
    public final LinkedHashSet a;
    public final Set b;
    public final Deque c;

    public C0679Mt(LinkedHashSet linkedHashSet, Set set, ArrayDeque arrayDeque) {
        if (!d && !linkedHashSet.containsAll(set)) {
            x1f.a();
            throw null;
        }
        this.a = linkedHashSet;
        this.b = set;
        this.c = arrayDeque;
    }

    public final boolean a() {
        return this.a.isEmpty() && this.b.isEmpty();
    }

    public final boolean equals(Object obj) {
        C0679Mt c0679Mt = (C0679Mt) obj;
        return this.a.equals(c0679Mt.a) && this.b.equals(c0679Mt.b);
    }

    public final int hashCode() {
        throw new Kk0();
    }
}
