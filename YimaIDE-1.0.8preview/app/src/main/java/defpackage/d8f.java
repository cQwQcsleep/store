package defpackage;

import java.util.HashSet;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class d8f implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new HashSet(((Integer) obj).intValue());
    }
}
