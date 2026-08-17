package defpackage;

import io.vavr.Function1;
import io.vavr.Function8;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class yq5 implements Function1, Serializable {
    public final /* synthetic */ Function8 b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ yq5(Function8 function8, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        this.b = function8;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
        this.f = obj4;
        this.g = obj5;
        this.h = obj6;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function8.E3(this.b, this.c, this.d, this.e, this.f, this.g, this.h, obj);
    }
}
