package defpackage;

import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class iw0 implements Function1, Serializable {
    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Character.valueOf((char) ((Integer) obj).intValue());
    }
}
