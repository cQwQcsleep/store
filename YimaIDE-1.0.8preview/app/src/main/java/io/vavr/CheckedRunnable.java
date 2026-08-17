package io.vavr;

import io.vavr.CheckedRunnable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CheckedRunnable {
    static /* synthetic */ void a(CheckedRunnable checkedRunnable) throws Throwable {
        checkedRunnable.getClass();
        try {
            checkedRunnable.run();
        } catch (Throwable th) {
            CheckedRunnableModule.sneakyThrow(th);
        }
    }

    static CheckedRunnable of(CheckedRunnable checkedRunnable) {
        return checkedRunnable;
    }

    void run() throws Throwable;

    default Runnable unchecked() {
        return new Runnable() { // from class: lr1
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                CheckedRunnable.a(this.b);
            }
        };
    }
}
