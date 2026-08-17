package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.diagnostic.internal.b;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
abstract class b<B extends b<B>> {
    public static final /* synthetic */ boolean b = true;
    public Origin a;

    public abstract DefinitionContext a();

    public b a(Origin origin) {
        this.a = origin;
        return b();
    }

    public abstract b b();
}
