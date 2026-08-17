package defpackage;

import io.vavr.collection.List;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ac9 implements BiFunction {
    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return ((List) obj).prepend(obj2);
    }
}
