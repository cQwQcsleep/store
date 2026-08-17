package javax.lang.model.type;

import java.lang.annotation.Annotation;
import java.util.List;
import javax.lang.model.AnnotatedConstruct;
import javax.lang.model.element.AnnotationMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TypeMirror extends AnnotatedConstruct {
    <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p);

    boolean equals(Object obj);

    @Override // javax.lang.model.AnnotatedConstruct
    <A extends Annotation> A getAnnotation(Class<A> cls);

    @Override // javax.lang.model.AnnotatedConstruct
    List<? extends AnnotationMirror> getAnnotationMirrors();

    @Override // javax.lang.model.AnnotatedConstruct
    <A extends Annotation> A[] getAnnotationsByType(Class<A> cls);

    TypeKind getKind();

    int hashCode();

    String toString();
}
