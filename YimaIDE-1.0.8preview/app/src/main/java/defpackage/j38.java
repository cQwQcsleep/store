package defpackage;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.shaking.K3;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class j38 implements Predicate {
    public final /* synthetic */ K3 b;

    public /* synthetic */ j38(K3 k3) {
        this.b = k3;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.b((I2) obj);
    }
}
