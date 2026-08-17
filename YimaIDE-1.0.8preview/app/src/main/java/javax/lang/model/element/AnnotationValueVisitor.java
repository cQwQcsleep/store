package javax.lang.model.element;

import java.util.List;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AnnotationValueVisitor<R, P> {
    default R visit(AnnotationValue annotationValue) {
        return visit(annotationValue, null);
    }

    R visit(AnnotationValue annotationValue, P p);

    R visitAnnotation(AnnotationMirror annotationMirror, P p);

    R visitArray(List<? extends AnnotationValue> list, P p);

    R visitBoolean(boolean z, P p);

    R visitByte(byte b, P p);

    R visitChar(char c, P p);

    R visitDouble(double d, P p);

    R visitEnumConstant(VariableElement variableElement, P p);

    R visitFloat(float f, P p);

    R visitInt(int i, P p);

    R visitLong(long j, P p);

    R visitShort(short s, P p);

    R visitString(String str, P p);

    R visitType(TypeMirror typeMirror, P p);

    R visitUnknown(AnnotationValue annotationValue, P p);
}
