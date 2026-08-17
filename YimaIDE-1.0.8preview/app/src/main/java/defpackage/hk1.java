package defpackage;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class hk1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction1 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ hk1(CheckedFunction1 checkedFunction1, Function function) {
        this.b = checkedFunction1;
        this.c = function;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction1.X7(this.b, this.c, obj);
    }
}
