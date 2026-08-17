package defpackage;

import io.vavr.CheckedFunction4;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class qm1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction4 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ qm1(CheckedFunction4 checkedFunction4, Object obj) {
        this.b = checkedFunction4;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction4.l3(this.b, this.c, obj);
    }
}
