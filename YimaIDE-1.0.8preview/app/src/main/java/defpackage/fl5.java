package defpackage;

import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class fl5 implements Function1, Serializable {
    public final /* synthetic */ Object b;

    public /* synthetic */ fl5(Object obj) {
        this.b = obj;
    }

    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Function1.ha(this.b, obj);
    }
}
