package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.I2;
import java.util.Collection;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface A {
    void a(I2 i2, Collection collection);

    void forEach(BiConsumer biConsumer);

    boolean isEmpty();
}
