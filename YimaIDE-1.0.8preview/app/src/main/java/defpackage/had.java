package defpackage;

import it.unimi.dsi.fastutil.shorts.ShortConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class had implements ShortConsumer {
    public final /* synthetic */ Consumer b;

    @Override // it.unimi.dsi.fastutil.shorts.ShortConsumer
    public final void accept(short s) {
        this.b.accept(Short.valueOf(s));
    }
}
