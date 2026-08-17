package javax.lang.model.element;

import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface VariableElement extends Element {
    @Override // javax.lang.model.element.Element
    TypeMirror asType();

    Object getConstantValue();

    @Override // javax.lang.model.element.Element
    Element getEnclosingElement();

    @Override // javax.lang.model.element.Element
    Name getSimpleName();

    default boolean isUnnamed() {
        return getSimpleName().length() == 0;
    }
}
