package defpackage;

import io.vavr.CheckedFunction7;
import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class cp1 implements Function1, Serializable {
    public final /* synthetic */ CheckedFunction7 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ cp1(CheckedFunction7 checkedFunction7, Object obj, Object obj2, Object obj3, Object obj4) {
        this.b = checkedFunction7;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
        this.f = obj4;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return CheckedFunction7.Uc(this.b, this.c, this.d, this.e, this.f, obj);
    }
}
