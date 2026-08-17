package defpackage;

import io.vavr.CheckedFunction8;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class nq1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction8 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ nq1(CheckedFunction8 checkedFunction8, Object obj, Object obj2, Object obj3, Object obj4) {
        this.b = checkedFunction8;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
        this.f = obj4;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction8.R6(this.b, this.c, this.d, this.e, this.f, obj);
    }
}
