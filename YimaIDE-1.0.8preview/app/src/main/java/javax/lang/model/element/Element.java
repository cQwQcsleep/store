package javax.lang.model.element;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.lang.model.AnnotatedConstruct;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Element extends AnnotatedConstruct {
    <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p);

    TypeMirror asType();

    boolean equals(Object obj);

    @Override // javax.lang.model.AnnotatedConstruct
    <A extends Annotation> A getAnnotation(Class<A> cls);

    @Override // javax.lang.model.AnnotatedConstruct
    List<? extends AnnotationMirror> getAnnotationMirrors();

    @Override // javax.lang.model.AnnotatedConstruct
    <A extends Annotation> A[] getAnnotationsByType(Class<A> cls);

    List<? extends Element> getEnclosedElements();

    Element getEnclosingElement();

    ElementKind getKind();

    Set<Modifier> getModifiers();

    Name getSimpleName();

    int hashCode();
}
