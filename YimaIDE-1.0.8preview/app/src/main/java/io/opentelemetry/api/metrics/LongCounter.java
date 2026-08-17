package io.opentelemetry.api.metrics;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.context.Context;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LongCounter {
    void add(long j);

    void add(long j, Attributes attributes);

    void add(long j, Attributes attributes, Context context);
}
