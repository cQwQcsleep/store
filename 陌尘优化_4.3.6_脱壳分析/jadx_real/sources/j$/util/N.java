package j$.util;

import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface N extends Q {
    void forEachRemaining(LongConsumer longConsumer);

    boolean tryAdvance(LongConsumer longConsumer);

    @Override // j$.util.Q, j$.util.U
    N trySplit();
}
