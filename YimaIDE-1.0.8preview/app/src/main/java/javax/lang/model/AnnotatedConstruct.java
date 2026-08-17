package javax.lang.model;

import java.lang.annotation.Annotation;
import java.util.List;
import javax.lang.model.element.AnnotationMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AnnotatedConstruct {
    <A extends Annotation> A getAnnotation(Class<A> cls);

    List<? extends AnnotationMirror> getAnnotationMirrors();

    <A extends Annotation> A[] getAnnotationsByType(Class<A> cls);
}
