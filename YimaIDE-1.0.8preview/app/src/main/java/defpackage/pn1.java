package defpackage;

import io.vavr.CheckedFunction5;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class pn1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction5 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ pn1(CheckedFunction5 checkedFunction5, Object obj, Object obj2) {
        this.b = checkedFunction5;
        this.c = obj;
        this.d = obj2;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction5.y9(this.b, this.c, this.d, obj);
    }
}
