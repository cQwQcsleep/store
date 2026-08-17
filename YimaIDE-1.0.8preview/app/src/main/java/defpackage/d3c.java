package defpackage;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.shaking.C3403i;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class d3c implements Predicate {
    public final /* synthetic */ C3403i b;

    public /* synthetic */ d3c(C3403i c3403i) {
        this.b = c3403i;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.l((I2) obj);
    }
}
