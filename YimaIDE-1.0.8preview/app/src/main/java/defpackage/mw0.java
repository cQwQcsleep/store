package defpackage;

import io.vavr.Function1;
import io.vavr.collection.BitSet;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class mw0 implements Function1, Serializable {
    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return BitSet.Builder.b((Integer) obj);
    }
}
