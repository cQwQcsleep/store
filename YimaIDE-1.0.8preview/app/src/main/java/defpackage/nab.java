package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final /* synthetic */ class nab implements Executor {
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
