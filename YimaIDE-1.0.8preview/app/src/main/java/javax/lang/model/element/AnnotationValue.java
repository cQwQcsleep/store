package javax.lang.model.element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AnnotationValue {
    <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p);

    Object getValue();

    String toString();
}
