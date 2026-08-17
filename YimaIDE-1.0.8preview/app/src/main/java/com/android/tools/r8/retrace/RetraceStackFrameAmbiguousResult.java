package com.android.tools.r8.retrace;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceStackFrameAmbiguousResult<T> {
    void forEach(Consumer<RetraceStackFrameResult<T>> consumer);

    void forEachWithIndex(BiConsumer<RetraceStackFrameResult<T>, Integer> biConsumer);

    RetraceStackFrameResult<T> get(int i);

    List<RetraceStackFrameResult<T>> getAmbiguousResult();

    boolean isAmbiguous();

    boolean isEmpty();

    int size();
}
