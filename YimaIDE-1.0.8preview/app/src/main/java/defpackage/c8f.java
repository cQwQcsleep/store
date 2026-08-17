package defpackage;

import io.vavr.collection.Stream;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class c8f implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Stream.ofAll((Iterable) obj);
    }
}
