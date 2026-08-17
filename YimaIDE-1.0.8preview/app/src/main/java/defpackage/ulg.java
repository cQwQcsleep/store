package defpackage;

import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class ulg implements Predicate {
    public final /* synthetic */ Set b;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.contains((String) obj);
    }
}
