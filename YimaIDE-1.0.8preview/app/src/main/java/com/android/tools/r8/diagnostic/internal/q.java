package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.MissingMethodInfo;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class q extends j implements MissingMethodInfo {
    public final MethodReference b;

    public q(MethodReference methodReference, AbstractC0551Hu abstractC0551Hu) {
        super(abstractC0551Hu);
        this.b = methodReference;
    }

    @Override // com.android.tools.r8.diagnostic.MissingMethodInfo
    public final MethodReference getMethodReference() {
        return this.b;
    }
}
