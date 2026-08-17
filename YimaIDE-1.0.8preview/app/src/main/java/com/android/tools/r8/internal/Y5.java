package com.android.tools.r8.internal;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface Y5 extends V5 {
    void a(BiConsumer biConsumer);

    Map e();

    Object get(Object obj);

    Object getOrDefault(Object obj, Object obj2);

    Set keySet();

    /* JADX INFO: renamed from: values */
    Set mo15values();
}
