package net.schmizz.concurrent;

import java.lang.Throwable;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ExceptionChainer<Z extends Throwable> {
    Z chain(Throwable th);
}
