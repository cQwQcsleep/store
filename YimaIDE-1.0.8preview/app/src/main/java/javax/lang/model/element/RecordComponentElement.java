package javax.lang.model.element;

import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface RecordComponentElement extends Element {
    @Override // javax.lang.model.element.Element
    TypeMirror asType();

    ExecutableElement getAccessor();

    @Override // javax.lang.model.element.Element
    Element getEnclosingElement();

    @Override // javax.lang.model.element.Element
    Name getSimpleName();
}
