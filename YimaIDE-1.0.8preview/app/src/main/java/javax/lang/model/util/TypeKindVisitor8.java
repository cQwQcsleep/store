package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.IntersectionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TypeKindVisitor8<R, P> extends TypeKindVisitor7<R, P> {
    public TypeKindVisitor8() {
        super(null);
    }

    public R visitIntersection(IntersectionType intersectionType, P p) {
        return (R) defaultAction(intersectionType, p);
    }

    public TypeKindVisitor8(R r) {
        super(r);
    }
}
