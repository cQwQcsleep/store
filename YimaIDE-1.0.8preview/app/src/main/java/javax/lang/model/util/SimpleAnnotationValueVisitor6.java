package javax.lang.model.util;

import java.util.List;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SimpleAnnotationValueVisitor6<R, P> extends AbstractAnnotationValueVisitor6<R, P> {
    protected final R DEFAULT_VALUE;

    @Deprecated
    public SimpleAnnotationValueVisitor6() {
        this.DEFAULT_VALUE = null;
    }

    public R defaultAction(Object obj, P p) {
        return this.DEFAULT_VALUE;
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitAnnotation(AnnotationMirror annotationMirror, P p) {
        return defaultAction(annotationMirror, p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitArray(List<? extends AnnotationValue> list, P p) {
        return defaultAction(list, p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitBoolean(boolean z, P p) {
        return defaultAction(Boolean.valueOf(z), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitByte(byte b, P p) {
        return defaultAction(Byte.valueOf(b), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitChar(char c, P p) {
        return defaultAction(Character.valueOf(c), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitDouble(double d, P p) {
        return defaultAction(Double.valueOf(d), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitEnumConstant(VariableElement variableElement, P p) {
        return defaultAction(variableElement, p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitFloat(float f, P p) {
        return defaultAction(Float.valueOf(f), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitInt(int i, P p) {
        return defaultAction(Integer.valueOf(i), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitLong(long j, P p) {
        return defaultAction(Long.valueOf(j), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitShort(short s, P p) {
        return defaultAction(Short.valueOf(s), p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitString(String str, P p) {
        return defaultAction(str, p);
    }

    @Override // javax.lang.model.element.AnnotationValueVisitor
    public R visitType(TypeMirror typeMirror, P p) {
        return defaultAction(typeMirror, p);
    }

    @Deprecated
    public SimpleAnnotationValueVisitor6(R r) {
        this.DEFAULT_VALUE = r;
    }
}
