package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.IntersectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SimpleTypeVisitor8<R, P> extends SimpleTypeVisitor7<R, P> {
    public SimpleTypeVisitor8() {
        super(null);
    }

    public R visitIntersection(IntersectionType intersectionType, P p) {
        return (R) defaultAction(intersectionType, p);
    }

    public SimpleTypeVisitor8(R r) {
        super(r);
    }
}
