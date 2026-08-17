package org.jetbrains.kotlin.js.sourceMap;

import java.io.Reader;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface SourceMapMappingConsumer {
    void addEmptyMapping();

    void addMapping(String str, Object obj, Supplier<Reader> supplier, int i, int i2, String str2);

    void newLine();
}
