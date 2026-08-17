package defpackage;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.internal.PY;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class jo9 implements BiConsumer {
    public final /* synthetic */ PY a;

    public /* synthetic */ jo9(PY py) {
        this.a = py;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        this.a.b((B5) obj, (B5) obj2);
    }
}
