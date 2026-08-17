package defpackage;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class k9f implements Consumer {
    public final /* synthetic */ CompletableFuture b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.completeExceptionally((Throwable) obj);
    }
}
