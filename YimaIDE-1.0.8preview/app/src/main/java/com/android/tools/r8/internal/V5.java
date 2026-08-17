package com.android.tools.r8.internal;

import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface V5 {
    Set a(Object obj);

    boolean containsKey(Object obj);

    boolean containsValue(Object obj);

    void forEach(BiConsumer biConsumer);

    boolean isEmpty();
}
