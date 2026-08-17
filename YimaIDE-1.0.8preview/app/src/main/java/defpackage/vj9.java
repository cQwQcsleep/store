package defpackage;

import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.InterfaceC0339y5;
import com.android.tools.r8.shaking.M;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class vj9 implements BiConsumer {
    public final /* synthetic */ M a;

    public /* synthetic */ vj9(M m) {
        this.a = m;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        this.a.b((E0) obj, (InterfaceC0339y5) obj2);
    }
}
