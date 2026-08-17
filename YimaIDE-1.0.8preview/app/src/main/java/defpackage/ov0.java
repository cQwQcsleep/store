package defpackage;

import io.vavr.Function1;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ov0 implements Function1, Serializable {
    @Override // io.vavr.Function1, java.util.function.Function
    public final Object apply(Object obj) {
        return Integer.valueOf(((Character) obj).charValue());
    }
}
