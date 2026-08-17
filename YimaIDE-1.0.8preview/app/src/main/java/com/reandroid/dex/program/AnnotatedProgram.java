package com.reandroid.dex.program;

import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.TypeKey;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AnnotatedProgram {
    default void addAnnotation(AnnotationItemKey annotationItemKey) {
        setAnnotation(getAnnotation().add(annotationItemKey));
    }

    void clearAnnotations();

    default AnnotationItemKey getAnnotation(TypeKey typeKey) {
        return getAnnotation().get(typeKey);
    }

    AnnotationSetKey getAnnotation();

    default boolean hasAnnotation(TypeKey typeKey) {
        return getAnnotation(typeKey) != null;
    }

    default boolean hasAnnotations() {
        return !getAnnotation().isEmpty();
    }

    default boolean removeAnnotation(TypeKey typeKey) {
        AnnotationSetKey annotation = getAnnotation();
        AnnotationSetKey annotationSetKeyRemove = annotation.remove(typeKey);
        if (annotation.equals(annotationSetKeyRemove)) {
            return false;
        }
        setAnnotation(annotationSetKeyRemove);
        return true;
    }

    default boolean removeAnnotationIf(Predicate<? super AnnotationItemKey> predicate) {
        AnnotationSetKey annotation = getAnnotation();
        AnnotationSetKey annotationSetKeyRemoveIf = annotation.removeIf(predicate);
        if (annotation.equals(annotationSetKeyRemoveIf)) {
            return false;
        }
        setAnnotation(annotationSetKeyRemoveIf);
        return true;
    }

    void setAnnotation(AnnotationSetKey annotationSetKey);
}
