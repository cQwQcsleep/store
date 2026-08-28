package j$.util;

import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface U {
    int characteristics();

    long estimateSize();

    void forEachRemaining(Consumer consumer);

    java.util.Comparator getComparator();

    long getExactSizeIfKnown();

    boolean hasCharacteristics(int i);

    boolean tryAdvance(Consumer consumer);

    U trySplit();
}
