package defpackage;

import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class uh9 implements LongConsumer {
    public final /* synthetic */ Consumer b;

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.b.accept(Long.valueOf(j));
    }
}
