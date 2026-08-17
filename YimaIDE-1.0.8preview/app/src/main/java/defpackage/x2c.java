package defpackage;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.shaking.P1;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class x2c implements Consumer {
    public final /* synthetic */ P1 b;

    public /* synthetic */ x2c(P1 p1) {
        this.b = p1;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.a((I2) obj);
    }
}
