package j$.util;

import java.util.function.DoubleConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface H extends Q {
    void forEachRemaining(DoubleConsumer doubleConsumer);

    boolean tryAdvance(DoubleConsumer doubleConsumer);

    @Override // j$.util.Q, j$.util.U
    H trySplit();
}
