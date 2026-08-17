package defpackage;

import io.vavr.CheckedFunction7;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class hp1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction7 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ hp1(CheckedFunction7 checkedFunction7, Object obj) {
        this.b = checkedFunction7;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction7.a(this.b, this.c, obj);
    }
}
