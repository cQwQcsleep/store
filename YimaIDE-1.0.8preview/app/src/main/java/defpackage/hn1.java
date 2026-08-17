package defpackage;

import io.vavr.CheckedFunction5;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class hn1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction5 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ hn1(CheckedFunction5 checkedFunction5, Object obj) {
        this.b = checkedFunction5;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction5.e(this.b, this.c, obj);
    }
}
