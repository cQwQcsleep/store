package defpackage;

import it.unimi.dsi.fastutil.chars.CharConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class zf1 implements CharConsumer {
    public final /* synthetic */ Consumer b;

    public /* synthetic */ zf1(Consumer consumer) {
        this.b = consumer;
    }

    @Override // it.unimi.dsi.fastutil.chars.CharConsumer
    public final void accept(char c) {
        this.b.accept(Character.valueOf(c));
    }
}
