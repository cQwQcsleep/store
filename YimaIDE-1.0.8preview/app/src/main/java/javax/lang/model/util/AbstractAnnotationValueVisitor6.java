package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.UnknownEntityException;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.UnknownAnnotationValueException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public abstract class AbstractAnnotationValueVisitor6<R, P> implements AnnotationValueVisitor<R, P> {
    @Deprecated
    public AbstractAnnotationValueVisitor6() {
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public final R visit(AnnotationValue annotationValue) {
        return (R) annotationValue.accept(this, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.lang.model.UnknownEntityException */
    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitUnknown(AnnotationValue annotationValue, P p) throws UnknownEntityException {
        throw new UnknownAnnotationValueException(annotationValue, p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public final R visit(AnnotationValue annotationValue, P p) {
        return (R) annotationValue.accept(this, p);
    }
}
