package defpackage;

import io.vavr.collection.LinkedHashSet;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class t8f implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return LinkedHashSet.ofAll((Iterable) obj);
    }
}
