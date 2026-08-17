package defpackage;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final /* synthetic */ class ma2 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (List) ((Map.Entry) obj).getValue();
    }
}
