package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2895vu extends AbstractC0706Nu implements R5 {
    @Override // com.android.tools.r8.internal.R5
    public final Object a(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC3066xu k() {
        throw new AssertionError("should never be called");
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    /* JADX INFO: renamed from: r */
    public final AbstractC3066xu values() {
        return f().keySet();
    }

    @Override // com.android.tools.r8.internal.R5
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public abstract AbstractC2895vu f();

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Collection values() {
        return f().keySet();
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Set values() {
        return f().keySet();
    }
}
