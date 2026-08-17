package defpackage;

import io.vavr.CheckedFunction8;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class br1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction8 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ br1(CheckedFunction8 checkedFunction8, Object obj, Object obj2, Object obj3) {
        this.b = checkedFunction8;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction8.s(this.b, this.c, this.d, this.e, obj);
    }
}
