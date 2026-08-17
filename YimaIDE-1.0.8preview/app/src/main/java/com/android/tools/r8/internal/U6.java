package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U6 extends G6 implements Set, Serializable, Cloneable {
    public final Object clone() {
        return W6.a;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        return (obj instanceof Set) && ((Set) obj).isEmpty();
    }
}
