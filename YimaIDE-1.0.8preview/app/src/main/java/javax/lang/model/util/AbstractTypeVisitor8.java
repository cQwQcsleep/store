package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.IntersectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public abstract class AbstractTypeVisitor8<R, P> extends AbstractTypeVisitor7<R, P> {
    public abstract R visitIntersection(IntersectionType intersectionType, P p);
}
