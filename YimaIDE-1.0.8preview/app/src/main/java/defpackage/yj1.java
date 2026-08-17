package defpackage;

import io.vavr.CheckedFunction0;
import io.vavr.Function0;
import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class yj1 implements Function0, Serializable {
    public final /* synthetic */ CheckedFunction0 b;
    public final /* synthetic */ Function c;

    public /* synthetic */ yj1(CheckedFunction0 checkedFunction0, Function function) {
        this.b = checkedFunction0;
        this.c = function;
    }

    @Override // io.vavr.Function0
    public final Object apply() {
        return CheckedFunction0.Ob(this.b, this.c);
    }
}
