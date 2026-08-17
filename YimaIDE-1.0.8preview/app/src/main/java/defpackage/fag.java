package defpackage;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.internal.UY;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class fag implements Consumer {
    public final /* synthetic */ UY b;

    public /* synthetic */ fag(UY uy) {
        this.b = uy;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((B5) obj);
    }
}
