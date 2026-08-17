package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.UnknownEntityException;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.UnknownTypeException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public abstract class AbstractTypeVisitor6<R, P> implements TypeVisitor<R, P> {
    @Deprecated
    public AbstractTypeVisitor6() {
    }

    @Override // javax.lang.model.type.TypeVisitor
    public final R visit(TypeMirror typeMirror) {
        return (R) typeMirror.accept(this, null);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitIntersection(IntersectionType intersectionType, P p) {
        return visitUnknown(intersectionType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitUnion(UnionType unionType, P p) {
        return visitUnknown(unionType, p);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.lang.model.UnknownEntityException */
    @Override // javax.lang.model.type.TypeVisitor
    public R visitUnknown(TypeMirror typeMirror, P p) throws UnknownEntityException {
        throw new UnknownTypeException(typeMirror, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public final R visit(TypeMirror typeMirror, P p) {
        return (R) typeMirror.accept(this, p);
    }
}
