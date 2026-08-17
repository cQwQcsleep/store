package com.android.tools.r8.retrace;

import java.util.function.Consumer;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceUnknownJsonMappingInformationResult {
    default void forEach(Consumer<RetraceUnknownMappingInformationElement> consumer) {
        stream().forEach(consumer);
    }

    Stream<RetraceUnknownMappingInformationElement> stream();
}
