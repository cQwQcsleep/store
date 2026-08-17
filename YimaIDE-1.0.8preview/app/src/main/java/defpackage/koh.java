package defpackage;

import com.android.tools.r8.graph.C0245l1;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class koh implements Predicate {
    public final /* synthetic */ Set b;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.add((C0245l1) obj);
    }
}
