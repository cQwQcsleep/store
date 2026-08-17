package defpackage;

import io.vavr.Function5;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class pn5 implements Function5, Serializable {
    public final /* synthetic */ Function5 b;

    public /* synthetic */ pn5(Function5 function5) {
        this.b = function5;
    }

    @Override // io.vavr.Function5
    public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.b.apply(obj5, obj4, obj3, obj2, obj);
    }
}
