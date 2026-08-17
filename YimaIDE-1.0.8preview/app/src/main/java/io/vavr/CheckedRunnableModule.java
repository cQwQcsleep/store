package io.vavr;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface CheckedRunnableModule {
    static <T extends Throwable, R> R sneakyThrow(Throwable th) throws Throwable {
        throw th;
    }
}
