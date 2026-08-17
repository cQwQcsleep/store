package defpackage;

import io.vavr.Function1;
import io.vavr.collection.Map;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class it9 implements Function1, Serializable {
    public final /* synthetic */ Map b;
    public final /* synthetic */ Object c;

    public /* synthetic */ it9(Map map, Object obj) {
        this.b = map;
        this.c = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.get(obj).getOrElse(this.c);
    }
}
