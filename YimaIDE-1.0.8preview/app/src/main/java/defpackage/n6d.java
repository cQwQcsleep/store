package defpackage;

import io.vavr.Function1;
import io.vavr.collection.Seq;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class n6d implements Function1, Serializable {
    public final /* synthetic */ Seq b;
    public final /* synthetic */ Object c;

    public /* synthetic */ n6d(Seq seq, Object obj) {
        this.b = seq;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Seq.kc(this.b, this.c, (Integer) obj);
    }
}
