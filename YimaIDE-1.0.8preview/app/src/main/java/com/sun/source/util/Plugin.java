package com.sun.source.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Plugin {
    default boolean autoStart() {
        return false;
    }

    String getName();

    void init(JavacTask javacTask, String... strArr);
}
