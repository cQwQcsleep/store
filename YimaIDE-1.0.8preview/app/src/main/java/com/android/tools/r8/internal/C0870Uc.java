package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Uc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0870Uc {
    public static final C0870Uc b = new C0870Uc(Collections.EMPTY_SET);
    public final Set a;

    public C0870Uc(Set set) {
        this.a = Collections.unmodifiableSet(set);
    }

    public final C0870Uc a(C0870Uc c0870Uc) {
        if (this.a.isEmpty()) {
            return c0870Uc;
        }
        if (!c0870Uc.a.isEmpty()) {
            HashSet hashSet = new HashSet(this.a);
            hashSet.addAll(c0870Uc.a);
            if (this.a.size() != hashSet.size()) {
                return new C0870Uc(hashSet);
            }
        }
        return this;
    }
}
