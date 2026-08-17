package defpackage;

import it.unimi.dsi.fastutil.bytes.ByteConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class w31 implements ByteConsumer {
    public final /* synthetic */ Consumer b;

    public /* synthetic */ w31(Consumer consumer) {
        this.b = consumer;
    }

    @Override // it.unimi.dsi.fastutil.bytes.ByteConsumer
    public final void accept(byte b) {
        this.b.accept(Byte.valueOf(b));
    }
}
