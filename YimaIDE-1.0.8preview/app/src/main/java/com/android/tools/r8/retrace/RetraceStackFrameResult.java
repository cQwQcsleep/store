package com.android.tools.r8.retrace;

import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceStackFrameResult<T> {
    void forEach(Consumer<T> consumer);

    T get(int i);

    List<T> getResult();

    boolean isEmpty();

    int size();
}
