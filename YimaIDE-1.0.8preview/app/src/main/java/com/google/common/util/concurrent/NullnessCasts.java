package com.google.common.util.concurrent;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class NullnessCasts {
    private NullnessCasts() {
    }

    public static <T> T uncheckedCastNullableTToT(T t) {
        return t;
    }

    public static <T> T uncheckedNull() {
        return null;
    }
}
