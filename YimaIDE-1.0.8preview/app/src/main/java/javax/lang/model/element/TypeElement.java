package javax.lang.model.element;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TypeElement extends Element, Parameterizable, QualifiedNameable {
    @Override // javax.lang.model.element.Element
    TypeMirror asType();

    @Override // javax.lang.model.element.Element
    List<? extends Element> getEnclosedElements();

    @Override // javax.lang.model.element.Element
    Element getEnclosingElement();

    List<? extends TypeMirror> getInterfaces();

    NestingKind getNestingKind();

    default List<? extends TypeMirror> getPermittedSubclasses() {
        return Collections.unmodifiableList(Arrays.asList(new TypeMirror[0]));
    }

    Name getQualifiedName();

    default List<? extends RecordComponentElement> getRecordComponents() {
        return Collections.unmodifiableList(Arrays.asList(new RecordComponentElement[0]));
    }

    @Override // javax.lang.model.element.Element
    Name getSimpleName();

    TypeMirror getSuperclass();

    @Override // javax.lang.model.element.Parameterizable
    List<? extends TypeParameterElement> getTypeParameters();
}
