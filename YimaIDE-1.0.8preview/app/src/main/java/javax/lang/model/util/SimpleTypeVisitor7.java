package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.UnionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SimpleTypeVisitor7<R, P> extends SimpleTypeVisitor6<R, P> {
    @Deprecated
    public SimpleTypeVisitor7() {
        super((Object) null);
    }

    public R visitUnion(UnionType unionType, P p) {
        return (R) defaultAction(unionType, p);
    }

    @Deprecated
    public SimpleTypeVisitor7(R r) {
        super(r);
    }
}
