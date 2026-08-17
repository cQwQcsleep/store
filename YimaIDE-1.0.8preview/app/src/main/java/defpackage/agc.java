package defpackage;

import com.intellij.util.ThrowableRunnable;
import com.intellij.util.io.PagedFileStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final /* synthetic */ class agc implements ThrowableRunnable {
    public final /* synthetic */ PagedFileStorage a;

    public /* synthetic */ agc(PagedFileStorage pagedFileStorage) {
        this.a = pagedFileStorage;
    }

    @Override // com.intellij.util.ThrowableRunnable
    public final void run() throws Exception {
        this.a.close();
    }
}
