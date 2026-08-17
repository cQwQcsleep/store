package com.google.common.util.concurrent;

import java.lang.Throwable;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class SneakyThrows<T extends Throwable> {
    private SneakyThrows() {
    }

    public static Error sneakyThrow(Throwable th) {
        throw new SneakyThrows().throwIt(th);
    }

    private Error throwIt(Throwable th) throws Throwable {
        throw th;
    }
}
