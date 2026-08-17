package org.snakeyaml.engine.v2.api;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface StreamDataWriter {
    default void flush() {
    }

    void write(String str);

    void write(String str, int i, int i2);
}
