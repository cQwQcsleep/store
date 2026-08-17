package io.vavr;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class j implements Function0, Memoized, Serializable {
    public final /* synthetic */ Lazy b;

    public /* synthetic */ j(Lazy lazy) {
        this.b = lazy;
    }

    @Override // io.vavr.Function0
    public final Object apply() {
        return this.b.get();
    }
}
