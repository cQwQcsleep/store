package defpackage;

import io.vavr.Function0;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class uk5 implements Function0, Serializable {
    public final /* synthetic */ Function0 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ uk5(Function0 function0, Function function) {
        this.b = function0;
        this.c = function;
    }

    @Override // io.vavr.Function0
    public final Object apply() {
        return this.c.apply(this.b.apply());
    }
}
