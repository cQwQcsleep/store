package defpackage;

import com.android.tools.r8.graph.D2;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class sjg implements Consumer {
    public final /* synthetic */ Set b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((D2) obj);
    }
}
