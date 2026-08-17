package defpackage;

import io.vavr.CheckedFunction6;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class lo1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction6 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ lo1(CheckedFunction6 checkedFunction6, Object obj) {
        this.b = checkedFunction6;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction6.Q0(this.b, this.c, obj);
    }
}
