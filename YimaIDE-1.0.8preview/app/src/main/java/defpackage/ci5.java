package defpackage;

import it.unimi.dsi.fastutil.floats.FloatConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ci5 implements FloatConsumer {
    public final /* synthetic */ Consumer b;

    @Override // it.unimi.dsi.fastutil.floats.FloatConsumer
    public final void accept(float f) {
        this.b.accept(Float.valueOf(f));
    }
}
