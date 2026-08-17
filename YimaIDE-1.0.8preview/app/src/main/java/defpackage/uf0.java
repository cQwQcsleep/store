package defpackage;

import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class uf0 implements Predicate {
    public final /* synthetic */ Set b;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.add(obj);
    }
}
