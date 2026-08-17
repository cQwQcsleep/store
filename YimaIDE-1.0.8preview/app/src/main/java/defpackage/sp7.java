package defpackage;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.Kl0;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class sp7 implements Consumer {
    public final /* synthetic */ Kl0 b;

    public /* synthetic */ sp7(Kl0 kl0) {
        this.b = kl0;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.b((D2) obj);
    }
}
