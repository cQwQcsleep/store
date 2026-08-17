package defpackage;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class bs6 implements IntConsumer {
    public final /* synthetic */ Consumer b;

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.b.accept(Integer.valueOf(i));
    }
}
