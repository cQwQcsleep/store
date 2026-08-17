package defpackage;

import com.intellij.util.Function;
import com.intellij.util.containers.FilteredTraverserBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final /* synthetic */ class ns4 implements Function {
    public final /* synthetic */ FilteredTraverserBase.Meta b;

    public /* synthetic */ ns4(FilteredTraverserBase.Meta meta) {
        this.b = meta;
    }

    @Override // com.intellij.util.Function
    public final Object fun(Object obj) {
        return this.b.children(obj);
    }
}
