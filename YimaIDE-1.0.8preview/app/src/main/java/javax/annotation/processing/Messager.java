package javax.annotation.processing;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Messager {
    default void printError(CharSequence charSequence) {
        printMessage(Diagnostic.Kind.ERROR, charSequence);
    }

    void printMessage(Diagnostic.Kind kind, CharSequence charSequence);

    void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element);

    void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationMirror annotationMirror);

    void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue);

    default void printNote(CharSequence charSequence) {
        printMessage(Diagnostic.Kind.NOTE, charSequence);
    }

    default void printWarning(CharSequence charSequence) {
        printMessage(Diagnostic.Kind.WARNING, charSequence);
    }

    default void printError(CharSequence charSequence, Element element) {
        printMessage(Diagnostic.Kind.ERROR, charSequence, element);
    }

    default void printNote(CharSequence charSequence, Element element) {
        printMessage(Diagnostic.Kind.NOTE, charSequence, element);
    }

    default void printWarning(CharSequence charSequence, Element element) {
        printMessage(Diagnostic.Kind.WARNING, charSequence, element);
    }
}
