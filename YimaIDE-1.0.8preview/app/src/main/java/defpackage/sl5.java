package defpackage;

import io.vavr.Function2;
import io.vavr.control.Try;
import java.io.Serializable;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class sl5 implements Function2, Serializable {
    public final /* synthetic */ BiFunction b;

    public /* synthetic */ sl5(BiFunction biFunction) {
        this.b = biFunction;
    }

    @Override // io.vavr.Function2, java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Try.of(new kl5(this.b, obj, obj2)).toOption();
    }
}
