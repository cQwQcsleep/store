package defpackage;

import io.vavr.collection.CharSeq;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class mg1 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return CharSeq.ofAll((Iterable) obj);
    }
}
