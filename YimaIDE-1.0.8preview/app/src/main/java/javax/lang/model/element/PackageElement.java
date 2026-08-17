package javax.lang.model.element;

import java.util.List;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PackageElement extends Element, QualifiedNameable {
    @Override // javax.lang.model.element.Element
    TypeMirror asType();

    @Override // javax.lang.model.element.Element
    List<? extends Element> getEnclosedElements();

    @Override // javax.lang.model.element.Element
    Element getEnclosingElement();

    @Override // javax.lang.model.element.QualifiedNameable
    Name getQualifiedName();

    @Override // javax.lang.model.element.Element
    Name getSimpleName();

    boolean isUnnamed();
}
