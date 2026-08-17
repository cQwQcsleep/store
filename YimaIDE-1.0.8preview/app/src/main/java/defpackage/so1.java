package defpackage;

import io.vavr.CheckedFunction6;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class so1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction6 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ so1(CheckedFunction6 checkedFunction6, Object obj, Object obj2, Object obj3) {
        this.b = checkedFunction6;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction6.N3(this.b, this.c, this.d, this.e, obj);
    }
}
