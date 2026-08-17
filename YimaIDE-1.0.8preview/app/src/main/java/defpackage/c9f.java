package defpackage;

import io.vavr.collection.HashSet;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class c9f implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return HashSet.ofAll((Iterable) obj);
    }
}
