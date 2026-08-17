package defpackage;

import io.vavr.Function1;
import io.vavr.Function5;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class dn5 implements Function1, Serializable {
    public final /* synthetic */ Function5 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ dn5(Function5 function5, Object obj, Object obj2, Object obj3, Object obj4) {
        this.b = function5;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
        this.f = obj4;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.apply(this.c, this.d, this.e, this.f, obj);
    }
}
