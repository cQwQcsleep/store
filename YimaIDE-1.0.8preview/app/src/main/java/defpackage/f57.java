package defpackage;

import io.vavr.PartialFunction;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class f57 implements Predicate {
    public final /* synthetic */ PartialFunction b;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.isDefinedAt(obj);
    }
}
