package javax.lang.model.element;

import java.util.List;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TypeParameterElement extends Element {
    @Override // javax.lang.model.element.Element
    TypeMirror asType();

    List<? extends TypeMirror> getBounds();

    @Override // javax.lang.model.element.Element
    Element getEnclosingElement();

    Element getGenericElement();
}
