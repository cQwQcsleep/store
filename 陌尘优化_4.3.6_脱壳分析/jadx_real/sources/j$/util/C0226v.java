package j$.util;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* renamed from: j$.util.v, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0226v implements IntConsumer {
    public final /* synthetic */ Consumer a;

    public /* synthetic */ C0226v(Consumer consumer) {
        this.a = consumer;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.a.accept(Integer.valueOf(i));
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }
}
