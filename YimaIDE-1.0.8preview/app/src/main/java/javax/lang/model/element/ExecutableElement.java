package javax.lang.model.element;

import java.util.List;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExecutableElement extends Element, Parameterizable {
    @Override // javax.lang.model.element.Element
    TypeMirror asType();

    AnnotationValue getDefaultValue();

    @Override // javax.lang.model.element.Element
    Element getEnclosingElement();

    List<? extends VariableElement> getParameters();

    TypeMirror getReceiverType();

    TypeMirror getReturnType();

    @Override // javax.lang.model.element.Element
    Name getSimpleName();

    List<? extends TypeMirror> getThrownTypes();

    @Override // javax.lang.model.element.Parameterizable
    List<? extends TypeParameterElement> getTypeParameters();

    boolean isDefault();

    boolean isVarArgs();
}
