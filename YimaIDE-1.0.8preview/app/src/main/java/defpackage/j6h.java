package defpackage;

import com.android.tools.r8.graph.I2;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class j6h implements Predicate {
    public final /* synthetic */ Set b;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.contains((I2) obj);
    }
}
