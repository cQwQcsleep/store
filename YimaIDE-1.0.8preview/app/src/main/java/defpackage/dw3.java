package defpackage;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class dw3 implements DoubleConsumer {
    public final /* synthetic */ Consumer b;

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.b.accept(Double.valueOf(d));
    }
}
