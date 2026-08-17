package defpackage;

import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.kotlin.C3296n;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class z1i implements Consumer {
    public final /* synthetic */ C3296n b;

    public /* synthetic */ z1i(C3296n c3296n) {
        this.b = c3296n;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.a((InterfaceC0189d1) obj);
    }
}
