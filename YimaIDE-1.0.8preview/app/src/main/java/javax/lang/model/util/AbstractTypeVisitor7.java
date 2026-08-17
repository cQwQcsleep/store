package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.UnionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public abstract class AbstractTypeVisitor7<R, P> extends AbstractTypeVisitor6<R, P> {
    @Deprecated
    public AbstractTypeVisitor7() {
    }

    @Override // javax.lang.model.util.AbstractTypeVisitor6, javax.lang.model.type.TypeVisitor
    public abstract R visitUnion(UnionType unionType, P p);
}
