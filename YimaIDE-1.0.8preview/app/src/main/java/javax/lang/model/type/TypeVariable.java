package javax.lang.model.type;

import javax.lang.model.element.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TypeVariable extends ReferenceType {
    Element asElement();

    TypeMirror getLowerBound();

    TypeMirror getUpperBound();
}
