package defpackage;

import io.vavr.collection.Tree;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class vle implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Tree.Node) obj).getValue();
    }
}
