package defpackage;

import io.vavr.collection.Queue;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class g8f implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Queue.ofAll((Iterable) obj);
    }
}
