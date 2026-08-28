package j$.util;

import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* renamed from: j$.util.z, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0230z implements LongConsumer {
    public final /* synthetic */ Consumer a;

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.a.accept(Long.valueOf(j));
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }
}
