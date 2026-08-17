package defpackage;

import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class lka implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Map.Entry) obj).getKey();
    }
}
