package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class c implements DefinitionContext {
    public final Origin a;

    public c(Origin origin) {
        this.a = origin;
    }

    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    public final Origin getOrigin() {
        return this.a;
    }
}
