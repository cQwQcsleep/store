package defpackage;

import com.intellij.util.ThrowableRunnable;
import com.intellij.util.io.ResizeableMappedFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final /* synthetic */ class u0b implements ThrowableRunnable {
    public final /* synthetic */ ResizeableMappedFile a;

    @Override // com.intellij.util.ThrowableRunnable
    public final void run() throws Exception {
        this.a.close();
    }
}
