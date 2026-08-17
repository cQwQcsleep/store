package defpackage;

import io.vavr.Function1;
import io.vavr.control.Try;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class zk5 implements Function1, Serializable {
    public final /* synthetic */ Function b;

    public /* synthetic */ zk5(Function function) {
        this.b = function;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Try.of(new dl5(this.b, obj)).toOption();
    }
}
