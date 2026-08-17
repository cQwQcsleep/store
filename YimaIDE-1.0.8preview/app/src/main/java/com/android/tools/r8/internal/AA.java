package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AA extends AbstractC1299dA implements InterfaceC3177zA, Serializable, Cloneable {
    public Object clone() {
        return BA.a;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        return (obj instanceof Set) && ((Set) obj).isEmpty();
    }
}
