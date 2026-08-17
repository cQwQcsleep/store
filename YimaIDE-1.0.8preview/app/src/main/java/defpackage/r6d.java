package defpackage;

import io.vavr.Function1;
import io.vavr.collection.Seq;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class r6d implements Function1, Serializable {
    public final /* synthetic */ Seq b;
    public final /* synthetic */ Function c;

    public /* synthetic */ r6d(Seq seq, Function function) {
        this.b = seq;
        this.c = function;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Seq.Aa(this.b, this.c, (Integer) obj);
    }
}
