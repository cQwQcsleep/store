package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import java.util.Collection;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class i {
    public final C0473Eu a = AbstractC0551Hu.g();

    public abstract i a();

    public final i a(Collection collection) {
        collection.forEach(new Consumer() { // from class: y4h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((DefinitionContext) obj);
            }
        });
        return a();
    }

    public final i a(DefinitionContext definitionContext) {
        this.a.a(definitionContext);
        return a();
    }
}
