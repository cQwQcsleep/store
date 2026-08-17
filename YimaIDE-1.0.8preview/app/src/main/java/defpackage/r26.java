package defpackage;

import com.android.tools.r8.graph.C0231j1;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class r26 implements Consumer {
    public final /* synthetic */ List b;

    public /* synthetic */ r26(List list) {
        this.b = list;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((C0231j1) obj);
    }
}
